// Exercicio 7.1 b: Exercicio_07_01.java
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;

public class Exercicio_07_01_b
{
	public class EspiralRect extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			int x = getWidth() / 2;
			int y = getHeight() / 2;
			int l = 30;
			
			g.setColor(Color.RED);
			
			for(int i = 0; i < getWidth() && i < getHeight(); i++)
			{
				g.drawArc(x, y, l, l, 0, 180);
				//g.drawRect(x, y, l, l);
				x -= 30; y -= 15; l += 30;
								
				g.drawArc(x, y, l, l, 180, 180);
				//g.drawRect(x, y, l, l);
				x -= 0; y -= 15; l+= 30;
			}
		}
	}
	
	public static void main(String ... args)
	{
		Exercicio_07_01_b me = new Exercicio_07_01_b();
		EspiralRect panel = me.new EspiralRect();
		
		JFrame janela = new JFrame();
		
		// configura o frame para ser encerrado quando ele é fechado
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.add(panel); // adiciona o painel ao frame
		janela.setSize(300, 300); // configura o tamanho do frame
		janela.setVisible(true); // torna o frame visivel
	}
}