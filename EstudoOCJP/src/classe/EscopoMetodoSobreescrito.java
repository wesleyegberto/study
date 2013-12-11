package classe;

public class EscopoMetodoSobreescrito {
	public static void main(String[] args) {
		/*
		 * A Saida será "0\n3" pq o método printThree() sobreescrito em TestM se
		 * torna o escopo da sub e superclasse.
		 */
		TestM t = new TestM();
		t.printThree();
	}
}

class SuperM {
	SuperM() {
		this.printThree();
	}

	void printThree() {
		System.out.println("three");
	}
}

class TestM extends SuperM {
	int three = (int) Math.PI; // That is 3 after superM() was invoked, before
								// this is 0.

	/*
	 * Sobreescreve o método, tornando-se o escopo para a superclasse.
	 */
	void printThree() {
		System.out.println(three);
	}
}
