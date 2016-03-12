package serverChatReader.unifieo.sd.clientchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;

import serverChatReader.unifieo.sd.clientchat.ui.GuiClient;
import serverChatReader.unifieo.sd.clientchat.ui.GuiPvtChat;

public class ClientChat {

	private String serverChatIp;
	private int serverChatPort;

	private GuiClient guiClient;

	private Socket serverChatSocket;
	private PrintWriter serverChatWriter;
	private BufferedReader serverChatReader;

	private List<String> usersChat = new ArrayList<>();

	private Map<String, GuiPvtChat> listPvtChats = new HashMap<>();

	public Map<String, GuiPvtChat> getListPvtChats() {
		return listPvtChats;
	}

	public void requestServerChatAddress() {
		Socket socket = null;
		PrintWriter pw = null;
		BufferedReader br = null;

		// Primeiro tenta no DNS 1
		String ipToTry = Settings.DNS_1_IP;
		int portToTry = Settings.DNS_1_PORT;
		
		System.out.println("[ClientChat] Efetuando consulta no DNS");
		for (int i = 0; i < 6; i++) {
			// Se tentou 3 vezes, então muda para o DNS 2
			if (i > 2) {
				ipToTry = Settings.DNS_2_IP;
				portToTry = Settings.DNS_2_PORT;
			}
			try {
				socket = new Socket(ipToTry, portToTry);

				pw = new PrintWriter(socket.getOutputStream());
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				// Requisita Servidor de Chat
				pw.println("1");
				pw.flush();

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}

				String retorno = br.readLine();
				System.out.println("[ClientChat] DNS retornou: " + retorno);

				String[] splittedData = retorno.split("\\|");

				if (splittedData == null || splittedData.length != 2) {
					throw new IllegalDataException("Dados inválidos");
				}

				// Valida os dados recebidos
				if (!splittedData[1].matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}:\\d{1,5}")) {
					throw new IllegalDataException("IP ou Porta invalidos");
				}

				String[] ipPort = splittedData[1].split(":");
				serverChatIp = ipPort[0];
				serverChatPort = Integer.parseInt(ipPort[1]);
				break;
			} catch (IllegalDataException | NumberFormatException | ArrayIndexOutOfBoundsException ex) {
				JOptionPane.showMessageDialog(null, "Dados recebidos são inválidos: " + ex.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
				serverChatIp = null;
				serverChatPort = 0;
				break;
			} catch (Exception ex) {
				guiClient.appendMessage("Efetuando nova tentativa...");
				continue;
			} finally {
				if (pw != null) {
					pw.flush();
					pw.close();
				}
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
					}
				}
				if (socket != null && !socket.isClosed()) {
					try {
						socket.close();
					} catch (IOException e) {
					}
				}
			}
		}

		if (serverChatIp == null || serverChatPort == 0) {
			System.out.println("[ClientChat] Não foi possível se conectar no DNS");
			JOptionPane.showMessageDialog(null, "Não foi possível obter endereço do Servidor de Chat, tente mais tarde.",
					"Erro", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}

	public boolean connectServerChat(String ip, int port) {
		// Tenta se conectar no DNS
		System.out.println("[ClientChat] Conectando ao Servidor de Chat: " + ip + ":" + port);
		for (int i = 0; i < 3; i++) {
			try {
				serverChatSocket = new Socket(ip, port);
				break;
			} catch (IOException e) {
				System.out.printf("[ClientChat] Tentativa %d: %s%n", i + 1, e.getMessage());
			}
		}
		if (serverChatSocket == null || !serverChatSocket.isConnected()) {
			System.out.println("[ClientChat] Não foi possível se conectar no Servidor de Chat: " + ip + ":" + port);
			return false;
		}

		// Conseguiu se conectar, então envia os dados de configuração
		try {
			serverChatWriter = new PrintWriter(serverChatSocket.getOutputStream());
			serverChatReader = new BufferedReader(new InputStreamReader(serverChatSocket.getInputStream()));

			// Formata os dados para serem enviados ao DNS
			serverChatWriter.print("1|");
			serverChatWriter.println(guiClient.getUsername());
			serverChatWriter.flush();

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
			String retorno = serverChatReader.readLine();

			// Se retornar OK inicia a sincronização
			if ("0".equals(retorno)) {
				System.out.println("[ClientChat] Conectou e sem necessidade de sincronização");
			} else if ("2".equals(retorno)) {
				System.out.println("[ClientChat] Iniciando sincronização");
				String syncData[] = null;
				usersChat = new ArrayList<>();
				while (true) {
					// Lê o nome do usuário
					retorno = serverChatReader.readLine();
					if ("4".equals(retorno)) {
						break;
					}
					System.out.println(retorno);
					syncData = retorno.split("\\|");
					usersChat.add(syncData[1]);
				}
				System.out.println("[ClientChat] Sincronização finalizada");
				guiClient.updateUserList(usersChat);
			} else {
				System.out.println("[ClientChat] Não foi possível se conectar");
				return false;
			}

			guiClient.appendMessage("Conectado.");
			return true;
		} catch (IOException e) {
			System.out.println("[ClientChat] Erro durante a conexão com o Servidor de Chat " + ip + ":" + port + " : " + e.getMessage());
		}
		return false;
	}

	public void disconnect() {
		if (serverChatWriter != null) {
			serverChatWriter.flush();
			serverChatWriter.close();
			serverChatWriter = null;
		}
		if (serverChatReader != null) {
			try {
				serverChatReader.close();
			} catch (IOException e) {
			}
		}
		if (serverChatSocket != null) {
			try {
				serverChatSocket.close();
			} catch (IOException e) {
			}
		}
	}

	public void start() {
		// Inicializa a tela de chat
		guiClient = new GuiClient();
		guiClient.appendMessage("Efetuando conexão...");

		// Instância o gerenciador do cache de servidores
		ServerCacheManager cache = new ServerCacheManager();
		String ipPortCached = cache.nextServer();
		while(ipPortCached != null) {
			String[] ipPort = ipPortCached.split(":");
			serverChatIp = ipPort[0];
			serverChatPort = Integer.parseInt(ipPort[1]);
			
			// Efetua conexão no servidor de chat
			if(connectServerChat(serverChatIp, serverChatPort)) {
				break;
			}
			
			// Remove do cache e solicita o próximo
			cache.removeServer(ipPortCached);
			ipPortCached = cache.nextServer();
			serverChatIp = null; // Flag para informar erro após finalizar os itens em cache
		}
		
		// Se não conseguiu se conectar
		if (serverChatIp == null || serverChatPort == 0) {
			// Solicita o endereço do servidor de chat ao DNS
			requestServerChatAddress();
	
			// Efetua conexão no servidor de chat
			if(!connectServerChat(serverChatIp, serverChatPort)) {
				JOptionPane.showMessageDialog(null, "Não foi possível se conectar no Servidor de Chat, tente mais tarde.",
						"Erro", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
			cache.addServer(serverChatIp, serverChatPort);
		}
		
		guiClient.setController(this);

		Thread threadSocketListener = new Thread(new SocketReaderListener(serverChatReader));
		threadSocketListener.start();
		
		// Salva no cache
		cache.saveCache();
	}

	private class SocketReaderListener implements Runnable {
		private Scanner sc;

		public SocketReaderListener(BufferedReader reader) {
			this.sc = new Scanner(reader);
		}

		public void run() {
			String message = null;
			try {
				while ((message = sc.nextLine()) != null) {
					System.out.println("Received: " + message);
					if (message.length() > 0 && message.contains("|")) {
						String[] data = message.split("\\|");

						if ("2".equals(data[0])) { // Usuário saiu
							if (usersChat.contains(data[1])) {
								usersChat.remove(data[1]);
								guiClient.updateUserList(usersChat);
								guiClient.appendMessage(data[2]);
							}

						} else if ("1".equals(data[0])) { // Usuário entrou
							usersChat.add(data[1]);
							guiClient.updateUserList(usersChat);
							guiClient.appendMessage(data[2]);

						} else if ("3".equals(data[0])) { // Usuário entrou
							guiClient.appendMessage(String.format("%s enviou:\n%s", data[1], data[2]));

						} else if ("4".equals(data[0])) {
							if(!listPvtChats.containsKey(data[1])) {
								guiClient.startPrivateChat(data[1]);
							}
							GuiPvtChat guiPvtChat = listPvtChats.get(data[1]);
							guiPvtChat.setVisible(true);
							guiPvtChat.appendMessage(String.format("%s enviou:\n%s", data[1], data[2]));
						}
					}
				}
			} catch (Exception ex) {
				System.out.println("[User Listener] Erro: " + ex.getMessage());
				JOptionPane.showMessageDialog(null, "Conexão com servidor perdida, por favor, reinicie a aplicação.",
						"Erro", JOptionPane.ERROR_MESSAGE);
			} finally {
				sc.close();
			}
		}

	}

	public void sendBroadcastMessage(String message) {
		if (serverChatWriter != null) {
			serverChatWriter.print("3|1|");
			serverChatWriter.println(message);
			serverChatWriter.flush();
		}
	}

	public void sendPrivateMessage(String usernameDest, String message) {
		if (serverChatWriter != null) {
			serverChatWriter.print("4|");
			serverChatWriter.print(usernameDest);
			serverChatWriter.print("|");
			serverChatWriter.println(message);
			serverChatWriter.flush();
		}
	}
}
