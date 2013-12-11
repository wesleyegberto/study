/**
 * List
 * 	.coleção ordenada (sequencial)
 * 	.pode conter elementos duplicados
 * 	.armazena os índices dos elementos
 *  .pesquisa e retorna o seu índice de um elemento
 *  .método set(int, E) e remove(int) retorna o objeto que está sendo adicionado ou removido
 * 	.geralmente remover elementos do fim da lista é mais rápido que remover do início
 * 
 * Vector
 * 	.classe que implementa List
 * 	.permite elementos null
 * 	.é sincronizada
 * 
 * ArrayList
 * 	.classe que implementa List
 * 	.permite elementos null
 * 	.não é sincronizada
 * 
 * LinkedList
 * 	.classe que implementa List e Deque
 * 	.permite elementos null
 * 	.não é sincronizada
 * 	.inserção por índice fará com que percora lista (a partir do início ou fim dependendo da posição)
 * 
 */

package collection.list;

import java.util.LinkedList;
import java.util.ListIterator;

public class ListTest {
	public static void main(String[] args) {
		LinkedList<String> ll = new LinkedList<String>();

		// LIFO/FILO - Stack: push(E), poll()/pop(), peek()
		ll.push("A"); // call addFirst(E)
		ll.push("B");
		ll.push("C");

		ll.offer("G"); // call add, retorna true
		ll.add("D"); // add on final of list, retorna true
		ll.addFirst("E"); // lança exception
		ll.addLast("F"); // lança exception

		// Retorna um ListIterator posicionado no início da lista para poder
		// navegar na ordem normal (o método previousIndex() nessa posição retorna
		// -1)
		ListIterator<String> li = ll.listIterator();
		while(li.hasNext()) {
			System.out.println(li.next());
		}

		// Pega e remove o primeiro elemento
		System.out.println("poll: " + ll.poll());
		// Pega o primeiro elemento sem remover
		System.out.println("peek: " + ll.peek());
		// Pega e remove o primeiro elemento
		System.out.println("pop: " + ll.pop());

		// Retorna um ListIterator posicionado no fim da lista para poder
		// navegar na ordem inversa (o método nextIndex() nessa posição retorna
		// o size da lista)
		li = ll.listIterator(ll.size());
		li.hasNext();

	}
}
