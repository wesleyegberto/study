package construtores;

public class SubclasseConstrutorTesteDrive {
	public static void main(String[] args) {

		// Ao inst�nciar o objeto, o construtor que imita o padr�o ir� chamar o
		// padr�o da sua superclasse (Construtor);
		SubclasseConstrutor sc1 = new SubclasseConstrutor("Odair");

		System.out.println("--sc1\n" + sc1.getNomeERg() + "\n"); // Com o super
																	// da outra
																	// classe,
																	// inicializar�
																	// os
																	// valores
	}

}
