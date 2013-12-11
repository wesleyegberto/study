package statics;

public class MetodoStaticTesteDrive extends MetodoStatic {
	// /Não ocorre sobreescrita.
	// @Override
	public static void metodoStatico() {
		System.out.println(x + "");
	}

	public static void main(String[] args) {
		MetodoStaticTesteDrive a = new MetodoStaticTesteDrive();

		a.metodoStatico();

	}
}
