// Exercício 7.31
// Verificar pontos em duas mãos de Poker (Regras na classe Rules)

public class Exercicio_07_31
{
	public static void main(String[] args)
	{
		Rules r = new Rules();
		DeckOfCards baralho = new DeckOfCards();
		int meusP = 0, seusP = 0;
		
		baralho.shuffle();
		
		Card[] minhaMao = new Card[5];
		Card[] suaMao = new Card[5];
		
		System.out.println();
		for(int i = 0; i < minhaMao.length; i++)
		{
			minhaMao[i] = baralho.dealCard();
			suaMao[i] = baralho.dealCard();
			System.out.println(minhaMao[i].toString() + "\t\t" + suaMao[i].toString());
		}
		
		System.out.println("\nMeu status:");
		meusP = r.compare(minhaMao);
		
		System.out.println("\nSeu status:");
		seusP = r.compare(suaMao);
		
		if(meusP > seusP)
			System.out.println("\nVenci!\n");
		else if(meusP < seusP)
			System.out.println("\nVenceu!\n");
		else
			System.out.println("\nEmpate!?\n");
	}
}