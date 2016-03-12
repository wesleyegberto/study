package br.unifieo.sd.servidornomes.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.unifieo.sd.servidornomes.IllegalDataException;
import br.unifieo.sd.servidornomes.data.ServerService;

/**
 * Classe que gerencia os servidores de servi�o de chat registrados.
 */
public class ServiceServerProcess {

	private LinkedList<ServerService> serverChatList = new LinkedList<>();
	private ExecutorService executorService;

	public ServiceServerProcess() {
		executorService = Executors.newCachedThreadPool();
	}

	public void addServer(ServerService server) {
		this.serverChatList.add(server);
	}

	/**
	 * Retorna um servidor de servi�o dispon�vel efetuando balanceamento.
	 */
	public ServerService getAvailableServer() {
		// Round-Robin
		ServerService server = serverChatList.poll();
		serverChatList.add(server);
		return server;
	}

	public LinkedList<ServerService> getServerChatList() {
		return serverChatList;
	}
	
	/**
	 * Efetua sincroniza��o de cada servidor de servi�o.
	 */
	public void sync() {
		ListIterator<ServerService> listIterator = serverChatList.listIterator();
		while(listIterator.hasNext()) {
			final ServerService serverService = listIterator.next();
			// Inicia a sincroniza��o em paralelo
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					Socket syncSocket = null;
					BufferedReader br = null;
					PrintWriter pw = null;
					
					try {
						System.out.println("[Sync DNS] Sincronizando " + serverService);
						
						// Conecta no servi�o de sincroniza��o
						syncSocket = new Socket(serverService.getIp(), serverService.getSyncPort());

						// Cria as streams para comunica��o
						br = new BufferedReader(new InputStreamReader(syncSocket.getInputStream()));
						pw = new PrintWriter(syncSocket.getOutputStream());
						
						pw.println("0|0");
						pw.flush();
						
						String response = br.readLine();
						if(response == null) {
							throw new IllegalDataException("Entrada vazia");
						}
						
						String[] splittedData = response.split("\\|");
						if(splittedData.length != 2) {
							throw new IllegalDataException("Formato invalido");
						}
						int status = Integer.parseInt(splittedData[0]);
						if(status == 0) {
							// Atualiza a quantidade de clientes conectados
							serverService.setQtyClients(Integer.parseInt(splittedData[1]));
							System.out.printf("[Sync DNS] Sincroniza��o de %s efetuada: %d%n",
									serverService, serverService.getQtyClients());
						} else {
							System.out.printf("[Sync DNS] Erro na sincroniza��o de %s: %s%n",
									serverService, splittedData[1]);
						}
					} catch (UnknownHostException | ConnectException e) {
						// Se n�o conseguiu sincronizar retira o servidor da lista
						System.out.printf("[Sync DNS] N�o foi poss�vel se conectar no %s%n",
								serverService, e.getMessage());
						listIterator.remove();
					} catch (NumberFormatException | IllegalDataException e) {
						System.out.printf("[Sync DNS] Retorno inv�lido de %s: %s%n",
								serverService, e.getMessage());
					} catch (IOException e) {
						System.out.printf("[Sync DNS] Erro ao efetuar sincroniza��o do server %s: %s%n", 
								serverService, e.getMessage());
					} finally {
						if(syncSocket != null && syncSocket.isConnected()) {
							if(pw != null) {
								pw.flush();
								pw.close();
							}
							if(br != null) {
								try {
									br.close();
								} catch (IOException e) {
								}
							}
							try {
								syncSocket.close();
							} catch (IOException e) {
							}
						}
					}

				}
			});

		}
	}
}
