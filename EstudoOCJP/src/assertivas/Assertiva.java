package assertivas;

public class Assertiva {
	public static void main(String[] args) {
		System.out.println("Teste de assert");

		int x = 3, y = 2, z = 9;

		assert (x > z) : "z e maior que x";

		System.out.println("Fim teste");
	}
}
