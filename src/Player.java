/**
 * Player.java
 * 
 * This class describes a player. Its main function is to record in an
 * ArrayList all the data associated with a player. It also has a calculate
 * and recalculate score methods. It also includes a toString() method to
 * facilitate the creation a CSV file when the game has ended.
 *
 * @author dhoffman
 */
 
import java.util.ArrayList;

public class Player {
	
	/**
	 * Declare needed variables.
	 */
	public String player = "";
	public String bid = "";
	public String tricksTaken = "";
	public String score = "";
	public String round = "";
	public String bags = "";

	public Boolean set = false;

	public String[] play;
	
	public ArrayList<String> turn;
	
	/**
	 * Constructor that creates a new player.
	 * 
	 * @param playerIn The name of the player.
	 */
	public Player(String playerIn) {	
		turn = new ArrayList<String>();
		player = playerIn;
		round = Integer.toString(Main.round);
		score = "0";
		bags = "0";
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
		turn = new ArrayList<String>();
		player = playerIn;
		round = Integer.toString(Main.round);
		score = scoreIn;
		bags = bagsIn;
		beginRound();
	}
	
	/**
	 * Method is used to input the bids and tricks taken by the player.
	 * 
	 * @param bidIn The bid made by the player.
	 * @param tricksIn The amount of tricks taken by the player.
	 */
	public void nextRound(String bidIn, String tricksIn) {
		round = Integer.toString(Main.round);
		bid = bidIn;
		tricksTaken = tricksIn;
		
		calculateScore();
		saveTurn();
	}

	/**
	 * Creates the first set of items added to the ArrayList, which becomes
	 * the header for the CSV file.
	 */
	public void beginRound() {
		turn.add(round);
		turn.add("bid");
		turn.add("tricks");
		turn.add("score");
		turn.add("bags");
		turn.add("set");
	}
	
	/**
	 * Calculates the score.
	 */
	public void calculateScore() {
		int bidTemp = Utils.stringToInt(bid);
		int tricksTemp = Utils.stringToInt(tricksTaken);
		int scoreTemp = Utils.stringToInt(score);
		int bagsTemp = Utils.stringToInt(bags);
		int bagsRecvd = 0;
		int bagsTotal = 0;

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
				
				//The player was not set.
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
		
		//Remove points for bags of multiple of 10.
		scoreTemp -= (Main.bagValueNumb * bagsTotal / 10) * 100;
				
		//Update all the variables.
		bags = Integer.toString(bagsTotal);
		score = Integer.toString(scoreTemp);
	}
	
	/**
	 * Recalculates the score after a change was made to the ArrayList.
	 */
	public void reCalculateScore() {
		
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
	 * Saves the player turn to the ArrayList.
	 */
	public void saveTurn() {
		turn.add(round);
		turn.add(bid);
		turn.add(tricksTaken);
		turn.add(score);
		turn.add(bags);
		turn.add(set.toString());
	}
	
	/**
	 * Convert the player object to a string.
	 */
	public String toString() {
		String str = "";
		String temp = "";
		boolean flag = true;
		int start = Utils.stringToInt(turn.get(0));
				
		for (int i = start; i < turn.size(); i += 6) {
			for (int j = 0; j < 6; j++) {
				//Get the desired item from the ArrayList.
				if (flag) {
					temp = "round";
				} else {
					temp = turn.get(i + j);
				}
				
				//Only used for first ArrayList item.
				flag = false;
				
				//Add the ArrayList item to the output String.
				str += temp;
				
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
