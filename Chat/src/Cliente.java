/**
 * @author Wesley Egberto de Brito
 * @author wesley_16738 Data: 27/07/2011
 */

import java.io.*;
import java.net.*;

public class Cliente {
	Socket s;
	PrintWriter bw;
	BufferedReader br;

	final String nick;
	final int id;
	final GUICliente guiCli;

	Thread t;

	public Cliente(int id, String nick, GUICliente guiCli) throws IOException, Exception {
		this.id = id;
		this.nick = nick;
		this.guiCli = guiCli;

		s = new Socket("127.0.0.1", 5000);

		bw = new PrintWriter(s.getOutputStream());
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));

		t = new Thread(new SocketServerReader());
		t.start();
	}

	// Envia uma mensagem
	public void sendMessage(String message) {
		if(s == null) {
			javax.swing.JOptionPane.showMessageDialog(null, "Sem conexão.");
			return;
		}

		bw.println(message);
		bw.flush();
	}

	// Classe para ficar recebendo mensagem do Servidor
	class SocketServerReader implements Runnable {
		java.util.Scanner sc;
		String message;

		public SocketServerReader() {
			try {
				sc = new java.util.Scanner(s.getInputStream());
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				while((message = sc.nextLine()) != null) {
					guiCli.txaConversa.setText(guiCli.txaConversa.getText() + "\n" + message);
				}
			} catch(java.util.NoSuchElementException ex) {
				javax.swing.JOptionPane.showMessageDialog(null, "Conexão com Servidor perdido. Tente mais tarde.");
				System.exit(0);
			}
		}
	}

}
