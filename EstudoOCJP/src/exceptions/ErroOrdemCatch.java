package exceptions;

public class ErroOrdemCatch {
	public static void main(String[] args) {
		try {
			int i = 9 / 0; // Causará um erro RuntimeException, se ! for
							// tratada, lançará um erro ArithmeticException: /
							// by zero
		}
		// catch(Exception e) //Se for assim, o comentário abaixo não compilará
		catch(ArithmeticException e) {
			System.err.println("Erro desconhecido.");
		}
		// catch(ArithmeticException e) //Pois a Exception já vai ser capturada
		// no primeiro catch, onde captura qualquer erro
		catch(Exception e) {
			System.err.println("Erro de divisão.");
		}
	}
}
