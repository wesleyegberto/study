/**
 * @author Wesley Egberto de Brito 27/03/2013
 * 
 */

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GuiFractal {
	public static void main(String[] args) {
		JFrame form = new JFrame();
		JPanel panel = new JFractalEspiralInterna(100, 100, 10, 100, 900, 1);

		// form.setLayout(new BorderLayout());
		form.setBackground(Color.WHITE);

		// form.setResizable(false);
		form.setLocation(0, 0);
		form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		form.setSize(1800, 980);
		form.setVisible(true);
		form.add(panel);
	}

}
