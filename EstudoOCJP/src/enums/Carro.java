package enums;

public enum Carro // Declara��o do enum Carro
{
	// Declara��o das constantes, DEVEM ser declaradas antes de construtores,
	// m�todos e vari�veis de inst�ncias
	GM("GM", "GMD3252"), // s�o implicitamente static e final,
	VW("VW", "OJF9329"), // quando se tem mais que uma, s�o separadas por
							// v�rgula
	FERRARI("Ferrari", "KFM3499"), // por convens�o os identificadores s�o todos
									// em mai�sculos pois s�o constantes
	FORD("Ford", "MDA9121");

	// os enum podem conter vari�veis de inst�ncia, pois cada constante enum �
	// um objeto
	private String marca;
	private String placa;

	// os enum podem conter construtores, que ser�o usados pelas constantes,
	// por�m n�o se pode dar new em um tipo enum
	Carro(String marca, String placa) {
		this.marca = marca;
		this.placa = placa;
	}

	// e podem conter m�todos
	public String getCarro() {
		return marca + " -- " + placa;
	}

}
