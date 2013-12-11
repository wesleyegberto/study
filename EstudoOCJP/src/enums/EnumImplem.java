package enums;

public enum EnumImplem implements Humano {
	SERIE() {
		public final String serie = "3 serie";

		public String getSerie() {
			return serie;
		}

		@Override
		public void anda() {
			System.out.println(this.name() + " is walking.");
		}

		@Override
		public String getCon() {
			return "MYSL";
		}

		@Override
		public void fala() {
			System.out.println(this.name() + " is talking about its " + getSerie());
		}
	},
	CURSO() {
		private int speed = 10;

		@Override
		protected void anda() {
			System.out.println(this.name() + " is walking fast: " + speed++ + "km/h.");
		}

		@Override
		public String getCon() {
			return "SQL";
		}
	};

	protected abstract void anda();

	public abstract String getCon();

	@Override
	public void fala() {

	}

	public void fala(EnumImplem t) {
		System.out.println(this.name() + " says something.");
		System.out.println(t.getCon());
	}

	public static void main(String... args) {
		CURSO.anda();
		SERIE.fala();

	}
}

enum EnumFilho // extends EnumPai
{
	TESTE();

	private EnumFilho() {
	}

	private EnumFilho(int n) {
	}

	// Abaixo é private implicatamente
	EnumFilho(int n, int m) {
	}

	int myname;

	/*
	 * Sobreescrita do equals(Object) não é permitido pois na class Enum ela é
	 * final. Declaração do enum atual: Enum<EnumFilho>
	 */
	public boolean equals() {
		return true;
	}
}

enum EnumPai {
	TESTE() {
		@Override
		void doStuff() {
		}
	};

	abstract void doStuff();
}

interface Humano {
	void fala();
}
