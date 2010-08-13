/**FINISHED
 * FileUtils.java
 * 
 * This class is responsible for handling all static methods dealing
 * with file IO operations.
 * 
 * @author David Hoffman
 */

import java.awt.Choice;
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
		File file = new File(Main.iniFile);

	    //Writes the lines of an array list to the file..
		try {
			FileWriter fw = new FileWriter(file);
			
			//Write the ini array list.
			for (int i = 0; i < Main.ini.size(); i++) {
				fw.write((String) Main.ini.get(i) + "\n");
			}
			
			fw.close();
		} catch (IOException e) {
			FrameUtils.showDialogBox("File could not be created.");
		}
	}

	/**
	 * Find and add just WAV files to the list of possible choices.
	 * 
	 * @return The list of playable sound files.
	 */
	public static Choice makeSoundsList() {
		ArrayList arrList = new ArrayList();
		Choice lst = new Choice();
		File temp;
		
		//First item in list is blank.
		lst.add("");
		
		//Get the WAV files.
		try {
			arrList = waveFinder(new File(Main.soundDir));
		} catch (IOException e) {
			FrameUtils.showDialogBox("File could not be found.");
		}
		
		//Add all the found wave files to the list.
		for (int i = 0; i < arrList.size(); i++) {
			temp = (File) arrList.get(i);
			lst.add(temp.getName());
		}
		
		return lst;
	}
	
	/**
	 * Finds all the WAV files in given source directory.
	 * 
	 * @param name Source directory to be searched.
	 * @return An string array list of found WAV file objects.
	 */
	public static ArrayList waveFinder(File rootNode) throws IOException {
		ArrayList waves = new ArrayList();
		File[] files;
		String str;
		String ext = "";

		//Get contents of names.
		files = rootNode.listFiles();

		//Parse through the files ArrayList.
		for (int i = 0; i < files.length; i++) {
			//Get path of found file.
			str = files[i].getName();

			//Get extension of found file.
			if (str.length() >= 5) ext = str.substring(str.length() - 4, str.length());

			//Add to the waves ArrayList if the extension is .wav.
			if (ext.equalsIgnoreCase(".wav")) {
				//Add new wave file to the return waves ArrayList.
				waves.add(files[i]);
			}
		}

		return waves;
	}
}
