/**
 * @author Wesley Egberto de Brito Objetivo: Testar a class HashMap -estrutura
 *         de dados interna em tabela hash -n�o permite chaves duplicadas -n�o
 *         garante a ordena��o e nem a const�ncia da ordem -n�o � synchronized
 *         -permite chave null
 */

package collection.map;

import java.util.Iterator;
import java.util.HashMap;

public class HashMapTest {
	public static void main(String[] args) {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();

		hm.put("Maria", 3);
		hm.put("Joao", 8);
		hm.put("Manuel", 7);
		hm.put("Marcelo", 6);

		Iterator<String> it = hm.keySet().iterator();
		String key;
		while(it.hasNext()) {
			key = it.next();
			System.out.println(key + " => " + hm.get(key));
		}

	}
}
