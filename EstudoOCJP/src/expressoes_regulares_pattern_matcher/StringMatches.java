package expressoes_regulares_pattern_matcher;

import java.util.regex.*;
import javax.swing.JOptionPane;

public class StringMatches {
	public static void main(String[] args) {
		Pattern pat = Pattern.compile("\\d{3}.\\d{3}.\\d{3}-\\d{2}");

		String cpf = JOptionPane.showInputDialog(null, "Entre com o CPF:");

		if(cpf.matches("\\d\\d\\d.\\d\\d\\d.\\d\\d\\d-\\d\\d"))
			JOptionPane.showMessageDialog(null, "CPF nos conformes");
		else
			JOptionPane.showMessageDialog(null, "Safado");

	}
}
