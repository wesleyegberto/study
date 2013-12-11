/**
 * @author Wesley Egberto de Brito
 * @author wesley_16738 Data: 10/09/2011
 * 
 *         Chat BSE Technology V1.0
 */

package version1_2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class GUIServer extends JFrame {
	private static final long serialVersionUID = 985410248241554441L;

	private JMenuBar mbrPrinc;
	private JMenu mnuServer, mnuProperty, mnuAjuda;
	private JMenuItem mitLigar, mitDesligar, mitSair, mitSobre;
	private JPanel panNorte, panDadosServer, panMessageServer, panCentro, panClientes, panChat;
	private JLabel lblIpServer, lblPortServer, lblClientes, lblNickClient, lblIpClient, lblSendMessage, lblChat;
	private JCheckBox ckbEditar;
	private JTextField txfMessageClient, txfMessageServer;
	private JTextArea txaChat;
	private JScrollPane scpLstClientes, scpChat;
	private JList lstClientes;
	private JButton btnSendServer, btnSendClient, btnKick;

	private Servidor server;

	@SuppressWarnings("unused")
	private Socket mySocket;

	public GUIServer() {
		super("Server's Control BSE Technology");

		setSize(800, 600);
		setLocation(400, 200);
		setBackground(Color.white);
		setLayout(new BorderLayout());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		server = new Servidor();

		try {
			for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch(Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
		}
		// Criação dos Menus
		mnuServer = new JMenu("Servidor");
		mnuServer.setMnemonic('S');
		mitLigar = new JMenuItem("Ligar");
		mitLigar.setMnemonic('L');
		mnuServer.add(mitLigar);
		mitDesligar = new JMenuItem("Desligar");
		mitDesligar.setMnemonic('D');
		mnuServer.add(mitDesligar);
		mitSair = new JMenuItem("Sair");
		mitSair.setMnemonic('r');
		mitSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Deseja sair?") == 0)
					System.exit(0);
			}
		});
		mnuServer.add(mitSair);
		mnuProperty = new JMenu("Propriedades");
		mnuProperty.setMnemonic('P');
		mnuAjuda = new JMenu("Ajuda");
		mnuAjuda.setMnemonic('A');
		mitSobre = new JMenuItem("Sobre");
		mitSobre.setMnemonic('b');
		mitSobre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,
						"Criado por Wesley Egberto de Brito.\n\nDireitos reservado para BSE Technology.");
			}
		});
		mnuAjuda.add(mitSobre);

		mbrPrinc = new JMenuBar();
		mbrPrinc.add(mnuServer);
		mbrPrinc.add(mnuProperty);
		mbrPrinc.add(mnuAjuda);
		setJMenuBar(mbrPrinc);

		// Painel com os dados do Servidor
		lblIpServer = new JLabel();
		lblIpServer.setFont(new Font("Comics Sans", Font.PLAIN, 17));
		lblIpServer.setText("  IP: " + this.server.getSS().getInetAddress().getHostAddress() + "   ");
		lblPortServer = new JLabel("  Porta: " + server.getSS().getLocalPort() + "   ");
		lblPortServer.setFont(new Font("Comics Sans", Font.PLAIN, 17));

		panDadosServer = new JPanel();
		panDadosServer.setLayout(new FlowLayout());
		panDadosServer.setBorder(new javax.swing.border.LineBorder(Color.GRAY, 2, true));
		panDadosServer.add(lblIpServer);
		panDadosServer.add(lblPortServer);

		// Painel para enviar mensagem
		lblSendMessage = new JLabel("Send a message:");
		txfMessageServer = new JTextField(30);
		btnSendServer = new JButton("Send");
		panMessageServer = new JPanel();
		panMessageServer.setLayout(new FlowLayout());
		panMessageServer.setBorder(new javax.swing.border.LineBorder(Color.GRAY, 2, true));
		panMessageServer.add(lblSendMessage);
		panMessageServer.add(txfMessageServer);
		panMessageServer.add(btnSendServer);

		panNorte = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 1));
		panNorte.add(panDadosServer);
		panNorte.add(panMessageServer);
		add(panNorte, BorderLayout.NORTH);

		// Painel de clientes conectados
		java.util.Vector<String> d = new java.util.Vector<String>();
		d.add("1:  Wesley:  192.168.40.1");
		d.add("2:  Maria:  192.1.69.1");
		d.add("3:  José:  192.88.52.1");
		d.add("4:  João:  175.85.55.2");
		d.add("5:  Lucio:  175.45.55.36");
		d.add("8:  Rato:  192.28.42.11");
		d.add("9:  Zé:  175.82.22.42");
		d.add("10:  Anonymous:  175.45.44.16");
		d.add("11:  Chico:  192.28.42.11");
		d.add("12:  Vilma:  145.1.0.2");
		d.add("13:  Roberto:  196.78.44.12");

		lblClientes = new JLabel();
		lblClientes.setText("Clientes (" + d.size() + ")");// +
															// server.clients.size()
															// + ")");
		lblClientes.setFont(new Font("Comics Sans", Font.PLAIN, 14));
		lblClientes.setBounds(10, 10, 170, 25);
		lstClientes = new JList(d);
		lstClientes.setVisibleRowCount(7);
		scpLstClientes = new JScrollPane(lstClientes);
		scpLstClientes.setBounds(10, 40, 200, 200);
		scpLstClientes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scpLstClientes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		lblNickClient = new JLabel("Nick: ");
		lblNickClient.setBounds(300, 40, 200, 25);
		lblIpClient = new JLabel("IP: ");
		lblIpClient.setBounds(300, 60, 200, 25);
		ckbEditar = new JCheckBox("Editar mensagem");
		ckbEditar.setBounds(300, 90, 150, 25);
		txfMessageClient = new JTextField();
		txfMessageClient.setBounds(300, 120, 260, 25);
		btnSendClient = new JButton("Send");
		btnSendClient.setBounds(300, 150, 100, 25);
		btnKick = new JButton("Kick");
		btnKick.setBounds(300, 200, 100, 25);

		panClientes = new JPanel();
		panClientes.setBorder(new javax.swing.border.LineBorder(null, 1, true));
		panClientes.setLayout(null);
		panClientes.add(lblClientes);
		panClientes.add(scpLstClientes);
		panClientes.add(lblNickClient);
		panClientes.add(lblIpClient);
		panClientes.add(ckbEditar);
		panClientes.add(txfMessageClient);
		panClientes.add(btnSendClient);
		panClientes.add(btnKick);

		// Painel do chat
		lblChat = new JLabel("Chat: ");
		lblChat.setBounds(10, 10, 100, 25);
		txaChat = new JTextArea();
		txaChat.setLineWrap(true);
		txaChat.setBounds(10, 30, 600, 200);
		txaChat.setEditable(false);
		scpChat = new JScrollPane(txaChat);
		scpChat.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scpChat.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		panChat = new JPanel();
		panChat.setLayout(null);
		panChat.setBorder(new javax.swing.border.LineBorder(null, 1, true));
		panChat.add(lblChat);
		// panChat.add(txaChat);

		panCentro = new JPanel(new GridLayout(2, 1));
		panCentro.add("Clientes", panClientes);
		panCentro.add("Chat", panChat);
		add(panCentro, BorderLayout.CENTER);

		setVisible(true);

		try {
			mySocket = new Socket("127.0.0.1", server.getSS().getLocalPort());
		} catch(IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void main(String... args) {
		new GUIServer();
	}
}
