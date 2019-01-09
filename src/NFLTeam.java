// import statements
import java.awt.Color;

/**
 * Used to create and manage NFL teams
 * @author Zachary Desai (github.com/ZacharyDesai)
 */
public class NFLTeam extends FootballTeam {
	
	// instance variables
	private int passOffRank;
	private int rushOffRank;
	private int passDefRank;
	private int rushDefRank;
	
	// Constructs an NFL team
	public NFLTeam(String teamAbbr, String locationName, String mascotName, Color primaryColor, Color secondaryColor,
			String logoFilePath, NFLConference conference, NFLDivision division) {
		
		// Uses the constructor in the superclass for all parameters except the division
		super(teamAbbr, locationName, mascotName, primaryColor, secondaryColor, logoFilePath, conference);
		
		// Validates and assigns the division parameter
		if (division == null) {
			throw new IllegalArgumentException("param cannot be null");
		}
		this.division = division;
		
		passOffRank = 0;
		rushOffRank = 0;
		passDefRank = 0;
		rushDefRank = 0;
	}
	
	// Sets the team's pass offense rank
	public void setPassOffRank(int rank) {
		passOffRank = rank;
	}
	
	// Sets the team's rush offense rank
	public void setRushOffRank(int rank) {
		rushOffRank = rank;
	}
	
	// Sets the team's pass defense rank
	public void setPassDefRank(int rank) {
		passDefRank = rank;
	}
	
	// Sets the team's rush defense rank
	public void setRushDefRank(int rank) {
		rushDefRank = rank;
	}
	
	// Gets the team's pass offense rank
	public int getPassOffRank() {
		return passOffRank;
	}
	
	// Gets the team's rush offense rank
	public int getRushOffRank() {
		return rushOffRank;
	}
	
	// Gets the team's pass defense rank
	public int getPassDefRank() {
		return passDefRank;
	}
	
	// Gets the team's rush defense rank
	public int getRushDefRank() {
		return rushDefRank;
	}
}
