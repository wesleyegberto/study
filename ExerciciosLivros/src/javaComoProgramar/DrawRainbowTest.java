// Figura 7.26 DrawRainbowTest.java
// Aplicativo para exibir uma arco-�ris.
import javax.swing.JFrame;

public class DrawRainbowTest
{
	public static void main(String[] args)
	{
		DrawRainbow panel = new DrawRainbow();
		JFrame janela = new JFrame();
		
		// configura o frame para ser encerrado quando ele � fechado
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.add(panel); // adiciona o painel ao frame
		janela.setSize(400, 250); // configura o tamanho do frame
		janela.setVisible(true); // torna o frame visivel
	}
}