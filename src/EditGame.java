/**
 * EditGame.java
 * 
 * This class will let the scorer go to any round of the game and either view
 * or edit the game data. This is useful if the program has a bug in it or the
 * game data was entered wrong while playing the game.
 *
 * @author David Hoffman
 */

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditGame extends Frame implements ActionListener {

	/**
	 * Declare needed variables.
	 */
	private static final long serialVersionUID = 1L;

	static int roundToEdit = 0;
	static int editedRound = 0;
	
	static boolean playerChanged = false;
	static boolean scoreChanged = false;
	
	Label player = new Label("  Player");
	Label bid = new Label("Bid");
	Label tricksTaken = new Label("Tricks Taken");
	Label bags = new Label("Bags");
	Label score = new Label("Score");
	Label set = new Label("Set");
	Label dealer = new Label("Dealer");
	Label playerLabel = new Label();
	Label bidLabel = new Label();
	Label tricksTakenLabel = new Label();
	Label bagsLabel = new Label();
	Label scoreLabel = new Label();
	Label setLabel = new Label();
	Label dealerLabel = new Label();
	Label roundLabel = new Label("   Round");

	TextField playerText = new TextField();
	TextField bidText = new TextField();
	TextField tricksTakenText = new TextField();
	TextField bagsText = new TextField();
	TextField scoreText = new TextField();
	TextField setText = new TextField();
	TextField dealerText = new TextField();
	
	Panel upperPanel;
	Panel middlePanel;
	Panel lowerPanel;
	
	Button buttonViewPlayer;
	Button buttonEditPlayer;
	Button buttonShowViewer;
	Button buttonShowEditor;
	Button buttonCalculateScore;
	Button buttonReturnView;
	Button buttonReturnEdit;
	Button buttonReturnViewName;
	Button buttonReturnEditName;
	Button buttonReturnMain;
	
	static Choice choiceBox1;
	static Choice choiceBox2;
	
	ScrollPane sp = new ScrollPane();

	Frame frame = new Frame();

	/**
	 * Default constructor for the class.
	 */
    public EditGame(Frame frameIn){
    	frame = frameIn;
    }

	/**
	 * Performs the assigned tasks when the corresponding button is pressed.
	 * 
	 * @param event The action that was triggered by a button press.
	 */
    public void actionPerformed(ActionEvent event) {
        //Performs this action when the ViewRound button is pressed.
        if (event.getActionCommand().equals("viewPlayer")) {
        	frame.removeAll();
        	createViewPlayerScreen();
        }  

        //Performs this action when the EditRound button is pressed.
        if (event.getActionCommand().equals("editPlayer")) {
        	frame.removeAll();
        	createEditPlayerScreen();
        }  

        //Performs this action when the Calculate Score button is pressed.
        if (event.getActionCommand().equals("calculateScore")) {
        	//Recalculate the score for a three handed game.
        	if (Main.isThreeHanded) {
        		Utils.reCalculateScore(Main.playerOne, editedRound, scoreChanged);
        		Utils.reCalculateScore(Main.playerTwo, editedRound, scoreChanged);
        		Utils.reCalculateScore(Main.playerThree, editedRound, scoreChanged);
        	}
        	
        	//Recalculate the score for a four handed game.
        	if (Main.isFourHandedSingle) {
        		Utils.reCalculateScore(Main.playerOne, editedRound, scoreChanged);
        		Utils.reCalculateScore(Main.playerTwo, editedRound, scoreChanged);
        		Utils.reCalculateScore(Main.playerThree, editedRound, scoreChanged);
        		Utils.reCalculateScore(Main.playerFour, editedRound, scoreChanged);
        	}
       	
        	//Recalculate the score for a two team game.
        	if (Main.isFourHandedTeams) {
        		//Utils.reCalculateScore(Main.teamOne, editedRound, scoreChanged);
        		//Utils.reCalculateScore(Main.teamTwo, editedRound, scoreChanged);
        	}
        	
        	frame.removeAll();
        	createShowNewScoresScreen();
        }  

        //Performs this action when the ReturnView button is pressed.
        if (event.getActionCommand().equals("returnView")) {
        	frame.removeAll();
        	createEditGameScreen();
        }  

        //Performs this action when the ReturnEdit button is pressed.
        if (event.getActionCommand().equals("returnEdit")) {
        	frame.removeAll();
        	createEditGameScreen();
        }  

        //Performs this action when the ShowViewer button is pressed.
        if (event.getActionCommand().equals("showViewer")) {
        	frame.removeAll();
        	createViewPlayerNameScreen();
        }  

        //Performs this action when the ShowEditor button is pressed.
        if (event.getActionCommand().equals("showEditor")) {
        	frame.removeAll();
        	createEditPlayerNameScreen();
        }  

        //Performs this action when the ReturnViewName button is pressed.
        if (event.getActionCommand().equals("returnViewName")) {
        	frame.removeAll();
        	createViewPlayerScreen();
        }  

        //Performs this action when the ReturnEditName button is pressed.
        if (event.getActionCommand().equals("returnEditName")) {
        	frame.removeAll();
        	createEditPlayerScreen();
        }  

        //Performs this action when the ReturnMain button is pressed.
        if (event.getActionCommand().equals("returnMain")) {
        	frame.removeAll();

        	Main game = new Main();
        	game.createMainMenuScreen();
        }  
	}

	/**
     * Creates the Edit Game screen.
     */
    public void createEditGameScreen() {
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("edit game");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();
		
		//Make all the needed buttons.
		buttonViewPlayer = FrameUtils.makeButton("View Player", "viewPlayer", true);
		buttonViewPlayer.addActionListener(this);
		buttonEditPlayer = FrameUtils.makeButton("Edit Player", "editPlayer", true);
		buttonEditPlayer.addActionListener(this);
		buttonCalculateScore = FrameUtils.makeButton("Recalculate Scores", "calculateScore", false);
		buttonCalculateScore.addActionListener(this);
		buttonReturnMain = FrameUtils.makeButton("  Return  ", "returnMain", false);
		buttonReturnMain.addActionListener(this);
		
		//Add the buttons to the proper panels.
		lowerPanel.add(buttonReturnMain);
		
		//Add items to the middle panel.
		middlePanel.setLayout(new GridBagLayout());
		middlePanel.add(buttonViewPlayer, FrameUtils.gbLayoutNormal(0, 0));
		middlePanel.add(buttonEditPlayer, FrameUtils.gbLayoutNormal(0, 1));
		
		//Only show Calculate score button if a change has been made.
		if (playerChanged) {
			middlePanel.add(buttonCalculateScore, FrameUtils.gbLayoutNormal(0, 2));
		}

		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
    }
    
    /**
     * Creates the View Player Screen.
     */
    public void createViewPlayerScreen() {
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("view player");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();
		
		//Create the choice box.
		choiceBox1 = FrameUtils.makeCurrentPlayerList();
		
		//Make all the needed buttons.
		buttonShowViewer = FrameUtils.makeButton("  Show  ", "showViewer", false);
		buttonShowViewer.addActionListener(this);
		buttonReturnView = FrameUtils.makeButton("  Return  ", "returnView", false);
		buttonReturnView.addActionListener(this);

		middlePanel.setLayout(new GridBagLayout());
		middlePanel.add(player, FrameUtils.gbLayoutNormal(0, 0));
		player.setFont(new Font("arial", Font.BOLD, 16));
		middlePanel.add(choiceBox1, FrameUtils.gbLayoutNormal(0, 1));
		middlePanel.add(buttonShowViewer, FrameUtils.gbLayoutNormal(0, 2));
		
		//Add button to lower panel.
		lowerPanel.add(buttonReturnView);

		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}
    
    /**
     * Creates the ViewPlayerName Screen.
     */
    public void createViewPlayerNameScreen() {
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("name");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();

		//Make all the needed buttons.
		buttonReturnViewName = FrameUtils.makeButton("  Return  ", "returnViewName", false);
		buttonReturnViewName.addActionListener(this);
		
		//Add button to lower panel.
		lowerPanel.add(buttonReturnViewName);
		
		//Add scroll pane to the panel.
		middlePanel.add(sp);

		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
    }

	/**
	 * Creates the Edit Player Screen.
	 */
    public void createEditPlayerScreen() {
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("edit player");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();
		
		//Create the choice box.
		choiceBox1 = FrameUtils.makeRoundList();
		choiceBox2 = FrameUtils.makeCurrentPlayerList();
		
		//Make all the needed buttons.
		buttonShowEditor = FrameUtils.makeButton("  Show  ", "showEditor", false);
		buttonShowEditor.addActionListener(this);
		buttonReturnEdit = FrameUtils.makeButton("  Return  ", "returnEdit", false);
		buttonReturnEdit.addActionListener(this);

		//Add the buttons to the lower panel.
		lowerPanel.add(buttonReturnEdit);

		//Add items to the middle panel.
		middlePanel.setLayout(new GridBagLayout());
		middlePanel.add(roundLabel, FrameUtils.gbLayoutNormal(0, 0));
		player.setFont(new Font("arial", Font.BOLD, 16));
		middlePanel.add(choiceBox1, FrameUtils.gbLayoutNormal(0, 1));
		middlePanel.add(player, FrameUtils.gbLayoutNormal(0, 2));
		player.setFont(new Font("arial", Font.BOLD, 16));
		middlePanel.add(choiceBox2, FrameUtils.gbLayoutNormal(0, 3));
		middlePanel.add(buttonShowEditor, FrameUtils.gbLayoutNormal(0, 4));

		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
    }

	/**
	 * Creates the EditPlayerName Screen.
	 */
    public void createEditPlayerNameScreen() {
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("name");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();

		//Make all the needed buttons.
		buttonReturnEditName = FrameUtils.makeButton("  Return  ", "returnEditName", false);
		buttonReturnEditName.addActionListener(this);

		//Add button to lower panel.
		lowerPanel.add(buttonReturnEditName);

		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
    }

	/**
	 * 
	 */
    public void createShowNewScoresScreen() {
		// TODO Auto-generated method stub
		
	}
}
