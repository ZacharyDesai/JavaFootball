/**
 * Stores the result of a play of a Java football game for play-by-play
 * @author Zachary Desai (github.com/ZacharyDesai)
 */
public class JavaFootballPlayResult extends JavaFootballPlay {
	
	// instance variables
	private String result;
	
	// Constructs a play result play
	public JavaFootballPlayResult(FootballTeam awayTeam, FootballTeam homeTeam, String result) {
		super(awayTeam, homeTeam);
		this.result = result;
	}
	
	// Returns the play as a String
	public String toString() {
		return result;
	}
}