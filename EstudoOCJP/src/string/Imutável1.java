package string;

public class Imutável1 {
	public static void main(String... args) {
		String s = "Java";

		// O código abaixo instância uma nova String em maiúsculo e devolverá a
		// referência. Logo 's' apontará para outra.
		s = s.toUpperCase();

		System.out.println(s); // Sairá a maiúscula

		// O código abaixo instância uma nova String em minúscula e devolverá a
		// referência.
		// Porém 's' ainda apontará para à anterior, pois não foi atribuido a
		// referência a ele.
		s.toLowerCase();

		System.out.println(s); // Sairá a maiúscula
	}
}
