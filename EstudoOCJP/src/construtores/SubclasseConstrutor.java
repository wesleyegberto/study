package construtores;

class SubclasseConstrutor extends Construtor {

	// Quando o construtor da classe filha não chamado nenhum construtor
	// explicitamnete.
	// É chamado o construtor da superclasse implicitamente
	public SubclasseConstrutor() {
		// super(); A chama do super() é feita implicitamente como se houvesse
		// esse código aqui.
		// Caso a superclasse não possua o construtor padrão ou construtor sem
		// parametros, é dado erro de compilação
		// e necessita chamar outro construtor explicitamente.

	}

	public SubclasseConstrutor(String nome) {
		super(nome); // Chama o construtor da superclasse (Construtor) que
						// inicializa o nome com o argumento
	}

}
