package observar;

import java.util.*;

public class Observavel extends Observable implements Runnable {
	int valor = 0;

	public void notifyObservers() {
		setChanged();
		super.notifyObservers();
	}

	public int getValue() {
		return valor;
	}

	public void run() {
		for(;;) {
			if(Math.random() > 0.5) {
				valor = (new Double(Math.random() * 200)).intValue();
				this.notifyObservers();
			}
			try {
				Thread.sleep(500);
			} catch(Exception e) {
			}
		}
	}
}
