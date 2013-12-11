/**
 * @author Wesley Egberto de Brito Objetivo: Testar os métodos da classe
 *         Collections
 */

package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class CollectionsTest {
	public static void main(String[] args) {
		List<Integer> l = new ArrayList<Integer>();
		l.add(20);
		l.add(21);
		l.add(200);
		l.add(12);
		l.add(0);
		l.add(-220);

		ListIterator li = l.listIterator();
		int i = 0;
		System.out.println("Ordem original");
		while(li.hasNext())
			System.out.println("\t[" + i++ + "] => " + li.next());

		li = l.listIterator();
		i = 0;
		Collections.sort(l);
		System.out.println("\nApos Collections.sort()");
		while(li.hasNext())
			System.out.println("\t[" + i++ + "] => " + li.next());
	}
}
