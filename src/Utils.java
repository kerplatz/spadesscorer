/**FINISHED
 * Utils.java
 * 
 * This class contains helper methods that do not process any display screens
 * or control inputs, such as mouse clicks. Its main function is to provide
 * the decision making for the program as it progresses.
 *
 * @author David Hoffman
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Utils {
	
	//Declare the needed variables.
	public static ArrayList names = new ArrayList();
	public static ArrayList defaults = new ArrayList();
	public static ArrayList sounds = new ArrayList();

	/**
	 * Resets the game so a new game can be started.
	 */
	public static void resetGame() {
		//Set all variables to their default values.
		Main.isGameStarted = false;
		Main.isSetupDone = false;
		Main.isGameWon = false;
		Main.isGameLost = false;
		Main.isThreeHanded = false;
		Main.isFourHandedSingle = false;
		Main.isFourHandedTeams = false;
		Main.doBidding = false;
		Main.doScoring = false;
		Main.dealerIsPlayer1 = false;
		Main.dealerIsPlayer2 = false;
		Main.dealerIsPlayer3 = false;
		Main.dealerIsPlayer4 = false;
		Main.isNilAllowed = true;
		Main.isDoubleNilAllowed = true;
		Main.nilBidTeam1 = false;
		Main.nilBidTeam2 = false;
		Main.doubleIsAllowedPlayer1 = false;
		Main.doubleIsAllowedPlayer2 = false;
		Main.doubleIsAllowedPlayer3 = false;
		Main.doubleIsAllowedPlayer4 = false;
		Main.doubleIsAllowedTeam1 = false;
		Main.doubleIsAllowedTeam2 = false;

		
		Main.player1 = "";
		Main.player2 = "";
		Main.player3 = "";
		Main.player4 = "";
		Main.team1 = "";
		Main.team2 = "";
		Main.player1Bid = "";
		Main.player2Bid = "";
		Main.player3Bid = "";
		Main.player4Bid = "";
		Main.player1TricksTaken = "";
		Main.player2TricksTaken = "";
		Main.player3TricksTaken = "";
		Main.player4TricksTaken = "";
		Main.curDealer = "";
		Main.startDealer = "";
		Main.bagValue = "1";
		Main.nilValue = "50";
		Main.doubleNilValue = "200";
		Main.winScore = "500";
		Main.loseScore = "-200";
		
		Main.round = 0;
		Main.player1TimesSet = 0;
		Main.player2TimesSet = 0;
		Main.player3TimesSet = 0;
		Main.player4TimesSet = 0;
		Main.player1Score = "0";
		Main.player2Score = "0";
		Main.player3Score = "0";
		Main.player4Score = "0";
		Main.team1Score = "0";
		Main.team2Score = "0";
		
		GameSetup.gameTypeHidden.setState(true);
		GameSetup.threeHanded.setEnabled(true);
		GameSetup.fourHandedSingle.setEnabled(true);
		GameSetup.fourHandedTeams.setEnabled(true);
		GameSetup.dealerHidden.setState(true);
		GameSetup.choiceBoxPlayer1.setEnabled(true);
		GameSetup.choiceBoxPlayer2.setEnabled(true);
		GameSetup.choiceBoxPlayer3.setEnabled(true);
		GameSetup.choiceBoxPlayer4.setEnabled(true);
		GameSetup.hasPlayerChanged = false;
		
		EditGame.playerChanged = false;
		EditGame.scoreChanged = false;
		EditGame.roundToEdit = 0;
		EditGame.editedRound = 0;
	}

	/**
	 * Shows the Game Type if it was previously selected.
	 */
	public static void showPreviousSelectedGameType() {
		GameSetup.threeHanded.setState(Main.isThreeHanded);
		GameSetup.fourHandedSingle.setState(Main.isFourHandedSingle);
		GameSetup.fourHandedTeams.setState(Main.isFourHandedTeams);
	}

	/**
	 * Shows the player names if they were previously entered.
	 */
	public static void showPreviousPlayerNames() {
		GameSetup.choiceBoxPlayer1.select(Main.player1);
		GameSetup.choiceBoxPlayer2.select(Main.player2);
		GameSetup.choiceBoxPlayer3.select(Main.player3);
		
		//Don't show fourth player if playing 3 handed game.
		if (!Main.isThreeHanded) {
			GameSetup.choiceBoxPlayer4.select(Main.player4);
		}
	}

	/**
	 * Shows the dealer if it was previously selected.
	 */
	public static void showPreviousSelectedDealer() {
		GameSetup.player1IsDealer.setState(Main.dealerIsPlayer1);
		GameSetup.player2IsDealer.setState(Main.dealerIsPlayer2);
		GameSetup.player3IsDealer.setState(Main.dealerIsPlayer3);
		
		//Don't show fourth player if playing 3 handed game.
		if (!Main.isThreeHanded) {
			GameSetup.player4IsDealer.setState(Main.dealerIsPlayer4);
		}
	}

	/**
	 * Shows which skin was previously selected. ISU is default.
	 */
	public static void showPreviousSelectedSkin() {
		GameOptions.iowaState.setState(Main.skinIsIowaState);
		GameOptions.iowa.setState(Main.skinIsIowa);
		GameOptions.northernIowa.setState(Main.skinIsNorthernIowa);
	}

	/**
	 * Shows what the values for these variables are.
	 */
	public static void showPreviousOptions() {
		GameOptions.bagValue.select(Main.bagValue);
		GameOptions.nilValueTextField.setText(Main.nilValue);
		GameOptions.doubleNilValueTextField.setText(Main.doubleNilValue);
		GameOptions.winScoreTextField.setText(Main.winScore);
		GameOptions.loseScoreTextField.setText(Main.loseScore);
	}

	/**
	 * Shows what the values for the sounds are.
	 */
	public static void showPreviousSounds() {
		IniSetup.locStart.select(Main.soundGameStart);
		IniSetup.locBags.select(Main.soundBags);
		IniSetup.locSet.select(Main.soundSet);
		IniSetup.locWin.select(Main.soundWin);
		IniSetup.locLose.select(Main.soundLose);
	}

	/**
	 * Shows what the values for the default options are.
	 */
	public static void showPreviousDefaults() {
		if (Main.skinIsIowa) IniSetup.skin.select("Iowa");
		if (Main.skinIsIowaState) IniSetup.skin.select("ISU");
		if (Main.skinIsNorthernIowa) IniSetup.skin.select("UNI");

		IniSetup.winScore.setText(Main.winScore);
		IniSetup.loseScore.setText(Main.loseScore);
		IniSetup.enterSoundPath.setText(Main.soundDir);
	}
	
	/**
	 * Clears the Game Type.
	 */
	public static void clearGameType() {
		Main.isThreeHanded = false;
		Main.isFourHandedSingle = false;
		Main.isFourHandedTeams = false;
	}
	
	/**
	 * Clear the player names.
	 */
	public static void clearPlayerNames() {
		if (GameSetup.choiceBoxPlayer1.isEnabled()) {
			GameSetup.choiceBoxPlayer1.select(0);
			Main.player1 = "";
		}

		if (GameSetup.choiceBoxPlayer2.isEnabled()) {
			GameSetup.choiceBoxPlayer2.select(0);
			Main.player2 = "";
		}

		if (GameSetup.choiceBoxPlayer3.isEnabled()) {
			GameSetup.choiceBoxPlayer3.select(0);
			Main.player3 = "";
		}
		
		//Don't show fourth player if playing 3 handed game.
		if (!Main.isThreeHanded) {
			if (GameSetup.choiceBoxPlayer4.isEnabled()) {
				GameSetup.choiceBoxPlayer4.select(0);
				Main.player4 = "";
			}
		}
	}

	/**
	 * Clear the selected dealer.
	 */
	public static void clearDealerName() {
		Main.dealerIsPlayer1 = false;
		Main.dealerIsPlayer2 = false;
		Main.dealerIsPlayer3 = false;
		
		GameSetup.player1IsDealer.setState(false);
		GameSetup.player2IsDealer.setState(false);
		GameSetup.player3IsDealer.setState(false);

		Main.curDealer = "";
		Main.startDealer = "";
		
		//Don't show fourth player if playing 3 handed game.
		if (!Main.isThreeHanded) {
			Main.dealerIsPlayer4 = false;
			GameSetup.player4IsDealer.setState(false);
		}
	}

	/**
	 * Clear the selected skin.
	 */
	public static void clearSkin() {
		Main.skinIsIowaState = false;
		Main.skinIsIowa = false;
		Main.skinIsNorthernIowa = false;
		
		GameOptions.iowaState.setState(false);
		GameOptions.iowa.setState(false);
		GameOptions.northernIowa.setState(false);
	}

	/**
	 * Clear the sound files.
	 */
	public static void clearSounds() {
		Main.soundBags = "";
		Main.soundSet = "";
		Main.soundWin = "";
		Main.soundLose = "";
		Main.soundGameStart= "";
	}

	/**
	 * Clear the bid made.
	 */
	public static void clearBidsMade() {
		FrameUtils.player1Bid.select("");
		FrameUtils.player2Bid.select("");
		FrameUtils.player3Bid.select("");
		
		Main.player1Bid = "";
		Main.player2Bid = "";
		Main.player3Bid = "";
		
		//Only clear the fourth player bid if playing 3 handed game.
		if (!Main.isThreeHanded) {
			FrameUtils.player4Bid.select("");
			Main.player4Bid = "";
		}
	}

	/**
	 * Clear the tricks taken.
	 */
	public static void clearTricksTaken() {
		FrameUtils.player1TricksTaken.select("");
		FrameUtils.player2TricksTaken.select("");
		FrameUtils.player3TricksTaken.select("");
		
		Main.player1TricksTaken = "";
		Main.player2TricksTaken = "";
		Main.player3TricksTaken = "";
		
		//Only clear the fourth player tricks taken if playing 3 handed game.
		if (!Main.isThreeHanded) {
			FrameUtils.player4TricksTaken.select("");
			Main.player4TricksTaken = "";
		}
	}
	
	/**
	 * Sets the values for these game options to default.
	 */
	public static void defaultGameOptions() {
		GameOptions.doubleNilValueTextField.setText("200");
		GameOptions.winScoreTextField.setText("500");
		GameOptions.loseScoreTextField.setText("-200");
		
		Main.doubleNilValue = "200";
		Main.winScore = "500";
		Main.loseScore = "-200";

		//Use the appropriate value for bagValue when three handed.
		if (Main.isThreeHanded){
			GameOptions.bagValue.select("2");
			GameOptions.nilValueTextField.setText("100");
			GameOptions.nilAllowed.setState(true);
			GameOptions.doubleNilAllowed.setState(true);
			GameOptions.nilValueTextField.setEditable(true);
			GameOptions.doubleNilValueTextField.setEditable(true);

			Main.bagValue = "2";
			Main.nilValue = "100";
			Main.isNilAllowed = true;
			Main.isDoubleNilAllowed = true;
		//Use the appropriate value for bagValue when four handed single.
		} else if (Main.isFourHandedSingle){
			GameOptions.bagValue.select("1");
			GameOptions.nilValueTextField.setText("50");
			GameOptions.nilAllowed.setState(true);
			GameOptions.doubleNilAllowed.setState(false);
			GameOptions.nilValueTextField.setEditable(true);
			GameOptions.doubleNilValueTextField.setEditable(false);

			Main.bagValue = "1";
			Main.nilValue = "50";
			Main.isNilAllowed = true;
			Main.isDoubleNilAllowed = false;
		//Use the appropriate value for bagValue when four handed teams.
		} else {
			GameOptions.bagValue.select("1");
			GameOptions.nilValueTextField.setText("50");
			GameOptions.nilAllowed.setState(true);
			GameOptions.doubleNilAllowed.setState(true);
			GameOptions.nilValueTextField.setEditable(true);
			GameOptions.doubleNilValueTextField.setEditable(true);

			Main.bagValue = "1";
			Main.nilValue = "50";
			Main.isNilAllowed = true;
			Main.isDoubleNilAllowed = true;
		}
	}
	
	/**
	 * Determines if the game type has been selected.
	 * 
	 * @return True if the game type selection is done, false otherwise.
	 */
	public static boolean isSelectGameTypeDone() {
		boolean gameTypeSelected = false;
		
		//Check if the game type has been selected.
		if (GameSetup.threeHanded.getState()) {
			gameTypeSelected = true;
		}
		if (GameSetup.fourHandedSingle.getState()) {
			gameTypeSelected = true;
		}
		if (GameSetup.fourHandedTeams.getState()) {
			gameTypeSelected = true;
		}

		//Show dialog box reminder.
		if (!gameTypeSelected) FrameUtils.showDialogBox("A Game Type must be selected.");
				
		//Saves the game type.
		saveGameType();
		
		return gameTypeSelected;
	}

	/**
	 * Determines if the players names have been entered.
	 * 
	 * @return True is the players setup is done, false otherwise.
	 */
	public static boolean isPlayersSetupDone() {
		boolean playersNamed = true;

		//Check if all the choice boxes have been selected.
		if (GameSetup.choiceBoxPlayer1.getSelectedIndex() == -1
				|| GameSetup.choiceBoxPlayer1.getSelectedIndex() == 0) {
			playersNamed = false;
		}
		if (GameSetup.choiceBoxPlayer2.getSelectedIndex() == -1
				|| GameSetup.choiceBoxPlayer2.getSelectedIndex() == 0) {
			playersNamed = false;
		}
		if (GameSetup.choiceBoxPlayer3.getSelectedIndex() == -1
				|| GameSetup.choiceBoxPlayer3.getSelectedIndex() == 0) {
			playersNamed = false;
		}
		
		//Don't show fourth player if playing 3 handed game.
		if (!Main.isThreeHanded) {
			if (GameSetup.choiceBoxPlayer4.getSelectedIndex() == -1
					|| GameSetup.choiceBoxPlayer4.getSelectedIndex() == 0) {
				playersNamed = false;
			}
		}
		
		//Show dialog box reminder.
		if (!playersNamed) FrameUtils.showDialogBox("All players must be selected.");
		
		//Save player names.
		savePlayerNames();
		
		return playersNamed;
	}

	/**
	 * Determines if the dealer selection has been completed.
	 * 
	 * @return True when the dealer selection is complete, false otherwise.
	 */
	public static boolean isDealerSelectionDone() {
		boolean dealerSelected = false;
		
		//Check if the dealer has been selected.
		if (GameSetup.player1IsDealer.getState()) {
			dealerSelected = true;
		}
		if (GameSetup.player2IsDealer.getState()) {
			dealerSelected = true;
		}
		if (GameSetup.player3IsDealer.getState()) {
			dealerSelected = true;
		}
		
		//Don't show fourth player if playing 3 handed game.
		if (!Main.isThreeHanded) {
			if (GameSetup.player4IsDealer.getState()) {
				dealerSelected = true;
			}
		}

		//Show dialog box reminder.
		if (!dealerSelected) FrameUtils.showDialogBox("A dealer must be selected.");
		
		//Save who the dealer is.
		saveDealerName();
			
		return dealerSelected;
	}

	/**
	 * Determines if a skin has been chosen. ISU is the default.
	 * 
	 * @return True if the skin has been chosen, false otherwise.
	 */
	public static boolean isChooseSkinDone() {
		boolean skinSelected = false;
		
		//Check if the dealer has been selected.
		if (GameOptions.iowaState.getState()) {
			skinSelected = true;
		}
		if (GameOptions.iowa.getState()) {
			skinSelected = true;
		}
		if (GameOptions.northernIowa.getState()) {
			skinSelected = true;
		}

		//Show dialog box reminder.
		if (!skinSelected) FrameUtils.showDialogBox("A skin must be selected.");
		
		//Save which skin is selected.
		saveSkinSelection();
		
		return skinSelected;
	}

	/**
	 * Determines if the game options have been entered.
	 * 
	 * @return True if the options have been entered, false otherwise.
	 */
	public static boolean isOptionsDone() {
		boolean gameOptionsDone = true;
		
		//Check if all the text fields are not blank or have correct data.
		try {
			Integer.parseInt(GameOptions.bagValue.getSelectedItem());
		} catch (NumberFormatException e) {
			gameOptionsDone = false;
		}
		try {
			Integer.parseInt(GameOptions.nilValueTextField.getText());
		} catch (NumberFormatException e) {
			gameOptionsDone = false;
		}
		try {
			Integer.parseInt(GameOptions.doubleNilValueTextField.getText());
		} catch (NumberFormatException e) {
			gameOptionsDone = false;
		}
		try {
			Integer.parseInt(GameOptions.winScoreTextField.getText());
		} catch (NumberFormatException e) {
			gameOptionsDone = false;
		}
		try {
			Integer.parseInt(GameOptions.loseScoreTextField.getText());
		} catch (NumberFormatException e) {
			gameOptionsDone = false;
		}
	
		//Show dialog box reminder.
		if (!gameOptionsDone) FrameUtils.showDialogBox("You have entered incorrect data.");
		
		//Save the options.
		saveOptions();
		
		//Convert the options to ints.
		convertOptionsToNumbers();
		
		return gameOptionsDone;
	}

	/**
	 * Determines if the game has been won.
	 * 
	 * @return True if the game has been won, false otherwise.
	 */
	public static boolean isGameWon() {
		boolean gameWon = false;
		
		//Game won for three handed game.
		if (Main.isThreeHanded){
			if (Utils.stringToInt(Main.player1Score) >= Main.winScoreNumb ||
					Utils.stringToInt(Main.player2Score) >= Main.winScoreNumb ||
					Utils.stringToInt(Main.player3Score) >= Main.winScoreNumb) {
				gameWon = true;
			}
		}
		//Game won for four handed single player game.
		if (Main.isFourHandedSingle){
			if (Utils.stringToInt(Main.player1Score) >= Main.winScoreNumb ||
					Utils.stringToInt(Main.player2Score) >= Main.winScoreNumb ||
					Utils.stringToInt(Main.player3Score) >= Main.winScoreNumb ||
					Utils.stringToInt(Main.player4Score) >= Main.winScoreNumb) {
				gameWon = true;
			}
		}
		//Game won for four handed team game.
		if (Main.isFourHandedTeams){
			if (Utils.stringToInt(Main.team1Score) >= Main.winScoreNumb ||
					Utils.stringToInt(Main.team2Score) >= Main.winScoreNumb) {
				gameWon = true;
			}
		}
		
		//Set Main variable.
		Main.isGameWon = gameWon;
		
		return gameWon;
	}

	/**
	 * Determines if the game has been lost.
	 * 
	 * @return True if the game is lost, false otherwise.
	 */
	public static boolean isGameLost() {
		boolean gameLost = false;
		
		//Game lost for three handed game.
		if (Main.isThreeHanded){
			if (Utils.stringToInt(Main.player1Score) <= Main.loseScoreNumb ||
					Utils.stringToInt(Main.player2Score) <= Main.loseScoreNumb ||
					Utils.stringToInt(Main.player3Score) <= Main.loseScoreNumb) {
				gameLost = true;
			}
		}
		//Game lost for four handed single player game.
		if (Main.isFourHandedSingle){
			if (Utils.stringToInt(Main.player1Score) <= Main.loseScoreNumb ||
					Utils.stringToInt(Main.player2Score) <= Main.loseScoreNumb ||
					Utils.stringToInt(Main.player3Score) <= Main.loseScoreNumb ||
					Utils.stringToInt(Main.player4Score) <= Main.loseScoreNumb) {
				gameLost = true;
			}
		}
		//Game lost for four handed team game.
		if (Main.isFourHandedTeams){
			if (Utils.stringToInt(Main.team1Score) <= Main.loseScoreNumb ||
					Utils.stringToInt(Main.team2Score) <= Main.loseScoreNumb) {
				gameLost = true;
			}
		}
		
		//Set Main variable.
		Main.isGameLost = gameLost;

		return gameLost;
	}
	
	/**
	 * Determines which Player won the game. TIES ARE NOT FIGURED.
	 * 
	 * @return The winner of the game.
	 */
	public static Player whoWonGame() {
		int highest = Main.winScoreNumb;
		Player winner = null;
		
		//Three handed play.
		if (Main.isThreeHanded){
			if (Utils.stringToInt(Main.player1Score) >= highest) {
				highest = Utils.stringToInt(Main.player1Score);
				winner = Main.playerOne;
			}
			if (Utils.stringToInt(Main.player2Score) >= highest) {
				highest = Utils.stringToInt(Main.player2Score);
				winner = Main.playerTwo;
			}
			if (Utils.stringToInt(Main.player3Score) >= highest) {
				highest = Utils.stringToInt(Main.player3Score);
				winner = Main.playerThree;
			}
		}
		
		//Four handed play with no teams.
		if (Main.isFourHandedSingle){
			if (Utils.stringToInt(Main.player1Score) >= highest) {
				highest = Utils.stringToInt(Main.player1Score);
				winner = Main.playerOne;
			}
			if (Utils.stringToInt(Main.player2Score) >= highest) {
				highest = Utils.stringToInt(Main.player2Score);
				winner = Main.playerTwo;
			}
			if (Utils.stringToInt(Main.player3Score) >= highest) {
				highest = Utils.stringToInt(Main.player3Score);
				winner = Main.playerThree;
			}
			if (Utils.stringToInt(Main.player4Score) >= highest) {
				highest = Utils.stringToInt(Main.player4Score);
				winner = Main.playerFour;
			}
		}
		
		return winner;
	}

	/**
	 * Determines which Team won the game. TIES ARE NOT FIGURED.
	 * 
	 * @return The winner of the game.
	 */
	public static Team whoWonGameTeam() {
		int highest = Main.winScoreNumb;
		Team winner = null;
		
		//Winner is team one.
		if (Utils.stringToInt(Main.team1Score) >= highest) {
			highest = Utils.stringToInt(Main.team1Score);
			winner = Main.teamOne;
		}
		//Winner is team two.
		if (Utils.stringToInt(Main.team2Score) >= highest) {
			highest = Utils.stringToInt(Main.team2Score);
			winner = Main.teamTwo;
		}
		
		return winner;
	}
	
	/**
	 * Determines which Player lost the game. TIES ARE NOT FIGURED.
	 * 
	 * @return The loser of the game.
	 */
	public static Player whoLostGame() {
		int lowest = Main.loseScoreNumb;
		Player loser = null;
		
		
		//Three handed play.
		if (Main.isThreeHanded){
			if (Utils.stringToInt(Main.player1Score) <= lowest) {
				lowest = Utils.stringToInt(Main.player1Score);
				loser = Main.playerOne;
			}
			if (Utils.stringToInt(Main.player2Score) <= lowest) {
				lowest = Utils.stringToInt(Main.player2Score);
				loser = Main.playerTwo;
			}
			if (Utils.stringToInt(Main.player3Score) <= lowest) {
				lowest = Utils.stringToInt(Main.player3Score);
				loser = Main.playerThree;
			}
		}
		
		//Four handed play with no teams.
		if (Main.isFourHandedSingle){
			if (Utils.stringToInt(Main.player1Score) <= lowest) {
				lowest = Utils.stringToInt(Main.player1Score);
				loser = Main.playerOne;
			}
			if (Utils.stringToInt(Main.player2Score) <= lowest) {
				lowest = Utils.stringToInt(Main.player2Score);
				loser = Main.playerTwo;
			}
			if (Utils.stringToInt(Main.player3Score) <= lowest) {
				lowest = Utils.stringToInt(Main.player3Score);
				loser = Main.playerThree;
			}
			if (Utils.stringToInt(Main.player4Score) <= lowest) {
				lowest = Utils.stringToInt(Main.player4Score);
				loser = Main.playerFour;
			}
		}
		
		return loser;
	}
	
	/**
	 * Determines which Team lost the game. TIES ARE NOT FIGURED.
	 * 
	 * @return The loser of the game.
	 */
	public static Team whoLostGameTeam() {
		int lowest = Main.loseScoreNumb;
		Team loser = null;
		
		//Team one is the looser.
		if (Utils.stringToInt(Main.team1Score) >= lowest) {
			lowest = Utils.stringToInt(Main.team1Score);
			loser = Main.teamOne;
		}
		//Team two is the loser.
		if (Utils.stringToInt(Main.team2Score) >= lowest) {
			lowest = Utils.stringToInt(Main.team2Score);
			loser = Main.teamTwo;
		}
		
		return loser;
	}

	/**
	 * Gets the GameType Selected and saves it to the appropriate variable.
	 */
	public static void saveGameType() {
		clearGameType();
		
		if (GameSetup.threeHanded.getState()) {
			Main.isThreeHanded = true;
		}
		if (GameSetup.fourHandedSingle.getState()) {
			Main.isFourHandedSingle = true;
		}
		if (GameSetup.fourHandedTeams.getState()) {
			Main.isFourHandedTeams = true;
		}
	}

	/**
	 * Gets the player names and copies them to their assigned names.
	 */
	public static void savePlayerNames() {
		Main.player1 = GameSetup.choiceBoxPlayer1.getSelectedItem();
		Main.player2 = GameSetup.choiceBoxPlayer2.getSelectedItem();
		Main.player3 = GameSetup.choiceBoxPlayer3.getSelectedItem();
		
		//Don't show fourth player if playing 3 handed game.
		if (!Main.isThreeHanded) {
			Main.player4 = GameSetup.choiceBoxPlayer4.getSelectedItem();
		}
	}

	/**
	 * Gets the team names and copies them to their assigned names.
	 */
	public static void saveTeamNames() {
		Main.team1 = Main.teamOne.name;
		Main.team2 = Main.teamTwo.name;
	}

	/**
	 * Gets the dealer name and copies it to the curDealer.
	 */
	public static void saveDealerName() {
		clearDealerName();
		
		if (GameSetup.player1IsDealer.getState()) {
			Main.dealerIsPlayer1 = true;
			Main.startDealer = Main.player1;
		}
		if (GameSetup.player2IsDealer.getState()) {
			Main.dealerIsPlayer2 = true;
			Main.startDealer = Main.player2;
		}
		if (GameSetup.player3IsDealer.getState()) {
			Main.dealerIsPlayer3 = true;
			Main.startDealer = Main.player3;
		}
		
		//Don't save fourth dealer if playing three handed game.
		if (!Main.isThreeHanded) {
			if (GameSetup.player4IsDealer.getState()) {
				Main.dealerIsPlayer4 = true;
				Main.startDealer = Main.player4;
			}
		}

		Main.curDealer = Main.startDealer;
	}
	
	/**
	 * Saves the currently selected skin.
	 */
	public static void saveSkinSelection() {
		clearSkin();
		
		if (GameOptions.iowaState.getState()) {
			Main.skinIsIowaState = true;
		}
		if (GameOptions.iowa.getState()) {
			Main.skinIsIowa = true;
		}
		if (GameOptions.northernIowa.getState()) {
			Main.skinIsNorthernIowa = true;
		}
	}

	/**
	 * Saves the currently entered sound selections.
	 */
	public static void saveSoundSelections() {
		Main.soundGameStart = IniSetup.locStart.getSelectedItem();
		Main.soundBags = IniSetup.locBags.getSelectedItem();
		Main.soundSet = IniSetup.locSet.getSelectedItem();
		Main.soundWin = IniSetup.locWin.getSelectedItem();
		Main.soundLose = IniSetup.locLose.getSelectedItem();
	}

	/**
	 * Saves the currently entered default values.
	 */
	public static void saveDefaultOptions() {
		//Save the skin that was selected.
		if (IniSetup.skin.getSelectedItem() == "ISU") {
			Main.skinIsIowaState = true;
			Main.skinIsIowa = false;
			Main.skinIsNorthernIowa = false;
		} else if (IniSetup.skin.getSelectedItem() == "Iowa") {
			Main.skinIsIowaState = false;
			Main.skinIsIowa = true;
			Main.skinIsNorthernIowa = false;
		} else if (IniSetup.skin.getSelectedItem() == "UNI") {
			Main.skinIsIowaState = false;
			Main.skinIsIowa = false;
			Main.skinIsNorthernIowa = true;
		}
		
		//Save the entered values.
		Main.winScore = IniSetup.winScore.getText();
		Main.loseScore = IniSetup.loseScore.getText();
		
		//Check if the soundPath is a folder.
		String str = IniSetup.enterSoundPath.getText();
		File file = new File(str);
		if (file.isDirectory()) Main.soundDir = str;
				
		//Save the state of sounds being enabled or not.
		if (IniSetup.soundsEnabled.getState()) {
			Main.sounds = true;
		} else {
			Main.sounds = false;
		}
		
		//Save the options as numbers.
		convertOptionsToNumbers();
	}
	
	/**
	 * Saves the current options entered.
	 */
	public static void saveOptions() {
		Main.bagValue = GameOptions.bagValue.getSelectedItem();
		Main.nilValue = GameOptions.nilValueTextField.getText();
		Main.doubleNilValue = GameOptions.doubleNilValueTextField.getText();
		Main.winScore = GameOptions.winScoreTextField.getText();
		Main.loseScore = GameOptions.loseScoreTextField.getText();
	}

	/**
	 * Saves the bid data to the appropriate variables.
	 */
	public static void saveBidData() {
		Main.player1Bid = FrameUtils.player1Bid.getSelectedItem();
		Main.player2Bid = FrameUtils.player2Bid.getSelectedItem();
		Main.player3Bid = FrameUtils.player3Bid.getSelectedItem();
		
		//Saves the bid data if playing with four players.
		if (!Main.isThreeHanded) {
			Main.player4Bid = FrameUtils.player4Bid.getSelectedItem();
		}
	}

	/**
	 * Saves the tricks taken data to the appropriate variables.
	 */
	public static void saveTricksTakenData() {
		Main.player1TricksTaken = FrameUtils.player1TricksTaken.getSelectedItem();
		Main.player2TricksTaken = FrameUtils.player2TricksTaken.getSelectedItem();
		Main.player3TricksTaken = FrameUtils.player3TricksTaken.getSelectedItem();
		
		//Saves the tricks taken if playing with four players.
		if (!Main.isThreeHanded) {
			Main.player4TricksTaken = FrameUtils.player4TricksTaken.getSelectedItem();
		}
	}
	
	/**
	 * Sets the skin to the selected skin.
	 */
	public static void setSkinSelected() {
		if (Main.skinIsIowaState) {
			FrameUtils.setISUColors();
		}
		if (Main.skinIsIowa) {
			FrameUtils.setIowaColors();
		}
		if (Main.skinIsNorthernIowa) {
			FrameUtils.setUNIColors();
		}
	}

	/**
	 * This method records the game data into Player Class and
	 * increments the round.
	 * 
	 * @throws AudioException 
	 */
	public static void recordGameData() throws AudioException {
		//Increment round.
		Main.round ++;

		//Record the four handed team game data to the player class.
		if (Main.isFourHandedTeams) {
			//Record four handed team play when nil bid in team one.
			if (Main.nilBidTeam1) {
				Main.teamOne.inputRound(Main.player1Bid, Main.player3Bid,
						Main.player1TricksTaken, Main.player3TricksTaken);
			//Record four handed team play when no nil bid in team one.
			} else {
				Main.teamOne.inputRound(Main.player1Bid, Main.player3Bid,
						Main.player1TricksTaken);
			}
			//Record four handed team play when nil bid in team two.
			if (Main.nilBidTeam2) {
				Main.teamTwo.inputRound(Main.player2Bid, Main.player4Bid,
						Main.player2TricksTaken, Main.player4TricksTaken);
			//Record four handed team play when no nil bid in team one.
			} else {
				Main.teamTwo.inputRound(Main.player2Bid, Main.player4Bid,
						Main.player2TricksTaken);
			}
		//Record the three handed game data to the player class.
		} else {
			Main.playerOne.inputRound(Main.player1Bid, Main.player1TricksTaken);
			Main.playerTwo.inputRound(Main.player2Bid, Main.player2TricksTaken);
			Main.playerThree.inputRound(Main.player3Bid, Main.player3TricksTaken);

			//Don't record the game data of fourth player when playing a
			//three handed game.
			if (!Main.isThreeHanded) {
				Main.playerFour.inputRound(Main.player4Bid, Main.player4TricksTaken);
			}
		}
	}

	/**
	 * This method posts the scores for each player.
	 */
	public static void postScores() {
		//Copies the scores for four handed team based play.
		if (Main.isFourHandedTeams) {
			Main.team1Score = Main.teamOne.score;
			Main.team2Score = Main.teamTwo.score;
		//Copies the scores for non four handed team based play.
		} else {
			Main.player1Score = Main.playerOne.score;
			Main.player2Score = Main.playerTwo.score;
			Main.player3Score = Main.playerThree.score;

			//Copies the scores for four handed single player game.
			if (!Main.isThreeHanded) {
				Main.player4Score = Main.playerFour.score;
			}
		}
	}
	
	/**
	 * Process the scoring info before the bidding screen is shown.
	 * 
	 * @return True if scoring is complete, false otherwise.
	 */
	public static boolean processScoring() {
		boolean done = true;
		int player1Tricks = 0;
		int player2Tricks = 0;
		int player3Tricks = 0;
		int player4Tricks = 0;
		
		//Gets the data for three players.
		player1Tricks = FrameUtils.player1TricksTaken.getSelectedIndex();
		player2Tricks = FrameUtils.player2TricksTaken.getSelectedIndex();
		player3Tricks = FrameUtils.player3TricksTaken.getSelectedIndex();

		//Gets the tricks taken if playing with four players.
		if (!Main.isThreeHanded) {
			player4Tricks = FrameUtils.player4TricksTaken.getSelectedIndex();
		}

		//Check if all the choice boxes have been selected.
		if (player1Tricks == -1 || player1Tricks == 0) {
			done = false;
		}
		if (player2Tricks == -1 || player2Tricks == 0) {
			done = false;
		}
		if (player3Tricks == -1 || player3Tricks == 0) {
			done = false;
		}
		
		//Don't check fourth player if playing three handed game.
		if (!Main.isThreeHanded) {
			if (player4Tricks == -1 || player4Tricks == 0) {
				done = false;
			}
		}

		//Get the value of three players tricks taken.
		player1Tricks = stringToInt(FrameUtils.player1TricksTaken.getSelectedItem());
		player2Tricks = stringToInt(FrameUtils.player2TricksTaken.getSelectedItem());
		player3Tricks = stringToInt(FrameUtils.player3TricksTaken.getSelectedItem());
		
		//Get the value of the fourth player in a four handed game.
		if (!Main.isThreeHanded) {
			player4Tricks = stringToInt(FrameUtils.player4TricksTaken.getSelectedItem());
		}
		
		//Check if tricks taken in a three handed game equals 17.
		if (Main.isThreeHanded) {
			if (player1Tricks + player2Tricks + player3Tricks != 17) {
				done = false;
			}
		}
		
		//Check if tricks taken in a non-three-handed game equals 13.
		if (!Main.isThreeHanded) {
			if (player1Tricks + player2Tricks + player3Tricks + player4Tricks != 13) {
				done = false;
			}
		}
		
		//Show dialog box reminder.
		if (!done) FrameUtils.showDialogBox("Tricks taken was entered wrong.");
			
		//Save tricks taken data.
		saveTricksTakenData();
		
		return done;
	}
	
	/**
	 * Process the scoring info before the bidding screen is shown.
	 * 
	 * @return True if scoring is complete, false otherwise.
	 */
	public static boolean processScoringTeams() {
		boolean done = true;
		int player1Tricks = 0;
		int player2Tricks = 0;
		int player3Tricks = 0;
		int player4Tricks = 0;
		
		//Get the value of all the players tricks taken.
		player1Tricks = FrameUtils.player1TricksTaken.getSelectedIndex();
		player2Tricks = FrameUtils.player2TricksTaken.getSelectedIndex();
		player3Tricks = FrameUtils.player3TricksTaken.getSelectedIndex();
		player4Tricks = FrameUtils.player4TricksTaken.getSelectedIndex();
		
		//Check if all the choice boxes have been selected.
		if (player1Tricks == -1 || player1Tricks == 0) {
			done = false;
		}
		if (player2Tricks == -1 || player2Tricks == 0) {
			done = false;
		}
		if (player3Tricks == -1 || player3Tricks == 0) {
			done = false;
			//Needed because the player bid was not a nil and the index for
			//the tricks taken is never read, by design. This only happens on
			//bids that are nils.
			if (FrameUtils.player3Bid.getSelectedItem() != "nil" ||
					FrameUtils.player3Bid.getSelectedItem() != "dbl") {
				done = true;
			}
		}
		if (player4Tricks == -1 || player4Tricks == 0) {
			done = false;
			//Needed because the player bid was not a nil and the index for
			//the tricks taken is never read, by design. This only happens on
			//bids that are nils.
			if (FrameUtils.player4Bid.getSelectedItem() != "nil" ||
					FrameUtils.player4Bid.getSelectedItem() != "dbl") {
				done = true;
			}
		}

		//Get the value of all the players tricks taken.
		player1Tricks = stringToInt(FrameUtils.player1TricksTaken.getSelectedItem());
		player2Tricks = stringToInt(FrameUtils.player2TricksTaken.getSelectedItem());
		player3Tricks = stringToInt(FrameUtils.player3TricksTaken.getSelectedItem());
		player4Tricks = stringToInt(FrameUtils.player4TricksTaken.getSelectedItem());
		
		//Check if tricks taken in a game equals 13.
		if (player1Tricks + player2Tricks + player3Tricks + player4Tricks != 13) {
			done = false;
		}
	
		//Show dialog box reminder.
		if (!done) FrameUtils.showDialogBox("Tricks taken was entered wrong.");
			
		//Save tricks taken data.
		saveTricksTakenData();
		
		return done;
	}

	/**
	 * Process the bidding info before the scoring screen is shown.
	 * 
	 * @return True if bidding is complete, false otherwise.
	 */
	public static boolean processBidding() {
		boolean done = true;
		
		//Check if all the choice boxes have been selected.
		if (FrameUtils.player1Bid.getSelectedIndex() == -1
				|| FrameUtils.player1Bid.getSelectedIndex() == 0) {
			done = false;
		}
		if (FrameUtils.player2Bid.getSelectedIndex() == -1
				|| FrameUtils.player2Bid.getSelectedIndex() == 0) {
			done = false;
		}
		if (FrameUtils.player3Bid.getSelectedIndex() == -1
				|| FrameUtils.player3Bid.getSelectedIndex() == 0) {
			done = false;
		}
		
		//Don't check fourth player if playing three handed game.
		if (!Main.isThreeHanded) {
			if (FrameUtils.player4Bid.getSelectedIndex() == -1
					|| FrameUtils.player4Bid.getSelectedIndex() == 0) {
				done = false;
			}
		}

		//Show dialog box reminder.
		if (!done) FrameUtils.showDialogBox("An incorrect bid was made.");
		
		//Save bid data.
		saveBidData();
			
		return done;
	}

	/**
	 * Add names to the players list.
	 */
	public static void processInputNames() {
		if (!IniSetup.enterNames.getText().equals("")) {
			names.add((String) IniSetup.enterNames.getText());
		}
	}

	/**
	 * Delete names from the players list.
	 */
	public static void processDeleteNames() {
		if (!IniSetup.enterNames.getText().equals("")) {
			names.remove((String) IniSetup.enterNames.getText());
		}
	}

	/**
	 * Saves the sounds entered and creates the sounds array list. The return true
	 * always was to keep the logic the same to similar methods.
	 * 
	 * @return True.
	 */
	public static boolean processSounds() {
		//Save the sounds selected.
		saveSoundSelections();
		
		//Create the sounds array list.
		createSoundsArrList();

		return true;
	}

	/**
	 * Processes the default settings and checks for errors.
	 * 
	 * @return True if inputs are ok, false otherwise.
	 */
	public static boolean processDefaults() {
		boolean done = false;
		int winScore = stringToInt(IniSetup.winScore.getText());
		int loseScore = stringToInt(IniSetup.loseScore.getText());
		File file = new File(Main.soundDir);
		
		//Check winning score value.
		if (winScore >= 100) {
			//Check loosing score value.
			if (loseScore < 0) {
				//Check if the input for sounds is a directory.
				if (file.isDirectory()) {
					done = true;
				}
			}
		}

		//Show dialog box reminder.
		if (!done) FrameUtils.showDialogBox("Check your inputs.");
		
		//Save the default options.
		saveDefaultOptions();
		
		//Create the defaults array list.
		createDefaultsArrList();

		return done;
	}

	/**
	 * This method converts the string value of a number to an int.
	 * 
	 * @param tricks The string to be converted.
	 * @return The actual value of the tricks taken.
	 */
	public static int stringToInt(String numb) {
		int temp = 0;
		
		//Convert the string to an int. Set it to a value of zero if not a number.
		try {
			temp = Integer.parseInt(numb);
		} catch (NumberFormatException e) {
			temp = 0;
		}
		
		return temp;
	}

	/**
	 * Advances the dealer to the next player in rotation.
	 */
	public static void advanceDealer() {
		//Advances when current dealer is player1.
		if (Main.curDealer.equals(Main.player1)) {
			Main.curDealer = Main.player2;
			Main.dealerIsPlayer1 = false;
			Main.dealerIsPlayer2 = true;
			return;
		}
		//Advances when current dealer is player2.
		if (Main.curDealer.equals(Main.player2)) {
			Main.curDealer = Main.player3;
			Main.dealerIsPlayer2 = false;
			Main.dealerIsPlayer3 = true;
			return;
		}
		//Advances when current dealer is player3.
		if (Main.curDealer.equals(Main.player3)) {
			Main.curDealer = Main.player1;
			Main.dealerIsPlayer3 = false;
			Main.dealerIsPlayer1 = true;
			return;
		}
		//For advancing the dealer when playing a four handed game.
		if (!Main.isThreeHanded) {
			//Advances when current dealer is player3.
 			if (Main.curDealer.equals(Main.player3)) {
				Main.curDealer = Main.player4;
				Main.dealerIsPlayer3 = false;
				Main.dealerIsPlayer4 = true;
				return;
			}
 			//Advances when current dealer is player4.
			if (Main.curDealer.equals(Main.player4)) {
				Main.curDealer = Main.player1;
				Main.dealerIsPlayer4 = false;
				Main.dealerIsPlayer1 = true;
				return;
			}
		}
	}
	
	/**
	 * Converts the options that are strings to ints so they can be used in
	 * calculations. There is no need for checking that the string is indeed
	 * a number, since it was already checked before the code gets here.
	 */
	public static void convertOptionsToNumbers() {
		//Convert the options to ints.
		Main.bagValueNumb = stringToInt(Main.bagValue);
		Main.nilValueNumb = stringToInt(Main.nilValue);
		Main.doubleNilValueNumb = stringToInt(Main.doubleNilValue);
		Main.winScoreNumb = stringToInt(Main.winScore);
		Main.loseScoreNumb = stringToInt(Main.loseScore);
	}
	
	/**
	 * This method will recalculate a player's score when a change has been
	 * made to the score or the tricks taken.
	 * 
	 * @param player The player.
	 * @param roundIn The round in which to recalculate the score.
	 * @param scoreChanged True if only a change to the score was made, otherwise false.
	 */
	public static void reCalculateScore(Player player, int roundIn, boolean scoreChanged) {
		// TODO Auto-generated block
	}
	
	/**
	 * This method will recalculate a player's score when a change has been
	 * made to the score or the tricks taken and is a four handed team game.
	 * 
	 * @param player The player.
	 * @param team The team to which the changes will be made.
	 * @param roundIn The round in which to recalculate the score.
	 * @param scoreChanged True if only a change to the score was made, otherwise false.
	 */
	public static void reCalculateScore(Player player, Team team, int roundIn,
			boolean scoreChanged) {
		// TODO Auto-generated block
	}
	
	/**
	 * This method saves the player being changed and creates the new player to
	 * take his place. Also transfers bags and score from the previous player to
	 * the new player.
	 * 
	 * @param old The player to be changed.
	 * @param addNew The new player to be added.
	 * @param place The player number to be changed.
	 */
	public static void changePlayer(Player old, Player addNew, int place) {
		Main.playerPrevious = new Player(old.player);
		Main.playerPrevious.equals(old);
		
		//Copy the players from temp to the correct player.
		if (place == 1) {
			Main.playerOne.equals(addNew);
		}
		if (place == 2) {
			Main.playerTwo.equals(addNew);
		}
		if (place == 3) {
			Main.playerThree.equals(addNew);
		}
		if (place == 4) {
			Main.playerFour.equals(addNew);
		}
	}
	
	/**
	 * This method saves the player being changed and creates the new player
	 * to take his place. Also transfers bags and score from the previous
	 * player to the new player.
	 * 
	 * @param old The player to be changed.
	 * @param addNew The new player to be added.
	 * @param team The team to which the changes will be made.
	 * @param place The player number to be changed.
	 */
	public static void changePlayerTeam(Player old, Player addNew, Team team, int place) {
		// TODO Auto-generated block
	}
	
	/**
	 * Provides an indication that the player can go double.
	 * 
	 * @param The player that wants to go double.
	 */
	public static boolean goDoublePlayer(Player player) {
		int highest= -100;
		boolean allowed = false;
		
		//Find the highest score, three handed.
		if (Main.isThreeHanded) {
			if(Utils.stringToInt(Main.playerOne.score) > highest)
				highest = Utils.stringToInt(Main.playerOne.score);
			if(Utils.stringToInt(Main.playerTwo.score) > highest)
				highest = Utils.stringToInt(Main.playerTwo.score);
			if(Utils.stringToInt(Main.playerThree.score) > highest)
				highest = Utils.stringToInt(Main.playerThree.score);
			
			//Compare highest with player to see if difference exceeds 200.
			if (Utils.stringToInt(player.score) + 200 <= highest)
				allowed = true;
		}
		
		//Find the highest score, four handed.
		if (Main.isFourHandedSingle) {
			if(Utils.stringToInt(Main.playerOne.score) > highest)
				highest = Utils.stringToInt(Main.playerOne.score);
			if(Utils.stringToInt(Main.playerTwo.score) > highest)
				highest = Utils.stringToInt(Main.playerTwo.score);
			if(Utils.stringToInt(Main.playerThree.score) > highest)
				highest = Utils.stringToInt(Main.playerThree.score);
			if(Utils.stringToInt(Main.playerFour.score) > highest)
				highest = Utils.stringToInt(Main.playerFour.score);
			
			//Compare highest with player to see if difference exceeds 200.
			if (Utils.stringToInt(player.score) + 200 <= highest)
				allowed = true;
		}
		
		return allowed;
	}
	
	/**
	 * Provides an indication that the team can go double.
	 * 
	 * @param The team that wants to go double.
	 */
	public static boolean goDoubleTeam(Team team) {
		int highest = -100;
		boolean allowed = false;
		
		//Find the highest score.
		if (Utils.stringToInt(Main.teamOne.score) > highest) 
			highest = Utils.stringToInt(Main.teamOne.score);
		if (Utils.stringToInt(Main.teamTwo.score) > highest) 
			highest = Utils.stringToInt(Main.teamTwo.score);
		
		//Compare highest with team to see if difference exceeds 200.
		if (Utils.stringToInt(team.score) + 200 <= highest)
			allowed = true;
	
		return allowed;
	}

	/**
	 * This method saves the players or team statistics to a file on the
	 * device.
	 */
	public static void saveStats() {
		if(Main.optionsCheckbox.getState()) FileUtils.exportGameOptions();
		
		if(Main.isFourHandedTeams) {
			if(Main.team1Checkbox.getState()) FileUtils.exportTeamFile(Main.teamOne);
			if(Main.team2Checkbox.getState()) FileUtils.exportTeamFile(Main.teamTwo);
			if(Main.teamPreviousCheckbox.getState()) FileUtils.exportTeamFile(Main.teamPrevious);
		}
		
		if(Main.isFourHandedSingle) {
			if(Main.player1Checkbox.getState()) FileUtils.exportPlayerFile(Main.playerOne);
			if(Main.player2Checkbox.getState()) FileUtils.exportPlayerFile(Main.playerTwo);
			if(Main.player3Checkbox.getState()) FileUtils.exportPlayerFile(Main.playerThree);
			if(Main.player4Checkbox.getState()) FileUtils.exportPlayerFile(Main.playerFour);
			if(Main.playerPreviousCheckbox.getState()) FileUtils.exportPlayerFile(Main.playerPrevious);
		}
		
		if(Main.isThreeHanded) {
			if(Main.player1Checkbox.getState()) FileUtils.exportPlayerFile(Main.playerOne);
			if(Main.player2Checkbox.getState()) FileUtils.exportPlayerFile(Main.playerTwo);
			if(Main.player3Checkbox.getState()) FileUtils.exportPlayerFile(Main.playerThree);
			if(Main.playerPreviousCheckbox.getState()) FileUtils.exportPlayerFile(Main.playerPrevious);
		}
	}

	/**
	 * Parse the ini array list and extract all the information. Assign the
	 * information to the correct variables.
	 */
	public static void parseIni() {
		//Find the indexes of the division headers.
		int namesIndex = findIndex("[names]");
		int defaultIndex = findIndex("[defaults]");
		int soundIndex = findIndex("[sounds]");
		
		//Extract the information from the ini Array List.
		names = extractSettings(namesIndex);
		defaults = extractSettings(defaultIndex);
		sounds = extractSettings(soundIndex);
		
		//Sort the names Array List and copy it to the main names Array List.
		Collections.sort(names);
		Main.names = names;
		
		//Set the sounds to their variables.
		setSounds();
		
		//Set the default game configurations.
		setDefaults();
	}

	/**
	 * Create the ini array list so it can be written to a file.
	 */
	public static void createIni() {
		//Clear the contents of the ini array list if not null.
		if (Main.ini != null) Main.ini.clear();
		
		//Start putting in the data.
		Main.ini.add("[Names]");
		
		//Loop through the entire names array list.
		for (int i = 0; i < names.size(); i++) {
			Main.ini.add(names.get(i));
		}
		
		//Start putting in the data.
		Main.ini.add("");
		Main.ini.add("[Defaults]");
		
		//Loop through the entire defaults array list.
		for (int i = 0; i < defaults.size(); i++) {
			Main.ini.add(defaults.get(i));
		}
		
		//Start putting in the data.
		Main.ini.add("");
		Main.ini.add("[Sounds]");
		
		//Loop through the entire names array list.
		for (int i = 0; i < sounds.size(); i++) {
			Main.ini.add(sounds.get(i));
		}
	}
	
	/**
	 * Finds the index of the given string in the ini array list.
	 * 
	 * @param str The string to look for.
	 * @return Returns a -1 if not found, otherwise it returns an index.
	 */
	public static int findIndex(String str) {
		int index = -1;

		//Loop through the entire ini array list to find the setting headings.
		for (int i = 0; i < Main.ini.size(); i++) {
			//When found, set the index equal to i.
			if (((String) Main.ini.get(i)).equalsIgnoreCase(str)) index = i;
		}

		return index;
	}
	
	/**
	 * This method will extract the settings from the ini Array List
	 * using an index and return them as an array list.
	 * 
	 * @param index The index at which to begin the extraction.
	 */
	public static ArrayList extractSettings(int index) {
		ArrayList al = new ArrayList ();
		String temp = "";
		
		//Extract the settings from the Main ini file.
		for (int i = index + 1; i < Main.ini.size(); i++) {
			temp = (String) Main.ini.get(i);
			if (!temp.equals("")) {
				al.add(temp);
			} else {
				break;
			}
		}

		return al;
	}

	/**
	 * Sets the sounds in the ini file to the correct variables.
	 */
	public static void setSounds() {
		String temp = "";
		String[] sa;
		String prefix = "";
		String suffix = "";
				
		//Loop through the entire sounds array list to find all the settings.
		for (int i = 0; i < sounds.size(); i++) {
			//When found, set the variable in main to that string..
			temp = (String) sounds.get(i);
			sa = temp.split("=");

			//Set the variables to empty if the setting is empty.
			if (temp.endsWith("=")) {
				prefix = sa[0];

				if (prefix.equalsIgnoreCase("start")) {
					Main.soundGameStart = "";
				} else if (prefix.equalsIgnoreCase("bags")){
					Main.soundBags = "";
				} else if (prefix.equalsIgnoreCase("set")){
					Main.soundSet = "";
				} else if (prefix.equalsIgnoreCase("win")){
					Main.soundWin = "";
				} else if (prefix.equalsIgnoreCase("lose")){
					Main.soundLose = "";
				}
			//Find the properties and set them to the desired values.
			} else {
				prefix = sa[0];
				suffix = sa[1];
			
				clearSounds();
		
				if (prefix.equalsIgnoreCase("start")) {
					Main.soundGameStart = suffix;
				} else if (prefix.equalsIgnoreCase("bags")){
					Main.soundBags = suffix;
				} else if (prefix.equalsIgnoreCase("set")){
					Main.soundSet = suffix;
				} else if (prefix.equalsIgnoreCase("win")){
					Main.soundWin = suffix;
				} else if (prefix.equalsIgnoreCase("lose")){
					Main.soundLose = suffix;
				}
			}
		}
	}

	/**
	 * Sets the default settings in the ini file to the correct variables.
	 */
	public static void setDefaults() {
		String temp = "";
		String[] sa;
		String prefix = "";
		String suffix = "";

		//Reset the skins so no more than one is selected when processed.
		clearSkin();
				
		//Loop through the entire sounds array list to find all the settings.
		for (int i = 0; i < defaults.size(); i++) {
			//When found, set the variable in main to that string..
			temp = (String) defaults.get(i);
			
			sa = temp.split("=");
			
			prefix = sa[0];
			suffix = sa[1];

			//Find the properties and set them to the desired values.
			if (prefix.equalsIgnoreCase("sounds")) {
				if (suffix.equalsIgnoreCase("true")) Main.sounds = true;
				if (suffix.equalsIgnoreCase("false")) Main.sounds = false;
			} else if (prefix.equalsIgnoreCase("skin")){
				if (suffix.equalsIgnoreCase("iowa")) Main.skinIsIowa = true;
				if (suffix.equalsIgnoreCase("isu")) Main.skinIsIowaState = true;
				if (suffix.equalsIgnoreCase("uni")) Main.skinIsNorthernIowa = true;
			} else if (prefix.equalsIgnoreCase("winscore")){
				Main.winScore = suffix;
			} else if (prefix.equalsIgnoreCase("losescore")){
				Main.loseScore = suffix;
			} else if (prefix.equalsIgnoreCase("sounddir")){
				Main.soundDir = suffix;
			}
		}
	}
	
	/**
	 * Puts the names array list in a string of my choosing.
	 * 
	 * @return The string of the names array list.
	 */
	public static String namesToString() {
		String temp = "";
		
		//Loop through the names array list and create a string.
		for (int i = 0; i < names.size(); i++) {
			temp += ((String) names.get(i));
			temp += "\n";
		}

		return temp;
	}
	
	/**
	 * This method will get the values of the variables and then create
	 * the sounds array list from them.
	 */
	public static void createSoundsArrList() {
		//Clears the sounds array list.
		sounds.clear();
		
		//Adds all the sound files to the sounds array list.
		sounds.add("Start=" + Main.soundGameStart);
		sounds.add("Bags=" + Main.soundBags);
		sounds.add("Set=" + Main.soundSet);
		sounds.add("Win=" + Main.soundWin);
		sounds.add("Lose=" + Main.soundLose);
	}
	
	/**
	 * This method will get the values of the variables and then create
	 * the defaults array list from them.
	 */
	public static void createDefaultsArrList() {
		//Clear the defaults array list.
		defaults.clear();
		
		//Determine if sounds are enabled.
		if (Main.sounds) {
			defaults.add("Sounds=true");
		} else {
			defaults.add("Sounds=false");
		}
		
		//Find which skin is the default.
		if (Main.skinIsIowa) {
			defaults.add("Skin=Iowa");
		} else if (Main.skinIsIowaState) {
			defaults.add("Skin=ISU");
		} else if (Main.skinIsNorthernIowa) {
			defaults.add("Skin=UNI");
		}
		
		//Add the values set to the array list.
		defaults.add("WinScore=" + Main.winScore);
		defaults.add("LoseScore=" + Main.loseScore);
		defaults.add("SoundDir=" + Main.soundDir);
	}
}
