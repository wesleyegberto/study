package io;

import java.io.*;
import java.util.Formatter;
import java.util.Scanner;

public class Varias {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {
		try {
			File file = new File("texte.txt");

			if(file.exists())
				System.out.println("Arquivo existe");

			FileOutputStream fos = new FileOutputStream(file, false);
			Formatter f = new Formatter(fos); // Formação igual ao
												// String.format()

			f.format("Teste %n 1 %n 2");
			f.flush();
			f.close();

			FileWriter fw = new FileWriter(file, true);

			fw.write("%n%n%n%nOutro teste com FileWriter");

			FileReader fr = new FileReader(file);

			FileInputStream fis = new FileInputStream(file);
			Scanner s = new Scanner(fis); // Pode vincula-lo à um objet File,
											// FileReader, FileInputStream, e
											// outros
			while(s.hasNext()) {
				System.out.println(s.next());
			}
			s.close();
			fw.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
