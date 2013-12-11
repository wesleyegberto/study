/**
 * Copyright (c) 2010-2013, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 26/01/2013
 * 
 */
package generics.wildcard;

import java.util.ArrayList;
import java.util.List;

public class WildCardTest {
	public static void main(String[] args) {
		List<Integer> lst = new ArrayList<Integer>();
		lst.add(3);
		lst.add(32);
		lst.add(-12);

		List<? super Integer> l = new ArrayList<Integer>(lst);
		l.add(3);

		m1(l, 34);

	}

	// Dependendo da declaração do List recebido, irá permitir a adição do item
	// na lista
	public static <T extends Object> void m1(List<T> l, T item) {
		l.add(item);
		for(Object i : l)
			System.out.println(i);
	}
}
