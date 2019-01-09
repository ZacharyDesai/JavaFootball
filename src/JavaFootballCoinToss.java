/**
 * Stores the result of the coin toss of a Java football game for play-by-play
 * @author Zachary Desai (github.com/ZacharyDesai)
 */
public class JavaFootballCoinToss extends JavaFootballPlay {
	
	// instance variables
	private boolean calledHeads;
	private boolean actualHeads;
	private boolean receiveFirst;
	
	// Constructs a coin toss play
	public JavaFootballCoinToss(FootballTeam awayTeam, FootballTeam homeTeam, boolean calledHeads, boolean actualHeads, boolean receiveFirst) {
		super(awayTeam, homeTeam);
		this.calledHeads = calledHeads;
		this.actualHeads = actualHeads;
		this.receiveFirst = receiveFirst;
	}
	
	// Returns the play as a String
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("COIN TOSS: ");
		sb.append(awayTeam.getTeamAbbr());
		sb.append(" called ");
		if (calledHeads) {
			sb.append("heads. ");
		}
		else {
			sb.append("tails. ");
		}
		sb.append("The coin was ");
		if (actualHeads) {
			sb.append("heads. ");
		}
		else {
			sb.append("tails. ");
		}
		if (calledHeads == actualHeads) {
			sb.append(awayTeam.getTeamAbbr());
		}
		else {
			sb.append(homeTeam.getTeamAbbr());
		}
		sb.append(" won the toss and chose to ");
		if (receiveFirst) {
			sb.append("receive");
		}
		else {
			sb.append("kickoff");
		}
		sb.append(" first.");
		return sb.toString();
	}
}
