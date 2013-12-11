package threading;

public class Exemplo1 {
	private static class Tarefa implements Runnable {
		static int i = 0;
		final int x;

		public Tarefa() {
			i++;
			x = i;
			System.out.println("T2." + x + " instancied");
		}

		@Override
		public void run() {
			System.out.println("T2." + x + " in top - run()");
			go();
		}

		public void go() {
			System.out.println("T2." + x + " in top - go()");
			otherGo();
		}

		public void otherGo() {
			System.out.println("T2." + x + " in top - otherGo");
		}
	}

	public void main1() {
		System.out.println("Princ in top - main1()");
		main2();
	}

	public void main2() {
		System.out.println("Princ in top - main2()");
	}

	public static void main(String[] args) {
		System.out.println("Princ in top - main()");

		Exemplo1 e = new Exemplo1();
		Runnable[] r = new Tarefa[5];
		Thread[] t = new Thread[r.length];

		for(int i = 0; i < t.length; i++) {
			r[i] = new Tarefa();
			t[i] = new Thread(r[i]);
			t[i].setPriority((i + 1) * 2);
		}

		for(int i = 0; i < t.length; i++) {
			t[i].start();
		}

		e.main1();
		System.out.println("End main");
	}
}
