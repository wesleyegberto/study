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

public class WildCardMethod {

	public static void main(String[] args) {
		List<Number> l = new ArrayList<Number>();
		l.add(3);
		l.add(1);
		wildCardExtends(l);

		for(Number i : l)
			System.out.println(i);
	}

	// Esse m�todo receber� qualquer List
	public static void wildCard(List<?> l) {
		// l.add(4); o Wildcard n�o permite adicionar objetos, pois n�o garante
		// que todas as List recebidas s�o compativeis
		l.add(null); // apenas null pois se aplica a todos os tipos de objetos
	}

	// Esse m�todo receber� qualquer List de subtipos de Number
	public static void wildCardExtends(List<? extends Number> l) {
		// l.add(4); tambem n�o permite adicionar
		l.add(null);
	}

	public static void wildCardSuper(List<? super Integer> l) {
		l.add(5); // Permite adicionar pois todas as List recebidas s�o
					// superclasses de Integer, ent�o aceitar�o o elemento
					// adicionado
		l.add(null);
	}
}
