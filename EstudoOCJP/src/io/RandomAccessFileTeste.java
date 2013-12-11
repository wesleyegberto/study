package io;

import java.io.RandomAccessFile;

public class RandomAccessFileTeste {
	public static void main(String[] args) {
		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile("Teste.txt", "rws");

			raf.getFilePointer();
			raf.writeBytes("Meu nome e Jose....");
			System.out.println(raf.getFilePointer());

			raf.seek(2);
			raf.skipBytes(5);

			raf.writeBytes("Meu Coração");
			System.out.println(raf.getFilePointer());

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
