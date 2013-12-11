package interface_abstractclass;

public class Macaco extends Mamifero {
	public void setNomeAnimal(String nome) {
		nomeAnimal = nome;
	}

	public String getNomeAnimal() {
		return nomeAnimal;
	}

	public void andar() {
		System.out.print("Andando...");
	}

	public void pular() {
		System.out.print("Pulando...");
	}
}
