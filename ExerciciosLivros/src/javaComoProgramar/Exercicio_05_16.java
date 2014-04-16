// Exercicio 5.16
import javax.swing.JOptionPane;

public class Exercicio_05_16 {
	public static void main(String[] args) {
		int[] num = new int[5];
		int i = 0, j;
		
		for(i = 0; i < num.length; i++)
			num[i] = Integer.parseInt(JOptionPane.showInputDialog(null, "Entre com um número"));
		
		
		for(i = 0; i < num.length; i++)
		{
			for(j = 0; j < num[i]; j++)
				System.out.print("*");
			
			System.out.println("\n");
		}

	}
}