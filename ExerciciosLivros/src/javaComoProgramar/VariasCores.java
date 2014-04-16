// Exercicio 6.1 - Pag.178
// Desenhar circulos um dentro do outro alternando as cores

import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.util.Random;

public class VariasCores extends JFrame {
	public class Colorindo extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Random rnd = new Random();
			
			for(int i = 0; i < 100; i += 10) {
				if(i % 2 == 0)
					g.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
				else
					g.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
					
				//g.fillOval(i + 10, i + 10, 100 - i, 100 - i);  //Formação de Cone
				g.fillOval(i + 20, i + 20, 200 - i * 2,  200 - i * 2);
			}
		}
	}
	
	
	public static void main(String[] args) {
		VariasCores cf = new VariasCores();
							  
		Colorindo of = cf.new Colorindo();
												  
		JFrame janela = new JFrame("Criando formas");
		
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.add(of);
		janela.setSize(250, 275);
		janela.setVisible(true);

	}
}