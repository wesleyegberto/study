package exceptions;

public class ThrowInCatch {
	public static void main(String[] args) {
		try {
			throw new ArithmeticException();
		} catch(ArithmeticException e) {
			// Achar que uma exce��o lan�ada em um catch ser� tratada no mesmo
			// ou em outro catch pode causar erros de l�gica
			System.err.println("Tratamento no primeiro catch, e lan�amento de uma nova.");
			// throw new Exception(); Isso � um erro, pois n�o houve tratamento.
		} catch(Exception e) {
			System.err.println("Tratamento no segundo catch da segunda excecao, Exception.");
		} finally {
			// Evite colocar c�digo que possa gerar exce��o no finally
			try // se poss�vel coloque dentro de uma instru��o try no finally
			{
				throw new Exception(); // Esta exce��o se n�o for tratada, a
										// primeira exce��o (se n�o fosse
										// tratada)
										// seria perdida e est� seria relan�ada
										// para o chamador.
			} catch(Exception e) {
				System.err.println("Instrucao try do finally.");
			}
		}
	}
}
