// Exercicio 5.22

public class Exercicio_05_22 {
	public static void main(String[] args) {
		int i = 0, j = 0;
		String elem = "";
		
		for(i = 1; i <= 10; i++)
		{
			elem = "";
			for(j = 1; j <= i; j++)
				elem += "*";
				
			System.out.printf("%-10s ", elem);
			
			elem = "";
			for(j = 10; j >= i; j--)
				elem += "*";
				
			System.out.printf("%-10s\t", elem);
			System.out.printf("%10s ", elem);
			
			elem = "";
			for(j = 1; j <= i; j++)
				elem += "*";
				
			System.out.printf("%10s\t", elem);
			
			System.out.println();
		}
		
	}
}