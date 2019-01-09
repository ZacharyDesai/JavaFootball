import java.util.List;

/**
 * The conferences of football
 * @author Zachary Desai (github.com/ZacharyDesai)
 */
public interface FootballConference {
	
	// Returns the conference as a String
	public abstract String toString();
	
	// Returns whether or not the conference has divisions
	public abstract boolean hasDivisions();
	
	// Returns a list of the divisions in the conference
	public abstract List<FootballDivision> getDivisions();
}