/**
 * Copyright (c) 2010-2013, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 27/01/2013
 * 
 */
package io;

import java.util.Scanner;

public class ScannerTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Entre com texto: ");
		String s = sc.next(); // le o valor até o delimitador padrão (espaço)
		System.out.println(s);

		sc = new Scanner(System.in);
		sc.useDelimiter("-"); // Altera o delimitador para -
		System.out.print("\nEntre novamente com texto: ");
		s = sc.next();
		System.out.println(s);

		sc.close();
	}

}
