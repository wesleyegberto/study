/**
 * @author Wesley Egberto de Brito Objetivo: Testar o método printf() e suas
 *         formatações
 */

package formatador;

import java.util.Date;

public class PrintfTest {
	public static void main(String[] args) {
		Date d = new Date();

		// O < indica q usará o argumento anterior
		// o 1$ indica q usará o primeiro argumento
		// %n quebra de linha
		// td -> dia com 2 digitos, tm -> mes com 2 digitos, tY -> ano cm 4
		// digitos
		System.out.printf("%td/%<tm/%1$tY%n", d);

		// tH -> hora com 2 digitos, tM -> minuto com 2 digitos, tS -> segundo,
		// tL -> milisegundos
		System.out.printf("%tH:%<tM:%<tS.%<tL%n", d);

		System.out.println();

		// 0 seguido de numero indica q são x casas e completada com 0 a
		// esquerda
		// , -> separador de milhares
		System.out.printf("%,09d%n", 1645);

		System.out.printf("%,.2f%n", 3998.634);

		System.out.printf("%,.2f%n", 3998.634);

		// ( -> quando o numero é negativo envolve em ()
		System.out.printf("%(d%n", -12324);

		// + -> utilizara sinal tanto para numero negativos quanto positivos
		System.out.printf("%+d%n", 5554);
		System.out.printf("%+d%n", -422);
	}
}
