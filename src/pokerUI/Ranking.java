package pokerUI;

/**
 * This class is the Ranking class of the poker UI. This class handles the
 * ranking system for the game. This class can get the ranking of the users hand
 * 
 * @author Golan Hassin
 * @version July 27, 2020
 */

public class Ranking {

	private Card card;
	private Player player; // The player
	private int count1; // Important counts
	private int count2;
	private int count3;
	private int count4;

	/**
	 * Create a ranking object and set all the counters
	 * 
	 * @param player The player whose hand will be analyzed
	 */
	public Ranking(Player player) {
		this.player = player;
		this.count1 = getCount1(player);
		this.count2 = getCount2(player);
		this.count3 = getCount3(player);
		this.count4 = getCount4(player);

	}

	/**
	 * A way to refresh the counts if the player's hand changes
	 * 
	 * @param player The player whose hand will be analyzed
	 */
	public void update(Player player) {
		this.player = player;
		count1 = getCount1(player);
		count2 = getCount2(player);
		count3 = getCount3(player);
		count4 = getCount4(player);
	}

	/**
	 * Getting the first count to see how many of the cards match with card one
	 * 
	 * @param player The player whose hand will be analyzed
	 * 
	 * @return int The count based on how many cards match
	 */
	public int getCount1(Player player) {

		Card card1 = player.getCard1(); // Get card1
		Card card2 = player.getCard2(); // get card2
		Card card3 = player.getCard3(); // get card3
		Card card4 = player.getCard4(); // get card4
		Card card5 = player.getCard5(); // get card5

		int count1 = 0;

		if (card1.getValue() == card2.getValue()) {
			count1++; // Compare card 1 and 2
		}

		if (card1.getValue() == card3.getValue()) {
			count1++; // Compare card 1 and 3
		}

		if (card1.getValue() == card4.getValue()) {
			count1++; // Compare card 1 and 4
		}

		if (card1.getValue() == card5.getValue()) {
			count1++; // Compare card 1 and 5
		}

		return count1;
	}

	/**
	 * Getting the second count to see how many of the cards match with card two
	 * 
	 * @param player The player whose hand will be analyzed
	 * 
	 * @return int The count based on how many cards match
	 */
	public int getCount2(Player player) {
		int count2 = 0;

		Card card2 = player.getCard2(); // get card2
		Card card3 = player.getCard3(); // get card3
		Card card4 = player.getCard4(); // get card4
		Card card5 = player.getCard5(); // get card5

		if (card2.getValue() == card3.getValue()) {
			count2++; // Compare card 2 and 3, since we already compared 1 to 2
		}

		if (card2.getValue() == card4.getValue()) {
			count2++; // Compare card 2 and 4
		}

		if (card2.getValue() == card5.getValue()) {
			count2++; // Compare card 2 and 5
		}

		return count2;
	}

	/**
	 * Getting the third count to see how many of the cards match with card three
	 * 
	 * @param player The player whose hand will be analyzed
	 * 
	 * @return int The count based on how many cards match
	 */
	public int getCount3(Player player) {
		int count3 = 0;

		Card card3 = player.getCard3(); // get card3
		Card card4 = player.getCard4(); // get card4
		Card card5 = player.getCard5(); // get card5

		if (card3.getValue() == card4.getValue()) {
			count3++; // Compare card 3 and 4
		}

		if (card3.getValue() == card5.getValue()) {
			count3++; // Compare card 3 and 5
		}

		return count3;
	}

	/**
	 * Getting the fourth count to see how many of the cards match with card four
	 * 
	 * @param player The player whose hand will be analyzed
	 * 
	 * @return int The count based on how many cards match
	 */
	public int getCount4(Player player) {
		int count4 = 0;

		Card card4 = player.getCard4(); // get card4
		Card card5 = player.getCard5(); // get card5

		if (card4.getValue() == card5.getValue()) {
			count4++; // Compare card 4 and 5
		}

		return count4;
	}

	/**
	 * See if the player is holding a pair of any kind
	 * 
	 * @param player The player whose hand will be analyzed
	 * 
	 * @return boolean If the player has at least a pair
	 */
	public boolean checkForPair(Player player) {

		if (count1 == 1) {
			card = player.getCard1();

			if (player.getCard1().getValue() > 10) {
				player.jacksBetter(); // If there is a pair make sure Jacks or better is checked for
				return true; // return true if there is a pair
			}
			return true;
		}

		// Repeat the same steps for the other counts

		if (count2 == 1) {
			card = player.getCard2();

			if (player.getCard2().getValue() > 10) {
				player.jacksBetter();
				return true;
			}
			return true;
		}

		if (count3 == 1) {
			card = player.getCard3();

			if (player.getCard3().getValue() > 10) {
				player.jacksBetter();
				return true;
			}
			return true;
		}

		if (count4 == 1) {
			card = player.getCard4();

			if (player.getCard4().getValue() > 10) {
				player.jacksBetter();
				return true;
			}
			return true;
		}

		else {
			return false;
		}
	}

