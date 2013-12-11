package regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexQualifier {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Pattern patterns[] = new Pattern[3];

		// Greedy
		patterns[0] = Pattern.compile("red.+red");
		// Reluctant
		patterns[1] = Pattern.compile("red.+?red");
		// Possessive
		patterns[2] = Pattern.compile("red.++red");

		System.out.format("Enter input string to search: ");
		String phrase = sc.nextLine();

		System.out.format("Text: %s", phrase);
		for(Pattern p : patterns) {
			boolean found = false;
			Matcher matcher = p.matcher(phrase);

			System.out.format("%n%s%n", p.pattern());
			while(matcher.find()) {
				System.out.format("    Found the text \"%s\" starting at index %d and ending at index %d.%n",
						matcher.group(), matcher.start(), matcher.end());
				found = true;
			}
			if(!found)
				System.out.format("    No match found.%n");
		}
	}
}
