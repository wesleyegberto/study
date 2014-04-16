package javaComoProgramar.cap24;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import javax.media.Manager;
import javax.media.Player;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Exercicio24_14 extends JFrame
{
	JMenu mnuAbrir, mnuConfig;
	JMenuItem mitAudio, mitImagens, mitSetDelay;
	JMenuBar barMenu;
	JFileChooser fileChooser = new JFileChooser();
	File[] f;
	Player p;
	JPanel panControl;
	ImagensPanel imgPan;
	
	public Exercicio24_14()
	{
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Animator");
		setResizable(false);
		setLayout(new BorderLayout());
		
		mitAudio = new JMenuItem("Audio");
		mitAudio.addActionListener(new MitAudioHandler());
		
		mitImagens = new JMenuItem("Imagens");
		mitImagens.addActionListener(new MitImagensHandler());
		
		mnuAbrir = new JMenu("Abrir");
		mnuAbrir.add(mitAudio);
		mnuAbrir.add(mitImagens);
		
		mitSetDelay = new JMenuItem("Timer");
		mitSetDelay.addActionListener(new MitSetDelayHandler());
		
		mnuConfig = new JMenu("Configuração");
		mnuConfig.add(mitSetDelay);
		
		barMenu = new JMenuBar();
		barMenu.add(mnuAbrir);
		barMenu.add(mnuConfig);
		setJMenuBar(barMenu);
		
		panControl = new JPanel();
		panControl.setSize(600, 100);
		add(panControl, BorderLayout.SOUTH);
		
		imgPan = new ImagensPanel();
		add(imgPan, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	private class MitSetDelayHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String input = JOptionPane.showInputDialog("Entre com um número, em nanossegundos, para o timer (é recomendado um valor maior que 500).");
			try
			{
				int time = Integer.parseInt(input);
				if(imgPan == null)
					return;
				
				imgPan.setDelay(time);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Número inválido.");
				ex.printStackTrace();
			}
		}
	}
	
	private class MitAudioHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			fileChooser.setMultiSelectionEnabled(false);	
			if(fileChooser.showOpenDialog(Exercicio24_14.this) != JFileChooser.APPROVE_OPTION)
				return;
			
			try
			{
				Manager.setHint(Manager.LIGHTWEIGHT_RENDERER, true);
				p = Manager.createRealizedPlayer(fileChooser.getSelectedFile().toURI().toURL());

				Component c = p.getControlPanelComponent();
				
				if(c != null)
				{
					panControl.add(c, BorderLayout.SOUTH);
					Exercicio24_14.this.repaint();
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	private class MitImagensHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			fileChooser.setMultiSelectionEnabled(true);			
			if(fileChooser.showOpenDialog(Exercicio24_14.this) != JFileChooser.APPROVE_OPTION)
				return;
			
			if(fileChooser.getSelectedFiles() == null)
			{
				System.out.println("Imagens is null.");
				return;
			}
						
			if(imgPan == null)
			{
				imgPan = new ImagensPanel();
				add(imgPan, BorderLayout.CENTER);
			}
			imgPan.stop();
			imgPan.files = fileChooser.getSelectedFiles();
			imgPan.start();
			
			repaint();
		}
	}
	
	public static void main(String[] args)
	{
		new Exercicio24_14();
	}
}

class ImagensPanel extends JPanel
{
	File[] files = null;
	private int pos = 0;
	private Timer t;
	private int delay = 1000;
	
	public ImagensPanel()
	{
		setSize(600, 400);
		t = new Timer(delay, new TimerHandler());
	}
	
	public void start()
	{
		t.start();
	}
	
	public void stop()
	{
		t.stop();
	}
	
	public void setDelay(int delay)
	{
		stop();
		this.delay = delay;
		t = new Timer(delay, new TimerHandler());
		start();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(files == null)
			return;
		
		try
		{
			byte[] bytesFile = new byte[(int) files[pos].length()];
			new FileInputStream(files[pos]).read(bytesFile);
			
			ImageIcon img = new ImageIcon(bytesFile);
			g.drawImage(img.getImage(), 0, 0, 600, 400, this);
			
			if(t.isRunning())
				pos = (pos + 1) % files.length;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public Dimension getMinimumSize()
	{
		return new Dimension(600, 400);
	}
	
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(600, 400);
	}
	
	private class TimerHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			repaint();
		}
	}
}
