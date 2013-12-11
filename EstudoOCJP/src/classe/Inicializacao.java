package classe;

public class Inicializacao {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		System.out.print("One o: ");
		// N�o inicializar� a One pois ela n�o foi usada ativamente.
		One o = null;

		System.out.printf("%nfinal Super.base: ");
		// Nessa chamada n�o � inicializada pois � o base � static final e isso
		// n�o inicializa
		// a classe ou superclasses
		int id = Super.base;

		System.out.printf("%nSuper.id: ");
		// Nessa chamada apenas a classe Super ser� inicializada pois o id �
		// est�tico e isso inicializa
		// apenas a classe em que foi declarada.
		id = Super.id;

		System.out.printf("%nnew Two(): ");
		// Inicializar� Two e a Super (caso n�o tenham sido inicializada).
		Two t = new Two();

		System.out.printf("%no == t: ");
		// Aqui a classe onte ainda n�o foi usada ativamente (apenas uma
		// refer�ncia dela).
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
