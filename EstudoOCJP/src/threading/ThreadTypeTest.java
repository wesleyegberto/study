package threading;

public class ThreadTypeTest {
	public static void main(String[] args) {
		System.out.println("main starts");
		new MyThread3().start();
		System.out.println("main ends");
	}
}

class MyThread3 extends Thread {
	public void run() {
		while(true) {
			System.out.println("I'm in run");
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
			}
		}
	}

	public void start() {
		// A chamada do start() na superclasse Thread cria uma nova thread.
		super.start();
		System.out.println("I'm in start");
	}
}
