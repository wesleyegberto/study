/**
 * @author Wesley Egberto de Brito Objetivo: Testar a classe PriorityQueue que
 *         implementa Queue
 */

package collection.queue;

import java.util.*;

public class PriorityQueueTest {
	public static void main(String... args) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

		// add ou offer adiciona o elemento na fila
		pq.add(5);
		pq.add(3);
		pq.offer(null);
		pq.offer(20);

		while(pq.size() > 0) {
			System.out.println(pq.peek()); // Apenas requisita sem remover
			// System.out.println(pq.element()); //Apenas requisita sem remover,
			// lança um NoSuchElementException caso a fila esteja vazia

			pq.poll(); // Requisita e remove o elemento superior, retorn null se
						// a fila estiver vazia
			// pq.remove(); //Requisita e remove o elemento superior, lança um
			// NoSuchElementException caso a fila esteja vazia
		}
	}
}
