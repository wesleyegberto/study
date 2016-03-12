import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

@SuppressWarnings("serial")
public class CirculoEQuadrado extends JPanel {
	int opc;
	int i = 0;
	int z = 10;
	Random rnd = new Random();

	public CirculoEQuadrado(int opc) {
		this.opc = opc;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		z++;
		for(i = 0; i < z; i++) {
			if(opc == 1) {
				g.drawRect(20 + i * 20, 20 + i * 20, 20 + i * 20, 20 + i * 20);
			} else if(opc == 2) {
				g.drawOval(20 + i * 20, 20 + i * 20, 20 + i * 20, 20 + i * 20);
			} else if(opc == 3) { // Esse processo é utilizado para criação dos
									// circulos
				g.drawRect(20 + i * 20, 20 + i * 20, 20 + i * 20, 20 + i * 20);
				g.drawOval(20 + i * 20, 20 + i * 20, 20 + i * 20, 20 + i * 20);
			} else if(opc == 4) {
				g.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
				g.fillRect(20 + i * 20, 20 + i * 20, 20 + i * 20, 20 + i * 20);
			} else if(opc == 5) {
				g.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
				g.fillOval(20 + i * 20, 20 + i * 20, 20 + i * 20, 20 + i * 20);
			} else if(opc == 6) { // Esse processo é utilizado para criação dos
									// circulos
				g.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
				g.fillRect(20 + i * 20, 20 + i * 20, 20 + i * 20, 20 + i * 20);
				g.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
				g.fillOval(20 + i * 20, 20 + i * 20, 20 + i * 20, 20 + i * 20);
			}
		}
	}

	public JFrame mostrar() {
		JFrame formas = new JFrame();
		formas.setSize(500, 500);
		formas.setVisible(true);

		formas.add(this);
		return formas;
	}
}
