/**
 * Thread
 * 
 * .join() -faz com que a thread atual aguarde até thread que executa join()
 * termine a tarefa. .interrupt() -faz com que a thread termine sua tarefa atual
 * e faça outra coisa (catch).
 * 
 */

package threading;

public class MetodosThread {
	public static void main(String[] args) {
		Thread t2 = new Thread(new R2());

		t2.start();
		long timeStarted = System.currentTimeMillis();
		while(t2.isAlive()) {
			System.out.println("T2 still running");
			try {
				t2.join(1000);
				if(System.currentTimeMillis() - timeStarted == 2000) {
					System.out.println("Time ends");
					t2.interrupt();
					t2.join();
				}
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

class R2 implements Runnable {
	public void run() {
		System.out.println("R2 started");
		while(true) {
			if(Thread.interrupted()) {
				System.out.println("R2 must ends");
				break;
			}
			System.out.println("R2 still running");
		}
		System.out.println("R2 ended");
	}
}
