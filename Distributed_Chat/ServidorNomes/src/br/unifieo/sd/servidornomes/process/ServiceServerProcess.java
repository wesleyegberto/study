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
 * Classe que gerencia os servidores de serviço de chat registrados.
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
	 * Retorna um servidor de serviço disponível efetuando balanceamento.
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
	 * Efetua sincronização de cada servidor de serviço.
	 */
	public void sync() {
		ListIterator<ServerService> listIterator = serverChatList.listIterator();
		while(listIterator.hasNext()) {
			final ServerService serverService = listIterator.next();
			// Inicia a sincronização em paralelo
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					Socket syncSocket = null;
					BufferedReader br = null;
					PrintWriter pw = null;
					
					try {
						System.out.println("[Sync DNS] Sincronizando " + serverService);
						
						// Conecta no serviço de sincronização
						syncSocket = new Socket(serverService.getIp(), serverService.getSyncPort());

						// Cria as streams para comunicação
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
							System.out.printf("[Sync DNS] Sincronização de %s efetuada: %d%n",
									serverService, serverService.getQtyClients());
						} else {
							System.out.printf("[Sync DNS] Erro na sincronização de %s: %s%n",
									serverService, splittedData[1]);
						}
					} catch (UnknownHostException | ConnectException e) {
						// Se não conseguiu sincronizar retira o servidor da lista
						System.out.printf("[Sync DNS] Não foi possível se conectar no %s%n",
								serverService, e.getMessage());
						listIterator.remove();
					} catch (NumberFormatException | IllegalDataException e) {
						System.out.printf("[Sync DNS] Retorno inválido de %s: %s%n",
								serverService, e.getMessage());
					} catch (IOException e) {
						System.out.printf("[Sync DNS] Erro ao efetuar sincronização do server %s: %s%n", 
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
