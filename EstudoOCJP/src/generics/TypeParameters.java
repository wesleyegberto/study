/**
 * Type Erasure .Gen�ricos foram introduzidos no Java para permitir uma
 * programa��o gen�rica e para permitir ao compilador checagem de tipos mais
 * rigorosas. .Para implementar gen�ricos o compilador Java executa um processo,
 * chamado Type Erasure, para deixar o c�digo gen�rico e consistente. Essas
 * opera��es s�o: -substituir todas as ocorr�ncias do type parameters pelos
 * tipos mais espec�ficos. Ex.: Quando s�o lower bounded ou ubounded �
 * substitu�do por Object List<? super Number> lst; => List<Object> lst; List<T>
 * lst; -> List<Object> lst; Quando s�o upper bounded s�o substitu�do pelo
 * supertipo passado List<? extends Number> lst; -> List<Number> lst; -insere os
 * casts necess�rios depois de substituir os type parameters Ex.: O c�digo
 * abaixo: List<? extends Number> lst = new ArrayList(); lst.add(10); int x =
 * lst.get(0).intValue(); Vira: List<Number> lst = new ArrayList<Number>();
 * lst.add((Number)10); int x = ((Number) lst.get(0)).intValue();
 * 
 * Varargs .Gen�ricos com varargs pode causas: -polui��o da mem�ria heap -lan�ar
 * ClassCastException .Se n�o aplicado corretamente por causar problemas. Ex.:
 * <T> void doStuff(T ... arg) { Object[] b = arg; // Isso permite adicionar
 * qualquer coisa no array e posteriormente quando for ler // poder� lan�ar
 * ClassCastException b[0] = new ArrayList<String>(); }
 * 
 * 
 */
package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypeParameters {
	/**
	 * Recebe List de qualquer coisa. O tipo "qualquer coisa" (T) deve ser
	 * declara entre colchetes angulares (<>) antes do tipo de retorno do
	 * m�todo.
	 */
	static <T> T printGeneric(List<T> l) {
		/*
		 * N�o permite inser��o l.add(new Integer(3)); l.add(null);
		 */

		for(T t : l) {
			System.out.print(t + "\t");
		}
		System.out.println();
		return null;
	}

	/**
	 * Recebe apenas List de Object ou RawType
	 */
	static void printObject(List<Object> l) {
		/*
		 * N�o permite inser��o l.add(new Object()); l.add(null);
		 */
		for(Object o : l) {
			System.out.print(o + "\t");
		}
		System.out.println();
	}

	/**
	 * Recebe apenas List de Integer ou RawType (lan�ar� exception se o RawType
	 * n�o conter somente Integer)
	 */
	static void printInteger(List<Integer> l) {
		/*
		 * N�o permite inser��o l.add(new Integer(3)); l.add(null);
		 */
		for(Integer i : l) {
			System.out.print(i + "\t");
		}
		System.out.println();
	}

	/**
	 * Recebe List de qualquer coisa
	 */
	static <T> void printListGeneric(List<List<T>> l) {
		/*
		 * N�o permite inser��o l.add(new Integer(3)); l.add(null);
		 */
		for(List<T> list : l)
			for(T t : list)
				System.out.print(t + "\t");
		System.out.println();
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List<Object> l1 = new ArrayList<Object>();
		l1.add(new Object());
		l1.add(new Object());
		l1.add(new String("TT"));
		l1.add(null); // -> N�o permite adicionar qualquer coisa que estenda
						// Object

		List<Integer> l2 = new ArrayList<Integer>();
		l2.add(10);
		l2.add(20);
		l2.add(30);
		List<String> l3 = Arrays.asList("T1", "T2", "T3");

		/**
		 * RawType: Usa Object. N�o especificar o tipo do argumento permite usar
		 * qualquer m�todo com o l5.
		 */
		List l5 = new ArrayList(); // Warning unchecked
		l5.add(10);
		l5.add("T20");
		l5.add(30);

		// l1 = l2; //-> N�o permite (N�o envolve RawType ou wildcard, e
		// List<Integer> n�o � subtipo de List<Object>)

		printGeneric(l1);
		printInteger(l2);
		printGeneric(l3);
		printObject(l1);
		printGeneric(l5); // Warning unchecked

		List<List<Integer>> list = new ArrayList<List<Integer>>();
		list.add(l2);
		// O argumento gen�rico abaixo � opcional pois o java usa Type Inference
		// para saber o tipo que ele precisa
		TypeParameters.<Integer> printListGeneric(list);

	}
}
