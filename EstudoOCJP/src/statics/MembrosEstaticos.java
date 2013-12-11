// Utiliza��o de vari�veis est�ticas e bloco de inicializa��o est�tico
package statics;

public class MembrosEstaticos {
	static int incremento; // vari�vel est�tica, � inicializada quando a classe
							// � carregada, deve ser inicializada antes pois �
							// final

	// bloco de inicializa��o est�tico, � a primeira coisa que � executado
	// quando a classe � carregada na mem�ria pela JVM
	static {
		System.out.println("Bloco de inicializacao estatico executado!\n");
	}

	// bloco de inicializa��o, � executado toda vez que um objeto � inst�nciado,
	// e antes do construtor
	{
		System.out.println("Bloco de inicializacao executado!\n");
	}

	public MembrosEstaticos() {
		System.out.println("Construtor executado!\n");
	}
}
