/**
 * Verificar a profundidade de níveis de uma árvore
 */
package javaComoProgramar.cap22;

import java.util.Random;

public class Exercicio22_19
{
	public static void main(String[] args)
	{
		Random rnd = new Random();
		Tree<Integer> t = new Tree<Integer>();
		
		System.out.println("Numeros gerados:");
		for(int i = 0, num; i < 20; i++)
		{
			num = rnd.nextInt(100);
			System.out.print(num + " ");
			t.insertNode(num);
		}
		
		System.out.println("\nPrint tree:");
		t.postorderTraversal();
		System.out.println("\nDepth: " + t.getDepth());
	}
}
