package construtores;

public class ConstrutorFinalStatic {
	// final n�o pode
	// nem static
	// private pode, por�m n�o haver� heran�a da classe, pois a subclasse n�o
	// enxergar� o construtor, imperindo a inst�ncia��o
	ConstrutorFinalStatic() {
		System.out.println("Este e um construtor final.");
	}
}
