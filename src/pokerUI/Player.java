package pokerUI;

import java.util.ArrayList;

/**
 * This class is the Player class of the poker UI.
 * This class handles all the important characteristics of the player.
 * These being their name, stack, bet, hand, hand value. 
 * are cards.
 * 
 * @author Golan Hassin
 * @version July 27, 2020
 */

public class Player {

	private String name; //The players name
	private ArrayList<Card> hand; //Their hand of Card objects
	private int stack; //Their stack
	private int bet; //The bet
	private int handValue; //The value of their hand
	private boolean jacks = false; //If they have Jacks or better

	/**
	 * Create a player with a name
	 * 
	 * @param name The players name
	 */
	public Player(String name) 
	{
		this.hand = new ArrayList<Card>();
		this.name = name;
	}

	/**
	 * Create a player with a name and a stack
	 * 
	 * @param name The player's name
	 * 
	 * @param stack The player's stack
	 */
	public Player(String name, int stack) 
	{
		this.hand = new ArrayList<Card>();
		this.name = name;
		this.stack = stack;
	}
	
	/**
	 * Create a player with a stack
	 * 
	 * @param stack The player's stack
	 */
	public Player(int stack)
	{
		this.hand = new ArrayList<Card>();
		this.stack = stack;
	}
	
	/**
	 * The player does not have Jacks or better
	 */
	public void isNotJacksBetter()
	{
		jacks = false;
	}
	
	/**
	 * The player has Jacks or better
	 */
	public void jacksBetter() 
	{
		jacks = true;
	}

	/**
	 * Get if the player has Jacks or better
	 */
	public boolean isJackOrBetter() 
	{
		return jacks;
	}

	/**
	 * Set the bet of the player
	 * 
	 * @param bet The player's bet
	 */
	public void setBet(int bet) 
	{
		this.bet = bet;
	}

	/**
	 * Get the bet of the player
	 */
	public int getBet() 
	{
		return this.bet;
	}

	/**
	 * Make a bet on the player's behalf
	 * 
	 * @param betSize The size of the bet
	 */
	public void makeBet(int betSize) 
	{
		
		
		if(betSize == 3 && (stack!=1 || stack!=2) )
		{
			this.setStack((stack - betSize));
			bet = betSize;
		}
		
		else if (stack < betSize) 
		{
			System.out.println("Invalid bet size.");
		} 
		else 
		{
			this.setStack((stack - betSize));
			bet = betSize;
		}
	}

	/**
	 * Add money to the stack of the player
	 * 
	 * @param winning The amount added to the stack
	 */
	public void addStack(int winning) 
	{
		this.stack = stack + winning;
	}
	
	/**
	 * Get the hand value  of the player
	 */
	public int getHandValue() 
	{
		return this.handValue;
	}

	/**
	 * Set the hand value of the player
	 * 
	 * @param value The player's hand value
	 */
	public void setHandValue(int value) 
	{
		this.handValue = value;
	}

	/**
	 * Get the name of the player
	 */
	public String getName() 
	{
		return this.name;
	}

	/**
	 * Set the name of the player
	 * 
	 * @param name The player's name
	 */
	public void setName(String name) 
	{
		this.name = name;
	}

	/**
	 * Get a card of the player at a specific index
	 * 
	 * @param index The index of the card that is needed
	 */
	public Card getCard(int index) 
	{
		return hand.get(index);
	}

	/**
	 * Add a card to the player's hand
	 * 
	 * @param ind The index of where the new card should be placed
	 * 
	 * @param card The card to be added to the player's hand
	 */
	public void addCard(int ind, Card card) 
	{
		hand.add(ind, card);
	}

	/**
	 * Set the card of the player at an index
	 * 
	 * @param ind The index of where the new card should be set
	 * 
	 * @param card The card to be set to the player's hand
	 */
	public void setCard(int ind, Card card) 
	{
		hand.set(ind, card);
	}

	/**
	 * Get the first card of the player
	 */
	public Card getCard1() 
	{
		return hand.get(0);
	}

	/**
	 * Set the first card of the player
	 * 
	 * @param card1 The card to be set
	 */
	public void setCard1(Card card1) 
	{
		hand.set(0, card1);
	}

	/**
	 * Get the second card of the player
	 */
	public Card getCard2() 
	{
		return hand.get(1);
	}

	/**
	 * Set the second card of the player
	 * 
	 * @param card2 The card to be set
	 */
	public void setCard2(Card card2) 
	{
		hand.set(1, card2);
	}

	/**
	 * Get the third card of the player
	 */
	public Card getCard3() 
	{
		return hand.get(2);
	}

	/**
	 * Set the third card of the player
	 * 
	 * @param card3 The card to be set
	 */
	public void setCard3(Card card3) 
	{
		hand.set(2, card3);
	}

	/**
	 * Get the fourth card of the player
	 */
	public Card getCard4() 
	{
		return hand.get(3);
	}

	/**
	 * Set the fourth card of the player
	 * 
	 * @param card4 The card to be set
	 */
	public void setCard4(Card card4) 
	{
		hand.set(3, card4);
	}

	/**
	 * Get the fifth of the player
	 */
	public Card getCard5() 
	{
		return hand.get(4);
	}

	/**
	 * Set the fifth card of the player
	 * 
	 * @param card5 The card to be set
	 */
	public void setCard5(Card card5) 
	{
		hand.set(4, card5);
	}

	/**
	 * Get the stack of the player
	 */
	public int getStack() 
	{
		return this.stack;
	}

	/**
	 * Set the stack of the player
	 * 
	 * @param stack The player's new stack
	 */
	public void setStack(int stack) 
	{
		this.stack = stack;
	}

	/**
	 * Clear the hand of the player
	 */
	public void clearHand() 
	{
		hand.clear();
	}

	/**
	 * Check if the player's hand is empty
	 * 
	 * @return boolean If the hand is empty
	 */
	public boolean emptyHand() 
	{
		if (hand.isEmpty()) 
		{
			return true;
		} 
		else 
		{
			return false;
		}
	}

	/**
	 * Print out on the console of the five cards the player is holding
	 */
	public void Peek() 
	{
		System.out.print("You are holding the: ");
		
		for (Card card : hand) 
		{
			if (card.equals(hand.get(4))) 
			{
				System.out.print("and " + card.toString());
				System.out.println("");
			} 
			else 
			{
				System.out.print(card.toString() + ", ");
			}
		}
	}

	/**
	 * Order the cards of the player based on highest to lowest value
	 */
	public void orderCards() 
	{
		for (int i = 0; i < 5; i++) // Loop through the array
		{
			for (int j = 0; j < 5 - i - 1; j++) // For every element compare it to all the others
			{

				if (this.getCard(j).getValue() > this.getCard(j + 1).getValue()) // if the given element is larger than the next
																				
				{
					// Swap the smaller with the larger
					Card temp = this.getCard(j);
					this.setCard(j, this.getCard(j + 1));
					this.setCard(j + 1, temp);
				}

			}

		}
	}

}

