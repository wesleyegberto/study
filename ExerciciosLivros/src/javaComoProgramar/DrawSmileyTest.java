// Figura 6.17: DrawSmileyTest.java
// Aplicatibo de teste que exibe um rosto sorridente.
import javax.swing.JFrame;

public class DrawSmileyTest
{
	public static void main(String[] args)
	{
		DrawSmiley panel = new DrawSmiley();
		JFrame application = new JFrame();
		
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(panel);
		application.setSize(230, 250);
		application.setVisible(true);
	}
}