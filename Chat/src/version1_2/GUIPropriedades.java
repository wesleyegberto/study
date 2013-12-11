/**
 * @author Wesley Egberto de Brito
 * @author wesley_16738 Data: 08/08/2011
 */

package version1_2;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class GUIPropriedades extends JFrame {
	private static final long serialVersionUID = 15435434543L;

	JLabel lblIp, lblPorta, lblNick;
	JTextField txfIp, txfPorta, txfNick;
	JPanel panel, panelB;
	JButton btnSalvar, btnSair;

	public GUIPropriedades() {
		setSize(300, 200);
		setTitle("Propriedades do Chat BSE");
		setBackground(Color.white);
		setLayout(new BorderLayout());
		setResizable(false);
		setLocation(500, 400);

		panel = new JPanel(null);

		lblIp = new JLabel("IP:");
		lblIp.setBounds(20, 20, 100, 25);
		txfIp = new JTextField();
		txfIp.setBounds(80, 20, 100, 25);
		txfIp.setText(Cliente.getCliente().getIp());

		lblPorta = new JLabel("Porta:");
		lblPorta.setBounds(20, 50, 100, 25);
		txfPorta = new JTextField();
		txfPorta.setBounds(80, 50, 100, 25);
		txfPorta.setText(Cliente.getCliente().getPorta() + "");
		lblNick = new JLabel("Nick:");
		lblNick.setBounds(20, 80, 100, 25);
		txfNick = new JTextField();
		txfNick.setBounds(80, 80, 100, 25);
		txfNick.setText(Cliente.getCliente().getNick());

		panel.add(lblIp);
		panel.add(txfIp);
		panel.add(lblPorta);
		panel.add(txfPorta);
		panel.add(lblNick);
		panel.add(txfNick);

		panelB = new JPanel(new FlowLayout());
		btnSalvar = new JButton("Salvar");
		btnSalvar.setMnemonic('S');
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				salvaDados();
			}
		});
		panelB.add(btnSalvar);

		btnSair = new JButton("Sair");
		btnSair.setMnemonic('r');
		btnSair.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				hide();
				dispose();
			}
		});
		panelB.add(btnSair);

		add(panel);
		add(panelB, BorderLayout.SOUTH);

		addWindowListener(new java.awt.event.WindowAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void windowClosing(WindowEvent e) {
				int x = JOptionPane.showConfirmDialog(null, "Deseja salvar antes de sair?", "Chat BSE Technology", 2);
				if(x == 0)
					salvaDados();
				else {
					hide();
					dispose();
				}
			}
		});

		setVisible(true);
	}

	private void salvaDados() {
		Cliente c = Cliente.getCliente();
		c.setIp(txfIp.getText());
		try {
			c.setPorta(Integer.parseInt(txfPorta.getText()));
		} catch(Exception ex) {
			c.setPorta(5000);
		}
		c.setNick(txfNick.getText());
		JOptionPane.showMessageDialog(null, "Alterações salvas com sucesso.", "Chat BSE Technology", 1);
	}
	/*
	 * public static void main(String[] args) { new GUIPropriedades(); }
	 */
}
