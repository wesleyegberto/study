/**
 * Interface Queue e Deque
 * 	.permite operações em ambas as direções
 * 	.as classes LinkedList e ArrayDeque implementam
 *  .geralmente não permite inserir null (exceto LinkedList)
 * 	.recomenda não permitir elementos null pois alguns métodos retornam null para indicar que a lista está vazia
 * 	.todos os métodos de operações possuem duas versões:
 * 		-uma que lança exception quando falha
 * 		-uma que retorna algum valor especial (null ou false)
 * 
 * Métodos da Queue
 * 	.add(e)
 * 		-adiciona o item e retorna true, se estiver sem espaço lança IllegalStateException
 * 	.offer(e)
 * 		-usado geralmente em Deque com limites de capacidades (pois o add(e) lança exception)
 * 	.remove() e element()
 * 		-lança NoSuchElementException se estiver vazia
 * 	.pool() e peek()
 * 		-retorn null se estiver vazia
 * 
 * Métodos da Deque:
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
		//deck.add(null); Não permite inserir null
		deck.offerFirst("D");
		deck.offerLast("E");

		for(String s : deck) {
			System.out.println(s);
		}
	}
}
