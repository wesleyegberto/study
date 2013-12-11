package formatador;

import java.text.DecimalFormat;

public class DecimalFormatTest {
	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("###,##0.00");
		System.out.println(df.format(2));
		System.out.println(df.format(1561.60500000001));
	}
}
