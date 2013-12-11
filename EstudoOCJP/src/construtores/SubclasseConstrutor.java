package construtores;

class SubclasseConstrutor extends Construtor {

	// Quando o construtor da classe filha n�o chamado nenhum construtor
	// explicitamnete.
	// � chamado o construtor da superclasse implicitamente
	public SubclasseConstrutor() {
		// super(); A chama do super() � feita implicitamente como se houvesse
		// esse c�digo aqui.
		// Caso a superclasse n�o possua o construtor padr�o ou construtor sem
		// parametros, � dado erro de compila��o
		// e necessita chamar outro construtor explicitamente.

	}

	public SubclasseConstrutor(String nome) {
		super(nome); // Chama o construtor da superclasse (Construtor) que
						// inicializa o nome com o argumento
	}

}
