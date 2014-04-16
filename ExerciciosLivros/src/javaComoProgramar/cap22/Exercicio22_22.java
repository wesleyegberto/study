package javaComoProgramar.cap22;

public class Exercicio22_22
{
	public static void main(String[] args)
	{
		Tree<Integer> t = new Tree<Integer>();
		
		t.insertNode(50);
		t.insertNode(25);
		t.insertNode(75);
		t.insertNode(15);
		t.insertNode(85);
		t.insertNode(10);
		
		System.out.println("Antes:");
		t.postorderTraversal();
		
		t.deleteNode(25);
		System.out.println("\nDepois do 25:");
		t.postorderTraversal();

		t.deleteNode(15);
		System.out.println("\nDepois do 15:");
		t.postorderTraversal();
		
		t.deleteNode(10);
		System.out.println("\nDepois do 10:");
		t.postorderTraversal();
		
	}
}
