package construtores;

public class SubclasseConstrutorTesteDrive {
	public static void main(String[] args) {

		// Ao instânciar o objeto, o construtor que imita o padrão irá chamar o
		// padrão da sua superclasse (Construtor);
		SubclasseConstrutor sc1 = new SubclasseConstrutor("Odair");

		System.out.println("--sc1\n" + sc1.getNomeERg() + "\n"); // Com o super
																	// da outra
																	// classe,
																	// inicializará
																	// os
																	// valores
	}

}
