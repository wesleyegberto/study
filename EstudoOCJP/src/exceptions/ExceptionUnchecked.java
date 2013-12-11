package exceptions;

public class ExceptionUnchecked {
	public static void my() throws RuntimeException // Mesmo declarando, o
													// compilador n�o exige
													// tratamento
	{
		System.out.println("In my");
		throw new RuntimeException();
	}

	public static void main(String[] args) {
		System.out.println("In main.");
		my(); // Posso chamar sem problemas
	}
}
