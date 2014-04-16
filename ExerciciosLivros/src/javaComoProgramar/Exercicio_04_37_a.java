import java.util.Scanner;

public class Exercicio_04_37_a {
	public static void main(String[] args) {
		int num;
		int fat = 1;
		
		Scanner scan = new Scanner(System.in);

		System.out.print("Entre com um numero para o fatorial: ");
		num = scan.nextInt();
		
		for(int i = num; i > 0; i--) {
			fat *= i;
		}
		
		System.out.println(num + "! = " + fat);
	}
}