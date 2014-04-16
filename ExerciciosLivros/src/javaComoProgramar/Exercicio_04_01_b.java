// Exercicio 4.1 b
import javax.swing.JFrame;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Exercicio_04_01_b
{
	public class DrawPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			int width = getWidth();
			int height = getHeight();
			int x = 0, y = 15;
			//g.drawLine(0, 23, width, height);
			
			for (y = 0, x = 15; x < width && y < height; x += 15, y += 15)
			{
				g.drawLine(0, y, x, height);
			}
			
			for (y = height, x = 0; x < width && y > 0; x += 15, y -= 15)
			{
				g.drawLine(x, height, width, y);
			}
			
			for (y = 15, x = 0; x < width && y < height; x += 15, y += 15)
			{
				g.drawLine(x, 0, width, y);
			}
			
			for (y = 15, x = width; x > 0 && y < height; x -= 15, y += 15)
			{
				g.drawLine(x, 0, 0, y);
			}
		}
	}
	
	public static void main(String[] args)
	{
		Exercicio_04_01_b me = new Exercicio_04_01_b();
		
		DrawPanel panel = me.new DrawPanel();
		
		JFrame janela = new JFrame();
		
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.add(panel);
		janela.setSize(500, 500);
		janela.setVisible(true);
	}
}