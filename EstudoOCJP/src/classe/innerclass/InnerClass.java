package classe.innerclass;

public class InnerClass {
	public class Teste {
		Teste(int x) {
		}
	}

	Teste get(final int j) {
		return new Teste(j) /* Retorna uma classe anônima que estende a Teste */
		{
			int f = j;
		};
	}

	static class Teste1 extends InnerClass {
	}

	public static void main(String[] args) {
		InnerClass ic = new InnerClass();

		Teste t = ic.get(3);
	}
}
