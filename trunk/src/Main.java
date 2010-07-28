/**FINISHED
 * Main.java
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
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Frame implements ActionListener,
											WindowListener, ItemListener {
	/**
	 * Declare needed variables.
	 */
	private static final long serialVersionUID = 1L;
	
	public static final boolean DEBUG = false;
	public static ArrayList names;
	public static ArrayList ini;
	
	public static boolean nilBidTeam1 = false;
	public static boolean nilBidTeam2 = false;
	public static boolean isExitAllowed = false;
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
	public static boolean doubleIsAllowedPlayer1;
	public static boolean doubleIsAllowedPlayer2;
	public static boolean doubleIsAllowedPlayer3;
	public static boolean doubleIsAllowedPlayer4;
	public static boolean doubleIsAllowedTeam1;
	public static boolean doubleIsAllowedTeam2;
	public static boolean sounds = false;
	public static boolean iniFailed = false;
	
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

	public static int game = 0;
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

	public static Checkbox optionsCheckbox = new Checkbox("Save", true);
	public static Checkbox player1Checkbox = new Checkbox("Save", true);
	public static Checkbox player2Checkbox = new Checkbox("Save", true);
	public static Checkbox player3Checkbox = new Checkbox("Save", true);
	public static Checkbox player4Checkbox = new Checkbox("Save", true);
	public static Checkbox playerPreviousCheckbox = new Checkbox("Save", false);
	public static Checkbox team1Checkbox = new Checkbox("Save", true);
	public static Checkbox team2Checkbox = new Checkbox("Save", true);
	public static Checkbox teamPreviousCheckbox = new Checkbox("Save", false);

	Label optionsLabel = new Label("  Options   ");
	Label player1Label = new Label("  Player 1  ");
	Label player2Label = new Label("  Player 2  ");
	Label player3Label = new Label("  Player 3  ");
	Label player4Label = new Label("  Player 4  ");
	Label playerPreviousLabel = new Label("  Player 5  ");
	Label team1Label = new Label("  Team 1  ");
	Label team2Label = new Label("  Team 2  ");
	Label teamPreviousLabel = new Label("  Team 3  ");
	Label message1 = new Label();
	Label message2 = new Label();
	Label version = new Label("Ver. 2.0.10");
	Label dummy = new Label(" Intermec ");
	
	Panel upperPanel;
	Panel middlePanel;
	Panel lowerPanel;
	
	Button buttonExit;
	Button buttonExitMain;
	Button buttonSave;
	Button buttonReturn;
	Button buttonPlay;
	Button buttonSetup;
	Button buttonNewGame;
	Button buttonContGame;
	Button buttonEditGame;
	Button buttonEndGame;
	
	static File iniFile = new File("spades.ini");
	static File soundBags = new File("ComeOn.wav");
	static File soundSet = new File("Yes.wav");
	static File soundWin = new File("Applause.wav");
	static File soundLose = new File("MaybeNextTime.wav");
	static File soundGameStart= new File("Startup.wav");
	
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
           	game++;
        	
           	Utils.resetGame();
           	frame.removeAll();
           	
           	GameSetup setup = new GameSetup(frame);
           	setup.createSetupScreen();
        }

        //Performs this action when the EndGame button is pressed.
        if (event.getActionCommand().equals("endGame")) {
            if(Main.doScoring) {
            	FrameUtils.showDialogBox("You have not finished scoring.");
            } else {
            	//Save winScore and set a new winScore to a low number.
            	int saveWinScore = Main.winScoreNumb;
            	Main.winScore = "1";
            	Main.winScoreNumb = 1;
            	
        		//Game should now be won by someone.
            	if (Utils.isGameWon()) {
					if (Main.isThreeHanded) {
	            		frame.removeAll();
	            		ThreeHanded end = new ThreeHanded(frame);
	        			end.createEndGameWonScreen();
					}
					if (Main.isFourHandedSingle) {
	            		frame.removeAll();
	            		FourHanded end = new FourHanded(frame);
	        			end.createEndGameWonScreen();
					}
					if (Main.isFourHandedTeams) {
	            		frame.removeAll();
	            		TwoTeams end = new TwoTeams(frame);
	        			end.createEndGameWonScreen();
					}
        		} else {
        			FrameUtils.showDialogBox("Something went wrong. Try again.");
        		}
            	
            	//Restore the winScore value.
            	Main.winScore = Integer.toString(saveWinScore);
            	Main.winScoreNumb = saveWinScore;
            }
            frame.removeAll();
            	
            Main game = new Main();
    		game.createEndGameScreen();
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

        //Performs this action when the Save button is pressed.
        if (event.getActionCommand().equals("save")) {
        	Utils.saveStats();
        	
        	//Display the files saved message.
        	FrameUtils.showDialogBox("All selected files were saved.");
        	
        	//Reset some of the variables.
        	isGameStarted = false;
        	isSetupDone = false;
        	isGameWon = false;
        	isGameLost = false;
        }  

        //Performs this action when the MainMenu button is pressed.
        if (event.getActionCommand().equals("mainMenu")) {
        	frame.removeAll();
        	
    		Main game = new Main();
    		game.createMainMenuScreen();
        }  

        //Performs this action when the ExitMain button is pressed.
        if (event.getActionCommand().equals("exitMain")) {
        	frame.removeAll();
        	createExitScreen();
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
		buttonEndGame = FrameUtils.makeButton("End Game", "endGame", true);
		buttonEndGame.addActionListener(this);
		buttonExitMain = FrameUtils.makeButton("   Exit  ", "exitMain", false);
		buttonExitMain.addActionListener(this);

		//Add the buttons and version label to the proper panels.
		lowerPanel.setLayout(new GridBagLayout());
		version.setFont(new Font("arial", Font.BOLD, 9));
		lowerPanel.add(version, FrameUtils.gbLayoutNormal(0, 1));
		lowerPanel.add(buttonExitMain, FrameUtils.gbLayoutNormal(1, 1));
		dummy.setFont(new Font("arial", Font.BOLD, 9));
		lowerPanel.add(dummy, FrameUtils.gbLayoutNormal(2, 1));

		//Add items to the middle panel.
		middlePanel.setLayout(new GridBagLayout());
		middlePanel.add(buttonNewGame, FrameUtils.gbLayoutNormal(0, 0));
		middlePanel.add(buttonEndGame, FrameUtils.gbLayoutNormal(0, 0));
		middlePanel.add(buttonSetup, FrameUtils.gbLayoutNormal(0, 1));
		middlePanel.add(buttonEditGame, FrameUtils.gbLayoutNormal(0, 2));
		middlePanel.add(buttonContGame, FrameUtils.gbLayoutNormal(0, 3));
		middlePanel.add(buttonPlay, FrameUtils.gbLayoutNormal(0, 3));

			
		//Adds these buttons to the middle panel when the game is in various modes.
		if (isGameStarted) {
			buttonNewGame.setVisible(false);
			buttonPlay.setVisible(false);
		} else if (isGameWon || isGameLost){
			buttonEndGame.setVisible(false);
			buttonContGame.setVisible(false);
			buttonPlay.setVisible(false);
			buttonSetup.setVisible(false);
		} else if (isSetupDone){
			buttonEndGame.setVisible(false);
			buttonContGame.setVisible(false);
			buttonEditGame.setVisible(false);
		} else {
			buttonEndGame.setVisible(false);
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
	 * Creates an end game screen.
	 */
	public void createEndGameScreen() {
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("save menu");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();
		
		//Makes all the needed buttons.
		buttonSave = FrameUtils.makeButton(" Save ", "save", false);
		buttonSave.addActionListener(this);
		buttonReturn = FrameUtils.makeButton("Main Menu", "mainMenu", false);
		buttonReturn.addActionListener(this);
		
		//Add buttons to the screen.
		lowerPanel.add(buttonReturn);
		
		//Add the content to be saved.
		middlePanel.setLayout(new GridBagLayout());
		
		//Add wording
		if(!isGameStarted) {
			message1.setText("There is nothing to save.");
			message1.setForeground(Main.labelColor);
			message1.setFont(new Font("arial", Font.BOLD, 12));
			middlePanel.add(message1, FrameUtils.gbLayoutNormal(0, 0));

			message2.setText("Press the Main Menu button.");
			message2.setForeground(Main.labelColor);
			message2.setFont(new Font("arial", Font.BOLD, 12));
			middlePanel.add(message2, FrameUtils.gbLayoutNormal(0, 1));
		}
		
		//Display only if the game has started.
		if(isGameStarted) {
			message1.setText("Check the items you wish");
			message1.setForeground(Main.labelColor);
			message1.setFont(new Font("arial", Font.BOLD, 12));
			middlePanel.add(message1, FrameUtils.gbLayoutTightDouble(0, 0));

			message2.setText("to save in separate files.");
			message2.setForeground(Main.labelColor);
			message2.setFont(new Font("arial", Font.BOLD, 12));
			middlePanel.add(message2, FrameUtils.gbLayoutTightDouble(0, 1));

			middlePanel.add(optionsLabel, FrameUtils.gbLayoutTight(0, 2));
			middlePanel.add(optionsCheckbox, FrameUtils.gbLayoutTight(2, 2));
			team1Checkbox.addItemListener(this);

			//Add the save button if the game has started.
			lowerPanel.add(buttonSave);

			//Show when four handed team based game.
			if (isFourHandedTeams){
				Label team1 = new Label(Main.team1);
				Label team2 = new Label(Main.team2);

				//Add items to the middle panel.
				middlePanel.add(team1Label, FrameUtils.gbLayoutTight(0, 3));
				middlePanel.add(team1, FrameUtils.gbLayoutTight(1, 3));
				middlePanel.add(team1Checkbox, FrameUtils.gbLayoutTight(2, 3));
				team1Checkbox.addItemListener(this);

				middlePanel.add(team2Label, FrameUtils.gbLayoutTight(0, 4));
				middlePanel.add(team2, FrameUtils.gbLayoutTight(1, 4));
				middlePanel.add(team2Checkbox, FrameUtils.gbLayoutTight(2, 4));
				team2Checkbox.addItemListener(this);
				
				//Add the previous team if there was one.
				if(teamPrevious != null) {
					Label teamPrevious = new Label(Main.teamPrevious.name);

					//Add items to the middle panel.
					middlePanel.add(teamPreviousLabel, FrameUtils.gbLayoutTight(0, 5));
					middlePanel.add(teamPrevious, FrameUtils.gbLayoutTight(1, 5));
					middlePanel.add(teamPreviousCheckbox, FrameUtils.gbLayoutTight(2, 5));
					teamPreviousCheckbox.addItemListener(this);
				}
			} else {
				Label player1 = new Label(Main.player1);
				Label player2 = new Label(Main.player2);
				Label player3 = new Label(Main.player3);

				//Add items to the middle panel.
				middlePanel.add(player1Label, FrameUtils.gbLayoutTight(0, 3));
				middlePanel.add(player1, FrameUtils.gbLayoutTight(1, 3));
				middlePanel.add(player1Checkbox, FrameUtils.gbLayoutTight(2, 3));
				player1Checkbox.addItemListener(this);

				middlePanel.add(player2Label, FrameUtils.gbLayoutTight(0, 4));
				middlePanel.add(player2, FrameUtils.gbLayoutTight(1, 4));
				middlePanel.add(player2Checkbox, FrameUtils.gbLayoutTight(2, 4));
				player2Checkbox.addItemListener(this);
			
				middlePanel.add(player3Label, FrameUtils.gbLayoutTight(0, 5));
				middlePanel.add(player3, FrameUtils.gbLayoutTight(1, 5));
				middlePanel.add(player3Checkbox, FrameUtils.gbLayoutTight(2, 5));
				player3Checkbox.addItemListener(this);

				//Add the four player if there is one.
				if(isFourHandedSingle) {
					Label player4 = new Label(Main.player4);

					//Add items to the middle panel.
					middlePanel.add(player4Label, FrameUtils.gbLayoutTight(0, 6));
					middlePanel.add(player4, FrameUtils.gbLayoutTight(1, 6));
					middlePanel.add(player4Checkbox, FrameUtils.gbLayoutTight(2, 6));
					player4Checkbox.addItemListener(this);
				}

				//Add the previous player if there was one.
				if(playerPrevious != null) {
					Label playerPrevious = new Label(Main.playerPrevious.player);

					//Add items to the middle panel.
					middlePanel.add(playerPreviousLabel, FrameUtils.gbLayoutTight(0, 7));
					middlePanel.add(playerPrevious, FrameUtils.gbLayoutTight(1, 7));
					middlePanel.add(playerPreviousCheckbox, FrameUtils.gbLayoutTight(2, 7));
					playerPreviousCheckbox.addItemListener(this);
				}
			}
		}

		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}

	/**
	 * Creates an exit screen.
	 */
	public void createExitScreen() {
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("exit menu");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();
		
		//Makes all the needed buttons.
		buttonReturn = FrameUtils.makeButton("  No  ", "mainMenu", false);
		buttonReturn.addActionListener(this);
		buttonExit = FrameUtils.makeButton(" Yes ", "exit", false);
		buttonExit.addActionListener(this);
		
		//Add buttons to the screen.
		lowerPanel.add(buttonExit);
		lowerPanel.add(buttonReturn);

		//Display exit message.
		message1.setText("Are you sure you want to exit?");
		message1.setForeground(Main.textColor);
		message1.setFont(new Font("arial", Font.BOLD, 12));
		middlePanel.add(message1, FrameUtils.gbLayoutNormal(0, 0));

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

	public void itemStateChanged(ItemEvent event) {
		//This is blank by design.
	}

	/**
	 * WindowListener for when the Window Close button is pressed. Will not
	 * work when a game has been started to prevent accidental closure.
	 */
	public void windowClosing(WindowEvent event) {
		if (isExitAllowed) exit();
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
		//Set the default screen skin.
		Utils.setSkinSelected();
		
		//Determine if a game ini has been created.
		if (!iniFile.exists()) {
			//Create an ini setup screen.
			IniSetup setup = new IniSetup(frame);
			setup.createSetupScreen();
		}

		//Load the ini file.
		FileUtils.loadIniFile();
			
		//Exit if ini fails to load.
		if (iniFailed) {
			Main exit = new Main();
			exit.createExitScreen();
		}
			
		//Extract all the information in the ini ArrayList.
		Utils.parseIni();

		Main game = new Main();
		game.createMainMenuScreen();
		
		//Don't play the sound if debug mode.
		if (!DEBUG && sounds) {
			//Play the startup sound.
			AudioPlayer ap = new AudioPlayer();
			try {
				ap.playAudio(Main.soundGameStart);
			} catch (AudioException e) {
				FrameUtils.showDialogBox("Sound file could not play.");
			}
		}
	}
}
