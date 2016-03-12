package br.unifieo.sd.serverchat.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import br.unifieo.sd.serverchat.IllegalDataException;
import br.unifieo.sd.serverchat.ServerChat;
import br.unifieo.sd.serverchat.Settings;
import br.unifieo.sd.serverchat.data.GroupManager;
import br.unifieo.sd.serverchat.data.ServerConstants;
import br.unifieo.sd.serverchat.data.UserChatListenerProcess;

/**
 * Atendente: Aguarda conex�es dos clientes, efetua o registro do cliente na sala e envia informa��es sobre a sala (usu�rios, etc) para o cliente.
 *  - Receber� os dados no formato <ACTION>|<NAME>:
 *    . ACTION: 1 - Conectar e 0 - Desconectar
 *    . NAME: Nome do usu�rio
 *  - Retornar� os dados no formato <TYPE>|<CONTENT>:
 *    . TYPE: 0 - Conectado, 1 - Erro, 2 - In�cio da sincroniza��o, 3 - Envio de nome de usu�rio e 4 - Fim da sincroniza��o
 *    . CONTENT: Mensagem de erro ou nome do usu�rio
 */
public class ClientReceiverProcess extends SocketProcess {

	private ServerSocket server;
	
	private GroupManager groupManager;
	
	public ClientReceiverProcess(GroupManager chatUsers) throws IOException {
		// Inicia o server
		server = new ServerSocket(Settings.SERVER_CHAT_PORT);
		
		this.groupManager = chatUsers;
	}
	
	public void run() {
		while(true) {
			try {
				// aguarda conex�es
				debug("Client Receiver", "Aguardando conex�es de clientes");
				final Socket socketClient = server.accept();
				
				if(socketClient == null)
					continue;
				// Executa o processamento em paralelo para liberar a conex�o para outros clientes
				ServerChat.getExecutorService().execute(new Runnable() {
					@Override
					public void run() {
						processRequest(socketClient);
					}
				});
			} catch (IOException e) {
				debug("Client Receiver", "Erro na solicita��o de resolu��o de nomes: " + e.getMessage());
			}
		}
	}
	
	@Override
	protected void processRequest(Socket socketClient) {
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			debug("Client Receiver", "Cliente conectado");
			
			// Cria as streams para comunica��o
			br = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			pw = new PrintWriter(socketClient.getOutputStream());
			
			// Le a entrada para formatar e converter os dados
			String inputClient = br.readLine();
			if(inputClient == null) {
				throw new IllegalDataException("Entrada vazia");
			}
			
			String[] splittedData = inputClient.split("\\|");
			if(splittedData.length != 2) {
				throw new IllegalDataException("Formato invalido");
			}
			
			// Extrai os dados da mensagem recebida
			short action = Short.parseShort(splittedData[0]);
			String username = splittedData[1];

			// Valida e adiciona de acordo com o tipo de servi�o
			if(action != 1) {
				throw new IllegalDataException("A�ao invalida");
			}
			// Cria o cliente
			UserChatListenerProcess newClient = new UserChatListenerProcess();
			newClient.setUsername(username);
			newClient.setSocket(socketClient);
			newClient.setReader(br);
			newClient.setWriter(pw);
			
			if(groupManager.getChatUsers().size() > 0) {
				// Sincroniza o cliente
				pw.println("2");
				pw.flush();
				// Envia todos os usu�rios da sala
				for (UserChatListenerProcess otherClient : groupManager.getChatUsers()) {
					pw.print("3|");
					pw.println(otherClient.getUsername());
					pw.flush();
				}
				// Informa a finaliza��o da sincroniza��o
				pw.println("4");
				pw.flush();
			} else {
				pw.println("0");
				pw.flush();
			}
						
			// Inclui o usu�rio na lista
			groupManager.addUser(newClient);
			
			// Envia mensagem de sucesso
			pw.println(ServerConstants.FLAG_SUCCESS);
			pw.flush();
			debug("Client Receiver", "Usu�rio conectado com sucesso");
		} catch (NumberFormatException ex) {
			sendErrorMessage(socketClient, pw, "Dados invalidos");
			closeResources(socketClient, br, pw);
		} catch (IllegalDataException | IllegalStateException ex) {
			sendErrorMessage(socketClient, pw, ex.getMessage());
			closeResources(socketClient, br, pw);
		} catch (IOException e) {
			debug("Client Receiver", "Erro na solicita��o de resolu��o de nomes: " + e.getMessage());
			closeResources(socketClient, br, pw);
		}
	}
	
	
}
