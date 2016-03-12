package serverChatReader.unifieo.sd.clientchat.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultCaret;

import serverChatReader.unifieo.sd.clientchat.ClientChat;

public class GuiPvtChat  extends JFrame {
	private static final long serialVersionUID = 1521841001990001983L;

	private JScrollPane scpMessage;
	private JTextArea txaMessages;
	private JButton btnSend, btnSair;
	private JPanel pnlActions;
	private JTextField txfMessage;

	private ClientChat controller;
	private String username;
	private String usernameDest;

	public GuiPvtChat(String username, String usernameDest, ClientChat controller) {
		this.username = username;
		this.usernameDest = usernameDest;
		this.controller = controller;
		
		initComponents();
	}

	private void initComponents() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}

		setSize(900, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setBackground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		setBackground(UIManager.getColor("Panel.background"));
		getContentPane().setLayout(new BorderLayout());

		LineBorder lineBorder = new LineBorder(SystemColor.textInactiveText);

		// Painel de mensagens
		txaMessages = new JTextArea(450, 350);
		txaMessages.setLineWrap(true);
		txaMessages.setEditable(false);
		txaMessages.setRows(20);
		txaMessages.setText("");
		((DefaultCaret) txaMessages.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scpMessage = new JScrollPane(txaMessages);
		scpMessage.setBorder(lineBorder);
		scpMessage.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scpMessage.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scpMessage.setAutoscrolls(true);
		getContentPane().add(scpMessage, BorderLayout.CENTER);

		// Lista de usuários logado
		JPanel pnlUserList = new JPanel(new BorderLayout());
		pnlUserList.setBorder(lineBorder);

		JLabel titleListUser = new JLabel("      Usuário Logados      ");
		titleListUser.setSize(350, 30);
		titleListUser.setHorizontalTextPosition(SwingConstants.CENTER);
		titleListUser.setSize(400, 30);
		titleListUser.setHorizontalAlignment(SwingConstants.CENTER);
		titleListUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnlUserList.add(titleListUser, BorderLayout.NORTH);

		// Painel de ações
		pnlActions = new JPanel();
		pnlActions.setLayout(new FlowLayout(3));
		pnlActions.setBorder(lineBorder);

		// JTextArea para mensagens
		txfMessage = new JTextField(65);
		txfMessage.setSize(400, 25);
		txfMessage.addKeyListener(new TxfMessageListener());
		pnlActions.add(txfMessage);

		btnSend = new JButton("Send");
		btnSend.addActionListener(new BtnSendListener());
		pnlActions.add(btnSend);

		btnSair = new JButton("Exit");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		pnlActions.add(btnSair);
		getContentPane().add(pnlActions, BorderLayout.SOUTH);

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});

		setTitle("Chat SD - " + username + " - PVT com " + usernameDest);
	}

	public void exit() {
		if (JOptionPane.showConfirmDialog(null, "Deseja fechar a conversa em privado?", "Chat SD",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			dispose();
		}
	}

	private class TxfMessageListener implements java.awt.event.KeyListener {
		@Override
		public void keyPressed(KeyEvent key) {
			if (key.getKeyChar() == KeyEvent.VK_ENTER) {
				btnSend.doClick();
				key.setKeyCode(0);
				txfMessage.setText("");
				txfMessage.requestFocus();
			}
		}

		@Override
		public void keyReleased(KeyEvent key) {
		}

		@Override
		public void keyTyped(KeyEvent key) {
		}

	}

	private class BtnSendListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (txfMessage.getText() == null || txfMessage.getText().equals(""))
				return;

			sendMessage(txfMessage.getText());
			txfMessage.setText("");
			txfMessage.requestFocus();
		}

	}

	public void appendMessage(String message) {
		txaMessages.append(message);
		txaMessages.append("\n");
	}

	private void sendMessage(String message) {
		appendMessage("Você enviou:");
		appendMessage(message);
		controller.sendPrivateMessage(usernameDest, message);
	}
}
