package threading;

public class Monitor {
	public synchronized void doFoo() {
		try {
			System.out.println(Thread.currentThread().getName() + " is going to sleep before doFoo.");
			Thread.sleep(5000);
			System.out.println(Thread.currentThread().getName() + " doing foo...");
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getName() + " fineshed foo...");
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void doBar() {
		try {
			System.out.println(Thread.currentThread().getName() + " is going to sleep before doBar.");
			Thread.sleep(5000);
			System.out.println(Thread.currentThread().getName() + " doing bar...");
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getName() + " fineshed bar...");
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void doAnyThing() {
		try {
			synchronized(this) {
				System.out.println(Thread.currentThread().getName() + " is going to sleep before doAnyThing.");
				System.out.println(Thread.currentThread().getName() + " doing doAnyThing...");
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName() + " fineshed doAnyThing...");
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String... args) {
		Monitor m = new Monitor();
		Thread t1 = new Thread(new MyThread(m, 1));
		Thread t2 = new Thread(new MyThread(m, 2));

		t1.start();
		t2.start();
	}
}

class MyThread implements Runnable {
	Monitor m;
	int a;

	public MyThread(Monitor m, int acao) {
		this.m = m;
		this.a = acao;
	}

	public void run() {
		if(a == 1) {
			System.out.println(Thread.currentThread().getName() + " needed doFoo().");
			m.doFoo();
		} else {
			m.doAnyThing();
			System.out.println(Thread.currentThread().getName() + " needed doBar().");
			m.doBar();
		}
	}

}
