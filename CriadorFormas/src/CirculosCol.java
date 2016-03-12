import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

@SuppressWarnings("serial")
public class CirculosCol extends JPanel {
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Random rnd = new Random();

		for(int i = 0; i < 200; i += 10) {
			g.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
			g.fillOval(i + 20, i + 20, 400 - i * 2, 400 - i * 2);
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
