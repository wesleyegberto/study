package javaComoProgramar.cap22;

public class Exercicio22_06
{
	public static void main(String[] args)
	{
		List<Character> l1 = new List<Character>();
		List<Character> l2 = new List<Character>();
		
		l1.insertAtFront('A');
		l1.insertAtFront('B');
		l2.insertAtBack('C');
		l2.insertAtBack('m');
		l1.insertAtFront('k');
		
		System.out.println("L1");
		l1.print();
		System.out.println("\nL2");
		l2.print();
		
		ListConcatenate.concat(l1, l2);
		
		System.out.println("\nNova lista.");
		l1.print();
	}
}

class ListConcatenate
{
	public static <T> void concat(List<T> dest, List<T> orig)
	{
		while(!orig.isEmpty())
		{
			dest.insertAtBack(orig.removeFromFront());
		}
	}
}