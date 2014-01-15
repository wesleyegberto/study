package javaComoProgramar.cap22;

public class Exercicio22_17 {
	public static void main(String[] args) {
		Tree<String> t = new Tree<String>();

		String frase = javax.swing.JOptionPane.showInputDialog("Entre com uma frase.");
		String[] palavras = frase.split(" ");

		for(String word : palavras) {
			t.insertNode(word);
		}

		System.out.println("Frase: " + frase);
		t.inorderTraversal();
		System.out.println();
		t.preorderTraversal();
		System.out.println();
		t.postorderTraversal();
		System.out.println();
	}
}
