// import statements
import javax.swing.ImageIcon;

/**
 * Used to create Java football cards
 * @author Zachary Desai (github.com/ZacharyDesai)
 */
public abstract class JavaFootballCard {
	
	// instance variables
	private String name;
	private ImageIcon icon;
	
	// Creates a Java football card
	protected JavaFootballCard(String str) {
		name = str;
		icon = new ImageIcon("JavaFootballCards/" + str + ".png");
	}
	
	// Returns the name of the Java football card
	public String getName() {
		return name;
	}
	
	// Returns the icon of the Java football card
	public ImageIcon getIcon() {
		return icon;
	}
}
