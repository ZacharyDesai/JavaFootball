// import statements
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * The conferences of the FCS
 * @author Zachary Desai (github.com/ZacharyDesai)
 */
public enum FCSConference implements CFBConference {
	BSKY ("Big Sky Conference"),
	BSOU ("Big South Conference"),
	CAA ("Colonial Athletic Association"),
	IND ("Independents"),
	IVY ("Ivy League"),
	MEAC ("Mid-Eastern Athletic Conference"),
	MVC ("Missouri Valley Conference"),
	NEC ("Northeast Conference"),
	OVC ("Ohio Valley Conference"),
	PATL ("Patriot League Conference"),
	PIOL ("Pioneer League"),
	SCON ("Southern Conference"),
	SLC ("Southland Conference"),
	SWAC ("Southwestern Athletic Conference");
	
	// instance variables
	private String fullName;
	
	// constructor
	private FCSConference(String fullName) {
		this.fullName = fullName;
	}
	
	// Returns the full name of the conference
	public String getFullName() {
		return fullName;
	}
	
	// Returns whether or not the conference has a championship game
	public boolean hasChampionshipGame() {
		if (this == SWAC) {
			return true;
		}
		else {
			return false;
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
			case BSKY:
				return "BSKY";
			case BSOU:
				return "BSOU";
			case CAA:
				return "CAA";
			case IND:
				return "IND";
			case IVY:
				return "IVY";
			case MEAC:
				return "MEAC";
			case MVC:
				return "MVC";
			case NEC:
				return "NEC";
			case OVC:
				return "OVC";
			case PATL:
				return "PATL";
			case PIOL:
				return "PIOL";
			case SCON:
				return "SCON";
			case SLC:
				return "SLC";
			case SWAC:
				return "SWAC";
		}
		return null;
	}
	
	// Returns whether or not the conference has divisions
	public boolean hasDivisions() {
		if (this == SWAC) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// Returns a list of the divisions in the conference
	public List<FootballDivision> getDivisions() {
		List<FootballDivision> divisions = new ArrayList<>();
		switch (this) {
			case BSKY:
				break;
			case BSOU:
				break;
			case CAA:
				break;
			case IND:
				break;
			case IVY:
				break;
			case MEAC:
				break;
			case MVC:
				break;
			case NEC:
				break;
			case OVC:
				break;
			case PATL:
				break;
			case PIOL:
				break;
			case SCON:
				break;
			case SLC:
				break;
			case SWAC:
				for (FootballDivision div : EnumSet.allOf(SWACDivision.class)) {
					divisions.add(div);
				}
				break;
		}
		return divisions;
	}
}