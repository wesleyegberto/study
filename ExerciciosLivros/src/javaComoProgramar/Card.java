// Figura 7.9: Card.java
// Classe Card representa uma carta de trabalho

public class Card
{
	String face; // face da carta ("Ace", "Deuce", ...)
	String suit; // naipe da carta
	
	// construtor de dois argumentos inicializa face e naipe da carta
	public Card(String cardFace, String cardSuit)
	{
		face = cardFace;
		suit = cardSuit;
	}
	
	// retorna representação String de CardLayout
	public String toString()
	{
		return face + " of " + suit;
	}
}