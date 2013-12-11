/**
 * Wildcards .� usado para poder receber cole��o de qualquer tipo. .S� permite
 * adicionar itens null na cole��o, pois o compilador n�o tem certeza se voc�
 * est� adicionando itens do tipo correto.
 * 
 * Quando Usar .Quando for usar wildcards � recomendado verificar a fun��o da
 * vari�vel. .A vari�vel por ter uma das fun��es: -"In" Variable: Fornece dados
 * no c�digo. -"Out" Variable: Armazena dados para utiliz�-los em outro lugar.
 * Ex.: copy(src, dest) src -> "in" variable dest -> "out" variable
 * 
 * .Dicas -quando a "in" variable � usada como Upper Bounded Type, use a keyword
 * extends. -quando a "out" variable � usada como Lower Bounded Type, use a
 * keywork super. -quando for preciso acessar membros definidos na classe Object
 * n�o precisar usar limitantes (keywork extends ou super). -quando o c�digo
 * precisa acessar a vari�vel como "in" E como "out" variable n�o deve usar
 * wildcard.
 */
package generics.wildcard;

import java.util.ArrayList;
import java.util.List;

public class Wildcards {
	/**
	 * In Variable Recebe List de qualquer coisa. Permite usar membros da classe
	 * Object. � usado Unbounded Wildcards (?) quando apenas utiliza membros da
	 * classe Object ou quando o tipo do par�metro n�o importa.
	 */
	static void printAnything(List<?> l) {
		for(Object o : l) {
			System.out.print(o + "\t");
		}
		System.out.println();
	}

	/**
	 * In Variable Recebe List de Number ou seus subtipos. � usado Upper Bounded
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
	 * Out Variable Recebe List de Number ou seus supertipos. � usado Lower
	 * Bounded Wildcard (? super Number). Permite adicionar o tipo ou subtipos
	 * de Number. Permite usar membros da classe Object.
	 */
	static void fillWithNumber(List<? super Number> l) {
		for(int i = 0; i < 3; i++)
			l.add(i);
	}

	/**
	 * In and Out Variable Recebe List de Number ou seus subtipos. Permite usar
	 * membros do Number. Permite adicionar itens do mesmo tipo. Poderia ou n�o
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

		l1.add(null); // -> List<?> s� permite adicionar null
		/*
		 * N�o permite adicionar itens, exceto null, pois o compilador n�o sabe
		 * que tipo de lista �. l1.add(10); l1.add("S20");
		 */
		printAnything(l1);

		List<Integer> l2 = new ArrayList<Integer>();
		l2.add(100);
		l2.add(20);
		l2.add(3);
		printNumber(l2);

		l1 = l2; // Permite atribuir l2 a l1 mas depois n�o deixar� modificar.

		List<Object> l3 = new ArrayList<Object>();
		l3.add(10);
		l3.add("T");
		fillWithNumber(l3);
		printAnything(l3);
	}
}
