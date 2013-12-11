/**
 * Copyright (c) 2010-2013, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 26/01/2013
 * 
 */
package io;

import java.io.PrintStream;

public class PrintStreamTest {
	public static void main(String[] args) {
		PrintStream ps = System.out; // Essa classe não lança Exception
		ps.println(new Object()); // Chama o toString()
		ps.println(ps.checkError()); // Retorna true se ocorreu algum erro na
										// operação anterior
		ps.close();
	}

}
