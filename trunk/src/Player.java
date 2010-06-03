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

	public boolean set = false;
	
	public int round;
	public int score = 0;
	
	public ArrayList<String> turn;
	
	/**
	 * 
	 * @param playerIn
	 */
	public Player(String playerIn) {	
		turn = new ArrayList<String>();
		player = playerIn;
		round = Main.round;
		newRound();
	}

	/**
	 * 
	 * @param playerIn
	 * @param ScoreIn
	 */
	public Player(String playerIn, int scoreIn) {	
		turn = new ArrayList<String>();
		player = playerIn;
		round = Main.round;
		score = scoreIn;
	}
	
	/**
	 * 
	 * @param bidIn
	 * @param tricksIn
	 */
	public void nextRound(String bidIn, String tricksIn) {
		
		
		
	}

	/**
	 * 
	 */
	public void newRound() {
		
		
	}
	
	/**
	 * 
	 */
	public void calculateScore() {
		
	}
	
	/**
	 * 
	 */
	public void reCalculateScore() {
		
	}
	
	/**
	 * 
	 */
	public String toString() {
		String str = "";
		
		return str;
	}
}
