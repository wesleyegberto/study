/**
 * Copyright (c) 2010-2013, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 04/05/2013
 * 
 */
package threading.callable_e_future;

import java.util.concurrent.*;

public class Test {
	static public void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();

		Callable<Integer> c = new MyCallable();

		// O submit() executa um objeto Callable em uma nova thread e retorna um
		// objeto Future
		Future<Integer> f = es.submit(c);

		try {
			System.out.println("Value returned: " + f.get());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

class MyCallable implements Callable<Integer> {
	@Override
	public Integer call() throws Exception {
		Thread.sleep(3000);
		return 3;
	}

}
