/** FINISHED
 * GameSetup.java
 * 
 * This class sets up the scorer for game play by asking the score keeper many
 * questions on several setup screens. This information is then used by the
 * various types of games that can be played. The screens are:
 * 
 * Setup Menu-
 * The main menu screen where you get access to the other setup screens.
 * 
 * Select Game Type-
 * This is where you select from 1 of 3 types of game play.
 * 
 * Setup Players-
 * The screen where the players names are entered.
 * 
 * Select Dealer-
 * The player who first deals the cards is selected here.
 *
 * @author David Hoffman
 */

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GameSetup extends Frame implements ActionListener, ItemListener {
	
    /**
	 * Declare needed variables.
	 */
	private static final long serialVersionUID = 1L;
	
	boolean hasPlayerChanged = false;

	static Choice choiceBoxPlayer1 = new Choice();
	static Choice choiceBoxPlayer2 = new Choice();
	static Choice choiceBoxPlayer3 = new Choice();
	static Choice choiceBoxPlayer4 = new Choice();
	
	static CheckboxGroup gameTypeGroup = new CheckboxGroup();
	static Checkbox gameTypeHidden = new Checkbox("Hidden", gameTypeGroup, false);
	static Checkbox threeHanded = new Checkbox("Three Handed", gameTypeGroup, false);
	static Checkbox fourHandedSingle = new Checkbox("4 Handed - Single", gameTypeGroup, false);
	static Checkbox fourHandedTeams = new Checkbox("4 Handed - Teams", gameTypeGroup, false);

	static CheckboxGroup dealerGroup = new CheckboxGroup();
	static Checkbox dealerHidden = new Checkbox("Hidden", dealerGroup, false);
	static Checkbox player1IsDealer = new Checkbox("Player 1", dealerGroup, false);
	static Checkbox player2IsDealer = new Checkbox("Player 2", dealerGroup, false);
	static Checkbox player3IsDealer = new Checkbox("Player 3", dealerGroup, false);
	static Checkbox player4IsDealer = new Checkbox("Player 4", dealerGroup, false);

	static Checkbox player1Checkbox = new Checkbox("Change", false);
	static Checkbox player2Checkbox = new Checkbox("Change", false);
	static Checkbox player3Checkbox = new Checkbox("Change", false);
	static Checkbox player4Checkbox = new Checkbox("Change", false);

	Label player1Label = new Label("  Player 1  ");
	Label player2Label = new Label("  Player 2  ");
	Label player3Label = new Label("  Player 3  ");
	Label player4Label = new Label("  Player 4  ");
		
	Panel upperPanel;
	Panel middlePanel;
	Panel lowerPanel;
	
	Button buttonSelectGameType;
	Button buttonReturnGameType;
	Button buttonSetupPlayers;
	Button buttonClearPlayers;
	Button buttonReturnPlayers;
	Button buttonSelectDealer;
	Button buttonReturnDealer;
	Button buttonGameOptions;
	Button buttonReturnMain;
	Button buttonContinueSetup1;
	Button buttonContinueSetup2;

	GridBagConstraints gridBagConstraints;

	Frame frame = new Frame();

	/**
	 * Default constructor for the class.
	 */
    public GameSetup(Frame frameIn){
    	frame = frameIn;
    }

	/**
	 * Performs the assigned tasks when the corresponding button is pressed.
	 * 
	 * @param event The action that was triggered by a button press.
	 */
    public void actionPerformed(ActionEvent event) {
        //Performs this action when the SelectGameType button is pressed.
        if (event.getActionCommand().equals("selectGameType")) {
        	frame.removeAll();
        	createSelectGameTypeScreen();
        }

        //Performs this action when the ReturnGameType button is pressed.
        if (event.getActionCommand().equals("returnGameType")) {
        	if (Utils.isSelectGameTypeDone()) {
            	frame.removeAll();
            	createSetupScreen();
            	
            	//Change the default values for game options.
            	Utils.defaultGameOptions();
        	}
        }  

        //Performs this action when the ContinueSetup1 button is pressed.
        if (event.getActionCommand().equals("continueSetup1")) {
        	if(Utils.isSelectGameTypeDone()) {
            	frame.removeAll();
            	createSetupPlayersScreen();
            	
            	//Change the default values for game options.
            	Utils.defaultGameOptions();
            	
            	//Show dialog box reminder for determining player rotation.
    			FrameUtils.showDialogBox("Start with yourself, then go CW.");
        	}
        }

        //Performs this action when the SetupPlayers button is pressed.
        if (event.getActionCommand().equals("setupPlayers")) {
        	frame.removeAll();
        	createSetupPlayersScreen();
        }

        //Performs this action when the ClearPlayers button is pressed.
        if (event.getActionCommand().equals("clearPlayers")) {
        	Utils.clearPlayerNames();
        }  

        //Performs this action when the ReturnPlayers button is pressed.
        if (event.getActionCommand().equals("returnPlayers")) {
        	if (Utils.isPlayersSetupDone()) {
            	if (hasPlayerChanged) {
            		//Determine which player was changed.
            		if (player1Checkbox.getState()) {
            			Player temp = new Player(Main.player1,
            					Main.playerOne.score, Main.playerOne.bags);
            			Utils.changePlayer(Main.playerOne, temp, 1);
            		}
            		if (player2Checkbox.getState()) {
            			Player temp = new Player(Main.player2,
            					Main.playerTwo.score, Main.playerTwo.bags);
            			Utils.changePlayer(Main.playerTwo, temp, 2);
            		}
            		if (player3Checkbox.getState()) {
            			Player temp = new Player(Main.player3,
            					Main.playerThree.score, Main.playerThree.bags);
            			Utils.changePlayer(Main.playerThree, temp, 3);
            		}
            		if (player4Checkbox.getState()) {
            			Player temp = new Player(Main.player4,
            					Main.playerFour.score, Main.playerFour.bags);
            			Utils.changePlayer(Main.playerFour, temp, 4);
            		}
            	}
        		frame.removeAll();
            	createSetupScreen();
        	}
        }  

        //Performs this action when the ContinueSetup2 button is pressed.
        if (event.getActionCommand().equals("continueSetup2")) {
        	if (Utils.isPlayersSetupDone()) {
             	frame.removeAll();
            	createSelectDealerScreen();
        	}
        }
        
        //Performs this action when the SelectDealer button is pressed.
        if (event.getActionCommand().equals("selectDealer")) {
        	frame.removeAll();
        	createSelectDealerScreen();
        }

        //Performs this action when the ReturnDealer button is pressed.
        if (event.getActionCommand().equals("returnDealer")) {
        	if (Utils.isDealerSelectionDone()) {
        		Main.isSetupDone = true;
        		frame.removeAll();
             	createSetupScreen();
        	}
        }  

        //Performs this action when the GameOptions button is pressed.
        if (event.getActionCommand().equals("gameOptions")) {
        	frame.removeAll();

        	GameOptions options = new GameOptions(frame);
        	options.createGameOptionsScreen();
        }  

        //Performs this action when the ReturnMain button is pressed.
        if (event.getActionCommand().equals("returnMain")) {
        	frame.removeAll();
        	
        	Main game = new Main();
        	game.createMainMenuScreen();
        }  
	}

	/**
	 * 
	 * @param event
	 */
    public void itemStateChanged(ItemEvent event) {
    	if (event.getItemSelectable().equals(player1Checkbox)) {
			if (player1Checkbox.getState()) {
				player2Checkbox.setEnabled(false);
				player3Checkbox.setEnabled(false);
				player4Checkbox.setEnabled(false);
				choiceBoxPlayer2.setEnabled(false);
				choiceBoxPlayer3.setEnabled(false);
				choiceBoxPlayer4.setEnabled(false);
				hasPlayerChanged = true;
			} else {
				player2Checkbox.setEnabled(true);
				player3Checkbox.setEnabled(true);
				player4Checkbox.setEnabled(true);
				choiceBoxPlayer2.setEnabled(true);
				choiceBoxPlayer3.setEnabled(true);
				choiceBoxPlayer4.setEnabled(true);
				hasPlayerChanged = false;
			}
    	}
    	if (event.getItemSelectable().equals(player2Checkbox)) {
			if (player2Checkbox.getState()) {
				player1Checkbox.setEnabled(false);
				player3Checkbox.setEnabled(false);
				player4Checkbox.setEnabled(false);
				choiceBoxPlayer1.setEnabled(false);
				choiceBoxPlayer3.setEnabled(false);
				choiceBoxPlayer4.setEnabled(false);
				hasPlayerChanged = true;
			} else {
				player1Checkbox.setEnabled(true);
				player3Checkbox.setEnabled(true);
				player4Checkbox.setEnabled(true);
				choiceBoxPlayer1.setEnabled(true);
				choiceBoxPlayer3.setEnabled(true);
				choiceBoxPlayer4.setEnabled(true);
				hasPlayerChanged = false;
			}
    	}
    	if (event.getItemSelectable().equals(player3Checkbox)) {
			if (player3Checkbox.getState()) {
				player1Checkbox.setEnabled(false);
				player2Checkbox.setEnabled(false);
				player4Checkbox.setEnabled(false);
				choiceBoxPlayer1.setEnabled(false);
				choiceBoxPlayer2.setEnabled(false);
				choiceBoxPlayer4.setEnabled(false);
				hasPlayerChanged = true;
			} else {
				player1Checkbox.setEnabled(true);
				player2Checkbox.setEnabled(true);
				player4Checkbox.setEnabled(true);
				choiceBoxPlayer1.setEnabled(true);
				choiceBoxPlayer2.setEnabled(true);
				choiceBoxPlayer4.setEnabled(true);
				hasPlayerChanged = false;
			}
    	}
    	if (event.getItemSelectable().equals(player4Checkbox)) {
			if (player4Checkbox.getState()) {
				player1Checkbox.setEnabled(false);
				player2Checkbox.setEnabled(false);
				player3Checkbox.setEnabled(false);
				choiceBoxPlayer1.setEnabled(false);
				choiceBoxPlayer2.setEnabled(false);
				choiceBoxPlayer3.setEnabled(false);
				hasPlayerChanged = true;
			} else {
				player1Checkbox.setEnabled(true);
				player2Checkbox.setEnabled(true);
				player3Checkbox.setEnabled(true);
				choiceBoxPlayer1.setEnabled(true);
				choiceBoxPlayer2.setEnabled(true);
				choiceBoxPlayer3.setEnabled(true);
				hasPlayerChanged = false;
			}
    	}
	}

	/**
	 * Creates the Setup screen.
	 */
	public void createSetupScreen() {
		//Set the default screen skin.
		Utils.setSkinSelected();

		//Do this if the Setup routine has not been shown previously.
		if (!Main.isSetupDone) {
			createSelectGameTypeScreen();
			createSetupPlayersScreen();
			createSelectDealerScreen();
		}

		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("setup menu");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();
		
		//Make all the needed buttons.
		buttonSelectGameType = FrameUtils.makeButton("  Game Type  ", "selectGameType", true);
		buttonSelectGameType.addActionListener(this);
		buttonSetupPlayers = FrameUtils.makeButton("Setup Players", "setupPlayers", true);
		buttonSetupPlayers.addActionListener(this);
		buttonSelectDealer = FrameUtils.makeButton("Select Dealer", "selectDealer", true);
		buttonSelectDealer.addActionListener(this);
		buttonGameOptions = FrameUtils.makeButton(" Game Options", "gameOptions", true);
		buttonGameOptions.addActionListener(this);
		buttonReturnMain = FrameUtils.makeButton("    Return   ", "returnMain", false);
		buttonReturnMain.addActionListener(this);
		
		//Add the buttons to the proper panels.
		lowerPanel.add(buttonReturnMain);
		
		middlePanel.setLayout(new GridBagLayout());
		middlePanel.add(buttonSelectGameType, FrameUtils.gbLayoutNormal(0, 0));
		middlePanel.add(buttonSetupPlayers, FrameUtils.gbLayoutNormal(0, 1));
		middlePanel.add(buttonSelectDealer, FrameUtils.gbLayoutNormal(0, 2));
		middlePanel.add(buttonGameOptions, FrameUtils.gbLayoutNormal(0, 3));

		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}

	/**
	 * Creates the GameType screen.
	 */
	public void createSelectGameTypeScreen() {
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("select game type");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();

		//Make all the needed buttons.
		buttonContinueSetup1 = FrameUtils.makeButton("  Continue  ", "continueSetup1", false);
		buttonContinueSetup1.addActionListener(this);
		buttonReturnGameType = FrameUtils.makeButton("   Return   ", "returnGameType", false);
		buttonReturnGameType.addActionListener(this);
		
		//Adds the appropriate button to the panel depending if setup has been
		//shown previously.
		if (Main.isSetupDone) {
			lowerPanel.add(buttonReturnGameType);
		} else {
			lowerPanel.add(buttonContinueSetup1);
		}

		middlePanel.setLayout(new GridBagLayout());
		middlePanel.add(threeHanded, FrameUtils.gbLayoutWest(0, 0));
		middlePanel.add(fourHandedSingle, FrameUtils.gbLayoutWest(0, 1));
		middlePanel.add(fourHandedTeams, FrameUtils.gbLayoutWest(0, 2));
		
		//Disable the other selections when a game has started.
		if (Main.isThreeHanded && Main.isGameStarted) {
			fourHandedSingle.setEnabled(false);
			fourHandedTeams.setEnabled(false);
		}
		if (Main.isFourHandedSingle && Main.isGameStarted) {
			threeHanded.setEnabled(false);
			fourHandedTeams.setEnabled(false);
		}
		if (Main.isFourHandedTeams && Main.isGameStarted) {
			threeHanded.setEnabled(false);
			fourHandedSingle.setEnabled(false);
		}
		
		//Makes the selected Game Type show if it was set already.
		Utils.showPreviousSelectedGameType();

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
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("setup players");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();
		
		//Creates the choice box.
		choiceBoxPlayer1 = FrameUtils.makePlayerList();
		choiceBoxPlayer2 = FrameUtils.makePlayerList();
		choiceBoxPlayer3 = FrameUtils.makePlayerList();
		choiceBoxPlayer4 = FrameUtils.makePlayerList();

		//Make all the needed buttons.
		buttonClearPlayers = FrameUtils.makeButton("  Clear  ", "clearPlayers", false);
		buttonClearPlayers.addActionListener(this);
		buttonContinueSetup2 = FrameUtils.makeButton("   Continue   ", "continueSetup2", false);
		buttonContinueSetup2.addActionListener(this);
		buttonReturnPlayers = FrameUtils.makeButton("  Return  ", "returnPlayers", false);
		buttonReturnPlayers.addActionListener(this);

		//Add the buttons to the proper panels.
		lowerPanel.add(buttonClearPlayers);

		//Adds the appropriate button to the panel depending if setup has been
		//shown previously.
		if (Main.isSetupDone) {
			lowerPanel.add(buttonReturnPlayers);
		} else {
			lowerPanel.add(buttonContinueSetup2);
		}

		//Add player labels and text fields.
		middlePanel.setLayout(new GridBagLayout());
		middlePanel.add(player1Label, FrameUtils.gbLayoutNormal(0, 0));
		middlePanel.add(choiceBoxPlayer1, FrameUtils.gbLayoutNormal(1, 0));
	    middlePanel.add(player2Label, FrameUtils.gbLayoutNormal(0, 1));
		middlePanel.add(choiceBoxPlayer2, FrameUtils.gbLayoutNormal(1, 1));
		middlePanel.add(player3Label, FrameUtils.gbLayoutNormal(0, 2));
		middlePanel.add(choiceBoxPlayer3, FrameUtils.gbLayoutNormal(1, 2));

		//Hide the check boxes if the game has not been started.
		if (Main.isGameStarted) {
			middlePanel.add(player2Checkbox, FrameUtils.gbLayoutNormal(2, 1));
			player2Checkbox.addItemListener(this);
			middlePanel.add(player1Checkbox, FrameUtils.gbLayoutNormal(2, 0));
			player1Checkbox.addItemListener(this);
			middlePanel.add(player3Checkbox, FrameUtils.gbLayoutNormal(2, 2));
			player3Checkbox.addItemListener(this);
			
			//Disable players if the clear button was pressed.
			if (player1Checkbox.getState()) {
				choiceBoxPlayer2.setEnabled(false);
				choiceBoxPlayer3.setEnabled(false);
				choiceBoxPlayer4.setEnabled(false);
			}
			if (player2Checkbox.getState()) {
				choiceBoxPlayer1.setEnabled(false);
				choiceBoxPlayer3.setEnabled(false);
				choiceBoxPlayer4.setEnabled(false);
			}
			if (player3Checkbox.getState()) {
				choiceBoxPlayer1.setEnabled(false);
				choiceBoxPlayer2.setEnabled(false);
				choiceBoxPlayer4.setEnabled(false);
			}
			if (player4Checkbox.getState()) {
				choiceBoxPlayer1.setEnabled(false);
				choiceBoxPlayer2.setEnabled(false);
				choiceBoxPlayer3.setEnabled(false);
			}
		}
		
		//Don't show fourth player if playing 3 handed game.
		if (!Main.isThreeHanded) {
			middlePanel.add(player4Label, FrameUtils.gbLayoutNormal(0, 3));
			middlePanel.add(choiceBoxPlayer4, FrameUtils.gbLayoutNormal(1, 3));
			
			//Hide the check boxes if the game has not been started.
			if (Main.isGameStarted) {
				middlePanel.add(player4Checkbox, FrameUtils.gbLayoutNormal(2, 3));
				player4Checkbox.addItemListener(this);
			}
		}
		
		//Shows the previous players, if there were any.
		Utils.showPreviousPlayerNames();
		
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
		player1IsDealer.setLabel("   " + Main.player1);
		player2IsDealer.setLabel("   " + Main.player2);
		player3IsDealer.setLabel("   " + Main.player3);
		player4IsDealer.setLabel("   " + Main.player4);
		
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("select dealer");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();
		
		//Makes all the needed buttons.
		buttonReturnDealer = FrameUtils.makeButton("  Return  ", "returnDealer", false);
		buttonReturnDealer.addActionListener(this);
		
		//Adds the buttons to the proper panels.
		lowerPanel.add(buttonReturnDealer);

		middlePanel.setLayout(new GridBagLayout());
		middlePanel.add(player1IsDealer, FrameUtils.gbLayoutWest(0, 0));
		middlePanel.add(player2IsDealer, FrameUtils.gbLayoutWest(0, 1));
		middlePanel.add(player3IsDealer, FrameUtils.gbLayoutWest(0, 2));
		
		//Don't show fourth player if playing 3 handed game.
		if (!Main.isThreeHanded) {
			middlePanel.add(player4IsDealer, FrameUtils.gbLayoutWest(0, 3));
		}
		
		//Makes the selected dealer show if it was set already.
		Utils.showPreviousSelectedDealer();
		
		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}
}
