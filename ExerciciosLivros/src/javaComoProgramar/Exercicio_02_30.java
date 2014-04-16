import java.util.Scanner;

public class Exercicio_02_30 {
	public static void main(String[] args) {
		int num;
		int x1, x2, x3, x4, x5, y1, y2, y3, y4, y5;
		
		Scanner scan = new Scanner(System.in);
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
		
		System.out.println(x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5);
	}
}