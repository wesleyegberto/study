// Figura 7.10: DeckOfCards.java
// classe DeckOfCards representa um baralho
import java.util.Random;

public class DeckOfCards
{
	private Card[] deck; // array de objetos Card
	private int currentCard; // �ndice do pr�ximo Card a ser distribuido
	private static final int NUMBER_OF_CARDS = 52; // n�mero constante de cards
	// gerador de n�meros aleat�rio
	private static final Random randomNumbers = new Random();
	
	// construtor preenche baralho de cartas
	public DeckOfCards()
	{
		String[] faces = {"A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
		
		deck = new Card[NUMBER_OF_CARDS]; //cria um array de objetos Card
		currentCard = 0; // configura como o primeiro card distribuido � deck[0]
		
		// preenche baralho com objetos card
		for(int i = 0; i < deck.length; i++)
		{
			deck[i] = new Card(faces[i % 13], suits[i / 13]);
		}
	}
	
	// embaralha as cartas com um algoritmo de uma passagem
	public void shuffle()
	{
		// depois de embaralhar, a distribui��o deve iniciar em deck[0] novamente
		currentCard = 0;
		
		// para cada Card, seleciona outro Card aleat�rio e os compara
		for(int first = 0; first < deck.length; first++)
		{
			// seleciona um n�mero aleat�rio entre 0 e 51
			int second = randomNumbers.nextInt(NUMBER_OF_CARDS);
			
			//compara Card atual com Card aleatoriamente selecionado
			Card temp = deck[first];
			deck[first] = deck[second];
			deck[second] = temp;
		}
	}
	
	// distribui um card
	public Card dealCard()
	{
		// determina se ainda h� Cards a serem distribuidos
		if(currentCard < deck.length)
			return deck[currentCard++];
		else
			return null;
	}
}