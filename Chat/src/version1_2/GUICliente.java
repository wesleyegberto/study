/**
 * @author Wesley Egberto de Brito
 * @author wesley_16738 Data: 08/08/2011
 * 
 *         Chat BSE Technology V1.0
 */

package version1_2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class GUICliente extends JFrame {
	private static final long serialVersionUID = 5872223096760543628L;

	JButton btnSend, btnSair;
	JPanel panAcao;
	JScrollPane scpMessage;
	JTextArea txaConversa;
	JTextField txfMessage;
	JMenuBar menu;
	JMenu mnuConexao, mnuAjuda;
	JMenuItem mitConectar, mitDesconectar, mitPropriedade, mitAbout;

	Cliente myCon = Cliente.getCliente();

	// Construtores
	private GUICliente() {
		// Configuracao da janela
		setTitle("Chat BSE Technology");
		setSize(730, 400);
		setLocation(400, 400);
		setBackground(Color.BLACK);
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);

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

		// JTextArea para mostrar as mensagens de todos os usu√°rios
		txaConversa = new JTextArea(100, 50);
		txaConversa.setLineWrap(true);
		txaConversa.setEditable(false);
		scpMessage = new JScrollPane(txaConversa);
		scpMessage.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scpMessage.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scpMessage.setAutoscrolls(true);
		getContentPane().add(scpMessage, BorderLayout.CENTER);

		// Menu
		mnuConexao = new JMenu("Conex„o");
		mnuConexao.setMnemonic('X');

		mitConectar = new JMenuItem("Conectar");
		mitConectar.setMnemonic('C');
		mitConectar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				conectar();
			}
		});
		mnuConexao.add(mitConectar);

		mitDesconectar = new JMenuItem("Desconectar");
		mitDesconectar.setMnemonic('D');
		mitDesconectar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				desconectar();
			}
		});
		mnuConexao.add(mitDesconectar);

		mitPropriedade = new JMenuItem("Propriedades");
		mitPropriedade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new GUIPropriedades();
			}
		});
		mitPropriedade.setMnemonic('P');

		mnuConexao.add(mitPropriedade);

		mnuAjuda = new JMenu("Ajuda");
		mnuAjuda.setMnemonic('A');

		mitAbout = new JMenuItem("Sobre");
		mitAbout.setMnemonic('S');
		mitAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,
						"Criado por Wesley Egberto de Brito.\n\nDireitos reservado para BSE Technology.");
			}
		});
		mnuAjuda.add(mitAbout);

		menu = new JMenuBar();
		menu.add(mnuConexao);
		menu.add(mnuAjuda);
		setJMenuBar(menu);

		// Painel de mensagem
		panAcao = new JPanel();
		panAcao.setLayout(new FlowLayout(3));

		// JTextArea para mensagens
		txfMessage = new JTextField(50);
		txfMessage.setSize(250, 25);
		txfMessage.addKeyListener(new TxfMessageListener());
		panAcao.add(txfMessage);

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
		getContentPane().add(panAcao, BorderLayout.SOUTH);

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Deseja sair?") == 0)
					System.exit(0);
			}
		});

		setVisible(true);
		trancar();
	}

	// MÈtodo que instancia a classe Cliente, caso a conexao falhe È encerrado o
	// programa
	void conectar() {
		try {
			myCon.conectar();
			myCon.setGuiCli(this);
			liberar();
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Conex„o n„o estabelecida.");
			trancar();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Erro.");
			trancar();
		}
	}

	void desconectar() {
		myCon.desconectar();
		trancar();
	}

	public void trancar() {
		panAcao.setEnabled(false);
		btnSend.setEnabled(false);
		btnSair.setEnabled(false);
		txfMessage.setEnabled(false);
		txfMessage.setText("");
		txaConversa.setText("");
		mitPropriedade.setEnabled(true);
		mitDesconectar.setEnabled(false);
		mitConectar.setEnabled(true);
	}

	public void liberar() {
		panAcao.setEnabled(true);
		btnSend.setEnabled(true);
		btnSair.setEnabled(true);
		txfMessage.setEnabled(true);
		mitPropriedade.setEnabled(false);
		mitDesconectar.setEnabled(true);
		mitConectar.setEnabled(false);
	}

	public void addMessage(String newMsg) {
		if(!newMsg.equals("")) {
			txaConversa.setText(txaConversa.getText() + "\n" + newMsg);
			txaConversa.setSelectionStart(txaConversa.getText().length());
		}
	}

	// Listener para botao de enviar de mensagem

	private class BtnSendListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(txfMessage.getText() == null || txfMessage.getText().equals(""))
				return;

			myCon.sendMessage(txfMessage.getText());
			txfMessage.setText("");
			txfMessage.requestFocus();
		}
	}

	// Listener para tecla <ENTER> poder enviar mensagem
	private class TxfMessageListener implements java.awt.event.KeyListener {
		@Override
		public void keyPressed(KeyEvent key) {
			if(key.getKeyChar() == KeyEvent.VK_ENTER) {
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

	// Main
	public static void main(String[] args) {
		new GUICliente();
	}

}
