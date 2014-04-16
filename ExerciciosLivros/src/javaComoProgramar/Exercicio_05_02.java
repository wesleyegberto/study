// Exercicio 5.2

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.Graphics;

public class Exercicio_05_02 extends JFrame {
	public class Grafico extends JPanel {
		private int num[];
		int j = 10;
		
		public Grafico(int num[]) {
			this.num = num;
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			for(int i = 0; i < num.length; j+= 20, i++) {
				g.drawRect(10, j, num[i], 10);
			}
		}
	}
	
	
	public static void main(String[] args) {
		int[] num = new int[5];

		Exercicio_05_02 exerc05 = new Exercicio_05_02();	

		for(int i = 0; i < num.length; i++)
			num[i] = Integer.parseInt(JOptionPane.showInputDialog(null, "Entre com um número (entre com números altos)"));
				
		Grafico graf = exerc05.new Grafico(num);
												  
		JFrame janela = new JFrame("Exercicio 5.2 - Criação de vários Graficos");
		
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.add(graf);
		janela.setSize(260, 150);
		janela.setVisible(true);

	}
}