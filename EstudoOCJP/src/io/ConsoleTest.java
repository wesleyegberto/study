/**
 * Copyright (c) 2010-2013, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 27/01/2013
 * 
 */
package io;

import java.io.Console;

public class ConsoleTest {
	public static void main(String[] args) {
		Console console = null;
		// Retorna uma referencia ao terminal que foi utilizado para iniciar o
		// programa, se for usado uma IDE ou sub-processo é retornado null
		console = System.console();

		String user = console.readLine("Entre com seu usuario: "); // Imprime a
																	// mensagem
																	// e le a
																	// resposta
																	// de
																	// entrada
		char[] pass = console.readPassword("Entre com sua senha: "); // Imprime
																		// a
																		// mensagem
																		// e le
																		// a
																		// resposta
																		// de
																		// entrada,
																		// porém
																		// não
																		// mostra
																		// no
																		// console

		System.out.println("Bem-vindo " + user + " com senha " + String.valueOf(pass));
	}

}
