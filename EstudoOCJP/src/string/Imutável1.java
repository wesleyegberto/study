package string;

public class Imut�vel1 {
	public static void main(String... args) {
		String s = "Java";

		// O c�digo abaixo inst�ncia uma nova String em mai�sculo e devolver� a
		// refer�ncia. Logo 's' apontar� para outra.
		s = s.toUpperCase();

		System.out.println(s); // Sair� a mai�scula

		// O c�digo abaixo inst�ncia uma nova String em min�scula e devolver� a
		// refer�ncia.
		// Por�m 's' ainda apontar� para � anterior, pois n�o foi atribuido a
		// refer�ncia a ele.
		s.toLowerCase();

		System.out.println(s); // Sair� a mai�scula
	}
}
