/**
 * The conferences of college football
 * @author Zachary Desai (github.com/ZacharyDesai)
 */
public interface CFBConference extends FootballConference {
	
	// Returns the full name of the conference
	public abstract String getFullName();
	
	// Returns whether or not the conference has a championship game
	public abstract boolean hasChampionshipGame();
	
	// Returns whether or not the conference is a conference (not independents)
	public abstract boolean isConference();
}
