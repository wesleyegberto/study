package exceptions;

public class ExceptionInConstructorCancel {
	int i;

	public ExceptionInConstructorCancel(String x) {
		try {
			i = Integer.parseInt(x);
		} catch(NumberFormatException e) {
			throw e;
		}
	}

	public int getI() {
		return i;
	}

	public static void main(String[] args) {
		ExceptionInConstructorCancel t;

		try {
			t = new ExceptionInConstructorCancel("2s");

			System.out.println(t.getI());
		} catch(NumberFormatException e) {
			System.err.println("Erro no construtor.");
		}

	}
}
