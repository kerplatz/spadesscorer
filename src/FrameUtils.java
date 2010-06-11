/**FINISHED
 * FrameUtils.java
 * 
 * This class is responsible for all the helper methods in creating the frames
 * needed to run the application. This class does not perform any calculations or
 * keep and variable values.
 *
 * @author David Hoffman
 */

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameUtils {

	//Declare needed variables.
	static Label player1Name = new Label();
	static Label player2Name = new Label();
	static Label player3Name = new Label();
	static Label player4Name = new Label();
	static Label player1Score = new Label();
	static Label player2Score = new Label();
	static Label player3Score = new Label();
	static Label player4Score = new Label();
	static Label player1Bags = new Label();
	static Label player2Bags = new Label();
	static Label player3Bags = new Label();
	static Label player4Bags = new Label();
	static Label player1Sets = new Label();
	static Label player2Sets = new Label();
	static Label player3Sets = new Label();
	static Label player4Sets = new Label();
	static Label score = new Label("Score");
	static Label bags = new Label("Bags");
	static Label sets = new Label("Sets");

	static Choice player1Bid;
	static Choice player2Bid;
	static Choice player3Bid;
	static Choice player4Bid;
	static Choice player1TricksTaken;
	static Choice player2TricksTaken;
	static Choice player3TricksTaken;
	static Choice player4TricksTaken;

	/**
	 * Creates the upper panel of a screen.
	 * 
	 * @param label The title text of the upper panel.
	 * @return The upper panel.
	 */
	public static Panel makeUpperPanel(String label) {
		//Declare needed variables.
		Panel upper = new Panel();
		Label title = new Label(label.toUpperCase());
		
		//Set the look of the panel.
		title.setAlignment(Label.CENTER);
		title.setFont(new Font("arial", Font.BOLD, 20));
		title.setForeground(Main.labelColor);
		upper.setBackground(Main.backgroundColor);
		upper.add(title);
		
		return upper;
	}

	/**
	 * Creates the middle panel of a screen.
	 * 
	 * @return The middle panel.
	 */
	public static Panel makeMiddlePanel() {
		//Declare needed variables.
		Panel middle = new Panel();
		
		//Set the look of the panel.
		middle.setBackground(Main.backgroundColor);
		middle.setForeground(Main.textColor);
		
		return middle;
	}

	/**
	 * Creates the lower panel of a screen.
	 * 
	 * @return The lower panel.
	 */
	public static Panel makeLowerPanel() {
		//Declare needed variables.
		Panel lower = new Panel();
		
		//Set the look of the panel.
		lower.setBackground(Main.backgroundColor);
		lower.setForeground(Main.textColor);
		
		return lower;
	}

	/**
	 * Makes a button with the included parameters.
	 * 
	 * @param title What the button will have for a label.
	 * @param action What action the button will do.
	 * @param upper Whether or not the button is in the middle panel.
	 * @return The button.
	 */
	public static Button makeButton(String title, String action, boolean middleButton) {
		//Declare needed variables.
		Button button = new Button(title);
		
		//Set the look and action of the button.
		button.setActionCommand(action);
		if (middleButton) {
			button.setBackground(Main.bgMiddleButtonColor);
			button.setForeground(Main.fgMiddleButtonColor);
		} else {
			button.setBackground(Main.bgLowerButtonColor);
			button.setForeground(Main.fgLowerButtonColor);
		}

		return button;
	}

	/**
	 * This method creates a choice box for entering bid data.
	 * 
	 * @return A choice box with all the elements added.
	 */
	public static Choice makeBidList() {
		Choice lst = new Choice();
		
		lst.add("");
		lst.add("1");
		lst.add("2");
		lst.add("3");
		lst.add("4");
		lst.add("5");
		lst.add("6");
		lst.add("7");
		lst.add("8");
		lst.add("9");
		lst.add("10");
		lst.add("11");
		lst.add("12");
		lst.add("13");
		
		//Show when it is a three handed game.
		if (Main.isThreeHanded) {
			lst.add("14");
			lst.add("15");
			lst.add("16");
			lst.add("17");
		}
		
		//Show if a nil is allowed.
		if (Main.isNilAllowed) lst.add("nil");
		
		//Show if a double nil is allowed.
		if (Main.isDoubleNilAllowed) lst.add("dbl");
		
		return lst;
	}

	/**
	 * This method creates a choice box for entering tricks taken data.
	 * 
	 * @return A choice box with all the elements added.
	 */
	public static Choice makeTricksTakenList() {
		Choice lst = new Choice();
		
		lst.add("");
		lst.add("0");
		lst.add("1");
		lst.add("2");
		lst.add("3");
		lst.add("4");
		lst.add("5");
		lst.add("6");
		lst.add("7");
		lst.add("8");
		lst.add("9");
		lst.add("10");
		lst.add("11");
		lst.add("12");
		lst.add("13");
		
		//Show when it is a three handed game.
		if (Main.isThreeHanded) {
			lst.add("14");
			lst.add("15");
			lst.add("16");
			lst.add("17");
		}

		return lst;
	}

	/**
	 * This method creates a choice box for selecting which round to edit
	 * or view.
	 * 
	 * @return A choice box with all the rounds added.
	 */
	public static Choice makeRoundList() {
		Choice lst = new Choice();
		
		for (int i = 0; i < Main.round + 1; i++) {
			Integer numb = new Integer(i);
			lst.add(numb.toString());
		}
		
		//Set the selection to the current round since it is the likely
		//round to be edited.
		lst.select(Main.round);
		
		return lst;
	}

	/**
	 * This method creates a choice box for selecting the players.
	 * 
	 * @return A choice box with all the players added.
	 */
	public static Choice makePlayerList() {
		Choice lst = new Choice();
		
		lst.add("");
		lst.add("Other");
		
		for (int i = 0; i < Main.names.size(); i++) {
			lst.add((String) Main.names.get(i));
		}
		
		return lst;
	}

	/**
	 * This method creates a choice box for selecting a current player.
	 * 
	 * @return A choice box with all the current players added.
	 */
	public static Choice makeCurrentPlayerList() {
		Choice lst = new Choice();
		
		//Determine the size of the list.
		if (Main.isThreeHanded) {
			lst.add(Main.playerOne.player);
			lst.add(Main.playerTwo.player);
			lst.add(Main.playerThree.player);
		} else {
			lst.add(Main.playerOne.player);
			lst.add(Main.playerTwo.player);
			lst.add(Main.playerThree.player);
			lst.add(Main.playerFour.player);
		}
		
		return lst;
	}

	/**
	 * This method creates a choice box for selecting the bag value.
	 * 
	 * @return A choice box with all the bag values added.
	 */
	public static Choice makeBagList() {
		Choice lst = new Choice();
		
		lst.add("1");
		lst.add("2");
		
		return lst;
	}
	
	/**
	 * This method places the object on the screen at these relative positions
	 * with a 5 pixel padding around it.
	 * 
	 * @param gridx The relative column.
	 * @param gridy The relative row.
	 * 
	 * @return The GridBagConstraints Object that describes this relative location.
	 */
	public static GridBagConstraints gbLayoutNormal(int gridx, int gridy) {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = gridx;
	    gridBagConstraints.gridy = gridy;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
	    
	    return gridBagConstraints;
	}

	/**
	 * This method places the object on the screen at these relative positions
	 * with no padding. Uses a WEST alignment.
	 * 
	 * @param gridx The relative column.
	 * @param gridy The relative row.
	 * 
	 * @return The GridBagConstraints Object that describes this relative location.
	 */
	public static GridBagConstraints gbLayoutWest(int gridx, int gridy) {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = gridx;
	    gridBagConstraints.gridy = gridy;
	    gridBagConstraints.anchor = GridBagConstraints.WEST;
	    
	    return gridBagConstraints;
	}

	/**
	 * This method places the object on the screen at these relative positions
	 * with no padding. Uses an EAST alignment.
	 * 
	 * @param gridx The relative column.
	 * @param gridy The relative row.
	 * 
	 * @return The GridBagConstraints Object that describes this relative location.
	 */
	public static GridBagConstraints gbLayoutEast(int gridx, int gridy) {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = gridx;
	    gridBagConstraints.gridy = gridy;
	    gridBagConstraints.anchor = GridBagConstraints.EAST;
	    
	    return gridBagConstraints;
	}

	/**
	 * This method places the object on the screen at these relative positions
	 * with more padding on the bottom.
	 * 
	 * @param gridx The relative column.
	 * @param gridy The relative row.
	 * 
	 * @return The GridBagConstraints Object that describes this relative location.
	 */
	public static GridBagConstraints gbLayoutPaddingBottom(int gridx, int gridy) {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = gridx;
	    gridBagConstraints.gridy = gridy;
	    gridBagConstraints.insets = new Insets(5, 5, 40, 5);
	    
	    return gridBagConstraints;
	}
	
	/**
	 * Shows a dialog box with the given message.
	 */
	public static void showDialogBox(String message) {
		final Dialog dialog;
		Frame window = new Frame();
		
		//Create a modal dialog.
		dialog = new Dialog(window, "ATTENTION: Please do this.", true);
		dialog.setLocation(5, 100);
		
		//Use a flow layout.
		dialog.setLayout(new FlowLayout());
	    
		//WindowListener for when the Window Close button is pressed.
		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// Hide dialog
				dialog.setVisible(false);
			}
		});
		
		//Create an OK button.
		Button ok = new Button("OK");
		ok.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent event) {
				// Hide dialog
				dialog.setVisible(false);
			}
		});
		
		dialog.add(new Label(message));
		dialog.add(ok);
		
		//Show dialog.
		dialog.pack();
		dialog.setVisible(true);
	}

	/**
	 * This method sets the display colors to ISU.
	 */
	public static void setISUColors() {
		Main.backgroundColor = new Color(255, 255, 0);
		Main.labelColor = new Color(255, 0 ,0);
		Main.textColor = new Color(0, 0, 0);
		Main.fgMiddleButtonColor = new Color(255, 255, 255); 
		Main.bgMiddleButtonColor = new Color(255, 0, 0);
		Main.fgLowerButtonColor = new Color(255, 255, 255);
		Main.bgLowerButtonColor = new Color(255, 0 ,0);
		Main.fgTextHighlightedColor = new Color(255, 255, 255);
		Main.bgTextHighlightedColor = new Color(0, 0, 255);
	}

	/**
	 * This method sets the display colors to U of Iowa.
	 */
	public static void setIowaColors() {
		Main.backgroundColor = new Color(255, 255, 0);
		Main.labelColor = new Color(0, 0, 0);
		Main.textColor = new Color(0, 0, 0);
		Main.fgMiddleButtonColor = new Color(255, 255, 255);
		Main.bgMiddleButtonColor = new Color(0, 0, 0);
		Main.fgLowerButtonColor = new Color(255, 255, 255);
		Main.bgLowerButtonColor = new Color(0, 0, 0); 
		Main.fgTextHighlightedColor = new Color(255, 255, 255);
		Main.bgTextHighlightedColor = new Color(0, 0, 255);
	}

	/**
	 * This method sets the display colors to UNI.
	 */
	public static void setUNIColors() {
		Main.backgroundColor = new Color(255, 255, 0);
		Main.labelColor = new Color(160, 32, 240);
		Main.textColor = new Color(0, 0, 0);
		Main.fgMiddleButtonColor = new Color(255, 255, 255); 
		Main.bgMiddleButtonColor = new Color(160, 32, 240);
		Main.fgLowerButtonColor = new Color(255, 255, 255);
		Main.bgLowerButtonColor = new Color(160, 32, 240);
		Main.fgTextHighlightedColor = new Color(255, 255, 255);
		Main.bgTextHighlightedColor = new Color(0, 0, 255);
	}

	/**
	 * This method creates the first line displayed in the middle panel,
	 * it contains the Headings for the columns.
	 * 
	 * @param panel The panel that the items are added to.
	 */
	public static void makeLine1(Panel panel) {
		Label name = new Label("Name");
		Label bid = new Label("Bid");
		Label tricks = new Label("Tricks");
		Label score = new Label("Score");
		
		name.setForeground(Main.labelColor);
		bid.setForeground(Main.labelColor);
		tricks.setForeground(Main.labelColor);
		score.setForeground(Main.labelColor);
		
		name.setFont(new Font("arial", Font.BOLD, 12));
		bid.setFont(new Font("arial", Font.BOLD, 12));
		tricks.setFont(new Font("arial", Font.BOLD, 12));
		score.setFont(new Font("arial", Font.BOLD, 12));

		panel.add(name, gbLayoutNormal(0, 1));
		panel.add(bid, gbLayoutNormal(1, 1));
		panel.add(tricks, gbLayoutNormal(2, 1));
		panel.add(score, gbLayoutWest(3, 1));
	}

	/**
	 * This method creates the second line displayed in the middle panel.
	 * it contains the Player1 information.
	 * 
	 * @param panel The panel that the items are added to.
	 */
	public static void makeLine2(Panel panel) {
		player1Name.setText(Main.player1);
		player1Bid = makeBidList();
		player1Score.setText(Main.player1Score);
		
		//Create the needed tricks taken choice box.
		player1TricksTaken = makeTricksTakenList();
		
		//Show the values of the Choice boxes.
		player1Bid.select(Main.player1Bid);
		player1TricksTaken.select(Main.player1TricksTaken);
		
		panel.add(player1Name, gbLayoutNormal(0, 2));
		panel.add(player1Bid, gbLayoutNormal(1, 2));
		panel.add(player1TricksTaken, gbLayoutNormal(2, 2));
		panel.add(player1Score, gbLayoutNormal(3, 2));
		
		//Set Choice boxes to not editable when game has not started.
		if (!Main.isGameStarted) {
			player1Bid.setEnabled(false);
			player1TricksTaken.setEnabled(false);
		}
		
		//Show if player is the dealer.
		if (Main.dealerIsPlayer1) {
			player1Name.setForeground(Main.bgTextHighlightedColor);
			player1Name.setFont(new Font("arial", Font.BOLD, 12));
		} else {
			player1Name.setForeground(Main.textColor);
			player1Name.setFont(new Font("arial", Font.PLAIN, 12));
		}
		
		//Set TricksTaken Choice boxes to not editable when bidding.
		if (Main.doBidding) player1TricksTaken.setEnabled(false);
		
		//Set Bid Choice boxes to not editable when scoring.
		if (Main.doScoring) player1Bid.setEnabled(false);
	}

	/**
	 * This method creates the third line displayed in the middle panel.
	 * it contains the Player2 information.
	 *
	 * @param panel The panel that the items are added to.
	 */
	public static void makeLine3(Panel panel) {
		player2Name.setText(Main.player2);
		player2Bid = makeBidList();
		player2Score.setText(Main.player2Score);
		
		//Create the needed tricks taken choice box.
		player2TricksTaken = makeTricksTakenList();
		
		//Show the values of the Choice boxes.
		player2Bid.select(Main.player2Bid);
		player2TricksTaken.select(Main.player2TricksTaken);
		
		panel.add(player2Name, gbLayoutNormal(0, 3));
		panel.add(player2Bid, gbLayoutNormal(1, 3));
		panel.add(player2TricksTaken, gbLayoutNormal(2, 3));
		panel.add(player2Score, gbLayoutNormal(3, 3));
		
		//Set Choice boxes to not editable when game has not started.
		if (!Main.isGameStarted) {
			player2Bid.setEnabled(false);
			player2TricksTaken.setEnabled(false);
		}
		
		//Show if player is the dealer.
		if (Main.dealerIsPlayer2) {
			player2Name.setForeground(Main.bgTextHighlightedColor);
			player2Name.setFont(new Font("arial", Font.BOLD, 12));
		} else {
			player2Name.setForeground(Main.textColor);
			player2Name.setFont(new Font("arial", Font.PLAIN, 12));
		}
		
		//Set TricksTaken Choice boxes to not editable when bidding.
		if (Main.doBidding) player2TricksTaken.setEnabled(false);
		
		//Set Bid Choice boxes to not editable when scoring.
		if (Main.doScoring) player2Bid.setEnabled(false);
	}

	/**
	 * This method creates the forth line displayed in the middle panel.
	 * it contains the Player3 information.
	 *
	 * @param panel The panel that the items are added to.
	 */
	public static void makeLine4(Panel panel) {
		player3Name.setText(Main.player3);
		player3Bid = makeBidList();
		player3Score.setText(Main.player3Score);
		
		//Create the needed tricks taken choice box.
		player3TricksTaken = makeTricksTakenList();
		
		//Show the values of the Choice boxes.
		player3Bid.select(Main.player3Bid);
		player3TricksTaken.select(Main.player3TricksTaken);
		
		panel.add(player3Name, gbLayoutNormal(0, 4));
		panel.add(player3Bid, gbLayoutNormal(1, 4));
		panel.add(player3TricksTaken, gbLayoutNormal(2, 4));
		panel.add(player3Score, gbLayoutNormal(3, 4));
		
		//Set Choice boxes to not editable when game has not started.
		if (!Main.isGameStarted) {
			player3Bid.setEnabled(false);
			player3TricksTaken.setEnabled(false);
		}
		
		//Show if player is the dealer.
		if (Main.dealerIsPlayer3) {
			player3Name.setForeground(Main.bgTextHighlightedColor);
			player3Name.setFont(new Font("arial", Font.BOLD, 12));
		} else {
			player3Name.setForeground(Main.textColor);
			player3Name.setFont(new Font("arial", Font.PLAIN, 12));
		}
		
		//Set TricksTaken Choice boxes to not editable when bidding.
		if (Main.doBidding) player3TricksTaken.setEnabled(false);
		
		//Set Bid Choice boxes to not editable when scoring.
		if (Main.doScoring) player3Bid.setEnabled(false);
	}

	/**
	 * This method creates the fifth line displayed in the middle panel.
	 * it contains the Player4 information.
	 *
	 * @param panel The panel that the items are added to.
	 */
	public static void makeLine5(Panel panel) {
		player4Name.setText(Main.player4);
		player4Bid = makeBidList();
		player4Score.setText(Main.player4Score);
		
		//Create the needed tricks taken choice box.
		player4TricksTaken = makeTricksTakenList();
		
		//Show the values of the Choice boxes.
		player4Bid.select(Main.player4Bid);
		player4TricksTaken.select(Main.player4TricksTaken);
		
		panel.add(player4Name, gbLayoutNormal(0, 5));
		panel.add(player4Bid, gbLayoutNormal(1, 5));
		panel.add(player4TricksTaken, gbLayoutNormal(2, 5));
		panel.add(player4Score, gbLayoutNormal(3, 5));
		
		//Set Choice boxes to not editable when game has not started.
		if (!Main.isGameStarted) {
			player4Bid.setEnabled(false);
			player4TricksTaken.setEnabled(false);
		}
		
		//Show if player is the dealer.
		if (Main.dealerIsPlayer4) {
			player4Name.setForeground(Main.bgTextHighlightedColor);
			player4Name.setFont(new Font("arial", Font.BOLD, 12));
		} else {
			player4Name.setForeground(Main.textColor);
			player4Name.setFont(new Font("arial", Font.PLAIN, 12));
		}
		
		//Set TricksTaken Choice boxes to not editable when bidding.
		if (Main.doBidding) player4TricksTaken.setEnabled(false);
		
		//Set Bid Choice boxes to not editable when scoring.
		if (Main.doScoring) player4Bid.setEnabled(false);
	}
	
	/**
	 * Creates a line for the end game screen that is a label for winner
	 * or loser and has the column headers.
	 *
	 * @param panel The panel that the items are added to.
	 * @param label The label that describes what follows on the next line(s).
	 */
	public static void makeEndGameLine1(Panel panel, Label label) {
		label.setFont(new Font("arial", Font.BOLD, 12));
		label.setForeground(Main.bgTextHighlightedColor);
		panel.add(label, gbLayoutNormal(0, 0));
		panel.add(score, gbLayoutNormal(1, 0));
		panel.add(bags, gbLayoutNormal(2, 0));
		panel.add(sets, gbLayoutNormal(3, 0));
	}
	
	/**
	 * Creates the Player stats line for a given player.
	 *
	 * @param panel The panel that the items are added to.
	 * @param player The player that the items pertain to.
	 */
	public static void makeEndGameLine2(Panel panel, Player player) {
		player1Name.setText(player.player);
		player1Name.setForeground(Main.labelColor);
		player1Name.setFont(new Font("arial", Font.BOLD, 12));
		player1Score.setText(player.score);
		player1Bags.setText(player.bags);
		player1Sets.setText(player.calculateTimesSet());
		
		panel.add(player1Name, gbLayoutNormal(0, 1));
		panel.add(player1Score, gbLayoutNormal(1, 1));
		panel.add(player1Bags, gbLayoutNormal(2, 1));
		panel.add(player1Sets, gbLayoutNormal(3, 1));
	}
	
	/**
	 * Creates a line for the end game screen that is a label for winner
	 * or loser and has the column headers.
	 *
	 * @param panel The panel that the items are added to.
	 * @param label The label that describes what follows on the next line(s).
	 */
	public static void makeEndGameLine3(Panel panel, Label label) {
		label.setFont(new Font("arial", Font.BOLD, 12));
		label.setForeground(Main.bgTextHighlightedColor);
		panel.add(label, gbLayoutNormal(0, 2));
		panel.add(score, gbLayoutNormal(1, 2));
		panel.add(bags, gbLayoutNormal(2, 2));
		panel.add(sets, gbLayoutNormal(3, 2));
	}

	/**
	 * Creates the Player stats line for a given player.
	 *
	 * @param panel The panel that the items are added to.
	 * @param player The player that the items pertain to.
	 */
	public static void makeEndGameLine4(Panel panel, Player player) {
		player2Name.setText(player.player);
		player2Name.setForeground(Main.labelColor);
		player2Name.setFont(new Font("arial", Font.BOLD, 12));
		player2Score.setText(player.score);
		player2Bags.setText(player.bags);
		player2Sets.setText(player.calculateTimesSet());
		
		panel.add(player2Name, gbLayoutNormal(0, 3));
		panel.add(player2Score, gbLayoutNormal(1, 3));
		panel.add(player2Bags, gbLayoutNormal(2, 3));
		panel.add(player2Sets, gbLayoutNormal(3, 3));
	}

	/**
	 * Creates the Player stats line for a given player.
	 *
	 * @param panel The panel that the items are added to.
	 * @param player The player that the items pertain to.
	 */
	public static void makeEndGameLine5(Panel panel, Player player) {
		player3Name.setText(player.player);
		player3Name.setForeground(Main.labelColor);
		player3Name.setFont(new Font("arial", Font.BOLD, 12));
		player3Score.setText(player.score);
		player3Bags.setText(player.bags);
		player3Sets.setText(player.calculateTimesSet());
		
		panel.add(player3Name, gbLayoutNormal(0, 4));
		panel.add(player3Score, gbLayoutNormal(1, 4));
		panel.add(player3Bags, gbLayoutNormal(2, 4));
		panel.add(player3Sets, gbLayoutNormal(3, 4));
	}

	/**
	 * Creates the Player stats line for a given player.
	 *
	 * @param panel The panel that the items are added to.
	 * @param player The player that the items pertain to.
	 */
	public static void makeEndGameLine6(Panel panel, Player player) {
		player4Name.setText(player.player);
		player4Name.setForeground(Main.labelColor);
		player4Name.setFont(new Font("arial", Font.BOLD, 12));
		player4Score.setText(player.score);
		player4Bags.setText(player.bags);
		player4Sets.setText(player.calculateTimesSet());
		
		panel.add(player4Name, gbLayoutNormal(0, 5));
		panel.add(player4Score, gbLayoutNormal(1, 5));
		panel.add(player4Bags, gbLayoutNormal(2, 5));
		panel.add(player4Sets, gbLayoutNormal(3, 5));
	}
}
