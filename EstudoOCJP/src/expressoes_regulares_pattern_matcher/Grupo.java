package expressoes_regulares_pattern_matcher;

import java.util.regex.*;
import javax.swing.JOptionPane;

public class Grupo {
	public static void main(String[] args) throws Exception {
		String data = "12-30-2002"; // MM-DD-AAAA
		Pattern p = Pattern.compile("(\\d{2})-(\\d{2})-(\\d{4})");
		Matcher m = p.matcher(data);
		if(!m.find()) {
			throw new Exception("A data não está no formato americano");
		}
		// $n --> indica um grupo ()
		String dataBrasileira = m.replaceAll("$2/$1/$3");
		System.out.println(dataBrasileira);
	}
}
