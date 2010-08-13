/**
 * IniSetup.Java
 * 
 * @author David Hoffman
 */

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

public class IniSetup extends Frame implements ActionListener, ItemListener {

	/**
	 * Declare needed variables.
	 */
	private static final long serialVersionUID = 1L;
	
	static TextField enterNames = new TextField(15);
	static TextField enterSoundPath = new TextField(25);
	static TextField winScore = new TextField(5);
	static TextField loseScore = new TextField(5);
	
	static Checkbox soundsEnabled = new Checkbox ("Sounds Enabled");
	
	static Choice skin = FrameUtils.makeSkinList();
	static Choice locStart = FileUtils.makeSoundsList();
	static Choice locBags = FileUtils.makeSoundsList();
	static Choice locSet = FileUtils.makeSoundsList();
	static Choice locWin = FileUtils.makeSoundsList();
	static Choice locLose = FileUtils.makeSoundsList();
	
	Label allNames = new Label("List of Names");
	Label nameInput = new Label("Name to Add");
	Label nameDelete = new Label("Name to Delete");
	Label waveStart = new Label("Game Start");
	Label waveBags = new Label("Loss for Bags");
	Label waveSet = new Label("Someone is Set");
	Label waveWin = new Label("Game is Won");
	Label waveLose = new Label("Game is Lost");
	Label wavePath = new Label("Folder Containing Audio Files");
	Label scoreWin = new Label("Winning Score");
	Label scoreLose = new Label("Losing Score");
	Label pickSkin = new Label("Pick a skin");
	
	Button buttonInput;
	Button buttonDelete;
	Button buttonInputNames;
	Button buttonDeleteNames;
	Button buttonSounds;
	Button buttonOther;
	Button buttonReturn;
	Button buttonReturnSounds;
	Button buttonReturnOther;
	Button buttonReturnMain;

	TextArea text;

	Panel upperPanel;
	Panel middlePanel;
	Panel lowerPanel;

	Frame frame = new Frame();

	/**
	 * Default constructor for the class.
	 */
    public IniSetup(Frame frameIn){
    	frame = frameIn;
    }

	/**
	 * Performs the assigned tasks when the corresponding button is pressed.
	 * 
	 * @param event The action that was triggered by a button press.
	 */
	public void actionPerformed(ActionEvent event) {
        //Performs this action when the Input Names button is pressed.
        if (event.getActionCommand().equals("inputNames")) {
        	frame.removeAll();
    		createInputNamesScreen();
        }  

        //Performs this action when the Delete Names button is pressed.
        if (event.getActionCommand().equals("deleteNames")) {
        	frame.removeAll();
    		createDeleteNamesScreen();
        }  

        //Performs this action when the Input button is pressed.
        if (event.getActionCommand().equals("input")) {
         	Utils.processInputNames();
         	enterNames.setText("");

         	frame.removeAll();
    		createInputNamesScreen();
        }  

        //Performs this action when the Delete button is pressed.
        if (event.getActionCommand().equals("delete")) {
         	Utils.processDeleteNames();
         	enterNames.setText("");

         	frame.removeAll();
    		createDeleteNamesScreen();
        }  

        //Performs this action when the Sounds button is pressed.
        if (event.getActionCommand().equals("sounds")) {
        	frame.removeAll();
    		createSoundsSetupScreen();
        }  

        //Performs this action when the Return Sounds button is pressed.
        if (event.getActionCommand().equals("returnSounds")) {
        	if (Utils.processSounds()) {
        		Utils.createIni();
            	
            	frame.removeAll();
        		createSetupScreen();
        	}
        }  

        //Performs this action when the Other button is pressed.
        if (event.getActionCommand().equals("other")) {
            frame.removeAll();
        	createOtherSetupScreen();
       }  

        //Performs this action when the Return Other button is pressed.
        if (event.getActionCommand().equals("returnOther")) {
        	if (Utils.processDefaults()) {
            	Utils.createIni();
            	Utils.setSkinSelected();
            	
            	frame.removeAll();
        		createSetupScreen();
        	}
        }  

        //Performs this action when the Return button is pressed.
        if (event.getActionCommand().equals("return")) {
        	Utils.createIni();
        	
        	frame.removeAll();
    		createSetupScreen();
        }  

        //Performs this action when the MainMenu button is pressed.
        if (event.getActionCommand().equals("returnMain")) {
        	frame.removeAll();
        	
        	try {
				FileUtils.writeIniFile();
			} catch (IOException e) {
				FrameUtils.showDialogBox("File could not be created.");
			}
        	
			Utils.parseIni();
			
    		Main game = new Main();
    		game.createMainMenuScreen();
        }  
	}

