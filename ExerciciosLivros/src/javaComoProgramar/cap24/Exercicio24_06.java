/**
 * @author Wesley Egberto de Brito
 * 
 * Desenvolver uma programa que apague a imagem de fundo aos poucos
 */
package javaComoProgramar.cap24;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercicio24_06 extends JFrame
{
	Quadro q = new Quadro();
	Random rnd = new Random();
	
	public Exercicio24_06() throws InterruptedException
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(q);
		setSize(844, 702);
		setVisible(true);
		
		while(true)
		{
			q.x.add(rnd.nextInt(q.getWidth()));
			q.y.add(rnd.nextInt(q.getHeight()));
			q.repaint();
			Thread.sleep(500);
		}
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		new Exercicio24_06();
	}
}

class Quadro extends JPanel
{
	ArrayList<Integer> x = new ArrayList<Integer>();
	ArrayList<Integer> y = new ArrayList<Integer>();
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		File file;
		byte[] bytesFile = null;
		
		try
		{
			file = new File("C:\\1.jpg");
			bytesFile = new byte[(int) file.length()];
			new FileInputStream(file).read(bytesFile);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		ImageIcon img = new ImageIcon(bytesFile);
		
		g.drawImage(img.getImage(), 0, 0, img.getIconWidth(), img.getIconHeight(), this);
		//setSize(img.getIconWidth(), img.getIconHeight());
		
		g.setColor(Color.WHITE);
		
		for(int i = 0; i < x.size(); i++)
			g.fillRect(x.get(i), y.get(i), 10, 10);
	}
}