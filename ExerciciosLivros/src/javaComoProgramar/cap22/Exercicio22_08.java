package javaComoProgramar.cap22;

public class Exercicio22_08 {
	public static void main(String[] args) {
		SortedList<Double> s1 = new SortedList<Double>();
		SortedList<Double> s2 = new SortedList<Double>();

		for(int i = 0; i < 5; i++)
			s1.insert((Math.random() * 100));

		for(int i = 0; i < 6; i++)
			s2.insert((Math.random() * 100));

		System.out.println("Content's s1");
		s1.print();
		System.out.println("Content's s2");
		s2.print();

		s1.merge(s2);

		System.out.println("New content's s1");
		s1.print();
		System.out.println("New content's s2");
		s2.print();
	}
}
