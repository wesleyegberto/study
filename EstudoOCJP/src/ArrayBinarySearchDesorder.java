import java.util.Arrays;

public class ArrayBinarySearchDesorder {
	public static void main(String[] args) {
		int[] a = new int[20];

		for(int i = 0; i < a.length; i++)
			a[i] = (int) (Math.random() * 100);

		System.out.print("[");
		for(int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.print("]\n");

		/*
		 * Arrays.sort(a); System.out.print("["); for(int i = 0; i < a.length;
		 * i++) System.out.print(a[i] + " "); System.out.print("]\n\n");
		 */

		while(true) {
			int r = (int) (Math.random() * 100);

			int location = Arrays.binarySearch(a, r);

			if(location >= 0) {
				System.out.println("Location of " + r + " is " + location + ", a[" + location + "] = " + a[location]);
				break; // Out of while loop
			}
		}
	}
}
