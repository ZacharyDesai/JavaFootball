import java.util.List;

/**
 * Stores the team stats for a Java football game
 * @author Zachary Desai (github.com/ZacharyDesai)
 */
public class FootballStats {
	
	// instance variables
	private String awayAbbr;
	private String homeAbbr;
	private int awayOffensePlays;
	private int awayOffenseYards;
	private double awayYardsPerPlay;
	private int awayPassingCompletions;
	private int awayPassingAttempts;
	private int awayPassingYards;
	private double awayPassingYardsPerAttempt;
	private int awayPassingTouchdowns;
	private int awayPassingInterceptions;
	private int awayPassingPickSixes;
	private int awayPassingSacks;
	private int awayPassingSackYards;
	private double awayPassingQBR;
	private int awayRushingAttempts;
	private int awayRushingYards;
	private double awayRushingYardsPerAttempt;
	private int awayRushingTouchdowns;
	private int awayRushingFumbles;
	private int awayRushingStripSixes;
	private int awayPenalties;
	private int awayPenaltyYards;
	private int awayTurnoverOnDowns;
	private int awayTurnovers;
	private int awayInterceptions;
	private int awayFumbles;
	private int awaySafeties;
	private int awayPuntReturnTouchdowns;
	private int awayFieldGoalReturnTouchdowns;
	private int awayKickoffReturnTouchdowns;
	private int awayOnsideKickCompletions;
	private int awayOnsideKickAttempts;
	private int awayExtraPointCompletions;
	private int awayExtraPointAttempts;
	private int awayTwoPointCompletions;
	private int awayTwoPointAttempts;
	private int awayFieldGoalCompletions;
	private int awayFieldGoalAttempts;
	private int awayFieldGoalLong;
	private int homeOffensePlays;
	private int homeOffenseYards;
	private double homeYardsPerPlay;
	private int homePassingCompletions;
	private int homePassingAttempts;
	private int homePassingYards;
	private double homePassingYardsPerAttempt;
	private int homePassingTouchdowns;
	private int homePassingInterceptions;
	private int homePassingPickSixes;
	private int homePassingSacks;
	private int homePassingSackYards;
	private double homePassingQBR;
	private int homeRushingAttempts;
	private int homeRushingYards;
	private double homeRushingYardsPerAttempt;
	private int homeRushingTouchdowns;
	private int homeRushingFumbles;
	private int homeRushingStripSixes;
	private int homePenalties;
	private int homePenaltyYards;
	private int homeTurnoverOnDowns;
	private int homeTurnovers;
	private int homeInterceptions;
	private int homeFumbles;
	private int homeSafeties;
	private int homePuntReturnTouchdowns;
	private int homeFieldGoalReturnTouchdowns;
	private int homeKickoffReturnTouchdowns;
	private int homeOnsideKickCompletions;
	private int homeOnsideKickAttempts;
	private int homeExtraPointCompletions;
	private int homeExtraPointAttempts;
	private int homeTwoPointCompletions;
	private int homeTwoPointAttempts;
	private int homeFieldGoalCompletions;
	private int homeFieldGoalAttempts;
	private int homeFieldGoalLong;
	
	// Constructs a new football stats object
	public FootballStats(String awayAbbr, String homeAbbr) {
		this.awayAbbr = awayAbbr;
		this.homeAbbr = homeAbbr;
		awayOffensePlays = 0;
		awayOffenseYards = 0;
		awayYardsPerPlay = 0.0;
		awayPassingCompletions = 0;
		awayPassingAttempts = 0;
		awayPassingYards = 0;
		awayPassingYardsPerAttempt = 0.0;
		awayPassingTouchdowns = 0;
		awayPassingInterceptions = 0;
		awayPassingPickSixes = 0;
		awayPassingSacks = 0;
		awayPassingSackYards = 0;
		awayPassingQBR = 0.0;
		awayRushingAttempts = 0;
		awayRushingYards = 0;
		awayRushingYardsPerAttempt = 0.0;
		awayRushingTouchdowns = 0;
		awayRushingFumbles = 0;
		awayRushingStripSixes = 0;
		awayPenalties = 0;
		awayPenaltyYards = 0;
		awayTurnoverOnDowns = 0;
		awayTurnovers = 0;
		awayInterceptions = 0;
		awayFumbles = 0;
		awayPuntReturnTouchdowns = 0;
		awayFieldGoalReturnTouchdowns = 0;
		awayKickoffReturnTouchdowns = 0;
		awayOnsideKickCompletions = 0;
		awayOnsideKickAttempts = 0;
		awayExtraPointCompletions = 0;
		awayExtraPointAttempts = 0;
		awayTwoPointCompletions = 0;
		awayTwoPointAttempts = 0;
		awayFieldGoalCompletions = 0;
		awayFieldGoalAttempts = 0;
		awayFieldGoalLong = 0;
		homeOffensePlays = 0;
		homeOffenseYards = 0;
		homeYardsPerPlay = 0.0;
		homePassingCompletions = 0;
		homePassingAttempts = 0;
		homePassingYards = 0;
		homePassingYardsPerAttempt = 0.0;
		homePassingTouchdowns = 0;
		homePassingInterceptions = 0;
		homePassingPickSixes = 0;
		homePassingSacks = 0;
		homePassingSackYards = 0;
		homePassingQBR = 0.0;
		homeRushingAttempts = 0;
		homeRushingYards = 0;
		homeRushingYardsPerAttempt = 0.0;
		homeRushingTouchdowns = 0;
		homeRushingFumbles = 0;
		homeRushingStripSixes = 0;
		homePenalties = 0;
		homePenaltyYards = 0;
		homeTurnoverOnDowns = 0;
		homeTurnovers = 0;
		homeInterceptions = 0;
		homeFumbles = 0;
		homePuntReturnTouchdowns = 0;
		homeFieldGoalReturnTouchdowns = 0;
		homeKickoffReturnTouchdowns = 0;
		homeOnsideKickCompletions = 0;
		homeOnsideKickAttempts = 0;
		homeExtraPointCompletions = 0;
		homeExtraPointAttempts = 0;
		homeTwoPointCompletions = 0;
		homeTwoPointAttempts = 0;
		homeFieldGoalCompletions = 0;
		homeFieldGoalAttempts = 0;
		homeFieldGoalLong = 0;
		
	}
	
