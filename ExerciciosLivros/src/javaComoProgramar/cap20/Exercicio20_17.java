package javaComoProgramar.cap20;

import java.util.LinkedList;
import java.util.Scanner;

public class Exercicio20_17 {
	public static void main(String args[]) {
		LinkedList<Integer> lst = new LinkedList<Integer>();
		Scanner sc = new Scanner(System.in);

		for(int i = 0; i < 20; i++) {
			System.out.print("Entre com um numero: ");
			lst.add(sc.nextInt());
		}

		double media;
		long total = 0;

		for(int i = 0; i < lst.size(); i++)
			total += lst.get(i);

		media = total / lst.size();

		System.out.println("Media: " + media);
	}
}
