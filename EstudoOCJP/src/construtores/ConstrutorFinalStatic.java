package construtores;

public class ConstrutorFinalStatic {
	// final não pode
	// nem static
	// private pode, porém não haverá herança da classe, pois a subclasse não
	// enxergará o construtor, imperindo a instânciação
	ConstrutorFinalStatic() {
		System.out.println("Este e um construtor final.");
	}
}
