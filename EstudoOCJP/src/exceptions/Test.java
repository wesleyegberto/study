package exceptions;

class T1 {
	void method() throws java.io.IOException {
	}

	public void main(String args[]) {
		Test t = new Test();
		// try {
		t.method();
		// }
		/*
		 * catch(java.io.IOException e) //Erro: Nunca será lançado essa
		 * exception { e.printStackTrace(); } catch(java.io.Exception e) //Erro:
		 * Nunca será lançado essa exception { e.printStackTrace(); }
		 */
	}
}

// O método sobrecarregado não declara exception, então pressupõe que será
// tratado caso lance
public class Test extends T1 {
	void method() {
	}
}
