//Exercício 7.21
//Gráfico de Tarturuga
import java.util.Scanner;

public class Exercicio_07_21
{
	int[][] floor = new int[20][20];
	int x;
	int y;
	int direcao = 1; // 0 -> CIMA, 1 -> DIREITA, 2 -> BAIXO, 3 -> ESQUERDA
	int caneta;
	Scanner sc = new Scanner(System.in);
	
	public void andar(int pos)
	{
		int j = 0;
		int y = this.y;
		int x = this.x;
		
		switch(direcao)
		{
			case 0:
				for(y = this.y; y <= 19 && j < pos; y++, j++)
					if(caneta == 1)
						floor[y][x] = 1;
				this.y = y;
				break;
			case 2:
				for(y = this.y; y >= 0 && j < pos; y--, j++)
					if(caneta == 1)
						floor[y][x] = 1;
				this.y = y;
				break;
			case 3:
				for(x = this.x; x >= 0 && j < pos; x--, j++)
					if(caneta == 1)
						floor[y][x] = 1;
				this.x = x;
				break;
			case 1:
				for(x = this.x; x <= 19 && j < pos; x++, j++)
					if(caneta == 1)
						floor[y][x] = 1;
				this.x = x;
				break;
		}
	}
	
	public void mostrar()
	{
		String saida = "";
		for(int i = 19; i >= 0; i--)
		{
			for(int j = 0; j < floor[i].length; j++)
			{
				if(floor[i][j] == 1)
					System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println("\n\n");
	}
	
	public static void main(String[] args)
	{
		Exercicio_07_21 tg = new Exercicio_07_21();
		
		int opc = 1;
		
		while(opc != 0)
		{
		
			System.out.println("\n\nEntre com a opcao:\n\n" +
							   "Comando\tSignificado\n" +
							   "1\tCaneta para cima\n" +
							   "2\tCaneta para baixo\n" +
							   "3\tVirar para a direita\n" +
							   "4\tVirar para a esquerda\n" +
							   "5\tAndar certa quantidade de casas\n" +
							   "6\tMostrar desenho\n" +
							   "9\tSobre\n" +
							   "0\tSair\n");
		
			opc = tg.sc.nextInt();
			
			switch(opc)
			{
				case 1:
					tg.caneta = 0;
					System.out.println("\nNao pode mais desenhar.\n");	break;
				case 2:
					tg.caneta = 1;
					System.out.println("\nPode desenhar.\n");	break;
				case 3:
					switch(tg.direcao)
					{
						case 3:
							tg.direcao = 0;	break;
						default:
							tg.direcao++;	break;
					}
					break;
				case 4:
					switch(tg.direcao)
					{
						case 0:
							tg.direcao = 3;	break;
						default:
							tg.direcao--;	break;
					}
					break;
				case 5:
					System.out.println("\nEntre com a quantidade de casas: ");
					tg.andar(tg.sc.nextInt());	break;
				case 6:
					tg.mostrar();	break;
				case 9:
					System.out.println("\n\nCriado por: Wesley - M4c4c0 L0k0" +
									   "\nData: 30/04/2011");
				case 0:
					continue;
				default:
					System.out.println("\nOpcao invalida!\n");	break;
			}
		}
	}
}