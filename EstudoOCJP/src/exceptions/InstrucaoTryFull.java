package exceptions;

public class InstrucaoTryFull {
	public static void main(String[] args) {
		try {
			System.out.println("Bloco try executando.");
			throw new Exception(); // Aqui lança uma exception, o objeto do tipo
									// Exception é instânciado e lançãdo
		} catch(Exception ex) {
			System.err.println("Bloco catch Exception executando.");
		} finally {
			System.out.println("Bloco finally executando."); // É executando
																// idenpedente
																// do resultado
																// acima.
			// Até se houver um break, continue ou return.
		}
	}
}
