package br.unifieo.sd.servidornomes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.unifieo.sd.servidornomes.data.DnsConstants;
import br.unifieo.sd.servidornomes.data.ServerService;
import br.unifieo.sd.servidornomes.process.BinderProcess;
import br.unifieo.sd.servidornomes.process.NameResolutionProcess;
import br.unifieo.sd.servidornomes.process.ServiceServerProcess;
import br.unifieo.sd.servidornomes.process.SocketProcess;
import br.unifieo.sd.servidornomes.process.StatusVerificationProcess;
import br.unifieo.sd.servidornomes.process.SyncDnsProcess;

/**
 * Inicia o processo do servidor DNS. 
 */
public class DnsServer {
	private ExecutorService executorService;
	
	private ServiceServerProcess servidoresChat;
	private ServiceServerProcess servidoresFtp;
	
	// Processos
	private BinderProcess binderProcess;
	private NameResolutionProcess nameResolution;
	private StatusVerificationProcess statusProcess;
	private SyncDnsProcess syncDnsProcess;
	
	public DnsServer() {
		// Inicia o pool de thread
		executorService = Executors.newCachedThreadPool();
		this.servidoresChat = new ServiceServerProcess();
		this.servidoresFtp = new ServiceServerProcess();
	}

	public void start() {
		// Inicia os processos
		syncDns();
		
		// Processo de registro de servidores de serviço
		try {
			binderProcess = new BinderProcess(servidoresChat, servidoresFtp);
		} catch (IOException e) {
			System.out.println("Erro ao inicar registrador de servidores de serviço: " + e.getMessage());
			System.exit(1);
		}
		executorService.execute(binderProcess);
		
		// Processo de resolução de nomes
		try {
			nameResolution = new NameResolutionProcess(servidoresChat, servidoresFtp);
		} catch (IOException e) {
			System.out.println("Erro ao inicar resolvedor de nomes: " + e.getMessage());
			System.exit(1);
		} 
		executorService.execute(nameResolution);
		
		// Processo de sincronização de servidores de serviço
		try {
			statusProcess = new StatusVerificationProcess(servidoresChat, servidoresFtp);
		} catch (IOException e) {
			System.out.println("Erro ao inicar sincronizador: " + e.getMessage());
			System.exit(1);
		}
		executorService.execute(statusProcess);
				
		// Processo de sincronização entre os DNS
		try {
			syncDnsProcess = new SyncDnsProcess(servidoresChat, servidoresFtp);
		} catch (IOException e) {
			System.out.println("Erro ao inicar sincronizador do DNS: " + e.getMessage());
			System.exit(1);
		}
		executorService.execute(syncDnsProcess);
		
	}
	
	@SuppressWarnings("resource")
	public void syncDns() {
		Socket socketDns = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			SocketProcess.debug("Sync DNS Client", "Inicializando sincronização");
			// Efetua conexão no DNS para sincronização
			socketDns = new Socket(Settings.DNS_1_IP, Settings.DNS_1_PORT);
			
			// Cria as streams para comunicação
			br = new BufferedReader(new InputStreamReader(socketDns.getInputStream()));
			pw = new PrintWriter(socketDns.getOutputStream());
			
			// Envia flag informando pedido de sincronização
			pw.println("1");
			pw.flush();

			// Delay do envio
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
			
			// Aguarda o retorno do servidor
			String inputClient = null;
			while((inputClient = br.readLine()) != null) {
				// Verifica se recebeu a flag de finalização
				if("0".equals(inputClient)) {
					SocketProcess.debug("Sync DNS Client", "Sincronização finalizada");
					break;
				}
				SocketProcess.debug("Sync DNS Client", "Recebido: " + inputClient);
				// Formata os dados recebido
				String[] splittedData = inputClient.split("\\|");
				if(splittedData.length != 3 && splittedData.length != 5) {
					throw new IllegalDataException("Formato invalido");
				}
				
				// Extrai os dados da mensagem recebida
				short typeServer = Short.parseShort(splittedData[0]);
				String ipPort = splittedData[1];
				String portSync = splittedData[2];
	
				// Valida a IP e porta
				if(!ipPort.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}:\\d{1,5}")) {
					throw new IllegalDataException("IP ou Porta invalidos");
				}
				if(!portSync.matches("\\d{1,5}")) {
					throw new IllegalDataException("Porta de sincronização invalida");
				}
				
				// Cria o objeto que representa o serviço extraindo os dados que são comuns para o Chat e FTP
				ServerService serverService = new ServerService();
				serverService.setIp(ipPort.split(":")[0]);
				serverService.setPort(Short.parseShort(ipPort.split(":")[1]));
				serverService.setSyncPort(Short.parseShort(portSync));
				
				// Valida e adiciona de acordo com o tipo de serviço
				switch(typeServer) {
					case DnsConstants.SERVICE_CHAT:
						servidoresChat.addServer(serverService);
						break;
						
					case DnsConstants.SERVICE_FTP:
						// Extrai os dados específicos do FTP
						short power = Short.parseShort(splittedData[3]);
						
						// Valida o poder computacional
						if(power < 1 || power > 5) {
							throw new IllegalDataException("Poder computacional invalido");
						}
						serverService.setPower(power);
						serverService.setQtyClients(Integer.parseInt(splittedData[4]));
						
						servidoresFtp.addServer(serverService);
						break;
				}
			}
		} catch (NumberFormatException ex) {
			SocketProcess.sendErrorMessage(socketDns, pw, "Dados invalidos");
		} catch (IllegalDataException ex) {
			SocketProcess.sendErrorMessage(socketDns, pw, ex.getMessage());
		} catch (IOException e) {
			SocketProcess.debug("DNS", "Erro na sincronização: " + e.getMessage());
		} finally {
			SocketProcess.closeResources(socketDns, br, pw);
		}
	}
	
	public void end() {
		Properties properties = new Properties();
		properties.setProperty("status", "1");
		
		try {
			properties.store(new PrintWriter("config"), "Last state");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
