import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

@SuppressWarnings("serial")
public class Malha3D extends JPanel {
	Random rnd = new Random();

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int width = getWidth();
		int height = getHeight();
		int y = 0, x = 15;
		// g.drawLine(0, 0, width, height);

		for(y = 0, x = 15; x < width && y < height; x += 15, y += 15) {
			g.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
			g.drawLine(0, y, x, height);
		}

		for(y = height, x = 0; x < width && y > 0; x += 15, y -= 15) {
			g.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
			g.drawLine(x, height, width, y);
		}

		for(y = 15, x = 0; x < width && y < height; x += 15, y += 15) {
			g.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
			g.drawLine(x, 0, width, y);
		}

		for(y = 15, x = width; x > 0 && y < height; x -= 15, y += 15) {
			g.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
			g.drawLine(x, 0, 0, y);
		}
	}

	public JFrame mostrar() {
		JFrame malha = new JFrame();
		malha.setSize(500, 500);
		malha.setVisible(true);

		malha.add(this);
		return malha;
	}
}