	/**
	 * Performs the assigned tasks when the check box state has changed.
	 * 
	 * @param event The action that was triggered by the state change.
	 */
	public void itemStateChanged(ItemEvent event) {
		//Determine if the sounds check box is checked.
		if (event.getItemSelectable().equals(soundsEnabled)) {
			//If checked, allow sounds to be used.
			if (soundsEnabled.getState()) {
				enterSoundPath.setEditable(true);
				locStart.setEnabled(true);
				locBags.setEnabled(true);
				locSet.setEnabled(true);
				locWin.setEnabled(true);
				locLose.setEnabled(true);
				
			//If not checked, don't allow sounds to be used.
			} else {
				enterSoundPath.setEditable(false);
				locStart.setEnabled(false);
				locBags.setEnabled(false);
				locSet.setEnabled(false);
				locWin.setEnabled(false);
				locLose.setEnabled(false);
			}
		}
	}

	/**
	 * Create the ini setup screen.
	 */
	public void createSetupScreen() {
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("config setup");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();

		//Makes all the needed buttons.
		buttonInputNames = FrameUtils.makeButton("Enter Names", "inputNames", false);
		buttonInputNames.addActionListener(this);
		buttonDeleteNames = FrameUtils.makeButton("Delete Names", "deleteNames", false);
		buttonDeleteNames.addActionListener(this);
		buttonSounds = FrameUtils.makeButton("Configure Sounds", "sounds", false);
		buttonSounds.addActionListener(this);
		buttonOther = FrameUtils.makeButton("Other Defaults", "other", false);
		buttonOther.addActionListener(this);
		buttonReturnMain = FrameUtils.makeButton("Main Menu", "returnMain", true);
		buttonReturnMain.addActionListener(this);
		
		//Add the buttons to the proper panels.
		lowerPanel.add(buttonReturnMain);
		
		//Add items to the middle panel.
		middlePanel.setLayout(new GridBagLayout());
		middlePanel.add(buttonInputNames, FrameUtils.gbLayoutNormal(0, 0));
		middlePanel.add(buttonDeleteNames, FrameUtils.gbLayoutNormal(0, 1));
		middlePanel.add(buttonSounds, FrameUtils.gbLayoutNormal(0, 2));
		middlePanel.add(buttonOther, FrameUtils.gbLayoutNormal(0, 3));

		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}

	/**
	 * Create the Input Names screen.
	 */
	public void createInputNamesScreen() {
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("input names");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();

		//Makes all the needed buttons.
		buttonInput = FrameUtils.makeButton("  Input  ", "input", true);
		buttonInput.addActionListener(this);
		buttonReturn = FrameUtils.makeButton(" Return ", "return", true);
		buttonReturn.addActionListener(this);
		
		//Add the buttons to the proper panels.
		lowerPanel.add(buttonInput);
		lowerPanel.add(buttonReturn);
		
		//Add items to the middle panel.
		middlePanel.setLayout(new GridBagLayout());
		text = new TextArea(Utils.namesToString(), 6, 16);
		text.setSize(50, 50);
		middlePanel.add(allNames, FrameUtils.gbLayoutTight(0, 0));
		middlePanel.add(text, FrameUtils.gbLayoutNormal(0, 1));
		middlePanel.add(nameInput, FrameUtils.gbLayoutTight(0, 2));
		middlePanel.add(enterNames, FrameUtils.gbLayoutNormal(0, 3));

		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}

	/**
	 * Creates the Delete Names screen.
	 */
	public void createDeleteNamesScreen() {
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("delete names");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();

		//Makes all the needed buttons.
		buttonDelete = FrameUtils.makeButton("  Delete  ", "delete", true);
		buttonDelete.addActionListener(this);
		buttonReturn = FrameUtils.makeButton(" Return ", "return", true);
		buttonReturn.addActionListener(this);
		
		//Add the buttons to the proper panels.
		lowerPanel.add(buttonDelete);
		lowerPanel.add(buttonReturn);
		
		//Add items to the middle panel.
		middlePanel.setLayout(new GridBagLayout());
		text = new TextArea(Utils.namesToString(), 6, 16);
		text.setSize(50, 50);
		middlePanel.add(allNames, FrameUtils.gbLayoutTight(0, 0));
		middlePanel.add(text, FrameUtils.gbLayoutNormal(0, 1));
		middlePanel.add(nameDelete, FrameUtils.gbLayoutTight(0, 2));
		middlePanel.add(enterNames, FrameUtils.gbLayoutNormal(0, 3));

		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}

