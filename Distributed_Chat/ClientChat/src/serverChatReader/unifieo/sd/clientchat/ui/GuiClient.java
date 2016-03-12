package serverChatReader.unifieo.sd.clientchat.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DefaultCaret;

import serverChatReader.unifieo.sd.clientchat.ClientChat;

public class GuiClient extends JFrame {
	private static final long serialVersionUID = 5258849431992064983L;

	private JScrollPane scpMessage;
	private JTextArea txaMessages;
	private JList<String> userList;
	private JButton btnSend, btnSair;
	private JPanel pnlActions;
	private JTextField txfMessage;

	private ClientChat controller;
	private String username;

	public GuiClient() {
		initComponents();

		setVisible(true);
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

		// Lista de usu�rios logado
		JPanel pnlUserList = new JPanel(new BorderLayout());
		pnlUserList.setBorder(lineBorder);

		JLabel titleListUser = new JLabel("      Usu�rio Logados      ");
		titleListUser.setSize(350, 30);
		titleListUser.setHorizontalTextPosition(SwingConstants.CENTER);
		titleListUser.setSize(400, 30);
		titleListUser.setHorizontalAlignment(SwingConstants.CENTER);
		titleListUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnlUserList.add(titleListUser, BorderLayout.NORTH);

		userList = new JList<>();
		userList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(userList.getSelectedIndex() >= 0 && !e.getValueIsAdjusting()) {
					startPrivateChat(userList.getSelectedValue());
				}
			}
		});
		pnlUserList.add(userList, BorderLayout.CENTER);
		getContentPane().add(pnlUserList, BorderLayout.EAST);

		// Painel de a��es
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

		username = JOptionPane.showInputDialog("Seu nome no chat:");
		while (username == null || username.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nome inv�lido.", "Chat SD", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		setTitle("Chat SD - " + username);
	}

	public String getUsername() {
		return username;
	}
	
	public void setController(ClientChat controller) {
		this.controller = controller;
	}

	public void exit() {
		if (JOptionPane.showConfirmDialog(null, "Deseja sair?", "Chat SD",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			if(controller != null) {
				controller.disconnect();
			}
			System.exit(0);
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
		appendMessage("Voc� enviou:");
		appendMessage(message);
		controller.sendBroadcastMessage(message);
	}

	public void updateUserList(final List<String> users) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DefaultListModel<String> newUsersModel = new DefaultListModel<>();
				for (String username : users) {
					newUsersModel.addElement(username);
				}
				userList.setModel(newUsersModel);
			}
		});
	}

	@SuppressWarnings("all")
	public void startPrivateChat(String usernamePvtDest) {
		GuiPvtChat chatPvt = (GuiPvtChat) controller.getListPvtChats().get(usernamePvtDest);
		
		if(chatPvt == null) {
			chatPvt = new GuiPvtChat(username, usernamePvtDest, controller);
			controller.getListPvtChats().put(usernamePvtDest, chatPvt);
		}
		chatPvt.setVisible(true);
	}
}