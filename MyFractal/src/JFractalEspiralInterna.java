/**
 * @author Wesley Egberto de Brito 29/03/2013
 * 
 */

import java.awt.Graphics;
import javax.swing.JPanel;

public class JFractalEspiralInterna extends JPanel {
	float level;
	float x1, y1, x2, y2;
	int side;

	public JFractalEspiralInterna(float level, float x1, float y1, float x2, float y2, int side) {
		this.level = level;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.side = side;
	}

	@Override
	public void paintComponent(Graphics g) {
		drawFractal(g, --level, x1, y1, x2, y2, side);
	}

	public void drawFractal(Graphics g, float level, float x1, float y1, float x2, float y2, int side) {
		System.out.println("Level: " + level + "\n\t\t(" + x1 + ";" + y1 + ")\t(" + x2 + ";" + y2 + ")");
		g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);

		if(g == null || level <= 0)
			return;
		else {
			float x3 = 0, y3 = 0, x4 = 0, y4 = 0;

			if(side == 1) {
				x3 = x2; // (x1 + x2) / 2;
				y3 = (y1 + y2) / 2;
				x4 = x3 + ((y2 - y1) / 2);
				y4 = y3;
				side = 2;
			} else if(side == 2) {
				x3 = (x1 + x2) / 2;
				y3 = y2;
				x4 = x3;
				y4 = y3 + ((x2 - x1) / 2);
				side = 3;
			} else if(side == 3) {
				x3 = x2;
				y3 = (y1 + y2) / 2;
				x4 = x3 - ((y2 - y1) / 2);
				y4 = y3;
				side = 4;
			} else {
				x3 = (x1 + x2) / 2;
				y3 = y2 + ((x2 - x1) / 2);
				x4 = x3;
				y4 = y3 - ((x2 - x1) / 2);
				side = 1;
			}
			--level;
			drawFractal(g, level, x1, y1, x3, y3, side);
			drawFractal(g, level, x3, y3, x4, y4, side);
			drawFractal(g, level, x3, y3, x2, y2, side);
		}
	}

}
