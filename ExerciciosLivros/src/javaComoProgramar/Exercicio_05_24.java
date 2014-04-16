// Exercicio 5.24

public class Exercicio_05_24 {
	public static void main(String[] args) {
		String elem = "";
		int j = 4;
		
		for(int x = 1; x <= 5; x++)
		{
			elem = "";
			for(int i = 0; i < 5 - x; i++)
				elem += "-";
			
			for(int i = 0; i < 5 - j; i++)
				elem += "*";
			
			for(int i = 0; i < 5 - x; i++)
				elem+= "-";
				
			j -= 2;
			System.out.println(elem);
		}
		
		j = 0;
		
		for(int x = 5; x > 1; x--)
		{
			elem = "";
			for(int i = 6 - x; i > 0; i--)
				elem += "-";
			
			for(int i = 7 - j; i > 0; i--)
				elem += "*";
			
			for(int i = 6 - x; i > 0; i--)
				elem += "-";
				
			j += 2;
			System.out.println(elem);
		}
	}
}