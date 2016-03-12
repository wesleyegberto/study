import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

@SuppressWarnings("serial")
public class ArteAbstrato extends JPanel {
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Random rnd = new Random();

		for(int i = 0; i < 100; i += 10) {
			g.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));

			g.fillOval(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
			g.fillRect(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
		}
	}

	public JFrame mostrar() {
		JFrame artAbs = new JFrame();
		artAbs.setSize(450, 450);
		artAbs.setVisible(true);

		artAbs.add(this);
		return artAbs;
	}
}
