/* Passos da criptografia
	-Substituir cada digito pelo resto da divisão por 10 da soma do digito a 7 (d1 + 7 % 10)
	-E troque o primeiro com o terceiro e o segundo com o quarto
*/
import java.util.Scanner;

public class Exercicio_04_38 {
	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		int opc = 0, num;
		String result = "";
		
		while(opc != 1 && opc != 2 && opc != 3) {
			System.out.println("Entre com uma opcao: \n\t1 --> Criptografar \n\t2 --> Descriptografar \n\t3 --> Sair\n");
			opc = scan.nextInt();
		}
		
		if(opc == 3)
			System.exit(0);
			
		System.out.print("\n\nEntre com o numero a ser criptografado: ");
		num = scan.nextInt();
		
		if(opc == 1)
			result = num + " criptografado --> " + criptografar(num);
		else if(opc == 2)
			result = num + " descriptografado --> " + descriptografar(num);
		
		System.out.println(result);
	}
	
	public static String criptografar(int num) {
	int d1, d2, d3, d4, y1, y2, y3, y4, x1, x2, x3, x4;
		
		y1 = num % 1000;
		d1 = (num - y1) / 1000;
		num -= (num - y1);
		
		y2 = num % 100;
		d2 = (num - y2) / 100;
		num -= (num - y2);
		
		y3 = num % 10;
		d3 = (num - y3) / 10;
		num -= (num - y3);
		
		y4 = num % 1;
		d4 = (num - y4) / 1;
		num -= (num - y4);
		
		x1 = (d1 + 7) % 10;
		x2 = (d2 + 7) % 10;
		x3 = (d3 + 7) % 10;
		x4 = (d4 + 7) % 10;
		
		String cript = String.valueOf(x3) + String.valueOf(x4) + String.valueOf(x1) + String.valueOf(x2);
		return cript;
	}

	public static String descriptografar(int num) {
	int d1, d2, d3, d4, y1, y2, y3, y4;
		
		y1 = num % 1000;
		d1 = (num - y1) / 1000;
		num -= (num - y1);
		
		y2 = num % 100;
		d2 = (num - y2) / 100;
		num -= (num - y2);
		
		y3 = num % 10;
		d3 = (num - y3) / 10;
		num -= (num - y3);
		
		y4 = num % 1;
		d4 = (num - y4) / 1;
		num -= (num - y4);
		
		if(d1 > 7)
			d1 -= 7;
		else
			d1 += 3;
			
		if(d2 > 7)
			d2 -= 7;
		else
			d2 += 3;
			
		if(d3 > 7)
			d3 -= 7;
		else
			d3 += 3;
			
		if(d4 > 7)
			d4 -= 7;
		else
			d4 += 3;
		
		String cript = String.valueOf(d3) + String.valueOf(d4) + String.valueOf(d1) + String.valueOf(d2);
		return cript;
	}
}