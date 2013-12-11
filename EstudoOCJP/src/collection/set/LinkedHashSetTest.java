/**
 * @author Wesley Egberto de Brito Objetivo: Testar classe LinkedHashSet
 * 
 *         .Igual ao HashSet, porém mantem a ordem de inserção
 */

package collection.set;

import java.util.LinkedHashSet;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class LinkedHashSetTest {
	public static void main(String[] args) {
		LinkedHashSet h = new LinkedHashSet();

		h.add(12); // Retorna false caso já exista
		h.add(14);
		h.add(50);
		h.add(11);
		h.add(12); // Retorna falso, pois já existe um 12 na fila

		Iterator it = h.iterator();
		int i = 0;

		// A ordem é a mesma que foi inserida
		while(it.hasNext())
			System.out.println(i++ + " => " + it.next());
	}
}
