/**
 * Copyright (c) 2010-2013, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 11/02/2013
 * 
 */
package threading;

public class MonitorTest {
	public static void main(String[] args) {
		MonitorTest m = new MonitorTest();
		Thread t1 = new MyThread2(m, "-");
		Thread t2 = new MyThread2(m, "@");
		Thread t3 = new MyThread2(m, "%");

		t1.start();
		t2.start();
		t3.start();
	}

	public synchronized void metodoThreadSafe(String message) {
		try {
			System.out.println(Thread.currentThread().getName() + " is running");
			// Thread.currentThread().wait();
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + " do " + message);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class MyThread2 extends Thread {
	MonitorTest m;
	String s;

	MyThread2(MonitorTest m, String s) {
		this.m = m;
		this.s = s;
	}

	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			m.metodoThreadSafe(i + "x " + s);
			Thread.yield(); // Interrompe temporariamente para outra thread
							// poder ser executada
		}
	}
}
