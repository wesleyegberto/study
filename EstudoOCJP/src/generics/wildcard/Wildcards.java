/**
 * Wildcards .É usado para poder receber coleção de qualquer tipo. .Só permite
 * adicionar itens null na coleção, pois o compilador não tem certeza se você
 * está adicionando itens do tipo correto.
 * 
 * Quando Usar .Quando for usar wildcards é recomendado verificar a função da
 * variável. .A variável por ter uma das funções: -"In" Variable: Fornece dados
 * no código. -"Out" Variable: Armazena dados para utilizá-los em outro lugar.
 * Ex.: copy(src, dest) src -> "in" variable dest -> "out" variable
 * 
 * .Dicas -quando a "in" variable é usada como Upper Bounded Type, use a keyword
 * extends. -quando a "out" variable é usada como Lower Bounded Type, use a
 * keywork super. -quando for preciso acessar membros definidos na classe Object
 * não precisar usar limitantes (keywork extends ou super). -quando o código
 * precisa acessar a variável como "in" E como "out" variable não deve usar
 * wildcard.
 */
package generics.wildcard;

import java.util.ArrayList;
import java.util.List;

public class Wildcards {
	/**
	 * In Variable Recebe List de qualquer coisa. Permite usar membros da classe
	 * Object. É usado Unbounded Wildcards (?) quando apenas utiliza membros da
	 * classe Object ou quando o tipo do parâmetro não importa.
	 */
	static void printAnything(List<?> l) {
		for(Object o : l) {
			System.out.print(o + "\t");
		}
		System.out.println();
	}

	/**
	 * In Variable Recebe List de Number ou seus subtipos. É usado Upper Bounded
	 * Wildcards (? extends Number) quando quer receber List apenas deste tipo
	 * ou seus subtipos. Permite usar membros da classe Number.
	 */
	static void printNumber(List<? extends Number> l) {
		for(Number n : l) {
			System.out.print(n + "\t");
		}
		System.out.println();
	}

	/**
	 * Out Variable Recebe List de Number ou seus supertipos. É usado Lower
	 * Bounded Wildcard (? super Number). Permite adicionar o tipo ou subtipos
	 * de Number. Permite usar membros da classe Object.
	 */
	static void fillWithNumber(List<? super Number> l) {
		for(int i = 0; i < 3; i++)
			l.add(i);
	}

	/**
	 * In and Out Variable Recebe List de Number ou seus subtipos. Permite usar
	 * membros do Number. Permite adicionar itens do mesmo tipo. Poderia ou não
	 * utilizar limitantes.
	 */
	static <T extends Number> void copyNumber(List<T> ls, List<T> ld) {
		for(T t : ls) {
			ld.add(t);
		}
	}

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		List<?> l1 = new ArrayList(); // Warning unchecked

		l1.add(null); // -> List<?> só permite adicionar null
		/*
		 * Não permite adicionar itens, exceto null, pois o compilador não sabe
		 * que tipo de lista é. l1.add(10); l1.add("S20");
		 */
		printAnything(l1);

		List<Integer> l2 = new ArrayList<Integer>();
		l2.add(100);
		l2.add(20);
		l2.add(3);
		printNumber(l2);

		l1 = l2; // Permite atribuir l2 a l1 mas depois não deixará modificar.

		List<Object> l3 = new ArrayList<Object>();
		l3.add(10);
		l3.add("T");
		fillWithNumber(l3);
		printAnything(l3);
	}
}
