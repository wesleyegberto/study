package threading;

public class Impasse {

	Foo f = new Foo();
	Bar b = new Bar();

	class Foo implements Runnable {
		@Override
		public void run() {
			doFoo();
		}

		public synchronized void doFoo() {
			System.out.println("Thread " + Thread.currentThread().getName() + " take the key's Foo");
			System.out.println("Thread " + Thread.currentThread().getName() + " go sleep now");
			try {
				Thread.sleep(2 * 1000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread " + Thread.currentThread().getName() + " wake up and need's method Bar.doBar");
			b.doBar();
		}
	}

	class Bar implements Runnable {
		@Override
		public void run() {
			doBar();
		}

		public synchronized void doBar() {
			System.out.println("Thread " + Thread.currentThread().getName() + " take the key's Bar");
			System.out.println("Thread " + Thread.currentThread().getName() + " go sleep now");
			try {
				Thread.sleep(2 * 1000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread " + Thread.currentThread().getName() + " wake up and need's method Foo.doFoo");
			new Foo().doFoo();
		}
	}

	public static void main(String[] args) {
		Impasse p = new Impasse();

		Thread t1 = new Thread(p.f, "t1");
		Thread t2 = new Thread(p.b, "t2");

		t1.start();
		t2.start();

		System.out.println("Back in main");
	}
}
