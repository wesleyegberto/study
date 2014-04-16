// Exercício 7.30
// Verificar pontos em uma mão de Poker (Regras na classe Rules)

public class Exercicio_07_30
{
	public static void main(String[] args)
	{
		Rules r = new Rules();
		DeckOfCards baralho = new DeckOfCards();
		baralho.shuffle();
		
		Card[] minhaMao = new Card[5];
		System.out.println();
		for(int i = 0; i < minhaMao.length; i++)
		{
			minhaMao[i] = baralho.dealCard();
			System.out.println(minhaMao[i].toString());
		}
		
		r.compare(minhaMao);
	}
}