package classe;

public class Teste0 {
	Teste0() {
	}

	class Teste {
		Teste() {
			System.out.println("Teste");
		}
	}

	public class Teste2 {
		class Teste {
		}

		public void teste() {
			class Teste {
				Teste() {
					System.out.println("inner Teste");
				}
			}

			Teste0.Teste t = new Teste0().new Teste();

		}

	}

	class Teste3 {
	}

	void go() {
		new Teste2().teste();
	}

	public static void main(String[] args) {
		Teste0 t0 = new Teste0();
		t0.go();

	}
}
