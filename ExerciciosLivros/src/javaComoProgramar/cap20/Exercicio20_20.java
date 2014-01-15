package javaComoProgramar.cap20;

import java.util.*;

public class Exercicio20_20 {
	public static void main(String[] args) {
		TreeSet<String> t = new TreeSet<String>();

		String frase = javax.swing.JOptionPane.showInputDialog(null, "Entre com uma frase qualquer.").toLowerCase();

		String[] tokens = frase.split(" ");

		t.addAll(Arrays.asList(tokens));

		Iterator i = t.iterator();

		while(i.hasNext())
			System.out.println(i.next());
	}
}
