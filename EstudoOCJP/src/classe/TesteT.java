package classe;

/* Modificadores de acesso em classe e interface interna */
class InnerAccess {
	public class Inner1 {
	}

	protected static class Inner2 {
	}

	private class Inner3 extends Inner1 {
	}

	class Inner4 {
	}

	public interface Inter1 {
	}

	protected interface Inter2 {
	}

	private interface Inter3 {
	}

	interface Inter4 {
	}

	Inner3 get() {
		return new Inner3();
	}
}

public class TesteT {
	public static void main(String[] args) {
		InnerAccess ia = new InnerAccess();
		InnerAccess.Inner1 i1;

		i1 = ia.get();

	}
}
