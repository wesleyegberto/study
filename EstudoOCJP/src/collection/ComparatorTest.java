/**
 * @author Wesley Egberto de Brito Objetivo: Testar a interface Comparator
 */
package collection;

import java.util.Comparator;

public class ComparatorTest {
	int i;

	public ComparatorTest(int i) {
		this.i = i;
	}

	public static void main(String[] args) {
		ComparatorTest c1 = new ComparatorTest(69);
		ComparatorTest c2 = new ComparatorTest(25);

		// Objeto que será usando na comparação
		ComparatorC c = new ComparatorC();

		System.out.println(c.compare(c1, c2));
	}
}

// Classe que implementa Comparator, será utilizada para efetuar comparação de
// objetos
class ComparatorC implements Comparator {
	public int compare(Object o1, Object o2) {
		ComparatorTest c1 = (ComparatorTest) o1;
		ComparatorTest c2 = (ComparatorTest) o2;

		return c1.i - c2.i;
	}
}
