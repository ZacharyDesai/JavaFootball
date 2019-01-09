// import statements
import java.awt.Color;

/**
 * Used to create and manage FCS teams
 * @author Zachary Desai (github.com/ZacharyDesai)
 */
public class FCSTeam extends CFBTeam {
	
	// Constructs an FCS team
	public FCSTeam(String teamAbbr, String locationName, String mascotName, Color primaryColor, Color secondaryColor,
			String logoFilePath, FCSConference conference, FCSDivision division) {
		
		// Uses the constructor in the superclass for all parameters
		super(teamAbbr, locationName, mascotName, primaryColor, secondaryColor, logoFilePath, conference, division);
	}
}