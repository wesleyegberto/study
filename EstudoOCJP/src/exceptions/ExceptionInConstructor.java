package exceptions;

public class ExceptionInConstructor {
	int i;

	// Quando relançada a exception o objeto não é criado
	public ExceptionInConstructor(String x) // throws NumberFormatException
	{
		try {
			i = Integer.parseInt(x);
		} catch(NumberFormatException e) {
			System.err.println("Erro no construtor.");
		}
	}

	public static void main(String[] args) {
		ExceptionInConstructor t = new ExceptionInConstructor("2s");

	}
}