	/**
	 * See if the player is holding two pairs of any kind
	 * 
	 * @param player The player whose hand will be analyzed
	 * 
	 * @return boolean If the player has two pairs
	 */
	public boolean checkForTwoPair(Player player) {
		if (count1 == 1) {
			if (count2 == 1 || count3 == 1 || count4 == 1) // If count1 means there is one pair, we need another for two
															// pairs
			{
				return true;
			}
		}

		if (count2 == 1) {
			if (count3 == 1 || count4 == 1) // If count2 means there is one pair, we need another for two pairs
											// We only look at count3 and count because count1 was handled above.
			{
				return true;
			}
		}
		// Check for last possible two pair combination
		if (count3 == 1) {
			if (count4 == 1) {
				return true;
			}
		}

		return false;

	}

	/**
	 * See if the player is holding three of a kind
	 * 
	 * @param player The player whose hand will be analyzed
	 * 
	 * @return boolean If the player has three of a kind
	 */
	public boolean checkForThreeOfaKind(Player player) {
		if (count1 == 2 || count2 == 2 || count3 == 2) // If count is 2 it means at least 3 cards match
		{
			return true;
		}
		return false;
	}

	/**
	 * See if the player is holding a straight of any kind
	 * 
	 * @param player The player whose hand will be analyzed
	 * 
	 * @return boolean If the player has a straight
	 */
	public boolean checkForStraight(Player player) {
		player.orderCards(); // Order the cards so the check is very easy
		Card card1 = player.getCard1(); // Get card1
		Card card2 = player.getCard2(); // get card2
		Card card3 = player.getCard3(); // get card3
		Card card4 = player.getCard4(); // get card4
		Card card5 = player.getCard5(); // get card5

		if ((card1.getValue() + 1 == card2.getValue()) && (card2.getValue() + 1 == card3.getValue())
				&& (card3.getValue() + 1 == card4.getValue()) && (card4.getValue() + 1 == card5.getValue())) {
			return true; // If the cards get incrementally bigger the it is a straight
		}

		// Handle Special Case
		if (card1.getValue() == 2 && card2.getValue() == 3 && card3.getValue() == 4 && card4.getValue() == 5
				&& card5.getValue() == 14) {
			return true; // This is the case where ace is small and the straight is A,2,3,4,5
		}

		return false;
	}

	/**
	 * See if the player is holding a flush of any kind
	 * 
	 * @param player The player whose hand will be analyzed
	 * 
	 * @return boolean If the player has a flush
	 */
	public boolean checkForFlush(Player player) {
		player.orderCards();
		Card card1 = player.getCard1(); // Get card1
		Card card2 = player.getCard2(); // get card2
		Card card3 = player.getCard3(); // get card3
		Card card4 = player.getCard4(); // get card4
		Card card5 = player.getCard5(); // get card5

		if ((card1.getSuit().equals(card2.getSuit())) && (card1.getSuit().equals(card3.getSuit()))
				&& (card1.getSuit().equals(card4.getSuit())) && (card1.getSuit().equals(card5.getSuit()))) {
			return true; // If all the suits match then its a flush. The suits just need to be compared
							// to one card because it must be all of them
		}

		return false;
	}

	/**
	 * See if the player is holding a full house of any kind
	 * 
	 * @param player The player whose hand will be analyzed
	 * 
	 * @return boolean If the player has a full house
	 */
	public boolean checkForFullHouse(Player player) {
		if (count1 == 2) // If there is a three of a kind
		{
			if (count2 == 1) // If there is a pair
			{
				if (player.getCard1().getValue() == player.getCard2().getValue()) {
					return false; // There could be a false positive with count2 if card1 is the same value as
									// card2
									// There is only a full house if card one has a three of a kind and card2 has a
									// separate pair
				}

				else {
					return true;
				}
			}

			if (count3 == 1) // If there is a pair
			{
				if (player.getCard1().getValue() == player.getCard3().getValue()) // Same check as before
				{
					return false;
				}

				else {
					return true;
				}
			}

			// Repeat the same checks
			if (count4 == 1) {
				if (player.getCard1().getValue() == player.getCard4().getValue()) {
					return false;
				}

				else {
					return true;
				}
			}

		}

		// Repeat the same check but with card 3
		if (count2 == 2) {
			if (count1 == 1) {
				if (player.getCard1().getValue() == player.getCard2().getValue()) {
					return false;
				}

				else {
					return true;
				}
			}

		}

		// Repeat the same check but with card 3
		if (count3 == 2) {
			if (count1 == 1) {
				if (player.getCard1().getValue() == player.getCard3().getValue()) {
					return false;
				}

				else {
					return true;
				}
			}

		}

		return false;
	}

