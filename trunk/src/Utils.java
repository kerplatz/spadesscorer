/**FINISHED
 * Utils.java
 * 
 * This class contains helper methods that do not process any display screens
 * or control inputs, such as mouse clicks. Its main function is to provide
 * the decision making for the program as it progresses.
 *
 * @author David Hoffman
 */

public class Utils {

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
		Main.skinIsIowaState = true;
		Main.skinIsIowa = false;
		Main.skinIsNorthernIowa = false;
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
	 * Clears the Game Type.
	 */
	public static void clearGameType() {
		Main.isThreeHanded = false;
		Main.isFourHandedSingle = false;
		Main.isFourHandedTeams = false;
	}
	
	/**
	 * Clears the player names.
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
	 * Clears the selected dealer.
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
	 * Clears the selected skin.
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
	 * The game type has been selected.
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
	 * The players names have been entered.
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
	 * The dealer selection has been completed.
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
	 * The skin has been chosen. ISU is the default.
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
	 * The game options have been entered.
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
	 * This method converts the string value of a number to an int.
	 * 
	 * @param tricks The string to be converted.
	 * @return The actual value of the tricks taken.
	 */
	public static int stringToInt(String numb) {
		int temp = 0;
		
		//Convert the string to an int. Set it to a value of '0' if not a number.
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
		// TODO Auto-generated method stub
	}

	/**
	 * Create the ini array list so it can be written to a file.
	 */
	public static void createIni() {
		// TODO Auto-generated method stub
	}
}
