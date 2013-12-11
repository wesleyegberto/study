package classe.innerclass;

public class Teste4 {
	public void teste(Object t) {

	}

	public static void main(String[] args) {
		Teste4 t = new Teste4();

		t.teste(new Object() {
			int x = 3;
		});

	}
}
