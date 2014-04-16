// Exercicio 7.1 a: Exercicio_07_01_a.java
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;

public class Exercicio_07_01_a
{
	public class EspiralRect extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			int xi = getWidth() / 2;
			int yi = getHeight() / 2;
			int xf = xi, yf = yi;
			
			int passo = 0;
			
			g.setColor(Color.RED);
			
			for(int i = xi; i < getWidth() && i < getHeight(); i++)
			{
				passo += 20;
				
				yf += passo;
				g.drawLine(xi, yi, xf, yf);
				
				xi = xf;
				yi = yf;
				xf -= passo;
				g.drawLine(xi, yi, xf, yf);
				
				xi = xf;
				yi = yf;
				passo += 20;
				yf -= passo;
				g.drawLine(xi, yi, xf, yf);
				
				xi = xf;
				yi = yf;
				xf += passo;
				g.drawLine(xi, yi, xf, yf);
				
				xi = xf;
				yi = yf;
			}
		}
	}
	
	public static void main(String ... args)
	{
		Exercicio_07_01_a me = new Exercicio_07_01_a();
		EspiralRect panel = me.new EspiralRect();
		
		JFrame janela = new JFrame();
		
		// configura o frame para ser encerrado quando ele é fechado
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.add(panel); // adiciona o painel ao frame
		janela.setSize(300, 300); // configura o tamanho do frame
		janela.setVisible(true); // torna o frame visivel
	}
}