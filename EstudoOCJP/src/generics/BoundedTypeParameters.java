/**
 * Os Bounded Type Parameters s�o usados para limitar os tipos de objetos que
 * ser�o adicionados em uma cole��o.
 * 
 * Sintaxe: Upper: < {nome} extends {Superclass or Interface}> Lower: < ? super
 * {Subclasse or interface}> Ex: <E extends Object> <E extends List> List<?
 * super Integer>
 */
package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoundedTypeParameters {
	/**
	 * Upper Bounded Type Recebe um List de elementos Number ou seus subtipos
	 */
	static <F extends Number> F sort(List<F> list) {
		F f = null;
		return f;
	}

	/**
	 * Upper Bounded Type Equivale a declara��o acima, por�m n�o poder�
	 * reutilizar o Type Argument para decla��o ou retorno
	 */
	static void sort2(List<? extends Number> list) {

	}

	/**
	 * Lower Bounded Type Recebe um List de elementos Number ou seus supertipos
	 */
	static void sort3(List<? super C> list) {

	}

	/**
	 * O Type Parameter L deve ser subclasse ou implementar todos os par�metros
	 * (A, D e C). Se um dos par�metros for uma classe esta deve ser a primeira
	 * (no caso a classe A) sen�o dar� erro.
	 */
	static <L extends A & D & E> void marge(List<L> list) {

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		List<Integer> l1 = Arrays.asList(1, 2, 3, 4, 5);
		sort(l1);

		// A classe A � subclasse da B que implementa C e D
		List<A> l2 = Arrays.asList(new A(), new A(), new A());
		marge(l2);

		List<C> l3 = Arrays.asList(new C() {
		}, new C() {
		});
		sort3(l3);

		List<Number> lst0 = new ArrayList<Number>();
		List<? extends Number> lst1 = lst0;
		List<? super Integer> lst2 = lst0;
	}
}

interface E {
}

interface D {
}

interface C extends E {
}

class B implements C, D {
}

class A extends B {
}
