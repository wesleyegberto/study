package applet;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.jnlp.FileContents;
import javax.jnlp.FileOpenService;
import javax.jnlp.ServiceManager;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TesteJnlp extends JApplet {
	private JTextField txfNome;
	private JButton btnAbrir;

	public void init() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		txfNome = new JTextField("Caminho");
		txfNome.setSize(150, 25);
		panel.add(txfNome);

		btnAbrir = new JButton("Abrir");
		btnAbrir.addActionListener(new BtnAbrirListener());
		panel.add(btnAbrir);

		add(panel);
	}

	public void start() {

	}

	class BtnAbrirListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				FileOpenService fos = (FileOpenService) ServiceManager.lookup("javax.jnlp.FileOpenService");
				FileContents file = fos.openFileDialog(null, null);

			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
