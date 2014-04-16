// Exercicio 5.15

public class Exercicio_05_15 {
	public static void main(String[] args) {
		int i = 0, j = 0;
		String elem = "";
		
		for(i = 1; i <= 10; i++)
		{
			for(j = 1; j <= i; j++)
				System.out.printf("%s", "*");
				
			System.out.println();
		}
		
		System.out.println();
		System.out.println();
		
		for(i = 10; i > 0; i--)
		{
			for(j = 1; j <= i; j++)
				System.out.printf("%s", "*");
				
			System.out.println();
		}
		
		System.out.println();
		System.out.println();
		
		for(i = 10; i > 0; i--)
		{
			elem = "";
			for(j = 1; j <= i; j++)
				elem += "*";
			
			System.out.printf("%10s\n", elem);
				
		}
		
		System.out.println();
		System.out.println();
		
		for(i = 1; i <= 10; i++)
		{
			elem = "";
			for(j = 1; j <= i; j++)
				elem += "*";
			
			System.out.printf("%10s\n", elem);
				
		}
	}
}