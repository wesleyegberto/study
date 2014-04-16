package javaComoProgramar.cap22;

public class Exercicio22_09
{
	public static void main(String[] args)
	{
		List<Integer> list = new List<Integer>();
		list.insertAtFront(1);
		list.insertAtFront(2);
		list.insertAtFront(3);
		list.insertAtFront(4);
		list.insertAtFront(5);

		list.print();
		list.printListBackward();
		List.reverse(list).print();
	}
}
