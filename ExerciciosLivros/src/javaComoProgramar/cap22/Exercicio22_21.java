package javaComoProgramar.cap22;

import java.util.Random;
import java.util.Scanner;

public class Exercicio22_21 {
	public static void main(String[] args) {
		List<Integer> list = new List<Integer>();
		Random rnd = new Random();
		Scanner sc = new Scanner(System.in);

		for(int i = 0; i < 10; i++)
			list.insertAtFront(rnd.nextInt(100));

		list.print();

		System.out.print("Entre com um número a ser pesquisado: ");
		int key = sc.nextInt();

		Integer value = list.search(key);

		if(value == null)
			System.out.println("Número não encontrado.");
		else
			System.out.println("Número encontrado: " + value);
	}
}
