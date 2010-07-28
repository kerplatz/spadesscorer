/**FINISHED
 * Player.java
 * 
 * This class describes a player. Its main function is to record in an
 * ArrayList all the data associated with a player. It also has a calculate
 * and recalculate score methods. It also includes a toString() method to
 * facilitate the creation a CSV file when the game has ended.
 *
 * @author David Hoffman
 */
 
import java.util.ArrayList;

public class Player {
	
	/**
	 * Declare needed variables.
	 */
	private static final boolean DEBUG = Main.DEBUG;

	public String player = "";
	public String bid = "";
	public String tricksTaken = "";
	public String score = "";
	public String bags = "";
	
	public int round_no;
	public int bagsMultiplier = 0;

	public boolean set = false;

	public ArrayList turn;
	
	/**
	 * Constructor that creates a new player.
	 * 
	 * @param playerIn The name of the player.
	 */
	public Player(String playerIn) {	
		turn = new ArrayList();
		round_no = 0;
		player = playerIn;
		score = "0";
		bags = "0";
		
		//Creates the first round which is just column headers.
		beginRound();
	}

	/**
	 * Constructor used when changing players.
	 * 
	 * @param playerIn The name of the player.
	 * @param scoreIn The score brought in from the old player.
	 * @param bagsIn The bags brought in from the old player.
	 */
	public Player(String playerIn, String scoreIn, String bagsIn) {	
		turn = new ArrayList();
		round_no = Main.round;
		player = playerIn;
		score = scoreIn;
		bags = bagsIn;

		//Creates the first round which is just column headers.
		beginRound();
	}

	/**
	 * Creates the first set of items added to the ArrayList, which becomes
	 * the header for the CSV file.
	 */
	public void beginRound() {
		turn.add("Round");
		turn.add(player + " Bid");
		turn.add(player + " Tricks");
		turn.add("Score");
		turn.add("Bags");
		turn.add("Set");
	}
	
	/**
	 * Method is used to input the bid and tricks taken by the player.
	 * 
	 * @param bidIn The bid made by the player.
	 * @param tricksIn The amount of tricks taken by the player.
	 * @throws AudioException 
	 */
	public void inputRound(String bidIn, String tricksIn) throws AudioException {
		round_no++;
		bid = bidIn;
		tricksTaken = tricksIn;
		
		//Computes and saves the rest of the round information.
		calculateScore();
		saveTurn();
	}

	/**
	 * Saves the player turn to the ArrayList.
	 */
	public void saveTurn() {
		turn.add(Integer.toString(round_no));
		turn.add(bid);
		turn.add(tricksTaken);
		turn.add(score);
		turn.add(bags);

		if (set) {
			turn.add("true");
		} else {
			turn.add("false");
		}
	}
	
	/**
	 * Calculates the score.
	 * 
	 * @throws AudioException 
	 */
	public void calculateScore() {
		int bidTemp = Utils.stringToInt(bid);
		int tricksTemp = Utils.stringToInt(tricksTaken);
		int scoreTemp = Utils.stringToInt(score);
		int bagsTemp = Utils.stringToInt(bags);
		int bagsRecvd = 0;
		int bagsTotal = 0;
		int bagsTotalTemp = 0;
		int bagsScored = 0;

		//Calculate the preliminary score when tricks taken exceeds bid.
		if (tricksTemp > bidTemp) {
			//When a nil or double nil bid.
			if (bidTemp == 0) {
				//Nil bid.
				if (bid.equals("nil")) {
					//Deal with the score.
					scoreTemp -= Main.nilValueNumb;
				}
				if (bid.equals("dbl")) {
					//Deal with the score.
					scoreTemp -= Main.doubleNilValueNumb;
				}
				
				//The player was set.
				set = true;
			}
			//When not a nil or double nil bid.	
			else {
				//Deal with score.
				scoreTemp += bidTemp * 10;
			
				//The player was not set.
				set = false;
			}
			
			//Deal with bags.
			bagsRecvd += tricksTemp - bidTemp;
			scoreTemp += bagsRecvd * Main.bagValueNumb;
			bagsTotal = bagsTemp + bagsRecvd;
		}
		
		//Calculate the preliminary score when tricks taken equals bid.
		if (tricksTemp == bidTemp) {
			//When a nil or double nil bid.
			if (bidTemp == 0) {
				//Nil bid.
				if (bid.equals("nil")) {
					//Deal with the score.
					scoreTemp += Main.nilValueNumb;
				}
				if (bid.equals("dbl")) {
					//Deal with the score.
					scoreTemp += Main.doubleNilValueNumb;
				}
			}
			//When not a nil or double nil bid.
			else {
				//Deal with the score.
				scoreTemp += bidTemp * 10;
			}
			
			//Save the bags.
			bagsTotal = bagsTemp;

			//The player was not set.
			set = false;
		}
		
		//Calculate the preliminary score when tricks taken is less than bid.
		if (tricksTemp < bidTemp) {
			//Deal with score.
			scoreTemp -= bidTemp * 10;
			
			//Save the bags.
			bagsTotal = bagsTemp;
			
			//Player was set.
			set = true;
		}

		bagsTotalTemp = bagsTotal;
		
		//Reduce the number of bags.
		for(int i = 0; i < bagsMultiplier; i++) {
			bagsTotalTemp -=10;
		}

		//Calculate if the bags have reached 10 or more.
		bagsScored = (Main.bagValueNumb * bagsTotalTemp / 10);
		bagsMultiplier += bagsScored;
		
		//Don't play any sound files if debug mode.
		if (!DEBUG) {
			//Play sound file if 10 or more bags has been reached.
			AudioPlayer ap = new AudioPlayer();
			if (bagsScored > 0) {
				try {
					ap.playAudio(Main.soundBags);
				} catch (AudioException e) {
					FrameUtils.showDialogBox("Audio file did not play.");
				}
			}

			//Play sound file if player was set.
			if (set) {
				try {
					ap.playAudio(Main.soundSet);
				} catch (AudioException e) {
					FrameUtils.showDialogBox("Audio file did not play.");
				}
			}
		}
		
		//Remove points for bags of multiple of 10.
		scoreTemp -= bagsScored * 100;
				
		//Update all the variables.
		bags = Integer.toString(bagsTotal);
		score = Integer.toString(scoreTemp);
	}

	/**
	 * This method counts the number of times the player went set.
	 * 
	 * @return A String that is the number of times the player went set.
	 */
	public String calculateTimesSet() {
		int numb = 0;
		
		//Search and record the number of times 'true' is found.
		for (int i = 0; i < turn.size(); i++) {
			if (turn.get(i).equals("true")) numb++;
		}
		
		return Integer.toString(numb);
	}
	
	/**
	 * Convert the player object to a string.
	 */
	public String toString() {
		String str = "";
				
		//Cycle through the entire array list.
		for (int i = 0; i < turn.size(); i += 6) {
			for (int j = 0; j < 6; j++) {
				//Get the desired item from the ArrayList.
				str += (String) turn.get(i + j);
				
				//Add a comma on all items except the last one.
				if (j != 5) {
					str += ",";
				} else {
					str += "\n";
				}
			}
		}
		
		return str;
	}
}
