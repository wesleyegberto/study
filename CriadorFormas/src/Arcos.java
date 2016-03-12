import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

@SuppressWarnings("serial")
public class Arcos extends JPanel {
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Random rnd = new Random();

		for(int i = 0; i < 100; i += 10) {
			g.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
			g.fillArc(i + 50, i + 50, 400 - i * 2, 400 - i * 2, 0, 180);
		}
	}

	public JFrame mostrar() {
		JFrame circu = new JFrame();
		circu.setSize(500, 500);
		circu.setVisible(true);

		circu.add(this);
		return circu;
	}
}
