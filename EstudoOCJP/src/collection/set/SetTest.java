/**
 * Set
 * 	.interface que estende Collection
 * 	.Não permite elemento duplicado (usando método equals)
 * 
 * HashSet
 * 	.classe que implementa Set
 * 	.armazena os elementos em um hash table
 * 	.não garante ordenação
 * 	.implementação de maior performance
 * 	.tempo constante de operação básicas (add, contains e remove), assumindo que a função hash distribuí os elementos
 * 	  apropriadamente nos conteiners
 *  .tempo de interação é proporcional à quantidade de container * a quantidade de elemento por container
 *  .é recomendável ter a capacidade inicial pequena (a padrão é 16, muito grande perde tempo na comparação
 *    e muito pequeno perde tempo realocando)
 * 	.não é sincronizada
 * 	.permite elementos null
 * 
 * TreeSet
 * 	.classe que implementa SortedSet
 * 	.armazena os elementos em árvore
 * 	.garante ordenação pela ordem natural dos elementos (método compare) ou um Comparator (recebido no construtor)
 * 	.mais lento que HashSet
 * 	.garante tempo log(n) para de operação básicas (add, contains e remove)
 * 	.não é sincronizada
 * 	.não permite elementos null
 * 
 * LinkedHashSet
 * 	.classe que implementa Set
 * 	.armazena os elementos em um hash table como lista lincada
 * 	.ordenação pela ordem de inserção
 * 	.tempo constante de operação básicas (add, contains e remove), assumindo que a função hash distribuí os elementos
 * 	  apropriadamente nos containers
 *  .tempo de interação é proporcional à quantidade de elementos
 *  .é recomendável ter a capacidade inicial grande (a padrão é 16, muito grande perde tempo na comparação
 *    e muito pequeno perde tempo realocando)
 * 	.não é sincronizada
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
		// ts.add(null); Não permite inserir elemento null

		for(String s : ts) {
			System.out.println(s);
		}
	}
}
