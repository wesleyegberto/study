/**
 * @author Wesley Egberto de Brito Objetivo: Teste da class Vector
 * 
 *         .A principal diferença do ArrayList é que é thread-safe .É uma
 *         implementação legada (anterior ao Collections Framework) e deve ser
 *         substituida pela java.util.concurrent.CopyOnWriteArrayList
 */

package collection.list;

import java.util.ListIterator;
import java.util.Vector;

@SuppressWarnings("unchecked")
public class VectorTest {
	public static void main(String[] args) {
		Vector v = new Vector();

		v.add(1);
		v.add(16);
		v.add(12);
		v.add(15);
		v.add(11);

		ListIterator lI = v.listIterator();

		while(lI.hasNext())
			System.out.println(lI.nextIndex() + " => " + lI.next());
	}
}
