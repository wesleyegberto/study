//Figura 4.18 DrawPanel.java
//Utilizando DrawLine para conectar os cantos de um painel
import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawPanel extends JPanel
{
	//Desenha um X a partir dos cantos do painel
	public void paintComponent(Graphics g) // sobrepõe o método da superclasse, esse método é chamado automáticamente quando é exibido
	{
		// chama paintComponent para assegurar que o painel seja exibido corretamete
		super.paintComponent(g);
		
		int width = getWidth(); //largura total
		int height = getHeight(); //altura total
		
		//desnha uma linha a partir do canto superior esquerdo até o inferior direito
		g.drawLine(0, 0, width, height); // desenha uma linha entre 2 pontos indicado
		
		// desenha uma linha a partir do canto inferior esquerdo até o superior direito
		g.drawLine(0, height, width, 0);
	}
}