package classe.innerclass;

public class Referencias {
	String nome = "Outter";

	private class Inner1 {
		String nome1 = "Inner1";
		String nome2 = nome;
		String nome3 = Referencias.this.nome;
	}

	public static void main(String[] args) {
		Referencias ref = new Referencias();
		Inner1 in = ref.new Inner1();

		System.out.println(in.nome1);
		System.out.println(in.nome2);
		System.out.println(in.nome3);
	}
}
