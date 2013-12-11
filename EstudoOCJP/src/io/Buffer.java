package io;

import java.io.*;

public class Buffer {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {
		try {
			File file = new File("C:" + File.separator + "Users" + File.separator + "Wesley1" + File.separator
					+ "Desktop" + File.separator + "Teste.txt");

			FileOutputStream fos = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(fos);

			byte[] b = { 124, 12, 41 };

			bos.write(b);
			bos.flush();
			bos.close();

			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);

			System.out.println(bis.read());
			bis.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
