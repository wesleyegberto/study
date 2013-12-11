package formatador;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.text.MaskFormatter;

public class MessageFormatTest {
	public static void main(String[] args) {
		final int BR = 0;
		final int US = 1;

		final String PATTERN_PARABENS = "{0,choice,0#Parabens para|0<Happy birthday to} {1} {0,choice,0#neste dia|0<in this day} {2, date}";
		String msg = null;

		msg = MessageFormat.format(PATTERN_PARABENS, BR, "Wesley", new Date());
		System.out.println(msg);

		msg = MessageFormat.format(PATTERN_PARABENS, US, "Wesley", new Date());
		System.out.println(msg);
		
		System.out.println(MessageFormat.format("###.###.###-##", 29336965223L));
		try {
			MaskFormatter maskFormatter = new MaskFormatter("###.###.###-##");
			maskFormatter.setValueContainsLiteralCharacters(false);
			System.out.println(maskFormatter.valueToString("43123909832"));
		} catch(ParseException e) {
			e.printStackTrace();
		}
		
		
	}
}
