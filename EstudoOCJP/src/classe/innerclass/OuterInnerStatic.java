package classe.innerclass;

public class OuterInnerStatic {
	static int x = 3;

	void print() {
		System.out.println("Outer");
		// Inner.this.println("Inner através do Outer");
	}

	static class Inner {
		void print() {
			System.out.println(x + "inner");
			// Outer.this.print();
		}
	}

	public static void main(String[] args) {
		OuterInnerStatic.Inner i = new OuterInnerStatic.Inner();

		i.print();
	}
}
