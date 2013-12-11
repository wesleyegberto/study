package classe.innerclass;

public class Outer {
	int x = 3;

	void print() {
		System.out.println("Outer");
		// Inner.this.println("Inner através do Outer");
	}

	public class Inner {
		void print() {
			System.out.println("inner");
			// Outer.this.print();
		}
	}

	public static void main(String[] args) {
		Outer o = new Outer();

		Inner i = o.new Inner();

		i.print();
	}
}
