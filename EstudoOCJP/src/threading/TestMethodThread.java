package threading;

public class TestMethodThread {
	static class Foo implements Runnable {
		public void run() {
			envia1(Thread.currentThread().getName());
		}
	}

	static class Bar implements Runnable {
		public void run() {
			envia2(Thread.currentThread().getName());
		}
	}

	public static void main(String... args) {
		Thread t1 = new Thread(new Foo());
		Thread t2 = new Thread(new Bar());
		t1.setName("Foo");
		t2.setName("Bar");
		t1.start();
		t2.start();
	}

	public synchronized static void envia1(String name) {
		System.out.println(name + " in main1");
		try {
			System.out.println(name + " is going to sleep.");
			Thread.sleep(10000);
			System.out.println(name + " wake up.");
			System.out.println(name + " needed envia2.");
			envia2(name);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(name + " is back to run from main1.");
	}

	public synchronized static void envia2(String name) {
		System.out.println(name + " in main2");
		try {
			System.out.println(name + " is going to sleep.");
			Thread.currentThread();
			Thread.yield();
			Thread.sleep(10000);
			System.out.println(name + " wake up.");
			System.out.println(name + " needed envia1.");
			envia1(name);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(name + " is back to run from main2.");
	}
}
