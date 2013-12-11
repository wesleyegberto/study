package expressoes_regulares_pattern_matcher;

/**
 * @author Wesley Egberto de Brito Objetivo: Teste de Expressão Regular
 */

import java.util.regex.Pattern; // Representa um padrão
import java.util.regex.Matcher; // Representa os resultados de uma busca

public class RegEx {
	public static void main(String[] args) {
		// Compila a expressão regular recebida na segunda String, quando for
		// usado com frequencia utiliza-se patter do objeto
		System.out.println(Pattern.matches("\\d\\w", "3d"));

		System.out.println(Pattern.matches("(\\d\\w)$", "asdasd3d"));

		// \D -> Nao numero
		System.out.println(Pattern.matches("\\D\\s\\w", "2 a"));

		System.out.println(Pattern.matches("\\d\\s\\w", "2 a"));

		System.out.println(Pattern.matches("0[xX]([a-fA-F0-9])+", "0x4A"));
	}
}
