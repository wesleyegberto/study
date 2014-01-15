package javaComoProgramar.cap22;

public class Exercicio22_10 {
	public static void main(String[] args) {
		StackComposition<Character> stack = new StackComposition<Character>();
		String text = javax.swing.JOptionPane.showInputDialog("Entre com uma frase:");
		char[] letras = text.toCharArray();

		for(int i = 0; i < letras.length; i++)
			stack.push(letras[i]);

		System.out.println("Frase invertida:");
		while(!stack.isEmpty())
			System.out.print(stack.pop());
	}
}
