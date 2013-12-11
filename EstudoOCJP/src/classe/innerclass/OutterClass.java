package classe.innerclass;

public class OutterClass {
	private int x;
	private String texto = "TextoOutterClass";

	public void print() {
		System.out.println(texto);
	}

	public class InnerClass {
		private String b = "TextoInnerClass";

		public void imprimir() {
			System.out.println(this.b + " - " + x);
		}
	}

}

class TesteT {
	public static void main(String[] args) {
		OutterClass o = new OutterClass();

		o.print();

		OutterClass.InnerClass i = o.new InnerClass();

		i.imprimir();
	}
}
