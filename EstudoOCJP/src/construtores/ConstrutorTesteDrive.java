package construtores;

public class ConstrutorTesteDrive {
	public static void main(String[] args) {

		// Construtor c1 = new Construtor();

		// System.out.println("--c1\n" + c1.getNomeERg() + "\n\n");

		Construtor c2 = new Construtor("Odair");

		System.out.println("--c2\n" + c2.getNomeERg() + "\n\n");

		Construtor c3 = new Construtor("Odair", "123456");

		System.out.println("--c3\n" + c3.getNomeERg() + "\n\n");

	}

}
