package classe.innerclass;

public class InnerClassCreate {
	static String a = "a";
	String b = "b";
	final String e = "e";

	static class C {
		public C() {
			System.out.println("Class C can modify static a");
			a = "a was changed by class C";
		}
	}

	class D {
		public D() {
			System.out.println("Class D can modify b");
			b = "b was changed by class D";
		}
	}

	public static void main(String... args) {
		String c = "b";
		final String d = "d";

		new C();

		MyAbstract m;
		m = new MyAbstract(c, d) {
			@Override
			public String doStuff() {
				/*
				 * Métodos internos só podem acessar variável final do método
				 * externo. return new String(b);
				 */
				System.out.println("Inner class in main() cannot access c because it is not final");
				return new String("Inner class in main() can only access final " + d);
			}
		};
		System.out.println(m.doStuff());

		InnerClassCreate in = new InnerClassCreate();
		InnerClassCreate.D dasd = in.new D();
		System.out.println(in.b);
	}
}

abstract class MyAbstract {
	public MyAbstract(String s, String t) {

	}

	public abstract String doStuff();
}
