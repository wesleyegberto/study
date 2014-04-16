/**
 * @author Wesley Egberto de Brito
 * 
 * Desenvolver um visualizar de imagens
 */
package javaComoProgramar.cap24;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercicio24_13 extends JFrame
{
	private JButton btnAbrir, btnSair;
	private JPanel panBarra;
	private ImagePanel imgPanel;
	
	public Exercicio24_13()
	{
		setLayout(new BorderLayout());
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panBarra = new JPanel();
		panBarra.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panBarra.setBackground(Color.WHITE);
		
		btnAbrir = new JButton("Abrir");
		btnAbrir.addActionListener(new AbrirArquivoListener());
		panBarra.add(btnAbrir);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				System.exit(0);
			}
		});
		panBarra.add(btnSair);
		
		add(panBarra, BorderLayout.SOUTH);
		imgPanel = new ImagePanel();
		add(imgPanel, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	private class AbrirArquivoListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.showOpenDialog(Exercicio24_13.this);
			
			File f = fileChooser.getSelectedFile();
			
			if(f != null)
			{
				imgPanel.file = f;
				imgPanel.repaint();
			}
		}
		
	}
	
	public static void main(String[] args)
	{
		new Exercicio24_13();
	}
}

class ImagePanel extends JPanel
{
	File file = null;
	
	@Override
	public void paintComponent(Graphics g)
	{
		if(file == null)
			return;
		
		try
		{
			byte[] bytesFile = new byte[(int) file.length()];
			new FileInputStream(file).read(bytesFile);
			ImageIcon img = new ImageIcon(bytesFile);			
			g.drawImage(img.getImage(), 0, 0, img.getIconWidth(), img.getIconHeight(), this);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
