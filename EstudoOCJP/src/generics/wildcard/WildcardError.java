package generics.wildcard;

import java.util.ArrayList;
import java.util.List;

public class WildcardError {
	/**
	 * O compilador n�o permite adicionar itens em cole��es com wildcards pois
	 * n�o tem certeza se os tipos s�o corretos. Para resolver esse problema �
	 * usado m�todos helper privados (Por conven��o: <methodName>Helper). void
	 * foo(List<?> i) { i.set(0, i.get(0)); }
	 */

	/**
	 * Recebe um List de qualquer coisa, por�m n�o permitir� adicionar nada.
	 */
	void foo(List<?> i) {
		fooHelper(i);
	}

	/**
	 * O type parameter T ir� usar o tipo em tempo de execu��o corretamente.
	 */
	private <T> void fooHelper(List<T> i) {
		i.add(i.get(0));
	}

	public static void main(String[] args) {
		List<String> lst = new ArrayList<String>();
		lst.add("A");
		lst.add("B");
		lst.add("C");
		new WildcardError().foo(lst);
		Wildcards.printAnything(lst);
	}
}
