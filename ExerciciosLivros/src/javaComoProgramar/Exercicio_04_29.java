import java.util.Scanner;

public class Exercicio_04_29 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int x = 0;
		
		while(x < 1 || x > 20) {
			System.out.print("Entre com a largura do quadrado: ");
			x = sc.nextInt();
		}
		
		for(int j = 0; j < x; j++) {
			System.out.print("*");
		}
		
		System.out.println();
		
		for(int i = 1; i < x - 1; i++) {
			for(int j = 0; j < x; j++) {
				if(j == 0)
					System.out.print("*");
				else if(j == (x - 1))
					System.out.println("*");
				else
					System.out.print(" ");
			}
		}
		
		for(int j = 0; j < x; j++) {
			System.out.print("*");
		}
	}
}