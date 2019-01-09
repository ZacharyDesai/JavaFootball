/**
 * Used to create Java football cards for offense
 * @author Zachary Desai (github.com/ZacharyDesai)
 */
public class JavaFootballOffenseCard extends JavaFootballCard {
	
	// instance variables
	private int[] passDefenseOutcome;
	private int[] runDefenseOutcome;
	private int[] fumbleOutcome;
	private int[] interceptionOutcome;
	private int[] penaltyFlagOutcome;
	private int[] allOutBlitzOutcome;
	private int[] zoneBlitzOutcome;
	
	// Creates a Java football card for offense
	public JavaFootballOffenseCard(String str) {
		super(str);
		passDefenseOutcome = null;
		runDefenseOutcome = null;
		fumbleOutcome = null;
		interceptionOutcome = null;
		penaltyFlagOutcome = null;
		allOutBlitzOutcome = null;
		zoneBlitzOutcome = null;
	}
	
	// Sets the outcome for the given defensive card
	public void setOutcome(String str, int yards, int downs, int turnover) {
		if (str.equals("passDefense")) {
			setPassDefenseOutcome(yards, downs, turnover);
		}
		if (str.equals("runDefense")) {
			setRunDefenseOutcome(yards, downs, turnover);
		}
		if (str.equals("fumble")) {
			setFumbleOutcome(yards, downs, turnover);
		}
		if (str.equals("interception")) {
			setInterceptionOutcome(yards, downs, turnover);
		}
		if (str.equals("penaltyFlag")) {
			setPenaltyFlagOutcome(yards, downs, turnover);
		}
		if (str.equals("allOutBlitz")) {
			setAllOutBlitzOutcome(yards, downs, turnover);
		}
		if (str.equals("zoneBlitz")) {
			setZoneBlitzOutcome(yards, downs, turnover);
		}
	}
	
	// Gets the outcome for the given defensive card
	public int[] getOutcome(String str) {
		if (str.equals("passDefense")) {
			return getPassDefenseOutcome();
		}
		if (str.equals("runDefense")) {
			return getRunDefenseOutcome();
		}
		if (str.equals("fumble")) {
			return getFumbleOutcome();
		}
		if (str.equals("interception")) {
			return getInterceptionOutcome();
		}
		if (str.equals("penaltyFlag")) {
			return getPenaltyFlagOutcome();
		}
		if (str.equals("allOutBlitz")) {
			return getAllOutBlitzOutcome();
		}
		if (str.equals("zoneBlitz")) {
			return getZoneBlitzOutcome();
		}
		return null;
	}
	
	// Sets the outcome for the pass defense card
	private void setPassDefenseOutcome(int yards, int downs, int turnover) {
		passDefenseOutcome = new int[] {yards, downs, turnover};
	}
	
	// Sets the outcome for the run defense card
	private void setRunDefenseOutcome(int yards, int downs, int turnover) {
		runDefenseOutcome = new int[] {yards, downs, turnover};
	}
	
	// Sets the outcome for the fumble card
	private void setFumbleOutcome(int yards, int downs, int turnover) {
		fumbleOutcome = new int[] {yards, downs, turnover};
	}
	
	// Sets the outcome for the interception card
	private void setInterceptionOutcome(int yards, int downs, int turnover) {
		interceptionOutcome = new int[] {yards, downs, turnover};
	}
	
	// Sets the outcome for the penalty flag card
	private void setPenaltyFlagOutcome(int yards, int downs, int turnover) {
		penaltyFlagOutcome = new int[] {yards, downs, turnover};
	}
	
	// Sets the outcome for the all-out blitz card
	private void setAllOutBlitzOutcome(int yards, int downs, int turnover) {
		allOutBlitzOutcome = new int[] {yards, downs, turnover};
	}
	
	// Sets the outcome for the all-out blitz card
	private void setZoneBlitzOutcome(int yards, int downs, int turnover) {
		zoneBlitzOutcome = new int[] {yards, downs, turnover};
	}
	
	// Gets the outcome for the pass defense card
	private int[] getPassDefenseOutcome() {
		return passDefenseOutcome;
	}
	
	// Gets the outcome for the run defense card
	private int[] getRunDefenseOutcome() {
		return runDefenseOutcome;
	}
	
	// Gets the outcome for the fumble card
	private int[] getFumbleOutcome() {
		return fumbleOutcome;
	}
	
	// Gets the outcome for the interception card
	private int[] getInterceptionOutcome() {
		return interceptionOutcome;
	}
	
	// Gets the outcome for the penalty flag card
	private int[] getPenaltyFlagOutcome() {
		return penaltyFlagOutcome;
	}
	
	// Gets the outcome for the all-out blitz card
	private int[] getAllOutBlitzOutcome() {
		return allOutBlitzOutcome;
	}
	
	// Gets the outcome for the all-out blitz card
	private int[] getZoneBlitzOutcome() {
		return zoneBlitzOutcome;
	}
}
