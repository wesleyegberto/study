package polimorfismo;

public class MatrizPolimorfica {
	public static void main(String... args) {
		Animal[] a = new Animal[3];

		a[0] = new Animal();
		a[1] = new Coelho();
		a[2] = new Aguia();

		for(Animal aux : a) {
			System.out.println("\n\n" + aux.getClass().getName());
			aux.andar(); // N�o tem problema pois todos possuem esse m�todo,
							// por�m ser�o feito de forma diferente.

			aux.barulho(); // Idem.

			// O compilador n�o permitir�. Pois a v�riavel 'a' s� conhece os
			// membros de sua classe e da superclasse (se houver)
			// aux.carinho(); // Pam. apenas os objetos do tipo Coelho t�m esse
			// m�todo

			// aux.voarRapido();

		}

		// Para executar os m�todos espec�fico de cada classe efetuarei um
		// casting

		Coelho c = (Coelho) a[1]; // Teria problema sem o casting (Coelho). Mas
									// voc� sabe que no 'a[1]' t�m uma inst�ncia
									// de Coelho

		Aguia ag = (Aguia) a[2]; // Idem.

		c.carinho();

		ag.voarRapido();
	}
}
