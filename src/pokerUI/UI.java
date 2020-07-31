package pokerUI;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * This class is the main UI class of the "Video Poker" application. Video poker
 * is a casino game where the user places a bet and then is given five cards.
 * They can swap as many of the five cards, as they want and after new cards are
 * dealt a payout is determined. The payout is based on strength of the poker
 * hand the user now has with their five cards.
 * 
 * To play this game, an instance of the UI class is made is the main and is
 * called.
 * 
 * This class creates instances of Player, Deck, Card, Ranking and Payout
 * classes.
 * 
 * This class also extends JFrame in order to create the frame and implements
 * ActionListener to allows for easy use of the Action Listeners on the buttons
 * are cards.
 * 
 * @author Golan Hassin
 * @version July 28, 2020
 */
public class UI extends JFrame implements ActionListener {

	private JButton button1, button2, button3, button4, button5, swap, newDeal; // The first five buttons are the cards
																				// the others swap and deal new hands.
	private JLabel name, bid, amount, hand, message, payout; // The different information on the screen
	private String inputName, rankingString;
	private int stack = 100; // The starting stack for the user
	private int inputBid, swapCount, button1Count, button2Count, button3Count, button4Count, button5Count = 0; // A way to keep track of the cards
																									
	private Player player; // The player
	private int ranking; // A way to keep track of the strength of the user's hand
	private boolean test1, test2, test3, bTest1, bTest2, bTest3, bTest4, bTest5 = false; // Different boolean tests used
																							// to manage things
	private JMenuBar menuBar; // A menu bar
	private JMenu mainMenu; // A menu
	private JMenuItem quit; // Menu item for quitting
	private JMenuItem newGame; // Menu item for a new game
	private Deck deck = new Deck(); // The deck
	private Ranking r; // The ranking system
	private Payout pay; // The payout system

	/**
	 * Create the game and its layout with all the buttons
	 */
	public UI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Makes sure the UI closes when exited

		JPanel panel1 = new JPanel(); // The panel

		panel1.setLayout(new GridBagLayout()); // Use a GridBagLayout

		GridBagConstraints gCons = new GridBagConstraints(); // Create an instance of the GridBagConstraints class

		inputName = JOptionPane.showInputDialog("Enter your name!: "); // Get the users screen name

		name = new JLabel("name: " + inputName);
		name.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		addComp(panel1, name, 0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE); // Set the name at the top
																								// left

		bid = new JLabel("bid:" + inputBid);
		bid.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		addComp(panel1, bid, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE); // Set the bid top middle

		amount = new JLabel("stack:" + stack);
		amount.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		addComp(panel1, amount, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE); // Set the stack top
																								// right

		Box cards = Box.createHorizontalBox(); // Box for the cards

		button1 = new JButton(); // Card 1
		button2 = new JButton(); // Card 2
		button3 = new JButton(); // Card 3
		button4 = new JButton(); // Card 4
		button5 = new JButton(); // Card 5

		swap = new JButton("Draw Cards"); // Swap cards
		newDeal = new JButton("New Round"); // New round

		newDeal.setEnabled(false); // Disable that initially

		button1.setIcon(new ImageIcon("src/PNG/Blank.gif")); // Blanks cards to begin with
		button2.setIcon(new ImageIcon("src/PNG/Blank.gif"));
		button3.setIcon(new ImageIcon("src/PNG/Blank.gif"));
		button4.setIcon(new ImageIcon("src/PNG/Blank.gif"));
		button5.setIcon(new ImageIcon("src/PNG/Blank.gif"));

		buttonTransparent(button1); // Make sure only the cards show
		buttonTransparent(button2);
		buttonTransparent(button3);
		buttonTransparent(button4);
		buttonTransparent(button5);

		button1.addActionListener(this); // Add Action Listeners
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		swap.addActionListener(this);
		newDeal.addActionListener(this);

		cards.add(button1); // Add the cards to the box group
		cards.add(button2);
		cards.add(button3);
		cards.add(button4);
		cards.add(button5);

		cards.setBorder(BorderFactory.createTitledBorder("Cards"));
		addComp(panel1, cards, 0, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE); // Set cards in the
																								// middle

		payout = new JLabel("payout");
		payout.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		addComp(panel1, payout, 0, 11, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE); // Set payout bottom
																								// right

