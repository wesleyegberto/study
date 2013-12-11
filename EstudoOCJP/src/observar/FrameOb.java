package observar;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class FrameOb extends Frame implements Observer {
	Observavel ob = new Observavel();
	Thread t = new Thread(ob);
	Canvas progress = new Canvas();

	public FrameOb() {
		setLayout(null);
		setSize(300, 60);
		add(progress);
		progress.setBackground(Color.blue);
		progress.setBounds(0, 0, 0, 30);
		setTitle("Exemplo de Observável");
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		ob.addObserver(this);
		t.start();
	}

	@Override
	public void update(Observable o, Object arg) {
		progress.setSize(ob.getValue(), 100);
	}

	public static void main(String args[]) {
		(new FrameOb()).setVisible(true);
	}
}
