package exceptions;

public class InstrucaoTryFull {
	public static void main(String[] args) {
		try {
			System.out.println("Bloco try executando.");
			throw new Exception(); // Aqui lan�a uma exception, o objeto do tipo
									// Exception � inst�nciado e lan��do
		} catch(Exception ex) {
			System.err.println("Bloco catch Exception executando.");
		} finally {
			System.out.println("Bloco finally executando."); // � executando
																// idenpedente
																// do resultado
																// acima.
			// At� se houver um break, continue ou return.
		}
	}
}
