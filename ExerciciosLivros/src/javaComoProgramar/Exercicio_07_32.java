// Exercício 7.32
// Verificar pontos em duas mãos de Poker (Regras na classe Rules) e com a mão do carteador, podendo trocar

public class Exercicio_07_32
{
	
	static DeckOfCards baralho = new DeckOfCards();
		
	public static void main(String[] args)
	{
		Rules r = new Rules();
		int meusP = 0, seusP = 0, casaP = 0;
		
		baralho.shuffle();
		
		Card[] minhaMao = new Card[5];
		Card[] suaMao = new Card[5];
		Card[] casaMao = new Card[5];
		
		System.out.println();
		for(int i = 0; i < minhaMao.length; i++)
		{
			minhaMao[i] = baralho.dealCard();
			suaMao[i] = baralho.dealCard();
			casaMao[i] = baralho.dealCard();
			System.out.println(minhaMao[i].toString() + "\t\t" + suaMao[i].toString() + "\t\t" + casaMao[i].toString());
		}
		
		System.out.println("\nMeu status:");
		meusP = r.compare(minhaMao);
		System.out.println("Meus pontos: " + meusP);
		
		System.out.println("\nSeu status:");
		seusP = r.compare(suaMao);
		System.out.println("Seu pontos: " + seusP);
		
		System.out.println("\nCasa status:");
		casaP = r.compareCasa(casaMao);
		System.out.println("Casa pontos: " + casaP + "\n\n");
		
		
		for(int i = 0; i < minhaMao.length; i++)
		{
			System.out.println(minhaMao[i].toString() + "\t\t" + suaMao[i].toString() + "\t\t" + casaMao[i].toString());
		}
		
		if(meusP > seusP && meusP > casaP)
			System.out.println("\nVenci!\n");
		else if(meusP < seusP && seusP > casaP)
			System.out.println("\nVenceu!\n");
		else
			System.out.println("\nCasa ganhou!\n");
	}
}