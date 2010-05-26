/** FINISHED
 * GameOptions.java
 * 
 * This class is only concerned about showing and changing the various game
 * options that are provided. Defaults to every option are maintained so the
 * person keeping score does not need to make any changes to the game options.
 * The screens are:
 * 
 * Game Options-
 * The main game options screen where you get access to the other options screens.
 * 
 * Select Skin-
 * Allows the scorer to select different college color schemes in the state.
 * 
 * Change Options-
 * Allows the game to be configured for different values for bags, win and lose
 * scores, nil and double nil values, and where to allow nil or double nil.
 *
 * @author kerplatz
 */

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GameOptions extends Frame implements ActionListener,
												  ItemListener {

	/**
	 * Declare needed variables.
	 */
	private static final long serialVersionUID = 1L;
	
	static TextField bagValueTextField = new TextField(5);
	static TextField nilValueTextField = new TextField(5);
	static TextField doubleNilValueTextField = new TextField(5);
	static TextField winScoreTextField = new TextField(5);
	static TextField loseScoreTextField = new TextField(5);
	
	static Checkbox nilAllowed = new Checkbox("nil Allowed", true);
	static Checkbox doubleNilAllowed = new Checkbox("double Allowed", true);
	
	static CheckboxGroup gameSkinSelection = new CheckboxGroup();
	static Checkbox iowaState = new Checkbox("ISU", gameSkinSelection, true);
	static Checkbox iowa = new Checkbox("U of I", gameSkinSelection, false);
	static Checkbox northernIowa = new Checkbox("UNI", gameSkinSelection, false);
	
	Label bagValueLabel = new Label("   Bag Value   ");
	Label nilValueLabel = new Label("   Nil Value   ");
	Label doubleNilValueLabel = new Label(" Dbl Nil Value ");
	Label winScoreLabel = new Label("   Win Score   ");
	Label loseScoreLabel = new Label("  Lose Score   ");

	Panel upperPanel;
	Panel middlePanel;
	Panel lowerPanel;
	
	Button buttonGameOptions;
	Button buttonDefaultOptions;
	Button buttonReturnOptions;
	Button buttonSelectSkin;
	Button buttonReturnSkin;
	Button buttonReturnSetup;

	GridBagConstraints gridBagConstraints;

	Frame frame = new Frame();

	/**
	 * Default constructor for the class.
	 */
    public GameOptions (Frame frameIn){
    	frame = frameIn;
    }

	/**
	 * Performs the assigned tasks when the corresponding button is pressed.
	 * 
	 * @param event The action that was triggered by a button press.
	 */
	public void actionPerformed(ActionEvent event) {
        //Performs this action when the GameOptions button is pressed.
        if (event.getActionCommand().equals("gameOptions")) {
        	frame.removeAll();
        	createOptionsScreen();
        }  

        //Performs this action when the DefaultOptions button is pressed.
        if (event.getActionCommand().equals("defaultOptions")) {
        	frame.removeAll();
        	Utils.defaultGameOptions();
        	createOptionsScreen();
        }  

        //Performs this action when the ReturnOptions button is pressed.
        if (event.getActionCommand().equals("returnOptions")) {
        	if (Utils.isOptionsDone()) {
            	frame.removeAll();
            	createGameOptionsScreen();
        	}
        }  

        //Performs this action when the SelectSkin button is pressed.
        if (event.getActionCommand().equals("selectSkin")) {
        	frame.removeAll();
        	createChooseSkinScreen();
        }  

        //Performs this action when the ReturnSkin button is pressed.
        if (event.getActionCommand().equals("returnSkin")) {
        	if (Utils.isChooseSkinDone()) {
            	frame.removeAll();
            	Utils.setSkinSelected();
            	createGameOptionsScreen();
        	}
        }  

        //Performs this action when the ReturnSetup button is pressed.
        if (event.getActionCommand().equals("returnSetup")) {
        	frame.removeAll();

        	GameSetup setup = new GameSetup(frame);
        	setup.createSetupScreen();
        }  
	}

	/**
	 * Performs the assigned tasks when the check box state has changed.
	 * 
	 * @param event The action that was triggered by the state change.
	 */
	public void itemStateChanged(ItemEvent event) {
		if (event.getItemSelectable().equals(nilAllowed)) {
			if (nilAllowed.getState()) {
				Main.isNilAllowed = true;
				nilValueTextField.setEditable(true);
			} else {
				Main.isNilAllowed = false;
				nilValueTextField.setEditable(false);
			}
		}

		if (event.getItemSelectable().equals(doubleNilAllowed)) {
			if (doubleNilAllowed.getState()) {
				Main.isDoubleNilAllowed = true;
				doubleNilValueTextField.setEditable(true);
			} else {
				Main.isDoubleNilAllowed = false;
				doubleNilValueTextField.setEditable(false);
			}
		}
	}

	/**
	 * Creates the GameOptions Screen.
	 */
	public void createGameOptionsScreen() {
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("game options");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();
		
		//Make all the needed buttons.
		buttonGameOptions = FrameUtils.makeButton("Change Options ", "gameOptions", true);
		buttonGameOptions.addActionListener(this);
		buttonSelectSkin = FrameUtils.makeButton(" Select Skin ", "selectSkin", true);
		buttonSelectSkin.addActionListener(this);
		buttonReturnSetup = FrameUtils.makeButton("    Return   ", "returnSetup", false);
		buttonReturnSetup.addActionListener(this);
		
		//Add the buttons to the proper panels.
		lowerPanel.add(buttonReturnSetup);
		
		middlePanel.setLayout(new GridBagLayout());
		middlePanel.add(buttonGameOptions, FrameUtils.gbLayoutNormal(0, 0));
		middlePanel.add(buttonSelectSkin, FrameUtils.gbLayoutNormal(0, 1));

		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}

	/**
	 * Creates the ChooseSkin Screen.
	 */
	public void createChooseSkinScreen() {
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("select skin");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();
		
		//Makes all the needed buttons.
		buttonReturnSkin = FrameUtils.makeButton("  Return  ", "returnSkin", false);
		buttonReturnSkin.addActionListener(this);
		
		//Adds the buttons to the proper panels.
		lowerPanel.add(buttonReturnSkin);

		middlePanel.setLayout(new GridBagLayout());
		middlePanel.add(iowaState, FrameUtils.gbLayoutWest(0, 0));
		middlePanel.add(iowa, FrameUtils.gbLayoutWest(0, 1));
		middlePanel.add(northernIowa, FrameUtils.gbLayoutWest(0, 2));
		
		//Makes the previously selected skin show, the default is iowaState.
		Utils.showPreviousSelectedSkin();
		
		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}

	/**
	 * Creates the Options Screen.
	 */
	public void createOptionsScreen() {
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("change options");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();
		
		//Makes all the needed buttons.
		buttonDefaultOptions = FrameUtils.makeButton("  Default  ", "defaultOptions", false);
		buttonDefaultOptions.addActionListener(this);
		buttonReturnOptions = FrameUtils.makeButton("  Return  ", "returnOptions", false);
		buttonReturnOptions.addActionListener(this);
		
		//Adds the buttons to the proper panels.
		lowerPanel.add(buttonDefaultOptions);
		lowerPanel.add(buttonReturnOptions);

		middlePanel.setLayout(new GridBagLayout());
		middlePanel.add(bagValueLabel, FrameUtils.gbLayoutNormal(0, 0));
	    middlePanel.add(nilValueLabel, FrameUtils.gbLayoutNormal(0, 1));
	    middlePanel.add(doubleNilValueLabel, FrameUtils.gbLayoutNormal(0, 2));
	    middlePanel.add(winScoreLabel, FrameUtils.gbLayoutNormal(0, 3));
		middlePanel.add(loseScoreLabel, FrameUtils.gbLayoutNormal(0, 4));
		middlePanel.add(nilAllowed, FrameUtils.gbLayoutNormal(0, 5));
		nilAllowed.addItemListener(this);
		middlePanel.add(bagValueTextField, FrameUtils.gbLayoutNormal(1, 0));
		middlePanel.add(nilValueTextField, FrameUtils.gbLayoutNormal(1, 1));
		middlePanel.add(doubleNilValueTextField, FrameUtils.gbLayoutNormal(1, 2));
		middlePanel.add(winScoreTextField, FrameUtils.gbLayoutNormal(1, 3));
		middlePanel.add(loseScoreTextField, FrameUtils.gbLayoutNormal(1, 4));
		middlePanel.add(doubleNilAllowed, FrameUtils.gbLayoutNormal(1, 5));
		doubleNilAllowed.addItemListener(this);
		
	    //Set text field not editable and change check box if nil is not allowed.
		if (!Main.isNilAllowed) {
			nilValueTextField.setEditable(false);
			nilAllowed.setState(Main.isNilAllowed);
		}
	    
	    //Set text field not editable and change check box if double nil is not allowed.
		if (!Main.isDoubleNilAllowed) {
			doubleNilValueTextField.setEditable(false);
			doubleNilAllowed.setState(Main.isDoubleNilAllowed);
		}
		
		//Makes the previous options show if they were set already, or shows the
		//default values.
		Utils.showPreviousOptions();
		
		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}
}
