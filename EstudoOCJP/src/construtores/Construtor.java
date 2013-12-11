package construtores;

public class Construtor {
	private String nome;
	private String rg;

	public String getNomeERg() {
		return "Nome:\t" + this.nome + "\nRG:\t" + rg;
	}

	// Os construtores s�o criados com sobrecarga, exigindo a assinatura
	// diferente uma da outra

	public Construtor() {
	} // Construtor padr�o, chama implicitamente o super();

	// Com a declara��o de um construtor, foi necess�rio criar outro com os
	// aspectos do padr�o
	public Construtor(String nome) { // Construtor inicializa o nome, por�m o rg
										// tamb�m inicializado com valor padr�o
		// � necess�rio usar o this para usar a vari�vel de inst�ncia porque o
		// compilador da prefer�ncia � vari�vel
		// local, isto � chamado de sombreamento
		this.nome = nome;
	}

	/*
	 * Nesse caso n�o funciona, pois o tipo String equivale ao construtor
	 * anterior e n�o efetua a sobrecarga public Teste(String rg) { this.rg =
	 * rg; }
	 */

	// Chamando outro construtor
	public Construtor(String nome, String rg) {
		// this(); // N�o pode chamar dois construtores, pois ir� pedir que
		// fiquem na primeira linha
		this(nome); // � chamado o construtor que atende o argumento

		this.rg = rg;
	}
}
