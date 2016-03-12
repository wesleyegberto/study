package br.unifieo.sd.serverchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.unifieo.sd.serverchat.data.GroupManager;
import br.unifieo.sd.serverchat.process.ClientReceiverProcess;
import br.unifieo.sd.serverchat.process.SyncProcess;

public class ServerChat {

	private static ExecutorService executorService = Executors.newCachedThreadPool();
	
	// Processos
	private ClientReceiverProcess clientReceiver;
	private SyncProcess syncProcess;

	public ServerChat() {
	}
	
	public static ExecutorService getExecutorService() {
		return executorService;
	}
	
	public boolean bindServer() {
		Socket socket = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		

		// Tenta se conectar no DNS
		short i = 1;
		System.out.println("[ServerChat] Conectando ao DNS 1");
		for (; i <= Settings.SERVER_QTY_TRY; i++) {
			try {
				socket = new Socket(Settings.DNS_1_IP, Settings.DNS_1_PORT);
				break;
			} catch (IOException e) {
				System.out.printf("[ServerChat] Tentativa %d: %s%n", i, e.getMessage());
			}
		}
		// Se tentou 3 vezes e não deu certo tenta no DNS 2
		if (socket == null || !socket.isConnected()) {
			System.out.println("[ServerChat] Conectando ao DNS 2");
			for (i = 1; i <= Settings.SERVER_QTY_TRY; i++) {
				try {
					socket = new Socket(Settings.DNS_2_IP, Settings.DNS_2_PORT);
					break;
				} catch (IOException e) {
					System.out.printf("[ServerChat] Tentativa %d: %s%n", i, e.getMessage());
				}
			}
		}
		if (socket == null || !socket.isConnected()) {
			System.out.println("[ServerChat] Não foi possível se conectar no DNS");
			System.exit(1);
		}

		// Conseguiu se conectar, então envia os dados de configuração
		try {
			pw = new PrintWriter(socket.getOutputStream());
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// Formata os dados para serem enviados ao DNS
			pw.print("1|");
			pw.print(socket.getLocalAddress().getHostAddress());
			pw.print(":");
			pw.print(Settings.SERVER_CHAT_PORT);
			pw.print("|");
			pw.print(Settings.SERVER_SYNC_PORT);
			pw.print("|");
			pw.println(Settings.SERVER_POWER);
			pw.flush();

			String retorno = br.readLine();
			if (!"0".equals(retorno)) {
				String[] data = retorno.split("\\|");
				System.out.println("[ServerChat] Erro ao efetuar registro no DNS: " + data[1]);
				System.exit(1);
			}
			return true;
		} catch (IOException e) {
			System.out.println("[ServerChat] Erro ao efetuar registro no DNS: " + e.getMessage());
			System.exit(1);
		} finally {
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
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}
		return false;
	}

	public void start() {
		// Efetua o registro do server no DNS
		bindServer();

		GroupManager groupManager = new GroupManager();
		// Inicia o processo de recepção de usuário do chay
		try {
			clientReceiver = new ClientReceiverProcess(groupManager);
		} catch (IOException e) {
			System.out.println("Erro ao inicar server: " + e.getMessage());
			System.exit(1);
		} 
		executorService.execute(clientReceiver);
		
		// Inicia o processo de sincronzação com o DNS
		try {
			syncProcess = new SyncProcess(groupManager);
		} catch (IOException e) {
			System.out.println("Erro ao inicar sincronizador: " + e.getMessage());
			System.exit(1);
		}
		executorService.execute(syncProcess);
	}

}
