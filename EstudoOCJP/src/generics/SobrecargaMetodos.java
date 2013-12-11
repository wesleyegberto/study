package generics;

// Com sobrecarga de métodos genéricos, é chamado aquele que "melhor"
// corresponde ao tipo passado
// aquele que é mais específico para o tipo passado.
public class SobrecargaMetodos {
	static <T, V> void metodo(T t, V v) {
		System.out.println("In metodo 1");
	}

	static <T extends Number, V> void metodo(T t, V v) {
		System.out.println("In metodo 2");
	}

	static <T extends Integer, V> void metodo(T t, V v) {
		System.out.println("In metodo 3");
	}

	public static void main(String[] args) {
		metodo(new Integer(2), new Object());
	}
}
