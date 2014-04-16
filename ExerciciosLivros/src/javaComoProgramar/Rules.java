// Classe com as regras do Poker
import java.util.ArrayList;

public class Rules
{
	public int compare(Card[] mao)
	{
		int pair = 0, pontos = 0;
		boolean trinca = false, quadra = false, flush = false, straight = false, fullHouse = false;
		
		pair = comparePair(mao);
		trinca = compareTrinca(mao);
		quadra = compareQuadra(mao);
		flush = compareFlush(mao);
		//straight
		if(pair == 2 && trinca == true)
			fullHouse = true;
		
		System.out.println("\nQuantidade de pares: " + pair);
		pontos += pair;
		
		if(trinca == true && quadra == false && flush == false && straight == false && fullHouse == false)
		{
			System.out.println("Trinca!");
			pontos += 3;
		}
		else
			System.out.println("No trinca!");
		
		if(quadra == true && flush == false && straight == false && fullHouse == false)
		{
			System.out.println("Quadra!");
			pontos += 4;
		}
		else
			System.out.println("No quadra!");
			
		if(flush == true && straight == false && fullHouse == false)
		{
			System.out.println("Fluse!");
			pontos += 5;
		}
		else
			System.out.println("No flush!");
			
		if(straight == true && fullHouse == false)
		{
			System.out.println("Straight!!!");
			pontos += 6;
		}
		else
			System.out.println("No straight!");
			
		if(fullHouse == true)
		{
			System.out.println("FULL HOUSE!!!");
			pontos += 7;
		}
		else
			System.out.println("No full house!");
		
		return pontos;
	}
	
	
	public int comparePair(Card[] mao)
	{
		int r = 0;
		
		for(int i = 0; i < mao.length; i++)
		{			
			for(int j = 0; j < mao.length; j++)
			{
				if(i == j)
					continue;
				
				if(mao[i].face.equals(mao[j].face))
				{
					r++;
					break;
				}
			}
		}
		
		return r / 2;
	}
	
	public boolean compareTrinca(Card[] mao)
	{
		for(int i = 0; i < mao.length; i++)
		{			
			for(int j = 0; j < mao.length; j++)
			{
				if(i == j)
					continue;
				
				if(mao[i].face.equals(mao[j].face))
				{
					for(int x = 0; x < mao.length; x++)
					{
						if(x == i || x == j)
							continue;
						
						if(mao[i].face.equals(mao[x].face))
							return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean compareQuadra(Card[] mao)
	{
		for(int i = 0; i < mao.length; i++)
		{			
			for(int j = 0; j < mao.length; j++)
			{
				if(i == j)
					continue;
				
				if(mao[i].face.equals(mao[j].face))
				{
					for(int x = 0; x < mao.length; x++)
					{
						if(x == i || x == j)
							continue;
						
						if(mao[i].face.equals(mao[x].face))
						{
							for(int y = 0; y < mao.length; y++)
							{
								if(y == i || y == j || y == x)
									continue;
								
								if(mao[i].face.equals(mao[y].face))
									return true;
							}
						}
					}
				}
			}
		}
		
		return false;
	}
	
	public boolean compareFlush(Card[] mao)
	{
		if(mao[0].suit.equals(mao[1].suit) && mao[0].suit.equals(mao[2].suit)  && mao[0].suit.equals(mao[3].suit) &&
		   mao[0].suit.equals(mao[4].suit))
			return true;
		else
			return false;
	}
	
	//Comparação dos pontos da casa
	
	public int compareCasa(Card[] mao) //Para trocar as cartas da casa, caso sejam fraca
	{
		int pair = 0, pontos = 0;
		boolean trinca = false, quadra = false, flush = false, straight = false, fullHouse = false;
		
		pair = comparePairCasa(mao);
		trinca = compareTrincaCasa(mao);
		quadra = compareQuadra(mao);
		flush = compareFlush(mao);
		//straight
		if(pair == 2 && trinca == true)
			fullHouse = true;
		
		System.out.println("\nQuantidade de pares: " + pair);
		pontos += pair;
		
		if(trinca == true && quadra == false && flush == false && straight == false && fullHouse == false)
		{
			System.out.println("Trinca!");
			pontos += 3;
		}
		else
			System.out.println("No trinca!");
		
		if(quadra == true && flush == false && straight == false && fullHouse == false)
		{
			System.out.println("Quadra!");
			pontos += 4;
		}
		else
			System.out.println("No quadra!");
			
		if(flush == true && straight == false && fullHouse == false)
		{
			System.out.println("Fluse!");
			pontos += 5;
		}
		else
			System.out.println("No flush!");
			
		if(straight == true && fullHouse == false)
		{
			System.out.println("Straight!!!");
			pontos += 6;
		}
		else
			System.out.println("No straight!");
			
		if(fullHouse == true)
		{
			System.out.println("FULL HOUSE!!!");
			pontos += 7;
		}
		else
			System.out.println("No full house!");
		
		return pontos;
	}
	
	
	public int comparePairCasa(Card[] mao)
	{
		int r = 0;
		
		for(int i = 0; i < mao.length; i++)
		{			
			for(int j = 0; j < mao.length; j++)
			{
				if(i == j)
					continue;
				
				if(mao[i].face.equals(mao[j].face))
				{
					r++;
					break;
				}
				else
				{
					mao[j] = Exercicio_07_32.baralho.dealCard(); 
				}
			}
		}
		
		return r / 2;
	}
	
	
	public boolean compareTrincaCasa(Card[] mao)
	{
		for(int i = 0; i < mao.length; i++)
		{			
			for(int j = 0; j < mao.length; j++)
			{
				if(i == j)
					continue;
				
				if(mao[i].face.equals(mao[j].face))
				{
					for(int x = 0; x < mao.length;)
					{
						if(x == i || x == j)
						{
							x++;
							continue;
						}
						
						if(mao[i].face.equals(mao[x].face))
							return true;
						else
							mao[x] = Exercicio_07_32.baralho.dealCard();
						
					}
				}
			}
		}
		
		return false;
	}
	
}