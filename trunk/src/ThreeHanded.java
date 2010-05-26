/**
 * ThreeHanded.java
 * 
 * This class controls how a three handed game is played. It also shows the
 * lose and end game screens. The screens are:
 * 
 * Three Handed-
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
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThreeHanded extends Frame implements ActionListener {

	/**
	 * Declare needed variables.
	 */
	private static final long serialVersionUID = 1L;
	
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
	public ThreeHanded(Frame frameIn) {
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
       			Scoring.calculateScores();
       			
        		//Determines if the game is won  or lost, otherwise game play
       			//continues.
        		if (Utils.isGameWon()) {
        			createEndGameWonScreen();
        		} else if (Utils.isGameLost()) {
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
   			Utils.recordGameData();
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
     * Creates the PlayGame screen for three handed play.
     */
	public void createPlayGameScreen() {
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("three handed");
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

		middlePanel.setLayout(new GridBagLayout());
		FrameUtils.makeLine1(middlePanel);
		FrameUtils.makeLine2(middlePanel);
		FrameUtils.makeLine3(middlePanel);
		FrameUtils.makeLine4(middlePanel);

		// This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);

		frame.setVisible(true);
	}

	/**
	 * 
	 */
	public void createEndGameLostScreen() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	public void createEndGameWonScreen() {
		// TODO Auto-generated method stub
		
	}
}
