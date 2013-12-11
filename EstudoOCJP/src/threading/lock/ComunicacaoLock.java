/**
 * @author Wesley Egberto de Brito 06/04/2013
 * 
 *         Objetivo: Implementar Lock e Condition
 */
package threading.lock;

import java.util.Random;
import java.util.concurrent.locks.*;

public class ComunicacaoLock {
	public static void main(String[] args) {
		Random rnd = new Random();
		Thread produtor;
		Thread consumidor;

		for(int i = 0; i < 5; i++) {
			produtor = new Thread(new Produtor(rnd.nextInt(3000)), "Produtor_" + i);
			consumidor = new Thread(new Consumidor(rnd.nextInt(3000)), "Consumidor_" + i);
			produtor.start();
			consumidor.start();
		}

		System.out.println("Modulos criados\n");
	}
}

class Fabrica {
	private Produto produto;
	Lock lock = new ReentrantLock();
	Condition addProd = lock.newCondition();
	Condition getProd = lock.newCondition();

	static Fabrica fabrica = new Fabrica();

	private Fabrica() {
	}

	public void addProd(Produto p) {
		lock.lock();
		try {
			while(produto != null) {
				System.out.println(Thread.currentThread().getName() + "\ttried write");
				addProd.await();
			}
			System.out.println(Thread.currentThread().getName() + "\twrites\t" + p.nome);
			this.produto = p;
			getProd.signal();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public Produto getProd() {
		Produto p = null;
		lock.lock();
		try {
			while(produto == null) {
				System.out.println(Thread.currentThread().getName() + "\ttried read");
				getProd.await();
			}
			p = this.produto;
			System.out.println(Thread.currentThread().getName() + "\treads\t" + p.nome);
			this.produto = null;
			addProd.signal();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			lock.unlock();
		}
		return p;
	}
}

class Produtor implements Runnable {
	private int timeToSleep;

	public Produtor(int time) {
		this.timeToSleep = time;
	}

	public void run() {
		for(int i = 0; true; i++) {
			try {
				Fabrica.fabrica.addProd(new Produto("Prod_" + i));
				Thread.sleep(timeToSleep);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Consumidor implements Runnable {
	private int timeToSleep;

	public Consumidor(int time) {
		this.timeToSleep = time;
	}

	public void run() {
		while(true) {
			try {
				Thread.sleep(timeToSleep);
				Fabrica.fabrica.getProd();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Produto {
	String nome;

	Produto(String nome) {
		this.nome = nome;
	}
}
