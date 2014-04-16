import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CriandoFormas extends JFrame {
	public class OpcaoForma extends JPanel {
		int opc;
		
		public OpcaoForma(int opc) {
			this.opc = opc;
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			for(int i = 0; i < 10; i++) {
				if(opc == 1) {
					g.drawRect(20 + i * 20, 20 + i * 20,  20 + i * 20, 20 + i * 20);
				}
				else if(opc == 2) {
					g.drawOval(20 + i * 20, 20 + i * 20,  20 + i * 20, 20 + i * 20);
				}
				else { //Esse processo é utilizado para criação dos circulos
					g.drawRect(20 + i * 20, 20 + i * 20,  20 + i * 20, 20 + i * 20);
					g.drawOval(20 + i * 20, 20 + i * 20,  20 + i * 20, 20 + i * 20);
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		CriandoFormas cf = new CriandoFormas();
		
		int opc = Integer.parseInt(JOptionPane.showInputDialog(null, "Entre com uma opção:" +
									  "\n 1 - Quadrado \n 2 - Circulo \n 3 - Os dois"));
									  
		OpcaoForma of = cf.new OpcaoForma(opc);
												  
		JFrame janela = new JFrame("Criando formas");
		
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.add(of);
		janela.setSize(500, 500);
		janela.setVisible(true);

	}
}