	/**
	 * Create the Sounds setup screen.
	 */
	public void createSoundsSetupScreen() {
		//Make the sounds list every time.
		locStart = FileUtils.makeSoundsList();
		locBags = FileUtils.makeSoundsList();
		locSet = FileUtils.makeSoundsList();
		locWin = FileUtils.makeSoundsList();
		locLose = FileUtils.makeSoundsList();

		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("sounds setup");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();

		//Makes all the needed buttons.
		buttonReturnSounds = FrameUtils.makeButton("Return", "returnSounds", true);
		buttonReturnSounds.addActionListener(this);
		
		//Add the buttons to the proper panels.
		lowerPanel.add(buttonReturnSounds);
		
		//Add items to the middle panel.
		middlePanel.setLayout(new GridBagLayout());
		middlePanel.add(waveStart, FrameUtils.gbLayoutWest(0, 0));
		waveStart.setFont(new Font("arial", Font.PLAIN, 10));
		middlePanel.add(locStart, FrameUtils.gbLayoutTight(0, 1));
		middlePanel.add(waveBags, FrameUtils.gbLayoutWest(0, 2));
		waveBags.setFont(new Font("arial", Font.PLAIN, 10));
		middlePanel.add(locBags, FrameUtils.gbLayoutTight(0, 3));
		middlePanel.add(waveSet, FrameUtils.gbLayoutWest(0, 4));
		waveSet.setFont(new Font("arial", Font.PLAIN, 10));
		middlePanel.add(locSet, FrameUtils.gbLayoutTight(0, 5));
		middlePanel.add(waveWin, FrameUtils.gbLayoutWest(0, 6));
		waveWin.setFont(new Font("arial", Font.PLAIN, 10));
		middlePanel.add(locWin, FrameUtils.gbLayoutTight(0, 7));
		middlePanel.add(waveLose, FrameUtils.gbLayoutWest(0, 8));
		waveLose.setFont(new Font("arial", Font.PLAIN, 10));
		middlePanel.add(locLose, FrameUtils.gbLayoutTight(0, 9));
		
		//Set the ability to select sounds to true if sounds are enabled.
		if (Main.sounds) {
			locStart.setEnabled(true);
			locBags.setEnabled(true);
			locSet.setEnabled(true);
			locWin.setEnabled(true);
			locLose.setEnabled(true);
		//Set the ability to select sounds to false if sounds are not enabled.
		} else {
			locStart.setEnabled(false);
			locBags.setEnabled(false);
			locSet.setEnabled(false);
			locWin.setEnabled(false);
			locLose.setEnabled(false);
		}
		
		//Makes the previous sounds show if they were set already, or shows the
		//default values.
		Utils.showPreviousSounds();

		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}

	/**
	 * Creates the other or defaults screen.
	 */
	public void createOtherSetupScreen() {
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("other setup");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();

		//Makes all the needed buttons.
		buttonReturnOther = FrameUtils.makeButton("Return", "returnOther", true);
		buttonReturnOther.addActionListener(this);
		
		//Add the buttons to the proper panels.
		lowerPanel.add(buttonReturnOther);
		
		//Add items to the middle panel.
		middlePanel.setLayout(new GridBagLayout());
		middlePanel.add(pickSkin, FrameUtils.gbLayoutNormal(0, 0));
		middlePanel.add(skin, FrameUtils.gbLayoutNormal(1, 0));
		middlePanel.add(scoreWin, FrameUtils.gbLayoutNormal(0, 1));
		middlePanel.add(winScore, FrameUtils.gbLayoutNormal(1, 1));
		middlePanel.add(scoreLose, FrameUtils.gbLayoutNormal(0, 2));
		middlePanel.add(loseScore, FrameUtils.gbLayoutNormal(1, 2));
		middlePanel.add(soundsEnabled, FrameUtils.gbLayoutDouble(0, 3));
		middlePanel.add(wavePath, FrameUtils.gbLayoutDouble(0, 4));
		middlePanel.add(enterSoundPath, FrameUtils.gbLayoutDouble(0, 5));
		soundsEnabled.addItemListener(this);

		//Set enterSoundPath to not editable and change check box if sounds are not enabled.
		if (Main.sounds) {
			enterSoundPath.setEditable(true);
			soundsEnabled.setState(Main.sounds);
		} else {
			enterSoundPath.setEditable(false);
			soundsEnabled.setState(Main.sounds);
		}
		
		//Makes the previous defaults show if they were set already.
		Utils.showPreviousDefaults();

		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}
}
