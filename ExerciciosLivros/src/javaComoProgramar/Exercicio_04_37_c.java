import java.util.Scanner;

public class Exercicio_04_37_c {
	public static void main(String[] args) {
		int num;
		double e = 1;
		
		Scanner scan = new Scanner(System.in);

		System.out.print("Entre com a quantidade de termos: ");
		num = scan.nextInt();
		
		for(int i = 1; i <= num; i++) {
			e += (double) (num * i) / (fatorial(i));
		}
		
		System.out.println("e = " + e);
	}
	
	public static int fatorial(int num) {
		int fat = 1;
			
		if (num == 0)
			return 1;
			
		for(int i = num; i > 0; i--) {
			fat *= i;
		}
		
		return fat;
	}
}