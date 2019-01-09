// import statements
import java.awt.Color;

/**
 * Used to create and manage FBS teams
 * @author Zachary Desai (github.com/ZacharyDesai)
 */
public class FBSTeam extends CFBTeam {
	
	// Constructs an FBS team
	public FBSTeam(String teamAbbr, String locationName, String mascotName, Color primaryColor, Color secondaryColor,
			String logoFilePath, FBSConference conference, FBSDivision division) {
		
		// Uses the constructor in the superclass for all parameters
		super(teamAbbr, locationName, mascotName, primaryColor, secondaryColor, logoFilePath, conference, division);
	}
}
