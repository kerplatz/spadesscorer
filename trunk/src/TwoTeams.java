/**FINISHED
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
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwoTeams extends Frame implements ActionListener {

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
        if (event.getActionCommand().equals("scoring")) {
        	if (Utils.processScoringTeams()) {
        		Utils.recordGameData();
       			Utils.postScores();
       			
        		//Determines if the game is won > 500 or lost < -200,
        		//otherwise game play continues.
        		if (Utils.isGameWon()) {
 					frame.removeAll();
        			createEndGameWonScreen();
        		} else if (Utils.isGameLost()) {
 					frame.removeAll();
        			createEndGameLostScreen();
        		} else {
            		Main.doBidding = true;
            		Main.doScoring = false;
            		
           			Utils.clearBidsMade();
           			Utils.clearTricksTaken();
           			Utils.advanceDealer();
           			
           			//Reset nil bid.
           			Main.nilBidTeam1 = false;
           			Main.nilBidTeam2 = false;
           			
            		frame.removeAll();
                	createPlayGameScreen();
        		}
        	}
        }  

        //Performs this action when the Scoring button is pressed.
        if (event.getActionCommand().equals("bidding")) {
        	if (Utils.processBidding()) {
        		Main.doBidding = false;
        		Main.doScoring = true;
        		Main.nilBidTeam1 = false;
        		Main.nilBidTeam2 = false;
        		
        		if (FrameUtils.player1Bid.getSelectedItem() == "nil" ||
        				FrameUtils.player1Bid.getSelectedItem() == "dbl" ||
        				FrameUtils.player3Bid.getSelectedItem() == "nil" ||
        				FrameUtils.player3Bid.getSelectedItem() == "dbl") {
        			Main.nilBidTeam1 = true;
        		}
        		if (FrameUtils.player2Bid.getSelectedItem() == "nil" ||
        				FrameUtils.player2Bid.getSelectedItem() == "dbl" ||
        				FrameUtils.player4Bid.getSelectedItem() == "nil" ||
        				FrameUtils.player4Bid.getSelectedItem() == "dbl") {
        			Main.nilBidTeam2 = true;
        		}
        		
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
        	if (Utils.processBidding()) {
        		Main.doBidding = true;
        		Main.doScoring = false;
        		Main.nilBidTeam1 = false;
        		Main.nilBidTeam2 = false;
    		
        		if (FrameUtils.player1Bid.getSelectedItem() == "nil" ||
        				FrameUtils.player1Bid.getSelectedItem() == "dbl" ||
        				FrameUtils.player3Bid.getSelectedItem() == "nil" ||
        				FrameUtils.player3Bid.getSelectedItem() == "dbl") {
        			Main.nilBidTeam1 = true;
        		}
        		if (FrameUtils.player2Bid.getSelectedItem() == "nil" ||
        				FrameUtils.player2Bid.getSelectedItem() == "dbl" ||
        				FrameUtils.player4Bid.getSelectedItem() == "nil" ||
        				FrameUtils.player4Bid.getSelectedItem() == "dbl") {
        			Main.nilBidTeam2 = true;
        		}
    		
        		frame.removeAll();
        		createPlayGameScreen();
        	}
        }

        //Performs this action when the Return to Main button is pressed.
        if (event.getActionCommand().equals("endGame")) {
      		frame.removeAll();

        	Main game = new Main();
        	game.createExitScreen();
        }
 
        //Performs this action when the ReturnMain button is pressed.
        if (event.getActionCommand().equals("returnMain")) {
       		//Utils.saveBidData();
       		//Utils.saveTricksTakenData();
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
		upperPanel = FrameUtils.makeUpperPanel("four handed teams");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();
		
		//Makes all the needed buttons.
		buttonReturnMain = FrameUtils.makeButton("Main Menu", "returnMain", false);
		buttonReturnMain.addActionListener(this);
		buttonBidding = FrameUtils.makeButton("  Next  ", "bidding", false);
		buttonBidding.addActionListener(this);
		buttonScoring = FrameUtils.makeButton("End Round", "scoring", false);
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
		middlePanel.add(round, FrameUtils.gbLayoutTight(1, 0));
		middlePanel.add(numbRound, FrameUtils.gbLayoutTight(2, 0));
		
		FrameUtils.makeTeamsLine1(middlePanel);
		FrameUtils.makeTeamsLine2(middlePanel);
		FrameUtils.makeTeamsLine3(middlePanel);
		FrameUtils.makeTeamsLine4(middlePanel);
		FrameUtils.makeTeamsLine5(middlePanel);
		FrameUtils.makeTeamsLine6(middlePanel);
		FrameUtils.makeTeamsLine7(middlePanel);

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
		Team theLoser = Utils.whoLostGameTeam();
		
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
		FrameUtils.makeEndGameTeamsLine1(middlePanel, loser);
		FrameUtils.makeEndGameTeamsLine2(middlePanel, theLoser);
		FrameUtils.makeEndGameTeamsLine3(middlePanel, winners);
		
		//Determine who the two winners are.
		if (Main.teamOne.equals(theLoser)){
			FrameUtils.makeEndGameTeamsLine4(middlePanel, Main.teamTwo);
		}
		if (Main.teamTwo.equals(theLoser)){
			FrameUtils.makeEndGameTeamsLine4(middlePanel, Main.teamOne);
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
		Team theWinner = Utils.whoWonGameTeam();
		
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
		FrameUtils.makeEndGameTeamsLine1(middlePanel, winner);
		FrameUtils.makeEndGameTeamsLine2(middlePanel, theWinner);
		FrameUtils.makeEndGameTeamsLine3(middlePanel, losers);
		
		//Determine who the two winners are.
		if (Main.teamOne.equals(theWinner)){
			FrameUtils.makeEndGameTeamsLine4(middlePanel, Main.teamTwo);
		}
		if (Main.teamTwo.equals(theWinner)){
			FrameUtils.makeEndGameTeamsLine4(middlePanel, Main.teamOne);
		}
				
		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}
}
