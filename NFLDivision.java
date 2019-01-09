/**
 * The divisions of the NFL
 * @author Zachary Desai (github.com/ZacharyDesai)
 */
public enum NFLDivision implements FootballDivision {
	East,
	North,
	South,
	West;
	
	// Returns the conference as a String
	public String toString() {
		switch (this) {
			case East:
				return "East";
			case North:
				return "North";
			case South:
				return "South";
			case West:
				return "West";
		}
		return null;
	}
}