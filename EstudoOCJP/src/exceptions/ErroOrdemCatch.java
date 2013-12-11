package exceptions;

public class ErroOrdemCatch {
	public static void main(String[] args) {
		try {
			int i = 9 / 0; // Causar� um erro RuntimeException, se ! for
							// tratada, lan�ar� um erro ArithmeticException: /
							// by zero
		}
		// catch(Exception e) //Se for assim, o coment�rio abaixo n�o compilar�
		catch(ArithmeticException e) {
			System.err.println("Erro desconhecido.");
		}
		// catch(ArithmeticException e) //Pois a Exception j� vai ser capturada
		// no primeiro catch, onde captura qualquer erro
		catch(Exception e) {
			System.err.println("Erro de divis�o.");
		}
	}
}
