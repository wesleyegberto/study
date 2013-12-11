package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRootPane;

public class GuiInterna extends JFrame {
	JDesktopPane desktopPane;
	int numInternalFrame = 0;

	public GuiInterna() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		setDefaultLookAndFeelDecorated(true);
		setTitle("Janelas Internas");

		desktopPane = new JDesktopPane();
		desktopPane.setDragMode(JDesktopPane.LIVE_DRAG_MODE);
		add(desktopPane);

		JMenuItem mitAdd = new JMenuItem("Adicionar");
		mitAdd.addActionListener(new MitAddHandler());

		JMenu mnuJanela = new JMenu("Janelas");
		mnuJanela.add(mitAdd);

		JMenuBar mnuBar = new JMenuBar();
		mnuBar.add(mnuJanela);

		setJMenuBar(mnuBar);

		setVisible(true);
	}

	private class MitAddHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JInternalFrame frame = new JInternalFrame("JIntenalFrame " + ++numInternalFrame, true, true, true, true);
			frame.setSize(400, 400);
			desktopPane.add(frame);
			frame.setVisible(true);
		}
	}

	public static void main(String[] args) {
		new GuiInterna();
	}
}
