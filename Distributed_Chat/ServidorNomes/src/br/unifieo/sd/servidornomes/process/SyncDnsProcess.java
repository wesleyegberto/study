package br.unifieo.sd.servidornomes.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.ListIterator;

import br.unifieo.sd.servidornomes.IllegalDataException;
import br.unifieo.sd.servidornomes.Settings;
import br.unifieo.sd.servidornomes.data.ServerService;

/**
 * <TYPE>|<IP>:<PORT>|<SYNC_PORT>
 */
public class SyncDnsProcess implements Runnable {

	private ServerSocket server;

	// Lista de servidores de serviço de Chat e FTP
	private ServiceServerProcess listaServidoresChat;
	private ServiceServerProcess listaServidoresFtp;
	
	public SyncDnsProcess(ServiceServerProcess listaServidoresChat,
			ServiceServerProcess listaServidoresFtp) throws IOException {
		server = new ServerSocket(Settings.DNS_1_PORT);
		
		this.listaServidoresChat = listaServidoresChat;
		this.listaServidoresFtp = listaServidoresFtp;
	}

	public void run() {
		Socket socketClient = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		
		while(true) {
			try {
				socketClient = server.accept();
				
				if(socketClient == null)
					continue;
				
				// Cria as streams para comunicação
				br = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
				pw = new PrintWriter(socketClient.getOutputStream());
				
				// Le a entrada para formatar e converter os dados
				String inputClient = br.readLine();
				if(inputClient == null) {
					throw new IllegalDataException("Entrada vazia");
				}
				
				// Pedido de sincronização
				if("1".equals(inputClient)) {
					SocketProcess.debug("Sync DNS", "Iniciando sincronização");
					ServerService servidor = null;
					
					// Envia os dados do servidor de chat no formato: <TYPE>|<IP>:<PORT>|<SYNC_PORT>
					LinkedList<ServerService> serverChatList = listaServidoresChat.getServerChatList();
					if(serverChatList.size() > 0) {
						servidor = serverChatList.getFirst();
						pw.print("2|");
						pw.print(servidor.getIp());
						pw.print(":");
						pw.print(servidor.getPort());
						pw.print("|");
						pw.print(servidor.getSyncPort());
						pw.println();
						pw.flush();
					}
					
					// Envia os dados dos servidores de FTP no formato: <TYPE>|<IP>:<PORT>|<SYNC_PORT>|<POWER>|<QTY_CLIENTS>
					ListIterator<ServerService> listIterator = listaServidoresFtp.getServerChatList().listIterator();
					while(listIterator.hasNext()) {
						servidor = listIterator.next();
						pw.print("3|");
						pw.print(servidor.getIp());
						pw.print(":");
						pw.print(servidor.getPort());
						pw.print("|");
						pw.print(servidor.getSyncPort());
						pw.print("|");
						pw.print(servidor.getPower());
						pw.print("|");
						pw.print(servidor.getQtyClients());
						pw.println();
						pw.flush();
					}
					// Finaliza a sincronização
					pw.println("0");
					pw.flush();
					
					SocketProcess.debug("Sync DNS", "Sincronização finalizada");
				}
			} catch (IllegalDataException | IllegalStateException ex) {
				SocketProcess.sendErrorMessage(socketClient, pw, ex.getMessage());
			} catch (IOException e) {
				SocketProcess.debug("Sync DNS", "Erro na solicitação de resolução de nomes: " + e.getMessage());
			} finally {
				SocketProcess.closeResources(socketClient, br, pw);
			}
		}
	}

}
