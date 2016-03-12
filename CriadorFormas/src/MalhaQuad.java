import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

@SuppressWarnings("serial")
public class MalhaQuad extends JPanel {
	Random rnd = new Random();

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int width = getWidth();
		int height = getHeight();
		int y = 0, x = 0;
		// g.drawLine(0, 0, width, height);

		for(y = height - 15, x = 15; y > 0 && x < width; x += 15, y -= 15) {
			g.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
			g.drawLine(0, 0, x, y);
		}

		for(y = 15, x = 15; y < height && x < width; x += 15, y += 15) {
			g.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
			g.drawLine(0, height, x, y);
		}

		for(y = height - 15, x = 15; y > 0 && x < width; x += 15, y -= 15) {
			g.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
			g.drawLine(width, height, x, y);
		}

		for(y = height - 15, x = width - 15; y > 0 && x > 0; x -= 15, y -= 15) {
			g.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
			g.drawLine(width, 0, x, y);
		}
	}

	public JFrame mostrar() {
		JFrame malhaQ = new JFrame();
		malhaQ.setSize(500, 500);
		malhaQ.setVisible(true);

		malhaQ.add(this);
		return malhaQ;
	}
}
