package formatador;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatTest {
	/**
	 * Instance -> Números CurrencyInstance -> Valores monetários
	 * IntegerInstance -> Números inteiros
	 */
	public static void main(String[] args) {
		NumberFormat nf = null;
		String number = null;

		System.out.println("==Using Instance==");
		nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(4); // Define a quantidade mínima de casas
										// decimais
		number = nf.format(300.232323);
		System.out.println(number);

		number = nf.format(300232323);
		System.out.println(number);

		nf = NumberFormat.getInstance(Locale.US);
		nf.setMinimumFractionDigits(4); // Define a quantidade mínima de casas
										// decimais
		number = nf.format(300.232323);
		System.out.println(number);

		number = nf.format(300232323);
		System.out.println(number);

		System.out.println("==Using Currency==");
		nf = NumberFormat.getCurrencyInstance();
		number = nf.format(300.232323);
		System.out.println(number);

		number = nf.format(300232323);
		System.out.println(number);

		nf = NumberFormat.getCurrencyInstance(Locale.US);
		number = nf.format(300.232323);
		System.out.println(number);

		number = nf.format(300232323);
		System.out.println(number);

		System.out.println("==Using IntegerInstance==");
		nf = NumberFormat.getIntegerInstance();
		number = nf.format(300.232323);
		System.out.println(number);

		number = nf.format(300232323);
		System.out.println(number);

		nf = NumberFormat.getIntegerInstance(Locale.US);
		number = nf.format(300.232323);
		System.out.println(number);

		number = nf.format(300232323);
		System.out.println(number);

	}
}
