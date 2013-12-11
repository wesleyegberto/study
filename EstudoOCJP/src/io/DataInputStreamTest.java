/**
 * Copyright (c) 2010-2013, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 26/01/2013
 * 
 */
package io;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataInputStreamTest {

	public static void main(String[] args) {
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream("dos.dat"));
			System.out.println(dis.readInt());
			System.out.println(dis.readBoolean());
			System.out.println(dis.readUTF());
			System.out.println("Finalizado!");
			dis.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
