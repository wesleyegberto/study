// Exercício 7.22 1
// Passeio de Cavalo pelo tabuleiro, com acessibilidade
import java.util.Random;

public class Exercicio_07_22_c
{	
	int p;
	int cR;
	int cC;
	int currentRow;
	int currentCol;
	private static final Random rnd = new Random();
	int[][] tab = new int[8][8];
	int[][] ace = new int[8][];
	int i = 0;
	int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
	int[] vertical =  {-1, -2, -2, -1, 1, 2, 2, 1};
	
	public Exercicio_07_22_c()
	{
		ace[0] = new int[]{2, 3, 4, 4, 4, 4, 3, 2};
		ace[1] = new int[]{3, 4, 6, 6, 6, 6 ,4, 3};
		ace[2] = new int[]{4, 6, 8, 8, 8, 8, 6, 4};
		ace[3] = new int[]{4, 6, 8, 8, 8, 8, 6, 4};
		ace[4] = new int[]{4, 6, 8, 8, 8, 8, 6, 4};
		ace[5] = new int[]{4, 6, 8, 8, 8, 8, 6, 4};
		ace[6] = new int[]{3, 4, 6, 6, 6, 6 ,4, 3};
		ace[7] = new int[]{2, 3, 4, 4, 4, 4, 3, 2};
	}
	
	void move()
	{
		while(true)
		{
			i = rnd.nextInt(8);
			cR += horizontal[i];
			cC += vertical[i];
			
			if(cR >= 0 && cC >= 0 && cR < 8 && cC < 8)
				if(ace[cR][cC] == 3)
					break;
			
			cR = currentRow; cC = currentCol;
		}
		currentRow = cR;
		currentCol = cC;
		if(tab[currentRow][currentCol] == 0)
			p++;
		tab[currentRow][currentCol] = 1;
		System.out.println(p + "o -> " + currentRow + " " + currentCol);
		//mostrar();
	}
	
	void mostrar()
	{
		for(int[] row : tab)
		{
			for(int i : row)
				System.out.print(i + " ");
				
			System.out.println();
		}
	}
	
	public static void main(String[] args)
	{
		Exercicio_07_22_c pc = new Exercicio_07_22_c();
		pc.tab[0][0] = 1;
		for(pc.p = 0; pc.p < 64;)
		{
			pc.move();
		}
		
		pc.mostrar();
	}
}