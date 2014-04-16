import java.util.Scanner;

public class Exercicio_04_30 {
	public static void main(String[] args) {
		int num;
		int x1, x2, x3, x4, x5, y1, y2, y3, y4, y5;
		boolean teste = false;
		
		Scanner scan = new Scanner(System.in);

		while(teste == false)
		{
			System.out.println("Entre com um número de 5 digitos: ");
			num = scan.nextInt();
			
			y1 = num % 10000;
			x1 = (num - y1) / 10000;
			num -= (num - y1);
		
			y2 = num % 1000;
			x2 = (num - y2) / 1000;
			num -= (num - y2);
		
			y3 = num % 100;
			x3 = (num - y3) / 100;
			num -= (num - y3);
		
			y4 = num % 10;
			x4 = (num - y4) / 10;
			num -= (num - y4);
		
			x5 = num;
		
			if (x1 == x5 && x2 == x4)
			{
				System.out.println("\nE um polindromo!\n");
				teste = true;
			}
			else
				System.out.println("\nNao e um polindromo!\n");
		}
	}
}