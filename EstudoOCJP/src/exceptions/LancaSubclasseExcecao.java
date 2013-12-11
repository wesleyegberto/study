package exceptions;

public class LancaSubclasseExcecao {
	public static void teste() throws Exception {
		throw new ClassNotFoundException();
	}

	public static void main(String[] args) {
		try {
			teste();
		} catch(ClassNotFoundException e) {
			System.err.println("Excecao recebida com sucesso, mesmo sendo declarado uma superclasse da excecao.");
		} catch(Exception e) {
			System.err.println("Excecao desconhecida, pois pode ser qualquer outra subexception.");
		}
	}
}
