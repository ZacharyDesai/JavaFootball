// import statements
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 * Used to create and manage football teams
 * @author Zachary Desai (github.com/ZacharyDesai)
 */
public abstract class FootballTeam implements Comparable<FootballTeam> {
	
	// instance variables
	private String teamName;
	private String teamAbbr;
	private String locationName;
	private String mascotName;
	private Color primaryColor;
	private Color secondaryColor;
	private ImageIcon logo;
	private FootballConference conference;
	protected FootballDivision division;
	
	// Creates a football team
	protected FootballTeam(String teamAbbr, String locationName, String mascotName, Color primaryColor, Color secondaryColor,
			String logoFilePath, FootballConference conference) {
		
		// Validates and assigns all the parameters
		if (teamAbbr == null || locationName == null || mascotName == null || primaryColor == null || secondaryColor == null ||
				logoFilePath == null || conference == null) {
			throw new IllegalArgumentException("param cannot be null");
		}
		this.teamName = locationName + " " + mascotName;
		this.teamAbbr = teamAbbr;
		this.locationName = locationName;
		this.mascotName = mascotName;
		this.primaryColor = primaryColor;
		this.secondaryColor = secondaryColor;
		this.logo = new ImageIcon(logoFilePath);
		this.conference = conference;
	}
	
	// Returns the full name of the team to represent it as a String
	public String toString() {
		return teamName;
	}
	
	// Compares the team name to another team
	public int compareTo(FootballTeam otherTeam) {
		return getTeamName().compareTo(otherTeam.getTeamName());
	}
	
	// Returns the full name of the team
	public String getTeamName() {
		return teamName;
	}
	
	// Returns the abbreviation of the team's name
	public String getTeamAbbr() {
		return teamAbbr;
	}
	
	// Returns the location part of the team's name
	public String getLocationName() {
		return locationName;
	}
	
	// Returns the mascot part of the team's name
	public String getMascotName() {
		return mascotName;
	}
	
	// Returns the primary color for the team
	public Color getPrimaryColor() {
		return primaryColor;
	}
	
	// Returns the secondary color for the team
	public Color getSecondaryColor() {
		return secondaryColor;
	}
	
	// Returns the logo for the team
	public ImageIcon getLogo() {
		return logo;
	}
	
	// Returns the conference the team is in
	public FootballConference getConference() {
		return conference;
	}
	
	// Returns the division the team is in
	public FootballDivision getDivision() {
		return division;
	}
}
