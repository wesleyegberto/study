/**
 * Copyright (c) 2010-2013, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 26/01/2013
 * 
 */
package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class StreamTest {
	File f;
	BufferedReader teclado;

	public StreamTest() {
		f = new File("teste.txt");
		InputStreamReader isr = new InputStreamReader(System.in); // Permite ler
																	// caracteres
																	// de um
																	// fluxo de
																	// bytes
																	// (converte
																	// de bytes
																	// para
																	// caracteres)
		teclado = new BufferedReader(isr); // Permite ler blocos de caracteres
											// de um fluxo de dados (formado de
											// caracteres) e armazena em cache
	}

	public void le() {
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(f); // Permite ler caracteres de um
												// arquivo
			br = new BufferedReader(fr); // Permite ler blocos de caracteres de
											// um fluxo de dados (formado de
											// caracteres) e armazena em cache
			String line = null;
			System.out.println("-------------Conteudo do arquivo-------------");
			while((line = br.readLine()) != null)
				System.out.println(line);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch(Exception e) {
					System.out.println("Erro ao fechar arquivo.");
				}
			}
		}
	}

	public void escreve() {
		PrintWriter pw = null;

		try {
			FileWriter fw = new FileWriter(f, true); // Permite escrever
														// caracteres em um
														// arquivo, true indica
														// que ira mesclar o
														// novo conteudo
			BufferedWriter bw = new BufferedWriter(fw); // Permite escrever
														// blocos de caracteres
														// em um fluxo de dados
														// de caracteres
			pw = new PrintWriter(bw); // Permite a escrita de caracteres e
										// objetos (toString()) em um fluxo de
										// dados de caracteres

			System.out.println("Entre com algo para ser salvo:");
			String line = teclado.readLine();
			pw.println(line);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pw != null) {
				try {
					pw.close();
				} catch(Exception e) {
					System.out.println("Erro ao fechar arquivo.");
				}
			}
		}
	}

	public void start() {
		int opt;
		do {
			opt = -1;
			System.out.println("\n\n==Entre com a opcao:==\n\t1 - Escrever\n\t2 - Ler\n\t0 - Sair");
			try {
				opt = Integer.parseInt(teclado.readLine());
				if(opt == 1)
					this.escreve();
				else if(opt == 2)
					this.le();
				else if(opt == 0)
					System.out.println("\nTchau!");
				else
					System.out.println("\nOpcao invalida.");
			} catch(NumberFormatException e) {
				System.out.println("\nOpcao invalida.");
			} catch(Exception e) {
				e.printStackTrace();
			}
		} while(opt != 0);
	}

	public static void main(String[] args) {
		new StreamTest().start();
	}

}
