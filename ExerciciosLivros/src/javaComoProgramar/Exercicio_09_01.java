// Exercicio 9.1 b: Exercicio_09_01.java
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import java.util.Random;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class Exercicio_09_01 extends JFrame
{
	public class EspiralRect extends JPanel
	{
		Random rnd = new Random();

		public int numLine;
		public int numOval;
		public int numRect;
			
		JFrame janela = new JFrame();
		JLabel total = new JLabel("");
		
		public EspiralRect()
		{
			this.add(total, BorderLayout.SOUTH);
		}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			numLine = rnd.nextInt(10);
			numOval = rnd.nextInt(10);
			numRect = rnd.nextInt(10);
			
			for(int i = 0; i < numLine; i++)
			{
				g.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
				g.drawLine(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256),rnd.nextInt(256));
			}
			
			for(int i = 0; i < numOval; i++)
			{
				g.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
				g.fillOval(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256),rnd.nextInt(256));
			}
			
			for(int i = 0; i < numRect; i++)
			{
				g.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
				g.fillRect(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256),rnd.nextInt(256));
			}

			total.setText("Lines: " + numLine + ", Ovals: " + numOval + ", Rectangles: " +  numRect);
		}
	}
	
	public static void main(String ... args)
	{
		Exercicio_09_01 me = new Exercicio_09_01();
		EspiralRect panel = me.new EspiralRect();
		
		JFrame jan = new JFrame();
		
		jan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jan.add(panel); // adiciona o painel ao frame
		jan.setSize(300, 300); // configura o tamanho do frame
		jan.setVisible(true);
	}
}