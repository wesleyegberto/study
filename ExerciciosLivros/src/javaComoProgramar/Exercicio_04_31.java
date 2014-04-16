import java.util.Scanner;

public class Exercicio_04_31 {
	public static void main(String[] args) {
		int num, numOr;
		int d1;
		int pot = 1;
		int resul = 0;
		
		Scanner scan = new Scanner(System.in);

		System.out.print("Entre com um numero binario: ");
		num = scan.nextInt();
		numOr = num;
		
		//System.out.println("Numero" + "\t-->\t" + "Digito" + "\t*\t" + "Pot");


		while(num > 0) {
			d1 = num % 10;
			resul += d1 * pot;
			//System.out.println(num + "\t-->\t" + d1 + "\t*\t" + pot);
	
			pot *= 2;
			num /= 10;
		}
		
		System.out.println("O binario " + numOr + " em decimal e " + resul);
	}
}