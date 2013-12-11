/**
 * @author Wesley Egberto de Brito Objetivo:
 *  Testar a class LinkedHashMap
 *    -estrutura de dados interna em tabela hash
 *    -n�o permite chaves duplicadas
 *    -n�o garante a ordena��o e nem a const�ncia da ordem
 *    -n�o � synchronized
 *    -permite chave null
 */

package collection.map;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class LinkedHashMapTest {
	public static void main(String[] args) {
		// Instancia um LinkedHashMap com capacidade inicial de 16 posi��es,
		// 0.75 de fator de carga e ordem de inser��o
		LinkedHashMap<String, Integer> hm = new LinkedHashMap<String, Integer>();

		hm.put("Maria", 3);
		hm.put("Joao", 8);
		hm.put("Roberto", 6);
		hm.put("Manuel", 7);

		Iterator<String> it = hm.keySet().iterator();
		String key = null;
		System.out.println("Ordem de insercao, 16 posicoes e fator de carga 0.75:");
		while(it.hasNext()) {
			key = it.next();
			System.out.println(key + " => " + hm.get(key));
		}

		// Instancia um
		// LinkedHashMap
		// com
		// capacidade
		// inicial de 6
		// posi��es, 0.5
		// de fator de
		// carga e ordem
		// de frequencia  de acesso
		hm = new LinkedHashMap<String, Integer>(6, 0.5f, true);

		hm.put("Maria", 3);
		hm.put("Joao", 8);
		hm.put("Roberto", 6);
		hm.put("Manuel", 7);

		// Altera a frequencia de acesso dos objetos, ordem se frequencia:
		// Manuel, Roberto, Maria, Joao
		hm.get("Roberto");
		hm.get("Maria");
		hm.get("Manuel");
		hm.get("Maria");
		hm.get("Roberto");
		hm.get("Maria");
		hm.get("Joao");

		it = hm.keySet().iterator();
		/*
		 * key = null; System.out.println(
		 * "\nOrdem de frequencia, 4 posicoes e fator de carga 0.5:");
		 * while(it.hasNext()) { N�o funciona, pois quando o get() � chamado a
		 * ordem � alterada por causa da frequencia de acesso key = it.next();
		 * System.out.println(key + " => " + hm.get(key));
		 * 
		 * 
		 * }
		 */
		Iterator<Integer> li = hm.values().iterator();
		key = null;
		System.out.println("\nOrdem de frequencia, 4 posicoes e fator de carga 0.5:");
		while(li.hasNext()) {
			key = it.next();
			System.out.println(key + " => " + li.next());
		}
	}
}
