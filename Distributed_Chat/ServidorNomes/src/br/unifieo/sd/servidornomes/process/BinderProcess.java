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
 * Binder: Escutando solicitações de registro dos servidores de serviço.
 *  - Receberá uma requisição no format: <TYPE>|<IP:PORT>|<SYNC_PORT>|<POWER>
 *    . TYPE: 1 - Chat e 2 - FTP
 *    . IP:PORT - IP e Porta do serviço no servidor
 *    . SYNC_PORT - Porta de sincronização
 *    . POWER - Poder computacional: 1 (pouco) à 5 (muito)
 *  - Retornará uma mensagem no format: <FLAG>|<CONTENT>
 *    . FLAG: 0 - Sucesso, 1 - Erro
 *    . CONTENT: Mensagem de erro
 */
public class BinderProcess extends SocketProcess implements Runnable {

	ExecutorService executorService;
	private ServerSocket server;
	
	// Lista de servidores de serviço de Chat e FTP
	private ServiceServerProcess listaServidoresChat;
	private ServiceServerProcess listaServidoresFtp;
	
	
	public BinderProcess(ServiceServerProcess servidoresChat, ServiceServerProcess servidoresFtp) throws IOException {
		// Cria o socket do servidor
		server = new ServerSocket(Settings.PORT_BINDER);
		debug("Binder", "Registrador de servidores de serviço registrado na porta " + server.getLocalPort());
		
		this.listaServidoresChat = servidoresChat;
		this.listaServidoresFtp = servidoresFtp;
		executorService = Executors.newCachedThreadPool();
	}
	
	public void run() {
		
		while(true) {
			try {
				// aguarda conexões
				debug("Binder", "Aguardando conexões de servidores de serviço");
				final Socket socketClient = server.accept();
				
				if(socketClient == null)
					continue;
				// Executa o processamento em paralelo para liberar a conexão para outros clientes
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						processRequest(socketClient);
					}
				});
			} catch (IOException e) {
				debug("Binder", "Erro na solicitação de resolução de nomes: " + e.getMessage());
			}
		}
	}
	
	protected void processRequest(Socket socketClient) {
		BufferedReader br = null;
		PrintWriter pw = null;
		try {
			debug("Binder", "Servidor de serviço conectado");
			
			// Cria as streams para comunicação
			br = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			pw = new PrintWriter(socketClient.getOutputStream());
			
			// Le a entrada para formatar e converter os dados
			String inputClient = br.readLine();
			if(inputClient == null) {
				throw new IllegalDataException("Entrada vazia");
			}
			
			String[] splittedData = inputClient.split("\\|");
			if(splittedData.length != 4) {
				throw new IllegalDataException("Formato invalido");
			}
			// Extrai os dados da mensagem recebida
			short typeCommucation = Short.parseShort(splittedData[0]);
			String ipPort = splittedData[1];
			String portSync = splittedData[2];
			short power = Short.parseShort(splittedData[3]);

			// Valida a porta
			if(!ipPort.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}:\\d{1,5}")) {
				throw new IllegalDataException("IP ou Porta invalidos");
			}
			if(!portSync.matches("\\d{1,5}")) {
				throw new IllegalDataException("Porta de sincronização invalida");
			}
			// Valida o poder computacional
			if(power < 1 || power > 5) {
				throw new IllegalDataException("Poder computacional invalido");
			}
			
			// Cria o objeto que representa o serviço
			ServerService serverService = new ServerService();
			serverService.setIp(ipPort.split(":")[0]);
			serverService.setPort(Short.parseShort(ipPort.split(":")[1]));
			serverService.setSyncPort(Short.parseShort(portSync));
			
			// Valida e adiciona de acordo com o tipo de serviço
			switch(typeCommucation) {
				case DnsConstants.SERVICE_CHAT:
					listaServidoresChat.addServer(serverService);
					break;
				case DnsConstants.SERVICE_FTP:
					listaServidoresFtp.addServer(serverService);
					break;
				default:
					throw new IllegalDataException("Tipo de serviço invalido");
			}
			
			// Envia mensagem de sucesso
			pw.println(DnsConstants.FLAG_SUCCESS);
			debug("Binder", "Servidor de serviço registrado com sucesso");
		} catch (NumberFormatException ex) {
			sendErrorMessage(socketClient, pw, "Dados invalidos");
		} catch (IllegalDataException ex) {
			sendErrorMessage(socketClient, pw, ex.getMessage());
		} catch (IOException e) {
			debug("Binder", "Erro na solicitação de resolução de nomes: " + e.getMessage());
		} finally {
			closeResources(socketClient, br, pw);
		}
	}
}
