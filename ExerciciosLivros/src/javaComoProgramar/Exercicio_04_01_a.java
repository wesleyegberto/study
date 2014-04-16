// Exercicio 4.1 a
import javax.swing.JFrame;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Exercicio_04_01_a
{
	public class DrawPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			int width = getWidth();
			int height = getHeight();
			int y = 0, x = 15;
			//g.drawLine(0, 0, width, height);
			
			for (y = 0, x = 0; y < height && x < width; x += 15, y += 15)
			{
				g.drawLine(0, y, x, height);
			}
		}
	}
	
	public static void main(String[] args)
	{
		Exercicio_04_01_a me = new Exercicio_04_01_a();
		
		DrawPanel panel = me.new DrawPanel();
		
		JFrame janela = new JFrame();
		
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.add(panel);
		janela.setSize(500, 500);
		
		janela.setVisible(true);
	}
}