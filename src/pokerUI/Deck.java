package pokerUI;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is the Deck class of the poker UI.
 * This class creates and handles the deck for the game.
 * The deck is represented by an ArrayList of cards
 *  
 * @author Golan Hassin
 * @version July 27, 2020
 */

public class Deck {
	ArrayList<Card> deck; 
	
	/**
	 * Create an empty deck
	 */
	public Deck() 
	{
		this.deck = new ArrayList<Card>();
	}
	
	/**
	 * Create the spades suit, thirteen cards, 2 to 14 all of spades.
	 * Add those to the deck
	 */
	public void createSpades() 
	{
		for(int i = 2; i<15 ; i++)
		{
			Card card = new Card(i, "Spades");
				
			deck.add(card);
		}
	}
	
	/**
	 * Create the hearts suit, thirteen cards, 2 to 14 all of hearts
	 * Add those to the deck
	 */
	public void createHearts() 
	{
		for(int i = 2; i<15 ; i++)
		{
			Card card = new Card(i, "Hearts");
				
			deck.add(card);
		}
	}
	
	/**
	 * Create the diamonds suit, thirteen cards, 2 to 14 all of diamonds
	 * Add those to the deck
	 */
	public void createDiamonds() 
	{
		for(int i = 2; i<15 ; i++)
		{
			Card card = new Card(i, "Diamonds");
				
			deck.add(card);
		}
	}
	
	/**
	 * Create the clubs suit, thirteen cards, 2 to 14 all of clubs
	 * Add those to the deck
	 */
	public void createClubs() 
	{
		for(int i = 2; i<15 ; i++)
		{
			Card card = new Card(i, "Clubs");
				
			deck.add(card);
		}
	}
	
	/**
	 * Create and add all the suits to the deck
	 */
	public void createDeck()
	{
		createSpades();
		createHearts();
		createDiamonds();
		createClubs();
	}
	
	/**
	 * Clear the deck, then add all the cards
	 */
	public void resetDeck()
	{
		deck.clear();
		createSpades();
		createHearts();
		createDiamonds();
		createClubs();
		Collections.shuffle(deck);
	}
	
	
	/**
	 * Shuffle the deck using the shuffle feature in Java
	 */
	public void Shuffle()
	{
		Collections.shuffle(deck);
	}
	
	/**
	 * A method to print out all the cards of the deck
	 */
	public void seeDeck()
	{
		int count = 0;
		for (Card card: deck)
		{
			System.out.println(card.toString());
			count++;
		}
		System.out.println(count);
		
	}
	
	/**
	 * Draw the top card from the deck, remove it and return it
	 * 
	 * @return Card The card that is drawn from the top 
	 */
	public Card draw()
	{
		Card card = deck.remove(0);
		return card;
	}
}

