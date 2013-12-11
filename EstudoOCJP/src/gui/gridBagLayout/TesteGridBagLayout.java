package gui.gridBagLayout;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import javax.swing.*;

@SuppressWarnings("serial")
public class TesteGridBagLayout extends JFrame {
	private JLabel lblNome, lblNasc, lblSexo;
	private JTextField txfNome, txfNasc;
	private ButtonGroup btgSexo;
	private JRadioButton radMasc, radFem;
	private JButton btnOk, btnSair;
	private GridBagLayout gbc;

	public TesteGridBagLayout() {
		super("Teste GridBagLayout");
		gbc = new GridBagLayout();
		setLayout(gbc);
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit toolKit = Toolkit.getDefaultToolkit(); // Objeto com informações
														// da tela do sistema

		setLocation(toolKit.getScreenSize().width / 2, toolKit.getScreenSize().height / 2);
		setLocationByPlatform(false); // O SO escolherá um local adequado
		// setExtendedState(JFrame.MAXIMIZED_BOTH);

		lblNome = new JLabel("Nome:");
		GridBagConstraints gbcLblNome = new GridBagConstraints();
		gbcLblNome.gridx = 0;
		gbcLblNome.gridy = 0;
		gbcLblNome.gridwidth = 1;
		gbcLblNome.gridheight = 1;
		gbcLblNome.weightx = 100;
		gbcLblNome.weighty = 100;
		gbc.setConstraints(lblNome, gbcLblNome);
		add(lblNome, gbcLblNome);

		txfNome = new JTextField();
		GridBagConstraints gbcTxfNome = new GridBagConstraints();
		gbcTxfNome.gridx = 1;
		gbcTxfNome.gridy = 0;
		gbcTxfNome.gridwidth = 7;
		gbcTxfNome.gridheight = 1;
		gbcTxfNome.weightx = 100;
		gbcTxfNome.weighty = 100;
		gbcTxfNome.ipadx = 10;
		gbcTxfNome.fill = GridBagConstraints.HORIZONTAL;
		gbcTxfNome.insets.right = 100;
		gbc.setConstraints(lblNome, gbcLblNome);
		add(txfNome, gbcTxfNome);

		lblNasc = new JLabel("Nasc.:");
		GridBagConstraints gbcLblNasc = new GridBagConstraints();
		gbcLblNasc.gridx = 0;
		gbcLblNasc.gridy = 1;
		gbcLblNasc.gridwidth = 1;
		gbcLblNasc.gridheight = 1;
		gbcLblNasc.weightx = 0;
		gbcLblNasc.weighty = 0;
		gbc.setConstraints(lblNasc, gbcLblNasc);
		add(lblNasc, gbcLblNasc);

		txfNasc = new JTextField();
		GridBagConstraints gbcTxfNasc = new GridBagConstraints();
		gbcTxfNasc.gridx = 1;
		gbcTxfNasc.gridy = 1;
		gbcTxfNasc.gridwidth = 7;
		gbcTxfNasc.gridheight = 1;
		gbcTxfNasc.weightx = 50;
		gbcTxfNasc.weighty = 100;
		gbcTxfNasc.fill = GridBagConstraints.HORIZONTAL;
		gbcTxfNasc.insets.right = 100;
		gbc.setConstraints(txfNasc, gbcTxfNasc);
		add(txfNasc, gbcTxfNasc);

		lblSexo = new JLabel("Sexo:");
		GridBagConstraints gbcLblSexo = new GridBagConstraints();
		gbcLblSexo.gridx = 0;
		gbcLblSexo.gridy = 3;
		gbcLblSexo.gridwidth = 1;
		gbcLblSexo.gridheight = 1;
		gbcLblSexo.weightx = 0;
		gbcLblSexo.weighty = 0;
		gbc.setConstraints(lblSexo, gbcLblSexo);
		add(lblSexo, gbcLblSexo);

		btgSexo = new ButtonGroup();

		radMasc = new JRadioButton("Masculino");
		btgSexo.add(radMasc);
		GridBagConstraints gbcRadMasc = new GridBagConstraints();
		gbcRadMasc.gridx = 1;
		gbcRadMasc.gridy = 3;
		gbcRadMasc.gridwidth = 1;
		gbcRadMasc.gridheight = 1;
		gbcRadMasc.weightx = 100;
		gbcRadMasc.weighty = 100;
		gbcRadMasc.anchor = GridBagConstraints.WEST;
		gbcRadMasc.fill = GridBagConstraints.HORIZONTAL;
		gbc.setConstraints(radMasc, gbcRadMasc);
		add(radMasc, gbcRadMasc);

		radFem = new JRadioButton("Feminino");
		btgSexo.add(radFem);
		GridBagConstraints gbcRadFem = new GridBagConstraints();
		gbcRadFem.gridx = 2;
		gbcRadFem.gridy = 3;
		gbcRadFem.gridwidth = 3;
		gbcRadFem.gridheight = 1;
		gbcRadFem.weightx = 100;
		gbcRadFem.weighty = 100;
		gbcRadFem.anchor = GridBagConstraints.WEST;
		gbcRadFem.fill = GridBagConstraints.HORIZONTAL;
		gbc.setConstraints(radFem, gbcRadFem);
		add(radFem, gbcRadFem);

		btnOk = new JButton("OK");
		GridBagConstraints gbcBtnOk = new GridBagConstraints();
		gbcBtnOk.gridx = 1;
		gbcBtnOk.gridy = 10;
		gbcBtnOk.gridwidth = 2;
		gbcBtnOk.gridheight = 1;
		gbcBtnOk.weightx = 100;
		gbcBtnOk.weighty = 100;
		gbcBtnOk.anchor = GridBagConstraints.WEST;
		gbc.setConstraints(btnOk, gbcBtnOk);
		add(btnOk, gbcBtnOk);

		btnSair = new JButton("Sair");
		GridBagConstraints gbcBtnSair = new GridBagConstraints();
		gbcBtnSair.gridx = 4;
		gbcBtnSair.gridy = 10;
		gbcBtnSair.gridwidth = 2;
		gbcBtnSair.gridheight = 1;
		gbcBtnSair.weightx = 100;
		gbcBtnSair.weighty = 100;
		gbcBtnSair.anchor = GridBagConstraints.WEST;
		gbc.setConstraints(btnSair, gbcBtnSair);
		add(btnSair, gbcBtnSair);

		setVisible(true);
	}

	public static void main(String[] args) {
		new TesteGridBagLayout();
	}
}
