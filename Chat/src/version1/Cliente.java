/**
 * @author Wesley Egberto de Brito
 * @author wesley_16738 Data: 08/08/2011
 * 
 *         Chat BSE Technology V1.0
 */

package version1;

import java.io.*;
import java.net.*;

public class Cliente {
	private Socket s;
	private PrintWriter bw;

	private String nick = "Anonymous";
	private String ip = "172.16.0.239";
	private int porta = 5000;
	private GUICliente guiCli;

	private Thread t;
	private static Cliente cli = new Cliente();

	private Cliente() {
	}

	// GETTERS E SETTERS
	public void setIp(String ip) {
		if(ip.equals(""))
			ip = "172.16.0.239";
		this.ip = ip;
	}

	public String getIp() {
		return this.ip;
	}

	public void setPorta(int porta) {
		if(porta < 0)
			porta = 5000;
		this.porta = porta;
	}

	public int getPorta() {
		return this.porta;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getNick() {
		return this.nick;
	}

	public void setGuiCli(GUICliente guiCli) {
		this.guiCli = guiCli;
	}

	public GUICliente getGuiCli() {
		return this.guiCli;
	}

	public static Cliente getCliente() {
		return cli;
	}

	public void conectar() throws UnknownHostException, IOException {
		s = new Socket(this.ip, this.porta);

		bw = new PrintWriter(s.getOutputStream());

		sendMessage(getNick());
		t = new Thread(new SocketServerReader());
		t.start();
	}

	@SuppressWarnings("deprecation")
	public void desconectar() {
		t.stop();
		t.interrupt();
		try {
			s.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		s = null;
		bw = null;
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
					guiCli.txaConversa.setText(message + "\n" + guiCli.txaConversa.getText());
				}
			} catch(java.util.NoSuchElementException ex) {
				javax.swing.JOptionPane.showMessageDialog(null, "Conexão com Servidor perdido. Tente mais tarde.");
				guiCli.trancar();
				guiCli.desconectar();
			}
		}
	}

}
