package br.unifieo.sd.servidornomes.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.unifieo.sd.servidornomes.IllegalDataException;
import br.unifieo.sd.servidornomes.Settings;
import br.unifieo.sd.servidornomes.data.DnsConstants;
import br.unifieo.sd.servidornomes.data.ServerService;

/**
 * Resolu��o de Nomes: Escuta solicita��es dos clientes em busca de servidores de servi�o.
 *  - Receber� uma requisi��o no format: <TYPE>|<CONTENT>
 *     . TYPE: 1 - Chat, 2 - FTP e 3 - Servidor offline
 *     . CONTENT: IP e Porta do servidor com atualiza��o
 *  - Retornar� uma mensagem no format: <FLAG>|<CONTENT>
 *    . FLAG: 0 - Sucesso, 1 - Erro
 *    . CONTENT: Mensagem de erro
 */
public class NameResolutionProcess extends SocketProcess {

	ExecutorService executorService;
	private ServerSocket server;
	
	// Lista de servidores de servi�o de Chat e FTP
	private ServiceServerProcess servidoresChat;
	private ServiceServerProcess servidoresFtp;
	
	public NameResolutionProcess(ServiceServerProcess servidoresChat, ServiceServerProcess servidoresFtp) throws IOException {
		server = new ServerSocket(Settings.PORT_NAME_RESOLUTION);
		debug("Name Resolution", "Resolvedor de nomes registrado na porta " + server.getLocalPort());
				
		this.servidoresChat = servidoresChat;
		this.servidoresFtp = servidoresFtp;
		executorService = Executors.newCachedThreadPool();
	}

	public void run() {
		while(true) {
			try {
				// aguarda conex�es
				debug("Name Resolution", "Aguardando conex�es de clientes");
				final Socket socketClient = server.accept();
				
				if(socketClient == null)
					continue;
				// Executa o processamento em paralelo para liberar a conex�o para outros clientes
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						processRequest(socketClient);
					}
				});
			} catch (IOException e) {
				debug("Name Resolution", "Erro na solicita��o de resolu��o de nomes: " + e.getMessage());
			}
		}
	}
	
	protected void processRequest(Socket socketClient) {
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			debug("Name Resolution", "Cliente conectado");
			
			// Cria as streams para comunica��o
			br = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			pw = new PrintWriter(socketClient.getOutputStream());
			
			// Le a entrada para formatar e converter os dados
			String inputClient = br.readLine();
			if(inputClient == null) {
				throw new IllegalDataException("Entrada vazia");
			}
			
			// Extrai os dados da mensagem recebida
			short typeService = Short.parseShort(inputClient);
			
			// Efetua o processamento de acordo com o servi�o
			ServerService availableServer = null;
			switch(typeService) {
				// Trata solicita��es de chat
				case DnsConstants.SERVICE_CHAT:
					availableServer = servidoresChat.getAvailableServer();
					if(availableServer == null) {
						throw new IllegalStateException("Nenhum servidor disponivel");
					}
					debug("Name Resolution", "Retornado " + availableServer.getIp() + ":" + availableServer.getPort());
					pw.print(DnsConstants.FLAG_SUCCESS);
					pw.print("|");
					pw.print(availableServer.getIp());
					pw.print(":");
					pw.println(availableServer.getPort());
					break;

				// Trata solicita��es de FTP
				case DnsConstants.SERVICE_FTP:
					availableServer = servidoresFtp.getAvailableServer();
					if(availableServer == null) {
						throw new IllegalStateException("Nenhum servidor disponivel");
					}
					debug("Name Resolution", "Retornado " + availableServer.getIp() + ":" + availableServer.getPort());
					pw.print(DnsConstants.FLAG_SUCCESS);
					pw.print("|");
					pw.print(availableServer.getIp());
					pw.print(":");
					pw.println(availableServer.getPort());
					break;
					
				default:
					throw new IllegalDataException("Tipo de servi�o invalido");
			}
		} catch (NumberFormatException ex) {
			sendErrorMessage(socketClient, pw, "Dados invalidos");
		} catch (IllegalDataException | IllegalStateException ex) {
			sendErrorMessage(socketClient, pw, ex.getMessage());
		} catch (IOException e) {
			debug("Name Resolution", "Erro na solicita��o de resolu��o de nomes: " + e.getMessage());
		} finally {
			closeResources(socketClient, br, pw);
		}
	}
}
