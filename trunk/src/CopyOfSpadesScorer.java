/**
 * Scorer.java
 * 
 * Application to keep score in a Spades Game.
 * 
 * ToDo List -
 * Make another set of screens for 3 handed.
 * Create an edit mode;
 * Finish/perfect the toString() for saving game data.
 * 
 * @author David Hoffman
 * 
 */


import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CopyOfSpadesScorer extends Frame implements ActionListener {
	/**
	 * Declare variables.
	 */
	static final long serialVersionUID = 1L;
	
	int team1Score = 0;
	int team2Score = 0;
	int round = 0;
	int tricksTakenTeam1 = 0;
	int tricksTakenTeam2 = 0;
	int tricksTakenSouth = 0;
	int tricksTakenWest = 0;
	int tricksTakenNorth = 0;
	int tricksTakenEast = 0;
	int bidTeam1 = 0;
	int bidTeam2 = 0;
	int bidSouth = 0;
	int bidWest = 0;
	int bidNorth = 0;
	int bidEast = 0;
	int bagsTeam1 = 0;
	int bagsTeam2 = 0;
		
	boolean gameStarted = false;
	boolean gameWon = false;
	boolean doBidding = true;
	boolean doScoring = false;
	boolean middleButton = false;
	static boolean playersDone = false;
	static boolean dealerDone = false;
	static boolean dealerSouth = false;
	static boolean dealerWest = false;
	static boolean dealerNorth = false;
	static boolean dealerEast = false;
	boolean nilSouth = false;
	boolean nilWest = false;
	boolean nilNorth = false;
	boolean nilEast = false;
	
	String playerSouth = "";
	String playerWest = "";
	String playerNorth = "";
	String playerEast = "";
	String bidPlayerSouth = "";
	String bidPlayerWest = "";
	String bidPlayerNorth = "";
	String bidPlayerEast = "";
	String tricksPlayerSouth = "";
	String tricksPlayerWest = "";
	String tricksPlayerNorth = "";
	String tricksPlayerEast = "";
	String curDealer = "";
	String startDealer = "";
	
	TextField sPlayerTextField = new TextField(10);
	TextField wPlayerTextField = new TextField(10);
	TextField nPlayerTextField = new TextField(10);
	TextField ePlayerTextField = new TextField(10);
	TextField scoreTeam1TextField = new TextField(5);
	TextField scoreTeam2TextField = new TextField(5);
	TextField sPlayerBidTextField = new TextField(5);
	TextField wPlayerBidTextField = new TextField(5);
	TextField nPlayerBidTextField = new TextField(5);
	TextField ePlayerBidTextField = new TextField(5);
	TextField sPlayerTricksTextField = new TextField(5);
	TextField wPlayerTricksTextField = new TextField(5);
	TextField nPlayerTricksTextField = new TextField(5);
	TextField ePlayerTricksTextField = new TextField(5);
	
	Label sPlayerLabel = new Label("South Player");
	Label wPlayerLabel = new Label("West Player ");
	Label nPlayerLabel = new Label("North Player");
	Label ePlayerLabel = new Label("East Player ");
	Label sPlayerName = new Label();
	Label wPlayerName = new Label();
	Label nPlayerName = new Label();
	Label ePlayerName = new Label();
	Label score1 = new Label("Score");
	Label score2 = new Label("Score");
	Label team1 = new Label("Team 1");
	Label team2 = new Label("Team 2");
	Label bid = new Label("Bid");
	Label tricks = new Label("Tricks");
	Label winner = new Label("WINNER");
	Label loser = new Label("LOSER");
	Label teamWinner = new Label();
	Label teamLoser = new Label();
		
	Panel upperPanel;
	Panel middlePanel;
	Panel lowerPanel;
	
	Button buttonExit;
	Button buttonPlay;
	Button buttonSetup;
	Button buttonNewGame;
	Button buttonContGame;
	Button buttonEditGame;
	Button buttonViewGame;
	Button buttonSetupPlayers;
	Button buttonSelectDealer;
	Button buttonBidding;
	Button buttonScoring;
	Button buttonPlayGame;
	Button buttonReturnMain;
	Button buttonReturnSetup;
	Button buttonReturnPlayers;
	Button buttonReturnDealer;
	Button buttonClearPlayers;
	
	static CheckboxGroup dealerGroup = new CheckboxGroup();
	static Checkbox southDealer = new Checkbox("South Player", dealerGroup, false);
	static Checkbox westDealer = new Checkbox("West Player ", dealerGroup, false);
	static Checkbox northDealer = new Checkbox("North Player", dealerGroup, false);
	static Checkbox eastDealer = new Checkbox("East Player ", dealerGroup, false);
	
	GridBagConstraints gridBagConstraints;

    static CopyOfSpadesScorer frame = new CopyOfSpadesScorer();

	/**
	 * Default constructor for the class.
	 */
    public CopyOfSpadesScorer(){
		super("Spades Scorer");
		setBounds(50, 50, 240, 320);
	    
		//WindowListener for when the Window Close button is pressed. Will not work when
		//a game has been started to prevent accidental closure.
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//if (!gameStarted) Utils.exit(frame);
			}
		});
    }
	
	/**
	 * Performs the assigned tasks when the corresponding button is pressed.
	 * 
	 * @param event
	 */
	public void actionPerformed(ActionEvent event) {
        //Performs this action when the NewGame button is pressed.
        if (event.getActionCommand().equals("newGame")) {
        	frame.removeAll();
        	resetGame();
        	createSetupScreen();
        }
        
        //Performs this action when the ContinueGame button is pressed.
        if (event.getActionCommand().equals("continueGame")) {
        	frame.removeAll();
        	createPlayGameScreen();
        }
        
        //Performs this action when the EditGame button is pressed.
        if (event.getActionCommand().equals("editGame")) {
        	frame.removeAll();
        	createEditGameScreen();
        }
        
        //Performs this action when the ViewGame button is pressed.
        if (event.getActionCommand().equals("viewGame")) {
        	frame.removeAll();
        	createViewGameScreen();
        }

        //Performs this action when the SetupPlayers button is pressed.
        if (event.getActionCommand().equals("setupPlayers")) {
        	frame.removeAll();
        	createSetupPlayersScreen();
        }
        
        //Performs this action when the SelectDealer button is pressed.
        if (event.getActionCommand().equals("selectDealer")) {
        	frame.removeAll();
        	createSelectDealerScreen();
        }

        //Performs this action when the PlayGame button is pressed.
        if (event.getActionCommand().equals("playGame")) {
        	frame.removeAll();
        	createPlayGameScreen();
        }  

        //Performs this action when the ClearPlayers button is pressed.
        if (event.getActionCommand().equals("clearPlayers")) {
        	frame.removeAll();
        	clearPlayerNames();
        	createSetupPlayersScreen();
        }  

        //Performs this action when the Play button is pressed.
        if (event.getActionCommand().equals("play")) {
        	frame.removeAll();
    		
        	//Prints game data for the first time when the game has not yet
        	//started. and increments the round to 1.
        	if (!gameStarted) {
    			printGameData();
            	gameStarted = true;
        	}
        	
        	//Starts at the correct screen depending upon where the game
        	//was when it was suspended.
        	if (doBidding) createBiddingScreen();
        	if (doScoring) createScoringScreen();
        }  

        //Performs this action when the Bidding button is pressed.
        if (event.getActionCommand().equals("bidding")) {
        	if (processScoring()) {
        		calculateScores();
        		printGameData();
        		advanceDealer();
        		clearBidsMade();
        		clearTricksTaken();
        	
        		//Determines if the game is won > 500 or lost < -200, otherwise
        		//game play continues.
        		if (isGameWon()) {
        			createEndGameWonScreen();
        		} else if (isGameLost()) {
        			createEndGameLostScreen();
        		} else {
            		doBidding = true;
            		doScoring = false;
            		frame.removeAll();
        			createBiddingScreen();
        		}
        	}
        }  

        //Performs this action when the Scoring button is pressed.
        if (event.getActionCommand().equals("scoring")) {
        	if (processBidding()) {
        		saveBidData();
        		doBidding = false;
        		doScoring = true;
        		frame.removeAll();
        		createScoringScreen();
        	}
        }  

        //Performs this action when the ReturnDealer button is pressed.
        if (event.getActionCommand().equals("returnDealer")) {
        	if (isDealerSelectionDone()) {
        		dealerDone = true;
            	frame.removeAll();
             	createSetupScreen();
        	}
        }  

        //Performs this action when the ReturnPlayers button is pressed.
        if (event.getActionCommand().equals("returnPlayers")) {
        	if (isPlayersSetupDone()) {
        		playersDone = true;
            	frame.removeAll();
            	createSetupScreen();
        	}
        }  

        //Performs this action when the Setup button is pressed.
        if (event.getActionCommand().equals("setup")) {
        	frame.removeAll();
        	createSetupScreen();
        }  

        //Performs this action when the ReturnMain button is pressed.
        if (event.getActionCommand().equals("returnMain")) {
        	saveBidData();
        	saveTricksTakenData();
        	frame.removeAll();
        	createMainMenuScreen();
        }  

        //Performs this action when the Exit button is pressed.
        if (event.getActionCommand().equals("exit")) {
        	//Utils.exit(frame);
        }  
    }

	/**
	 * Creates the MainMenu screen.
	 */
	public void createMainMenuScreen() {
		//Create the 3 panel components of the screen.
		upperPanel = makeUpperPanel("main menu");
		middlePanel = makeMiddlePanel();
		lowerPanel = makeLowerPanel();
		
		//Makes all the needed buttons.
		buttonNewGame = makeButton("New Game", "newGame", true);
		buttonContGame = makeButton("Continue", "continueGame", true);
		buttonSetup = makeButton("  Setup  ", "setup", true);
		buttonEditGame = makeButton("Edit Game", "editGame", true);
		buttonViewGame = makeButton("View Game", "viewGame", true);
		buttonExit = makeButton("  Exit  ", "exit", false);
		
		//Add the buttons to the proper panels.
		lowerPanel.add(buttonExit);

		middlePanel.setLayout(new GridBagLayout());
		middlePanel.add(buttonNewGame, gbLayoutNormal(0, 0));
		middlePanel.add(buttonViewGame, gbLayoutNormal(0, 1));
		middlePanel.add(buttonEditGame, gbLayoutNormal(0, 2));
		middlePanel.add(buttonSetup, gbLayoutNormal(0, 3));
		middlePanel.add(buttonContGame, gbLayoutNormal(0, 4));

			
		//Adds these buttons to the middle panel only when the game has started.
		if (gameStarted) {
			buttonSetup.setVisible(true);
			buttonEditGame.setVisible(true);
			buttonViewGame.setVisible(true);
			buttonContGame.setVisible(true);
		} else {
			buttonSetup.setVisible(false);
			buttonEditGame.setVisible(false);
			buttonViewGame.setVisible(false);
			buttonContGame.setVisible(false);
		}
		
		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}

	/**
	 * Creates the Setup screen.
	 */
	public void createSetupScreen() {
		//Create the 3 panel components of the screen.
		upperPanel = makeUpperPanel("setup menu");
		middlePanel = makeMiddlePanel();
		lowerPanel = makeLowerPanel();
		
		//Make all the needed buttons.
		buttonSetupPlayers = makeButton("  Setup Players  ", "setupPlayers", true);
		buttonSelectDealer = makeButton("  Select Dealer  ", "selectDealer", true);
		buttonPlayGame = makeButton("Play Game", "playGame", true);
		buttonReturnMain = makeButton("  Return  ", "returnMain", false);
		
		//Add the buttons to the proper panels.
		lowerPanel.add(buttonReturnMain);
		
		middlePanel.setLayout(new GridBagLayout());
		middlePanel.add(buttonSetupPlayers, gbLayoutNormal(0, 0));
		middlePanel.add(buttonSelectDealer, gbLayoutNormal(0, 1));
		middlePanel.add(buttonPlayGame, gbLayoutNormal(0, 2));

		//Adds the DealerSelected button only after the players have been entered.
		if (playersDone) {
			buttonSelectDealer.setVisible(true);
		} else {
			buttonSelectDealer.setVisible(false);
		}
		
		//Adds the PlayGame button only after the other 2 screens are done.
		if (playersDone) {
			buttonPlayGame.setVisible(true);
		} else {
			buttonPlayGame.setVisible(false);
		}
		
		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}

	/**
	 * Creates the SetupPlayers screen.
	 */
	public void createSetupPlayersScreen() {
		//Create the components of the screen.
		upperPanel = makeUpperPanel("setup players");
		middlePanel = makeMiddlePanel();
		middlePanel.setLayout(new GridBagLayout());
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 0;
	    gridBagConstraints.gridy = 0;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		middlePanel.add(sPlayerLabel, gridBagConstraints);
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 0;
	    gridBagConstraints.gridy = 1;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
	    middlePanel.add(wPlayerLabel, gridBagConstraints);
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 0;
	    gridBagConstraints.gridy = 2;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		middlePanel.add(nPlayerLabel, gridBagConstraints);
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 0;
	    gridBagConstraints.gridy = 3;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		middlePanel.add(ePlayerLabel, gridBagConstraints);

	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 1;
	    gridBagConstraints.gridy = 0;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		middlePanel.add(sPlayerTextField, gridBagConstraints);
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 1;
	    gridBagConstraints.gridy = 1;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		middlePanel.add(wPlayerTextField, gridBagConstraints);
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 1;
	    gridBagConstraints.gridy = 2;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		middlePanel.add(nPlayerTextField, gridBagConstraints);
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 1;
	    gridBagConstraints.gridy = 3;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		middlePanel.add(ePlayerTextField, gridBagConstraints);

		showPreviousPlayerNames();
		
		buttonClearPlayers = makeButton("  Clear  ", "clearPlayers", false);
		buttonReturnPlayers = makeButton("  Return  ", "returnPlayers", false);
		lowerPanel = makeLowerPanel();
		lowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));
		lowerPanel.add(buttonClearPlayers);
		lowerPanel.add(buttonReturnPlayers);
		
		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}

	/**
	 * Creates the SelectDealer screen.
	 */
	public void createSelectDealerScreen() {
		//Create the needed player labels.
		southDealer.setLabel("   " + playerSouth);
		westDealer.setLabel("   " + playerWest);
		northDealer.setLabel("   " + playerNorth);
		eastDealer.setLabel("   " + playerEast);
		
		//Create the 3 panel components of the screen.
		upperPanel = makeUpperPanel("select dealer");
		middlePanel = makeMiddlePanel();
		lowerPanel = makeLowerPanel();
		
		//Makes all the needed buttons.
		buttonReturnDealer = makeButton("  Return  ", "returnDealer", false);

		//Adds the buttons to the proper panels.
		lowerPanel.add(buttonReturnDealer);

		middlePanel.setLayout(new GridBagLayout());
		middlePanel.add(southDealer, gbLayoutWest(0, 0));
		middlePanel.add(westDealer, gbLayoutWest(0, 1));
		middlePanel.add(northDealer, gbLayoutWest(0, 2));
		middlePanel.add(eastDealer, gbLayoutWest(0, 3));
		
		//Makes the selected dealer show is it was set already.
		Utils.showPreviousSelectedDealer();
		
		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}

	/**
	 * Creates the PlayGame screen.
	 */
	public void createPlayGameScreen() {
		sPlayerName.setText(playerSouth);
		wPlayerName.setText(playerWest);
		nPlayerName.setText(playerNorth);
		ePlayerName.setText(playerEast);
		
		upperPanel = makeUpperPanel("play game");
		middlePanel = makeMiddlePanel();
		middlePanel.setLayout(new GridBagLayout());

		makeLine1();
		makeLine2();
		makeLine3();
		makeLine4();
		makeLine5();
		makeLine6();
		makeLine7();
				
		buttonReturnSetup = makeButton("  Return  ", "returnSetup", false);
		buttonReturnMain = makeButton("  Return  ", "returnMain", false);
		buttonPlay = makeButton("  Play  ", "play", false);
		lowerPanel = makeLowerPanel();
		lowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));
		lowerPanel.add(buttonPlay);
		
		if (!gameStarted) {
			lowerPanel.add(buttonReturnSetup);
		} else {
			lowerPanel.add(buttonReturnMain);
		}
		
		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}

	/**
	 * Creates the Bidding screen.
	 */
	public void createBiddingScreen() {
		upperPanel = makeUpperPanel("bidding");
		middlePanel = makeMiddlePanel();
		middlePanel.setLayout(new GridBagLayout());

		makeLine1();
		makeLine2();
		makeLine3();
		makeLine4();
		makeLine5();
		makeLine6();
		makeLine7();
		
	    sPlayerBidTextField.setEditable(true);
	    wPlayerBidTextField.setEditable(true);
	    nPlayerBidTextField.setEditable(true);
	    ePlayerBidTextField.setEditable(true);
	    sPlayerTricksTextField.setEditable(false);
	    wPlayerTricksTextField.setEditable(false);
	    nPlayerTricksTextField.setEditable(false);
	    ePlayerTricksTextField.setEditable(false);
		
		buttonReturnMain = makeButton("  Return  ", "returnMain", false);
		buttonScoring = makeButton("  Scoring  ", "scoring", false);
		lowerPanel = makeLowerPanel();
		lowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));
		lowerPanel.add(buttonScoring);
		lowerPanel.add(buttonReturnMain);
		
		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}

	/**
	 * Creates the Scoring screen.
	 */
	public void createScoringScreen() {
		upperPanel = makeUpperPanel("scoring");
		middlePanel = makeMiddlePanel();
		middlePanel.setLayout(new GridBagLayout());

		makeLine1();
		makeLine2();
		makeLine3();
		makeLine4();
		makeLine5();
		makeLine6();
		makeLine7();

		sPlayerBidTextField.setEditable(false);
	    wPlayerBidTextField.setEditable(false);
	    nPlayerBidTextField.setEditable(false);
	    ePlayerBidTextField.setEditable(false);
	    sPlayerTricksTextField.setEditable(true);
	    wPlayerTricksTextField.setEditable(true);
	    nPlayerTricksTextField.setEditable(true);
	    ePlayerTricksTextField.setEditable(true);
	    
	    if (!nilSouth && !nilNorth) {
	    	sPlayerTricksTextField.setVisible(false);
	    }
	    if (!nilWest && !nilEast) {
	    	ePlayerTricksTextField.setVisible(false);
	    }
	    
		buttonReturnMain = makeButton("  Return  ", "returnMain", false);
		buttonBidding = makeButton("  Bidding  ", "bidding", false);
		lowerPanel = makeLowerPanel();
		lowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));
		lowerPanel.add(buttonBidding);
		lowerPanel.add(buttonReturnMain);
		
		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}

	/**
	 * Creates the EditGame screen.
	 */
	public void createEditGameScreen() {
		
	}

	/**
	 * Creates the ViewGame screen.
	 */
	public void createViewGameScreen() {
		
	}
	
	/**
	 * Creates the EndGame won screen.
	 */
	public void createEndGameWonScreen() {
		String theWinner = whoWonGame();
		String theLoser = "";
		String scoreWinner = "";
		String scoreLoser = "";
		
		//Determine who was the loser.
		if (theWinner.equals("Team 1")) {
			theLoser = "Team 2";
			scoreWinner = "" + team1Score;
			scoreLoser = "" + team2Score;
		} else {
			theLoser = "Team 1";
			scoreWinner = "" + team2Score;
			scoreLoser = "" + team1Score;
		}

		teamWinner.setText(theWinner);
		teamLoser.setText(theLoser);

		upperPanel = makeUpperPanel("game won");
		middlePanel = makeMiddlePanel();
		middlePanel.setLayout(new GridBagLayout());

		middlePanel.setLayout(new GridBagLayout());
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 0;
	    gridBagConstraints.gridy = 0;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
	    gridBagConstraints.anchor = GridBagConstraints.WEST;
		winner.setFont(new Font("arial", Font.BOLD, 16));
    	winner.setForeground(Color.blue);
		middlePanel.add(winner, gridBagConstraints);
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 0;
	    gridBagConstraints.gridy = 1;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
	    gridBagConstraints.anchor = GridBagConstraints.WEST;
	    middlePanel.add(score1, gridBagConstraints);
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 0;
	    gridBagConstraints.gridy = 2;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
	    gridBagConstraints.anchor = GridBagConstraints.WEST;
		middlePanel.add(score2, gridBagConstraints);
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 0;
	    gridBagConstraints.gridy = 3;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
	    gridBagConstraints.anchor = GridBagConstraints.WEST;
		loser.setFont(new Font("arial", Font.BOLD, 16));
    	loser.setForeground(Color.black);
		middlePanel.add(loser, gridBagConstraints);
		
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 1;
	    gridBagConstraints.gridy = 0;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
	    gridBagConstraints.anchor = GridBagConstraints.EAST;
		middlePanel.add(teamWinner, gridBagConstraints);
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 1;
	    gridBagConstraints.gridy = 1;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
	    gridBagConstraints.anchor = GridBagConstraints.EAST;
	    scoreTeam1TextField.setText(scoreWinner);
	    middlePanel.add(scoreTeam1TextField, gridBagConstraints);
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 1;
	    gridBagConstraints.gridy = 2;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
	    gridBagConstraints.anchor = GridBagConstraints.EAST;
	    scoreTeam2TextField.setText(scoreLoser);
		middlePanel.add(scoreTeam2TextField, gridBagConstraints);
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 1;
	    gridBagConstraints.gridy = 3;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
	    gridBagConstraints.anchor = GridBagConstraints.EAST;
		middlePanel.add(teamLoser, gridBagConstraints);

		buttonReturnMain = makeButton("  Return  ", "returnMain", false);
		lowerPanel = makeLowerPanel();
		lowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));
		lowerPanel.add(buttonReturnMain);
		
		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
		
		gameStarted = false;
	}

	/**
	 * Creates the EndGame Lost screen.
	 */
	public void createEndGameLostScreen() {
		String theWinner = whoWonGame();
		String theLoser = "";
		String scoreWinner = "";
		String scoreLoser = "";
		
		//Determine who was the loser.
		if (theWinner.equals("Team 1")) {
			theLoser = "Team 2";
			scoreWinner = "" + team1Score;
			scoreLoser = "" + team2Score;
		} else {
			theLoser = "Team 1";
			scoreWinner = "" + team2Score;
			scoreLoser = "" + team1Score;
		}

		teamWinner.setText(theWinner);
		teamLoser.setText(theLoser);

		upperPanel = makeUpperPanel("game won");
		middlePanel = makeMiddlePanel();
		middlePanel.setLayout(new GridBagLayout());

		middlePanel.setLayout(new GridBagLayout());
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 0;
	    gridBagConstraints.gridy = 0;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
	    gridBagConstraints.anchor = GridBagConstraints.WEST;
		winner.setFont(new Font("arial", Font.BOLD, 16));
    	winner.setForeground(Color.blue);
		middlePanel.add(winner, gridBagConstraints);
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 0;
	    gridBagConstraints.gridy = 1;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
	    gridBagConstraints.anchor = GridBagConstraints.WEST;
	    middlePanel.add(score1, gridBagConstraints);
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 0;
	    gridBagConstraints.gridy = 2;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
	    gridBagConstraints.anchor = GridBagConstraints.WEST;
		middlePanel.add(score2, gridBagConstraints);
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 0;
	    gridBagConstraints.gridy = 3;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
	    gridBagConstraints.anchor = GridBagConstraints.WEST;
		loser.setFont(new Font("arial", Font.BOLD, 16));
    	loser.setForeground(Color.black);
		middlePanel.add(loser, gridBagConstraints);
		
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 1;
	    gridBagConstraints.gridy = 0;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
	    gridBagConstraints.anchor = GridBagConstraints.EAST;
		middlePanel.add(teamWinner, gridBagConstraints);
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 1;
	    gridBagConstraints.gridy = 1;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
	    gridBagConstraints.anchor = GridBagConstraints.EAST;
	    scoreTeam1TextField.setText(scoreWinner);
	    middlePanel.add(scoreTeam1TextField, gridBagConstraints);
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 1;
	    gridBagConstraints.gridy = 2;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
	    gridBagConstraints.anchor = GridBagConstraints.EAST;
	    scoreTeam2TextField.setText(scoreLoser);
		middlePanel.add(scoreTeam2TextField, gridBagConstraints);
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 1;
	    gridBagConstraints.gridy = 3;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
	    gridBagConstraints.anchor = GridBagConstraints.EAST;
		middlePanel.add(teamLoser, gridBagConstraints);

		buttonReturnMain = makeButton("  Return  ", "returnMain", false);
		lowerPanel = makeLowerPanel();
		lowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));
		lowerPanel.add(buttonReturnMain);
		
		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
		
		gameStarted = false;
	}

	/**
	 * Creates the upper panel of a screen.
	 * 
	 * @param label The title text of the upper panel.
	 * @return The upper panel.
	 */
	public Panel makeUpperPanel(String label) {
		//Declare needed variables.
		Panel upper = new Panel();
		Label title = new Label(label.toUpperCase());
		
		//Set the look of the panel.
		title.setAlignment(Label.CENTER);
		title.setFont(new Font("arial", Font.BOLD, 20));
		title.setForeground(Color.red);
		upper.setBackground(Color.yellow);
		upper.add(title);
		
		return upper;
	}

	/**
	 * Creates the middle panel of a screen.
	 * 
	 * @return The middle panel.
	 */
	public Panel makeMiddlePanel() {
		//Declare needed variables.
		Panel middle = new Panel();
		
		//Set the look of the panel.
		middle.setBackground(Color.yellow);
		middle.setForeground(Color.black);
		
		return middle;
	}

	/**
	 * Creates the lower panel of a screen.
	 * 
	 * @return The lower panel.
	 */
	public Panel makeLowerPanel() {
		//Declare needed variables.
		Panel lower = new Panel();
		
		//Set the look of the panel.
		lower.setBackground(Color.yellow);
		lower.setForeground(Color.black);
		
		return lower;
	}

	/**
	 * Creates the first line of the play, bidding and scoring screen.
	 */
	public void makeLine1() {
		gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 0;
	    gridBagConstraints.gridy = 0;
		team1.setFont(new Font("arial", Font.BOLD, 16));
	    gridBagConstraints.anchor = GridBagConstraints.WEST;
		if (isDoubleAllowed("team1")) {
			team1.setForeground(Color.blue);
		} else {
			team1.setForeground(Color.black);
		}
	    middlePanel.add(team1, gridBagConstraints);
		gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 1;
	    gridBagConstraints.gridy = 0;
		middlePanel.add(score1, gridBagConstraints);
		gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 2;
	    gridBagConstraints.gridy = 0;
	    scoreTeam1TextField.setEditable(false);
	    scoreTeam1TextField.setText("" + team1Score);
	    middlePanel.add(scoreTeam1TextField, gridBagConstraints);
	}

	/**
	 * Creates the second line of the play, bidding and scoring screen.
	 */
	public void makeLine2() {
		gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 1;
	    gridBagConstraints.gridy = 1;
		bid.setFont(new Font("arial", Font.BOLD, 12));
	    gridBagConstraints.anchor = GridBagConstraints.EAST;
		middlePanel.add(bid, gridBagConstraints);
		gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 2;
	    gridBagConstraints.gridy = 1;
		tricks.setFont(new Font("arial", Font.BOLD, 12));
	    gridBagConstraints.anchor = GridBagConstraints.EAST;
		middlePanel.add(tricks, gridBagConstraints);
	}

	/**
	 * Creates the third line of the play, bidding and scoring screen.
	 */
	public void makeLine3() {
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 0;
	    gridBagConstraints.gridy = 2;
	    gridBagConstraints.insets = new Insets(0, 0, 3, 0);
	    gridBagConstraints.anchor = GridBagConstraints.WEST;
	    if (dealerSouth) {
	    	sPlayerName.setBackground(Color.blue);
	    	sPlayerName.setForeground(Color.white);
	    } else {
	    	sPlayerName.setBackground(Color.yellow);
	    	sPlayerName.setForeground(Color.black);
	    }
		middlePanel.add(sPlayerName, gridBagConstraints);
		gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 1;
	    gridBagConstraints.gridy = 2;
	    gridBagConstraints.insets = new Insets(0, 0, 3, 0);
	    sPlayerBidTextField.setEditable(false);
	    sPlayerBidTextField.setText(bidPlayerSouth);
		middlePanel.add(sPlayerBidTextField, gridBagConstraints);
		gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 2;
	    gridBagConstraints.gridy = 2;
	    gridBagConstraints.insets = new Insets(0, 15, 3, 0);
	    sPlayerTricksTextField.setEditable(false);
    	sPlayerTricksTextField.setVisible(true);
	    sPlayerTricksTextField.setText(tricksPlayerSouth);
	    middlePanel.add(sPlayerTricksTextField, gridBagConstraints);
	}

	/**
	 * Creates the forth line of the play, bidding and scoring screen.
	 */
	public void makeLine4() {
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 0;
	    gridBagConstraints.gridy = 3;
	    gridBagConstraints.insets = new Insets(0, 0, 3, 0);
	    gridBagConstraints.anchor = GridBagConstraints.WEST;
	    if (dealerNorth) {
	    	nPlayerName.setBackground(Color.blue);
	    	nPlayerName.setForeground(Color.white);
	    } else {
	    	nPlayerName.setBackground(Color.yellow);
	    	nPlayerName.setForeground(Color.black);
	    }
		middlePanel.add(nPlayerName, gridBagConstraints);
		gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 1;
	    gridBagConstraints.gridy = 3;
	    gridBagConstraints.insets = new Insets(0, 0, 3, 0);
	    nPlayerBidTextField.setEditable(false);
	    nPlayerBidTextField.setText(bidPlayerNorth);
		middlePanel.add(nPlayerBidTextField, gridBagConstraints);
		gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 2;
	    gridBagConstraints.gridy = 3;
	    gridBagConstraints.insets = new Insets(0, 15, 3, 0);
    	nPlayerTricksTextField.setEditable(false);
    	nPlayerTricksTextField.setVisible(true);
	    nPlayerTricksTextField.setText(tricksPlayerNorth);
	    middlePanel.add(nPlayerTricksTextField, gridBagConstraints);
	}

	/**
	 * Creates the fifth line of the play, bidding and scoring screen.
	 */
	public void makeLine5() {
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 0;
	    gridBagConstraints.gridy = 4;
	    gridBagConstraints.insets = new Insets(0, 0, 3, 0);
	    gridBagConstraints.anchor = GridBagConstraints.WEST;
	    if (dealerWest) {
	    	wPlayerName.setBackground(Color.blue);
	    	wPlayerName.setForeground(Color.white);
	    } else {
	    	wPlayerName.setBackground(Color.yellow);
	    	wPlayerName.setForeground(Color.black);
	    }
		middlePanel.add(wPlayerName, gridBagConstraints);
		gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 1;
	    gridBagConstraints.gridy = 4;
	    gridBagConstraints.insets = new Insets(0, 0, 3, 0);
	    wPlayerBidTextField.setEditable(false);
	    wPlayerBidTextField.setText(bidPlayerWest);
		middlePanel.add(wPlayerBidTextField, gridBagConstraints);
		gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 2;
	    gridBagConstraints.gridy = 4;
	    gridBagConstraints.insets = new Insets(0, 15, 3, 0);
    	wPlayerTricksTextField.setEditable(false);
    	wPlayerTricksTextField.setVisible(true);
	    wPlayerTricksTextField.setText(tricksPlayerWest);
	    middlePanel.add(wPlayerTricksTextField, gridBagConstraints);
	}

	/**
	 * Creates the sixth line of the play, bidding and scoring screen.
	 */
	public void makeLine6() {
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 0;
	    gridBagConstraints.gridy = 5;
	    gridBagConstraints.anchor = GridBagConstraints.WEST;
	    if (dealerEast) {
	    	ePlayerName.setBackground(Color.blue);
	    	ePlayerName.setForeground(Color.white);
	    } else {
	    	ePlayerName.setBackground(Color.yellow);
	    	ePlayerName.setForeground(Color.black);
	    }
		middlePanel.add(ePlayerName, gridBagConstraints);
		gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 1;
	    gridBagConstraints.gridy = 5;
	    ePlayerBidTextField.setEditable(false);
	    ePlayerBidTextField.setText(bidPlayerEast);
		middlePanel.add(ePlayerBidTextField, gridBagConstraints);
		gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 2;
	    gridBagConstraints.gridy = 5;
	    gridBagConstraints.insets = new Insets(0, 15, 0, 0);
	    ePlayerTricksTextField.setEditable(false);
    	ePlayerTricksTextField.setVisible(true);
	    ePlayerTricksTextField.setText(tricksPlayerEast);
	    middlePanel.add(ePlayerTricksTextField, gridBagConstraints);
	}

	/**
	 * Creates the last line of the play, bidding and scoring screen.
	 */
	public void makeLine7() {
		gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 0;
	    gridBagConstraints.gridy = 6;
	    gridBagConstraints.insets = new Insets(20, 0, 0, 0);
		team2.setFont(new Font("arial", Font.BOLD, 16));
	    gridBagConstraints.anchor = GridBagConstraints.WEST;
		if (isDoubleAllowed("team2")) {
			team2.setForeground(Color.blue);
		} else {
			team2.setForeground(Color.black);
		}
		middlePanel.add(team2, gridBagConstraints);
		gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 1;
	    gridBagConstraints.gridy = 6;
	    gridBagConstraints.insets = new Insets(20, 0, 0, 0);
		middlePanel.add(score2, gridBagConstraints);
		gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 2;
	    gridBagConstraints.gridy = 6;
	    gridBagConstraints.insets = new Insets(20, 0, 0, 0);
	    scoreTeam2TextField.setEditable(false);
	    scoreTeam2TextField.setText("" + team2Score);
	    middlePanel.add(scoreTeam2TextField, gridBagConstraints);
	}

	/**
	 * Makes a button with the included parameters.
	 * 
	 * @param title What the button will have for a label.
	 * @param action What action the button will do.
	 * @param upper Whether or not the button is in the upper panel.
	 * @return The button.
	 */
	public Button makeButton(String title, String action, boolean middleButton) {
		//Declare needed variables.
		Button button = new Button(title);
		
		//Set the look and action of the button.
		button.setActionCommand(action);
		button.addActionListener(this);
		if (middleButton) {
			button.setBackground(Color.black);
			button.setForeground(Color.white);
		} else {
			button.setBackground(Color.red);
			button.setForeground(Color.white);
		}

		return button;
	}

	/**
	 * This method
	 * 
	 * @param gridx
	 * @param gridy
	 * 
	 * @return
	 */
	public GridBagConstraints gbLayoutNormal(int gridx, int gridy) {
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = gridx;
	    gridBagConstraints.gridy = gridy;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
	    
	    return gridBagConstraints;
	}

	/**
	 * This method
	 * 
	 * @param gridx
	 * @param gridy
	 * 
	 * @return
	 */
	public GridBagConstraints gbLayoutWest(int gridx, int gridy) {
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = gridx;
	    gridBagConstraints.gridy = gridy;
	    gridBagConstraints.anchor = GridBagConstraints.WEST;
	    
	    return gridBagConstraints;
	}
	
	/**
	 * Calculates the scores after the tricks taken have been recorded.
	 */
	public void calculateScores() {
		int score1 = 0;
		int score2 = 0;
		int bags1 = 0;
		int bags2 = 0;
		bidTeam1 = bidSouth + bidNorth;
		bidTeam2 = bidWest + bidEast;
		
		//Calculate team 1 scores.
		if (!nilSouth && !nilNorth) {
			if (tricksTakenTeam1 >= bidTeam1) {
				bags1 = tricksTakenTeam1 - bidTeam1;
				score1 = (bidTeam1 * 10) + bags1;
			} else {
				score1 = bidTeam1 * -10;
			}
		} else {
			if (nilSouth) {
				if (tricksPlayerSouth.equalsIgnoreCase("d")
						&& bidPlayerSouth.equalsIgnoreCase("d")) {
					score1 += 200;
				} else if (tricksPlayerSouth.equalsIgnoreCase("n")
						&& bidPlayerSouth.equalsIgnoreCase("n")) {
					score1 += 50;
				} else {
					if (tricksPlayerSouth.equalsIgnoreCase("d")) {
						score1 -= 200;
					} else if (tricksPlayerSouth.equalsIgnoreCase("n")) {
						score1 -= 50;
					}				
				}
			} else {
				if (tricksTakenSouth >= bidTeam1) {
					bags1 = tricksTakenSouth - bidTeam1;
					score1 += (bidTeam1 * 10) + bags1;
				} else {
					score1 -= bidTeam1 * 10;
				}
			}
			if (nilNorth) {
				if (tricksPlayerNorth.equalsIgnoreCase("d")
						&& bidPlayerNorth.equalsIgnoreCase("d")) {
					score1 += 200;
				} else if (tricksPlayerNorth.equalsIgnoreCase("n")
						&& bidPlayerNorth.equalsIgnoreCase("n")) {
					score1 += 50;
				} else {
					if (tricksPlayerNorth.equalsIgnoreCase("d")) {
						score1 -= 200;
					} else if (tricksPlayerNorth.equalsIgnoreCase("n")) {
						score1 -= 50;
					}				
				}
			} else {
				if (tricksTakenNorth >= bidTeam1) {
					bags1 = tricksTakenNorth - bidTeam1;
					score1 += (bidTeam1 * 10) + bags1;
				} else {
					score1 -= bidTeam1 * 10;
				}
			}
		}
		
		//calculate team 2 scores.
		if (!nilWest && !nilEast) {
			if (tricksTakenTeam2 >= bidTeam2) {
				bags2 = tricksTakenTeam2 - bidTeam2;
				score2 = (bidTeam2 * 10) + bags2;
			} else {
				score2 = bidTeam2 * -10;
			}
		} else {
			if (nilWest) {
				if (tricksPlayerWest.equalsIgnoreCase("d")
						&& bidPlayerWest.equalsIgnoreCase("d")) {
					score2 += 200;
				} else if (tricksPlayerWest.equalsIgnoreCase("n")
						&& bidPlayerWest.equalsIgnoreCase("n")) {
					score2 += 50;
				} else {
					if (tricksPlayerWest.equalsIgnoreCase("d")) {
						score2 -= 200;
					} else if (tricksPlayerWest.equalsIgnoreCase("n")) {
						score2 -= 50;
					}				
				}
			} else {
				if (tricksTakenWest >= bidTeam2) {
					bags2 = tricksTakenWest - bidTeam2;
					score2 += (bidTeam2 * 10) + bags2;
				} else {
					score2 -= bidTeam2 * 10;
				}
			}
			if (nilEast) {
				if (tricksPlayerEast.equalsIgnoreCase("d")
						&& bidPlayerEast.equalsIgnoreCase("d")) {
					score2 += 200;
				} else if (tricksPlayerEast.equalsIgnoreCase("n")
						&& bidPlayerEast.equalsIgnoreCase("n")) {
					score2 += 50;
				} else {
					if (tricksPlayerEast.equalsIgnoreCase("d")) {
						score2 -= 200;
					} else if (tricksPlayerEast.equalsIgnoreCase("n")) {
						score2 -= 50;
					}				
				}
			} else {
				if (tricksTakenEast >= bidTeam2) {
					bags2 = tricksTakenEast - bidTeam2;
					score2 += (bidTeam2 * 10) + bags2;
				} else {
					score2 -= bidTeam2 * 10;
				}
			}
		}
		
		//Calculate when a nil bid get set.
		if (bidPlayerSouth.equalsIgnoreCase("n") 
				&& tricksPlayerSouth.equalsIgnoreCase("s")) {
			team1Score -= 50;
		}
		if (bidPlayerWest.equalsIgnoreCase("n") 
				&& tricksPlayerWest.equalsIgnoreCase("s")) {
			team2Score -= 50;
		}
		if (bidPlayerNorth.equalsIgnoreCase("n") 
				&& tricksPlayerNorth.equalsIgnoreCase("s")) {
			team1Score -= 50;
		}
		if (bidPlayerEast.equalsIgnoreCase("n") 
				&& tricksPlayerEast.equalsIgnoreCase("s")) {
			team2Score -= 50;
		}
		if (bidPlayerSouth.equalsIgnoreCase("d") 
				&& tricksPlayerSouth.equalsIgnoreCase("s")) {
			team1Score -= 200;
		}
		if (bidPlayerWest.equalsIgnoreCase("d") 
				&& tricksPlayerWest.equalsIgnoreCase("s")) {
			team2Score -= 200;
		}
		if (bidPlayerNorth.equalsIgnoreCase("d") 
				&& tricksPlayerNorth.equalsIgnoreCase("s")) {
			team1Score -= 200;
		}
		if (bidPlayerEast.equalsIgnoreCase("d") 
				&& tricksPlayerEast.equalsIgnoreCase("s")) {
			team2Score -= 200;
		}

		//Add scores to totals.
		team1Score += score1;
		team2Score += score2;
		
		//Change score to account for 10 bags taken.
		bagsTeam1 += bags1;
		bagsTeam2 += bags2;
		
		if (bagsTeam1 > 9) {
			team1Score -= 100;
			bagsTeam1 -= 10;
		}
		if (bagsTeam2 > 9) {
			team2Score -= 100;
			bagsTeam2 -= 10;
		}
	}

	/**
	 * Shows the player names if they were previously entered.
	 */
	public void showPreviousPlayerNames() {
		sPlayerTextField.setText(playerSouth);
		wPlayerTextField.setText(playerWest);
		nPlayerTextField.setText(playerNorth);
		ePlayerTextField.setText(playerEast);
	}

	/**
	 * Clears the player names.
	 */
	public void clearPlayerNames() {
		sPlayerTextField.setText("");
		wPlayerTextField.setText("");
		nPlayerTextField.setText("");
		ePlayerTextField.setText("");
		
		playerSouth = "";
		playerWest = "";
		playerNorth = "";
		playerEast = "";
	}

	/**
	 * Clears the selected dealer.
	 */
	public void clearDealerName() {
		dealerSouth = false;
		dealerWest = false;
		dealerNorth = false;
		dealerEast = false;
		
		southDealer.setState(false);
		westDealer.setState(false);
		northDealer.setState(false);
		eastDealer.setState(false);

		curDealer = "";
		startDealer = "";
	}

	/**
	 * Clears the trick taken.
	 */
	public void clearTricksTaken() {
		tricksTakenTeam1 = 0;
		tricksTakenTeam2 = 0;
		tricksTakenSouth = 0;
		tricksTakenWest = 0;
		tricksTakenNorth = 0;
		tricksTakenEast = 0;
		
		tricksPlayerSouth = "";
		tricksPlayerWest = "";
		tricksPlayerNorth = "";
		tricksPlayerEast = "";
		
		sPlayerTricksTextField.setText("");
		wPlayerTricksTextField.setText("");
		nPlayerTricksTextField.setText("");
		ePlayerTricksTextField.setText("");
	}

	/**
	 * Clears the bids made.
	 */
	public void clearBidsMade() {
		bidTeam1 = 0;
		bidTeam2 = 0;
		bidSouth = 0;
		bidWest = 0;
		bidNorth = 0;
		bidEast = 0;
		nilSouth = false;
		nilWest = false;
		nilNorth = false;
		nilEast = false;
		
		bidPlayerSouth = "";
		bidPlayerWest = "";
		bidPlayerNorth = "";
		bidPlayerEast = "";
		
		sPlayerBidTextField.setText("");
		wPlayerBidTextField.setText("");
		nPlayerBidTextField.setText("");
		ePlayerBidTextField.setText("");
	}

	/**
	 * Gets the player names and copies them to their assigned names.
	 */
	public void setPlayerNames() {
		playerSouth = sPlayerTextField.getText();
		playerWest = wPlayerTextField.getText();
		playerNorth = nPlayerTextField.getText();
		playerEast = ePlayerTextField.getText();
	}

	/**
	 * Gets the dealer name and copies it to the curDealer.
	 */
	public void setDealerName() {
		clearDealerName();
		
		if (southDealer.getState()) {
			dealerSouth = true;
			startDealer = playerSouth;
		}
		if (westDealer.getState()) {
			dealerWest = true;
			startDealer = playerWest;
		}
		if (northDealer.getState()) {
			dealerNorth = true;
			startDealer = playerNorth;
		}
		if (eastDealer.getState()) {
			dealerEast = true;
			startDealer = playerEast;
		}

		curDealer = startDealer;
	}

	/**
	 * Saves the bidding data.
	 */
	public void saveBidData() {
		//Get the data entered in the text fields.
		bidPlayerSouth = sPlayerBidTextField.getText();
		bidPlayerWest = wPlayerBidTextField.getText();
		bidPlayerNorth = nPlayerBidTextField.getText();
		bidPlayerEast = ePlayerBidTextField.getText();
	}

	/**
	 * Saves the tricks taken data.
	 */
	public void saveTricksTakenData() {
		//Get the data entered in the text fields.
		tricksPlayerSouth = sPlayerTricksTextField.getText();
		tricksPlayerWest = wPlayerTricksTextField.getText();
		tricksPlayerNorth = nPlayerTricksTextField.getText();
		tricksPlayerEast = ePlayerTricksTextField.getText();
	}

	/**
	 * The players names have been entered.
	 * 
	 * @return True is the players setup is done, false otherwise.
	 */
	public boolean isPlayersSetupDone() {
		boolean ret = true;
		
		//Check if all the text fields have data.
		if (sPlayerTextField.getText().equals("")) {
			ret = false;
		}
		if (sPlayerTextField.getText().equals("")) {
			ret = false;
		}
		if (sPlayerTextField.getText().equals("")) {
			ret = false;
		}
		if (sPlayerTextField.getText().equals("")) {
			ret = false;
		}

		//Show dialog box reminder.
		if (!ret) showDialogBox("All players must be named.");
		
		//Save player names.
		setPlayerNames();
		
		return ret;
	}

	/**
	 * The dealer selection has been completed.
	 * 
	 * @return True when the dealer selection is complete. false otherwise.
	 */
	public boolean isDealerSelectionDone() {
		boolean dealerSelected = false;
		boolean selectionFlag = true;
		
		//Check if the dealer has been selected.
		if (southDealer.getState() && selectionFlag) {
			dealerSelected = true;
			selectionFlag = false;
		}
		if (westDealer.getState() && selectionFlag) {
			dealerSelected = true;
			selectionFlag = false;
		}
		if (northDealer.getState() && selectionFlag) {
			dealerSelected = true;
			selectionFlag = false;
		}
		if (eastDealer.getState() && selectionFlag) {
			dealerSelected = true;
			selectionFlag = false;
		}

		//Show dialog box reminder.
		if (!dealerSelected) showDialogBox("A dealer must be selected.");
		
		//Save who the dealer is.
		setDealerName();
			
		return dealerSelected;
	}

	/**
	 * Can the team do a double nil?
	 * @return True is it is possible, false otherwise.
	 */
	public boolean isDoubleAllowed(String team) {
		boolean allowed = false;
		
		if (team.equalsIgnoreCase("team1")) {
			allowed = (team2Score - team1Score) > 200;
		} else if (team.equalsIgnoreCase("team2")) {
			allowed = (team1Score - team2Score) > 200;
		}
	
		return allowed;
	}

	/**
	 * Determines if the games has been won.
	 * 
	 * @return True if the game is won, false otherwise.
	 */
	public boolean isGameWon() {
		return (team1Score > 500 || team2Score > 500);
	}

	/**
	 * Determines if the games has been lost.
	 * 
	 * @return True if the game is lost, false otherwise.
	 */
	public boolean isGameLost() {
		return (team1Score <= -200 || team2Score <= -200);
	}
	
	/**
	 * Determines which team won the game.
	 * 
	 * @return The winner of the game.
	 */
	public String whoWonGame() {
		String winner = "";
		
		if (team1Score > 500 && team2Score > 500) {
			if (team1Score > team2Score) winner = "Team 1";
			if (team1Score < team2Score) winner = "Team 2";
		} else if (team1Score > 500) {
			winner = "Team 1";
		} else if (team2Score > 500) {
			winner = "Team 2";
		}
		
		return winner;
	}
	
	/**
	 * Shows a dialog box with the given message.
	 */
	public void showDialogBox(String message) {
		final Dialog dialog;
		Frame window = new Frame();
		
		//Create a modal dialog.
		dialog = new Dialog(window, "You can't do that", true);
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
	 * Process the bidding info before the scoring screen is shown.
	 * 
	 * @return True if bidding is complete, false otherwise.
	 */
	public boolean processBidding() {
		boolean done = true;
		nilSouth = false;
		nilWest = false;
		nilNorth = false;
		nilEast = false;
		
		//Check if the bids are valid.
		bidPlayerSouth = sPlayerBidTextField.getText();
		bidPlayerWest = wPlayerBidTextField.getText();
		bidPlayerNorth = nPlayerBidTextField.getText();
		bidPlayerEast = ePlayerBidTextField.getText();
		
		if (bidPlayerSouth.equals("")) {
			done = false;
		} else {
			try {
				bidSouth = Integer.parseInt(bidPlayerSouth);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				bidSouth = -1;
			}
			if ((bidSouth < 1 || bidSouth > 13) && !(bidSouth == -1)) {
				done = false;
			} else if (bidSouth == -1) {
				nilSouth = true;
				bidSouth = 0;
				if (!bidPlayerSouth.equalsIgnoreCase("n")) {
					if (!bidPlayerSouth.equalsIgnoreCase("d")) {
						done = false;
					}
				}
			}
		}
		if (bidPlayerWest.equals("")) {
			done = false;
		} else {
			try {
				bidWest = Integer.parseInt(bidPlayerWest);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				bidWest = -1;
			}
			if ((bidWest < 1 || bidWest > 13) && !(bidWest == -1)) {
				done = false;
			} else if (bidWest == -1) {
				nilWest = true;
				bidWest = 0;
				if (!bidPlayerWest.equalsIgnoreCase("n")) {
					if (!bidPlayerWest.equalsIgnoreCase("d")) {
						done = false;
					}
				}
			}
		}
		if (bidPlayerNorth.equals("")) {
			done = false;
		} else {
			try {
				bidNorth = Integer.parseInt(bidPlayerNorth);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				bidNorth = -1;
			}
			if ((bidNorth < 1 || bidNorth > 13) && !(bidNorth == -1)) {
				done = false;
			} else if (bidNorth == -1) {
				nilNorth = true;
				bidNorth = 0;
				if (!bidPlayerNorth.equalsIgnoreCase("n")) {
					if (!bidPlayerNorth.equalsIgnoreCase("d")) {
						done = false;
					}
				}
			}
		}
		if (bidPlayerEast.equals("")) {
			done = false;
		} else {
			try {
				bidEast = Integer.parseInt(bidPlayerEast);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				bidEast = -1;
			}
			if ((bidEast < 1 || bidEast > 13) && !(bidEast == -1)) {
				done = false;
			} else if (bidEast == -1) {
				nilEast = true;
				bidEast = 0;
				if (!bidPlayerEast.equalsIgnoreCase("n")) {
					if (!bidPlayerEast.equalsIgnoreCase("d")) {
						done = false;
					}
				}
			}
		}

		//Show dialog box reminder.
		if (!done) showDialogBox("An incorrect bid was made.");
			
		return done;
	}

	/**
	 * Process the scoring info before the bidding screen is shown.
	 * 
	 * @return True if scoring is complete, false otherwise.
	 */
	public boolean processScoring() {
		boolean done = true;
		boolean lockOutTeam1 = nilSouth || nilNorth;
		boolean lockOutTeam2 = nilWest || nilEast;
		int totalTricksTaken = 0;
		tricksTakenSouth = 0;
		tricksTakenEast = 0;

		//Check if the bids are valid.
		tricksPlayerSouth = sPlayerTricksTextField.getText();
		tricksPlayerWest = wPlayerTricksTextField.getText();
		tricksPlayerNorth = nPlayerTricksTextField.getText();
		tricksPlayerEast = ePlayerTricksTextField.getText();
		
		if (tricksPlayerSouth.equals("") && lockOutTeam1) {
			done = false;
		} else {
			try {
				tricksTakenSouth = Integer.parseInt(tricksPlayerSouth);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				tricksTakenSouth = -1;
			}
			if ((tricksTakenSouth < 1 || tricksTakenSouth > 13) &&
					!(tricksTakenSouth == -1)) {
				done = false;
			} else if (tricksTakenSouth == -1) {
				tricksTakenSouth = 0;
				if (!tricksPlayerSouth.equals("")) {
					if (!tricksPlayerSouth.equalsIgnoreCase("n")) {
						if (!tricksPlayerSouth.equalsIgnoreCase("d")) {
							if (!tricksPlayerSouth.equalsIgnoreCase("s")) done = false;
						}
					}
				}
			}
		}
		if (tricksPlayerWest.equals("")) {
			done = false;
		} else {
			try {
				tricksTakenWest = Integer.parseInt(tricksPlayerWest);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				tricksTakenWest = -1;
			}
			if ((tricksTakenWest < 1 || tricksTakenWest > 13) &&
					!(tricksTakenWest == -1)) {
				done = false;
			} else if (tricksTakenWest == -1) {
				tricksTakenWest = 0;
				if (!tricksPlayerWest.equalsIgnoreCase("n")) {
					if (!tricksPlayerWest.equalsIgnoreCase("d")) {
						if (!tricksPlayerWest.equalsIgnoreCase("s")) done = false;
					}
				}
			}
		}
		if (tricksPlayerNorth.equals("")) {
			done = false;
		} else {
			try {
				tricksTakenNorth = Integer.parseInt(tricksPlayerNorth);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				tricksTakenNorth = -1;
			}
			if ((tricksTakenNorth < 1 || tricksTakenNorth > 13) &&
					!(tricksTakenNorth == -1)) {
				done = false;
			} else if (tricksTakenNorth == -1) {
				tricksTakenNorth = 0;
				if (!tricksPlayerNorth.equalsIgnoreCase("n")) {
					if (!tricksPlayerNorth.equalsIgnoreCase("d")) {
						if (!tricksPlayerNorth.equalsIgnoreCase("s")) done = false;
					}
				}
			}
		}
		if (tricksPlayerEast.equals("") && lockOutTeam2) {
			done = false;
		} else {
			try {
				tricksTakenEast = Integer.parseInt(tricksPlayerEast);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				tricksTakenEast = -1;
			}
			if ((tricksTakenEast < 1 || tricksTakenEast > 13) &&
					!(tricksTakenEast == -1)) {
				done = false;
			} else if (tricksTakenEast == -1) {
				tricksTakenEast = 0;
				if (!tricksPlayerEast.equals("")) {
					if (!tricksPlayerEast.equalsIgnoreCase("n")) {
						if (!tricksPlayerEast.equalsIgnoreCase("d")) {
							if (!tricksPlayerEast.equalsIgnoreCase("s")) done = false;
						}
					}
				}
			}
		}

		tricksTakenTeam1 = tricksTakenSouth + tricksTakenNorth;
		tricksTakenTeam2 = tricksTakenWest + tricksTakenEast;
		totalTricksTaken = tricksTakenSouth + tricksTakenWest +
				tricksTakenNorth + tricksTakenEast;

		if (totalTricksTaken != 13) done = false;
		
		//Show dialog box reminder.
		if (!done) showDialogBox("Tricks taken was entered wrong.");
			
		return done;
	}

	/**
	 * Resets the game so a new game can be started.
	 */
	public void resetGame() {
		//Put all variables to their default values.
		clearPlayerNames();
		clearDealerName();

		team1Score = 0;
		team2Score = 0;
		round = 0;
		tricksTakenTeam1 = 0;
		tricksTakenTeam2 = 0;
		tricksTakenSouth = 0;
		tricksTakenWest = 0;
		tricksTakenNorth = 0;
		tricksTakenEast = 0;
		bidTeam1 = 0;
		bidTeam2 = 0;
		bidSouth = 0;
		bidWest = 0;
		bidNorth = 0;
		bidEast = 0;
			
		playersDone = false;
		dealerDone= false;
		gameStarted = false;
		gameWon = false;
		doBidding = true;
		doScoring = false;
		dealerSouth = false;
		dealerWest = false;
		dealerNorth = false;
		dealerEast = false;
		nilSouth = false;
		nilWest = false;
		nilNorth = false;
		nilEast = false;
		
		bidPlayerSouth = "";
		bidPlayerWest = "";
		bidPlayerNorth = "";
		bidPlayerEast = "";
		tricksPlayerSouth = "";
		tricksPlayerWest = "";
		tricksPlayerNorth = "";
		tricksPlayerEast = "";
		
		southDealer.setState(false);
		westDealer.setState(false);
		northDealer.setState(false);
		eastDealer.setState(false);
		
		sPlayerTextField.setText("");
		wPlayerTextField.setText("");
		nPlayerTextField.setText("");
		ePlayerTextField.setText("");
		scoreTeam1TextField.setText("");
		scoreTeam2TextField.setText("");
		sPlayerBidTextField.setText("");
		wPlayerBidTextField.setText("");
		nPlayerBidTextField.setText("");
		ePlayerBidTextField.setText("");
		sPlayerTricksTextField.setText("");
		wPlayerTricksTextField.setText("");
		nPlayerTricksTextField.setText("");
		ePlayerTricksTextField.setText("");
	}
	
	/**
	 * Advances the dealer to the next player in rotation.
	 */
	public void advanceDealer() {
		boolean once = false;
		if (curDealer.equals(playerSouth) && !once) {
			curDealer = playerWest;
			dealerSouth = false;
			dealerWest = true;
			once = true;
		}
		if (curDealer.equals(playerWest) && !once) {
			curDealer = playerNorth;
			dealerWest = false;
			dealerNorth = true;
			once = true;
		}
		if (curDealer.equals(playerNorth) && !once) {
			curDealer = playerEast;
			dealerNorth = false;
			dealerEast = true;
			once = true;
		}
		if (curDealer.equals(playerEast) && !once) {
			curDealer = playerSouth;
			dealerEast = false;
			dealerSouth = true;
			once = true;
		}
	}
	
	/**
	 * Saves the game data for each round to a file.
	 */
	public void printGameData() {
		File file = new File("game.dat");
		System.out.print(this.toString());
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(this.toString());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		round++;
	}
	
	/**
	 * This outputs all the variables showing their values for each round of play.
	 */
	public String toString() {
		String temp = "";
		
		if (round == 0) {
			temp = temp + "Round " + round + "\n";
			temp = temp + "Start Dealer = " + startDealer + "\n";
			temp = temp + "--Team 1--\n";
			temp = temp + "Player South = " + playerSouth + "\n";
			temp = temp + "Player North = " + playerNorth + "\n";
			temp = temp + "--Team 2--\n";
			temp = temp + "Player West = " + playerWest + "\n";
			temp = temp + "Player East = " + playerEast + "\n";
		} else {
			temp = temp + "Round " + round + "\n";
			temp = temp + "curDealer = " + curDealer + "\n";
			temp = temp + "--Team 1--\n";
			temp = temp + "Bid Player South = " + bidPlayerSouth + "\n";
			temp = temp + "Bid Player North = " + bidPlayerNorth + "\n";
			temp = temp + "Tricks Taken = ";
			if (nilSouth || nilNorth) {
				temp = temp + playerSouth + ": " + tricksTakenSouth + " & ";
				temp = temp + playerNorth + ": " + tricksTakenNorth + "\n";
			} else {
				temp = temp + tricksTakenTeam1 + "\n";
			}
			temp = temp + "Score = " + team1Score + "\n";
			temp = temp + "--Team 2--\n";
			temp = temp + "Bid Player West = " + bidPlayerWest + "\n";
			temp = temp + "Bid Player East = " + bidPlayerEast + "\n";
			temp = temp + "Tricks Taken = ";
			if (nilWest || nilEast) {
				temp = temp + playerWest + ": " + tricksTakenWest + " & ";
				temp = temp + playerEast + ": " + tricksTakenEast + "\n";
			} else {
				temp = temp + tricksTakenTeam2 + "\n";
			}
			temp = temp + "Score = " + team2Score + "\n";
		}
		
		return temp;
	}

	/**
	 * The main method that starts the whole process at the Menu screen.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		CopyOfSpadesScorer game = new CopyOfSpadesScorer();
		game.createMainMenuScreen();
	}
}
