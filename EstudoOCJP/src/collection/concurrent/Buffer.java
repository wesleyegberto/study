/**
 * Copyright (c) 2010-2013, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 24/03/2013
 * 
 *         Objetivo: Testar a classe ArrayBlockingQueue. -Implementa a interface
 *         BlockingQueue (que estende Queue) -É thread-safe -put() e take() são
 *         os métodos synchronized
 * 
 */
package collection.concurrent;

import java.util.concurrent.ArrayBlockingQueue;

public class Buffer {
	// Classe thread-safe que usa
	ArrayBlockingQueue<Integer> buffer;

	public Buffer() {
		buffer = new ArrayBlockingQueue<Integer>(1);
	}

	public void put(int x) throws InterruptedException {
		// Insere
		buffer.put(x);
		System.out.println(x + " was setted.");
	}

	public int get() throws InterruptedException {
		int x = buffer.take(); // Retorna o primeiro item da fila
		System.out.println(x + " was taked.");
		return x;
	}
}