		hand = new JLabel("hand:" + rankingString);
		hand.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		addComp(panel1, hand, 0, 11, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE); // Set hand ranking bottom
																								// left

		addComp(panel1, swap, 0, 5, 5, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE); // Set swap bottom middle

		addComp(panel1, newDeal, 0, 5, 5, 1, GridBagConstraints.WEST, GridBagConstraints.NONE); // Set new round bottom
																								// left

		message = new JLabel("Click on the cards to keep!");
		addComp(panel1, message, 0, 9, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE); // Message at the
																									// bottom

		// Create everything

		createMenuBar();

		this.add(panel1);

		this.pack();

		this.setVisible(true);

		this.setLocationRelativeTo(null);

		this.setResizable(true);

		player = new Player(inputName, stack);

		play(); // Call the play method to start the game
	}

	/**
	 * Create the Menu Bar with all its components.
	 */

	private void createMenuBar() {

		menuBar = new JMenuBar(); // Create all the components
		mainMenu = new JMenu("Menu");

		newGame = new JMenuItem("New");

		// Allows for CTRL + N to start a new game

		Action ctrlN = new AbstractAction("New") {

			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
				player.setStack(100);
				play();
			}
		};

		ctrlN.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK)); // Bind
																												// the
																												// command
																												// with
																												// CRTL
																												// + N

		newGame.setAction(ctrlN);

		quit = new JMenuItem("Quit");

		// Allow for CTRL + Q to quit
		Action ctrlQ = new AbstractAction("Quit") {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};

		ctrlQ.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK)); // Bind
																												// the
																												// command

		quit.setAction(ctrlQ);

		mainMenu.add(newGame); // Add all the elements
		mainMenu.add(quit);

		menuBar.add(mainMenu);
		setJMenuBar(menuBar);

	}

	/**
	 * Runs the first half of the games functionality. This method collects a bid
	 * from the user, takes it from their stack, creates and shuffles a Deck and
	 * deals 5 cards to the user
	 */

	private void play() {

		if (player.getStack() <= 0) {
			endGame(); // If the user runs out of money end the game
		} else {

			setBlank(player); // Set the cards to blanks

			payout.setText("payout:0"); // Set the payout to default

			deck.resetDeck(); // Create the deck
			deck.Shuffle();

			if (!player.emptyHand()) {
				player.clearHand();
			}

			// Gets a bid from the user and repeats until a proper integer bet is made
			// Only when the three tests are passed can the user continue
			while (!test1 || !test2 || !test3) {

				try {
					test1 = true;
					inputBid = Integer.parseInt(JOptionPane.showInputDialog("Enter a bet(1-5):"));
				}

				// Get an integer
				catch (Exception e) {
					test1 = false;
					JOptionPane.showMessageDialog(this, "Please enter an integer.", "Inane error",
							JOptionPane.ERROR_MESSAGE);
				}

				// Get an integer 1-5
				if (inputBid > 5 || inputBid < 1) {
					JOptionPane.showMessageDialog(this, "Please make a bet, 1-5.", "Inane error",
							JOptionPane.ERROR_MESSAGE);
					test2 = false;
				} else {
					test2 = true;
				}

				// If the user has less than 5 but more than 0, ensure they cannot go negative
				if (inputBid > player.getStack()) {
					JOptionPane.showMessageDialog(this, "Bet is larger than stack.", "Inane error",
							JOptionPane.ERROR_MESSAGE);
					test3 = false;
				} else {
					test3 = true;
				}

			}

			setBid(inputBid); // Set the text

			player.makeBet(inputBid); // Place the bid

			setStack(player.getStack()); // Change the users stack based on the bet

			for (int i = 0; i < 5; i++) {
				player.addCard(i, deck.draw()); // Deal the cards
			}

			player.orderCards();

			setButtons(player); // Make the cards visible

			r = new Ranking(player);

			hand.setText(r.getRankingUI(player)); // Get the initial value of their hand

			test1 = false; // Reset all the tests
			test2 = false;
			test3 = false;
		}

	}

	/**
	 * Runs the second half of the games functionality. This method swaps the cards
	 * that have been clicked and pays the user based on the ranking of their hand
	 */

	private void play2() {

		// Check each one of the booleans associated with the cards, if they were not
		// clicked,
		// do not save them and swap them instead.
		if (!bTest1) {
			swap(player, 0);
		}

		if (!bTest2) {
			swap(player, 1);
		}

		if (!bTest3) {
			swap(player, 2);
		}

		if (!bTest4) {
			swap(player, 3);
		}

		if (!bTest5) {
			swap(player, 4);
		}

		setButtons(player); // Set the new cards

		player.orderCards();

		r = new Ranking(player);

		hand.setText(r.getRankingUI(player)); // Get the ranking of the hand

		pay = new Payout();

		payout.setText("payout:" + pay.getPayout(player)); // Pay the player

		amount.setText("stack:" + player.getStack());

		if (player.getStack() <= 0) {
			endGame(); // If the bust then end the game
		}
	}

	/**
	 * Swaps the players card
	 * 
	 * @param player The player who's cards will be swapped
	 * 
	 * @param pos    The postion of the card to be swapped
	 */

	public void swap(Player player, int pos) {
		player.setCard(pos, deck.draw());
	}

	/**
	 * Ends the game buy producing a dialog that asks the user to start a new game
	 * 
	 * It also disables all the buttons and sets the cards to blanks
	 */
	private void endGame() {

		JOptionPane.showMessageDialog(this,
				"You are out of money. Please click \"new game\" " + " in the menu, or CTRL+N to play again.",
				"Inane error", JOptionPane.ERROR_MESSAGE);

		swap.setEnabled(false);
		newDeal.setEnabled(false);
		setBlank(player);

	}

	/**
	 * Resets the important counters and booleans of the game
	 */
	private void reset() {
		button1Count = 0;
		button2Count = 0;
		button3Count = 0;
		button4Count = 0;
		button5Count = 0;

		bTest1 = false;
		bTest2 = false;
		bTest3 = false;
		bTest4 = false;
		bTest5 = false;

		test1 = false;
		test2 = false;
		test3 = false;
	}

	/**
	 * Modifies and adds a component to a panel of the GridBagLayout
	 * 
	 * @param thePanel   The panel to which the component will be added
	 * 
	 * @param comp       The component to be added
	 * 
	 * @param xPos       The x position of the component
	 * 
	 * @param yPos       The y position of the component
	 * 
	 * @param compWidth  The width of the component
	 * 
	 * @param compHeight The height of the component
	 * 
	 * @param place      The placing of the component: WEST,CENTER,EAST,NORTH,SOUTH
	 * 
	 * @param stretch    The stretch of the component
	 *
	 */
	private void addComp(JPanel thePanel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight, int place,
			int stretch) {
		GridBagConstraints gridConstraints = new GridBagConstraints();

		gridConstraints.gridx = xPos;

		gridConstraints.gridy = yPos;

		gridConstraints.gridwidth = compWidth;

		gridConstraints.gridheight = compHeight;

		gridConstraints.weightx = 100;

		gridConstraints.weighty = 100;

		gridConstraints.insets = new Insets(5, 5, 5, 5);

		gridConstraints.anchor = place;

		gridConstraints.fill = stretch;

		thePanel.add(comp, gridConstraints);

	}

	/**
	 * Gets rid of the borders around the cards
	 * 
	 * @param button The button to be modified
	 */
	private void buttonTransparent(JButton button) {
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
	}

	/**
	 * Adds the borders around the cards
	 * 
	 * @param button The button to be modified
	 */
	private void buttonVisible(JButton button) {
		button.setOpaque(true);
		button.setContentAreaFilled(true);
		button.setBorderPainted(true);
	}

	/**
	 * Creates a border around a button
	 * 
	 * @param button    The button to be modified
	 * 
	 * @param color     The color of the new border
	 * 
	 * @param thickenss The thickness of the new border
	 */
	private void addBorder(JButton button, Color color, int thickness) {
		Border border = new LineBorder(color, thickness);

		button.setBorder(border);
	}

	/**
	 * Sets the bet text
	 * 
	 * @param bet The size of the bet
	 */
	private void setBid(int bet) {
		bid.setText(("bid:" + bet));
	}

	/**
	 * Adds the borders around the cards
	 * 
	 * @param stack The size of the stack
	 */
	private void setStack(int stack) {
		amount.setText(("stack:" + stack));
	}

	/**
	 * Converts a Card object into a string that can be used. It will be the first
	 * letter of the card plus the value
	 * 
	 * @param card The card to converted to a string
	 * 
	 * @return The string of the card.
	 */
	public String cardConvert(Card card) {
		String suit = card.getSuit();
		String firstC = suit.substring(0, 1);
		String image = firstC + "" + card.getValue();
		return image;
	}

	/**
	 * Grabs the cards from the player, gets their respective Image Icon and sets
	 * them as the images for the buttons.
	 * 
	 * @param player The player so the card objects can be used
	 */
	public void setButtons(Player player) {
		String card1 = this.cardConvert(player.getCard1());
		String card2 = this.cardConvert(player.getCard2());
		String card3 = this.cardConvert(player.getCard3());
		String card4 = this.cardConvert(player.getCard4());
		String card5 = this.cardConvert(player.getCard5());

		button1.setIcon(new ImageIcon("src/PNG/" + card1 + ".gif"));
		button2.setIcon(new ImageIcon("src/PNG/" + card2 + ".gif"));
		button3.setIcon(new ImageIcon("src/PNG/" + card3 + ".gif"));
		button4.setIcon(new ImageIcon("src/PNG/" + card4 + ".gif"));
		button5.setIcon(new ImageIcon("src/PNG/" + card5 + ".gif"));
	}

	/**
	 * Sets all the cards to blank
	 * 
	 * @param player The player
	 */
	private void setBlank(Player player) {
		button1.setIcon(new ImageIcon("src/PNG/blank.gif"));
		button2.setIcon(new ImageIcon("src/PNG/blank.gif"));
		button3.setIcon(new ImageIcon("src/PNG/blank.gif"));
		button4.setIcon(new ImageIcon("src/PNG/blank.gif"));
		button5.setIcon(new ImageIcon("src/PNG/blank.gif"));
	}

	/**
	 * This is the method for the Action Listeners which handles all the different
	 * actions when the cards or buttons are clicked.
	 * 
	 * @param e The Action Event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {

			button1Count++;

			// If clicked set the border to green
			// If clicked again get rid of the border and so on
			if (button1Count % 2 == 1) {
				buttonVisible(button1);
				addBorder(button1, Color.green, 8);
				bTest1 = true;
			} else {
				buttonTransparent(button1);
				bTest1 = false;
			}
		}

		if (e.getSource() == button2) {

			button2Count++;

			// If clicked set the border to green
			// If clicked again get rid of the border and so on
			if (button2Count % 2 == 1) {
				buttonVisible(button2);
				addBorder(button2, Color.green, 8);
				bTest2 = true;
			} else {
				buttonTransparent(button2);
				bTest2 = false;
			}
		}

		if (e.getSource() == button3) {

			button3Count++;

			// If clicked set the border to green
			// If clicked again get rid of the border and so on
			if (button3Count % 2 == 1) {
				buttonVisible(button3);
				addBorder(button3, Color.green, 8);
				bTest3 = true;
			} else {
				buttonTransparent(button3);
				bTest3 = false;
			}
		}

		if (e.getSource() == button4) {
			button4Count++;

			// If clicked set the border to green
			// If clicked again get rid of the border and so on
			if (button4Count % 2 == 1) {
				buttonVisible(button4);
				addBorder(button4, Color.green, 8);
				bTest4 = true;
			} else {
				buttonTransparent(button4);
				bTest4 = false;
			}
		}

		if (e.getSource() == button5) {
			button5Count++;

			// If clicked set the border to green
			// If clicked again get rid of the border and so on
			if (button5Count % 2 == 1) {
				buttonVisible(button5);
				addBorder(button5, Color.green, 8);
				bTest5 = true;
			} else {
				buttonTransparent(button5);
				bTest5 = false;
			}
		}

		if (e.getSource() == swap) {
			swap.setEnabled(false); // Disable the swap button
			newDeal.setEnabled(true); // Enable the new round button
			play2(); // Start the second phase of the game
		}

		if (e.getSource() == newDeal) {
			swap.setEnabled(true); // Enable the swap button
			newDeal.setEnabled(false); // Disable the new round button
			buttonTransparent(button1); // Get rid of all the borders
			buttonTransparent(button2);
			buttonTransparent(button3);
			buttonTransparent(button4);
			buttonTransparent(button5);

			reset(); // Reset the important variables

			play(); // play the game
		}

	}

}
