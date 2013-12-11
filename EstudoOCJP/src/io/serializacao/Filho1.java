package io.serializacao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Filho1 extends PaiSemImplementar implements Serializable {
	// private static final long serialVersionUID = 6096408386252305338L;

	public int cod1;
	public transient double cod2;

	// public int cod3; //se salvar esse objeto com o cod3 e tentar serializar
	// com ele comentado e não houver um serial dará erro

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Filho1 f = new Filho1();
		f.cod1 = f.cod += 3;
		f.cod2 = 3.1;
		System.out.println("\nf : " + f.cod + "\n" + f.cod1 + "\n\n");

		FileOutputStream fos = new FileOutputStream("obj1.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		oos.writeObject(f);

		oos.close();

		FileInputStream fis = new FileInputStream("obj1.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);

		Filho1 f2 = (Filho1) ois.readObject();

		System.out.println("\nf2 : " + f2.cod + "\n" + f2.cod1 + "\n " + f2.cod2 + "\n");
	}
}
