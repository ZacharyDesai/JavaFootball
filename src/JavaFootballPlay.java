/**
 * Stores the result of a play of a Java football game for play-by-play
 * @author Zachary Desai (github.com/ZacharyDesai)
 */
public abstract class JavaFootballPlay {
	
	// instance variables
	protected FootballTeam awayTeam;
	protected FootballTeam homeTeam;
	
	// Constructs a play object
	protected JavaFootballPlay(FootballTeam awayTeam, FootballTeam homeTeam) {
		this.awayTeam = awayTeam;
		this.homeTeam = homeTeam;
	}
}
