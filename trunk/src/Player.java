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
	
	/**
	 * 
	 * @param bidIn
	 * @param tricksIn
	 */
	public Player(String playerIn) {	
		player = playerIn;
		round = Main.round;
		
		nextRound("0", "0");
	}
	
	public void nextRound(String bidIn, String tricksIn) {
		ArrayList turn = new ArrayList();
		
		
	}
}



//		bid = bidIn;
//		tricksTaken = tricksIn;
