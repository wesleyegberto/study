/**
 * @author Wesley Egberto de Brito Objetivo: Testar a classe ArrayDeque que
 *         implementa Deque -� uma fila FIFO que tamb�m permite as opera��es no
 *         fim da lista -possui os mesmos m�todos da Queue, por�m com extens�o
 *         First e Last (para o inicio e fim da lista) Ex: offerFirst(),
 *         offerLast(), pollFirst(), pollLast(), peekFirst(), peekLast(),
 *         addLast(), ...
 */
package collection.deque;

import java.util.ArrayDeque;

@SuppressWarnings("unchecked")
public class ArrayDequeTest {
	public static void main(String[] args) {
		ArrayDeque deck = new ArrayDeque<Integer>();

		deck.addLast(2); // Adiciona no fim, lan�a NullPointerException se o
							// argumento for null
		deck.addFirst(3); // Adiciona no inicio, lan�a NullPointerException se o
							// argumento for null
		deck.offerFirst(32); // Adiciona no inicio, lan�a NullPointerException
								// se o argumento for null
		deck.offerLast(31); // Adiciona no inicio, lan�a NullPointerException se
							// o argumento for null

		deck.peekFirst();
		deck.peekLast();

		deck.pollFirst(); // Retira o 32
		deck.pollLast(); // Retira o 31

		while(!deck.isEmpty())
			System.out.println(deck.pollLast());
	}
}
