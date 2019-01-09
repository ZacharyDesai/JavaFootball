// import statements
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * The conferences of the FBS
 * @author Zachary Desai (github.com/ZacharyDesai)
 */
public enum FBSConference implements CFBConference {
	AAC ("American Athletic Conference"),
	ACC ("Atlantic Coast Conference"),
	B12 ("Big 12 Conference"),
	B10 ("Big Ten Conference"),
	CUSA ("Conference USA"),
	IND ("Independents"),
	MAC ("Mid-American Conference"),
	MWC ("Mountain West Conference"),
	P12 ("Pac-12 Conference"),
	SEC ("Southeastern Conference"),
	SBC ("Sun Belt Conference");
	
	// instance variables
	private String fullName;
	
	// constructor
	private FBSConference(String fullName) {
		this.fullName = fullName;
	}
	
	// Returns the full name of the conference
	public String getFullName() {
		return fullName;
	}
	
	// Returns whether or not the conference has a championship game
	public boolean hasChampionshipGame() {
		if (this == IND) {
			return false;
		}
		else {
			return true;
		}
	}
	
	// Returns whether or not the conference is a conference (not independents)
	public boolean isConference() {
		if (this == IND) {
			return false;
		}
		else {
			return true;
		}
	}
	
	// Returns the conference as a String
	public String toString() {
		switch (this) {
			case AAC:
				return "AAC";
			case ACC:
				return "ACC";
			case B12:
				return "B12";
			case B10:
				return "B10";
			case CUSA:
				return "CUSA";
			case IND:
				return "IND";
			case MAC:
				return "MAC";
			case MWC:
				return "MWC";
			case P12:
				return "P12";
			case SEC:
				return "SEC";
			case SBC:
				return "SBC";
		}
		return null;
	}
	
	// Returns whether or not the conference has divisions
	public boolean hasDivisions() {
		if (this == B12 || this == IND) {
			return false;
		}
		else {
			return true;
		}
	}
	
	// Returns a list of the divisions in the conference
	public List<FootballDivision> getDivisions() {
		List<FootballDivision> divisions = new ArrayList<>();
		switch (this) {
			case AAC:
				for (FootballDivision div : EnumSet.allOf(AACDivision.class)) {
					divisions.add(div);
				}
				break;
			case ACC:
				for (FootballDivision div : EnumSet.allOf(ACCDivision.class)) {
					divisions.add(div);
				}
				break;
			case B12:
				break;
			case B10:
				for (FootballDivision div : EnumSet.allOf(B10Division.class)) {
					divisions.add(div);
				}
				break;
			case CUSA:
				for (FootballDivision div : EnumSet.allOf(CUSADivision.class)) {
					divisions.add(div);
				}
				break;
			case IND:
				break;
			case MAC:
				for (FootballDivision div : EnumSet.allOf(MACDivision.class)) {
					divisions.add(div);
				}
				break;
			case MWC:
				for (FootballDivision div : EnumSet.allOf(MWCDivision.class)) {
					divisions.add(div);
				}
				break;
			case P12:
				for (FootballDivision div : EnumSet.allOf(P12Division.class)) {
					divisions.add(div);
				}
				break;
			case SEC:
				for (FootballDivision div : EnumSet.allOf(SECDivision.class)) {
					divisions.add(div);
				}
				break;
			case SBC:
				for (FootballDivision div : EnumSet.allOf(SBCDivision.class)) {
					divisions.add(div);
				}
				break;
		}
		return divisions;
	}
}