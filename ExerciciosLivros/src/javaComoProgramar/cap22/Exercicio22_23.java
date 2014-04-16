package javaComoProgramar.cap22;

public class Exercicio22_23
{
	public static void main(String[] args)
	{
		Tree<Integer> t = new Tree<Integer>();
		
		t.insertNode(20);
		t.insertNode(10);
		t.insertNode(5);
		t.insertNode(23);
		t.insertNode(24);
		t.insertNode(21);
		t.insertNode(19);
		
		
		t.levelOrder();
		System.out.println("\n");
		t.outputTree();
		System.out.println("\n\nProcurando 24.");

		TreeNode<Integer> resp = t.contains(24);
		
		if(resp == null)
			System.out.println("Não encontrado.");
		else
			System.out.println("Encontrado: " + resp.data);
	}
}
