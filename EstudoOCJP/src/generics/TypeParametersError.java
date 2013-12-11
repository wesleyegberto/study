package generics;

public class TypeParametersError {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		NodeInteger myNode = new NodeInteger();
		myNode.set(10);

		// Por não passar o Type Argument o compilador com Type Erasure
		// substituirá as ocorrências de T por Object
		Node n = myNode;

		// Erro em tempo de execução pois está com instância do NodeInteger que
		// recebe um Integer
		// e em tempo de compilação recebe um Object.
		n.set("EPA");
		Integer i = myNode.get();
		System.out.println("What I have: " + i);

	}
}

class Node<T> {
	private T t;

	public T get() {
		return t;
	}

	public void set(T t) {
		System.out.println("Set in Node");
		this.t = t;
	}
}

class NodeInteger extends Node<Integer> {
	public Integer get() {
		return super.get();
	}

	public void set(Integer i) {
		System.out.println("Set in NodeInteger");
		super.set(i);
	}

	/**
	 * Bridge Method Quando uma classe estende outra classe ou implementa uma
	 * interface genérica, para preservar o polimorfismo, o compilador cria um
	 * método sintético (chamado de Brigde method) que faz com que o método da
	 * superclasse ou interface seja realmente sobreescrito. Ex.: No caso o
	 * set(T) não é sobreescrito por set(Integer) e sim sobrecarregado, pois as
	 * assinaturas são diferentes. Então o compilador cria o método abaixo para
	 * sobreescrever o método genérico corretamente. public void setData(Object
	 * data) { setData((Integer) data); }
	 */
}
