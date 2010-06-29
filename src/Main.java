/**FINISHED
 * Scorer.java
 * 
 * Application to keep score in a Spades Game. This will have the ability to
 * keep the score for 3-handed, 4-handed single, and 4-handed  with 2 teams
 * type games. It will also allow for changing how bags are counted and what
 * the values for nils are. The application will also keep a record of all
 * actions of game play for review when there is a dispute. This data will be
 * editable if a mistake is found.
 * 
 * @author David Hoffman
 */

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main  extends Frame implements ActionListener,
											WindowListener {
	/**
	 * Declare needed variables.
	 */
	private static final long serialVersionUID = 1L;
	
	public static ArrayList names;
	
	public static boolean isGameStarted = false;
	public static boolean isSetupDone;
	public static boolean isGameWon;
	public static boolean isGameLost;
	public static boolean isThreeHanded;
	public static boolean isFourHandedSingle;
	public static boolean isFourHandedTeams;
	public static boolean doBidding;
	public static boolean doScoring;
	public static boolean dealerIsPlayer1;
	public static boolean dealerIsPlayer2;
	public static boolean dealerIsPlayer3;
	public static boolean dealerIsPlayer4;
	public static boolean skinIsIowaState = true;
	public static boolean skinIsIowa;
	public static boolean skinIsNorthernIowa;
	public static boolean isNilAllowed;
	public static boolean isDoubleNilAllowed;
	
	public static String player1;
	public static String player2;
	public static String player3;
	public static String player4;
	public static String team1;
	public static String team2;
	public static String player1Bid;
	public static String player2Bid;
	public static String player3Bid;
	public static String player4Bid;
	public static String player1TricksTaken;
	public static String player2TricksTaken;
	public static String player3TricksTaken;
	public static String player4TricksTaken;
	public static String player1Score;
	public static String player2Score;
	public static String player3Score;
	public static String player4Score;
	public static String team1Score;
	public static String team2Score;
	public static String curDealer;
	public static String startDealer;
	public static String bagValue;
	public static String nilValue;
	public static String doubleNilValue;
	public static String winScore;
	public static String loseScore;

	public static int game = 1;
	public static int round;
	public static int player1TimesSet;
	public static int player2TimesSet;
	public static int player3TimesSet;
	public static int player4TimesSet;
	public static int bagValueNumb;
	public static int nilValueNumb;
	public static int doubleNilValueNumb;
	public static int winScoreNumb;
	public static int loseScoreNumb;

	public static Color backgroundColor;
	public static Color labelColor;
	public static Color textColor;
	public static Color goDoubleColor;
	public static Color fgMiddleButtonColor;
	public static Color bgMiddleButtonColor;
	public static Color fgLowerButtonColor;
	public static Color bgLowerButtonColor;
	public static Color fgTextHighlightedColor;
	public static Color bgTextHighlightedColor;
	
	public static Player playerOne;
	public static Player playerTwo;
	public static Player playerThree;
	public static Player playerFour;
	public static Player playerPrevious;
	
	public static Team teamOne;
	public static Team teamTwo;
	public static Team teamPrevious;
	
	Panel upperPanel;
	Panel middlePanel;
	Panel lowerPanel;
	
	Button buttonExit;
	Button buttonPlay;
	Button buttonSetup;
	Button buttonNewGame;
	Button buttonContGame;
	Button buttonEditGame;
	
	static Main frame = new Main();

	/**
	 * Default constructor for the class.
	 */
    public Main(){
		super("Spades Scorer");
		setBounds(50, 50, 240, 320);
		addWindowListener(this);
    }

	/**
	 * Performs the assigned tasks when the corresponding button is pressed.
	 * 
	 * @param event The action that was triggered by a button press.
	 */
    public void actionPerformed(ActionEvent event) {
        //Performs this action when the NewGame button is pressed.
        if (event.getActionCommand().equals("newGame")) {
        	if (isGameStarted || isGameWon || isGameLost) {
            	Utils.exportGameOptions();
            	Utils.exportPlayerFile(playerOne);
            	Utils.exportPlayerFile(playerTwo);
            	Utils.exportPlayerFile(playerThree);
            	
            	if (!isThreeHanded) {
                	Utils.exportPlayerFile(playerFour);
            	}
            	game++;
        	}
        	
        	frame.removeAll();
        	Utils.resetGame();

        	GameSetup setup = new GameSetup(frame);
    		setup.createSetupScreen();
        }
        
        //Performs this action when the EditGame button is pressed.
        if (event.getActionCommand().equals("editGame")) {
        	frame.removeAll();

        	EditGame editor = new EditGame(frame);
    		editor.createEditGameScreen();
        }

        //Performs this action when the PlayGame button is pressed.
        if (event.getActionCommand().equals("playGame")) {
        	frame.removeAll();
        	Utils.convertOptionsToNumbers();
    		
        	//Shows the correct screens for the game being played.
        	if (isThreeHanded) {
        		//Create player only if a new game is started.
        		if (!isGameStarted) {
        			playerOne = new Player(player1);
        			playerTwo = new Player(player2);
        			playerThree = new Player(player3);
        		}
        		
        		ThreeHanded three = new ThreeHanded(frame);
           		three.createPlayGameScreen();
        	}

        	if (isFourHandedSingle) {
        		//Create player only if a new game is started.
        		if (!isGameStarted) {
        			playerOne = new Player(player1);
        			playerTwo = new Player(player2);
        			playerThree = new Player(player3);
        			playerFour = new Player(player4);
        		}
        		
        		FourHanded four = new FourHanded(frame);
           		four.createPlayGameScreen();
        	}

        	if (isFourHandedTeams) {
        		//Create player only if a new game is started.
        		if (!isGameStarted) {
        			playerOne = new Player(player1);
        			playerTwo = new Player(player2);
        			playerThree = new Player(player3);
        			playerFour = new Player(player4);
        			
        			teamOne = new Team(playerOne, playerThree);
        			teamTwo = new Team(playerTwo, playerFour);
        			
        			Utils.saveTeamNames();
        		}
        		
        		TwoTeams teams = new TwoTeams(frame);
           		teams.createPlayGameScreen();
        	}
        }  

        //Performs this action when the Setup button is pressed.
        if (event.getActionCommand().equals("setup")) {
        	frame.removeAll();
 
        	GameSetup setup = new GameSetup(frame);
    		setup.createSetupScreen();
        }  

        //Performs this action when the Exit button is pressed.
        if (event.getActionCommand().equals("exit")) {
        	exit();
        }  
 	}

	/**
	 * Creates the MainMenu screen.
	 */
	public void createMainMenuScreen() {
		//Set the default screen skin.
		Utils.setSkinSelected();
		
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("main menu");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();
		
		//Makes all the needed buttons.
		buttonNewGame = FrameUtils.makeButton(" New Game", "newGame", true);
		buttonNewGame.addActionListener(this);
		buttonContGame = FrameUtils.makeButton(" Continue", "playGame", true);
		buttonContGame.addActionListener(this);
		buttonPlay = FrameUtils.makeButton("   Play  ", "playGame", true);
		buttonPlay.addActionListener(this);
		buttonSetup = FrameUtils.makeButton("  Setup  ", "setup", true);
		buttonSetup.addActionListener(this);
		buttonEditGame = FrameUtils.makeButton("Edit Game", "editGame", true);
		buttonEditGame.addActionListener(this);
		buttonExit = FrameUtils.makeButton("   Exit  ", "exit", false);
		buttonExit.addActionListener(this);

		//Add the buttons to the proper panels.
		lowerPanel.add(buttonExit);

		middlePanel.setLayout(new GridBagLayout());
		middlePanel.add(buttonNewGame, FrameUtils.gbLayoutNormal(0, 0));
		middlePanel.add(buttonSetup, FrameUtils.gbLayoutNormal(0, 1));
		middlePanel.add(buttonEditGame, FrameUtils.gbLayoutNormal(0, 2));
		middlePanel.add(buttonContGame, FrameUtils.gbLayoutNormal(0, 3));
		middlePanel.add(buttonPlay, FrameUtils.gbLayoutNormal(0, 3));

			
		//Adds these buttons to the middle panel when the game is in various modes.
		if (isGameStarted) {
			buttonContGame.setVisible(true);
			buttonPlay.setVisible(false);
			buttonSetup.setVisible(true);
			buttonEditGame.setVisible(true);
		} else if (isGameWon || isGameLost){
			buttonContGame.setVisible(false);
			buttonPlay.setVisible(false);
			buttonSetup.setVisible(true);
			buttonEditGame.setVisible(true);
		} else if (isSetupDone){
			buttonContGame.setVisible(false);
			buttonPlay.setVisible(true);
			buttonSetup.setVisible(true);
			buttonEditGame.setVisible(false);
		} else {
			buttonContGame.setVisible(false);
			buttonPlay.setVisible(false);
			buttonSetup.setVisible(false);
			buttonEditGame.setVisible(false);
		}
		
		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}
	
	/**
	 * Exits the application.
	 */
	public static void exit() {
	    frame.dispose();
	    System.exit(0);
	}

	/**
	 * WindowListener for when the Window Close button is pressed. Will not
	 * work when a game has been started to prevent accidental closure.
	 */
	public void windowClosing(WindowEvent event) {
		if (!isGameStarted) exit();
	}

	public void windowActivated(WindowEvent event) {
		//This is blank by design.
	}

	public void windowClosed(WindowEvent event) {
		//This is blank by design.
	}

	public void windowDeactivated(WindowEvent event) {
		//This is blank by design.
	}

	public void windowDeiconified(WindowEvent event) {
		//This is blank by design.
	}

	public void windowIconified(WindowEvent event) {
		//This is blank by design.
	}

	public void windowOpened(WindowEvent event) {
		//This is blank by design.
	}

	/**
	 * The main method that starts the whole process.
	 * 
	 * @param args There are none.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//Load player names from a file.
		loadNames();

		Main game = new Main();
		game.createMainMenuScreen();
	}

	/**
	 * Reads the names of available players from the file names.txt.
	 * @throws IOException 
	 */
	private static void loadNames() throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("names.txt"));
		String line;
		names = new ArrayList();

	    try {    
	    	if (!in.ready());

	        while ((line = in.readLine()) != null) {
	        	names.add(line);
	        }
	    }
        catch (IOException e) {
	            System.out.println(e);
	    }
        
    	in.close();
	}
}
