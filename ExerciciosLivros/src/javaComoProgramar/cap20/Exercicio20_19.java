package javaComoProgramar.cap20;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Exercicio20_19
{
	public static void main(String args[])
	{
		int num = Integer.parseInt(javax.swing.JOptionPane.showInputDialog(null, "Insira um numero inteiro."));
				
		for(int i = 2; i < num; i++)
		{
			if(num % i == 0)
				calculaFatoresPrimos(num);
		}
		
		System.out.println(num + " é um número primo.");
	}
	
	static void calculaFatoresPrimos(int num)
	{
		Set<Integer> s = new HashSet<Integer>();
		Iterator<Integer> it = null;
		int resto = num;
		
		for(int i = 2; i < num; i++)
		{
			if(resto % i == 0)
			{
				resto /= i;
				s.add(i);
				i = 2;
			}
		}

		it = s.iterator();
		while(it.hasNext())
			System.out.println(it.next());
		
		System.exit(0);
	}
}
