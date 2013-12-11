/**
 * @author Wesley Egberto de Brito Objetivo: Teste com a classe DateFormat
 */

package formatador;

import static java.lang.System.out;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

public class DateFormatTest {
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();

		c.set(Calendar.DAY_OF_MONTH, 21);
		c.set(Calendar.DATE, 21); // Idem ao anterior
		c.set(Calendar.MONTH, 11); // 0 -> Janeiro, ..., 11 -> Dezembro
		c.set(Calendar.YEAR, 2012);

		c.set(Calendar.HOUR, 10); // Hora da manhã ou tarde (12h e am/pm)
		c.set(Calendar.HOUR_OF_DAY, 10); // Hora do dia (24h)
		c.set(Calendar.MINUTE, 45);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 899);

		Date d = c.getTime();

		DateFormat f = DateFormat.getInstance(); // Usa o formator para a
													// localidade e style
													// default, SHORT (ex.:
													// 21/12/12 10:45)
		out.println("SHORT => " + f.format(d));

		f = DateFormat.getDateInstance(DateFormat.MEDIUM); // Usa o formator
															// para a localidade
															// default e o style
															// recebido, MEDIUM
															// (ex.: 21/12/2012)
		out.println("MEDIUM => " + f.format(d));

		f = DateFormat.getDateInstance(DateFormat.LONG); // Usa o formator para
															// a localidade
															// default e o style
															// recebido, LONG
															// (ex.: 21 de
															// Dezembro de 2012)
		out.println("LONG => " + f.format(d));

		f = DateFormat.getDateInstance(DateFormat.FULL); // Usa o formator para
															// a localidade
															// default e o style
															// recebido, FULL
															// (ex.:
															// Sexta-feira, 21
															// de Dezembro de
															// 2012)
		out.println("FULL => " + f.format(d));

		System.out.println("\n\nTestes com Locale");
		Locale loc = new Locale("en", "Us"); // Lingua e pais

		f = DateFormat.getDateInstance(DateFormat.LONG, loc); // Usa o
																// formatador
																// para o style
																// e a
																// localidade
																// recebida
		out.println("LONG e Locale => " + f.format(d));

		String dataExtenso = null;
		DateFormat dateFormat = null;

		c.set(Calendar.DAY_OF_MONTH, 2);
		c.set(Calendar.MONTH, 2);

		Properties myConfig = System.getProperties();
		Locale.setDefault(new Locale(myConfig.getProperty("user.language"), myConfig.getProperty("user.country")));

		dateFormat = new SimpleDateFormat("EEEEE',' dd 'de' MMMM");
		dataExtenso = dateFormat.format(c.getTime());
		System.out.println(dataExtenso);

		dateFormat = new SimpleDateFormat("'Barueri,' dd 'de' MMMM 'de' yyyy");
		dataExtenso = dateFormat.format(c.getTime());
		System.out.println(dataExtenso);

		Locale.setDefault(new Locale("en", "US"));
		dateFormat = new SimpleDateFormat("MMM dd',' yyyy");
		dataExtenso = dateFormat.format(c.getTime());
		System.out.println(dataExtenso);
	}

}
