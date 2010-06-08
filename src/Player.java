/**
 * Player.java
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
	 * 
	 * @param playerIn
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
	 * 
	 * @param playerIn
	 * @param scoreIn
	 * @param bagsIn
	 */
	public Player(String playerIn, String scoreIn, String bagsIn) {	
		turn = new ArrayList<String>();
		player = playerIn;
		round = Integer.toString(Main.round);
		score = scoreIn;
		bags = bagsIn;
	}
	
	/**
	 * 
	 * @param bidIn
	 * @param tricksIn
	 */
	public void nextRound(String bidIn, String tricksIn) {
		bid = bidIn;
		tricksTaken = tricksIn;
		
		calculateScore();
	}

	/**
	 * 
	 */
	public void beginRound() {
		
		
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
			//Deal with bags.
			bagsRecvd += tricksTemp - bidTemp;
			scoreTemp += bagsRecvd * Main.bagValueNumb;
			bagsTotal = bagsTemp + bagsRecvd;
			
			//Deal with score.
			scoreTemp += bidTemp * 10;
			
			//The player was not set.
			set = false;
		}
		
		//Calculate the preliminary score when tricks taken equals bid.
		if (tricksTemp == bidTemp) {
			//When a nil bid.
			if (bidTemp == 0) {
				
			}
			//When not a nil bid.
			else {
				scoreTemp += bidTemp * 10;
				set = false;
			}
		}
		
		//Calculate the preliminary score when tricks taken is less than bid.
		if (tricksTemp < bidTemp) {
			//Deal with score.
			scoreTemp -= bidTemp * 10;
			
			//Player was set.
			set = true;
		}
		
		//Remove points for bags of multiple of 10.
		if (GameOptions.bagValue.getSelectedItem().equals("2")) {
			scoreTemp -= (Main.bagValueNumb * bagsTotal / 10) * 100;
		}
		if (GameOptions.bagValue.getSelectedItem().equals("1")) {
			scoreTemp -= (bagsTotal / 10) * 100;
		}
		
		//Update all the variables.
		bags = Integer.toString(bagsTotal);
		score = Integer.toString(scoreTemp);
	}
	
	/**
	 * 
	 */
	public void reCalculateScore() {
		
	}

	/**
	 * 
	 * @return
	 */
	public String calculateTimesSet() {
		String numb = "0";
		
		
		return numb;
	}
	
	/**
	 * 
	 */
	public String toString() {
		String str = "";
		
		return str;
	}
}
