/**
 * Copyright (c) 2010-2013, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 26/01/2013
 * 
 */
package io;

import java.io.File;
import java.io.PrintWriter;

public class PrintWriterTest {
	public static void main(String[] args) {
		try {
			PrintWriter pw = new PrintWriter(new File("saida.txt"));
			pw.println(new Object()); // Chama o método toString()
			pw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