	// Reads the play-by-play and updates the game stats accordingly
	public void updateStats(List<JavaFootballPlay> playByPlayList) {
		for (JavaFootballPlay play : playByPlayList) {
			if (play instanceof JavaFootballPlayResult) {
				String result = play.toString();
				if (result.contains("END OF")) {
					String[] temp = result.split("END OF");
					result = temp[0];
				}
				if (result.contains("TWO MINUTE")) {
					String[] temp = result.split("END OF");
					result = temp[0];
				}
				String[] resultWords = result.split(" ");
				if (resultWords[0].equals("Timeout") && resultWords.length > 4) {
					String[] temp = new String[resultWords.length - 4];
					for (int i = 0; i < temp.length; i++) {
						temp[i] = resultWords[i + 4];
					}
					resultWords = temp;
				}
				if (result.contains("kickoff")) {
					if (result.contains("FUMBLED")) {
						if (result.contains("TOUCHDOWN")) {
							if (resultWords[0].equals(awayAbbr)) {
								homeTurnovers++;
								homeFumbles++;
								awayKickoffReturnTouchdowns++;
							}
							else if (resultWords[0].equals(homeAbbr)) {
								awayTurnovers++;
								awayFumbles++;
								homeKickoffReturnTouchdowns++;
							}
							else {
								System.out.println("ERROR #1");
							}
						}
						else {
							if (resultWords[0].equals(awayAbbr)) {
								homeTurnovers++;
								homeFumbles++;
							}
							else if (resultWords[0].equals(homeAbbr)) {
								awayTurnovers++;
								awayFumbles++;
							}
							else {
								System.out.println("ERROR #2");
							}
						}
					}
					else {
						if (result.contains("TOUCHDOWN")) {
							if (resultWords[0].equals(awayAbbr)) {
								homeKickoffReturnTouchdowns++;
							}
							else if (resultWords[0].equals(homeAbbr)) {
								awayKickoffReturnTouchdowns++;
							}
							else {
								System.out.println("ERROR #3");
							}
						}
					}
				}
				else if (result.contains("punt")) {
					if (result.contains("FUMBLED")) {
						if (result.contains("TOUCHDOWN")) {
							if (resultWords[0].equals(awayAbbr)) {
								homeTurnovers++;
								homeFumbles++;
								awayPuntReturnTouchdowns++;
							}
							else if (resultWords[0].equals(homeAbbr)) {
								awayTurnovers++;
								awayFumbles++;
								homePuntReturnTouchdowns++;
							}
							else {
								System.out.println("ERROR #4");
							}
						}
						else {
							if (resultWords[0].equals(awayAbbr)) {
								homeTurnovers++;
								homeFumbles++;
							}
							else if (resultWords[0].equals(homeAbbr)) {
								awayTurnovers++;
								awayFumbles++;
							}
							else {
								System.out.println("ERROR #5");
							}
						}
					}
					else {
						if (result.contains("TOUCHDOWN")) {
							if (resultWords[0].equals(awayAbbr)) {
								homePuntReturnTouchdowns++;
							}
							else if (resultWords[0].equals(homeAbbr)) {
								awayPuntReturnTouchdowns++;
							}
							else {
								System.out.println("ERROR #6");
							}
						}
					}
				}
				else if (result.contains("onside")) {
					if (resultWords[0].equals(awayAbbr)) {
						awayOnsideKickAttempts++;
						if (resultWords[5].equals(awayAbbr)) {
							awayOnsideKickCompletions++;
						}
					}
					else if (resultWords[0].equals(homeAbbr)) {
						homeOnsideKickAttempts++;
						if (resultWords[5].equals(awayAbbr)) {
							homeOnsideKickCompletions++;
						}
					}
					else {
						System.out.println("ERROR #7");
					}
				}
				else if (result.contains("extra point")) {
					if (resultWords[0].equals(awayAbbr)) {
						awayExtraPointAttempts++;
						if (result.contains("RETURNED")) {
							homeSafeties++;
						}
						else if (result.contains("NO GOOD")) {
							
						}
						else {
							awayExtraPointCompletions++;
						}
					}
					else if (resultWords[0].equals(homeAbbr)) {
						homeExtraPointAttempts++;
						if (result.contains("RETURNED")) {
							awaySafeties++;
						}
						else if (result.contains("NO GOOD")) {
							
						}
						else {
							homeExtraPointCompletions++;
						}
					}
					else {
						System.out.println("ERROR #8");
					}
				}
				else if (result.contains("two point")) {
					if (resultWords[0].equals(awayAbbr)) {
						awayTwoPointAttempts++;
						if (result.contains("RETURNED")) {
							homeSafeties++;
						}
						else if (result.contains("NO GOOD")) {
							
						}
						else {
							awayTwoPointCompletions++;
						}
					}
					else if (resultWords[0].equals(homeAbbr)) {
						homeTwoPointAttempts++;
						if (result.contains("RETURNED")) {
							awaySafeties++;
						}
						else if (result.contains("NO GOOD")) {
							
						}
						else {
							homeTwoPointCompletions++;
						}
					}
					else {
						System.out.println("ERROR #9");
					}
				}
				else if (result.contains("field goal")) {
					if (resultWords[0].equals(awayAbbr)) {
						awayFieldGoalAttempts++;
						if (result.contains("RETURNED")) {
							homeFieldGoalReturnTouchdowns++;
						}
						else if (result.contains("NO GOOD")) {
							
						}
						else {
							awayFieldGoalCompletions++;
							int distance = Integer.parseInt(resultWords[1]);
							if (distance > awayFieldGoalLong) {
								awayFieldGoalLong = distance;
							}
						}
					}
					else if (resultWords[0].equals(homeAbbr)) {
						homeFieldGoalAttempts++;
						if (result.contains("RETURNED")) {
							awayFieldGoalReturnTouchdowns++;
						}
						else if (result.contains("NO GOOD")) {
							
						}
						else {
							homeFieldGoalCompletions++;
							int distance = Integer.parseInt(resultWords[1]);
							if (distance > homeFieldGoalLong) {
								homeFieldGoalLong = distance;
							}
						}
					}
					else {
						System.out.println("ERROR #10");
					}
				}
				else if (result.contains("PENALTY")) {
					String teamAbbr = resultWords[4];
					if (teamAbbr.indexOf('.') != -1) {
						teamAbbr = teamAbbr.substring(0, teamAbbr.indexOf('.'));
					}
					if (teamAbbr.equals(awayAbbr)) {
						awayPenalties++;
						int distance = Integer.parseInt(resultWords[0]);
						awayPenaltyYards += distance;
						if (result.contains("SAFETY")) {
							homeSafeties++;
						}
					}
					else if (teamAbbr.equals(homeAbbr)) {
						homePenalties++;
						int distance = Integer.parseInt(resultWords[0]);
						homePenaltyYards += distance;
						if (result.contains("SAFETY")) {
							awaySafeties++;
						}
					}
					else {
						System.out.println("ERROR #11");
					}
				}
				else if (result.contains("FUMBLE")) {
					if (result.contains("TOUCHDOWN")) {
						if (resultWords[0].equals(awayAbbr)) {
							awayOffensePlays++;
							awayRushingAttempts++;
							awayRushingFumbles++;
							awayRushingStripSixes++;
							awayTurnovers++;
							awayFumbles++;
						}
						else if (resultWords[0].equals(homeAbbr)) {
							homeOffensePlays++;
							homeRushingAttempts++;
							homeRushingFumbles++;
							homeRushingStripSixes++;
							homeTurnovers++;
							homeFumbles++;
						}
						else {
							System.out.println("ERROR #12");
						}
					}
					else {
						if (resultWords[0].equals(awayAbbr)) {
							awayOffensePlays++;
							awayRushingAttempts++;
							awayRushingFumbles++;
							awayTurnovers++;
							awayFumbles++;
						}
						else if (resultWords[0].equals(homeAbbr)) {
							homeOffensePlays++;
							homeRushingAttempts++;
							homeRushingFumbles++;
							homeTurnovers++;
							homeFumbles++;
						}
						else {
							System.out.println("ERROR #13");
						}
					}
				}
				else if (result.contains("INTERCEPT")) {
					if (result.contains("TOUCHDOWN")) {
						if (resultWords[0].equals(awayAbbr)) {
							awayOffensePlays++;
							awayPassingAttempts++;
							awayPassingInterceptions++;
							awayPassingPickSixes++;
							awayTurnovers++;
							awayInterceptions++;
						}
						else if (resultWords[0].equals(homeAbbr)) {
							homeOffensePlays++;
							homePassingAttempts++;
							homePassingInterceptions++;
							homePassingPickSixes++;
							homeTurnovers++;
							homeInterceptions++;
						}
						else {
							System.out.println("ERROR #14");
						}
					}
					else {
						if (resultWords[0].equals(awayAbbr)) {
							awayOffensePlays++;
							awayPassingAttempts++;
							awayPassingInterceptions++;
							awayTurnovers++;
							awayInterceptions++;
						}
						else if (resultWords[0].equals(homeAbbr)) {
							homeOffensePlays++;
							homePassingAttempts++;
							homePassingInterceptions++;
							homeTurnovers++;
							homeInterceptions++;
						}
						else {
							System.out.println("ERROR #15");
						}
					}
				}
				else if (result.contains("SACK")) {
					String teamAbbr = resultWords[4];
					if (teamAbbr.indexOf('.') != -1) {
						teamAbbr = teamAbbr.substring(0, teamAbbr.indexOf('.'));
					}
					if (teamAbbr.equals(homeAbbr)) {
						if (result.contains("SAFETY")) {
							homeSafeties++;
						}
						if (result.contains("TURNOVER ON DOWNS")) {
							awayTurnoverOnDowns++;
						}
						awayOffensePlays++;
						awayPassingSacks++;
						int distance = Integer.parseInt(resultWords[0]);
						awayPassingSackYards += distance;
					}
					else if (teamAbbr.equals(awayAbbr)) {
						if (result.contains("SAFETY")) {
							awaySafeties++;
						}
						if (result.contains("TURNOVER ON DOWNS")) {
							homeTurnoverOnDowns++;
						}
						homeOffensePlays++;
						homePassingSacks++;
						int distance = Integer.parseInt(resultWords[0]);
						homePassingSackYards += distance;
					}
					else {
						System.out.println("ERROR #16");
					}
				}
				else if (result.contains("TACKLE FOR A LOSS")) {
					String teamAbbr = resultWords[7];
					if (teamAbbr.indexOf('.') != -1) {
						teamAbbr = teamAbbr.substring(0, teamAbbr.indexOf('.'));
					}
					if (teamAbbr.equals(homeAbbr)) {
						if (result.contains("SAFETY")) {
							homeSafeties++;
						}
						if (result.contains("TURNOVER ON DOWNS")) {
							awayTurnoverOnDowns++;
						}
						awayOffensePlays++;
						awayRushingAttempts++;
						int distance = Integer.parseInt(resultWords[0]);
						awayRushingYards -= distance;
					}
					else if (teamAbbr.equals(awayAbbr)) {
						if (result.contains("SAFETY")) {
							awaySafeties++;
						}
						if (result.contains("TURNOVER ON DOWNS")) {
							homeTurnoverOnDowns++;
						}
						homeOffensePlays++;
						awayRushingAttempts++;
						int distance = Integer.parseInt(resultWords[0]);
						awayRushingYards -= distance;
					}
					else {
						System.out.println("ERROR #17");
					}
				}
				else if (result.contains("pass")) {
					if (result.contains("TURNOVER ON DOWNS")) {
						String teamAbbr = resultWords[resultWords.length - 4];
						if (teamAbbr.indexOf('.') != -1) {
							teamAbbr = teamAbbr.substring(0, teamAbbr.indexOf('.'));
						}
						if (teamAbbr.equals(awayAbbr)) {
							awayTurnoverOnDowns++;
						}
						else if (teamAbbr.equals(homeAbbr)) {
							homeTurnoverOnDowns++;
						}
						else {
							System.out.println("ERROR #18");
						}
					}
					if (result.contains("TOUCHDOWN")) {
						String teamAbbr = resultWords[resultWords.length - 2];
						if (teamAbbr.indexOf('.') != -1) {
							teamAbbr = teamAbbr.substring(0, teamAbbr.indexOf('.'));
						}
						if (teamAbbr.equals(awayAbbr)) {
							awayPassingTouchdowns++;
						}
						else if (teamAbbr.equals(homeAbbr)) {
							homePassingTouchdowns++;
						}
						else {
							System.out.println("ERROR #19");
						}
					}
					if (result.contains("screen pass")) {
						if (result.contains("No gain")) {
							String teamAbbr = resultWords[7];
							if (teamAbbr.indexOf('.') != -1) {
								teamAbbr = teamAbbr.substring(0, teamAbbr.indexOf('.'));
							}
							if (teamAbbr.equals(awayAbbr)) {
								awayOffensePlays++;
								awayPassingAttempts++;
								awayPassingCompletions++;
							}
							else if (teamAbbr.equals(homeAbbr)) {
								homeOffensePlays++;
								homePassingAttempts++;
								homePassingCompletions++;
							}
							else {
								System.out.println("ERROR #20");
							}
						}
						else {
							String teamAbbr = resultWords[5];
							if (teamAbbr.indexOf('.') != -1) {
								teamAbbr = teamAbbr.substring(0, teamAbbr.indexOf('.'));
							}
							int yardsGained = Integer.parseInt(resultWords[0]);
							if (teamAbbr.equals(awayAbbr)) {
								awayOffensePlays++;
								awayPassingAttempts++;
								awayPassingCompletions++;
								awayPassingYards += yardsGained;
								awayOffenseYards += yardsGained;
							}
							else if (teamAbbr.equals(homeAbbr)) {
								homeOffensePlays++;
								homePassingAttempts++;
								homePassingCompletions++;
								homePassingYards += yardsGained;
								homeOffenseYards += yardsGained;
							}
							else {
								System.out.println("ERROR #21");
							}
						}
					}
					else if (result.contains("play action")) {
						if (result.contains("No gain")) {
							String teamAbbr = resultWords[8];
							if (teamAbbr.indexOf('.') != -1) {
								teamAbbr = teamAbbr.substring(0, teamAbbr.indexOf('.'));
							}
							if (teamAbbr.equals(awayAbbr)) {
								awayOffensePlays++;
								awayPassingAttempts++;
								awayPassingCompletions++;
							}
							else if (teamAbbr.equals(homeAbbr)) {
								homeOffensePlays++;
								homePassingAttempts++;
								homePassingCompletions++;
							}
							else {
								System.out.println("ERROR #22");
							}
						}
						else {
							String teamAbbr = resultWords[6];
							if (teamAbbr.indexOf('.') != -1) {
								teamAbbr = teamAbbr.substring(0, teamAbbr.indexOf('.'));
							}
							int yardsGained = Integer.parseInt(resultWords[0]);
							if (teamAbbr.equals(awayAbbr)) {
								awayOffensePlays++;
								awayPassingAttempts++;
								awayPassingCompletions++;
								awayPassingYards += yardsGained;
								awayOffenseYards += yardsGained;
							}
							else if (teamAbbr.equals(homeAbbr)) {
								homeOffensePlays++;
								homePassingAttempts++;
								homePassingCompletions++;
								homePassingYards += yardsGained;
								homeOffenseYards += yardsGained;
							}
							else {
								System.out.println("ERROR #23");
							}
						}
					}
					else {
						if (result.contains("Incomplete")) {
							String teamAbbr = resultWords[3];
							if (teamAbbr.indexOf('.') != -1) {
								teamAbbr = teamAbbr.substring(0, teamAbbr.indexOf('.'));
							}
							if (teamAbbr.equals(awayAbbr)) {
								awayOffensePlays++;
								awayPassingAttempts++;
							}
							else if (teamAbbr.equals(homeAbbr)) {
								homeOffensePlays++;
								homePassingAttempts++;
							}
							else {
								System.out.println("ERROR #24");
							}
						}
						else {
							String teamAbbr = resultWords[4];
							if (teamAbbr.indexOf('.') != -1) {
								teamAbbr = teamAbbr.substring(0, teamAbbr.indexOf('.'));
							}
							int yardsGained = Integer.parseInt(resultWords[0]);
							if (teamAbbr.equals(awayAbbr)) {
								awayOffensePlays++;
								awayPassingAttempts++;
								awayPassingCompletions++;
								awayPassingYards += yardsGained;
								awayOffenseYards += yardsGained;
							}
							else if (teamAbbr.equals(homeAbbr)) {
								homeOffensePlays++;
								homePassingAttempts++;
								homePassingCompletions++;
								homePassingYards += yardsGained;
								homeOffenseYards += yardsGained;
							}
							else {
								System.out.println("ERROR #25");
							}
						}
					}
				}
				else if (result.contains("run")) {
					if (result.contains("TURNOVER ON DOWNS")) {
						String teamAbbr = resultWords[resultWords.length - 4];
						if (teamAbbr.indexOf('.') != -1) {
							teamAbbr = teamAbbr.substring(0, teamAbbr.indexOf('.'));
						}
						if (teamAbbr.equals(awayAbbr)) {
							awayTurnoverOnDowns++;
						}
						else if (teamAbbr.equals(homeAbbr)) {
							homeTurnoverOnDowns++;
						}
						else {
							System.out.println("ERROR #26");
						}
					}
					if (result.contains("TOUCHDOWN")) {
						String teamAbbr = resultWords[resultWords.length - 2];
						if (teamAbbr.indexOf('.') != -1) {
							teamAbbr = teamAbbr.substring(0, teamAbbr.indexOf('.'));
						}
						if (teamAbbr.equals(awayAbbr)) {
							awayRushingTouchdowns++;
						}
						else if (teamAbbr.equals(homeAbbr)) {
							homeRushingTouchdowns++;
						}
						else {
							System.out.println("ERROR #27");
						}
					}
					if (result.contains("run draw")) {
						if (result.contains("No gain")) {
							String teamAbbr = resultWords[7];
							if (teamAbbr.indexOf('.') != -1) {
								teamAbbr = teamAbbr.substring(0, teamAbbr.indexOf('.'));
							}
							if (teamAbbr.equals(awayAbbr)) {
								awayOffensePlays++;
								awayRushingAttempts++;
							}
							else if (teamAbbr.equals(homeAbbr)) {
								homeOffensePlays++;
								homeRushingAttempts++;
							}
							else {
								System.out.println("ERROR #28");
							}
						}
						else {
							String teamAbbr = resultWords[5];
							if (teamAbbr.indexOf('.') != -1) {
								teamAbbr = teamAbbr.substring(0, teamAbbr.indexOf('.'));
							}
							int yardsGained = Integer.parseInt(resultWords[0]);
							if (teamAbbr.equals(awayAbbr)) {
								awayOffensePlays++;
								awayRushingAttempts++;
								awayRushingYards += yardsGained;
								awayOffenseYards += yardsGained;
							}
							else if (teamAbbr.equals(homeAbbr)) {
								homeOffensePlays++;
								homeRushingAttempts++;
								homeRushingYards += yardsGained;
								homeOffenseYards += yardsGained;
							}
							else {
								System.out.println("ERROR #29");
							}
						}
					}
					else {
						if (result.contains("No gain")) {
							String teamAbbr = resultWords[6];
							if (teamAbbr.indexOf('.') != -1) {
								teamAbbr = teamAbbr.substring(0, teamAbbr.indexOf('.'));
							}
							if (teamAbbr.equals(awayAbbr)) {
								awayOffensePlays++;
								awayRushingAttempts++;
							}
							else if (teamAbbr.equals(homeAbbr)) {
								homeOffensePlays++;
								homeRushingAttempts++;
							}
							else {
								System.out.println("ERROR #30");
							}
						}
						else {
							String teamAbbr = resultWords[4];
							if (teamAbbr.indexOf('.') != -1) {
								teamAbbr = teamAbbr.substring(0, teamAbbr.indexOf('.'));
							}
							int yardsGained = Integer.parseInt(resultWords[0]);
							if (teamAbbr.equals(awayAbbr)) {
								awayOffensePlays++;
								awayRushingAttempts++;
								awayRushingYards += yardsGained;
								awayOffenseYards += yardsGained;
							}
							else if (teamAbbr.equals(homeAbbr)) {
								homeOffensePlays++;
								homeRushingAttempts++;
								homeRushingYards += yardsGained;
								homeOffenseYards += yardsGained;
							}
							else {
								System.out.println("ERROR #31");
							}
						}
					}
				}
			}
		}
		awayYardsPerPlay = ((double) awayOffenseYards) / awayOffensePlays;
		awayPassingYardsPerAttempt = ((double) awayPassingYards) / awayPassingAttempts;
		awayRushingYardsPerAttempt = ((double) awayRushingYards) / awayRushingAttempts;
		homeYardsPerPlay = ((double) homeOffenseYards) / homeOffensePlays;
		homePassingYardsPerAttempt = ((double) homePassingYards) / homePassingAttempts;
		homeRushingYardsPerAttempt = ((double) homeRushingYards) / homeRushingAttempts;
		if (awayOffensePlays == 0) {
			awayYardsPerPlay = 0.0;
		}
		if (awayPassingAttempts == 0) {
			awayPassingYardsPerAttempt = 0.0;
		}
		if (awayRushingAttempts == 0) {
			awayRushingYardsPerAttempt = 0.0;
		}
		if (homeOffensePlays == 0) {
			homeYardsPerPlay = 0.0;
		}
		if (homePassingAttempts == 0) {
			homePassingYardsPerAttempt = 0.0;
		}
		if (homeRushingAttempts == 0) {
			homeRushingYardsPerAttempt = 0.0;
		}
		awayPassingQBR = calculateQBR(false);
		homePassingQBR = calculateQBR(true);
	}
	
