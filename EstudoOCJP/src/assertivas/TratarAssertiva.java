package assertivas;

public class TratarAssertiva {
	public static void main(String[] args) {
		System.out.println("Teste de assert");

		int x = 3, y = 2, z = 9;

		try {
			assert (x > z) : "z e maior que x";
		} catch(AssertionError e) {
			e.printStackTrace();
		} finally {
			System.out.println("Fim teste");
		}
	}
}
