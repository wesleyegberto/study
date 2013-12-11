package gui.gridBagLayout;

import java.awt.GridBagLayout;
import javax.swing.*;

@SuppressWarnings("static-access")
public class Teste2 extends JFrame {
	private static final long serialVersionUID = 9009400743208332769L;

	private JButton btnCadastrar;
	private JLabel lblNome, lblEndereco, lblSexo;
	private JTextField txfNome, txfEndereco, txfSexo;

	public Teste2() {
		setDefaultLookAndFeelDecorated(false);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 200);
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);

		lblNome = new JLabel("Nome:");
		add(lblNome,
				new GBC(0, 0, 1, 1, GBC.NONE, GBC.NORTHWEST).setWeight(100, 100).setInsets(5, 15, 5, 0).setIpad(0, 0));

		txfNome = new JTextField();
		add(txfNome, new GBC(0, 0, 2, 1, GBC.HORIZONTAL, GBC.NORTHWEST).setWeight(100, 100).setInsets(50, 10, 0, 0)
				.setIpad(200, 5));

		lblEndereco = new JLabel("Endereço:");
		add(lblEndereco, new GBC(0, 0, 1, 1, GBC.NONE, GBC.WEST).setWeight(100, 0).setInsets(5, 0, 5, 0).setIpad(0, 0));

		txfEndereco = new JTextField();
		add(txfEndereco, new GBC(0, 0, 2, 1, GBC.HORIZONTAL, GBC.WEST).setWeight(100, 0).setInsets(75, 0, 0, 0)
				.setIpad(200, 5));

		lblSexo = new JLabel("Sexo:");
		add(lblSexo, new GBC(3, 0, 1, 1, GBC.NONE, GBC.WEST).setWeight(100, 0).setInsets(10, 0, 0, 0).setIpad(0, 10));

		txfSexo = new JTextField();
		add(txfSexo, new GBC(3, 0, 1, 1, GBC.NONE, GBC.WEST).setWeight(100, 0).setInsets(60, 0, 0, 0).setIpad(100, 5));

		btnCadastrar = new JButton("Cadastrar");
		add(btnCadastrar, new GBC(3, 3, 2, 1).setWeight(100, 0).setIpad(100, 5));

	}

	public static void main(String[] args) {
		new Teste2().setVisible(true);
	}
}
