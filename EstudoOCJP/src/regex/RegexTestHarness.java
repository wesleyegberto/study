package regex;

/**
 * RegEx em Java
 * 
 * Qualificadores Greedy = Primeiro testa a palavra inteira e depois testa em
 * partes. Reluctant = Testa primeiro em partes e depois a palavra toda
 * Possessive = Testa apenas a palavra toda
 * 
 * 
 * ?=<pattern> -> verifica se a String contém o padrão do pattern sem consumir o
 * texto verificado. Deve ser utilizado com matcher.group() quando o
 * matcher.find() for true. <pattern> -> padrão que será utilizado no matcher.
 * ?:=<pattern> -: verifica se a String contém o padrão do pattern e consome o
 * texto verificado. Deve ser utilizado com matcher.group() quando o
 * matcher.find() for true. <pattern> -> padrão que será utilizado no matcher.
 * ?!<pattern> -> verifica se a String não contém o padrão do pattern sem
 * consumir o texto verificado. Deve ser utilizado com matcher.group() quando o
 * matcher.find() for true (indicando que não contém). <pattern> -> padrão que
 * será utilizado no matcher. ^ -> início da linha $ -> fim da linha \b ->
 * indica delimitador de palavra. Ex: \bdog\b => matches "I have a dog" and
 * doesn't matches "I have two dogs" \B -> indica não delimitador de palavra.
 * Ex: \bdog\B => matches "I have two dogs" and doesn't matches "I have a dog"
 * \A -> inicio da input string. Ex: \Adog => matches "dog on" \z -> fim da
 * input string. Ex: dog\z => matches "on dog" \G -> verifica se começa no fim
 * do match anterior, se não existir não verifica o início (no primeiro match).
 * Ex: \Gdog => matches "dogdog" and doesn't matches "dog dog" (O espaço usa a
 * posição do fim do match anterior)
 * 
 * 
 * Flags Usa a flag com o seguinte formato: (?<F>), onde <F> é a flag. Ou
 * utilizando as constantes da class Pattern
 * 
 * Literal Mode Desibilita os significados especiais dos metacaracterse ('\n'
 * não será mais quebra de linha). Pattern.LITERAL
 * 
 * Case Insensitive Desabilita o case sensitive da pesquisa. A compação é feita
 * em ASCII ('a' == 'A', 'á' != 'Á', 'ç' != 'Ç').
 * Pattern.Pattern.CASE_INSENSITIVE ou ?i
 * 
 * Comments Habilita comentário. Tudo a partir do caracter hashtag (#) até o fim
 * da linha será ignorado na string e ignora os espaços em brancos no regex.
 * Pattern.COMMENTS ou ?x
 * 
 * Dottal Mode Habilita o teste em todos os caracteres, até os especiais de como
 * '\n', '\r', '\t', etc Pattern.DOTALL ou ?s
 * 
 * Multiline Mode Habilita uma input string com várias linhas. Pattern.MULTILINE
 * ou ?m
 * 
 * UNICODE Mode Habilita o teste unicode. Permite a comparação de letras com
 * acentuação. Pattern.UNICODE_CASE ou ?u
 * 
 * Unix Mode Habilita a terminação de linha somente com '\n'. Pattern.UNIX_LINES
 * ou ?d
 */

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTestHarness {
	public static void main(String[] args) {
		/**
		 * Bug no Eclipse -> retorna null pois não tem console vinculado Console
		 * console = System.console(); if(console == null) {
		 * System.err.println("No console."); System.exit(1); }
		 */
		Scanner sc = new Scanner(System.in);
		while(true) {
			// System.out.println("123asd3123".replaceAll("(\\D)+", " "));
			// if(true) break;
			System.out.format("%nEnter your regex: ");
			Pattern pattern = Pattern.compile(sc.nextLine());
			System.out.format("Enter input string to search: ");
			Matcher matcher = pattern.matcher(sc.nextLine());

			boolean found = false;

			while(matcher.find()) {
				System.out.format("I found the text" + " \"%s\" starting at " + "index %d and ending at index %d.%n",
						matcher.group(), matcher.start(), matcher.end());
				found = true;
			}
			if(!found) {
				System.out.format("No match found.%n");
			}
		}
	}
}
