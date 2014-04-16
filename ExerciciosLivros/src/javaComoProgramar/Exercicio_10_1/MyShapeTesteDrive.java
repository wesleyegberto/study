import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;

public class MyShapeTesteDrive
{
	public static void main(String[] args)
	{
		MyShape my = null;
		
		JFrame janela = new JFrame("Minhas Formas");
		janela.setSize(300, 300);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		int i = Integer.parseInt(JOptionPane.showInputDialog("Entre com a opção\n1 - Linha\n2-Oval\n3-Retangulo"));
		
		switch(i)
		{
			case 1:
				my = new MyLine(12, 25, 189, 195, Color.GREEN);
				break;
			case 2:
				my = new MyOval(92, 57, 238, 208, Color.BLUE, true);
				break;
			case 3:
				my = new MyRect(75, 98, 125, 135, Color.RED, false);
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opção inválida.");
				System.exit(0);
		}
		
		janela.add(my);
		janela.setVisible(true);
	}
}