/**
 * @author Wesley Egberto de Brito Objetivo: Testar classe HashSet
 *   .Não permite duplicata nos conjuntos
 *   .É baseado no valor de hash code e equals
 *   .Acesso mais rápido, pois é organizado pelo hash code
 */

package collection.set;

import java.util.HashSet;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class HashSetTest {
	public static void main(String[] args) {
		HashSet h = new HashSet();

		h.add(12); // Retorna false caso já exista
		h.add(14);
		h.add(50);
		h.add(11);
		h.add(12); // Retorna falso, pois já existe um 12 na fila

		Iterator it = h.iterator();
		int i = 0;
		while(it.hasNext())
			System.out.println(i++ + " => " + it.next());
	}
}
