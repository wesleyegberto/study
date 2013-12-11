/**
 * Copyright (c) 2010-2013, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito May 27, 2013
 * 
 */
package interface_abstractclass;

abstract class A {
	public A() {
		System.out.println("In A construct");
	}

	abstract void m1();
}

public class AbstractClass extends A {
	public AbstractClass() {
		System.out.println("In AbstractClass' construct");
	}

	@Override
	void m1() {

	}

	static public void main(String... args) {
		AbstractClass a = new AbstractClass();
	}
}
