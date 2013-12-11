package expressoes_regulares_pattern_matcher;

import java.util.regex.*;
import javax.swing.JOptionPane;

public class PatternMatcher {
	public static void main(String[] args) {
		Pattern pat = Pattern.compile("(\\d{3}.\\d{3}.\\d{3}-\\d{2})?");

		String cpf = JOptionPane.showInputDialog(null, "Entre com o CPF:");

		Matcher mat = pat.matcher(cpf);

		while(mat.find()) {
			System.out.println(mat.group());
		}
	}
}
