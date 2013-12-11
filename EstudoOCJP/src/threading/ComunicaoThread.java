package threading;

import java.util.LinkedList;
import java.util.Queue;

public class ComunicaoThread {
	public static void main(String[] args) {
		Thread criador = new Thread(new Criador(), "Criador1");
		Thread empacotador = new Thread(new Empacotador(), "Empacotador1");
		Thread entregador = new Thread(new Entregador(), "Entregador1");

		System.out.println("Modulos criados\n");
		criador.start();
		empacotador.start();
		entregador.start();

	}
}

class Fabrica {
	private Queue<Produto> prodsCriado = new LinkedList<Produto>(); // Produtos para criar
	private Queue<Produto> prodsEmpacotado = new LinkedList<Produto>(); // Produtos para  empacotar
	private Queue<Produto> prodsEntregado = new LinkedList<Produto>(); // Produtos para  entregar

	static Fabrica fabrica = new Fabrica();

	private Fabrica() {
	}

	public Produto getProdCriado() {
		Produto p = null;
		try {
			synchronized(this.prodsCriado) {
				if(prodsCriado.size() == 0) {
					prodsCriado.wait();
					System.out.println(Thread.currentThread().getName() + " esta WAITING a criacao de produtos.");
				} else {
					p = prodsCriado.poll();
					System.out.println(Thread.currentThread().getName() + " vai empacotar " + p.nome);
				}
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		return p;
	}

	public Produto getProdEmpacotado() {
		Produto p = null;
		try {
			synchronized(this.prodsEmpacotado) {
				if(prodsEmpacotado.size() == 0) {
					prodsEmpacotado.wait();
					System.out.println(Thread.currentThread().getName() + " esta WAITING o empacotamento de produtos.");
				} else {
					p = prodsEmpacotado.poll();
					System.out.println(Thread.currentThread().getName() + " vai entregar " + p.nome);
				}
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		return p;
	}

	public void addProdCriado(Produto p) {
		synchronized(this.prodsCriado) {
			prodsCriado.add(p);
			System.out.println(Thread.currentThread().getName() + " criou " + p.nome);
			prodsCriado.notifyAll();
		}
	}

	public void addProdEmpacotado(Produto p) {
		synchronized(this.prodsEmpacotado) {
			prodsEmpacotado.add(p);
			System.out.println(Thread.currentThread().getName() + " empacotou " + p.nome);
			prodsEmpacotado.notifyAll();
		}
	}

	public void addProdEntregado(Produto p) {
		synchronized(this.prodsEntregado) {
			prodsEntregado.add(p);
			System.out.println(Thread.currentThread().getName() + " entregou " + p.nome);
			prodsEntregado.notifyAll();
		}
	}
}

class Criador implements Runnable {
	public void run() {
		for(int i = 0; true; i++) {
			try {
				Thread.sleep(1000);
				Fabrica.fabrica.addProdCriado(new Produto("Prod_" + i));
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Empacotador implements Runnable {
	public void run() {
		while(true) {
			try {
				Thread.sleep(1500);
				Produto p = Fabrica.fabrica.getProdCriado();
				if(p != null)
					Fabrica.fabrica.addProdEmpacotado(p);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Entregador implements Runnable {
	public void run() {
		while(true) {
			try {
				Thread.sleep(200);
				Produto p = Fabrica.fabrica.getProdEmpacotado();
				if(p != null)
					Fabrica.fabrica.addProdEntregado(p);
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
