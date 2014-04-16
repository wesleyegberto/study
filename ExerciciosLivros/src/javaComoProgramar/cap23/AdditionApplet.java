package javaComoProgramar.cap23;

import java.awt.Graphics;
import javax.swing.JApplet;
import javax.swing.JOptionPane;

public class AdditionApplet extends JApplet
{
	private double sum;
	
	public void init()
	{
		String firstNumber = JOptionPane.showInputDialog("Entre com o primeiro n�mero.");
		String secondNumber = JOptionPane.showInputDialog("Entre com o segundo n�mero.");
		
		double num1 = Double.parseDouble(firstNumber);
		double num2 = Double.parseDouble(secondNumber);
		
		sum = num1 + num2;
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		
		g.drawRect(20, 10, 150, 30);
		g.drawString("A soma � " + sum, 30, 30);
		
	}
}
