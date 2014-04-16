// Exercício 7.22 b
// Passeio de Cavalo pelo tabuleiro
import java.util.Random;

public class Exercicio_07_22_b
{
	int p;
	int cR, cC;
	int currentRow;
	int currentCol;
	int[][] tab = new int[8][8];
	private static final Random rnd = new Random();
	int i = 0;
	int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
	int[] vertical = {-1, -2, -2, -1, 1, 2, 2, 1};
	
	void move()
	{
		while(true)
		{
			i = rnd.nextInt(8);
			cR += horizontal[i];
			cC += vertical[i];
			
			if(cR >= 0 && cC >= 0 && cR < 8 && cC < 8)
				//if(tab[cR][cC] == 0)
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
		Exercicio_07_22_b pc = new Exercicio_07_22_b();
		pc.tab[0][0] = 1;
		for(pc.p = 0; pc.p < 64;)
		{
			pc.move();
		}
		
		pc.mostrar();
	}
}