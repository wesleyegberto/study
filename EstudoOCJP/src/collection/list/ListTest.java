/**
 * List
 * 	.cole��o ordenada (sequencial)
 * 	.pode conter elementos duplicados
 * 	.armazena os �ndices dos elementos
 *  .pesquisa e retorna o seu �ndice de um elemento
 *  .m�todo set(int, E) e remove(int) retorna o objeto que est� sendo adicionado ou removido
 * 	.geralmente remover elementos do fim da lista � mais r�pido que remover do in�cio
 * 
 * Vector
 * 	.classe que implementa List
 * 	.permite elementos null
 * 	.� sincronizada
 * 
 * ArrayList
 * 	.classe que implementa List
 * 	.permite elementos null
 * 	.n�o � sincronizada
 * 
 * LinkedList
 * 	.classe que implementa List e Deque
 * 	.permite elementos null
 * 	.n�o � sincronizada
 * 	.inser��o por �ndice far� com que percora lista (a partir do in�cio ou fim dependendo da posi��o)
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
		ll.addFirst("E"); // lan�a exception
		ll.addLast("F"); // lan�a exception

		// Retorna um ListIterator posicionado no in�cio da lista para poder
		// navegar na ordem normal (o m�todo previousIndex() nessa posi��o retorna
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
		// navegar na ordem inversa (o m�todo nextIndex() nessa posi��o retorna
		// o size da lista)
		li = ll.listIterator(ll.size());
		li.hasNext();

	}
}
