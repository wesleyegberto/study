// Exercicio 9.1 b: Exercicio_09_01.java
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.util.Random;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class Teste extends JFrame
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
			
			Graphics2D g2d = (Graphics2D) g;
			
			GradientPaint grad = new GradientPaint(70, 70, Color.BLUE, 150, 150, Color.RED);
			
			g2d.setPaint(grad);
			
			g2d.fillOval(70, 50, 100, 120);
		}
	}
	
	public static void main(String ... args)
	{
		Teste me = new Teste();
		EspiralRect panel = me.new EspiralRect();
		
		JFrame jan = new JFrame();
		
		jan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jan.add(panel); // adiciona o painel ao frame
		jan.setSize(300, 300); // configura o tamanho do frame
		jan.setVisible(true);
	}
}