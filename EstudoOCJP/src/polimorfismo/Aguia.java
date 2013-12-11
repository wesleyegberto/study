package polimorfismo;

class Aguia extends Animal {
	@Override
	public void andar() {
		System.out.println("Estou voando!");
	}

	@Override
	public void barulho() {
		System.out.println("Estou piando!");
	}

	// M�todo �nico na hierarquia
	public void voarRapido() {
		System.out.println("Estou voando rapido.");
	}
}
