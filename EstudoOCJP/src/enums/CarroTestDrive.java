package enums;

import java.util.EnumSet; // cont�m m�todos

public class CarroTestDrive {
	public static void main(String[] args) {
		// para todo enum, o compilador cria um m�todo est�tico chamado
		// values(), que retorna um array com as constantes
		// declarada no enum na mesma ordem de declara��o
		for(Carro c : Carro.values()) {
			// quando se converte um tipo enum (que no caso � a vari�vel c) em
			// uma String, o identificador da constante
			// � a representa��o em String
			System.out.println(c + "\t" + c.getCarro());
		}

		System.out.println("\n\n\n");

		// o m�todo est�tico range(E e1, E e2) da classe EnumSet retorna um enum
		// com as constantes selecionada entre
		// os intervalos definidos pelos argumentos
		// o primeiro argumento � o inicio e o segundo � o fim, inclusive
		for(Carro c : EnumSet.range(Carro.GM, Carro.FERRARI)) {
			System.out.println(c + "\t" + c.getCarro());
		}

		System.out.println("\n\n\n");

	}
}
