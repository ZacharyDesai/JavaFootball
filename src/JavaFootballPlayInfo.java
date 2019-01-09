/**
 * Stores the current game info before a play of a Java football game for play-by-play
 * @author Zachary Desai (github.com/ZacharyDesai)
 */
public class JavaFootballPlayInfo extends JavaFootballPlay {
	
	// instance variables
	private int awayScore;
	private int homeScore;
	private int quarterNum;
	private String time;
	private String downs;
	private String position;
	
	// Constructs a play info play
	public JavaFootballPlayInfo(FootballTeam awayTeam, FootballTeam homeTeam, int awayScore, int homeScore,
			int quarterNum, String time, String downs, String position) {
		super(awayTeam, homeTeam);
		this.awayScore = awayScore;
		this.homeScore = homeScore;
		this.quarterNum = quarterNum;
		this.time = time;
		this.downs = downs;
		this.position = position;
	}
	
	// Returns the play as a String
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(awayTeam.getTeamAbbr());
		sb.append(" ");
		sb.append("" + awayScore);
		sb.append("-");
		sb.append("" + homeScore);
		sb.append(" ");
		sb.append(homeTeam.getTeamAbbr());
		if (awayScore < 10) {
			sb.append(" ");
		}
		if (homeScore < 10) {
			sb.append(" ");
		}
		if (quarterNum < 5) {
			sb.append("     Quarter ");
			sb.append(quarterNum);
		}
		else {
			sb.append("     Overtime ");
			sb.append(quarterNum - 4);
		}
		sb.append(" ");
		sb.append(time);
		if (time.length() < 5) {
			sb.append(" ");
		}
		sb.append("     ");
		sb.append(downs);
		if (downs.length() < 8) {
			sb.append(" ");
		}
		if (downs.length() < 4) {
			sb.append("    ");
		}
		sb.append("     ");
		sb.append(position);
		return sb.toString();
	}
}
