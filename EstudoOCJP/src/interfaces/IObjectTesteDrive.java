package interfaces;

public class IObjectTesteDrive implements IObject {
	@Override
	public void getHash1() {
		// Aqui será usado o getHash()
		System.out.println(Math.random());
	}

	public void testeMetodoObject(IObject t) {
		t.getClass(); // o método getClass() é da classe Object, da qual todas
						// as classes herdão
		// e mesmo assim um tipo interface pode acessar, pois todo objeto
		// estendem a classe Object
	}

	public static void main(String... args) {
		IObjectTesteDrive t = new IObjectTesteDrive();

		t.getHash1();
	}

}
