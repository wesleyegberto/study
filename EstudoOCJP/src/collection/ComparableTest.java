/**
 * @author Wesley Egberto de Brito Objetivo: Teste da interface
 *         java.lang.Comparable
 */
package collection;

public class ComparableTest implements Comparable {
	int i;

	public ComparableTest(int i) {
		this.i = i;
	}

	public boolean equals(ComparableTest c) {
		if(this.i == c.i)
			return true;
		else
			return false;
	}

	/* Seguindo equals(), retornará 0 quando forem iguais. */
	public int compareTo(Object o) {
		ComparableTest c = (ComparableTest) o;

		return this.i - c.i;
	}

	public static void main(String[] args) {
		ComparableTest c1 = new ComparableTest(4);
		ComparableTest c2 = new ComparableTest(25);

		System.out.println(c1.compareTo(c2));
	}
}
