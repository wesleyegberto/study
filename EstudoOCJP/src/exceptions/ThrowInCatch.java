package exceptions;

public class ThrowInCatch {
	public static void main(String[] args) {
		try {
			throw new ArithmeticException();
		} catch(ArithmeticException e) {
			// Achar que uma exceção lançada em um catch será tratada no mesmo
			// ou em outro catch pode causar erros de lógica
			System.err.println("Tratamento no primeiro catch, e lançamento de uma nova.");
			// throw new Exception(); Isso é um erro, pois não houve tratamento.
		} catch(Exception e) {
			System.err.println("Tratamento no segundo catch da segunda excecao, Exception.");
		} finally {
			// Evite colocar código que possa gerar exceção no finally
			try // se possível coloque dentro de uma instrução try no finally
			{
				throw new Exception(); // Esta exceção se não for tratada, a
										// primeira exceção (se não fosse
										// tratada)
										// seria perdida e está seria relançada
										// para o chamador.
			} catch(Exception e) {
				System.err.println("Instrucao try do finally.");
			}
		}
	}
}
