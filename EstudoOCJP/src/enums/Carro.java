package enums;

public enum Carro // Declaração do enum Carro
{
	// Declaração das constantes, DEVEM ser declaradas antes de construtores,
	// métodos e variáveis de instâncias
	GM("GM", "GMD3252"), // são implicitamente static e final,
	VW("VW", "OJF9329"), // quando se tem mais que uma, são separadas por
							// vírgula
	FERRARI("Ferrari", "KFM3499"), // por convensão os identificadores são todos
									// em maiúsculos pois são constantes
	FORD("Ford", "MDA9121");

	// os enum podem conter variáveis de instância, pois cada constante enum é
	// um objeto
	private String marca;
	private String placa;

	// os enum podem conter construtores, que serão usados pelas constantes,
	// porém não se pode dar new em um tipo enum
	Carro(String marca, String placa) {
		this.marca = marca;
		this.placa = placa;
	}

	// e podem conter métodos
	public String getCarro() {
		return marca + " -- " + placa;
	}

}
