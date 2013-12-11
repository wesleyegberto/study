/**
 * @author Wesley Egberto de Brito
 * @author wesley_16738 Data: 28/07/2011
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.*;

public class GUICliente extends JFrame {
	private static final long serialVersionUID = 5872223096760543628L;

	JButton btnSend, btnSair;
	JPanel panAcao;
	JScrollPane scpMessage;
	JTextArea txaConversa, txaMessage;

	String nick;
	Cliente myCon;
	static int numClients;

	// Construtores
	private GUICliente() {
		// Configuração da janela
		setSize(730, 400);
		setBackground(Color.white);
		setLayout(new BorderLayout());
		setResizable(false);

		// JTextArea para mostrar as mensagens de todos os usuários
		txaConversa = new JTextArea(100, 50);
		txaConversa.setLineWrap(true);
		txaConversa.setEditable(false);
		scpMessage = new JScrollPane(txaConversa);
		scpMessage.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scpMessage.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scpMessage, BorderLayout.CENTER);

		// Painel de mensagem
		panAcao = new JPanel();
		panAcao.setLayout(new FlowLayout(3));

		// JTextArea para mensagens
		txaMessage = new JTextArea(1, 50);
		txaMessage.setWrapStyleWord(false);
		txaMessage.setSize(25, 100);
		txaMessage.addKeyListener(new TxaMessageListener());
		panAcao.add(txaMessage);

		btnSend = new JButton("Send");
		btnSend.addActionListener(new BtnSendListener());
		panAcao.add(btnSend);

		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Deseja sair?") == 0)
					System.exit(0);
			}
		});
		panAcao.add(btnSair);
		add(panAcao, BorderLayout.SOUTH);

		WindowListener x = new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Deseja sair?") == 0)
					System.exit(0);
			}
		};
		addWindowListener(x);
		setVisible(true);
	}

	GUICliente(String user) {
		this();
		this.nick = user;
		setTitle("Usuário " + nick);
		conectar();
	}

	// Método que instância a classe Cliente, caso a conexão falhe é encerrado o
	// programa
	void conectar() {
		try {
			myCon = new Cliente(numClients++, nick, this);
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Conexão não estabelecida.");
			System.exit(1);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Erro.");
			System.exit(2);
		}
	}

	// Listener para botão de enviar de mensagem
	private class BtnSendListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(txaMessage.getText() == null || txaMessage.getText().equals(""))
				return;

			myCon.sendMessage(txaMessage.getText());
			txaMessage.setText("");
			txaMessage.requestFocus();
		}
	}

	// Listener para tecla <ENTER> poder enviar mensagem
	private class TxaMessageListener implements java.awt.event.KeyListener {
		@Override
		public void keyPressed(KeyEvent key) {
			if(key.getKeyChar() == KeyEvent.VK_ENTER) {
				btnSend.doClick();
				key.setKeyCode(0);
				txaMessage.setText("");
				txaMessage.requestFocus();
			}
		}

		@Override
		public void keyReleased(KeyEvent key) {
		}

		@Override
		public void keyTyped(KeyEvent key) {
		}

	}

	// Main
	public static void main(String[] args) {
		GUICliente chat;
		String nick = JOptionPane.showInputDialog("Insira um nick.");

		if(nick == null)
			chat = new GUICliente(numClients + "");
		else
			chat = new GUICliente(nick);

		// Envia uma mensagem para o Servidor poder identificá-lo
		chat.myCon.sendMessage(chat.nick);
	}

}
