package generics.wildcard;

import java.util.ArrayList;
import java.util.List;

public class WildcardError {
	/**
	 * O compilador não permite adicionar itens em coleções com wildcards pois
	 * não tem certeza se os tipos são corretos. Para resolver esse problema é
	 * usado métodos helper privados (Por convenção: <methodName>Helper). void
	 * foo(List<?> i) { i.set(0, i.get(0)); }
	 */

	/**
	 * Recebe um List de qualquer coisa, porém não permitirá adicionar nada.
	 */
	void foo(List<?> i) {
		fooHelper(i);
	}

	/**
	 * O type parameter T irá usar o tipo em tempo de execução corretamente.
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
