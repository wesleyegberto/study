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
		 * catch(java.io.IOException e) //Erro: Nunca ser� lan�ado essa
		 * exception { e.printStackTrace(); } catch(java.io.Exception e) //Erro:
		 * Nunca ser� lan�ado essa exception { e.printStackTrace(); }
		 */
	}
}

// O m�todo sobrecarregado n�o declara exception, ent�o pressup�e que ser�
// tratado caso lance
public class Test extends T1 {
	void method() {
	}
}
