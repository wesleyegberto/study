package classe.innerclass;

public class TesteInnerMethod {
	int x = 2;

	public void innerTeste() {
		final int y = 9;
		int z = 3;

		class Inner {
			private void print() {
				System.out.println("Inner in method " + TesteInnerMethod.this.x);
				// System.out.println(z); //Erro: pois no final do método z
				// sairá de escopo,
				// e a instância de Inner ainda poderá estar viva no heap
				System.out.println(y); // Permitido, pois a variável z é final e
										// o compilador vai cuidar
										// disso com antecedência
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
