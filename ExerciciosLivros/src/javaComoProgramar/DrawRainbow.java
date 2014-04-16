// Figura 7.25: DrawRaibow.java
// Demostra a utilização de cores em um array
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class DrawRainbow extends JPanel
{
	// define cores indigo e violeta
	public static final Color VIOLET = new Color(180, 0, 180);
	public static final Color INDIGO = new Color(75, 0, 130);
	
	// a utilizar no arco-iris, iniciando da parte mais interna
	// as duas entradas em branco resultam em um arco vazio no centro
	private Color[] colors = {Color.WHITE, Color.WHITE, VIOLET, INDIGO, Color.BLUE,
							  Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED};
							  
	// construtor
	public DrawRainbow()
	{
		setBackground(Color.WHITE); // configura o fundo como branco
	}
	
	// desenha um arco-íris utilizando arcos concêntricos
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		int radius = 20;
		
		// desenha o arco-íris perto da parte central inferior
		int centerX = getWidth() / 2;
		int centerY = getHeight() - 10;
		
		// desenha arcos preenchidos com o mais externo
		for(int counter = colors.length; counter > 0; counter--)
		{
			// configura a cor para o arco atual
			g.setColor(colors[counter - 1]);
			
			// preenche o arco de 0 a 180 graus
			g.fillArc(centerX - counter * radius, centerY - counter * radius,
					  counter * radius * 2, counter * radius * 2,
					  0, 180);
		}
	}
}