	// Calculates the QBR for the away or home passing team
	private double calculateQBR(boolean home) {
		double completionPct;
		double yardAvg;
		double touchdownPct;
		double interceptionPct;
		if (!home) {
			completionPct = ((double) awayPassingCompletions) / awayPassingAttempts;
			yardAvg = awayPassingYardsPerAttempt;
			touchdownPct = ((double) awayPassingTouchdowns) / awayPassingAttempts;
			interceptionPct = ((double) awayPassingInterceptions) / awayPassingAttempts;
			if (awayPassingAttempts == 0) {
				completionPct = 0.0;
				yardAvg = 0.0;
				touchdownPct = 0.0;
				interceptionPct = 0.0;
			}
		}
		else {
			completionPct = ((double) homePassingCompletions) / homePassingAttempts;
			yardAvg = homePassingYardsPerAttempt;
			touchdownPct = ((double) homePassingTouchdowns) / homePassingAttempts;
			interceptionPct = ((double) homePassingInterceptions) / homePassingAttempts;
			if (homePassingAttempts == 0) {
				completionPct = 0.0;
				yardAvg = 0.0;
				touchdownPct = 0.0;
				interceptionPct = 0.0;
			}
		}
		double part1 = (completionPct * 100 - 30) * .05;
		double part2 = (yardAvg - 3) * .25;
		double part3 = touchdownPct * 100 * .2;
		double part4 = 2.375 - (interceptionPct * 100 * .25);
		if (part1 < 0) {
			part1 = 0;
		}
		if (part1 > 2.375) {
			part1 = 2.375;
		}
		if (part2 < 0) {
			part2 = 0;
		}
		if (part2 > 2.375) {
			part2 = 2.375;
		}
		if (part3 < 0) {
			part3 = 0;
		}
		if (part3 > 2.375) {
			part3 = 2.375;
		}
		if (part4 < 0) {
			part4 = 0;
		}
		if (part4 > 2.375) {
			part4 = 2.375;
		}
		double rating = (part1 + part2 + part3 + part4) / 6 * 100;
		return rating;
	}
	
