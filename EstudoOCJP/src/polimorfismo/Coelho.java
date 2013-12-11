package polimorfismo;

class Coelho extends Animal {
	@Override
	public void andar() {
		System.out.println("Estou pulando!");
	}

	@Override
	public void barulho() {
		System.out.println("Estou fazendo barulhinho!");
	}

	// Método único na hierarquia
	public void carinho() {
		System.out.println("Estou fazendo carinho.");
	}
}
