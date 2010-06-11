/**FINISHED
 * FourHanded.java
 * 
 * This class controls how a four handed game is played. It also shows the
 * lose and end game screens. The screens are:
 * 
 * Four Handed-
 * Shows the screen for getting game data, the current dealer, and the score. 
 * 
 * End Game Lost-
 * This screen shows who the loser is when the score is below the loseScore.
 * 
 * End Game Won-
 * This screen shows who the winner is when the score is below the winScore.
 * 
 * @author David Hoffman
 */

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FourHanded extends Frame implements ActionListener {

	/**
	 * Declare needed variables.
	 */
	private static final long serialVersionUID = 1L;
	
	Label winner = new Label("WINNER");
	Label winners = new Label("WINNERS");
	Label loser = new Label("LOSER");
	Label losers = new Label("LOSERS");
	Label round = new Label("ROUND");
	Label numbRound = new Label();

	Panel upperPanel;
	Panel middlePanel;
	Panel lowerPanel;
	
	Button buttonReturnMain;
	Button buttonScoring;
	Button buttonBidding;
	Button buttonBack;
	Button buttonPlay;

	Frame frame = new Frame();

	/**
	 * Default constructor for the class.
	 */
    public FourHanded(Frame frameIn){
    	frame = frameIn;
    }

	/**
	 * Performs the assigned tasks when the corresponding button is pressed.
	 * 
	 * @param event The action that was triggered by a button press.
	 */
	public void actionPerformed(ActionEvent event) {
        //Performs this action when the Scoring button is pressed.
        if (event.getActionCommand().equals("scoring")) {
        	if (Utils.processScoring()) {
        		Utils.recordGameData();
       			Utils.postScores();
       			
        		//Determines if the game is won > 500 or lost < -200,
        		//otherwise game play continues.
        		if (Utils.isGameWon()) {
        			Main.isGameStarted = false;
					frame.removeAll();
        			createEndGameWonScreen();
        		} else if (Utils.isGameLost()) {
        			Main.isGameStarted = false;
					frame.removeAll();
        			createEndGameLostScreen();
        		} else {
            		Main.doBidding = true;
            		Main.doScoring = false;
            		
           			Utils.clearBidsMade();
           			Utils.clearTricksTaken();
           			Utils.advanceDealer();
           			
            		frame.removeAll();
                	createPlayGameScreen();
        		}
        	}
        }  

        //Performs this action when the Bidding button is pressed.
        if (event.getActionCommand().equals("bidding")) {
        	if (Utils.processBidding()) {
        		Main.doBidding = false;
        		Main.doScoring = true;
        		
        		frame.removeAll();
            	createPlayGameScreen();
        	}
        }

        //Performs this action when the Play button is pressed.
        if (event.getActionCommand().equals("play")) {
        	//Prints game data for the first time when the game has not yet
        	//started. and increments the round to 1.
           	Main.isGameStarted = true;
           	Main.doBidding = true;
           	
        	frame.removeAll();
        	createPlayGameScreen();
        }  

        //Performs this action when the Back button is pressed.
        if (event.getActionCommand().equals("back")) {
    		Main.doBidding = true;
    		Main.doScoring = false;
    		
    		frame.removeAll();
        	createPlayGameScreen();
        }
        
        //Performs this action when the Return to Main button is pressed.
        if (event.getActionCommand().equals("endGame")) {
    		Utils.exportGameOptions();
    		Utils.exportPlayerFile(Main.playerOne);
    		Utils.exportPlayerFile(Main.playerTwo);
    		Utils.exportPlayerFile(Main.playerThree);
       		frame.removeAll();

        	Main game = new Main();
        	game.createMainMenuScreen();
        }
 
        //Performs this action when the ReturnMain button is pressed.
        if (event.getActionCommand().equals("returnMain")) {
       		Utils.saveBidData();
       		Utils.saveTricksTakenData();
       		frame.removeAll();

        	Main game = new Main();
        	game.createMainMenuScreen();
        }  
	}

	/**
     * Creates the PlayGame screen for a four handed game..
     */
	public void createPlayGameScreen() {
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("four handed");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();
		
		//Makes all the needed buttons.
		buttonReturnMain = FrameUtils.makeButton("Main Menu", "returnMain", false);
		buttonReturnMain.addActionListener(this);
		buttonBidding = FrameUtils.makeButton(" Bidding ", "bidding", false);
		buttonBidding.addActionListener(this);
		buttonScoring = FrameUtils.makeButton(" Scoring ", "scoring", false);
		buttonScoring.addActionListener(this);
		buttonBack = FrameUtils.makeButton(" Back ", "back", false);
		buttonBack.addActionListener(this);
		buttonPlay = FrameUtils.makeButton(" Play ", "play", false);
		buttonPlay.addActionListener(this);

		//Add the buttons to the proper panels.
		if (Main.isGameStarted) {
			if (Main.doBidding) lowerPanel.add(buttonBidding);
			if (Main.doScoring) {
				lowerPanel.add(buttonScoring);
				lowerPanel.add(buttonBack);
			}
		} else {
			lowerPanel.add(buttonPlay);
		}
		lowerPanel.add(buttonReturnMain);

		//Create the middle panel.
		middlePanel.setLayout(new GridBagLayout());
		
		//Shows which round is being played.
		numbRound.setText(Integer.toString(Main.round + 1));
		numbRound.setForeground(Main.labelColor);
		numbRound.setFont(new Font("arial", Font.BOLD, 12));
		middlePanel.add(round, FrameUtils.gbLayoutNormal(1, 0));
		middlePanel.add(numbRound, FrameUtils.gbLayoutNormal(2, 0));
		
		FrameUtils.makeLine1(middlePanel);
		FrameUtils.makeLine2(middlePanel);
		FrameUtils.makeLine3(middlePanel);
		FrameUtils.makeLine4(middlePanel);
		FrameUtils.makeLine5(middlePanel);

		// This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);

		frame.setVisible(true);
	}

	/**
	 * Creates the End Game Lost screen.
	 */
	public void createEndGameLostScreen() {
		Player theLoser = Utils.whoLostGame();
		
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("game lost");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();
		
		//Makes all the needed buttons.
		buttonReturnMain = FrameUtils.makeButton("Return to Main", "endGame", false);
		buttonReturnMain.addActionListener(this);

		//Add the buttons to the proper panels.
		lowerPanel.add(buttonReturnMain);

		//Create the middle panel.
		middlePanel.setLayout(new GridBagLayout());
		FrameUtils.makeEndGameLine1(middlePanel, loser);
		FrameUtils.makeEndGameLine2(middlePanel, theLoser);
		FrameUtils.makeEndGameLine3(middlePanel, winners);
		
		//Determine who the two winners are.
		if (Main.playerOne.equals(theLoser)){
			FrameUtils.makeEndGameLine4(middlePanel, Main.playerTwo);
			FrameUtils.makeEndGameLine5(middlePanel, Main.playerThree);
			FrameUtils.makeEndGameLine6(middlePanel, Main.playerFour);
		}
		if (Main.playerTwo.equals(theLoser)){
			FrameUtils.makeEndGameLine4(middlePanel, Main.playerOne);
			FrameUtils.makeEndGameLine5(middlePanel, Main.playerThree);
			FrameUtils.makeEndGameLine6(middlePanel, Main.playerFour);
		}
		if (Main.playerThree.equals(theLoser)){
			FrameUtils.makeEndGameLine4(middlePanel, Main.playerOne);
			FrameUtils.makeEndGameLine5(middlePanel, Main.playerTwo);
			FrameUtils.makeEndGameLine6(middlePanel, Main.playerFour);
		}
		if (Main.playerFour.equals(theLoser)){
			FrameUtils.makeEndGameLine4(middlePanel, Main.playerOne);
			FrameUtils.makeEndGameLine5(middlePanel, Main.playerTwo);
			FrameUtils.makeEndGameLine6(middlePanel, Main.playerThree);
		}
		
		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}

	/**
	 * Creates the End Game Won Screen.
	 */
	public void createEndGameWonScreen() {
		Player theWinner = Utils.whoWonGame();
		
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("game won");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();
		
		//Makes all the needed buttons.
		buttonReturnMain = FrameUtils.makeButton("Return to Main", "endGame", false);
		buttonReturnMain.addActionListener(this);

		//Add the buttons to the proper panels.
		lowerPanel.add(buttonReturnMain);

		//Create the middle panel.
		middlePanel.setLayout(new GridBagLayout());
		FrameUtils.makeEndGameLine1(middlePanel, winner);
		FrameUtils.makeEndGameLine2(middlePanel, theWinner);
		FrameUtils.makeEndGameLine3(middlePanel, losers);
		
		//Determine who the two winners are.
		if (Main.playerOne.equals(theWinner)){
			FrameUtils.makeEndGameLine4(middlePanel, Main.playerTwo);
			FrameUtils.makeEndGameLine5(middlePanel, Main.playerThree);
			FrameUtils.makeEndGameLine6(middlePanel, Main.playerFour);
		}
		if (Main.playerTwo.equals(theWinner)){
			FrameUtils.makeEndGameLine4(middlePanel, Main.playerOne);
			FrameUtils.makeEndGameLine5(middlePanel, Main.playerThree);
			FrameUtils.makeEndGameLine6(middlePanel, Main.playerFour);
		}
		if (Main.playerThree.equals(theWinner)){
			FrameUtils.makeEndGameLine4(middlePanel, Main.playerOne);
			FrameUtils.makeEndGameLine5(middlePanel, Main.playerTwo);
			FrameUtils.makeEndGameLine6(middlePanel, Main.playerFour);
		}
		if (Main.playerFour.equals(theWinner)){
			FrameUtils.makeEndGameLine4(middlePanel, Main.playerOne);
			FrameUtils.makeEndGameLine5(middlePanel, Main.playerTwo);
			FrameUtils.makeEndGameLine6(middlePanel, Main.playerThree);
		}
				
		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}
}
