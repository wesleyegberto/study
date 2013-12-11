/**
 * @author Wesley Egberto de Brito Objetivo: Testar classe LinkedHashSet
 * 
 *         .Igual ao HashSet, por�m mantem a ordem de inser��o
 */

package collection.set;

import java.util.LinkedHashSet;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class LinkedHashSetTest {
	public static void main(String[] args) {
		LinkedHashSet h = new LinkedHashSet();

		h.add(12); // Retorna false caso j� exista
		h.add(14);
		h.add(50);
		h.add(11);
		h.add(12); // Retorna falso, pois j� existe um 12 na fila

		Iterator it = h.iterator();
		int i = 0;

		// A ordem � a mesma que foi inserida
		while(it.hasNext())
			System.out.println(i++ + " => " + it.next());
	}
}
