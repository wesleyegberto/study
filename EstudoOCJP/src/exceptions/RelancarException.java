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

	public static void method1() throws Exception // Isso permiti que o m�todo
													// n�o precise tratar a
													// exce��o, sem isso seria
													// necess�rio o try
	{
		System.out.println("method1");
		method2(); // O m�todo chamado ir� lan�ar uma exce��o, mas n�o ser�
					// tratado e sim lan�ado pois n�o tem intru��o try
		// se ocorrer erro, a exce��o ser� relan�ada
		System.out.println("fim do method1"); // N�o est� errado, pois o
												// compilador n�o sabe que ser�
												// lan�ado uma exce��o
	}

	public static void method2() throws Exception {
		try {
			System.out.println("method2");
			badMethod();
		} catch(Exception e) // Neste bloco catch ele captura e relan�a a
								// exce��o lan�ada por badMethod
		{
			System.out.println("catch do method2. Relancara a Exception e");
			throw e;
		}
		System.out.println("fim do method2");
	}

	public static void badMethod() throws Exception {
		System.out.println("badMethod");
		// Aqui ser� lan�ado a exce��o
		throw new Exception();
		// System.out.println("fim do badMethod"); //erro pois nunca ser�
		// alcan�ada
	}
}
