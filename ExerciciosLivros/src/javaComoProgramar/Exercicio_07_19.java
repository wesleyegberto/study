// Exercício 7.19
// Reservas de assentos de avião
import java.util.Scanner;

public class Exercicio_07_19
{
	static boolean[] assentos = new boolean[10];
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Entre com a sua classe: \n1 - First \n2 - Economy");
		int i = sc.nextInt();
		
		if(i == 1)
		{
			for(int j = 0; j < 5; j++)
				if(assentos[j] == false)
				{
					assentos[j] = true;
					System.out.println("\nSeu assento e " + (j + 1));
					break;
				}				
		}
		else if(i == 2)
		{
			for(int j = 5; j < 10; j++)
				if(assentos[j] == false)
				{
					assentos[j] = true;
					System.out.println("\nSeu assento e " + (j + 1));
					break;
				}
		}
	}
}