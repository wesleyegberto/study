/**
 * @author Wesley Egberto de Brito
 * Objetivo: Testar a classe TreeSet e a interface NavigableSet TreeSet
 *  -ou utiliza a compara��o natural do objeto com o m�todo compareTo da interface java.lang.Comparable que deve ser implementada,
 *   quando tentado adicionar um objeto que n�o implementa � lan�ado um ClassCastException
 *  -ou utiliza um objeto que implemente java.lang.Comparator que � recebido no construtor
 *  -mantem os objetos ordenados e n�o permite duplicatas
 */

package collection.set;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

@SuppressWarnings("unchecked")
public class TreeSetTest {
	static public void main(String[] args) {
		// � utilizado o ordenador natural (m�todo compareTo() da interface
		// Comparable que a classe Integer implementa)
		TreeSet t = new TreeSet<Integer>();

		t.add(30);
		t.add(20);
		t.add(21);
		t.add(10);

		Iterator it = t.iterator();
		int i = 0;
		System.out.println("Utilizando o natural:");
		while(it.hasNext())
			System.out.println("\t" + i++ + " => " + it.next());

		// � utilizado o objeto comparador recebido
		t = new TreeSet<Integer>(new CompareInteger());

		t.add(30);
		t.add(12);
		t.add(12);
		t.add(21);
		t.add(22);

		it = t.iterator();
		i = 0;
		System.out.println("\n\nUtilizando o Comparator:");
		while(it.hasNext())
			System.out.println("\t" + i++ + " => " + it.next());
	}
}

class CompareInteger implements Comparator {
	public int compare(Object o1, Object o2) {
		Integer i1 = (Integer) o1;
		Integer i2 = (Integer) o2;

		return i1 - i2;
	}
}
