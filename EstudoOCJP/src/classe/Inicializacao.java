package classe;

public class Inicializacao {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		System.out.print("One o: ");
		// Não inicializará a One pois ela não foi usada ativamente.
		One o = null;

		System.out.printf("%nfinal Super.base: ");
		// Nessa chamada não é inicializada pois é o base é static final e isso
		// não inicializa
		// a classe ou superclasses
		int id = Super.base;

		System.out.printf("%nSuper.id: ");
		// Nessa chamada apenas a classe Super será inicializada pois o id é
		// estático e isso inicializa
		// apenas a classe em que foi declarada.
		id = Super.id;

		System.out.printf("%nnew Two(): ");
		// Inicializará Two e a Super (caso não tenham sido inicializada).
		Two t = new Two();

		System.out.printf("%no == t: ");
		// Aqui a classe onte ainda não foi usada ativamente (apenas uma
		// referência dela).
		System.out.println((Object) o == (Object) t);

	}
}

class Super {
	static final int base = 3;
	static int id = 30;
	static {
		System.out.print("Super ");
	}
}

class One {
	static int id = 30;
	static {
		System.out.print("One ");
	}
}

class Two extends Super {
	static {
		System.out.print("Two ");
	}
}
