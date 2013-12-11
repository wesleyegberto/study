/**
 * @author Wesley Egberto de Brito
 * @author wesley_16738 Data: 08/08/2011
 * 
 *         Chat BSE Technology V1.0
 */

package version1;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

class Servidor {
	ServerSocket ss;
	ArrayList<Thread> listClient = new ArrayList<Thread>();
	ArrayList<SocketClient> clients = new ArrayList<SocketClient>();
	PrintWriter pw;
	BufferedReader br;
	java.util.Date data = new java.util.Date();

	static int numClients = 0;

	// Construtor já disponibiliza a porta como servidor
	public Servidor() {
		try {
			ss = new ServerSocket(5000);
			System.out.println(ss.getInetAddress().getHostAddress());
		} catch(Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}
	}

	void go() {
		Thread cliente;
		Socket sClient;
		String nickClient;
		System.out.println("Servidor ligado...\n");
		SocketClient socCli;
		while(true) {
			try {
				sClient = ss.accept(); // Espera um novo cliente realizar um
										// contato
				if(sClient == null)
					continue;

				br = new BufferedReader(new InputStreamReader(sClient.getInputStream()));

				// Carrega o nick do novo cliente
				nickClient = br.readLine();

				// Cria uma thread separada para o novo cliente
				socCli = new SocketClient(numClients++, nickClient, sClient.getOutputStream(), sClient.getInputStream());
				clients.add(socCli);

				// Cria e adiciona a thread na lista
				cliente = new Thread(socCli);
				listClient.add(cliente);
				cliente.start();

				// Avisa a todos que um novo cliente na sala
				deliveryMessage(nickClient + " entrou no chat da BSE.");
			} catch(BindException ex) {
				System.out.println("Porta já está sendo usado.");
				System.exit(0);
			} catch(Exception ex) {
				ex.printStackTrace();
				System.exit(1);
			}
		}
	}

	// Método usado para mandar mensagem a todos os clientes
	synchronized void deliveryMessage(String message) {
		for(SocketClient s : clients) {
			if(s == null)
				continue;

			s.sendMessage(message);
		}
		System.out.println(message);
	}

	// Classe para ler mensagens do Servidor
	class SocketClient implements Runnable {
		final String nick;
		final int id;
		final PrintWriter pw;
		final java.util.Scanner sc;
		String message;

		public SocketClient(int i, String nick, OutputStream os, InputStream is) {
			this.nick = nick;
			this.id = i;
			this.pw = new PrintWriter(os);
			sc = new java.util.Scanner(is);
		}

		public void sendMessage(String message) {
			pw.println(message);
			pw.flush();
		}

		@SuppressWarnings({ "deprecation", "boxing" })
		@Override
		public void run() {
			try {
				while((message = sc.nextLine()) != null) {
					data.setTime(System.currentTimeMillis());
					deliveryMessage("    " + message + "\n" + nick + " says at "
							+ ((data.getHours() < 10) ? "0" + data.getHours() : data.getHours()) + ":"
							+ ((data.getMinutes() < 10) ? "0" + data.getMinutes() : data.getMinutes()) + ":"
							+ ((data.getSeconds() < 10) ? "0" + data.getSeconds() : data.getSeconds()) + " :");
				}
			} catch(java.util.NoSuchElementException ex) {
				deliveryMessage(nick + " saiu do chat.");
			}
		}
	}

	public static void main1(String[] args) {
		new Servidor().go();
	}
}
