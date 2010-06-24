/**
 * 
 * 
 * @author dhoffman
 */

import java.util.ArrayList;

public class Team {
	
	/**
	 * Declare needed variables.
	 */
	public String name = "";
	public String bid = "";
	public String tricksTaken = "";
	public String score = "";
	public String bags = "";
	
	public int round_no;

	public boolean set = false;

	public ArrayList turn;

	public Player one;
	public Player two;
	
	/**
	 * 
	 * @param one
	 * @param two
	 */
	public Team(Player one, Player two) {
		this.one = one;
		this.two = two;
		
		nameTeam();
		turn = new ArrayList();
		round_no = 0;
		score = "0";
		bags = "0";

	}
	
	/**
	 * Creates the name of the team from the players names.
	 * 
	 * @return The name of the team.
	 */
	public void nameTeam() {
		name = one.player + " & " + two.player;
	}
	
	/**
	 * 
	 * @param bidIn1
	 * @param bidIn2
	 * @param tricksIn1
	 * @param tricksIn2
	 */
	public void inputRound(String bidIn1, String bidIn2, String tricksIn1,
			String tricksIn2) {
		round_no++;
		//bid = bidIn;
		//tricksTaken = tricksIn;
		
		//Update players.
		
		
		//Computes and saves the rest of the round information.
		//calculateScore();
		//saveTurn();
	}
	
	
	public void calculateScore() {
		
	}
	
	public String toString() {
		
		return null;
	}
}
