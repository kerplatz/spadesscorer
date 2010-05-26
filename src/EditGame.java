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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditGame extends Frame implements ActionListener {

	/**
	 * Declare needed variables.
	 */
	private static final long serialVersionUID = 1L;

	static int roundToEdit;
	
	Label roundLabel = new Label("   Round");

	Panel upperPanel;
	Panel middlePanel;
	Panel lowerPanel;
	
	Button buttonViewRound;
	Button buttonEditRound;
	Button buttonEditMore;
	Button buttonCalculateScore;
	Button buttonReturnEdit;
	Button buttonReturnMain;
	
	static Choice choiceBox;

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
        if (event.getActionCommand().equals("viewRound")) {
        	if (Utils.isRoundReady()) {
            	frame.removeAll();
            	createViewRoundScreen();
        	}
        }  

        //Performs this action when the EditRound button is pressed.
        if (event.getActionCommand().equals("editRound")) {
        	if (Utils.isRoundReady()) {
            	frame.removeAll();
            	createEditRoundScreen();
        	}
        }  

        //Performs this action when the Edit More button is pressed.
        if (event.getActionCommand().equals("editMore")) {
        	frame.removeAll();
        	createEditRoundScreen();
        }  

        //Performs this action when the Calculate Score button is pressed.
        if (event.getActionCommand().equals("calculateScore")) {
        	frame.removeAll();
        	//calculate scores();
        }  

        //Performs this action when the ReturnEdit button is pressed.
        if (event.getActionCommand().equals("returnEdit")) {
        	frame.removeAll();
        	createEditGameScreen();
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
		
		//Create the choice box.
		choiceBox = FrameUtils.makeRoundList();
		
		//Make all the needed buttons.
		buttonViewRound = FrameUtils.makeButton("View Round", "viewRound", true);
		buttonViewRound.addActionListener(this);
		buttonEditRound = FrameUtils.makeButton("Edit Round", "editRound", true);
		buttonEditRound.addActionListener(this);
		buttonReturnMain = FrameUtils.makeButton("  Return  ", "returnMain", false);
		buttonReturnMain.addActionListener(this);
		
		//Add the buttons to the proper panels.
		lowerPanel.add(buttonReturnMain);
		
		middlePanel.setLayout(new GridBagLayout());
		middlePanel.add(roundLabel, FrameUtils.gbLayoutNormal(0, 0));
		roundLabel.setFont(new Font("arial", Font.BOLD, 16));
		middlePanel.add(choiceBox, FrameUtils.gbLayoutPaddingBottom(0, 1));
		middlePanel.add(buttonViewRound, FrameUtils.gbLayoutNormal(0, 2));
		middlePanel.add(buttonEditRound, FrameUtils.gbLayoutNormal(0, 3));

		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
    }
    
    /**
     * Creates the Edit Round Screen.
     */
    public void createEditRoundScreen() {
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("edit round");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();
		
		//Make all the needed buttons.
		buttonCalculateScore = FrameUtils.makeButton("Calculate Score", "calculateScore", false);
		buttonCalculateScore.addActionListener(this);
		buttonEditMore = FrameUtils.makeButton("Edit More", "editMore", false);
		buttonEditMore.addActionListener(this);
		buttonReturnEdit = FrameUtils.makeButton("  Return  ", "returnEdit", false);
		buttonReturnEdit.addActionListener(this);
		
		//Add the buttons to the proper panels.
		lowerPanel.add(buttonReturnEdit);
		
		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}

	/**
	 * Creates the View Round Screen
	 */
    public void createViewRoundScreen() {
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("view round");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();
		
		//Make all the needed buttons.
		buttonReturnEdit = FrameUtils.makeButton("  Return  ", "returnEdit", false);
		buttonReturnEdit.addActionListener(this);
		
		//Add the buttons to the proper panels.
		lowerPanel.add(buttonReturnEdit);
		
		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}
}
