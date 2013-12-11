/**
 * @author Wesley Egberto de Brito
 */
package threading.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceTest {
	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();

		es.execute(new MyRunnable("T1"));
		es.execute(new MyRunnable("T2"));
		es.execute(new MyRunnable("T3"));

		es.shutdown();

		System.out.println("main ends");
	}
}

class MyRunnable implements Runnable {
	private String name;

	public MyRunnable(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		try {
			System.out.println(name + " is goint to sleep.");
			Thread.sleep(3000);
			System.out.println(name + " wake up.");
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
