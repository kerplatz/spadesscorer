/**
 * IniSetup.Java
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

public class IniSetup extends Frame implements ActionListener {

	/**
	 * Declare needed variables.
	 */
	private static final long serialVersionUID = 1L;
	
	Button buttonReturn;

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

        //Performs this action when the MainMenu button is pressed.
        if (event.getActionCommand().equals("returnMain")) {
        	frame.removeAll();
        	
    		Main game = new Main();
    		game.createMainMenuScreen();
        }  
	}

	public void createSetupScreen() {
		//Create the 3 panel components of the screen.
		upperPanel = FrameUtils.makeUpperPanel("ini setup");
		middlePanel = FrameUtils.makeMiddlePanel();
		lowerPanel = FrameUtils.makeLowerPanel();

		//Makes all the needed buttons.
		buttonReturn = FrameUtils.makeButton("Main Menu", "returnMain", false);
		buttonReturn.addActionListener(this);
		
		//Add the buttons to the proper panels.
		lowerPanel.add(buttonReturn);
		
		//This adds all the panels to the frame.
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}

}
