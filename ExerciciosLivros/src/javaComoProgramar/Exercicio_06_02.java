// Exercicio 6.1 - Pag.178
// Desenhar circulos um dentro do outro alternando as cores

import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.util.Random;

public class Exercicio_06_02 extends JFrame {
	public class Colorindo extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Random rnd = new Random();
			
			for(int i = 0; i < 100; i += 10) {
				if(i % 2 == 0)
					g.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
				else
					g.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
					
				g.fillOval(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
				g.fillRect(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
			}
		}
	}
	
	
	public static void main(String[] args) {
		Exercicio_06_02 cf = new Exercicio_06_02();
							  
		Colorindo of = cf.new Colorindo();
												  
		JFrame janela = new JFrame("Criando formas");
		
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.add(of);
		janela.setSize(250, 275);
		janela.setVisible(true);

	}
}