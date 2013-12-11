package classe;

public class SobrecargaIncorreta {
	public static void main(String[] args) {
		SobrecargaIncorreta si = new SobrecargaIncorreta();

		System.out.println("Area: " + si.area(3, 4));
	}

	public int area(int a, int b) {
		return a * b;
	}

	/*
	 * Incorreta, pois não pode mudar o tipo de retorno public double area(int
	 * a, int b) { return (double) a * b; }
	 */

	public float area(float a, float b) {
		return a * b;
	}
}
