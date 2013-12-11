/**
 * Copyright (c) 2010-2013, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 17 de Jul de 2013
 * 
 */
package classe;

class MyClass {
	static final int a = 20;

	static void call() {
		System.out.println(" two ");
	}

	static {
		System.out.println(" one ");
	}
}

public class Inicializacao2 {
	public static void main(String[] args) {
		System.out.println(MyClass.a);
	}
}
