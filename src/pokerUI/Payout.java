package pokerUI;

/**
 * This class is the Payout class of the poker UI.
 * This class handles the payout system for the game.
 *  
 * @author Golan Hassin
 * @version July 27, 2020
 */
public class Payout {

	Ranking r;
	
	/**
	 * Get the payout required based on
	 * the value of the player's hand
	 * 
	 * @param player The Player whose hand will be analyzed
	 */	
	public int getPayout(Player player)
	{
		int payout = 0; //Default payout
		int bet = player.getBet(); //Get the bid of the player
		
		r = new Ranking(player);
		
		player.setHandValue(r.getRanking(player)); //Set the ranking of their hand
		
		int value = player.getHandValue(); //Get the ranking
		
		if(value == 10)
		{
			player.setBet(0); //If high card pay nothing
			return payout;
		}
		
		if(value == 9)
		{
			if(player.isJackOrBetter())
			{
				payout = 1 * bet; //If jacks or better, pay 1*bet
				player.addStack(payout);
				player.isNotJacksBetter();
				return payout;
			}
			else
			{
				player.setBet(0);
				payout = 0;
				return payout; //Otherwise pay nothing
			}
		}
		
		if(value == 8)
		{
			payout = 2 * bet; //If two pair 2*bet
			player.addStack(payout);
			return payout;
		}
		
		if(value == 7)
		{
			payout = 3 * bet; //If three of a kind 3*bet
			player.addStack(payout);
			return payout;
		}
		
		if(value == 6)
		{
			payout = 4 * bet; //If straight 4*bet
			player.addStack(payout);
			return payout;
		}
		
		if(value == 5)
		{
			payout = 6 * bet; //If flush 6*bet
			player.addStack(payout);
			return payout;
		}
		
		if(value == 4)
		{
			payout = 9 * bet; //If full house 9*bet
			player.addStack(payout);
			return payout;
		}
		
		if(value == 3)
		{
			payout = 25 * bet;  //If four of a kind 25*bet
			player.addStack(payout);
			return payout;
		}
		
		if(value == 2)
		{
			payout = 50 * bet; //If straight flush 50*bet
			player.addStack(payout);
			return payout;
		}
		
		if(value == 1)
		{
			if(bet == 5)
			{
				payout = 4000; //If royal flush and the bet was 5, 800*bet
				return payout;
			}
			else
			{
				payout = 250 * bet; //Otherwise 250*bet
				return payout;
			}
		}
		return payout;
	}
}
