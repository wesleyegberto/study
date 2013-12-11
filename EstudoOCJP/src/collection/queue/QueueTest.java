/**
 * Interface Queue e Deque
 * 	.permite opera��es em ambas as dire��es
 * 	.as classes LinkedList e ArrayDeque implementam
 *  .geralmente n�o permite inserir null (exceto LinkedList)
 * 	.recomenda n�o permitir elementos null pois alguns m�todos retornam null para indicar que a lista est� vazia
 * 	.todos os m�todos de opera��es possuem duas vers�es:
 * 		-uma que lan�a exception quando falha
 * 		-uma que retorna algum valor especial (null ou false)
 * 
 * M�todos da Queue
 * 	.add(e)
 * 		-adiciona o item e retorna true, se estiver sem espa�o lan�a IllegalStateException
 * 	.offer(e)
 * 		-usado geralmente em Deque com limites de capacidades (pois o add(e) lan�a exception)
 * 	.remove() e element()
 * 		-lan�a NoSuchElementException se estiver vazia
 * 	.pool() e peek()
 * 		-retorn null se estiver vazia
 * 
 * M�todos da Deque:
 * 	|__throws exception__|__return especial__|
 * 	| add[First|Last]	 | offer[First|Last] |
 * 	| remove[First|Last] | poll[First|Last]	 |
 * 	| get[First|Last]	 | peek[First|Last]	 |
 * 
 * 
 * Estruturas ( Queue/Deque )
 * -LIFO/FILO (Stack)
 * 	.Adicionar: push
 *	.Pegar: peek
 *	.Remover: pop, poll
 *
 * -FIFO
 * 	.Adicionar: add/addLast, offer/offerLast
 * 	.Pegar: element/getFirst, peek/peekFirst
 * 	.Remover: remove/removeFirst, poll/pollFirst
 */
package collection.queue;

import java.util.ArrayDeque;

public class QueueTest {
	public static void main(String[] args) {
		ArrayDeque<String> deck = new ArrayDeque<String>();

		deck.addFirst("A");
		deck.add("B");
		deck.offer("C");
		//deck.add(null); N�o permite inserir null
		deck.offerFirst("D");
		deck.offerLast("E");

		for(String s : deck) {
			System.out.println(s);
		}
	}
}
