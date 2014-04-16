// Exercicio 5.1

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class Exercicio_05_01 extends JFrame {
	public class Circulos extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			for(int i = 0; i < 100; i += 10) {
				//g.drawOval(i + 10, i + 10, 100 - i, 100 - i);  //Formação de Cone
				g.drawOval(i + 20, i + 20, 200 - i * 2,  200 - i * 2);
			}
		}
	}
	
	
	public static void main(String[] args) {
		Exercicio_05_01 exerc05 = new Exercicio_05_01();						  
		Circulos circ = exerc05.new Circulos();
												  
		JFrame janela = new JFrame("Exercicio 5.1 - Criação de vários circulos");
		
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.add(circ);
		janela.setSize(260, 270);
		janela.setVisible(true);

	}
}