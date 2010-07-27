/**FINISHED
 * FileUtils.java
 * 
 * This class is responsible for handling all static methods dealing
 * with file IO operations.
 * 
 * @author David Hoffman
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileUtils {

	/**
	 * Exports the game options file that contains how the game was
	 * configured.
	 */
	public static void exportGameOptions() {
		File file = new File("game" + Main.game + "_options.csv");
		String str = "";
		
		//Say what type of game it is.
		if (Main.isFourHandedSingle) str += "Four Handed - Single Players\n";
		if (Main.isFourHandedTeams) str += "Four Handed - Teams\n";
		if (Main.isThreeHanded) str += "Three Handed\n";
		
		//Give the values for all the options.
		str += "Bag Value - " + Main.bagValue + "\n";
		str += "Nil Value - " + Main.nilValue + "\n";
		str += "Double Nil Value - " + Main.doubleNilValueNumb + "\n";
		str += "Win Score - " + Main.winScore + "\n";
		str += "Lose Score - " + Main.loseScore + "\n";
		str += "Start Dealer - " + Main.startDealer + "\n";
		
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(str);
			fw.close();
		} catch (IOException e) {
			FrameUtils.showDialogBox("File could not be created.");
		}
	}
	
	/**
	 * Exports a file that contains all the Player information for each
	 * round played.
	 * 
	 * @param player The Player data exported to a file.
	 */
	public static void exportPlayerFile(Player player) {
		File file = new File(player.player + "_game" + Main.game + ".csv");

		try {
			FileWriter fw = new FileWriter(file);
			fw.write(player.toString());
			fw.close();
		} catch (IOException e) {
			FrameUtils.showDialogBox("File could not be created.");
		}
	}

	/**
	 * Exports a file that contains all the Team information for each
	 * round played.
	 * 
	 * @param player The Team data exported to a file.
	 */
	public static void exportTeamFile(Team team) {
		File file = new File(team.name + "_game" + Main.game + ".csv");

		try {
			FileWriter fw = new FileWriter(file);
			fw.write(team.toString());
			fw.close();
		} catch (IOException e) {
			FrameUtils.showDialogBox("File could not be created.");
		}
	}

	/**
	 * Reads the lines of the spades.ini file.
	 * 
	 * @throws IOException 
	 */
	public static void loadIniFile() throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(Main.iniFile));
		String line;
		Main.ini = new ArrayList();

	    //Read all the lines into an array list.
		try {    
	    	if (!in.ready());

	        while ((line = in.readLine()) != null) {
	        	Main.ini.add(line);
	        }
	    }
        catch (IOException e) {
			FrameUtils.showDialogBox("Ini file did not load.");
			Main.iniFailed = true;
	    }
        
    	in.close();
	}
	/**
	 * Writes the lines of the spades.ini file.
	 * 
	 * @throws IOException 
	 */
	public static void writeIniFile() throws IOException {
		File file = new File(Main.iniFile.getName());

	    //Writes the lines of an array list to the file..
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(Main.ini.toString());
			fw.close();
		} catch (IOException e) {
			FrameUtils.showDialogBox("File could not be created.");
		}
	}
}
