package exceptions;

public class RelancarException {
	public static void main(String[] args) {
		try {
			method1();
			System.out.println("fim do try");
		} catch(Exception ex) {
			System.err.println("Bloco catch que trata a exception lancada no badMethod e relancada pelos outros.");
		}
		System.out.println("fim do main");
	}

	public static void method1() throws Exception // Isso permiti que o método
													// não precise tratar a
													// exceção, sem isso seria
													// necessário o try
	{
		System.out.println("method1");
		method2(); // O método chamado irá lançar uma exceção, mas não será
					// tratado e sim lançado pois não tem intrução try
		// se ocorrer erro, a exceção será relançada
		System.out.println("fim do method1"); // Não está errado, pois o
												// compilador não sabe que será
												// lançado uma exceção
	}

	public static void method2() throws Exception {
		try {
			System.out.println("method2");
			badMethod();
		} catch(Exception e) // Neste bloco catch ele captura e relança a
								// exceção lançada por badMethod
		{
			System.out.println("catch do method2. Relancara a Exception e");
			throw e;
		}
		System.out.println("fim do method2");
	}

	public static void badMethod() throws Exception {
		System.out.println("badMethod");
		// Aqui será lançado a exceção
		throw new Exception();
		// System.out.println("fim do badMethod"); //erro pois nunca será
		// alcançada
	}
}
