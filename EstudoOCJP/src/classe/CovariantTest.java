/**
 * Copyright (c) 2010-2013, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito May 26, 2013
 * 
 */
package classe;

class A {
	int x = 5;

	public A() {
		System.out.println("In A construct");
	}

	public int getX() {
		return x;
	}
}

class B extends A {
	int x = 6;

	public B() {
		System.out.println("In B construct");
	}

	public int getX() {
		return x;
	}
}

public class CovariantTest {
	public A getObject() {
		System.out.println("In CovariantTest.getObject");
		return new A();
	}

	public static void main(String[] args) {
		/**
		 * O código abaixo imprimira 5 por causa do tipo de c1 ser usado da
		 * superclasse. O método executado será da subclasse, porém o tipo do
		 * retorno está no tipo da superclasse.
		 */
		CovariantTest c1 = new SubCovariantTest();
		System.out.println(((B) c1.getObject()).x);
	}
}

class SubCovariantTest extends CovariantTest {
	public B getObject() {
		System.out.println("In SubCovariantTest.getObject");
		return new B();
	}
}
