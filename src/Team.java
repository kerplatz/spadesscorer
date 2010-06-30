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
	public String score = "";
	public String bags = "";
	
	public int round_no;

	public boolean set = false;

	public ArrayList turn;

	public Player one;
	public Player two;
	
	/**
	 * Constructor for creating the Team object.
	 * 
	 * @param one The first player of the Team.
	 * @param two The second player of the Team.
	 */
	public Team(Player one, Player two) {
		this.one = one;
		this.two = two;
		round_no = 0;
		score = "0";
		bags = "0";
		turn = new ArrayList();
		
		//Create the team name.
		nameTeam();

		//Creates the first round which is just column headers.
		beginRound();
	}
	
	/**
	 * Creates the name of the team from the players names.
	 * 
	 * @return The name of the team.
	 */
	public void nameTeam() {
		name = one.player + " and " + two.player;
	}

	/**
	 * Creates the first set of items added to the ArrayList, which becomes
	 * the header for the CSV file.
	 */
	public void beginRound() {
		turn.add("round");
		turn.add("player 1 bid");
		turn.add("player 1 tricks");
		turn.add("player 2 bid");
		turn.add("player 2 tricks");
		turn.add("score");
		turn.add("bags");
		turn.add("set");
	}
	
	/**
	 * This method takes in the bids and tricks taken for each Player on
	 * the team.
	 * 
	 * @param bidIn1 The bid of Player one.
	 * @param bidIn2 The bid of Player two.
	 * @param tricksIn1 The tricks taken by Player one.
	 * @param tricksIn2 The tricks taken by Player two.
	 */
	public void inputRound(String bidIn1, String bidIn2, String tricksIn1,
			String tricksIn2) {
		
		round_no++;
		one.inputRound(bidIn1, tricksIn1);
		two.inputRound(bidIn2, tricksIn2);

		//Computes and saves the rest of the round information.
		calculateScore();
		saveTurn();
	}
	
	/**
	 * This method takes in the bids and tricks taken for each Player on
	 * the team.
	 * 
	 * @param bidIn1 The bid of Player one.
	 * @param bidIn2 The bid of Player two.
	 * @param tricksIn1 The tricks taken by Player one.
	 * @param tricksIn2 The tricks taken by Player two.
	 */
	public void inputRound(String bidIn1, String bidIn2, String tricksIn1) {

		round_no++;
		one.inputRound(bidIn1, tricksIn1);
		two.inputRound(bidIn2, tricksIn1);

		//Computes and saves the rest of the round information.
		calculateScore();
		saveTurn();
	}
	
	/**
	 * Calculates the score.
	 */
	public void calculateScore() {
		int bidTemp1 = Utils.stringToInt(one.bid);
		int bidTemp2 = Utils.stringToInt(two.bid);
		int tricksTemp1 = Utils.stringToInt(one.tricksTaken);
		int tricksTemp2 = Utils.stringToInt(two.tricksTaken);
		int scoreTemp = Utils.stringToInt(score);
		int bagsTemp = Utils.stringToInt(bags);
		int bagsRecvd = 0;
		int bagsTotal = 0;

		if (one.bid.equals("nil") || one.bid.equals("dbl") ||
				two.bid.equals("nil") || two.bid.equals("dbl")) {
			//Player 1.
			//Calculate the preliminary score when tricks taken exceeds bid.
			if (tricksTemp1 > bidTemp1) {
				//When a nil or double nil bid.
				if (bidTemp1 == 0) {
					//Nil bid.
					if (one.bid.equals("nil")) {
						//Deal with the score.
						scoreTemp -= Main.nilValueNumb;
					}
					if (one.bid.equals("dbl")) {
						//Deal with the score.
						scoreTemp -= Main.doubleNilValueNumb;
					}
					
					//The player was set.
					set = true;
				}
				//When not a nil or double nil bid.	
				else {
					//Deal with score.
					scoreTemp += bidTemp1 * 10;
				
					//The player was not set.
					set = false;
				}
				
				//Deal with bags.
				bagsRecvd += tricksTemp1 - bidTemp1;
				scoreTemp += bagsRecvd * Main.bagValueNumb;
				bagsTotal = bagsTemp + bagsRecvd;
			}
			
			//Calculate the preliminary score when tricks taken equals bid.
			if (tricksTemp1 == bidTemp1) {
				//When a nil or double nil bid.
				if (bidTemp1 == 0) {
					//Nil bid.
					if (one.bid.equals("nil")) {
						//Deal with the score.
						scoreTemp += Main.nilValueNumb;
					}
					if (one.bid.equals("dbl")) {
						//Deal with the score.
						scoreTemp += Main.doubleNilValueNumb;
					}
				}
				//When not a nil or double nil bid.
				else {
					//Deal with the score.
					scoreTemp += bidTemp1 * 10;
				}
				
				//Save the bags.
				bagsTotal = bagsTemp;

				//The player was not set.
				set = false;
			}
			
			//Calculate the preliminary score when tricks taken is less than bid.
			if (tricksTemp1 < bidTemp1) {
				//Deal with score.
				scoreTemp -= bidTemp1 * 10;
				
				//Save the bags.
				bagsTotal = bagsTemp;
				
				//Player was set.
				set = true;
			}
			
			//Player 2.
			//Calculate the preliminary score when tricks taken exceeds bid.
			if (tricksTemp2 > bidTemp2) {
				//When a nil or double nil bid.
				if (bidTemp2 == 0) {
					//Nil bid.
					if (two.bid.equals("nil")) {
						//Deal with the score.
						scoreTemp -= Main.nilValueNumb;
					}
					if (two.bid.equals("dbl")) {
						//Deal with the score.
						scoreTemp -= Main.doubleNilValueNumb;
					}
					
					//The player was set.
					set = true;
				}
				//When not a nil or double nil bid.	
				else {
					//Deal with score.
					scoreTemp += bidTemp2 * 10;
				
					//The player was not set.
					set = false;
				}
				
				//Deal with bags.
				bagsRecvd += tricksTemp2 - bidTemp2;
				scoreTemp += bagsRecvd * Main.bagValueNumb;
				bagsTotal = bagsTemp + bagsRecvd;
			}
			
			//Calculate the preliminary score when tricks taken equals bid.
			if (tricksTemp2 == bidTemp2) {
				//When a nil or double nil bid.
				if (bidTemp2 == 0) {
					//Nil bid.
					if (two.bid.equals("nil")) {
						//Deal with the score.
						scoreTemp += Main.nilValueNumb;
					}
					if (two.bid.equals("dbl")) {
						//Deal with the score.
						scoreTemp += Main.doubleNilValueNumb;
					}
				}
				//When not a nil or double nil bid.
				else {
					//Deal with the score.
					scoreTemp += bidTemp2 * 10;
				}
				
				//Save the bags.
				bagsTotal = bagsTemp;

				//The player was not set.
				set = false;
			}
			
			//Calculate the preliminary score when tricks taken is less than bid.
			if (tricksTemp2 < bidTemp2) {
				//Deal with score.
				scoreTemp -= bidTemp2 * 10;
				
				//Save the bags.
				bagsTotal = bagsTemp;
				
				//Player was set.
				set = true;
			}

		} else {
			//Calculate the preliminary score when tricks taken exceeds bid.
			if (tricksTemp1 > bidTemp1 + bidTemp2) {
				//Deal with score.
				scoreTemp += (bidTemp1 + bidTemp2) * 10;
				
				//The player was not set.
				set = false;
				
				//Deal with bags.
				bagsRecvd += tricksTemp1 - (bidTemp1 + bidTemp2);
				scoreTemp += bagsRecvd * Main.bagValueNumb;
				bagsTotal = bagsTemp + bagsRecvd;
			}
			
			//Calculate the preliminary score when tricks taken equals bid.
			if (tricksTemp1 == bidTemp1 + bidTemp2) {
				//Deal with the score.
				scoreTemp += (bidTemp1 + bidTemp2) * 10;
				
				//Save the bags.
				bagsTotal = bagsTemp;

				//The player was not set.
				set = false;
			}
			
			//Calculate the preliminary score when tricks taken is less than bid.
			if (tricksTemp1 < bidTemp1 + bidTemp2) {
				//Deal with score.
				scoreTemp -= (bidTemp1 + bidTemp2) * 10;
				
				//Save the bags.
				bagsTotal = bagsTemp;
				
				//Player was set.
				set = true;
			}
		}
		
		//Remove points for bags of multiple of 10.
		scoreTemp -= (Main.bagValueNumb * bagsTotal / 10) * 100;
				
		//Update all the variables.
		bags = Integer.toString(bagsTotal);
		score = Integer.toString(scoreTemp);
	}
	
	/**
	 * Saves the Team turn to the ArrayList.
	 */
	public void saveTurn() {
		turn.add(Integer.toString(round_no));
		turn.add(one.bid);
		turn.add(one.tricksTaken);
		turn.add(two.bid);
		turn.add(two.tricksTaken);
		turn.add(score);
		turn.add(bags);

		if (set) {
			turn.add("true");
		} else {
			turn.add("false");
		}
	}

	/**
	 * This method counts the number of times the Team went set.
	 * 
	 * @return A String that is the number of times the Team went set.
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
	 * Convert the team object to a string.
	 */
	public String toString() {
		String str = "";
				
		for (int i = 0; i < turn.size(); i += 8) {
			for (int j = 0; j < 8; j++) {
				//Get the desired item from the ArrayList.
				str += (String) turn.get(i + j);
				
				//Add a comma on all items except the last one.
				if (j != 7) {
					str += ",";
				} else {
					str += "\n";
				}
			}
		}
		
		return str;
	}
}
