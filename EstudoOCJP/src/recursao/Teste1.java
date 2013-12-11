package recursao;

public class Teste1 {
	static int i;

	public static void main(String[] args) {
		int[] x = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

		String y = new Teste1().eleva(x, 0);

		System.out.println(y);
	}

	String eleva(int[] x, int y) {
		if(y < x.length) {
			String z = String.format("%s%d", eleva(x, y + 1), x[y]);
			System.out.println(z);
			return z;
		} else
			return "";
	}
}
