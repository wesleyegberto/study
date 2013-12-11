package exceptions;

public class ExceptionUnchecked {
	public static void my() throws RuntimeException // Mesmo declarando, o
													// compilador não exige
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
