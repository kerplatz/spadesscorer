/**
 * TwoTeams.java
 * 
 * This class controls how a four handed game using 2 teams is played. It also
 * shows the lose and end game screens. The screens are:
 * 
 * Four Handed 2 Teams-
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

public class TwoTeams extends Frame implements ActionListener {

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

	Frame frame = new Frame();

	/**
	 * Default constructor for the class.
	 */
    public TwoTeams(Frame frameIn){
    	frame = frameIn;
    }

	/**
	 * Performs the assigned tasks when the corresponding button is pressed.
	 * 
	 * @param event The action that was triggered by a button press.
	 */
	public void actionPerformed(ActionEvent event) {
        //Performs this action when the Bidding button is pressed.
        if (event.getActionCommand().equals("bidding")) {
        	if (Utils.processScoring()) {
        		//Utils.calculateScores();
        		Utils.recordGameData();
        		Utils.advanceDealer();
        		Utils.clearBidsMade();
        		Utils.clearTricksTaken();
        	
        		//Determines if the game is won > 500 or lost < -200, otherwise
        		//game play continues.
        		if (Utils.isGameWon()) {
        			createEndGameWonScreen();
        		} else if (Utils.isGameLost()) {
        			createEndGameLostScreen();
        		} else {
            		Main.doBidding = true;
            		Main.doScoring = false;
            		frame.removeAll();
        			createBiddingScreen();
        		}
        	}
        }  

        //Performs this action when the Scoring button is pressed.
        if (event.getActionCommand().equals("scoring")) {
        	if (Utils.processBidding()) {
        		Utils.saveBidData();
        		Main.doBidding = false;
        		Main.doScoring = true;
        		frame.removeAll();
        		createScoringScreen();
        	}
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
     * 
     */
	public void createPlayGameScreen() {
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("4 handed 2 teams");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();
		
		//Makes all the needed buttons.
		buttonReturnMain = FrameUtils.makeButton("Main Menu", "returnMain", false);
		buttonReturnMain.addActionListener(this);
		buttonBidding = FrameUtils.makeButton(" Bidding ", "bidding", false);
		buttonBidding.addActionListener(this);

		//Add the buttons to the proper panels.
		lowerPanel.add(buttonBidding);
		lowerPanel.add(buttonReturnMain);

		middlePanel.setLayout(new GridBagLayout());

		// This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);

		frame.setVisible(true);
	}

	/**
	 * Creates the Bidding screen.
	 */
	public void createBiddingScreen() {
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("bidding");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();
		
		//Makes all the needed buttons.
		buttonReturnMain = FrameUtils.makeButton("Main Menu", "returnMain", false);
		buttonReturnMain.addActionListener(this);
		buttonScoring = FrameUtils.makeButton(" Scoring ", "scoring", false);
		buttonScoring.addActionListener(this);

		//Add the buttons to the proper panels.
		lowerPanel.add(buttonScoring);
		lowerPanel.add(buttonReturnMain);

		// This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);

		frame.setVisible(true);
	}

	/**
	 * Creates the Scoring screen.
	 */
	public void createScoringScreen() {
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("scoring");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();
		
		//Makes all the needed buttons.
		buttonReturnMain = FrameUtils.makeButton("Main Menu", "returnMain", false);
		buttonReturnMain.addActionListener(this);
		buttonBidding = FrameUtils.makeButton(" Scoring ", "bidding", false);
		buttonBidding.addActionListener(this);

		//Add the buttons to the proper panels.
		lowerPanel.add(buttonBidding);
		lowerPanel.add(buttonReturnMain);

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
