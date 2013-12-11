/**
 * @author Wesley Egberto de Brito Objetivo: Teste da class LinkedList
 * 
 *         .� eficiente na inser��o de itens nas extremidades (Pilha e Fila),
 *         por�m tem navega��o e localiza��o � ruim .Permite itens repetidos e
 *         null .Implementa Deque .N�o � sincronizada .Contem implementa��o para
 *         se comportar como uma Pilha e uma Fila
 */

package collection.list;

import java.util.ListIterator;
import java.util.List;
import java.util.LinkedList;

@SuppressWarnings("unchecked")
public class LinkedListTest {
	public static void main(String[] args) {
		LinkedList l = new LinkedList();

		l.add(1);
		l.add(2);
		l.add(2, 5); // Adiciona o Integer(5) na posi��o 2, se for uma posi��o
						// mais que o total � lan�ado uma exception de
						// IndexOutOfBounds

		System.out.println("Itens full");
		for(int i = 0; i < l.size(); i++)
			System.out.println(i + " => " + l.get(i));

		// Imitando Pilha
		l.pop(); // Retira um item da pilha (do inicio da lista)
		System.out.println("\nItens com 1 pop");
		for(int i = 0; i < l.size(); i++)
			System.out.println(i + " => " + l.get(i));

		l.push(8); // Coloca um item na pilha (no inicio da lista)
		System.out.println("\nItens com 1 push(8)");
		for(int i = 0; i < l.size(); i++)
			System.out.println(i + " => " + l.get(i));

		// Imitando Fila
		l.poll(); // Retira um item na fila (no inicio da lista)
		System.out.println("\nItens com 1 poll");
		for(int i = 0; i < l.size(); i++)
			System.out.println(i + " => " + l.get(i));

		l.offer(18); // Coloca um item na fila (no fim da lista)
		System.out.println("\nItens com 1 offer(18)");
		ListIterator lstIt = l.listIterator();
		while(lstIt.hasNext())
			System.out.println(lstIt.nextIndex() + " => " + lstIt.next());

	}
}
