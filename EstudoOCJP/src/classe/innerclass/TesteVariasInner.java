package classe.innerclass;

class A {
	void m() {
		System.out.println("outer");
	}
}

public class TesteVariasInner {
	public static void main(String[] args) {
		new TesteVariasInner().go();
	}

	void go() {
		new A().m(); // Aqui ser� chamada a classe com middle, pois a inner
						// ainda n�o existe no escopo

		class A // Classe interna do m�todo
		{
			void m() {
				System.out.println("inner");
			}
		}

		new A().m(); // Aqui ser� chamada a classe com inner, pois j� existe no
						// escopo

	}

	private class B {}
	private enum C {}
	private interface D {}
	private abstract class E {}
	
	class A // Classe interna de TesteVariasInner
	{
		void m() {
			System.out.println("middle");
		}
	}
}
