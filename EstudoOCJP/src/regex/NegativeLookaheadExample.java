package regex;

import java.util.regex.*;

public class NegativeLookaheadExample {
	public static void main(String args[]) throws Exception {
		// define the pattern
		String regex = "John (?!Smith)[A-Z]\\w+";

		// compile the pattern
		Pattern pattern = Pattern.compile(regex);

		String candidate = "I think that John Smith ";
		candidate += "is a fictional character. His real name ";
		candidate += "might be John Jackson, John Westling, ";
		candidate += "or John Holmes for all we know.";

		// extract a matcher for the candidate string
		Matcher matcher = pattern.matcher(candidate);

		String tmp = null;

		// extract the matching group. Notice that it's
		// the default group, since lookarounds are
		// noncapturing
		while(matcher.find()) {
			tmp = matcher.group();
			System.out.println("MATCH:" + tmp);
		}
	}
}
