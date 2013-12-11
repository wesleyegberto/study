package enums;

import java.util.EnumSet; // contém métodos

public class CarroTestDrive {
	public static void main(String[] args) {
		// para todo enum, o compilador cria um método estático chamado
		// values(), que retorna um array com as constantes
		// declarada no enum na mesma ordem de declaração
		for(Carro c : Carro.values()) {
			// quando se converte um tipo enum (que no caso é a variável c) em
			// uma String, o identificador da constante
			// é a representação em String
			System.out.println(c + "\t" + c.getCarro());
		}

		System.out.println("\n\n\n");

		// o método estático range(E e1, E e2) da classe EnumSet retorna um enum
		// com as constantes selecionada entre
		// os intervalos definidos pelos argumentos
		// o primeiro argumento é o inicio e o segundo é o fim, inclusive
		for(Carro c : EnumSet.range(Carro.GM, Carro.FERRARI)) {
			System.out.println(c + "\t" + c.getCarro());
		}

		System.out.println("\n\n\n");

	}
}