	/**
	 * See if the player is holding four of a kind
	 * 
	 * @param player The player whose hand will be analyzed
	 * 
	 * @return boolean If the player has four of a kind
	 */
	public boolean checkForFourofaKind(Player player) {
		if (count1 == 3 || count2 == 3) // Simple, if the count is 3, four cards are the same
		{
			return true;
		}
		return false;
	}

	/**
	 * See if the player is holding a straight flush
	 * 
	 * @param player The player whose hand will be analyzed
	 * 
	 * @return boolean If the player has a straight flush
	 */
	public boolean checkForStraighFlush(Player player) {
		if (checkForFlush(player) && checkForStraight(player)) // Simply use the previous methods
		{
			return true;
		}

		return false;
	}

	/**
	 * See if the player is holding a royal flush
	 * 
	 * @param player The player whose hand will be analyzed
	 * 
	 * @return boolean If the player has a royal flush
	 */
	public boolean checkForRoyalFlush(Player player) {
		Card card1 = player.getCard1(); // Get card1
		Card card2 = player.getCard2(); // get card2
		Card card3 = player.getCard3(); // get card3
		Card card4 = player.getCard4(); // get card4
		Card card5 = player.getCard5(); // get card5

		if (checkForStraighFlush(player)) // They need a straight flush for a royal flush
		{
			if ((card1.getValue() == 10) && (card2.getValue() == 11) && (card3.getValue() == 12)
					&& (card4.getValue() == 13) && (card5.getValue() == 14)) {
				return true; // Check for the specific cards needed for a royal flush: 10,J,Q,K,A
			}
		}
		return false;
	}

	/**
	 * A method that uses all the checks above to get the value of the players hand
	 * 
	 * @param player The player whose hand will be analyzed
	 * 
	 * @return int The strength of their hand
	 */
	public int getRanking(Player player) {
		player.orderCards();
		Card card1 = player.getCard1(); // Get card1
		Card card2 = player.getCard2(); // get card2
		Card card3 = player.getCard3(); // get card3
		Card card4 = player.getCard4(); // get card4
		Card card5 = player.getCard5(); // get card5

		if (checkForRoyalFlush(player)) // Check for higher rankings first
		{
			// System.out.println("You are holding a Royal Flush");
			return 1;
		}

		if (checkForStraighFlush(player)) {
			// System.out.println("You are holding a Straight Flush");
			return 2;
		}

		if (checkForFourofaKind(player)) {
			// System.out.println("You are holding a Four of a Kind");
			return 3;
		}

		if (checkForFullHouse(player)) {
			// System.out.println("You are holding a Full House");
			return 4;
		}

		if (checkForFlush(player)) {
			// System.out.println("You are holding a Flush");
			return 5;
		}

		if (checkForStraight(player)) {
			// System.out.println("You are holding a Straight");
			return 6;
		}

		if (checkForThreeOfaKind(player)) {
			// System.out.println("You are holding a Three of a Kind");
			return 7;
		}

		if (checkForTwoPair(player)) {
			// System.out.println("You are holding Two Pair");
			return 8;
		}

		if (checkForPair(player)) {
			// System.out.println("You are holding a pair of: " + card.valueString() + "s");
			return 9;
		}

		else {
			// System.out.println("You are holding a High Card: "+ card5.valueString());
			return 10;
		}

	}

	/**
	 * A method that uses all the checks above to get the value of the players hand.
	 * This is used for the GUI as the actual value will be displayed and not an int
	 * 
	 * @param player The player whose hand will be analyzed
	 * 
	 * @return String The ranking of the players hand
	 */
	public String getRankingUI(Player player) {
		player.orderCards();
		Card card1 = player.getCard1(); // Get card1
		Card card2 = player.getCard2(); // get card2
		Card card3 = player.getCard3(); // get card3
		Card card4 = player.getCard4(); // get card4
		Card card5 = player.getCard5(); // get card5

		if (checkForRoyalFlush(player)) {
			return "You are holding a Royal Flush";
		}

		if (checkForStraighFlush(player)) {
			return "You are holding a Straight Flush";
		}

		if (checkForFourofaKind(player)) {
			return "You are holding a Four of a Kind";
		}

		if (checkForFullHouse(player)) {
			return "You are holding a Full House";
		}

		if (checkForFlush(player)) {
			return "You are holding a Flush";
		}

		if (checkForStraight(player)) {
			return "You are holding a Straight";
		}

		if (checkForThreeOfaKind(player)) {
			return "You are holding a Three of a Kind";
		}

		if (checkForTwoPair(player)) {
			return "You are holding Two Pairs";
		}

		if (checkForPair(player)) {
			return "You are holding a pair of: " + card.valueString() + "s";

		}

		else {
			return "You are holding a High Card: " + card5.valueString();

		}

	}
}
