package polimorfismo;

public class MatrizPolimorfica {
	public static void main(String... args) {
		Animal[] a = new Animal[3];

		a[0] = new Animal();
		a[1] = new Coelho();
		a[2] = new Aguia();

		for(Animal aux : a) {
			System.out.println("\n\n" + aux.getClass().getName());
			aux.andar(); // Não tem problema pois todos possuem esse método,
							// porém serão feito de forma diferente.

			aux.barulho(); // Idem.

			// O compilador não permitirá. Pois a váriavel 'a' só conhece os
			// membros de sua classe e da superclasse (se houver)
			// aux.carinho(); // Pam. apenas os objetos do tipo Coelho têm esse
			// método

			// aux.voarRapido();

		}

		// Para executar os métodos específico de cada classe efetuarei um
		// casting

		Coelho c = (Coelho) a[1]; // Teria problema sem o casting (Coelho). Mas
									// você sabe que no 'a[1]' têm uma instância
									// de Coelho

		Aguia ag = (Aguia) a[2]; // Idem.

		c.carinho();

		ag.voarRapido();
	}
}
