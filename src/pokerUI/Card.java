package pokerUI;

/**
 * This class is the Card class of the poker UI.
 * This class handles all the important characteristics of the card.
 * These being the suit and the value of the card. 
 * are cards.
 * 
 * @author Golan Hassin
 * @version July 27, 2020
 */
public class Card {
	private int value;
	 private String suit;
	
	/**
	 * Create a Card with a value
	 * 
	 * @param value The card's value
	 */	 
	public Card(int value)
	{
		this.value = value;
	}
	
	/**
	 * Create a Card with a suit
	 * 
	 * @param suit The card's suit
	 */	 
	public Card(String suit)
	{
		this.suit = suit;
	}
	
	/**
	 * Create a Card with a value and a suit
	 * 
	 * @param value The card's value
	 * 
	 * @param suit The card's suit
	 */	 
	public Card (int value, String suit) 
	{
		this.value = value;
		this.suit = suit;
	}
	
	/**
	 * Get the suit of a Card
	 */
	public String getSuit() 
	{
		return this.suit;
	}
	
	/**
	 * Set the suit of a Card
	 * 
	 * @param suit The suit of the card
	 */
	public void setSuit(String suit) 
	{
		this.suit  = suit;
	}
	
	/**
	 * Get the value of a Card
	 */
	public int getValue() 
	{
		return this.value;
	}
	
	/**
	 * set the value of a Card
	 * 
	 * @param value The value of the card
	 */
	public void setValue(int value) 
	{
		this.value  = value;
	}
	
	/**
	 * If the card's value is a face card or an Ace,
	 * this method handles those exceptions
	 */
	public String toString ()
	{
		if (value == 11)
		{
			return "Jack of " + suit;
		}
		else if (value == 12)
		{
			return "Queen of " + suit;
		}
		else if (value == 13)
		{
			return "King of " + suit;
		}
		else if (value == 14)
		{
			return "Ace of " + suit;
		}
		else
		{
		return value + " of " + suit;
		}
	}
	
	/**
	 * Only get the name of card based on the value
	 * not the entire card itself 
	 */
	public String valueString()
	{
		if (value == 11)
		{
			return "Jack";
		}
		else if (value == 12)
		{
			return "Queen";
		}
		else if (value == 13)
		{
			return "King";
		}
		else if (value == 14)
		{
			return "Ace";
		}
		else
		{
		return String.valueOf(value);
		}
	}
	
	
	
}

