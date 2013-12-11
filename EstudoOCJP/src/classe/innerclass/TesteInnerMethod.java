package classe.innerclass;

public class TesteInnerMethod {
	int x = 2;

	public void innerTeste() {
		final int y = 9;
		int z = 3;

		class Inner {
			private void print() {
				System.out.println("Inner in method " + TesteInnerMethod.this.x);
				// System.out.println(z); //Erro: pois no final do m�todo z
				// sair� de escopo,
				// e a inst�ncia de Inner ainda poder� estar viva no heap
				System.out.println(y); // Permitido, pois a vari�vel z � final e
										// o compilador vai cuidar
										// disso com anteced�ncia
			}

			static final int x = 12;

			String y = "Teste";
		}

		Inner i = new Inner();

		i.print();

	}
}

class Teste {
	public static void main(String args[]) {
		TesteInnerMethod t = new TesteInnerMethod();

		t.innerTeste();
	}
}
