import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * The conferences of the NFL
 * @author Zachary Desai (github.com/ZacharyDesai)
 */
public enum NFLConference implements FootballConference {
	AFC,
	NFC;
	
	// Returns the conference as a String
	public String toString() {
		switch (this) {
			case AFC:
				return "AFC";
			case NFC:
				return "NFC";
		}
		return null;
	}
	
	// Returns whether or not the conference has divisions
	public boolean hasDivisions() {
		return true;
	}
	
	// Returns a list of the divisions in the conference
	public List<FootballDivision> getDivisions() {
		List<FootballDivision> divisions = new ArrayList<>();
		for (FootballDivision div : EnumSet.allOf(NFLDivision.class)) {
			divisions.add(div);
		}
		return divisions;
	}
}