	// Returns the game stats as a String
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-32s     ", "Team Abbreviation"));
		sb.append(String.format("%-6s     ", awayAbbr));
		sb.append(String.format("%-6s%n", homeAbbr));
		sb.append(String.format("%-32s     ", "Total Plays"));
		sb.append(String.format("%-6d     ", awayOffensePlays));
		sb.append(String.format("%-6d%n", homeOffensePlays));
		sb.append(String.format("%-32s     ", "Total Yards"));
		sb.append(String.format("%-6d     ", awayOffenseYards));
		sb.append(String.format("%-6d%n", homeOffenseYards));
		sb.append(String.format("%-32s     ", "Average Yards per Play"));
		sb.append(String.format("%-6.1f     ", awayYardsPerPlay));
		sb.append(String.format("%-6.1f%n", homeYardsPerPlay));
		sb.append(String.format("%-32s     ", "Pass Completions/Attempts"));
		sb.append(String.format("%-2d/%2d      ", awayPassingCompletions, awayPassingAttempts));
		sb.append(String.format("%-2d/%2d %n", homePassingCompletions, homePassingAttempts));
		sb.append(String.format("%-32s     ", "Pass Yards"));
		sb.append(String.format("%-6d     ", awayPassingYards));
		sb.append(String.format("%-6d%n", homePassingYards));
		sb.append(String.format("%-32s     ", "Pass Yards per Attempt"));
		sb.append(String.format("%-6.1f     ", awayPassingYardsPerAttempt));
		sb.append(String.format("%-6.1f%n", homePassingYardsPerAttempt));
		sb.append(String.format("%-32s     ", "Pass Touchdowns"));
		sb.append(String.format("%-6d     ", awayPassingTouchdowns));
		sb.append(String.format("%-6d%n", homePassingTouchdowns));
		sb.append(String.format("%-32s     ", "Pass Interceptions"));
		sb.append(String.format("%-6d     ", awayPassingInterceptions));
		sb.append(String.format("%-6d%n", homePassingInterceptions));
		sb.append(String.format("%-32s     ", "Pass Pick Sixes"));
		sb.append(String.format("%-6d     ", awayPassingPickSixes));
		sb.append(String.format("%-6d%n", homePassingPickSixes));
		sb.append(String.format("%-32s     ", "Pass Sacks-Yards"));
		sb.append(String.format("%-2d-%2d      ", awayPassingSacks, awayPassingSackYards));
		sb.append(String.format("%-2d-%2d %n", homePassingSacks, homePassingSackYards));
		sb.append(String.format("%-32s     ", "Pass QBR"));
		sb.append(String.format("%-6.1f     ", awayPassingQBR));
		sb.append(String.format("%-6.1f%n", homePassingQBR));
		sb.append(String.format("%-32s     ", "Rush Attempts"));
		sb.append(String.format("%-6d     ", awayRushingAttempts));
		sb.append(String.format("%-6d%n", homeRushingAttempts));
		sb.append(String.format("%-32s     ", "Rush Yards"));
		sb.append(String.format("%-6d     ", awayRushingYards));
		sb.append(String.format("%-6d%n", homeRushingYards));
		sb.append(String.format("%-32s     ", "Rush Yards per Attempt"));
		sb.append(String.format("%-6.1f     ", awayRushingYardsPerAttempt));
		sb.append(String.format("%-6.1f%n", homeRushingYardsPerAttempt));
		sb.append(String.format("%-32s     ", "Rush Touchdowns"));
		sb.append(String.format("%-6d     ", awayRushingTouchdowns));
		sb.append(String.format("%-6d%n", homeRushingTouchdowns));
		sb.append(String.format("%-32s     ", "Rush Fumbles"));
		sb.append(String.format("%-6d     ", awayRushingFumbles));
		sb.append(String.format("%-6d%n", homeRushingFumbles));
		sb.append(String.format("%-32s     ", "Rush Strip Sixes"));
		sb.append(String.format("%-6d     ", awayRushingStripSixes));
		sb.append(String.format("%-6d%n", homeRushingStripSixes));
		sb.append(String.format("%-32s     ", "Penalties-Yards"));
		sb.append(String.format("%-2d-%2d      ", awayPenalties, awayPenaltyYards));
		sb.append(String.format("%-2d-%2d %n", homePenalties, homePenaltyYards));
		sb.append(String.format("%-32s     ", "Turnover on Downs"));
		sb.append(String.format("%-6d     ", awayTurnoverOnDowns));
		sb.append(String.format("%-6d%n", homeTurnoverOnDowns));
		sb.append(String.format("%-32s     ", "Turnovers"));
		sb.append(String.format("%-6d     ", awayTurnovers));
		sb.append(String.format("%-6d%n", homeTurnovers));
		sb.append(String.format("%-32s     ", "Interceptions"));
		sb.append(String.format("%-6d     ", awayInterceptions));
		sb.append(String.format("%-6d%n", homeInterceptions));
		sb.append(String.format("%-32s     ", "Fumbles"));
		sb.append(String.format("%-6d     ", awayFumbles));
		sb.append(String.format("%-6d%n", homeFumbles));
		sb.append(String.format("%-32s     ", "Safeties"));
		sb.append(String.format("%-6d     ", awaySafeties));
		sb.append(String.format("%-6d%n", homeSafeties));
		sb.append(String.format("%-32s     ", "Punt Return Touchdowns"));
		sb.append(String.format("%-6d     ", awayPuntReturnTouchdowns));
		sb.append(String.format("%-6d%n", homePuntReturnTouchdowns));
		sb.append(String.format("%-32s     ", "Field Goal Return Touchdowns"));
		sb.append(String.format("%-6d     ", awayFieldGoalReturnTouchdowns));
		sb.append(String.format("%-6d%n", homeFieldGoalReturnTouchdowns));
		sb.append(String.format("%-32s     ", "Kickoff Return Touchdowns"));
		sb.append(String.format("%-6d     ", awayKickoffReturnTouchdowns));
		sb.append(String.format("%-6d%n", homeKickoffReturnTouchdowns));
		sb.append(String.format("%-32s     ", "Onside Kick Recoveries-Attempts"));
		sb.append(String.format("%-2d/%2d      ", awayOnsideKickCompletions, awayOnsideKickAttempts));
		sb.append(String.format("%-2d/%2d %n", homeOnsideKickCompletions, homeOnsideKickAttempts));
		sb.append(String.format("%-32s     ", "Extra Points Made/Attempted"));
		sb.append(String.format("%-2d/%2d      ", awayExtraPointCompletions, awayExtraPointAttempts));
		sb.append(String.format("%-2d/%2d %n", homeExtraPointCompletions, homeExtraPointAttempts));
		sb.append(String.format("%-32s     ", "Two Points Made/Attempted"));
		sb.append(String.format("%-2d/%2d      ", awayTwoPointCompletions, awayTwoPointAttempts));
		sb.append(String.format("%-2d/%2d %n", homeTwoPointCompletions, homeTwoPointAttempts));
		sb.append(String.format("%-32s     ", "Field Goals Made/Attempted"));
		sb.append(String.format("%-2d/%2d      ", awayFieldGoalCompletions, awayFieldGoalAttempts));
		sb.append(String.format("%-2d/%2d %n", homeFieldGoalCompletions, homeFieldGoalAttempts));
		sb.append(String.format("%-32s     ", "Field Goal Long"));
		sb.append(String.format("%-6d     ", awayFieldGoalLong));
		sb.append(String.format("%-6d%n", homeFieldGoalLong));
		return sb.toString();
	}
}
