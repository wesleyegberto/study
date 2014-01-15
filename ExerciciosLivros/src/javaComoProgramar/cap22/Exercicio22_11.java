package javaComoProgramar.cap22;

public class Exercicio22_11 {
	public static void main(String[] args) {
		List<Character> list = new List<Character>();
		String text = javax.swing.JOptionPane.showInputDialog("Entre com uma frase:");
		char[] letras = text.toCharArray();
		int medio = 0;
		boolean isPalindromo = true;
		int tamanho = 0;

		for(int i = 0; i < letras.length; i++) {
			if(Character.isLetterOrDigit(letras[i])) {
				list.insertAtBack(Character.toLowerCase(letras[i]));
				tamanho++;
			}
		}

		if(letras.length % 2 == 0)
			medio = tamanho / 2;
		else
			medio = (tamanho - 1) / 2;

		for(int i = 0; i < medio; i++)
			if(!list.removeFromFront().equals(list.removeFromBack())) {
				isPalindromo = false;
			}

		if(isPalindromo)
			System.out.println("É um palíndromo.");
		else
			System.out.println("Não é um palíndromo.");
	}
}
