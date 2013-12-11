/**
 * @author Wesley Egberto de Brito Objetivo: Verificar a obriga��o de utilizar
 *         throws na classe filha quando a superclasse lan�a exceptions
 */

package classe;

class BaseExcecao {
	public BaseExcecao() throws Exception {
		throw new Exception("Erro no construtor BaseExcecao");
	}
}

class Nivel1 extends BaseExcecao {
	// � obrigado a declarar o lan�amento de exception
	public Nivel1() throws Exception {
		super();
	}

	static void doAlgo() {

	}
}

public class TesteCast {
	public static void main(String[] args) throws Exception {
		BaseExcecao be = new BaseExcecao();
		Nivel1 n1 = new Nivel1();

		BaseExcecao n2 = (BaseExcecao) n1;

	}
}
