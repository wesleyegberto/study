/**
 * @author Wesley Egberto de Brito 27/03/2013
 * 
 */

import java.awt.Graphics;
import javax.swing.JPanel;

public class JFractalEscada extends JPanel {
	float level;
	float x1, y1, x2, y2;
	boolean firstVert;

	public JFractalEscada(float level, float x1, float y1, float x2, float y2, boolean firstVert) {
		this.level = level;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.firstVert = firstVert;
	}

	@Override
	public void paintComponent(Graphics g) {
		drawFractal(g, --level, x1, y1, x2, y2, firstVert);
	}

	public void drawFractal(Graphics g, float level, float x1, float x2, float y1, float y2, boolean horiz) {
		System.out.println("Level: " + level + "\n\t\t(" + x1 + ";" + y1 + ")\t(" + x2 + ";" + y2 + ")");
		g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
		if(g == null || level == 0)
			return;
		else {
			float x3 = 0, y3 = 0, x4 = 0, y4 = 0;

			if(horiz) {
				x3 = (x1 + x2) / 2;
				y3 = (y1 + y2) / 2;
				x4 = x3 + (y2 - y1) / 2;
				y4 = y3;
			} else {
				x3 = (y1 + y2) / 2;
				y3 = y2;
				x4 = x3;
				y4 = y3 + ((x2 - x1) / 2);
			}

			drawFractal(g, --level, x3, y3, x4, y4, !horiz);
		}
	}

}
