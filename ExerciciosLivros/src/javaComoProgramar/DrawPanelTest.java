// Figura 4.19 DrawPanelTest.java
// Aplicativo para exibir uma DrawPanel.
import javax.swing.JFrame;

public class DrawPanelTest
{
	public static void main(String[] args)
	{
		// cria um painel que contém nosso desenho
		DrawPanel panel = new DrawPanel();
		
		// cria um quadro para armazenar o painel
		JFrame janela = new JFrame();
		
		// configura o frame para ser encerrado quando ele é fechado
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		janela.add(panel); // adiciona o painel ao frame
		janela.setSize(250, 250); // configura o tamanho do frame
		janela.setVisible(true); // torna o frame visivel
	}
}