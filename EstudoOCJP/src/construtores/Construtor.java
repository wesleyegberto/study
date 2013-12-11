package construtores;

public class Construtor {
	private String nome;
	private String rg;

	public String getNomeERg() {
		return "Nome:\t" + this.nome + "\nRG:\t" + rg;
	}

	// Os construtores são criados com sobrecarga, exigindo a assinatura
	// diferente uma da outra

	public Construtor() {
	} // Construtor padrão, chama implicitamente o super();

	// Com a declaração de um construtor, foi necessário criar outro com os
	// aspectos do padrão
	public Construtor(String nome) { // Construtor inicializa o nome, porém o rg
										// também inicializado com valor padrão
		// é necessário usar o this para usar a variável de instância porque o
		// compilador da preferência à variável
		// local, isto é chamado de sombreamento
		this.nome = nome;
	}

	/*
	 * Nesse caso não funciona, pois o tipo String equivale ao construtor
	 * anterior e não efetua a sobrecarga public Teste(String rg) { this.rg =
	 * rg; }
	 */

	// Chamando outro construtor
	public Construtor(String nome, String rg) {
		// this(); // Não pode chamar dois construtores, pois irá pedir que
		// fiquem na primeira linha
		this(nome); // é chamado o construtor que atende o argumento

		this.rg = rg;
	}
}
