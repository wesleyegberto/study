/**
 * Set
 * 	.interface que estende Collection
 * 	.N�o permite elemento duplicado (usando m�todo equals)
 * 
 * HashSet
 * 	.classe que implementa Set
 * 	.armazena os elementos em um hash table
 * 	.n�o garante ordena��o
 * 	.implementa��o de maior performance
 * 	.tempo constante de opera��o b�sicas (add, contains e remove), assumindo que a fun��o hash distribu� os elementos
 * 	  apropriadamente nos conteiners
 *  .tempo de intera��o � proporcional � quantidade de container * a quantidade de elemento por container
 *  .� recomend�vel ter a capacidade inicial pequena (a padr�o � 16, muito grande perde tempo na compara��o
 *    e muito pequeno perde tempo realocando)
 * 	.n�o � sincronizada
 * 	.permite elementos null
 * 
 * TreeSet
 * 	.classe que implementa SortedSet
 * 	.armazena os elementos em �rvore
 * 	.garante ordena��o pela ordem natural dos elementos (m�todo compare) ou um Comparator (recebido no construtor)
 * 	.mais lento que HashSet
 * 	.garante tempo log(n) para de opera��o b�sicas (add, contains e remove)
 * 	.n�o � sincronizada
 * 	.n�o permite elementos null
 * 
 * LinkedHashSet
 * 	.classe que implementa Set
 * 	.armazena os elementos em um hash table como lista lincada
 * 	.ordena��o pela ordem de inser��o
 * 	.tempo constante de opera��o b�sicas (add, contains e remove), assumindo que a fun��o hash distribu� os elementos
 * 	  apropriadamente nos containers
 *  .tempo de intera��o � proporcional � quantidade de elementos
 *  .� recomend�vel ter a capacidade inicial grande (a padr�o � 16, muito grande perde tempo na compara��o
 *    e muito pequeno perde tempo realocando)
 * 	.n�o � sincronizada
 * 	.permite elementos null
 *  
 *  
 */

package collection.set;

import java.util.TreeSet;

public class SetTest {
	public static void main(String[] args) {
		TreeSet<String> ts = new TreeSet<String>();

		ts.add("A");
		ts.add("C");
		ts.add("E");
		ts.add("B");
		// ts.add(null); N�o permite inserir elemento null

		for(String s : ts) {
			System.out.println(s);
		}
	}
}
