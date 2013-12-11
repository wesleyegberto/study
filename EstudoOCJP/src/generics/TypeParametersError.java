package generics;

public class TypeParametersError {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		NodeInteger myNode = new NodeInteger();
		myNode.set(10);

		// Por n�o passar o Type Argument o compilador com Type Erasure
		// substituir� as ocorr�ncias de T por Object
		Node n = myNode;

		// Erro em tempo de execu��o pois est� com inst�ncia do NodeInteger que
		// recebe um Integer
		// e em tempo de compila��o recebe um Object.
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
	 * interface gen�rica, para preservar o polimorfismo, o compilador cria um
	 * m�todo sint�tico (chamado de Brigde method) que faz com que o m�todo da
	 * superclasse ou interface seja realmente sobreescrito. Ex.: No caso o
	 * set(T) n�o � sobreescrito por set(Integer) e sim sobrecarregado, pois as
	 * assinaturas s�o diferentes. Ent�o o compilador cria o m�todo abaixo para
	 * sobreescrever o m�todo gen�rico corretamente. public void setData(Object
	 * data) { setData((Integer) data); }
	 */
}
