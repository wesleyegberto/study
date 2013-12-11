package classes;

import java.util.Arrays;

public class ArraysTest {
	public static void main(String... args) {
		int[] i = new int[4];

		for(int x = 0; x < 4; x++)
			i[x] = x * 2;

		for(int y : i)
			System.out.print(y + " ");

		System.out.println("\n");

		int[] z = Arrays.copyOfRange(i, 1, 3);

		for(int y : z)
			System.out.print(y + " ");

		int[] f = new int[] { 51, 51, 65, 12, 21, 84, 485 };
		String x = Arrays.toString(f);

		System.out.println("\n" + x);

		System.out.println(Arrays.hashCode(f));
	}
}
