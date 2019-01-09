// import statements
import java.awt.Color;

/**
 * Used to create and manage college football teams
 * @author Zachary Desai (github.com/ZacharyDesai)
 */
public abstract class CFBTeam extends FootballTeam {
	
	// Constructs an FBS team
	protected CFBTeam(String teamAbbr, String locationName, String mascotName, Color primaryColor, Color secondaryColor,
			String logoFilePath, CFBConference conference, CFBDivision division) {
		
		// Uses the constructor in the superclass for all parameters except the division
		super(teamAbbr, locationName, mascotName, primaryColor, secondaryColor, logoFilePath, conference);
		
		// Validates and assigns the division parameter
		if (conference.hasDivisions()) {
			if (division == null) {
				throw new IllegalArgumentException("param cannot be null");
			}
			else if (!conference.getDivisions().contains(division)) {
				throw new IllegalArgumentException("division not in conference");
			}
			else {
				this.division = division;
			}
		}
		else {
			this.division = null;
		}
	}
}
