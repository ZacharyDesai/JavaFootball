// import statements
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JWindow;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Plays or simulates a football game
 * @author Zachary Desai (github.com/ZacharyDesai)
 */
public class JavaFootballGame {
	
	// instance variables
	private FootballTeam awayTeam;
	private FootballTeam homeTeam;
	private boolean awayHuman;
	private boolean homeHuman;
	private boolean neutral;
	private int quarterNum;
	private JLabel quarter;
	private String timeLeft;
	private JLabel time;
	private int down;
	private int yardsToGo;
	private JLabel downs;
	private boolean homeSideOfField;
	private int yardLine;
	private JLabel position;
	private int awayScore;
	private JLabel awayPoints;
	private int homeScore;
	private JLabel homePoints;
	private JLabel awayPossession;
	private JLabel homePossession;
	private JPanel field;
	private JButton[] yardMarkers;
	private JLabel football;
	private boolean awayReceiveFirst;
	private boolean kickoffStart;
	private JButton awayTimeout1;
	private JButton awayTimeout2;
	private JButton awayTimeout3;
	private JButton homeTimeout1;
	private JButton homeTimeout2;
	private JButton homeTimeout3;
	private JButton awayKickoff;
	private JButton awayOnsideKick;
	private JButton awayPunt;
	private JButton awayFieldGoal;
	private JButton awayExtraPoint;
	private JButton awayTwoPoint;
	private JButton homeKickoff;
	private JButton homeOnsideKick;
	private JButton homePunt;
	private JButton homeFieldGoal;
	private JButton homeExtraPoint;
	private JButton homeTwoPoint;
	private JTextArea lastPlay;
	private JButton awayPlay1;
	private JButton awayPlay2;
	private JButton awayPlay3;
	private JButton awayPlay4;
	private JButton awayPlay5;
	private JButton homePlay1;
	private JButton homePlay2;
	private JButton homePlay3;
	private JButton homePlay4;
	private JButton homePlay5;
	private Map<String, JavaFootballOffenseCard> offenseCards;
	private Map<String, JavaFootballDefenseCard> defenseCards;
	private JavaFootballOffenseCard[] awayOffenseCards;
	private JavaFootballOffenseCard[] homeOffenseCards;
	private JavaFootballDefenseCard[] awayDefenseCards;
	private JavaFootballDefenseCard[] homeDefenseCards;
	private boolean timeoutUsed;
	private boolean twoMinuteWarningUsed;
	private JButton awaySim;
	private JButton homeSim;
	private List<JavaFootballPlay> playByPlayList;
	private JavaFootball mainMenu;
	private double[] awayPassOff;
	private double[] awayRushOff;
	private double[] awayRushDrawOff;
	private double[] awayPassDef;
	private double[] awayRushDef;
	private double[] homePassOff;
	private double[] homeRushOff;
	private double[] homeRushDrawOff;
	private double[] homePassDef;
	private double[] homeRushDef;
	private boolean overtimeNow;
	private boolean regularSeasonNFL;
	private boolean overtimeAwayPoss;
	private boolean overtimeHomePoss;
	private int numCollegeOTPoss;
	
	// Constructs a new football game
	public JavaFootballGame(FootballTeam selectedAwayTeam, FootballTeam selectedHomeTeam,
			boolean playerVsComputer, boolean computerVsPlayer, boolean playerVsPlayer,
			boolean computerVsComputer, boolean neutralField, JavaFootball mainMenu, boolean regularSeasonNFL) throws FileNotFoundException {
		awayTeam = selectedAwayTeam;
		homeTeam = selectedHomeTeam;
		if (playerVsComputer) {
			awayHuman = true;
			homeHuman = false;
		}
		if (computerVsPlayer) {
			awayHuman = false;
			homeHuman = true;
		}
		if (playerVsPlayer) {
			awayHuman = true;
			homeHuman = true;
		}
		if (computerVsComputer) {
			awayHuman = false;
			homeHuman = false;
		}
		neutral = neutralField;
		quarterNum = 1;
		quarter = null;
		timeLeft = "15:00";
		time = null;
		down = 1;
		yardsToGo = 10;
		downs = null;
		homeSideOfField = false;
		yardLine = 35;
		position = null;
		awayScore = 0;
		awayPoints = null;
		homeScore = 0;
		homePoints = null;
		awayPossession = null;
		homePossession = null;
		field = null;
		yardMarkers = null;
		football = null;
		awayReceiveFirst = false;
		kickoffStart = true;
		awayTimeout1 = null;
		awayTimeout2 = null;
		awayTimeout3 = null;
		homeTimeout1 = null;
		homeTimeout2 = null;
		homeTimeout3 = null;
		awayKickoff = null;
		awayOnsideKick = null;
		awayPunt = null;
		awayFieldGoal = null;
		awayExtraPoint = null;
		awayTwoPoint = null;
		homeKickoff = null;
		homeOnsideKick = null;
		homePunt = null;
		homeFieldGoal = null;
		homeExtraPoint = null;
		homeTwoPoint = null;
		lastPlay = null;
		awayPlay1 = null;
		awayPlay2 = null;
		awayPlay3 = null;
		awayPlay4 = null;
		awayPlay5 = null;
		homePlay1 = null;
		homePlay2 = null;
		homePlay3 = null;
		homePlay4 = null;
		homePlay5 = null;
		offenseCards = new HashMap<>();
		defenseCards = new HashMap<>();
		loadCards();
		awayOffenseCards = new JavaFootballOffenseCard[5];
		homeOffenseCards = new JavaFootballOffenseCard[5];
		awayDefenseCards = new JavaFootballDefenseCard[5];
		homeDefenseCards = new JavaFootballDefenseCard[5];
		timeoutUsed = false;
		twoMinuteWarningUsed = false;
		awaySim = null;
		homeSim = null;
		playByPlayList = new ArrayList<>();
		this.mainMenu = mainMenu;
		awayPassOff = new double[] {.35, .30, .20, .10, .05};
		awayRushOff = new double[] {.35, .30, .20, .10, .05};
		awayRushDrawOff = new double[] {.45, .40, .10, .05};
		awayPassDef = new double[] {.80, .15, .05};
		awayRushDef = new double[] {.80, .15, .05};
		if (awayTeam instanceof NFLTeam) {
			updateAwayRanks();
		}
		homePassOff = new double[] {.35, .30, .20, .10, .05};
		homeRushOff = new double[] {.35, .30, .20, .10, .05};
		homeRushDrawOff = new double[] {.45, .40, .10, .05};
		homePassDef = new double[] {.80, .15, .05};
		homeRushDef = new double[] {.80, .15, .05};
		if (homeTeam instanceof NFLTeam) {
			updateHomeRanks();
		}
		overtimeNow = false;
		this.regularSeasonNFL = regularSeasonNFL;
		overtimeAwayPoss = false;
		overtimeHomePoss = false;
		numCollegeOTPoss = 0;
	}
	
	// Loads the Java football cards from the text files
	private void loadCards() throws FileNotFoundException {
		loadOffenseCards();
		loadDefenseCards();
	}
	
	// Loads the Java football offense cards from the text files
	private void loadOffenseCards() throws FileNotFoundException {
		File file = new File("JavaFootballOffenseCards.txt");
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine()) {
			String str = scan.next();
			JavaFootballOffenseCard card = new JavaFootballOffenseCard(str);
			card.setOutcome("passDefense", scan.nextInt(), scan.nextInt(), scan.nextInt());
			card.setOutcome("runDefense", scan.nextInt(), scan.nextInt(), scan.nextInt());
			card.setOutcome("fumble", scan.nextInt(), scan.nextInt(), scan.nextInt());
			card.setOutcome("interception", scan.nextInt(), scan.nextInt(), scan.nextInt());
			card.setOutcome("penaltyFlag", scan.nextInt(), scan.nextInt(), scan.nextInt());
			card.setOutcome("allOutBlitz", scan.nextInt(), scan.nextInt(), scan.nextInt());
			card.setOutcome("zoneBlitz", scan.nextInt(), scan.nextInt(), scan.nextInt());
			offenseCards.put(str, card);
		}
		scan.close();
	}
	
	// Loads the Java football defense cards from the text files
	private void loadDefenseCards() throws FileNotFoundException {
		File file = new File("JavaFootballDefenseCards.txt");
		Scanner scan = new Scanner(file);
		while (scan.hasNext()) {
			String str = scan.next();
			JavaFootballDefenseCard card = new JavaFootballDefenseCard(str);
			defenseCards.put(str, card);
		}
		scan.close();
	}
	
	// Plays or simulates a game
	public void runGame(JFrame frame) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		
        quarter = new JLabel("Quarter " + quarterNum, JLabel.CENTER);
        quarter.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 40));
        quarter.setForeground(Color.BLACK);
        quarter.setBounds(frame.getWidth() * 2 / 5, 0, frame.getWidth() / 5, 40);
        panel.add(quarter);
        
        time = new JLabel(timeLeft, JLabel.CENTER);
        time.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        time.setForeground(Color.BLACK);
        time.setBounds(frame.getWidth() * 9 / 20, 40, frame.getWidth() / 10, 50);
        panel.add(time);
        
        downs = new JLabel("COIN", JLabel.CENTER);
        downs.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
        downs.setForeground(Color.BLACK);
        downs.setBounds(frame.getWidth() * 2 / 5, 95, frame.getWidth() / 10, 30);
        panel.add(downs);
        
        position = new JLabel("TOSS", JLabel.CENTER);
        position.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
        position.setForeground(Color.BLACK);
        position.setBounds(frame.getWidth() / 2, 95, frame.getWidth() / 10, 30);
        panel.add(position);
        
        awayPoints = new JLabel("" + awayScore, JLabel.CENTER);
        awayPoints.setFont(new Font(Font.MONOSPACED, Font.BOLD, 120));
        awayPoints.setBackground(awayTeam.getPrimaryColor());
        awayPoints.setForeground(awayTeam.getSecondaryColor());
        awayPoints.setBounds(frame.getWidth() * 3 / 10, 0, frame.getWidth() / 10, 120);
        awayPoints.setOpaque(true);
        panel.add(awayPoints);
        
        homePoints = new JLabel("" + homeScore, JLabel.CENTER);
        homePoints.setFont(new Font(Font.MONOSPACED, Font.BOLD, 120));
        homePoints.setBackground(homeTeam.getPrimaryColor());
        homePoints.setForeground(homeTeam.getSecondaryColor());
        homePoints.setBounds(frame.getWidth() * 3 / 5, 0, frame.getWidth() / 10, 120);
        homePoints.setOpaque(true);
        panel.add(homePoints);
        
        awayPossession = new JLabel();
        awayPossession.setIcon(new ImageIcon("football.png"));
        awayPossession.setBounds(frame.getWidth() / 3 - 49, 170, 98, 60);
        awayPossession.setVisible(false);
        panel.add(awayPossession);
        
        homePossession = new JLabel();
        homePossession.setIcon(new ImageIcon("football.png"));
        homePossession.setBounds(frame.getWidth() * 2 / 3 - 49, 170, 98, 60);
        homePossession.setVisible(false);
        panel.add(homePossession);
        
        JLabel awayTeamLogo = new JLabel();
        awayTeamLogo.setIcon(new ImageIcon(awayTeam.getLogo().getImage()
        		.getScaledInstance(180, 120, Image.SCALE_DEFAULT)));
        awayTeamLogo.setBounds(20, 0, 180, 120);
        panel.add(awayTeamLogo);
        
        JLabel homeTeamLogo = new JLabel();
        homeTeamLogo.setIcon(new ImageIcon(homeTeam.getLogo().getImage()
        		.getScaledInstance(180, 120, Image.SCALE_DEFAULT)));
        homeTeamLogo.setBounds(frame.getWidth() - 200, 0, 180, 120);
        panel.add(homeTeamLogo);
        
        JLabel awayTeamName = new JLabel(awayTeam.getTeamAbbr(), JLabel.CENTER);
        awayTeamName.setFont(new Font(Font.MONOSPACED, Font.BOLD, 120));
        awayTeamName.setBackground(awayTeam.getPrimaryColor());
        awayTeamName.setForeground(awayTeam.getSecondaryColor());
        awayTeamName.setBounds(200, 0, frame.getWidth() * 3 / 10 - 200, 120);
        awayTeamName.setOpaque(true);
        panel.add(awayTeamName);
        
        JLabel homeTeamName = new JLabel(homeTeam.getTeamAbbr(), JLabel.CENTER);
        homeTeamName.setFont(new Font(Font.MONOSPACED, Font.BOLD, 120));
        homeTeamName.setBackground(homeTeam.getPrimaryColor());
        homeTeamName.setForeground(homeTeam.getSecondaryColor());
        homeTeamName.setBounds(frame.getWidth() * 7 / 10 - 1, 0, frame.getWidth() * 3 / 10 - 200, 120);
        homeTeamName.setOpaque(true);
        panel.add(homeTeamName);
        
        JLabel fullAwayTeamName = new JLabel(awayTeam.getTeamName(), JLabel.CENTER);
        fullAwayTeamName.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
        fullAwayTeamName.setBackground(awayTeam.getPrimaryColor());
        fullAwayTeamName.setForeground(awayTeam.getSecondaryColor());
        fullAwayTeamName.setBounds(0, 590, frame.getWidth() / 2, frame.getHeight() * 2 / 3 - 610);
        fullAwayTeamName.setOpaque(true);
        panel.add(fullAwayTeamName);
        
        JLabel fullHomeTeamName = new JLabel(homeTeam.getTeamName(), JLabel.CENTER);
        fullHomeTeamName.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
        fullHomeTeamName.setBackground(homeTeam.getPrimaryColor());
        fullHomeTeamName.setForeground(homeTeam.getSecondaryColor());
        fullHomeTeamName.setBounds(frame.getWidth() / 2, 590, frame.getWidth() / 2, frame.getHeight() * 2 / 3 - 610);
        fullHomeTeamName.setOpaque(true);
        panel.add(fullHomeTeamName);
        
        awayTimeout1 = new JButton("Timeout");
        awayTimeout1.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 40));
        awayTimeout1.setBackground(awayTeam.getPrimaryColor());
        awayTimeout1.setForeground(awayTeam.getSecondaryColor());
        awayTimeout1.setBounds(0, 120, frame.getWidth() * 2 / 15, 50);
        awayTimeout1.setEnabled(false);
        if (awayHuman) {
        	awayTimeout1.setVisible(true);
        }
        else {
        	awayTimeout1.setVisible(false);
        }
        awayTimeout1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		timeoutUsed = true;
        		awayTimeout1.setEnabled(false);
        		int timeoutsUsed = 1;
        		if (!awayTimeout2.isEnabled()) {
        			timeoutsUsed++;
        		}
        		if (!awayTimeout3.isEnabled()) {
        			timeoutsUsed++;
        		}
        		lastPlay.setText("Timeout #" + timeoutsUsed + " " + awayTeam.getTeamAbbr() + ".");
        	}
        });
        panel.add(awayTimeout1);
        
        awayTimeout2 = new JButton("Timeout");
        awayTimeout2.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 40));
        awayTimeout2.setBackground(awayTeam.getPrimaryColor());
        awayTimeout2.setForeground(awayTeam.getSecondaryColor());
        awayTimeout2.setBounds(frame.getWidth() * 2 / 15, 120, frame.getWidth() * 2 / 15, 50);
        awayTimeout2.setEnabled(false);
        if (awayHuman) {
        	awayTimeout2.setVisible(true);
        }
        else {
        	awayTimeout2.setVisible(false);
        }
        awayTimeout2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		timeoutUsed = true;
        		awayTimeout2.setEnabled(false);
        		int timeoutsUsed = 1;
        		if (!awayTimeout1.isEnabled()) {
        			timeoutsUsed++;
        		}
        		if (!awayTimeout3.isEnabled()) {
        			timeoutsUsed++;
        		}
        		lastPlay.setText("Timeout #" + timeoutsUsed + " " + awayTeam.getTeamAbbr() + ".");
        	}
        });
        panel.add(awayTimeout2);
        
        awayTimeout3 = new JButton("Timeout");
        awayTimeout3.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 40));
        awayTimeout3.setBackground(awayTeam.getPrimaryColor());
        awayTimeout3.setForeground(awayTeam.getSecondaryColor());
        awayTimeout3.setBounds(frame.getWidth() * 4 / 15, 120, frame.getWidth() * 2 / 15, 50);
        awayTimeout3.setEnabled(false);
        if (awayHuman) {
        	awayTimeout3.setVisible(true);
        }
        else {
        	awayTimeout3.setVisible(false);
        }
        awayTimeout3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		timeoutUsed = true;
        		awayTimeout3.setEnabled(false);
        		int timeoutsUsed = 1;
        		if (!awayTimeout1.isEnabled()) {
        			timeoutsUsed++;
        		}
        		if (!awayTimeout2.isEnabled()) {
        			timeoutsUsed++;
        		}
        		lastPlay.setText("Timeout #" + timeoutsUsed + " " + awayTeam.getTeamAbbr() + ".");
        	}
        });
        panel.add(awayTimeout3);
        
        homeTimeout1 = new JButton("Timeout");
        homeTimeout1.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 40));
        homeTimeout1.setBackground(homeTeam.getPrimaryColor());
        homeTimeout1.setForeground(homeTeam.getSecondaryColor());
        homeTimeout1.setBounds(frame.getWidth() * 9 / 15, 120, frame.getWidth() * 2 / 15, 50);
        homeTimeout1.setEnabled(false);
        if (homeHuman) {
        	homeTimeout1.setVisible(true);
        }
        else {
        	homeTimeout1.setVisible(false);
        }
        homeTimeout1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		timeoutUsed = true;
        		homeTimeout1.setEnabled(false);
        		int timeoutsUsed = 1;
        		if (!homeTimeout2.isEnabled()) {
        			timeoutsUsed++;
        		}
        		if (!homeTimeout3.isEnabled()) {
        			timeoutsUsed++;
        		}
        		lastPlay.setText("Timeout #" + timeoutsUsed + " " + homeTeam.getTeamAbbr() + ".");
        	}
        });
        panel.add(homeTimeout1);
        
        homeTimeout2 = new JButton("Timeout");
        homeTimeout2.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 40));
        homeTimeout2.setBackground(homeTeam.getPrimaryColor());
        homeTimeout2.setForeground(homeTeam.getSecondaryColor());
        homeTimeout2.setBounds(frame.getWidth() * 11 / 15 - 1, 120, frame.getWidth() * 2 / 15 + 1, 50);
        homeTimeout2.setEnabled(false);
        if (homeHuman) {
        	homeTimeout2.setVisible(true);
        }
        else {
        	homeTimeout2.setVisible(false);
        }
        homeTimeout2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		timeoutUsed = true;
        		homeTimeout2.setEnabled(false);
        		int timeoutsUsed = 1;
        		if (!homeTimeout1.isEnabled()) {
        			timeoutsUsed++;
        		}
        		if (!homeTimeout3.isEnabled()) {
        			timeoutsUsed++;
        		}
        		lastPlay.setText("Timeout #" + timeoutsUsed + " " + homeTeam.getTeamAbbr() + ".");
        	}
        });
        panel.add(homeTimeout2);
        
        homeTimeout3 = new JButton("Timeout");
        homeTimeout3.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 40));
        homeTimeout3.setBackground(homeTeam.getPrimaryColor());
        homeTimeout3.setForeground(homeTeam.getSecondaryColor());
        homeTimeout3.setBounds(frame.getWidth() * 13 / 15, 120, frame.getWidth() * 2 / 15, 50);
        homeTimeout3.setEnabled(false);
        if (homeHuman) {
        	homeTimeout3.setVisible(true);
        }
        else {
        	homeTimeout3.setVisible(false);
        }
        homeTimeout3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		timeoutUsed = true;
        		homeTimeout3.setEnabled(false);
        		int timeoutsUsed = 1;
        		if (!homeTimeout1.isEnabled()) {
        			timeoutsUsed++;
        		}
        		if (!homeTimeout2.isEnabled()) {
        			timeoutsUsed++;
        		}
        		lastPlay.setText("Timeout #" + timeoutsUsed + " " + homeTeam.getTeamAbbr() + ".");
        	}
        });
        panel.add(homeTimeout3);
        
        awayKickoff = new JButton("Kickoff");
        awayKickoff.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
        awayKickoff.setBackground(awayTeam.getPrimaryColor());
        awayKickoff.setForeground(awayTeam.getSecondaryColor());
        awayKickoff.setBounds(frame.getWidth() * 4 / 15, 230, frame.getWidth() * 2 / 15, 50);
        awayKickoff.setEnabled(false);
        awayKickoff.setVisible(false);
        awayKickoff.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		down = 1;
				yardsToGo = 10;
				downs.setText("1st & 10");
				homeSideOfField = true;
				
        		double i = Math.random();
        		
        		if (i < 0.99) {
    				awayPossession.setVisible(false);
    				homePossession.setVisible(true);
    				
        			double j = Math.random();
        			if (j < 0.05) {
        				yardLine = 5;
        				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(awayTeam.getTeamAbbr() + " kickoff to the " + yardLine + ".");
        				play(frame, panel);
        			}
        			else if (j < 0.1) {
        				yardLine = 10;
        				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(awayTeam.getTeamAbbr() + " kickoff to the " + yardLine + ".");
        				play(frame, panel);
        			}
        			else if (j < 0.15) {
        				yardLine = 15;
        				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(awayTeam.getTeamAbbr() + " kickoff to the " + yardLine + ".");
        				play(frame, panel);
        			}
        			else if (j < 0.2) {
        				yardLine = 20;
        				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(awayTeam.getTeamAbbr() + " kickoff to the " + yardLine + ".");
        				play(frame, panel);
        			}
        			else if (j < 0.7) {
        				yardLine = 25;
        				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(awayTeam.getTeamAbbr() + " kickoff to the " + yardLine + ".");
        				play(frame, panel);
        			}
        			else if (j < 0.75) {
        				yardLine = 30;
        				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(awayTeam.getTeamAbbr() + " kickoff to the " + yardLine + ".");
        				play(frame, panel);
        			}
        			else if (j < 0.8) {
        				yardLine = 35;
        				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(awayTeam.getTeamAbbr() + " kickoff to the " + yardLine + ".");
        				play(frame, panel);
        			}
        			else if (j < 0.85) {
        				yardLine = 40;
        				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(awayTeam.getTeamAbbr() + " kickoff to the " + yardLine + ".");
        				play(frame, panel);
        			}
        			else if (j < 0.9) {
        				yardLine = 45;
        				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(awayTeam.getTeamAbbr() + " kickoff to the " + yardLine + ".");
        				play(frame, panel);
        			}
        			else if (j < 0.95) {
        				yardLine = 50;
        				position.setText("" + yardLine);
        				lastPlay.setText(awayTeam.getTeamAbbr() + " kickoff to the " + yardLine + ".");
        				play(frame, panel);
        			}
        			else {
        				lastPlay.setText(awayTeam.getTeamAbbr() + " kickoff returned for a TOUCHDOWN by " + homeTeam.getTeamAbbr() + "!");
        				touchdown(false, frame, panel);
        			}
        		}
        		else {
    				awayPossession.setVisible(true);
    				homePossession.setVisible(false);
    				
    				if (overtimeNow) {
    					overtimeHomePoss = true;
    				}
    				
        			double j = Math.random();
        			if (j < 0.1) {
        				yardLine = 10;
        				downs.setText("1st & GL");
        				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(awayTeam.getTeamAbbr() + " kickoff FUMBLED and RECOVERED by " + 
        						awayTeam.getTeamAbbr() + " at the " + yardLine + "!");
        				play(frame, panel);
        			}
        			else if (j < 0.2) {
        				yardLine = 15;
        				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(awayTeam.getTeamAbbr() + " kickoff FUMBLED and RECOVERED by " + 
        						awayTeam.getTeamAbbr() + " at the " + yardLine + "!");
        				play(frame, panel);
        			}
        			else if (j < 0.4) {
        				yardLine = 20;
        				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(awayTeam.getTeamAbbr() + " kickoff FUMBLED and RECOVERED by " + 
        						awayTeam.getTeamAbbr() + " at the " + yardLine + "!");
        				play(frame, panel);
        			}
        			else if (j < 0.65) {
        				yardLine = 25;
        				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(awayTeam.getTeamAbbr() + " kickoff FUMBLED and RECOVERED by " + 
        						awayTeam.getTeamAbbr() + " at the " + yardLine + "!");
        				play(frame, panel);
        			}
        			else if (j < 0.85) {
        				yardLine = 30;
        				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(awayTeam.getTeamAbbr() + " kickoff FUMBLED and RECOVERED by " + 
        						awayTeam.getTeamAbbr() + " at the " + yardLine + "!");
        				play(frame, panel);
        			}
        			else if (j < 0.95) {
        				yardLine = 35;
        				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(awayTeam.getTeamAbbr() + " kickoff FUMBLED and RECOVERED by " + 
        						awayTeam.getTeamAbbr() + " at the " + yardLine + "!");
        				play(frame, panel);
        			}
        			else {
        				lastPlay.setText(awayTeam.getTeamAbbr() + " kickoff FUMBLED and RECOVERED by " + 
        						awayTeam.getTeamAbbr() + " and returned for a TOUCHDOWN!");
        				touchdown(true, frame, panel);
        			}
        		}
            }
        });
        panel.add(awayKickoff);
        
        awayOnsideKick = new JButton("Onside Kick");
        awayOnsideKick.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
        awayOnsideKick.setBackground(awayTeam.getPrimaryColor());
        awayOnsideKick.setForeground(awayTeam.getSecondaryColor());
        awayOnsideKick.setBounds(frame.getWidth() * 4 / 15, 280, frame.getWidth() * 2 / 15, 50);
        awayOnsideKick.setEnabled(false);
        awayOnsideKick.setVisible(false);
        awayOnsideKick.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		down = 1;
				yardsToGo = 10;
				downs.setText("1st & 10");
				homeSideOfField = false;
				
        		double i = Math.random();
        		if (i < 0.8) {
    				awayPossession.setVisible(false);
    				homePossession.setVisible(true);
    				
        			double j = Math.random();
        			if (j < 0.15) {
        				yardLine = 35;
        				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(awayTeam.getTeamAbbr() + " onside kick recovered by " +
        						homeTeam.getTeamAbbr() + " at the " + yardLine + "!");
        			}
        			else if (j < 0.25) {
        				yardLine = 40;
        				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(awayTeam.getTeamAbbr() + " onside kick recovered by " +
        						homeTeam.getTeamAbbr() + " at the " + yardLine + "!");
        			}
        			else if (j < 0.75) {
        				yardLine = 45;
        				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(awayTeam.getTeamAbbr() + " onside kick recovered by " +
        						homeTeam.getTeamAbbr() + " at the " + yardLine + "!");
        			}
        			else {
        				yardLine = 50;
        				position.setText("" + yardLine);
        				lastPlay.setText(awayTeam.getTeamAbbr() + " onside kick recovered by " +
        						homeTeam.getTeamAbbr() + " at the " + yardLine + "!");
        			}
        		}
        		else {
        			awayPossession.setVisible(true);
    				homePossession.setVisible(false);
    				
    				if (overtimeNow) {
    					overtimeHomePoss = true;
    				}
    				
        			double j = Math.random();
        			if (j < 0.75) {
        				yardLine = 45;
        				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(awayTeam.getTeamAbbr() + " onside kick recovered by " +
        						awayTeam.getTeamAbbr() + " at the " + yardLine + "!");
        			}
        			else {
        				yardLine = 50;
        				position.setText("" + yardLine);
        				lastPlay.setText(awayTeam.getTeamAbbr() + " onside kick recovered by " +
        						awayTeam.getTeamAbbr() + " at the " + yardLine + "!");
        			}
        		}
        		play(frame, panel);
        	}
        });
        panel.add(awayOnsideKick);
        
        awayPunt = new JButton("Punt");
        awayPunt.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
        awayPunt.setBackground(awayTeam.getPrimaryColor());
        awayPunt.setForeground(awayTeam.getSecondaryColor());
        awayPunt.setBounds(frame.getWidth() * 4 / 15, 330, frame.getWidth() * 2 / 15, 50);
        awayPunt.setEnabled(false);
        awayPunt.setVisible(false);
        awayPunt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (overtimeNow) {
        			overtimeAwayPoss = true;
        		}
        		
        		down = 1;
				yardsToGo = 10;
				downs.setText("1st & 10");
				updateTime();
				
        		double i = Math.random();
        		if (i < 0.99) {
    				awayPossession.setVisible(false);
    				homePossession.setVisible(true);
    				
        			double j = Math.random();
        			if (j < 0.05) {
        				if (!homeSideOfField && yardLine != 50) {
        					yardLine += 30;
        					if (yardLine < 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else if (yardLine == 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + yardLine + ".");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				else {
        					yardLine -= 30;
        					homeSideOfField = true;
        					if (yardLine >= 0) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				play(frame, panel);
        			}
        			else if (j < 0.15) {
        				if (!homeSideOfField && yardLine != 50) {
        					yardLine += 35;
        					if (yardLine < 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else if (yardLine == 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + yardLine + ".");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				else {
        					yardLine -= 35;
        					homeSideOfField = true;
        					if (yardLine >= 0) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				play(frame, panel);
        			}
        			else if (j < 0.35) {
        				if (!homeSideOfField && yardLine != 50) {
        					yardLine += 40;
        					if (yardLine < 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else if (yardLine == 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + yardLine + ".");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				else {
        					yardLine -= 40;
        					homeSideOfField = true;
        					if (yardLine >= 0) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				play(frame, panel);
        			}
        			else if (j < 0.6) {
        				if (!homeSideOfField && yardLine != 50) {
        					yardLine += 45;
        					if (yardLine < 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else if (yardLine == 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + yardLine + ".");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				else {
        					yardLine -= 45;
        					homeSideOfField = true;
        					if (yardLine >= 0) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				play(frame, panel);
        			}
        			else if (j < 0.8) {
        				if (!homeSideOfField && yardLine != 50) {
        					yardLine += 50;
        					if (yardLine < 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else if (yardLine == 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + yardLine + ".");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				else {
        					yardLine -= 50;
        					homeSideOfField = true;
        					if (yardLine >= 0) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				play(frame, panel);
        			}
        			else if (j < 0.9) {
        				if (!homeSideOfField && yardLine != 50) {
        					yardLine += 55;
        					if (yardLine < 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else if (yardLine == 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + yardLine + ".");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				else {
        					yardLine -= 55;
        					homeSideOfField = true;
        					if (yardLine >= 0) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				play(frame, panel);
        			}
        			else if (j < 0.95) {
        				if (!homeSideOfField && yardLine != 50) {
        					yardLine += 60;
        					if (yardLine < 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else if (yardLine == 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + yardLine + ".");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				else {
        					yardLine -= 60;
        					homeSideOfField = true;
        					if (yardLine >= 0) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt to the " + homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				play(frame, panel);
        			}
        			else {
        				lastPlay.setText(awayTeam.getTeamAbbr() + " punt returned for a TOUCHDOWN by " + homeTeam.getTeamAbbr() + "!");
        				touchdown(false, frame, panel);
        			}
        		}
        		else {
    				awayPossession.setVisible(true);
    				homePossession.setVisible(false);
    				
        			double j = Math.random();
        			if (j < 0.05) {
        				if (!homeSideOfField && yardLine != 50) {
        					yardLine += 30;
        					if (yardLine < 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else if (yardLine == 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + yardLine + "!");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				else {
        					yardLine -= 30;
        					homeSideOfField = true;
        					if (yardLine >= 0) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				if (homeSideOfField && yardLine <= 10) {
        					downs.setText("1st & GL");
        					if (yardLine == 5) {
        						yardsToGo = 5;
        					}
        				}
        				play(frame, panel);
        			}
        			else if (j < 0.15) {
        				if (!homeSideOfField && yardLine != 50) {
        					yardLine += 35;
        					if (yardLine < 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else if (yardLine == 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + yardLine + "!");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				else {
        					yardLine -= 35;
        					homeSideOfField = true;
        					if (yardLine >= 0) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				if (homeSideOfField && yardLine <= 10) {
        					downs.setText("1st & GL");
        					if (yardLine == 5) {
        						yardsToGo = 5;
        					}
        				}
        				play(frame, panel);
        			}
        			else if (j < 0.35) {
        				if (!homeSideOfField && yardLine != 50) {
        					yardLine += 40;
        					if (yardLine < 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else if (yardLine == 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + yardLine + "!");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				else {
        					yardLine -= 40;
        					homeSideOfField = true;
        					if (yardLine >= 0) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				if (homeSideOfField && yardLine <= 10) {
        					downs.setText("1st & GL");
        					if (yardLine == 5) {
        						yardsToGo = 5;
        					}
        				}
        				play(frame, panel);
        			}
        			else if (j < 0.6) {
        				if (!homeSideOfField && yardLine != 50) {
        					yardLine += 45;
        					if (yardLine < 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else if (yardLine == 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + yardLine + "!");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				else {
        					yardLine -= 45;
        					homeSideOfField = true;
        					if (yardLine >= 0) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				if (homeSideOfField && yardLine <= 10) {
        					downs.setText("1st & GL");
        					if (yardLine == 5) {
        						yardsToGo = 5;
        					}
        				}
        				play(frame, panel);
        			}
        			else if (j < 0.8) {
        				if (!homeSideOfField && yardLine != 50) {
        					yardLine += 50;
        					if (yardLine < 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else if (yardLine == 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + yardLine + "!");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				else {
        					yardLine -= 50;
        					homeSideOfField = true;
        					if (yardLine >= 0) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				if (homeSideOfField && yardLine <= 10) {
        					downs.setText("1st & GL");
        					if (yardLine == 5) {
        						yardsToGo = 5;
        					}
        				}
        				play(frame, panel);
        			}
        			else if (j < 0.9) {
        				if (!homeSideOfField && yardLine != 50) {
        					yardLine += 55;
        					if (yardLine < 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else if (yardLine == 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + yardLine + "!");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				else {
        					yardLine -= 55;
        					homeSideOfField = true;
        					if (yardLine >= 0) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				if (homeSideOfField && yardLine <= 10) {
        					downs.setText("1st & GL");
        					if (yardLine == 5) {
        						yardsToGo = 5;
        					}
        				}
        				play(frame, panel);
        			}
        			else if (j < 0.95) {
        				if (!homeSideOfField && yardLine != 50) {
        					yardLine += 60;
        					if (yardLine < 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else if (yardLine == 50) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + yardLine + "!");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
        						homeSideOfField = true;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				else {
        					yardLine -= 60;
        					homeSideOfField = true;
        					if (yardLine >= 0) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ awayTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				if (homeSideOfField && yardLine <= 10) {
        					downs.setText("1st & GL");
        					if (yardLine == 5) {
        						yardsToGo = 5;
        					}
        				}
        				play(frame, panel);
        			}
        			else {
        				lastPlay.setText(awayTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
        						+ awayTeam.getTeamAbbr() + " and returned for a TOUCHDOWN!");
        				touchdown(true, frame, panel);
        			}
        		}
            }
        });
        panel.add(awayPunt);
        
        awayFieldGoal = new JButton("Field Goal");
        awayFieldGoal.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
        awayFieldGoal.setBackground(awayTeam.getPrimaryColor());
        awayFieldGoal.setForeground(awayTeam.getSecondaryColor());
        awayFieldGoal.setBounds(frame.getWidth() * 4 / 15, 380, frame.getWidth() * 2 / 15, 50);
        awayFieldGoal.setEnabled(false);
        awayFieldGoal.setVisible(false);
        awayFieldGoal.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (overtimeNow) {
        			overtimeAwayPoss = true;
        		}
        		
        		updateTime();
        		
        		if (yardLine == 5) {
        			double i = Math.random();
            		if (i < 0.95) {
            			awayScore += 3;
            			awayPoints.setText("" + awayScore);
            			lastPlay.setText(awayTeam.getTeamAbbr() + " 23 yard field goal is GOOD!");
            			if (overtimeNow && overtimeHomePoss && homeTeam instanceof NFLTeam && awayScore != homeScore) {
							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
							quarter.setText("FINAL");
							awayTimeout1.setVisible(false);
							awayTimeout2.setVisible(false);
							awayTimeout3.setVisible(false);
							homeTimeout1.setVisible(false);
							homeTimeout2.setVisible(false);
							homeTimeout3.setVisible(false);
							awaySim.setVisible(false);
							homeSim.setVisible(false);
							awayKickoff.setVisible(false);
							awayOnsideKick.setVisible(false);
							awayPunt.setVisible(false);
							awayFieldGoal.setVisible(false);
							awayExtraPoint.setVisible(false);
							awayTwoPoint.setVisible(false);
							homeKickoff.setVisible(false);
							homeOnsideKick.setVisible(false);
							homePunt.setVisible(false);
							homeFieldGoal.setVisible(false);
							homeExtraPoint.setVisible(false);
							homeTwoPoint.setVisible(false);
							awayPlay1.setVisible(false);
							awayPlay2.setVisible(false);
							awayPlay3.setVisible(false);
							awayPlay4.setVisible(false);
							awayPlay5.setVisible(false);
							homePlay1.setVisible(false);
							homePlay2.setVisible(false);
							homePlay3.setVisible(false);
							homePlay4.setVisible(false);
							homePlay5.setVisible(false);
							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
							returnToMainMenu.setBackground(Color.BLACK);
							returnToMainMenu.setForeground(Color.WHITE);
							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
							returnToMainMenu.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									mainMenu.mainMenu();
								}
							});
							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
    					}
    					else {
    						kickoff(true, frame, panel);
    					}
            		}
            		else {
        				down = 1;
        				yardsToGo = 10;
        				downs.setText("1st & 10");
        				awayPossession.setVisible(false);
        				homePossession.setVisible(true);
        				
            			double j = Math.random();
            			if (j < 0.8) {
            				homeSideOfField = true;
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 23 yard field goal is NO GOOD!");
                			if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(true, frame, panel);;
                			}
                			else {
                				play(frame, panel);
                			}
            			}
            			else if (j < 0.95) {
            				yardLine += 10;
            				if (yardLine < 50) {
            					homeSideOfField = true;
            					position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
            				}
            				else if (yardLine == 50) {
            					homeSideOfField = false;
            					position.setText("" + yardLine);
            				}
            				else {
            					yardLine = 100 - yardLine;
            					homeSideOfField = false;
            					position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
            				}
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 23 yard field goal is BLOCKED and NO GOOD!");
            				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(true, frame, panel);;
                			}
                			else {
                				play(frame, panel);
                			}
            			}
            			else {
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 23 yard field goal is NO GOOD, AND RETURNED FOR A TOUCHDOWN BY "
            						+ homeTeam.getTeamAbbr() + "!");
            				touchdown(false, frame, panel);
            			}
            		}
        		}
        		else if (yardLine == 10) {
        			double i = Math.random();
            		if (i < 0.9) {
            			awayScore += 3;
            			awayPoints.setText("" + awayScore);
            			lastPlay.setText(awayTeam.getTeamAbbr() + " 28 yard field goal is GOOD!");
            			if (overtimeNow && overtimeHomePoss && homeTeam instanceof NFLTeam && awayScore != homeScore) {
							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
							quarter.setText("FINAL");
							awayTimeout1.setVisible(false);
							awayTimeout2.setVisible(false);
							awayTimeout3.setVisible(false);
							homeTimeout1.setVisible(false);
							homeTimeout2.setVisible(false);
							homeTimeout3.setVisible(false);
							awaySim.setVisible(false);
							homeSim.setVisible(false);
							awayKickoff.setVisible(false);
							awayOnsideKick.setVisible(false);
							awayPunt.setVisible(false);
							awayFieldGoal.setVisible(false);
							awayExtraPoint.setVisible(false);
							awayTwoPoint.setVisible(false);
							homeKickoff.setVisible(false);
							homeOnsideKick.setVisible(false);
							homePunt.setVisible(false);
							homeFieldGoal.setVisible(false);
							homeExtraPoint.setVisible(false);
							homeTwoPoint.setVisible(false);
							awayPlay1.setVisible(false);
							awayPlay2.setVisible(false);
							awayPlay3.setVisible(false);
							awayPlay4.setVisible(false);
							awayPlay5.setVisible(false);
							homePlay1.setVisible(false);
							homePlay2.setVisible(false);
							homePlay3.setVisible(false);
							homePlay4.setVisible(false);
							homePlay5.setVisible(false);
							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
							returnToMainMenu.setBackground(Color.BLACK);
							returnToMainMenu.setForeground(Color.WHITE);
							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
							returnToMainMenu.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									mainMenu.mainMenu();
								}
							});
							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
    					}
    					else {
    						kickoff(true, frame, panel);
    					}
            		}
            		else {
        				down = 1;
        				yardsToGo = 10;
        				downs.setText("1st & 10");
        				awayPossession.setVisible(false);
        				homePossession.setVisible(true);
        				
            			double j = Math.random();
            			if (j < 0.8) {
            				homeSideOfField = true;
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 28 yard field goal is NO GOOD!");
            				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(true, frame, panel);;
                			}
                			else {
                				play(frame, panel);
                			}
            			}
            			else if (j < 0.95) {
            				yardLine += 10;
            				if (yardLine < 50) {
            					homeSideOfField = true;
            					position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
            				}
            				else if (yardLine == 50) {
            					homeSideOfField = false;
            					position.setText("" + yardLine);
            				}
            				else {
            					yardLine = 100 - yardLine;
            					homeSideOfField = false;
            					position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
            				}
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 28 yard field goal is BLOCKED and NO GOOD!");
            				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(true, frame, panel);;
                			}
                			else {
                				play(frame, panel);
                			}
            			}
            			else {
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 28 yard field goal is NO GOOD, AND RETURNED FOR A TOUCHDOWN BY "
            						+ homeTeam.getTeamAbbr() + "!");
            				touchdown(false, frame, panel);
            			}
            		}
        		}
        		else if (yardLine == 15) {
        			double i = Math.random();
            		if (i < 0.85) {
            			awayScore += 3;
            			awayPoints.setText("" + awayScore);
            			lastPlay.setText(awayTeam.getTeamAbbr() + " 33 yard field goal is GOOD!");
            			if (overtimeNow && overtimeHomePoss && homeTeam instanceof NFLTeam && awayScore != homeScore) {
							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
							quarter.setText("FINAL");
							awayTimeout1.setVisible(false);
							awayTimeout2.setVisible(false);
							awayTimeout3.setVisible(false);
							homeTimeout1.setVisible(false);
							homeTimeout2.setVisible(false);
							homeTimeout3.setVisible(false);
							awaySim.setVisible(false);
							homeSim.setVisible(false);
							awayKickoff.setVisible(false);
							awayOnsideKick.setVisible(false);
							awayPunt.setVisible(false);
							awayFieldGoal.setVisible(false);
							awayExtraPoint.setVisible(false);
							awayTwoPoint.setVisible(false);
							homeKickoff.setVisible(false);
							homeOnsideKick.setVisible(false);
							homePunt.setVisible(false);
							homeFieldGoal.setVisible(false);
							homeExtraPoint.setVisible(false);
							homeTwoPoint.setVisible(false);
							awayPlay1.setVisible(false);
							awayPlay2.setVisible(false);
							awayPlay3.setVisible(false);
							awayPlay4.setVisible(false);
							awayPlay5.setVisible(false);
							homePlay1.setVisible(false);
							homePlay2.setVisible(false);
							homePlay3.setVisible(false);
							homePlay4.setVisible(false);
							homePlay5.setVisible(false);
							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
							returnToMainMenu.setBackground(Color.BLACK);
							returnToMainMenu.setForeground(Color.WHITE);
							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
							returnToMainMenu.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									mainMenu.mainMenu();
								}
							});
							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
    					}
    					else {
    						kickoff(true, frame, panel);
    					}
            		}
            		else {
        				down = 1;
        				yardsToGo = 10;
        				downs.setText("1st & 10");
        				awayPossession.setVisible(false);
        				homePossession.setVisible(true);
        				
            			double j = Math.random();
            			if (j < 0.8) {
            				homeSideOfField = true;
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 33 yard field goal is NO GOOD!");
            				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(true, frame, panel);;
                			}
                			else {
                				play(frame, panel);
                			}
            			}
            			else if (j < 0.95) {
            				yardLine += 10;
            				if (yardLine < 50) {
            					homeSideOfField = true;
            					position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
            				}
            				else if (yardLine == 50) {
            					homeSideOfField = false;
            					position.setText("" + yardLine);
            				}
            				else {
            					yardLine = 100 - yardLine;
            					homeSideOfField = false;
            					position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
            				}
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 33 yard field goal is BLOCKED and NO GOOD!");
            				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(true, frame, panel);;
                			}
                			else {
                				play(frame, panel);
                			}
            			}
            			else {
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 33 yard field goal is NO GOOD, AND RETURNED FOR A TOUCHDOWN BY "
            						+ homeTeam.getTeamAbbr() + "!");
            				touchdown(false, frame, panel);
            			}
            		}
        		}
        		else if (yardLine == 20) {
        			double i = Math.random();
            		if (i < 0.8) {
            			awayScore += 3;
            			awayPoints.setText("" + awayScore);
            			lastPlay.setText(awayTeam.getTeamAbbr() + " 38 yard field goal is GOOD!");
            			if (overtimeNow && overtimeHomePoss && homeTeam instanceof NFLTeam && awayScore != homeScore) {
							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
							quarter.setText("FINAL");
							awayTimeout1.setVisible(false);
							awayTimeout2.setVisible(false);
							awayTimeout3.setVisible(false);
							homeTimeout1.setVisible(false);
							homeTimeout2.setVisible(false);
							homeTimeout3.setVisible(false);
							awaySim.setVisible(false);
							homeSim.setVisible(false);
							awayKickoff.setVisible(false);
							awayOnsideKick.setVisible(false);
							awayPunt.setVisible(false);
							awayFieldGoal.setVisible(false);
							awayExtraPoint.setVisible(false);
							awayTwoPoint.setVisible(false);
							homeKickoff.setVisible(false);
							homeOnsideKick.setVisible(false);
							homePunt.setVisible(false);
							homeFieldGoal.setVisible(false);
							homeExtraPoint.setVisible(false);
							homeTwoPoint.setVisible(false);
							awayPlay1.setVisible(false);
							awayPlay2.setVisible(false);
							awayPlay3.setVisible(false);
							awayPlay4.setVisible(false);
							awayPlay5.setVisible(false);
							homePlay1.setVisible(false);
							homePlay2.setVisible(false);
							homePlay3.setVisible(false);
							homePlay4.setVisible(false);
							homePlay5.setVisible(false);
							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
							returnToMainMenu.setBackground(Color.BLACK);
							returnToMainMenu.setForeground(Color.WHITE);
							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
							returnToMainMenu.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									mainMenu.mainMenu();
								}
							});
							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
						}
    					else {
    						kickoff(true, frame, panel);
    					}
            		}
            		else {
        				down = 1;
        				yardsToGo = 10;
        				downs.setText("1st & 10");
        				awayPossession.setVisible(false);
        				homePossession.setVisible(true);
        				
            			double j = Math.random();
            			if (j < 0.8) {
            				homeSideOfField = true;
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 38 yard field goal is NO GOOD!");
            				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(true, frame, panel);;
                			}
                			else {
                				play(frame, panel);
                			}
            			}
            			else if (j < 0.95) {
            				yardLine += 10;
            				if (yardLine < 50) {
            					homeSideOfField = true;
            					position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
            				}
            				else if (yardLine == 50) {
            					homeSideOfField = false;
            					position.setText("" + yardLine);
            				}
            				else {
            					yardLine = 100 - yardLine;
            					homeSideOfField = false;
            					position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
            				}
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 38 yard field goal is BLOCKED and NO GOOD!");
            				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(true, frame, panel);;
                			}
                			else {
                				play(frame, panel);
                			}
            			}
            			else {
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 38 yard field goal is NO GOOD, AND RETURNED FOR A TOUCHDOWN BY "
            						+ homeTeam.getTeamAbbr() + "!");
            				touchdown(false, frame, panel);
            			}
            		}
        		}
        		else if (yardLine == 25) {
        			double i = Math.random();
            		if (i < 0.7) {
            			awayScore += 3;
            			awayPoints.setText("" + awayScore);
            			lastPlay.setText(awayTeam.getTeamAbbr() + " 43 yard field goal is GOOD!");
            			if (overtimeNow && overtimeHomePoss && homeTeam instanceof NFLTeam && awayScore != homeScore) {
							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
							quarter.setText("FINAL");
							awayTimeout1.setVisible(false);
							awayTimeout2.setVisible(false);
							awayTimeout3.setVisible(false);
							homeTimeout1.setVisible(false);
							homeTimeout2.setVisible(false);
							homeTimeout3.setVisible(false);
							awaySim.setVisible(false);
							homeSim.setVisible(false);
							awayKickoff.setVisible(false);
							awayOnsideKick.setVisible(false);
							awayPunt.setVisible(false);
							awayFieldGoal.setVisible(false);
							awayExtraPoint.setVisible(false);
							awayTwoPoint.setVisible(false);
							homeKickoff.setVisible(false);
							homeOnsideKick.setVisible(false);
							homePunt.setVisible(false);
							homeFieldGoal.setVisible(false);
							homeExtraPoint.setVisible(false);
							homeTwoPoint.setVisible(false);
							awayPlay1.setVisible(false);
							awayPlay2.setVisible(false);
							awayPlay3.setVisible(false);
							awayPlay4.setVisible(false);
							awayPlay5.setVisible(false);
							homePlay1.setVisible(false);
							homePlay2.setVisible(false);
							homePlay3.setVisible(false);
							homePlay4.setVisible(false);
							homePlay5.setVisible(false);
							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
							returnToMainMenu.setBackground(Color.BLACK);
							returnToMainMenu.setForeground(Color.WHITE);
							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
							returnToMainMenu.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									mainMenu.mainMenu();
								}
							});
    						returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
    					}
    					else {
    						kickoff(true, frame, panel);
    					}
            		}
            		else {
        				down = 1;
        				yardsToGo = 10;
        				downs.setText("1st & 10");
        				awayPossession.setVisible(false);
        				homePossession.setVisible(true);
        				
            			double j = Math.random();
            			if (j < 0.8) {
            				homeSideOfField = true;
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 43 yard field goal is NO GOOD!");
            				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(true, frame, panel);;
                			}
                			else {
                				play(frame, panel);
                			}
            			}
            			else if (j < 0.95) {
            				yardLine += 10;
            				if (yardLine < 50) {
            					homeSideOfField = true;
            					position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
            				}
            				else if (yardLine == 50) {
            					homeSideOfField = false;
            					position.setText("" + yardLine);
            				}
            				else {
            					yardLine = 100 - yardLine;
            					homeSideOfField = false;
            					position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
            				}
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 43 yard field goal is BLOCKED and NO GOOD!");
            				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(true, frame, panel);;
                			}
                			else {
                				play(frame, panel);
                			}
            			}
            			else {
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 43 yard field goal is NO GOOD, AND RETURNED FOR A TOUCHDOWN BY "
            						+ homeTeam.getTeamAbbr() + "!");
            				touchdown(false, frame, panel);
            			}
            		}
        		}
        		else if (yardLine == 30) {
        			double i = Math.random();
            		if (i < 0.6) {
            			awayScore += 3;
            			awayPoints.setText("" + awayScore);
            			lastPlay.setText(awayTeam.getTeamAbbr() + " 48 yard field goal is GOOD!");
            			if (overtimeNow && overtimeHomePoss && homeTeam instanceof NFLTeam && awayScore != homeScore) {
							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
							quarter.setText("FINAL");
							awayTimeout1.setVisible(false);
							awayTimeout2.setVisible(false);
							awayTimeout3.setVisible(false);
							homeTimeout1.setVisible(false);
							homeTimeout2.setVisible(false);
							homeTimeout3.setVisible(false);
							awaySim.setVisible(false);
							homeSim.setVisible(false);
							awayKickoff.setVisible(false);
							awayOnsideKick.setVisible(false);
							awayPunt.setVisible(false);
							awayFieldGoal.setVisible(false);
							awayExtraPoint.setVisible(false);
							awayTwoPoint.setVisible(false);
							homeKickoff.setVisible(false);
							homeOnsideKick.setVisible(false);
							homePunt.setVisible(false);
							homeFieldGoal.setVisible(false);
							homeExtraPoint.setVisible(false);
							homeTwoPoint.setVisible(false);
							awayPlay1.setVisible(false);
							awayPlay2.setVisible(false);
							awayPlay3.setVisible(false);
							awayPlay4.setVisible(false);
							awayPlay5.setVisible(false);
							homePlay1.setVisible(false);
							homePlay2.setVisible(false);
							homePlay3.setVisible(false);
							homePlay4.setVisible(false);
							homePlay5.setVisible(false);
							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
							returnToMainMenu.setBackground(Color.BLACK);
							returnToMainMenu.setForeground(Color.WHITE);
							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
							returnToMainMenu.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									mainMenu.mainMenu();
								}
							});
							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
    					}
    					else {
    						kickoff(true, frame, panel);
    					}
            		}
            		else {
        				down = 1;
        				yardsToGo = 10;
        				downs.setText("1st & 10");
        				awayPossession.setVisible(false);
        				homePossession.setVisible(true);
        				
            			double j = Math.random();
            			if (j < 0.8) {
            				homeSideOfField = true;
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 48 yard field goal is NO GOOD!");
            				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(true, frame, panel);;
                			}
                			else {
                				play(frame, panel);
                			}
            			}
            			else if (j < 0.95) {
            				yardLine += 10;
            				if (yardLine < 50) {
            					homeSideOfField = true;
            					position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
            				}
            				else if (yardLine == 50) {
            					homeSideOfField = false;
            					position.setText("" + yardLine);
            				}
            				else {
            					yardLine = 100 - yardLine;
            					homeSideOfField = false;
            					position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
            				}
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 48 yard field goal is BLOCKED and NO GOOD!");
            				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(true, frame, panel);;
                			}
                			else {
                				play(frame, panel);
                			}
            			}
            			else {
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 48 yard field goal is NO GOOD, AND RETURNED FOR A TOUCHDOWN BY "
            						+ homeTeam.getTeamAbbr() + "!");
            				touchdown(false, frame, panel);
            			}
            		}
        		}
        		else if (yardLine == 35) {
        			double i = Math.random();
            		if (i < 0.5) {
            			awayScore += 3;
            			awayPoints.setText("" + awayScore);
            			lastPlay.setText(awayTeam.getTeamAbbr() + " 53 yard field goal is GOOD!");
            			if (overtimeNow && overtimeHomePoss && homeTeam instanceof NFLTeam && awayScore != homeScore) {
							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
							quarter.setText("FINAL");
							awayTimeout1.setVisible(false);
							awayTimeout2.setVisible(false);
							awayTimeout3.setVisible(false);
							homeTimeout1.setVisible(false);
							homeTimeout2.setVisible(false);
							homeTimeout3.setVisible(false);
							awaySim.setVisible(false);
							homeSim.setVisible(false);
							awayKickoff.setVisible(false);
							awayOnsideKick.setVisible(false);
							awayPunt.setVisible(false);
							awayFieldGoal.setVisible(false);
							awayExtraPoint.setVisible(false);
							awayTwoPoint.setVisible(false);
							homeKickoff.setVisible(false);
							homeOnsideKick.setVisible(false);
							homePunt.setVisible(false);
							homeFieldGoal.setVisible(false);
							homeExtraPoint.setVisible(false);
							homeTwoPoint.setVisible(false);
							awayPlay1.setVisible(false);
							awayPlay2.setVisible(false);
							awayPlay3.setVisible(false);
							awayPlay4.setVisible(false);
							awayPlay5.setVisible(false);
							homePlay1.setVisible(false);
							homePlay2.setVisible(false);
							homePlay3.setVisible(false);
							homePlay4.setVisible(false);
							homePlay5.setVisible(false);
							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
							returnToMainMenu.setBackground(Color.BLACK);
							returnToMainMenu.setForeground(Color.WHITE);
							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
							returnToMainMenu.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									mainMenu.mainMenu();
								}
							});
							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
						}
    					else {
    						kickoff(true, frame, panel);
    					}
            		}
            		else {
        				down = 1;
        				yardsToGo = 10;
        				downs.setText("1st & 10");
        				awayPossession.setVisible(false);
        				homePossession.setVisible(true);
        				
            			double j = Math.random();
            			if (j < 0.8) {
            				homeSideOfField = true;
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 53 yard field goal is NO GOOD!");
            				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(true, frame, panel);;
                			}
                			else {
                				play(frame, panel);
                			}
            			}
            			else if (j < 0.95) {
            				yardLine += 10;
            				if (yardLine < 50) {
            					homeSideOfField = true;
            					position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
            				}
            				else if (yardLine == 50) {
            					homeSideOfField = false;
            					position.setText("" + yardLine);
            				}
            				else {
            					yardLine = 100 - yardLine;
            					homeSideOfField = false;
            					position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
            				}
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 53 yard field goal is BLOCKED and NO GOOD!");
            				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(true, frame, panel);;
                			}
                			else {
                				play(frame, panel);
                			}
            			}
            			else {
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 53 yard field goal is NO GOOD, AND RETURNED FOR A TOUCHDOWN BY "
            						+ homeTeam.getTeamAbbr() + "!");
            				touchdown(false, frame, panel);
            			}
            		}
        		}
        		else if (yardLine == 40) {
        			double i = Math.random();
            		if (i < 0.35) {
            			awayScore += 3;
            			awayPoints.setText("" + awayScore);
            			lastPlay.setText(awayTeam.getTeamAbbr() + " 58 yard field goal is GOOD!");
            			if (overtimeNow && overtimeHomePoss && homeTeam instanceof NFLTeam && awayScore != homeScore) {
							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
							quarter.setText("FINAL");
							awayTimeout1.setVisible(false);
							awayTimeout2.setVisible(false);
							awayTimeout3.setVisible(false);
							homeTimeout1.setVisible(false);
							homeTimeout2.setVisible(false);
							homeTimeout3.setVisible(false);
							awaySim.setVisible(false);
							homeSim.setVisible(false);
							awayKickoff.setVisible(false);
							awayOnsideKick.setVisible(false);
							awayPunt.setVisible(false);
							awayFieldGoal.setVisible(false);
							awayExtraPoint.setVisible(false);
							awayTwoPoint.setVisible(false);
							homeKickoff.setVisible(false);
							homeOnsideKick.setVisible(false);
							homePunt.setVisible(false);
							homeFieldGoal.setVisible(false);
							homeExtraPoint.setVisible(false);
							homeTwoPoint.setVisible(false);
							awayPlay1.setVisible(false);
							awayPlay2.setVisible(false);
							awayPlay3.setVisible(false);
							awayPlay4.setVisible(false);
							awayPlay5.setVisible(false);
							homePlay1.setVisible(false);
							homePlay2.setVisible(false);
							homePlay3.setVisible(false);
							homePlay4.setVisible(false);
							homePlay5.setVisible(false);
							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
							returnToMainMenu.setBackground(Color.BLACK);
							returnToMainMenu.setForeground(Color.WHITE);
							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
							returnToMainMenu.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									mainMenu.mainMenu();
								}
							});
							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
						}
    					else {
    						kickoff(true, frame, panel);
    					}
            		}
            		else {
        				down = 1;
        				yardsToGo = 10;
        				downs.setText("1st & 10");
        				awayPossession.setVisible(false);
        				homePossession.setVisible(true);
        				
            			double j = Math.random();
            			if (j < 0.8) {
            				homeSideOfField = true;
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 58 yard field goal is NO GOOD!");
            				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(true, frame, panel);;
                			}
                			else {
                				play(frame, panel);
                			}
            			}
            			else if (j < 0.95) {
            				yardLine += 10;
            				if (yardLine < 50) {
            					homeSideOfField = true;
            					position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
            				}
            				else if (yardLine == 50) {
            					homeSideOfField = false;
            					position.setText("" + yardLine);
            				}
            				else {
            					yardLine = 100 - yardLine;
            					homeSideOfField = false;
            					position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
            				}
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 58 yard field goal is BLOCKED and NO GOOD!");
            				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(true, frame, panel);;
                			}
                			else {
                				play(frame, panel);
                			}
            			}
            			else {
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 58 yard field goal is NO GOOD, AND RETURNED FOR A TOUCHDOWN BY "
            						+ homeTeam.getTeamAbbr() + "!");
            				touchdown(false, frame, panel);
            			}
            		}
        		}
        		else if (yardLine == 45) {
        			double i = Math.random();
            		if (i < 0.2) {
            			awayScore += 3;
            			awayPoints.setText("" + awayScore);
            			lastPlay.setText(awayTeam.getTeamAbbr() + " 63 yard field goal is GOOD!");
            			if (overtimeNow && overtimeHomePoss && homeTeam instanceof NFLTeam && awayScore != homeScore) {
							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
							quarter.setText("FINAL");
							awayTimeout1.setVisible(false);
							awayTimeout2.setVisible(false);
							awayTimeout3.setVisible(false);
							homeTimeout1.setVisible(false);
							homeTimeout2.setVisible(false);
							homeTimeout3.setVisible(false);
							awaySim.setVisible(false);
							homeSim.setVisible(false);
							awayKickoff.setVisible(false);
							awayOnsideKick.setVisible(false);
							awayPunt.setVisible(false);
							awayFieldGoal.setVisible(false);
							awayExtraPoint.setVisible(false);
							awayTwoPoint.setVisible(false);
							homeKickoff.setVisible(false);
							homeOnsideKick.setVisible(false);
							homePunt.setVisible(false);
							homeFieldGoal.setVisible(false);
							homeExtraPoint.setVisible(false);
							homeTwoPoint.setVisible(false);
							awayPlay1.setVisible(false);
							awayPlay2.setVisible(false);
							awayPlay3.setVisible(false);
							awayPlay4.setVisible(false);
							awayPlay5.setVisible(false);
							homePlay1.setVisible(false);
							homePlay2.setVisible(false);
							homePlay3.setVisible(false);
							homePlay4.setVisible(false);
							homePlay5.setVisible(false);
							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
							returnToMainMenu.setBackground(Color.BLACK);
							returnToMainMenu.setForeground(Color.WHITE);
							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
							returnToMainMenu.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									mainMenu.mainMenu();
								}
							});
							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
    					}
    					else {
    						kickoff(true, frame, panel);
    					}
            		}
            		else {
        				down = 1;
        				yardsToGo = 10;
        				downs.setText("1st & 10");
        				awayPossession.setVisible(false);
        				homePossession.setVisible(true);
        				
            			double j = Math.random();
            			if (j < 0.8) {
            				homeSideOfField = true;
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 63 yard field goal is NO GOOD!");
            				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(true, frame, panel);;
                			}
                			else {
                				play(frame, panel);
                			}
            			}
            			else if (j < 0.95) {
            				yardLine += 10;
            				if (yardLine < 50) {
            					homeSideOfField = true;
            					position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
            				}
            				else if (yardLine == 50) {
            					homeSideOfField = false;
            					position.setText("" + yardLine);
            				}
            				else {
            					yardLine = 100 - yardLine;
            					homeSideOfField = false;
            					position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
            				}
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 63 yard field goal is BLOCKED and NO GOOD!");
            				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(true, frame, panel);;
                			}
                			else {
                				play(frame, panel);
                			}
            			}
            			else {
            				lastPlay.setText(awayTeam.getTeamAbbr() + " 63 yard field goal is NO GOOD, AND RETURNED FOR A TOUCHDOWN BY "
            						+ homeTeam.getTeamAbbr() + "!");
            				touchdown(false, frame, panel);
            			}
            		}
        		}
            }
        });
        panel.add(awayFieldGoal);
        
        awayExtraPoint = new JButton("Extra Point");
        awayExtraPoint.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
        awayExtraPoint.setBackground(awayTeam.getPrimaryColor());
        awayExtraPoint.setForeground(awayTeam.getSecondaryColor());
        awayExtraPoint.setBounds(frame.getWidth() * 4 / 15, 430, frame.getWidth() * 2 / 15, 50);
        awayExtraPoint.setEnabled(false);
        awayExtraPoint.setVisible(false);
        awayExtraPoint.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		double i = Math.random();
        		if (i < 0.9) {
        			awayScore++;
        			awayPoints.setText("" + awayScore);
        			lastPlay.setText(awayTeam.getTeamAbbr() + " extra point is GOOD!");
        		}
        		else {
        			double j = Math.random();
        			if (j < 0.95) {
        				lastPlay.setText(awayTeam.getTeamAbbr() + " extra point is NO GOOD!");
        			}
        			else {
        				homeScore += 2;
        				homePoints.setText("" + homeScore);
        				lastPlay.setText(awayTeam.getTeamAbbr() + " extra point is NO GOOD, AND RETURNED FOR 2 POINTS BY "
        						+ homeTeam.getTeamAbbr() + "!");
        			}
        		}
        		kickoff(true, frame, panel);
            }
        });
        panel.add(awayExtraPoint);
        
        awayTwoPoint = new JButton("Two-Point");
        awayTwoPoint.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
        awayTwoPoint.setBackground(awayTeam.getPrimaryColor());
        awayTwoPoint.setForeground(awayTeam.getSecondaryColor());
        awayTwoPoint.setBounds(frame.getWidth() * 4 / 15, 480, frame.getWidth() * 2 / 15, 50);
        awayTwoPoint.setEnabled(false);
        awayTwoPoint.setVisible(false);
        awayTwoPoint.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		double i = Math.random();
        		if (i < 0.45) {
        			awayScore += 2;
        			awayPoints.setText("" + awayScore);
        			lastPlay.setText(awayTeam.getTeamAbbr() + " two point conversion is GOOD!");
        		}
        		else {
        			double j = Math.random();
        			if (j < 0.95) {
        				lastPlay.setText(awayTeam.getTeamAbbr() + " two point conversion is NO GOOD!");
        			}
        			else {
        				homeScore += 2;
        				homePoints.setText("" + homeScore);
        				lastPlay.setText(awayTeam.getTeamAbbr() + " two point conversion is NO GOOD, AND RETURNED FOR 2 POINTS BY "
        						+ homeTeam.getTeamAbbr() + "!");
        			}
        		}
        		kickoff(true, frame, panel);
            }
        });
        panel.add(awayTwoPoint);
        
        homeKickoff = new JButton("Kickoff");
        homeKickoff.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
        homeKickoff.setBackground(homeTeam.getPrimaryColor());
        homeKickoff.setForeground(homeTeam.getSecondaryColor());
        homeKickoff.setBounds(frame.getWidth() * 9 / 15, 230, frame.getWidth() * 2 / 15, 50);
        homeKickoff.setEnabled(false);
        homeKickoff.setVisible(false);
        homeKickoff.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		down = 1;
				yardsToGo = 10;
				downs.setText("1st & 10");
				homeSideOfField = false;
				
        		double i = Math.random();
        		if (i < 0.99) {
    				awayPossession.setVisible(true);
    				homePossession.setVisible(false);
    				
        			double j = Math.random();
        			if (j < 0.05) {
        				yardLine = 5;
        				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(homeTeam.getTeamAbbr() + " kickoff to the " + yardLine + ".");
        				play(frame, panel);
        			}
        			else if (j < 0.1) {
        				yardLine = 10;
        				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(homeTeam.getTeamAbbr() + " kickoff to the " + yardLine + ".");
        				play(frame, panel);
        			}
        			else if (j < 0.15) {
        				yardLine = 15;
        				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(homeTeam.getTeamAbbr() + " kickoff to the " + yardLine + ".");
        				play(frame, panel);
        			}
        			else if (j < 0.2) {
        				yardLine = 20;
        				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(homeTeam.getTeamAbbr() + " kickoff to the " + yardLine + ".");
        				play(frame, panel);
        			}
        			else if (j < 0.7) {
        				yardLine = 25;
        				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(homeTeam.getTeamAbbr() + " kickoff to the " + yardLine + ".");
        				play(frame, panel);
        			}
        			else if (j < 0.75) {
        				yardLine = 30;
        				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(homeTeam.getTeamAbbr() + " kickoff to the " + yardLine + ".");
        				play(frame, panel);
        			}
        			else if (j < 0.8) {
        				yardLine = 35;
        				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(homeTeam.getTeamAbbr() + " kickoff to the " + yardLine + ".");
        				play(frame, panel);
        			}
        			else if (j < 0.85) {
        				yardLine = 40;
        				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(homeTeam.getTeamAbbr() + " kickoff to the " + yardLine + ".");
        				play(frame, panel);
        			}
        			else if (j < 0.9) {
        				yardLine = 45;
        				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(homeTeam.getTeamAbbr() + " kickoff to the " + yardLine + ".");
        				play(frame, panel);
        			}
        			else if (j < 0.95) {
        				yardLine = 50;
        				position.setText("" + yardLine);
        				lastPlay.setText(homeTeam.getTeamAbbr() + " kickoff to the " + yardLine + ".");
        				play(frame, panel);
        			}
        			else {
        				lastPlay.setText(homeTeam.getTeamAbbr() + " kickoff returned for a TOUCHDOWN by " + awayTeam.getTeamAbbr() + "!");
        				touchdown(true, frame, panel);
        			}
        		}
        		else {
    				awayPossession.setVisible(false);
    				homePossession.setVisible(true);
    				
    				if (overtimeNow) {
    					overtimeAwayPoss = true;
    				}
    				
        			double j = Math.random();
        			if (j < 0.1) {
        				downs.setText("1st & GL");
        				yardLine = 10;
        				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(homeTeam.getTeamAbbr() + " kickoff FUMBLED and RECOVERED by " + 
        						homeTeam.getTeamAbbr() + " at the " + yardLine + "!");
        				play(frame, panel);
        			}
        			else if (j < 0.2) {
        				yardLine = 15;
        				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(homeTeam.getTeamAbbr() + " kickoff FUMBLED and RECOVERED by " + 
        						homeTeam.getTeamAbbr() + " at the " + yardLine + "!");
        				play(frame, panel);
        			}
        			else if (j < 0.4) {
        				yardLine = 20;
        				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(homeTeam.getTeamAbbr() + " kickoff FUMBLED and RECOVERED by " + 
        						homeTeam.getTeamAbbr() + " at the " + yardLine + "!");
        				play(frame, panel);
        			}
        			else if (j < 0.65) {
        				yardLine = 25;
        				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(homeTeam.getTeamAbbr() + " kickoff FUMBLED and RECOVERED by " + 
        						homeTeam.getTeamAbbr() + " at the " + yardLine + "!");
        				play(frame, panel);
        			}
        			else if (j < 0.85) {
        				yardLine = 30;
        				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(homeTeam.getTeamAbbr() + " kickoff FUMBLED and RECOVERED by " + 
        						homeTeam.getTeamAbbr() + " at the " + yardLine + "!");
        				play(frame, panel);
        			}
        			else if (j < 0.95) {
        				yardLine = 35;
        				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(homeTeam.getTeamAbbr() + " kickoff FUMBLED and RECOVERED by " + 
        						homeTeam.getTeamAbbr() + " at the " + yardLine + "!");
        				play(frame, panel);
        			}
        			else {
        				lastPlay.setText(homeTeam.getTeamAbbr() + " kickoff FUMBLED and RECOVERED by " + 
        						homeTeam.getTeamAbbr() + " and returned for a TOUCHDOWN!");
        				touchdown(false, frame, panel);
        			}
        		}
            }
        });
        panel.add(homeKickoff);
        
        homeOnsideKick = new JButton("Onside Kick");
        homeOnsideKick.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
        homeOnsideKick.setBackground(homeTeam.getPrimaryColor());
        homeOnsideKick.setForeground(homeTeam.getSecondaryColor());
        homeOnsideKick.setBounds(frame.getWidth() * 9 / 15, 280, frame.getWidth() * 2 / 15, 50);
        homeOnsideKick.setEnabled(false);
        homeOnsideKick.setVisible(false);
        homeOnsideKick.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		down = 1;
				yardsToGo = 10;
				downs.setText("1st & 10");
				homeSideOfField = true;
				
        		double i = Math.random();
        		if (i < 0.8) {
    				awayPossession.setVisible(true);
    				homePossession.setVisible(false);
    				
        			double j = Math.random();
        			if (j < 0.15) {
        				yardLine = 35;
        				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(homeTeam.getTeamAbbr() + " onside kick recovered by " +
        						awayTeam.getTeamAbbr() + " at the " + yardLine + "!");
        			}
        			else if (j < 0.25) {
        				yardLine = 40;
        				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(homeTeam.getTeamAbbr() + " onside kick recovered by " +
        						awayTeam.getTeamAbbr() + " at the " + yardLine + "!");
        			}
        			else if (j < 0.75) {
        				yardLine = 45;
        				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(homeTeam.getTeamAbbr() + " onside kick recovered by " +
        						awayTeam.getTeamAbbr() + " at the " + yardLine + "!");
        			}
        			else {
        				yardLine = 50;
        				position.setText("" + yardLine);
        				lastPlay.setText(homeTeam.getTeamAbbr() + " onside kick recovered by " +
        						awayTeam.getTeamAbbr() + " at the " + yardLine + "!");
        			}
        		}
        		else {
        			awayPossession.setVisible(false);
    				homePossession.setVisible(true);
    				
    				if (overtimeNow) {
    					overtimeAwayPoss = true;
    				}
    				
        			double j = Math.random();
        			if (j < 0.75) {
        				yardLine = 45;
        				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
        				lastPlay.setText(homeTeam.getTeamAbbr() + " onside kick recovered by " +
        						homeTeam.getTeamAbbr() + " at the " + yardLine + "!");
        			}
        			else {
        				yardLine = 50;
        				position.setText("" + yardLine);
        				lastPlay.setText(homeTeam.getTeamAbbr() + " onside kick recovered by " +
        						homeTeam.getTeamAbbr() + " at the " + yardLine + "!");
        			}
        		}
        		play(frame, panel);
        	}
        });
        panel.add(homeOnsideKick);
        
        homePunt = new JButton("Punt");
        homePunt.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
        homePunt.setBackground(homeTeam.getPrimaryColor());
        homePunt.setForeground(homeTeam.getSecondaryColor());
        homePunt.setBounds(frame.getWidth() * 9 / 15, 330, frame.getWidth() * 2 / 15, 50);
        homePunt.setEnabled(false);
        homePunt.setVisible(false);
        homePunt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (overtimeNow) {
        			overtimeHomePoss = true;
        		}
        		
        		down = 1;
				yardsToGo = 10;
				downs.setText("1st & 10");
				updateTime();
				
        		double i = Math.random();
        		if (i < 0.99) {
    				awayPossession.setVisible(true);
    				homePossession.setVisible(false);
    				
        			double j = Math.random();
        			if (j < 0.05) {
        				if (homeSideOfField && yardLine != 50) {
        					yardLine += 30;
        					if (yardLine < 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else if (yardLine == 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + yardLine + ".");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				else {
        					yardLine -= 30;
        					homeSideOfField = false;
        					if (yardLine >= 0) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				play(frame, panel);
        			}
        			else if (j < 0.15) {
        				if (homeSideOfField && yardLine != 50) {
        					yardLine += 35;
        					if (yardLine < 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else if (yardLine == 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + yardLine + ".");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				else {
        					yardLine -= 35;
        					homeSideOfField = false;
        					if (yardLine >= 0) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				play(frame, panel);
        			}
        			else if (j < 0.35) {
        				if (homeSideOfField && yardLine != 50) {
        					yardLine += 40;
        					if (yardLine < 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else if (yardLine == 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + yardLine + ".");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				else {
        					yardLine -= 40;
        					homeSideOfField = false;
        					if (yardLine >= 0) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				play(frame, panel);
        			}
        			else if (j < 0.6) {
        				if (homeSideOfField && yardLine != 50) {
        					yardLine += 45;
        					if (yardLine < 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else if (yardLine == 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + yardLine + ".");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				else {
        					yardLine -= 45;
        					homeSideOfField = false;
        					if (yardLine >= 0) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				play(frame, panel);
        			}
        			else if (j < 0.8) {
        				if (homeSideOfField && yardLine != 50) {
        					yardLine += 50;
        					if (yardLine < 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else if (yardLine == 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + yardLine + ".");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				else {
        					yardLine -= 50;
        					homeSideOfField = false;
        					if (yardLine >= 0) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				play(frame, panel);
        			}
        			else if (j < 0.9) {
        				if (homeSideOfField && yardLine != 50) {
        					yardLine += 55;
        					if (yardLine < 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else if (yardLine == 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + yardLine + ".");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				else {
        					yardLine -= 55;
        					homeSideOfField = false;
        					if (yardLine >= 0) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				play(frame, panel);
        			}
        			else if (j < 0.95) {
        				if (homeSideOfField && yardLine != 50) {
        					yardLine += 60;
        					if (yardLine < 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + homeTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else if (yardLine == 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + yardLine + ".");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				else {
        					yardLine -= 60;
        					homeSideOfField = false;
        					if (yardLine >= 0) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt to the " + awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        					else {
        						yardLine = 25;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt for a TOUCHBACK at the "
                						+ awayTeam.getTeamAbbr() + " " + yardLine + ".");
        					}
        				}
        				play(frame, panel);
        			}
        			else {
        				lastPlay.setText(homeTeam.getTeamAbbr() + " punt returned for a TOUCHDOWN by " + awayTeam.getTeamAbbr() + "!");
        				touchdown(true, frame, panel);
        			}
        		}
        		else {
    				awayPossession.setVisible(false);
    				homePossession.setVisible(true);
    				
        			double j = Math.random();
        			if (j < 0.05) {
        				if (homeSideOfField && yardLine != 50) {
        					yardLine += 30;
        					if (yardLine < 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else if (yardLine == 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + yardLine + "!");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				else {
        					yardLine -= 30;
        					homeSideOfField = false;
        					if (yardLine >= 0) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				if (!homeSideOfField && yardLine <= 10) {
        					downs.setText("1st & GL");
        					if (yardLine == 5) {
        						yardsToGo = 5;
        					}
        				}
        				play(frame, panel);
        			}
        			else if (j < 0.15) {
        				if (homeSideOfField && yardLine != 50) {
        					yardLine += 35;
        					if (yardLine < 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else if (yardLine == 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + yardLine + "!");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				else {
        					yardLine -= 35;
        					homeSideOfField = false;
        					if (yardLine >= 0) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				if (!homeSideOfField && yardLine <= 10) {
        					downs.setText("1st & GL");
        					if (yardLine == 5) {
        						yardsToGo = 5;
        					}
        				}
        				play(frame, panel);
        			}
        			else if (j < 0.35) {
        				if (homeSideOfField && yardLine != 50) {
        					yardLine += 40;
        					if (yardLine < 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else if (yardLine == 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + yardLine + "!");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				else {
        					yardLine -= 40;
        					homeSideOfField = false;
        					if (yardLine >= 0) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				if (!homeSideOfField && yardLine <= 10) {
        					downs.setText("1st & GL");
        					if (yardLine == 5) {
        						yardsToGo = 5;
        					}
        				}
        				play(frame, panel);
        			}
        			else if (j < 0.6) {
        				if (homeSideOfField && yardLine != 50) {
        					yardLine += 45;
        					if (yardLine < 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else if (yardLine == 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + yardLine + "!");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				else {
        					yardLine -= 45;
        					homeSideOfField = false;
        					if (yardLine >= 0) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				if (!homeSideOfField && yardLine <= 10) {
        					downs.setText("1st & GL");
        					if (yardLine == 5) {
        						yardsToGo = 5;
        					}
        				}
        				play(frame, panel);
        			}
        			else if (j < 0.8) {
        				if (homeSideOfField && yardLine != 50) {
        					yardLine += 50;
        					if (yardLine < 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else if (yardLine == 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + yardLine + "!");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				else {
        					yardLine -= 50;
        					homeSideOfField = false;
        					if (yardLine >= 0) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				if (!homeSideOfField && yardLine <= 10) {
        					downs.setText("1st & GL");
        					if (yardLine == 5) {
        						yardsToGo = 5;
        					}
        				}
        				play(frame, panel);
        			}
        			else if (j < 0.9) {
        				if (homeSideOfField && yardLine != 50) {
        					yardLine += 55;
        					if (yardLine < 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else if (yardLine == 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + yardLine + "!");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				else {
        					yardLine -= 55;
        					homeSideOfField = false;
        					if (yardLine >= 0) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				if (!homeSideOfField && yardLine <= 10) {
        					downs.setText("1st & GL");
        					if (yardLine == 5) {
        						yardsToGo = 5;
        					}
        				}
        				play(frame, panel);
        			}
        			else if (j < 0.95) {
        				if (homeSideOfField && yardLine != 50) {
        					yardLine += 60;
        					if (yardLine < 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + homeTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else if (yardLine == 50) {
                				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + yardLine + "!");
        					}
        					else if (yardLine <= 100) {
        						yardLine = 100 - yardLine;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
        						homeSideOfField = false;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				else {
        					yardLine -= 60;
        					homeSideOfField = false;
        					if (yardLine >= 0) {
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        					else {
        						yardLine = 10;
                				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
                				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
                						+ homeTeam.getTeamAbbr() + " at the " + awayTeam.getTeamAbbr() + " " + yardLine + "!");
        					}
        				}
        				if (!homeSideOfField && yardLine <= 10) {
        					downs.setText("1st & GL");
        					if (yardLine == 5) {
        						yardsToGo = 5;
        					}
        				}
        				play(frame, panel);
        			}
        			else {
        				lastPlay.setText(homeTeam.getTeamAbbr() + " punt FUMBLED and RECOVERED by "
        						+ homeTeam.getTeamAbbr() + " and returned for a TOUCHDOWN!");
        				touchdown(false, frame, panel);
        			}
        		}
            }
        });
        panel.add(homePunt);
        
        homeFieldGoal = new JButton("Field Goal");
        homeFieldGoal.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
        homeFieldGoal.setBackground(homeTeam.getPrimaryColor());
        homeFieldGoal.setForeground(homeTeam.getSecondaryColor());
        homeFieldGoal.setBounds(frame.getWidth() * 9 / 15, 380, frame.getWidth() * 2 / 15, 50);
        homeFieldGoal.setEnabled(false);
        homeFieldGoal.setVisible(false);
        homeFieldGoal.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if (overtimeNow) {
        			overtimeHomePoss = true;
        		}
	    		
	    		updateTime();
	    		
	    		if (yardLine == 5) {
	    			double i = Math.random();
	        		if (i < 0.95) {
	        			homeScore += 3;
	        			homePoints.setText("" + homeScore);
	        			lastPlay.setText(homeTeam.getTeamAbbr() + " 23 yard field goal is GOOD!");
	        			if (overtimeNow && overtimeAwayPoss && homeTeam instanceof NFLTeam && awayScore != homeScore) {
							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
							quarter.setText("FINAL");
							awayTimeout1.setVisible(false);
							awayTimeout2.setVisible(false);
							awayTimeout3.setVisible(false);
							homeTimeout1.setVisible(false);
							homeTimeout2.setVisible(false);
							homeTimeout3.setVisible(false);
							awaySim.setVisible(false);
							homeSim.setVisible(false);
							awayKickoff.setVisible(false);
							awayOnsideKick.setVisible(false);
							awayPunt.setVisible(false);
							awayFieldGoal.setVisible(false);
							awayExtraPoint.setVisible(false);
							awayTwoPoint.setVisible(false);
							homeKickoff.setVisible(false);
							homeOnsideKick.setVisible(false);
							homePunt.setVisible(false);
							homeFieldGoal.setVisible(false);
							homeExtraPoint.setVisible(false);
							homeTwoPoint.setVisible(false);
							awayPlay1.setVisible(false);
							awayPlay2.setVisible(false);
							awayPlay3.setVisible(false);
							awayPlay4.setVisible(false);
							awayPlay5.setVisible(false);
							homePlay1.setVisible(false);
							homePlay2.setVisible(false);
							homePlay3.setVisible(false);
							homePlay4.setVisible(false);
							homePlay5.setVisible(false);
							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
							returnToMainMenu.setBackground(Color.BLACK);
							returnToMainMenu.setForeground(Color.WHITE);
							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
							returnToMainMenu.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									mainMenu.mainMenu();
								}
							});
							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
						}
    					else {
    						kickoff(false, frame, panel);;
    					}
	        		}
	        		else {
	    				down = 1;
	    				yardsToGo = 10;
	    				downs.setText("1st & 10");
	    				awayPossession.setVisible(true);
	    				homePossession.setVisible(false);
	    				
	        			double j = Math.random();
	        			if (j < 0.8) {
	        				homeSideOfField = false;
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 23 yard field goal is NO GOOD!");
	        				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(false, frame, panel);
                			}
                			else {
                				play(frame, panel);
                			}
	        			}
	        			else if (j < 0.95) {
	        				yardLine += 10;
	        				if (yardLine < 50) {
	        					homeSideOfField = false;
	        					position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
	        				}
	        				else if (yardLine == 50) {
	        					homeSideOfField = true;
	        					position.setText("" + yardLine);
	        				}
	        				else {
	        					yardLine = 100 - yardLine;
	        					homeSideOfField = true;
	        					position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
	        				}
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 23 yard field goal is BLOCKED and NO GOOD!");
	        				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(false, frame, panel);
                			}
                			else {
                				play(frame, panel);
                			}
	        			}
	        			else {
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 23 yard field goal is NO GOOD, AND RETURNED FOR A TOUCHDOWN BY "
	        						+ awayTeam.getTeamAbbr() + "!");
	        				touchdown(true, frame, panel);
	        			}
	        		}
	    		}
	    		else if (yardLine == 10) {
	    			double i = Math.random();
	        		if (i < 0.9) {
	        			homeScore += 3;
	        			homePoints.setText("" + homeScore);
	        			lastPlay.setText(homeTeam.getTeamAbbr() + " 28 yard field goal is GOOD!");
	        			if (overtimeNow && overtimeAwayPoss && homeTeam instanceof NFLTeam && awayScore != homeScore) {
							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
							quarter.setText("FINAL");
							awayTimeout1.setVisible(false);
							awayTimeout2.setVisible(false);
							awayTimeout3.setVisible(false);
							homeTimeout1.setVisible(false);
							homeTimeout2.setVisible(false);
							homeTimeout3.setVisible(false);
							awaySim.setVisible(false);
							homeSim.setVisible(false);
							awayKickoff.setVisible(false);
							awayOnsideKick.setVisible(false);
							awayPunt.setVisible(false);
							awayFieldGoal.setVisible(false);
							awayExtraPoint.setVisible(false);
							awayTwoPoint.setVisible(false);
							homeKickoff.setVisible(false);
							homeOnsideKick.setVisible(false);
							homePunt.setVisible(false);
							homeFieldGoal.setVisible(false);
							homeExtraPoint.setVisible(false);
							homeTwoPoint.setVisible(false);
							awayPlay1.setVisible(false);
							awayPlay2.setVisible(false);
							awayPlay3.setVisible(false);
							awayPlay4.setVisible(false);
							awayPlay5.setVisible(false);
							homePlay1.setVisible(false);
							homePlay2.setVisible(false);
							homePlay3.setVisible(false);
							homePlay4.setVisible(false);
							homePlay5.setVisible(false);
							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
							returnToMainMenu.setBackground(Color.BLACK);
							returnToMainMenu.setForeground(Color.WHITE);
							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
							returnToMainMenu.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									mainMenu.mainMenu();
								}
							});
							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
						}
    					else {
    						kickoff(false, frame, panel);;
    					}
	        		}
	        		else {
	    				down = 1;
	    				yardsToGo = 10;
	    				downs.setText("1st & 10");
	    				awayPossession.setVisible(true);
	    				homePossession.setVisible(false);
	    				
	        			double j = Math.random();
	        			if (j < 0.8) {
	        				homeSideOfField = false;
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 28 yard field goal is NO GOOD!");
	        				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(false, frame, panel);
                			}
                			else {
                				play(frame, panel);
                			}
	        			}
	        			else if (j < 0.95) {
	        				yardLine += 10;
	        				if (yardLine < 50) {
	        					homeSideOfField = false;
	        					position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
	        				}
	        				else if (yardLine == 50) {
	        					homeSideOfField = true;
	        					position.setText("" + yardLine);
	        				}
	        				else {
	        					yardLine = 100 - yardLine;
	        					homeSideOfField = true;
	        					position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
	        				}
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 28 yard field goal is BLOCKED and NO GOOD!");
	        				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(false, frame, panel);
                			}
                			else {
                				play(frame, panel);
                			}
	        			}
	        			else {
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 28 yard field goal is NO GOOD, AND RETURNED FOR A TOUCHDOWN BY "
	        						+ awayTeam.getTeamAbbr() + "!");
	        				touchdown(true, frame, panel);
	        			}
	        		}
	    		}
	    		else if (yardLine == 15) {
	    			double i = Math.random();
	        		if (i < 0.85) {
	        			homeScore += 3;
	        			homePoints.setText("" + homeScore);
	        			lastPlay.setText(homeTeam.getTeamAbbr() + " 33 yard field goal is GOOD!");
	        			if (overtimeNow && overtimeAwayPoss && homeTeam instanceof NFLTeam && awayScore != homeScore) {
							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
							quarter.setText("FINAL");
							awayTimeout1.setVisible(false);
							awayTimeout2.setVisible(false);
							awayTimeout3.setVisible(false);
							homeTimeout1.setVisible(false);
							homeTimeout2.setVisible(false);
							homeTimeout3.setVisible(false);
							awaySim.setVisible(false);
							homeSim.setVisible(false);
							awayKickoff.setVisible(false);
							awayOnsideKick.setVisible(false);
							awayPunt.setVisible(false);
							awayFieldGoal.setVisible(false);
							awayExtraPoint.setVisible(false);
							awayTwoPoint.setVisible(false);
							homeKickoff.setVisible(false);
							homeOnsideKick.setVisible(false);
							homePunt.setVisible(false);
							homeFieldGoal.setVisible(false);
							homeExtraPoint.setVisible(false);
							homeTwoPoint.setVisible(false);
							awayPlay1.setVisible(false);
							awayPlay2.setVisible(false);
							awayPlay3.setVisible(false);
							awayPlay4.setVisible(false);
							awayPlay5.setVisible(false);
							homePlay1.setVisible(false);
							homePlay2.setVisible(false);
							homePlay3.setVisible(false);
							homePlay4.setVisible(false);
							homePlay5.setVisible(false);
							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
							returnToMainMenu.setBackground(Color.BLACK);
							returnToMainMenu.setForeground(Color.WHITE);
							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
							returnToMainMenu.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									mainMenu.mainMenu();
								}
							});
							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
						}
    					else {
    						kickoff(false, frame, panel);;
    					}
	        		}
	        		else {
	    				down = 1;
	    				yardsToGo = 10;
	    				downs.setText("1st & 10");
	    				awayPossession.setVisible(true);
	    				homePossession.setVisible(false);
	    				
	        			double j = Math.random();
	        			if (j < 0.8) {
	        				homeSideOfField = false;
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 33 yard field goal is NO GOOD!");
	        				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(false, frame, panel);
                			}
                			else {
                				play(frame, panel);
                			}
	        			}
	        			else if (j < 0.95) {
	        				yardLine += 10;
	        				if (yardLine < 50) {
	        					homeSideOfField = false;
	        					position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
	        				}
	        				else if (yardLine == 50) {
	        					homeSideOfField = true;
	        					position.setText("" + yardLine);
	        				}
	        				else {
	        					yardLine = 100 - yardLine;
	        					homeSideOfField = true;
	        					position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
	        				}
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 33 yard field goal is BLOCKED and NO GOOD!");
	        				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(false, frame, panel);
                			}
                			else {
                				play(frame, panel);
                			}
	        			}
	        			else {
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 33 yard field goal is NO GOOD, AND RETURNED FOR A TOUCHDOWN BY "
	        						+ awayTeam.getTeamAbbr() + "!");
	        				touchdown(true, frame, panel);
	        			}
	        		}
	    		}
	    		else if (yardLine == 20) {
	    			double i = Math.random();
	        		if (i < 0.8) {
	        			homeScore += 3;
	        			homePoints.setText("" + homeScore);
	        			lastPlay.setText(homeTeam.getTeamAbbr() + " 38 yard field goal is GOOD!");
	        			if (overtimeNow && overtimeAwayPoss && homeTeam instanceof NFLTeam && awayScore != homeScore) {
							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
							quarter.setText("FINAL");
							awayTimeout1.setVisible(false);
							awayTimeout2.setVisible(false);
							awayTimeout3.setVisible(false);
							homeTimeout1.setVisible(false);
							homeTimeout2.setVisible(false);
							homeTimeout3.setVisible(false);
							awaySim.setVisible(false);
							homeSim.setVisible(false);
							awayKickoff.setVisible(false);
							awayOnsideKick.setVisible(false);
							awayPunt.setVisible(false);
							awayFieldGoal.setVisible(false);
							awayExtraPoint.setVisible(false);
							awayTwoPoint.setVisible(false);
							homeKickoff.setVisible(false);
							homeOnsideKick.setVisible(false);
							homePunt.setVisible(false);
							homeFieldGoal.setVisible(false);
							homeExtraPoint.setVisible(false);
							homeTwoPoint.setVisible(false);
							awayPlay1.setVisible(false);
							awayPlay2.setVisible(false);
							awayPlay3.setVisible(false);
							awayPlay4.setVisible(false);
							awayPlay5.setVisible(false);
							homePlay1.setVisible(false);
							homePlay2.setVisible(false);
							homePlay3.setVisible(false);
							homePlay4.setVisible(false);
							homePlay5.setVisible(false);
							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
							returnToMainMenu.setBackground(Color.BLACK);
							returnToMainMenu.setForeground(Color.WHITE);
							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
							returnToMainMenu.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									mainMenu.mainMenu();
								}
							});
							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
						}
    					else {
    						kickoff(false, frame, panel);;
    					}
	        		}
	        		else {
	    				down = 1;
	    				yardsToGo = 10;
	    				downs.setText("1st & 10");
	    				awayPossession.setVisible(true);
	    				homePossession.setVisible(false);
	    				
	        			double j = Math.random();
	        			if (j < 0.8) {
	        				homeSideOfField = false;
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 38 yard field goal is NO GOOD!");
	        				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(false, frame, panel);
                			}
                			else {
                				play(frame, panel);
                			}
	        			}
	        			else if (j < 0.95) {
	        				yardLine += 10;
	        				if (yardLine < 50) {
	        					homeSideOfField = false;
	        					position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
	        				}
	        				else if (yardLine == 50) {
	        					homeSideOfField = true;
	        					position.setText("" + yardLine);
	        				}
	        				else {
	        					yardLine = 100 - yardLine;
	        					homeSideOfField = true;
	        					position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
	        				}
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 38 yard field goal is BLOCKED and NO GOOD!");
	        				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(false, frame, panel);
                			}
                			else {
                				play(frame, panel);
                			}
	        			}
	        			else {
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 38 yard field goal is NO GOOD, AND RETURNED FOR A TOUCHDOWN BY "
	        						+ awayTeam.getTeamAbbr() + "!");
	        				touchdown(true, frame, panel);
	        			}
	        		}
	    		}
	    		else if (yardLine == 25) {
	    			double i = Math.random();
	        		if (i < 0.7) {
	        			homeScore += 3;
	        			homePoints.setText("" + homeScore);
	        			lastPlay.setText(homeTeam.getTeamAbbr() + " 43 yard field goal is GOOD!");
	        			if (overtimeNow && overtimeAwayPoss && homeTeam instanceof NFLTeam && awayScore != homeScore) {
							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
							quarter.setText("FINAL");
							awayTimeout1.setVisible(false);
							awayTimeout2.setVisible(false);
							awayTimeout3.setVisible(false);
							homeTimeout1.setVisible(false);
							homeTimeout2.setVisible(false);
							homeTimeout3.setVisible(false);
							awaySim.setVisible(false);
							homeSim.setVisible(false);
							awayKickoff.setVisible(false);
							awayOnsideKick.setVisible(false);
							awayPunt.setVisible(false);
							awayFieldGoal.setVisible(false);
							awayExtraPoint.setVisible(false);
							awayTwoPoint.setVisible(false);
							homeKickoff.setVisible(false);
							homeOnsideKick.setVisible(false);
							homePunt.setVisible(false);
							homeFieldGoal.setVisible(false);
							homeExtraPoint.setVisible(false);
							homeTwoPoint.setVisible(false);
							awayPlay1.setVisible(false);
							awayPlay2.setVisible(false);
							awayPlay3.setVisible(false);
							awayPlay4.setVisible(false);
							awayPlay5.setVisible(false);
							homePlay1.setVisible(false);
							homePlay2.setVisible(false);
							homePlay3.setVisible(false);
							homePlay4.setVisible(false);
							homePlay5.setVisible(false);
							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
							returnToMainMenu.setBackground(Color.BLACK);
							returnToMainMenu.setForeground(Color.WHITE);
							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
							returnToMainMenu.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									mainMenu.mainMenu();
								}
							});
							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
						}
    					else {
    						kickoff(false, frame, panel);;
    					}
	        		}
	        		else {
	    				down = 1;
	    				yardsToGo = 10;
	    				downs.setText("1st & 10");
	    				awayPossession.setVisible(true);
	    				homePossession.setVisible(false);
	    				
	        			double j = Math.random();
	        			if (j < 0.8) {
	        				homeSideOfField = false;
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 43 yard field goal is NO GOOD!");
	        				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(false, frame, panel);
                			}
                			else {
                				play(frame, panel);
                			}
	        			}
	        			else if (j < 0.95) {
	        				yardLine += 10;
	        				if (yardLine < 50) {
	        					homeSideOfField = false;
	        					position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
	        				}
	        				else if (yardLine == 50) {
	        					homeSideOfField = true;
	        					position.setText("" + yardLine);
	        				}
	        				else {
	        					yardLine = 100 - yardLine;
	        					homeSideOfField = true;
	        					position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
	        				}
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 43 yard field goal is BLOCKED and NO GOOD!");
	        				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(false, frame, panel);
                			}
                			else {
                				play(frame, panel);
                			}
	        			}
	        			else {
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 43 yard field goal is NO GOOD, AND RETURNED FOR A TOUCHDOWN BY "
	        						+ awayTeam.getTeamAbbr() + "!");
	        				touchdown(true, frame, panel);
	        			}
	        		}
	    		}
	    		else if (yardLine == 30) {
	    			double i = Math.random();
	        		if (i < 0.6) {
	        			homeScore += 3;
	        			homePoints.setText("" + homeScore);
	        			lastPlay.setText(homeTeam.getTeamAbbr() + " 48 yard field goal is GOOD!");
	        			if (overtimeNow && overtimeAwayPoss && homeTeam instanceof NFLTeam && awayScore != homeScore) {
							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
							quarter.setText("FINAL");
							awayTimeout1.setVisible(false);
							awayTimeout2.setVisible(false);
							awayTimeout3.setVisible(false);
							homeTimeout1.setVisible(false);
							homeTimeout2.setVisible(false);
							homeTimeout3.setVisible(false);
							awaySim.setVisible(false);
							homeSim.setVisible(false);
							awayKickoff.setVisible(false);
							awayOnsideKick.setVisible(false);
							awayPunt.setVisible(false);
							awayFieldGoal.setVisible(false);
							awayExtraPoint.setVisible(false);
							awayTwoPoint.setVisible(false);
							homeKickoff.setVisible(false);
							homeOnsideKick.setVisible(false);
							homePunt.setVisible(false);
							homeFieldGoal.setVisible(false);
							homeExtraPoint.setVisible(false);
							homeTwoPoint.setVisible(false);
							awayPlay1.setVisible(false);
							awayPlay2.setVisible(false);
							awayPlay3.setVisible(false);
							awayPlay4.setVisible(false);
							awayPlay5.setVisible(false);
							homePlay1.setVisible(false);
							homePlay2.setVisible(false);
							homePlay3.setVisible(false);
							homePlay4.setVisible(false);
							homePlay5.setVisible(false);
							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
							returnToMainMenu.setBackground(Color.BLACK);
							returnToMainMenu.setForeground(Color.WHITE);
							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
							returnToMainMenu.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									mainMenu.mainMenu();
								}
							});
							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
    					}
    					else {
    						kickoff(false, frame, panel);;
    					}
	        		}
	        		else {
	    				down = 1;
	    				yardsToGo = 10;
	    				downs.setText("1st & 10");
	    				awayPossession.setVisible(true);
	    				homePossession.setVisible(false);
	    				
	        			double j = Math.random();
	        			if (j < 0.8) {
	        				homeSideOfField = false;
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 48 yard field goal is NO GOOD!");
	        				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(false, frame, panel);
                			}
                			else {
                				play(frame, panel);
                			}
	        			}
	        			else if (j < 0.95) {
	        				yardLine += 10;
	        				if (yardLine < 50) {
	        					homeSideOfField = false;
	        					position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
	        				}
	        				else if (yardLine == 50) {
	        					homeSideOfField = true;
	        					position.setText("" + yardLine);
	        				}
	        				else {
	        					yardLine = 100 - yardLine;
	        					homeSideOfField = true;
	        					position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
	        				}
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 48 yard field goal is BLOCKED and NO GOOD!");
	        				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(false, frame, panel);
                			}
                			else {
                				play(frame, panel);
                			}
	        			}
	        			else {
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 48 yard field goal is NO GOOD, AND RETURNED FOR A TOUCHDOWN BY "
	        						+ awayTeam.getTeamAbbr() + "!");
	        				touchdown(true, frame, panel);
	        			}
	        		}
	    		}
	    		else if (yardLine == 35) {
	    			double i = Math.random();
	        		if (i < 0.5) {
	        			homeScore += 3;
	        			homePoints.setText("" + homeScore);
	        			lastPlay.setText(homeTeam.getTeamAbbr() + " 53 yard field goal is GOOD!");
	        			if (overtimeNow && overtimeAwayPoss && homeTeam instanceof NFLTeam && awayScore != homeScore) {
							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
							quarter.setText("FINAL");
							awayTimeout1.setVisible(false);
							awayTimeout2.setVisible(false);
							awayTimeout3.setVisible(false);
							homeTimeout1.setVisible(false);
							homeTimeout2.setVisible(false);
							homeTimeout3.setVisible(false);
							awaySim.setVisible(false);
							homeSim.setVisible(false);
							awayKickoff.setVisible(false);
							awayOnsideKick.setVisible(false);
							awayPunt.setVisible(false);
							awayFieldGoal.setVisible(false);
							awayExtraPoint.setVisible(false);
							awayTwoPoint.setVisible(false);
							homeKickoff.setVisible(false);
							homeOnsideKick.setVisible(false);
							homePunt.setVisible(false);
							homeFieldGoal.setVisible(false);
							homeExtraPoint.setVisible(false);
							homeTwoPoint.setVisible(false);
							awayPlay1.setVisible(false);
							awayPlay2.setVisible(false);
							awayPlay3.setVisible(false);
							awayPlay4.setVisible(false);
							awayPlay5.setVisible(false);
							homePlay1.setVisible(false);
							homePlay2.setVisible(false);
							homePlay3.setVisible(false);
							homePlay4.setVisible(false);
							homePlay5.setVisible(false);
							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
							returnToMainMenu.setBackground(Color.BLACK);
							returnToMainMenu.setForeground(Color.WHITE);
							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
							returnToMainMenu.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									mainMenu.mainMenu();
								}
							});
							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
						}
    					else {
    						kickoff(false, frame, panel);;
    					}
	        		}
	        		else {
	    				down = 1;
	    				yardsToGo = 10;
	    				downs.setText("1st & 10");
	    				awayPossession.setVisible(true);
	    				homePossession.setVisible(false);
	    				
	        			double j = Math.random();
	        			if (j < 0.8) {
	        				homeSideOfField = false;
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 53 yard field goal is NO GOOD!");
	        				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(false, frame, panel);
                			}
                			else {
                				play(frame, panel);
                			}
	        			}
	        			else if (j < 0.95) {
	        				yardLine += 10;
	        				if (yardLine < 50) {
	        					homeSideOfField = false;
	        					position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
	        				}
	        				else if (yardLine == 50) {
	        					homeSideOfField = true;
	        					position.setText("" + yardLine);
	        				}
	        				else {
	        					yardLine = 100 - yardLine;
	        					homeSideOfField = true;
	        					position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
	        				}
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 53 yard field goal is BLOCKED and NO GOOD!");
	        				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(false, frame, panel);
                			}
                			else {
                				play(frame, panel);
                			}
	        			}
	        			else {
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 53 yard field goal is NO GOOD, AND RETURNED FOR A TOUCHDOWN BY "
	        						+ awayTeam.getTeamAbbr() + "!");
	        				touchdown(true, frame, panel);
	        			}
	        		}
	    		}
	    		else if (yardLine == 40) {
	    			double i = Math.random();
	        		if (i < 0.35) {
	        			homeScore += 3;
	        			homePoints.setText("" + homeScore);
	        			lastPlay.setText(homeTeam.getTeamAbbr() + " 58 yard field goal is GOOD!");
	        			if (overtimeNow && overtimeAwayPoss && homeTeam instanceof NFLTeam && awayScore != homeScore) {
							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
							quarter.setText("FINAL");
							awayTimeout1.setVisible(false);
							awayTimeout2.setVisible(false);
							awayTimeout3.setVisible(false);
							homeTimeout1.setVisible(false);
							homeTimeout2.setVisible(false);
							homeTimeout3.setVisible(false);
							awaySim.setVisible(false);
							homeSim.setVisible(false);
							awayKickoff.setVisible(false);
							awayOnsideKick.setVisible(false);
							awayPunt.setVisible(false);
							awayFieldGoal.setVisible(false);
							awayExtraPoint.setVisible(false);
							awayTwoPoint.setVisible(false);
							homeKickoff.setVisible(false);
							homeOnsideKick.setVisible(false);
							homePunt.setVisible(false);
							homeFieldGoal.setVisible(false);
							homeExtraPoint.setVisible(false);
							homeTwoPoint.setVisible(false);
							awayPlay1.setVisible(false);
							awayPlay2.setVisible(false);
							awayPlay3.setVisible(false);
							awayPlay4.setVisible(false);
							awayPlay5.setVisible(false);
							homePlay1.setVisible(false);
							homePlay2.setVisible(false);
							homePlay3.setVisible(false);
							homePlay4.setVisible(false);
							homePlay5.setVisible(false);
							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
							returnToMainMenu.setBackground(Color.BLACK);
							returnToMainMenu.setForeground(Color.WHITE);
							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
							returnToMainMenu.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									mainMenu.mainMenu();
								}
							});
							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
						}
    					else {
    						kickoff(false, frame, panel);;
    					}
	        		}
	        		else {
	    				down = 1;
	    				yardsToGo = 10;
	    				downs.setText("1st & 10");
	    				awayPossession.setVisible(true);
	    				homePossession.setVisible(false);
	    				
	        			double j = Math.random();
	        			if (j < 0.8) {
	        				homeSideOfField = false;
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 58 yard field goal is NO GOOD!");
	        				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(false, frame, panel);
                			}
                			else {
                				play(frame, panel);
                			}
	        			}
	        			else if (j < 0.95) {
	        				yardLine += 10;
	        				if (yardLine < 50) {
	        					homeSideOfField = false;
	        					position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
	        				}
	        				else if (yardLine == 50) {
	        					homeSideOfField = true;
	        					position.setText("" + yardLine);
	        				}
	        				else {
	        					yardLine = 100 - yardLine;
	        					homeSideOfField = true;
	        					position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
	        				}
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 58 yard field goal is BLOCKED and NO GOOD!");
	        				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(false, frame, panel);
                			}
                			else {
                				play(frame, panel);
                			}
	        			}
	        			else {
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 58 yard field goal is NO GOOD, AND RETURNED FOR A TOUCHDOWN BY "
	        						+ awayTeam.getTeamAbbr() + "!");
	        				touchdown(true, frame, panel);
	        			}
	        		}
	    		}
	    		else if (yardLine == 45) {
	    			double i = Math.random();
	        		if (i < 0.2) {
	        			homeScore += 3;
	        			homePoints.setText("" + homeScore);
	        			lastPlay.setText(homeTeam.getTeamAbbr() + " 63 yard field goal is GOOD!");
	        			if (overtimeNow && overtimeAwayPoss && homeTeam instanceof NFLTeam && awayScore != homeScore) {
							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
							quarter.setText("FINAL");
							awayTimeout1.setVisible(false);
							awayTimeout2.setVisible(false);
							awayTimeout3.setVisible(false);
							homeTimeout1.setVisible(false);
							homeTimeout2.setVisible(false);
							homeTimeout3.setVisible(false);
							awaySim.setVisible(false);
							homeSim.setVisible(false);
							awayKickoff.setVisible(false);
							awayOnsideKick.setVisible(false);
							awayPunt.setVisible(false);
							awayFieldGoal.setVisible(false);
							awayExtraPoint.setVisible(false);
							awayTwoPoint.setVisible(false);
							homeKickoff.setVisible(false);
							homeOnsideKick.setVisible(false);
							homePunt.setVisible(false);
							homeFieldGoal.setVisible(false);
							homeExtraPoint.setVisible(false);
							homeTwoPoint.setVisible(false);
							awayPlay1.setVisible(false);
							awayPlay2.setVisible(false);
							awayPlay3.setVisible(false);
							awayPlay4.setVisible(false);
							awayPlay5.setVisible(false);
							homePlay1.setVisible(false);
							homePlay2.setVisible(false);
							homePlay3.setVisible(false);
							homePlay4.setVisible(false);
							homePlay5.setVisible(false);
							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
							returnToMainMenu.setBackground(Color.BLACK);
							returnToMainMenu.setForeground(Color.WHITE);
							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
							returnToMainMenu.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									mainMenu.mainMenu();
								}
							});
							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
						}
    					else {
    						kickoff(false, frame, panel);;
    					}
	        		}
	        		else {
	    				down = 1;
	    				yardsToGo = 10;
	    				downs.setText("1st & 10");
	    				awayPossession.setVisible(true);
	    				homePossession.setVisible(false);
	    				
	        			double j = Math.random();
	        			if (j < 0.8) {
	        				homeSideOfField = false;
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 63 yard field goal is NO GOOD!");
	        				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(false, frame, panel);
                			}
                			else {
                				play(frame, panel);
                			}
	        			}
	        			else if (j < 0.95) {
	        				yardLine += 10;
	        				if (yardLine < 50) {
	        					homeSideOfField = false;
	        					position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
	        				}
	        				else if (yardLine == 50) {
	        					homeSideOfField = true;
	        					position.setText("" + yardLine);
	        				}
	        				else {
	        					yardLine = 100 - yardLine;
	        					homeSideOfField = true;
	        					position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
	        				}
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 63 yard field goal is BLOCKED and NO GOOD!");
	        				if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
                				kickoff(false, frame, panel);
                			}
                			else {
                				play(frame, panel);
                			}
	        			}
	        			else {
	        				lastPlay.setText(homeTeam.getTeamAbbr() + " 63 yard field goal is NO GOOD, AND RETURNED FOR A TOUCHDOWN BY "
	        						+ awayTeam.getTeamAbbr() + "!");
	        				touchdown(true, frame, panel);
	        			}
	        		}
	    		}
	        }
	    });
        panel.add(homeFieldGoal);
        
        homeExtraPoint = new JButton("Extra Point");
        homeExtraPoint.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
        homeExtraPoint.setBackground(homeTeam.getPrimaryColor());
        homeExtraPoint.setForeground(homeTeam.getSecondaryColor());
        homeExtraPoint.setBounds(frame.getWidth() * 9 / 15, 430, frame.getWidth() * 2 / 15, 50);
        homeExtraPoint.setEnabled(false);
        homeExtraPoint.setVisible(false);
        homeExtraPoint.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		double i = Math.random();
        		if (i < 0.9) {
        			homeScore++;
        			homePoints.setText("" + homeScore);
        			lastPlay.setText(homeTeam.getTeamAbbr() + " extra point is GOOD!");
        		}
        		else {
        			double j = Math.random();
        			if (j < 0.95) {
        				lastPlay.setText(homeTeam.getTeamAbbr() + " extra point is NO GOOD!");
        			}
        			else {
        				awayScore += 2;
        				awayPoints.setText("" + awayScore);
        				lastPlay.setText(homeTeam.getTeamAbbr() + " extra point is NO GOOD, AND RETURNED FOR 2 POINTS BY "
        						+ awayTeam.getTeamAbbr() + "!");
        			}
        		}
        		kickoff(false, frame, panel);;
            }
        });
        panel.add(homeExtraPoint);
        
        homeTwoPoint = new JButton("Two-Point");
        homeTwoPoint.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
        homeTwoPoint.setBackground(homeTeam.getPrimaryColor());
        homeTwoPoint.setForeground(homeTeam.getSecondaryColor());
        homeTwoPoint.setBounds(frame.getWidth() * 9 / 15, 480, frame.getWidth() * 2 / 15, 50);
        homeTwoPoint.setEnabled(false);
        homeTwoPoint.setVisible(false);
        homeTwoPoint.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		double i = Math.random();
        		if (i < 0.45) {
        			homeScore += 2;
        			homePoints.setText("" + homeScore);
        			lastPlay.setText(homeTeam.getTeamAbbr() + " two point conversion is GOOD!");
        		}
        		else {
        			double j = Math.random();
        			if (j < 0.95) {
        				lastPlay.setText(homeTeam.getTeamAbbr() + " two point conversion is NO GOOD!");
        			}
        			else {
        				awayScore += 2;
        				awayPoints.setText("" + awayScore);
        				lastPlay.setText(homeTeam.getTeamAbbr() + " two point conversion is NO GOOD, AND RETURNED FOR 2 POINTS BY "
        						+ awayTeam.getTeamAbbr() + "!");
        			}
        		}
        		kickoff(false, frame, panel);;
            }
        });
        panel.add(homeTwoPoint);
        
        JButton playByPlay = new JButton("Play-by-Play");
        playByPlay.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 22));
        playByPlay.setBackground(Color.BLACK);
        playByPlay.setForeground(Color.WHITE);
        playByPlay.setBounds(frame.getWidth() * 2 / 5, 130, frame.getWidth() / 10, 40);
        playByPlay.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JWindow window = new JWindow();
        		window.setBounds(frame.getWidth() / 4, 230, frame.getWidth() / 2, frame.getHeight() / 2 + 25);
        		JPanel panel = new JPanel();
        		panel.setLayout(null);
        		JButton close = new JButton("Close");
        		close.setBounds(0, 0, window.getWidth(), 50);
        		close.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 24));
        		close.setBackground(Color.RED);
        		close.setForeground(Color.WHITE);
        		close.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		window.setVisible(false);
                	}
        		});
        		panel.add(close);
        		JTextArea textArea = new JTextArea();
        		textArea.setBounds(0, 50, window.getWidth(), frame.getHeight() / 2 - 25);
        		textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 24));
        		textArea.setBackground(Color.WHITE);
        		textArea.setForeground(Color.BLACK);
        		StringBuilder sb = new StringBuilder();
        		for (JavaFootballPlay play : playByPlayList) {
        			sb.append(play.toString());
        			sb.append("\n");
        		}
        		textArea.setText(sb.toString());
        		System.out.println(sb.toString());
        		textArea.setLineWrap(true);
        		textArea.setWrapStyleWord(true);
        		textArea.setCaretPosition(0);
        		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        		scrollPane.setBounds(0, 50, window.getWidth(), frame.getHeight() / 2 - 25);
        		panel.add(scrollPane);
        		window.setContentPane(panel);
        		window.setVisible(true);
        	}
        });
        panel.add(playByPlay);
        
        JButton gameStats = new JButton("Game Stats");
        gameStats.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 24));
        gameStats.setBackground(Color.BLACK);
        gameStats.setForeground(Color.WHITE);
        gameStats.setBounds(frame.getWidth() / 2, 130, frame.getWidth() / 10, 40);
        gameStats.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
        		JWindow window = new JWindow();
	    		window.setBounds(frame.getWidth() / 4, 230, frame.getWidth() / 2, frame.getHeight() / 2 + 25);
	    		JPanel panel = new JPanel();
	    		panel.setLayout(null);
	    		JButton close = new JButton("Close");
	    		close.setBounds(0, 0, window.getWidth(), 50);
	    		close.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 24));
	    		close.setBackground(Color.RED);
	    		close.setForeground(Color.WHITE);
	    		close.addActionListener(new ActionListener() {
	            	public void actionPerformed(ActionEvent e) {
	            		window.setVisible(false);
	            	}
	    		});
	    		panel.add(close);
	    		JTextArea textArea = new JTextArea();
	    		textArea.setBounds(0, 50, window.getWidth(), frame.getHeight() / 2 - 25);
	    		textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 24));
	    		textArea.setBackground(Color.WHITE);
	    		textArea.setForeground(Color.BLACK);
	    		FootballStats stats = new FootballStats(awayTeam.getTeamAbbr(), homeTeam.getTeamAbbr());
	    		stats.updateStats(playByPlayList);
	    		textArea.setText(stats.toString());
	    		System.out.println(stats.toString());
	    		textArea.setLineWrap(true);
	    		textArea.setWrapStyleWord(true);
	    		textArea.setCaretPosition(0);
	    		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    		scrollPane.setBounds(0, 50, window.getWidth(), frame.getHeight() / 2 - 25);
	    		panel.add(scrollPane);
	    		window.setContentPane(panel);
	    		window.setVisible(true);
	        }
        });
        panel.add(gameStats);
        
        JLabel awayEndzone;
        if (neutral) {
        	awayEndzone = new JLabel(awayTeam.getTeamAbbr(), JLabel.CENTER);
        	awayEndzone.setBackground(awayTeam.getPrimaryColor());
        	awayEndzone.setForeground(awayTeam.getSecondaryColor());
        }
        else {
        	awayEndzone = new JLabel(homeTeam.getTeamAbbr(), JLabel.CENTER);
        	awayEndzone.setBackground(homeTeam.getPrimaryColor());
        	awayEndzone.setForeground(homeTeam.getSecondaryColor());
        }
        awayEndzone.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
        awayEndzone.setBounds(0, frame.getHeight() * 2 / 3, frame.getWidth() / 12, frame.getHeight() / 3);
        awayEndzone.setOpaque(true);
        panel.add(awayEndzone);
        
        JLabel homeEndzone = new JLabel(homeTeam.getTeamAbbr(), JLabel.CENTER);
        homeEndzone.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
        homeEndzone.setBackground(homeTeam.getPrimaryColor());
        homeEndzone.setForeground(homeTeam.getSecondaryColor());
        homeEndzone.setBounds(frame.getWidth() * 11 / 12, frame.getHeight() * 2 / 3, frame.getWidth() / 12, frame.getHeight() / 3);
        homeEndzone.setOpaque(true);
        panel.add(homeEndzone);
        
        field = new JPanel();
        field.setLayout(null);
        field.setBackground(new Color(0, 150, 0));
        field.setBounds(frame.getWidth() / 12, frame.getHeight() * 2 / 3, frame.getWidth() * 5 / 6, frame.getHeight() / 3);
        panel.add(field);
        
        yardMarkers = new JButton[21];
        for (int i = 0; i <= 100; i++) {
        	JButton yardMarker = new JButton();
        	yardMarker.setBackground(Color.WHITE);
        	if (i % 5 == 0) {
        		yardMarker.setBounds(field.getWidth() * i / 100 - 2, 0, 4, field.getHeight());
        	}
        	else {
        		yardMarker.setBounds(field.getWidth() * i / 100 - 2, field.getHeight() / 4, 4, field.getHeight() / 4);
        	}
        	yardMarker.setEnabled(false);
            field.add(yardMarker);
            if (i % 5 == 0) {
            	yardMarkers[i / 5] = yardMarker;
            }
            
            if (i % 10 == 0 && i != 0 && i != 100) {
            	int yardMark;
            	if (i <= 50) {
            		yardMark = i;
            	}
            	else {
            		yardMark = 100 - i; 
            	}
            	JLabel yardMarkText = new JLabel("" + yardMark, JLabel.CENTER);
            	yardMarkText.setFont(new Font(Font.MONOSPACED, Font.BOLD, 60));
            	yardMarkText.setForeground(Color.WHITE);
            	yardMarkText.setBounds(field.getWidth() * i / 100 - 50, field.getHeight() / 2, 100, 100);
            	field.add(yardMarkText);
            }
        }
        
        JLabel awayKickoffMark = new JLabel("X", JLabel.CENTER);
        awayKickoffMark.setFont(new Font(Font.MONOSPACED, Font.BOLD, 60));
        awayKickoffMark.setForeground(Color.WHITE);
        awayKickoffMark.setBounds(field.getWidth() * 35 / 100 - 30, field.getHeight() * 3 / 8 - 30, 60, 60);
        field.add(awayKickoffMark);
        
        JLabel homeKickoffMark = new JLabel("X", JLabel.CENTER);
        homeKickoffMark.setFont(new Font(Font.MONOSPACED, Font.BOLD, 60));
        homeKickoffMark.setForeground(Color.WHITE);
        homeKickoffMark.setBounds(field.getWidth() * 65 / 100 - 30, field.getHeight() * 3 / 8 - 30, 60, 60);
        field.add(homeKickoffMark);
        
        JLabel fieldLogo;
        if (neutral) {
        	fieldLogo = new JLabel("JAVA", JLabel.CENTER);
        	fieldLogo.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
        	fieldLogo.setBackground(Color.WHITE);
        	fieldLogo.setForeground(Color.BLACK);
        	fieldLogo.setOpaque(true);
        }
        else {
        	fieldLogo = new JLabel();
        	fieldLogo.setIcon(new ImageIcon(homeTeam.getLogo().getImage()
            		.getScaledInstance(field.getHeight() * 3 / 8, field.getHeight() / 4, Image.SCALE_DEFAULT)));
        }
        fieldLogo.setBounds(field.getWidth() / 2 - field.getHeight() * 3 / 16, field.getHeight() / 4, field.getHeight() * 3 / 8, field.getHeight() / 4);
        field.add(fieldLogo);
        
        football = new JLabel();
        football.setIcon(new ImageIcon("football.png"));
        football.setBounds(field.getWidth() / 2 - 49, field.getHeight() / 8 - 30, 98, 60);
        field.add(football);
        
        lastPlay = new JTextArea();
		lastPlay.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 35));
		lastPlay.setBackground(Color.BLACK);
		lastPlay.setForeground(Color.WHITE);
		lastPlay.setBounds(frame.getWidth() * 4 / 10, 170, frame.getWidth() / 5, 360);
		lastPlay.setLineWrap(true);
		lastPlay.setVisible(false);
		lastPlay.setWrapStyleWord(true);
		panel.add(lastPlay);
		
		awayPlay1 = new JButton();
		awayPlay1.setEnabled(false);
		awayPlay1.setVisible(false);
		awayPlay1.setBounds(0, 230, frame.getWidth() * 4 / 15, 60);
		awayPlay1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		updateTime();
        		int i = -1;
        		if (awayPossession.isVisible()) {
        			JavaFootballOffenseCard off = awayOffenseCards[0];
        			awayOffenseCards[0] = null;
        			JavaFootballDefenseCard def = null;
        			while (def == null) {
        				i = (int) (Math.random() * 5);
        				def = homeDefenseCards[i];
        			}
        			homeDefenseCards[i] = null;
        			int[] outcome = off.getOutcome(def.getName());
            		awayPlay1.setEnabled(false);
        			switch (i) {
    					case 0:
    						homePlay1.setEnabled(false);
    						break;
    					case 1:
    						homePlay2.setEnabled(false);
    						break;
    					case 2:
    						homePlay3.setEnabled(false);
    						break;
    					case 3:
    						homePlay4.setEnabled(false);
    						break;
    					case 4:
    						homePlay5.setEnabled(false);
    						break;
        			}
        			awayPlay(outcome, off, def, frame, panel);
        		}
        		else {
        			JavaFootballDefenseCard def = awayDefenseCards[0];
        			awayDefenseCards[0] = null;
        			JavaFootballOffenseCard off = null;
        			while (off == null) {
        				i = (int) (Math.random() * 5);
        				off = homeOffenseCards[i];
        			}
        			homeOffenseCards[i] = null;
        			int[] outcome = off.getOutcome(def.getName());
            		awayPlay1.setEnabled(false);
        			switch (i) {
    					case 0:
    						homePlay1.setEnabled(false);
    						break;
    					case 1:
    						homePlay2.setEnabled(false);
    						break;
    					case 2:
    						homePlay3.setEnabled(false);
    						break;
    					case 3:
    						homePlay4.setEnabled(false);
    						break;
    					case 4:
    						homePlay5.setEnabled(false);
    						break;
        			}
        			homePlay(outcome, off, def, frame, panel);
        		}
        	}
		});
		panel.add(awayPlay1);
		
		awayPlay2 = new JButton();
		awayPlay2.setEnabled(false);
		awayPlay2.setVisible(false);
		awayPlay2.setBounds(0, 290, frame.getWidth() * 4 / 15, 60);
		awayPlay2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		updateTime();
        		int i = -1;
        		if (awayPossession.isVisible()) {
        			JavaFootballOffenseCard off = awayOffenseCards[1];
        			awayOffenseCards[1] = null;
        			JavaFootballDefenseCard def = null;
        			while (def == null) {
        				i = (int) (Math.random() * 5);
        				def = homeDefenseCards[i];
        			}
        			homeDefenseCards[i] = null;
        			int[] outcome = off.getOutcome(def.getName());
            		awayPlay2.setEnabled(false);
        			switch (i) {
    					case 0:
    						homePlay1.setEnabled(false);
    						break;
    					case 1:
    						homePlay2.setEnabled(false);
    						break;
    					case 2:
    						homePlay3.setEnabled(false);
    						break;
    					case 3:
    						homePlay4.setEnabled(false);
    						break;
    					case 4:
    						homePlay5.setEnabled(false);
    						break;
        			}
        			awayPlay(outcome, off, def, frame, panel);
        		}
        		else {
        			JavaFootballDefenseCard def = awayDefenseCards[1];
        			awayDefenseCards[1] = null;
        			JavaFootballOffenseCard off = null;
        			while (off == null) {
        				i = (int) (Math.random() * 5);
        				off = homeOffenseCards[i];
        			}
        			homeOffenseCards[i] = null;
        			int[] outcome = off.getOutcome(def.getName());
            		awayPlay2.setEnabled(false);
        			switch (i) {
    					case 0:
    						homePlay1.setEnabled(false);
    						break;
    					case 1:
    						homePlay2.setEnabled(false);
    						break;
    					case 2:
    						homePlay3.setEnabled(false);
    						break;
    					case 3:
    						homePlay4.setEnabled(false);
    						break;
    					case 4:
    						homePlay5.setEnabled(false);
    						break;
        			}
        			homePlay(outcome, off, def, frame, panel);
        		}
        	}
		});
		panel.add(awayPlay2);
		
		awayPlay3 = new JButton();
		awayPlay3.setEnabled(false);
		awayPlay3.setVisible(false);
		awayPlay3.setBounds(0, 350, frame.getWidth() * 4 / 15, 60);
		awayPlay3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		updateTime();
        		int i = -1;
        		if (awayPossession.isVisible()) {
        			JavaFootballOffenseCard off = awayOffenseCards[2];
        			awayOffenseCards[2] = null;
        			JavaFootballDefenseCard def = null;
        			while (def == null) {
        				i = (int) (Math.random() * 5);
        				def = homeDefenseCards[i];
        			}
        			homeDefenseCards[i] = null;
        			int[] outcome = off.getOutcome(def.getName());
            		awayPlay3.setEnabled(false);
        			switch (i) {
    					case 0:
    						homePlay1.setEnabled(false);
    						break;
    					case 1:
    						homePlay2.setEnabled(false);
    						break;
    					case 2:
    						homePlay3.setEnabled(false);
    						break;
    					case 3:
    						homePlay4.setEnabled(false);
    						break;
    					case 4:
    						homePlay5.setEnabled(false);
    						break;
        			}
        			awayPlay(outcome, off, def, frame, panel);
        		}
        		else {
        			JavaFootballDefenseCard def = awayDefenseCards[2];
        			awayDefenseCards[2] = null;
        			JavaFootballOffenseCard off = null;
        			while (off == null) {
        				i = (int) (Math.random() * 5);
        				off = homeOffenseCards[i];
        			}
        			homeOffenseCards[i] = null;
        			int[] outcome = off.getOutcome(def.getName());
            		awayPlay3.setEnabled(false);
        			switch (i) {
    					case 0:
    						homePlay1.setEnabled(false);
    						break;
    					case 1:
    						homePlay2.setEnabled(false);
    						break;
    					case 2:
    						homePlay3.setEnabled(false);
    						break;
    					case 3:
    						homePlay4.setEnabled(false);
    						break;
    					case 4:
    						homePlay5.setEnabled(false);
    						break;
        			}
        			homePlay(outcome, off, def, frame, panel);
        		}
        	}
		});
		panel.add(awayPlay3);
		
		awayPlay4 = new JButton();
		awayPlay4.setEnabled(false);
		awayPlay4.setVisible(false);
		awayPlay4.setBounds(0, 410, frame.getWidth() * 4 / 15, 60);
		awayPlay4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		updateTime();
        		int i = -1;
        		if (awayPossession.isVisible()) {
        			JavaFootballOffenseCard off = awayOffenseCards[3];
        			awayOffenseCards[3] = null;
        			JavaFootballDefenseCard def = null;
        			while (def == null) {
        				i = (int) (Math.random() * 5);
        				def = homeDefenseCards[i];
        			}
        			homeDefenseCards[i] = null;
        			int[] outcome = off.getOutcome(def.getName());
        			awayPlay4.setEnabled(false);
        			switch (i) {
    					case 0:
    						homePlay1.setEnabled(false);
    						break;
    					case 1:
    						homePlay2.setEnabled(false);
    						break;
    					case 2:
    						homePlay3.setEnabled(false);
    						break;
    					case 3:
    						homePlay4.setEnabled(false);
    						break;
    					case 4:
    						homePlay5.setEnabled(false);
    						break;
        			}
        			awayPlay(outcome, off, def, frame, panel);
        		}
        		else {
        			JavaFootballDefenseCard def = awayDefenseCards[3];
        			awayDefenseCards[3] = null;
        			JavaFootballOffenseCard off = null;
        			while (off == null) {
        				i = (int) (Math.random() * 5);
        				off = homeOffenseCards[i];
        			}
        			homeOffenseCards[i] = null;
        			int[] outcome = off.getOutcome(def.getName());
        			awayPlay4.setEnabled(false);
        			switch (i) {
    					case 0:
    						homePlay1.setEnabled(false);
    						break;
    					case 1:
    						homePlay2.setEnabled(false);
    						break;
    					case 2:
    						homePlay3.setEnabled(false);
    						break;
    					case 3:
    						homePlay4.setEnabled(false);
    						break;
    					case 4:
    						homePlay5.setEnabled(false);
    						break;
        			}
        			homePlay(outcome, off, def, frame, panel);
        		}
        	}
		});
		panel.add(awayPlay4);
		
		awayPlay5 = new JButton();
		awayPlay5.setEnabled(false);
		awayPlay5.setVisible(false);
		awayPlay5.setBounds(0, 470, frame.getWidth() * 4 / 15, 60);
		awayPlay5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		updateTime();
        		int i = -1;
        		if (awayPossession.isVisible()) {
        			JavaFootballOffenseCard off = awayOffenseCards[4];
        			awayOffenseCards[4] = null;
        			JavaFootballDefenseCard def = null;
        			while (def == null) {
        				i = (int) (Math.random() * 5);
        				def = homeDefenseCards[i];
        			}
        			homeDefenseCards[i] = null;
        			int[] outcome = off.getOutcome(def.getName());
        			awayPlay5.setEnabled(false);
        			switch (i) {
    					case 0:
    						homePlay1.setEnabled(false);
    						break;
    					case 1:
    						homePlay2.setEnabled(false);
    						break;
    					case 2:
    						homePlay3.setEnabled(false);
    						break;
    					case 3:
    						homePlay4.setEnabled(false);
    						break;
    					case 4:
    						homePlay5.setEnabled(false);
    						break;
        			}
        			awayPlay(outcome, off, def, frame, panel);
        		}
        		else {
        			JavaFootballDefenseCard def = awayDefenseCards[4];
        			awayDefenseCards[4] = null;
        			JavaFootballOffenseCard off = null;
        			while (off == null) {
        				i = (int) (Math.random() * 5);
        				off = homeOffenseCards[i];
        			}
        			homeOffenseCards[i] = null;
        			int[] outcome = off.getOutcome(def.getName());
        			awayPlay5.setEnabled(false);
        			switch (i) {
    					case 0:
    						homePlay1.setEnabled(false);
    						break;
    					case 1:
    						homePlay2.setEnabled(false);
    						break;
    					case 2:
    						homePlay3.setEnabled(false);
    						break;
    					case 3:
    						homePlay4.setEnabled(false);
    						break;
    					case 4:
    						homePlay5.setEnabled(false);
    						break;
        			}
        			homePlay(outcome, off, def, frame, panel);
        		}
        	}
		});
		panel.add(awayPlay5);
		
		homePlay1 = new JButton();
		homePlay1.setEnabled(false);
		homePlay1.setVisible(false);
		homePlay1.setBounds(frame.getWidth() * 11 / 15, 230, frame.getWidth() * 4 / 15, 60);
		homePlay1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		updateTime();
        		int i = -1;
        		if (homePossession.isVisible()) {
        			JavaFootballOffenseCard off = homeOffenseCards[0];
        			homeOffenseCards[0] = null;
        			JavaFootballDefenseCard def = null;
        			while (def == null) {
        				i = (int) (Math.random() * 5);
        				def = awayDefenseCards[i];
        			}
        			awayDefenseCards[i] = null;
        			int[] outcome = off.getOutcome(def.getName());
            		homePlay1.setEnabled(false);
        			switch (i) {
    					case 0:
    						awayPlay1.setEnabled(false);
    						break;
    					case 1:
    						awayPlay2.setEnabled(false);
    						break;
    					case 2:
    						awayPlay3.setEnabled(false);
    						break;
    					case 3:
    						awayPlay4.setEnabled(false);
    						break;
    					case 4:
    						awayPlay5.setEnabled(false);
    						break;
        			}
        			homePlay(outcome, off, def, frame, panel);
        		}
        		else {
        			JavaFootballDefenseCard def = homeDefenseCards[0];
        			homeDefenseCards[0] = null;
        			JavaFootballOffenseCard off = null;
        			while (off == null) {
        				i = (int) (Math.random() * 5);
        				off = awayOffenseCards[i];
        			}
        			awayOffenseCards[i] = null;
        			int[] outcome = off.getOutcome(def.getName());
            		homePlay1.setEnabled(false);
        			switch (i) {
    					case 0:
    						awayPlay1.setEnabled(false);
    						break;
    					case 1:
    						awayPlay2.setEnabled(false);
    						break;
    					case 2:
    						awayPlay3.setEnabled(false);
    						break;
    					case 3:
    						awayPlay4.setEnabled(false);
    						break;
    					case 4:
    						awayPlay5.setEnabled(false);
    						break;
        			}
        			awayPlay(outcome, off, def, frame, panel);
        		}
        	}
		});
		panel.add(homePlay1);
		
		homePlay2 = new JButton();
		homePlay2.setEnabled(false);
		homePlay2.setVisible(false);
		homePlay2.setBounds(frame.getWidth() * 11 / 15, 290, frame.getWidth() * 4 / 15, 60);
		homePlay2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		updateTime();
        		int i = -1;
        		if (homePossession.isVisible()) {
        			JavaFootballOffenseCard off = homeOffenseCards[1];
        			homeOffenseCards[1] = null;
        			JavaFootballDefenseCard def = null;
        			while (def == null) {
        				i = (int) (Math.random() * 5);
        				def = awayDefenseCards[i];
        			}
        			awayDefenseCards[i] = null;
        			int[] outcome = off.getOutcome(def.getName());
        			homePlay2.setEnabled(false);
        			switch (i) {
    					case 0:
    						awayPlay1.setEnabled(false);
    						break;
    					case 1:
    						awayPlay2.setEnabled(false);
    						break;
    					case 2:
    						awayPlay3.setEnabled(false);
    						break;
    					case 3:
    						awayPlay4.setEnabled(false);
    						break;
    					case 4:
    						awayPlay5.setEnabled(false);
    						break;
        			}
        			homePlay(outcome, off, def, frame, panel);
        		}
        		else {
        			JavaFootballDefenseCard def = homeDefenseCards[1];
        			homeDefenseCards[1] = null;
        			JavaFootballOffenseCard off = null;
        			while (off == null) {
        				i = (int) (Math.random() * 5);
        				off = awayOffenseCards[i];
        			}
        			awayOffenseCards[i] = null;
        			int[] outcome = off.getOutcome(def.getName());
            		homePlay2.setEnabled(false);
        			switch (i) {
    					case 0:
    						awayPlay1.setEnabled(false);
    						break;
    					case 1:
    						awayPlay2.setEnabled(false);
    						break;
    					case 2:
    						awayPlay3.setEnabled(false);
    						break;
    					case 3:
    						awayPlay4.setEnabled(false);
    						break;
    					case 4:
    						awayPlay5.setEnabled(false);
    						break;
        			}
        			awayPlay(outcome, off, def, frame, panel);
        		}
        	}
		});
		panel.add(homePlay2);
		
		homePlay3 = new JButton();
		homePlay3.setEnabled(false);
		homePlay3.setVisible(false);
		homePlay3.setBounds(frame.getWidth() * 11 / 15, 350, frame.getWidth() * 4 / 15, 60);
		homePlay3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		updateTime();
        		int i = -1;
        		if (homePossession.isVisible()) {
        			JavaFootballOffenseCard off = homeOffenseCards[2];
        			homeOffenseCards[2] = null;
        			JavaFootballDefenseCard def = null;
        			while (def == null) {
        				i = (int) (Math.random() * 5);
        				def = awayDefenseCards[i];
        			}
        			awayDefenseCards[i] = null;
        			int[] outcome = off.getOutcome(def.getName());
        			homePlay3.setEnabled(false);
        			switch (i) {
    					case 0:
    						awayPlay1.setEnabled(false);
    						break;
    					case 1:
    						awayPlay2.setEnabled(false);
    						break;
    					case 2:
    						awayPlay3.setEnabled(false);
    						break;
    					case 3:
    						awayPlay4.setEnabled(false);
    						break;
    					case 4:
    						awayPlay5.setEnabled(false);
    						break;
        			}
        			homePlay(outcome, off, def, frame, panel);
        		}
        		else {
        			JavaFootballDefenseCard def = homeDefenseCards[2];
        			homeDefenseCards[2] = null;
        			JavaFootballOffenseCard off = null;
        			while (off == null) {
        				i = (int) (Math.random() * 5);
        				off = awayOffenseCards[i];
        			}
        			awayOffenseCards[i] = null;
        			int[] outcome = off.getOutcome(def.getName());
        			homePlay3.setEnabled(false);
        			switch (i) {
    					case 0:
    						awayPlay1.setEnabled(false);
    						break;
    					case 1:
    						awayPlay2.setEnabled(false);
    						break;
    					case 2:
    						awayPlay3.setEnabled(false);
    						break;
    					case 3:
    						awayPlay4.setEnabled(false);
    						break;
    					case 4:
    						awayPlay5.setEnabled(false);
    						break;
        			}
        			awayPlay(outcome, off, def, frame, panel);
        		}
        	}
		});
		panel.add(homePlay3);
		
		homePlay4 = new JButton();
		homePlay4.setEnabled(false);
		homePlay4.setVisible(false);
		homePlay4.setBounds(frame.getWidth() * 11 / 15, 410, frame.getWidth() * 4 / 15, 60);
		homePlay4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		updateTime();
        		int i = -1;
        		if (homePossession.isVisible()) {
        			JavaFootballOffenseCard off = homeOffenseCards[3];
        			homeOffenseCards[3] = null;
        			JavaFootballDefenseCard def = null;
        			while (def == null) {
        				i = (int) (Math.random() * 5);
        				def = awayDefenseCards[i];
        			}
        			awayDefenseCards[i] = null;
        			int[] outcome = off.getOutcome(def.getName());
        			homePlay4.setEnabled(false);
        			switch (i) {
    					case 0:
    						awayPlay1.setEnabled(false);
    						break;
    					case 1:
    						awayPlay2.setEnabled(false);
    						break;
    					case 2:
    						awayPlay3.setEnabled(false);
    						break;
    					case 3:
    						awayPlay4.setEnabled(false);
    						break;
    					case 4:
    						awayPlay5.setEnabled(false);
    						break;
        			}
        			homePlay(outcome, off, def, frame, panel);
        		}
        		else {
        			JavaFootballDefenseCard def = homeDefenseCards[3];
        			homeDefenseCards[3] = null;
        			JavaFootballOffenseCard off = null;
        			while (off == null) {
        				i = (int) (Math.random() * 5);
        				off = awayOffenseCards[i];
        			}
        			awayOffenseCards[i] = null;
        			int[] outcome = off.getOutcome(def.getName());
        			homePlay4.setEnabled(false);
        			switch (i) {
    					case 0:
    						awayPlay1.setEnabled(false);
    						break;
    					case 1:
    						awayPlay2.setEnabled(false);
    						break;
    					case 2:
    						awayPlay3.setEnabled(false);
    						break;
    					case 3:
    						awayPlay4.setEnabled(false);
    						break;
    					case 4:
    						awayPlay5.setEnabled(false);
    						break;
        			}
        			awayPlay(outcome, off, def, frame, panel);
        		}
        	}
		});
		panel.add(homePlay4);
		
		homePlay5 = new JButton();
		homePlay5.setEnabled(false);
		homePlay5.setVisible(false);
		homePlay5.setBounds(frame.getWidth() * 11 / 15, 470, frame.getWidth() * 4 / 15, 60);
		homePlay5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		updateTime();
        		int i = -1;
        		if (homePossession.isVisible()) {
        			JavaFootballOffenseCard off = homeOffenseCards[4];
        			homeOffenseCards[4] = null;
        			JavaFootballDefenseCard def = null;
        			while (def == null) {
        				i = (int) (Math.random() * 5);
        				def = awayDefenseCards[i];
        			}
        			awayDefenseCards[i] = null;
        			int[] outcome = off.getOutcome(def.getName());
        			homePlay5.setEnabled(false);
        			switch (i) {
    					case 0:
    						awayPlay1.setEnabled(false);
    						break;
    					case 1:
    						awayPlay2.setEnabled(false);
    						break;
    					case 2:
    						awayPlay3.setEnabled(false);
    						break;
    					case 3:
    						awayPlay4.setEnabled(false);
    						break;
    					case 4:
    						awayPlay5.setEnabled(false);
    						break;
        			}
        			homePlay(outcome, off, def, frame, panel);
        		}
        		else {
        			JavaFootballDefenseCard def = homeDefenseCards[4];
        			homeDefenseCards[4] = null;
        			JavaFootballOffenseCard off = null;
        			while (off == null) {
        				i = (int) (Math.random() * 5);
        				off = awayOffenseCards[i];
        			}
        			awayOffenseCards[i] = null;
        			int[] outcome = off.getOutcome(def.getName());
        			homePlay5.setEnabled(false);
        			switch (i) {
    					case 0:
    						awayPlay1.setEnabled(false);
    						break;
    					case 1:
    						awayPlay2.setEnabled(false);
    						break;
    					case 2:
    						awayPlay3.setEnabled(false);
    						break;
    					case 3:
    						awayPlay4.setEnabled(false);
    						break;
    					case 4:
    						awayPlay5.setEnabled(false);
    						break;
        			}
        			awayPlay(outcome, off, def, frame, panel);
        		}
        	}
		});
		panel.add(homePlay5);
		
		awaySim = new JButton("SIM");
		awaySim.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 100));
		awaySim.setBackground(Color.BLACK);
		awaySim.setForeground(Color.WHITE);
		awaySim.setEnabled(true);
		awaySim.setVisible(false);
		awaySim.setBounds(0, 230, frame.getWidth() * 2 / 5, 300);
		panel.add(awaySim);
		
		homeSim = new JButton("SIM");
		homeSim.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 100));
		homeSim.setBackground(Color.BLACK);
		homeSim.setForeground(Color.WHITE);
		homeSim.setEnabled(true);
		homeSim.setVisible(false);
		homeSim.setBounds(frame.getWidth() * 3 / 5, 230, frame.getWidth() * 2 / 5, 300);
		panel.add(homeSim);
		
		lastPlay.getDocument().addDocumentListener(new DocumentListener() {
			
			public void changedUpdate(DocumentEvent e) {
				
			}
			
			public void insertUpdate(DocumentEvent e) {
				if (lastPlay.getText().contains("\n")) {
					if (lastPlay.getText().contains("Timeout")) {
						
					}
					else {
						playByPlayList.remove(playByPlayList.size() - 1);
						playByPlayList.add(new JavaFootballPlayResult(awayTeam, homeTeam, lastPlay.getText()));
					}
				}
				else {
					playByPlayList.add(new JavaFootballPlayResult(awayTeam, homeTeam, lastPlay.getText()));
				}
			}
			
			public void removeUpdate(DocumentEvent e) {
				
			}
        	
        });
		
		frame.setContentPane(panel);
		frame.revalidate();
		
		coinToss(frame, panel);
	}
	
	// Performs a coin toss
	private void coinToss(JFrame frame, JPanel panel) {
		
		if (!overtimeNow) {
			timeLeft = "15:00";
			time.setText(timeLeft);
		}
		else if (homeTeam instanceof NFLTeam) {
			timeLeft = "10:00";
			time.setText(timeLeft);
		}
		else {
			time.setText("OT");
		}
		
		boolean actualHeads = Math.random() < 0.5;
		
		if (awayHuman) {
			JLabel flipCoin = new JLabel(awayTeam.getTeamAbbr() + ", call heads or tails.", JLabel.CENTER);
			flipCoin.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
			flipCoin.setBackground(awayTeam.getPrimaryColor());
			flipCoin.setForeground(awayTeam.getSecondaryColor());
			flipCoin.setBounds(frame.getWidth() / 8, frame.getHeight() / 4, frame.getWidth() * 3 / 4, frame.getHeight() / 8);
			flipCoin.setOpaque(true);
        	panel.add(flipCoin);
        	
        	JButton heads = new JButton("Heads");
        	heads.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
        	heads.setBackground(Color.BLACK);
        	heads.setForeground(Color.WHITE);
        	heads.setBounds(frame.getWidth() * 3 / 16, frame.getHeight() * 3 / 8, frame.getWidth() / 4, frame.getHeight() / 8);
        	panel.add(heads);
        	
        	JButton tails = new JButton("Tails");
        	tails.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
        	tails.setBackground(Color.BLACK);
        	tails.setForeground(Color.WHITE);
        	tails.setBounds(frame.getWidth() * 9 / 16, frame.getHeight() * 3 / 8, frame.getWidth() / 4, frame.getHeight() / 8);
        	panel.add(tails);
        	
        	flipCoin.setVisible(true);
        	heads.setVisible(true);
        	tails.setVisible(true);
    		
        	heads.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		boolean calledHeads = true;
            		flipCoin.setVisible(false);
            		heads.setVisible(false);
            		tails.setVisible(false);
            		if (actualHeads == calledHeads) {
            			winnerCoinToss(frame, panel, true, calledHeads, actualHeads);
            		}
            		else {
            			winnerCoinToss(frame, panel, false, calledHeads, actualHeads);
            		}
                }
            });
    		tails.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		boolean calledHeads = false;
            		flipCoin.setVisible(false);
            		heads.setVisible(false);
            		tails.setVisible(false);
            		if (actualHeads == calledHeads) {
            			winnerCoinToss(frame, panel, true, calledHeads, actualHeads);
            		}
            		else {
            			winnerCoinToss(frame, panel, false, calledHeads, actualHeads);
            		}
                }
            });
    		
        	
		}
		
		else {
			boolean calledHeads = Math.random() < 0.5;
			if (actualHeads == calledHeads) {
				winnerCoinToss(frame, panel, true, calledHeads, actualHeads);
			}
			else {
				winnerCoinToss(frame, panel, false, calledHeads, actualHeads);
			}
		}
	}
	
	// Lets the team who won the coin toss decide whether to receive the ball
	private void winnerCoinToss(JFrame frame, JPanel panel, boolean awayWonToss, boolean calledHeads, boolean actualHeads) {
		if ((awayWonToss && awayHuman) || (!awayWonToss && homeHuman)) {
			JLabel makeDecision;
			if (awayWonToss) {
				makeDecision = new JLabel(awayTeam.getTeamAbbr() + ", you won the toss. Select one.", JLabel.CENTER);
				makeDecision.setBackground(awayTeam.getPrimaryColor());
				makeDecision.setForeground(awayTeam.getSecondaryColor());
			}
			else {
				makeDecision = new JLabel(homeTeam.getTeamAbbr() + ", you won the toss. Select one.", JLabel.CENTER);
				makeDecision.setBackground(homeTeam.getPrimaryColor());
				makeDecision.setForeground(homeTeam.getSecondaryColor());
			}
			makeDecision.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
			makeDecision.setBounds(frame.getWidth() / 8, frame.getHeight() / 4, frame.getWidth() * 3 / 4, frame.getHeight() / 8);
			makeDecision.setOpaque(true);
        	panel.add(makeDecision);
        	
        	JButton receive = new JButton("Receive");
        	receive.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
        	receive.setBackground(Color.BLACK);
        	receive.setForeground(Color.WHITE);
        	receive.setBounds(frame.getWidth() * 3 / 16, frame.getHeight() * 3 / 8, frame.getWidth() / 4, frame.getHeight() / 8);
        	panel.add(receive);
        	
        	JButton kickoff = new JButton("Kickoff");
        	kickoff.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
        	kickoff.setBackground(Color.BLACK);
        	kickoff.setForeground(Color.WHITE);
        	kickoff.setBounds(frame.getWidth() * 9 / 16, frame.getHeight() * 3 / 8, frame.getWidth() / 4, frame.getHeight() / 8);
        	panel.add(kickoff);
        	
        	if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
        		receive.setText("Offense First");
        		kickoff.setText("Defense First");
        	}
        	
        	makeDecision.setVisible(true);
        	receive.setVisible(true);
        	kickoff.setVisible(true);
        	
        	receive.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		awayReceiveFirst = awayWonToss;
            		makeDecision.setVisible(false);
            		receive.setVisible(false);
            		kickoff.setVisible(false);
            		playByPlayList.add(new JavaFootballCoinToss(awayTeam, homeTeam, calledHeads, actualHeads, true));
            		if (overtimeNow) {
            			if (awayReceiveFirst) {
            				overtimeAwayPoss = true;
            			}
            			else {
            				overtimeHomePoss = true;
            			}
            		}
            		play(frame, panel);
                }
            });
        	kickoff.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		awayReceiveFirst = !awayWonToss;
            		makeDecision.setVisible(false);
            		receive.setVisible(false);
            		kickoff.setVisible(false);
            		playByPlayList.add(new JavaFootballCoinToss(awayTeam, homeTeam, calledHeads, actualHeads, false));
            		if (overtimeNow) {
            			if (awayReceiveFirst) {
            				overtimeAwayPoss = true;
            			}
            			else {
            				overtimeHomePoss = true;
            			}
            		}
            		play(frame, panel);
                }
            });
		}
		
		else {
			if (!overtimeNow) {
				awayReceiveFirst = Math.random() < 0.5;
			}
			else if (homeTeam instanceof NFLTeam) {
				awayReceiveFirst = awayWonToss;
			}
			else {
				awayReceiveFirst = !awayWonToss;
			}
			if (awayHuman || homeHuman) {
				StringBuilder resultTxt = new StringBuilder();
				if (awayWonToss) {
					resultTxt.append(awayTeam.getTeamAbbr());
				}
				else {
					resultTxt.append(homeTeam.getTeamAbbr());
				}
				resultTxt.append(" won and will ");
				if (awayWonToss == awayReceiveFirst) {
					if (!overtimeNow || homeTeam instanceof NFLTeam) {
						playByPlayList.add(new JavaFootballCoinToss(awayTeam, homeTeam, calledHeads, actualHeads, true));
						resultTxt.append("receive.");
					}
					else {
						playByPlayList.add(new JavaFootballCoinToss(awayTeam, homeTeam, calledHeads, actualHeads, true));
						resultTxt.append("play offense first.");
					}
				}
				else {
					if (!overtimeNow || homeTeam instanceof NFLTeam) {
						playByPlayList.add(new JavaFootballCoinToss(awayTeam, homeTeam, calledHeads, actualHeads, false));
						resultTxt.append("receive.");
					}
					else {
						playByPlayList.add(new JavaFootballCoinToss(awayTeam, homeTeam, calledHeads, actualHeads, false));
						resultTxt.append("play defense first.");
					}
				}
				resultTxt.append(" Click here!");
				JButton result = new JButton(resultTxt.toString());
				result.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
				result.setBackground(Color.BLACK);
				result.setForeground(Color.WHITE);
				result.setBounds(frame.getWidth() / 8, frame.getHeight() / 4, frame.getWidth() * 3 / 4, frame.getHeight() / 8);
	        	panel.add(result);
	        	result.addActionListener(new ActionListener() {
	            	public void actionPerformed(ActionEvent e) {
	            		result.setVisible(false);
	            		if (overtimeNow) {
	            			if (awayReceiveFirst) {
	            				overtimeAwayPoss = true;
	            			}
	            			else {
	            				overtimeHomePoss = true;
	            			}
	            		}
	            		play(frame, panel);
	                }
	            });
	        	result.setVisible(true);
			}
			else {
				if (calledHeads == actualHeads) {
					if (awayReceiveFirst) {
						playByPlayList.add(new JavaFootballCoinToss(awayTeam, homeTeam, calledHeads, actualHeads, true));
					}
					else {
						playByPlayList.add(new JavaFootballCoinToss(awayTeam, homeTeam, calledHeads, actualHeads, false));
					}
				}
				else {
					if (awayReceiveFirst) {
						playByPlayList.add(new JavaFootballCoinToss(awayTeam, homeTeam, calledHeads, actualHeads, false));
					}
					else {
						playByPlayList.add(new JavaFootballCoinToss(awayTeam, homeTeam, calledHeads, actualHeads, true));
					}
				}
				if (overtimeNow) {
        			if (awayReceiveFirst) {
        				overtimeAwayPoss = true;
        			}
        			else {
        				overtimeHomePoss = true;
        			}
        		}
				play(frame, panel);
			}
		}
	}
	
	// Executes a play of the football game
	private void play(JFrame frame, JPanel panel) {
		if (awayHuman) {
			awayKickoff.setVisible(true);
			awayOnsideKick.setVisible(true);
			awayPunt.setVisible(true);
			awayFieldGoal.setVisible(true);
			awayExtraPoint.setVisible(true);
			awayTwoPoint.setVisible(true);
		}
		if (homeHuman) {
			homeKickoff.setVisible(true);
			homeOnsideKick.setVisible(true);
			homePunt.setVisible(true);
			homeFieldGoal.setVisible(true);
			homeExtraPoint.setVisible(true);
			homeTwoPoint.setVisible(true);
		}
		lastPlay.setVisible(true);
		
		updatePositionMarkers();
		
		if ((quarterNum != 1 && quarterNum != 3) && timeLeft.equals("2:00") && homeTeam instanceof NFLTeam) {
			if (!twoMinuteWarningUsed) {
				lastPlay.setText(lastPlay.getText() + "\nTWO MINUTE WARNING.");
				timeoutUsed = true;
				twoMinuteWarningUsed = true;
			}
		}
		
		if (timeLeft.equals("10:00") && overtimeNow && homeTeam instanceof NFLTeam && quarterNum > 4) {
			quarter.setText("Overtime " + (quarterNum - 4));
			timeLeft = "10:00";
			time.setText(timeLeft);
		}
		
		if (timeLeft.equals("0:00")) {
			quarterNum++;
			if (!overtimeNow) {
				quarter.setText("Quarter " + quarterNum);
				timeLeft = "15:00";
				time.setText(timeLeft);
			}
			else if (homeTeam instanceof NFLTeam) {
				System.out.println("here1");
				quarter.setText("Overtime " + (quarterNum - 4));
				timeLeft = "10:00";
				time.setText(timeLeft);
			}
			else {
				quarter.setText("Overtime " + (quarterNum - 4));
				time.setText("OT");
			}
			
			if (quarterNum == 2) {
				lastPlay.setText(lastPlay.getText() + "\nEND OF THE 1ST QUARTER.");
			}
			else if (quarterNum == 3) {
				lastPlay.setText(lastPlay.getText() + "\nEND OF THE 2ND QUARTER. HALFTIME!");
				kickoffStart = true;
				awayTimeout1.setEnabled(true);
				awayTimeout2.setEnabled(true);
				awayTimeout3.setEnabled(true);
				homeTimeout1.setEnabled(true);
				homeTimeout2.setEnabled(true);
				homeTimeout3.setEnabled(true);
				timeoutUsed = false;
				twoMinuteWarningUsed = false;
			}
			else if (quarterNum == 4) {
				lastPlay.setText(lastPlay.getText() + "\nEND OF THE 3RD QUARTER.");
			}
			else if (quarterNum == 5) {
				lastPlay.setText(lastPlay.getText() + "\nEND OF THE 4TH QUARTER. END OF REGULATION!");
				quarter.setText("FINAL");
				awayTimeout1.setVisible(false);
				awayTimeout2.setVisible(false);
				awayTimeout3.setVisible(false);
				homeTimeout1.setVisible(false);
				homeTimeout2.setVisible(false);
				homeTimeout3.setVisible(false);
				awaySim.setVisible(false);
				homeSim.setVisible(false);
				awayKickoff.setVisible(false);
				awayOnsideKick.setVisible(false);
				awayPunt.setVisible(false);
				awayFieldGoal.setVisible(false);
				awayExtraPoint.setVisible(false);
				awayTwoPoint.setVisible(false);
				homeKickoff.setVisible(false);
				homeOnsideKick.setVisible(false);
				homePunt.setVisible(false);
				homeFieldGoal.setVisible(false);
				homeExtraPoint.setVisible(false);
				homeTwoPoint.setVisible(false);
				awayPlay1.setVisible(false);
				awayPlay2.setVisible(false);
				awayPlay3.setVisible(false);
				awayPlay4.setVisible(false);
				awayPlay5.setVisible(false);
				homePlay1.setVisible(false);
				homePlay2.setVisible(false);
				homePlay3.setVisible(false);
				homePlay4.setVisible(false);
				homePlay5.setVisible(false);
				if (awayScore != homeScore) {
					JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
					returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
					returnToMainMenu.setBackground(Color.BLACK);
					returnToMainMenu.setForeground(Color.WHITE);
					returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
					returnToMainMenu.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							mainMenu.mainMenu();
						}
					});
					returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
				}
				else {
					overtimeNow = true;
					coinToss(frame, panel);
					if (homeTeam instanceof NFLTeam) {
						System.out.println("here2");
						quarter.setText("Overtime " + (quarterNum - 4));
						timeLeft = "10:00";
						time.setText(timeLeft);
					}
					else {
						quarter.setText("Overtime " + (quarterNum - 4));
						timeLeft = "10:00";
						time.setText("OT");
					}
					kickoffStart = true;
				}
			}
			else if (quarterNum > 5) {
				if (regularSeasonNFL || awayScore != homeScore) {
					lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
					quarter.setText("FINAL");
					awayTimeout1.setVisible(false);
					awayTimeout2.setVisible(false);
					awayTimeout3.setVisible(false);
					homeTimeout1.setVisible(false);
					homeTimeout2.setVisible(false);
					homeTimeout3.setVisible(false);
					awaySim.setVisible(false);
					homeSim.setVisible(false);
					awayKickoff.setVisible(false);
					awayOnsideKick.setVisible(false);
					awayPunt.setVisible(false);
					awayFieldGoal.setVisible(false);
					awayExtraPoint.setVisible(false);
					awayTwoPoint.setVisible(false);
					homeKickoff.setVisible(false);
					homeOnsideKick.setVisible(false);
					homePunt.setVisible(false);
					homeFieldGoal.setVisible(false);
					homeExtraPoint.setVisible(false);
					homeTwoPoint.setVisible(false);
					awayPlay1.setVisible(false);
					awayPlay2.setVisible(false);
					awayPlay3.setVisible(false);
					awayPlay4.setVisible(false);
					awayPlay5.setVisible(false);
					homePlay1.setVisible(false);
					homePlay2.setVisible(false);
					homePlay3.setVisible(false);
					homePlay4.setVisible(false);
					homePlay5.setVisible(false);
					JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
					returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
					returnToMainMenu.setBackground(Color.BLACK);
					returnToMainMenu.setForeground(Color.WHITE);
					returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
					returnToMainMenu.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							mainMenu.mainMenu();
						}
					});
					returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
				}
				else {
					lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD.");
					kickoffStart = true;
					awayTimeout1.setEnabled(true);
					awayTimeout2.setEnabled(true);
					awayTimeout3.setEnabled(true);
					homeTimeout1.setEnabled(true);
					homeTimeout2.setEnabled(true);
					homeTimeout3.setEnabled(true);
					timeoutUsed = false;
					twoMinuteWarningUsed = false;
					overtimeAwayPoss = false;
					overtimeHomePoss = false;
				}
			}
		}
		
		if (!downs.getText().contains("COIN")) {
			playByPlayList.add(new JavaFootballPlayInfo(awayTeam, homeTeam, awayScore, homeScore, quarterNum,
					time.getText(), downs.getText(), position.getText()));
		}
		
		if (awayHuman || homeHuman) {
			awaySim.setVisible(false);
			homeSim.setVisible(false);
		}
		
		if (kickoffStart && quarterNum < 5) {
			boolean awayKick = (!awayReceiveFirst && quarterNum == 1) || (awayReceiveFirst && quarterNum == 3);
			kickoff(awayKick, frame, panel);
		}
		
		else if (quarterNum < 5) {
			awayKickoff.setEnabled(false);
			awayOnsideKick.setEnabled(false);
			awayExtraPoint.setEnabled(false);
			awayTwoPoint.setEnabled(false);
			homeKickoff.setEnabled(false);
			homeOnsideKick.setEnabled(false);
			homeExtraPoint.setEnabled(false);
			homeTwoPoint.setEnabled(false);
			
			if (awayPossession.isVisible()) {
				awayPunt.setEnabled(true);
			}
			else {
				awayPunt.setEnabled(false);
			}
			if (homePossession.isVisible()) {
				homePunt.setEnabled(true);
			}
			else {
				homePunt.setEnabled(false);
			}
			
			if (awayPossession.isVisible() && homeSideOfField && yardLine != 50) {
				awayFieldGoal.setEnabled(true);
			}
			else {
				awayFieldGoal.setEnabled(false);
			}
			if (homePossession.isVisible() && !homeSideOfField && yardLine != 50) {
				homeFieldGoal.setEnabled(true);
			}
			else {
				homeFieldGoal.setEnabled(false);
			}
			
			normalOffenseDefensePlay();
		}
		
		else {
			if (homeTeam instanceof NFLTeam && awayScore != homeScore && overtimeAwayPoss && overtimeHomePoss) {
				lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
				quarter.setText("FINAL");
				awayTimeout1.setVisible(false);
				awayTimeout2.setVisible(false);
				awayTimeout3.setVisible(false);
				homeTimeout1.setVisible(false);
				homeTimeout2.setVisible(false);
				homeTimeout3.setVisible(false);
				awaySim.setVisible(false);
				homeSim.setVisible(false);
				awayKickoff.setVisible(false);
				awayOnsideKick.setVisible(false);
				awayPunt.setVisible(false);
				awayFieldGoal.setVisible(false);
				awayExtraPoint.setVisible(false);
				awayTwoPoint.setVisible(false);
				homeKickoff.setVisible(false);
				homeOnsideKick.setVisible(false);
				homePunt.setVisible(false);
				homeFieldGoal.setVisible(false);
				homeExtraPoint.setVisible(false);
				homeTwoPoint.setVisible(false);
				awayPlay1.setVisible(false);
				awayPlay2.setVisible(false);
				awayPlay3.setVisible(false);
				awayPlay4.setVisible(false);
				awayPlay5.setVisible(false);
				homePlay1.setVisible(false);
				homePlay2.setVisible(false);
				homePlay3.setVisible(false);
				homePlay4.setVisible(false);
				homePlay5.setVisible(false);
				JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
				returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
				returnToMainMenu.setBackground(Color.BLACK);
				returnToMainMenu.setForeground(Color.WHITE);
				returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
				returnToMainMenu.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mainMenu.mainMenu();
					}
				});
				returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
			}
			
			else if (kickoffStart) {
				boolean awayKick = (!awayReceiveFirst && quarterNum % 2 == 1) || (awayReceiveFirst && quarterNum % 2 == 0);
				kickoff(awayKick, frame, panel);
			}
			
			else {
				awayKickoff.setEnabled(false);
				awayOnsideKick.setEnabled(false);
				awayExtraPoint.setEnabled(false);
				awayTwoPoint.setEnabled(false);
				homeKickoff.setEnabled(false);
				homeOnsideKick.setEnabled(false);
				homeExtraPoint.setEnabled(false);
				homeTwoPoint.setEnabled(false);
				
				if (awayPossession.isVisible() && (!overtimeNow || homeTeam instanceof NFLTeam)) {
					awayPunt.setEnabled(true);
				}
				else {
					awayPunt.setEnabled(false);
				}
				if (homePossession.isVisible() && (!overtimeNow || homeTeam instanceof NFLTeam)) {
					homePunt.setEnabled(true);
				}
				else {
					homePunt.setEnabled(false);
				}
				
				if (awayPossession.isVisible() && homeSideOfField && yardLine != 50) {
					awayFieldGoal.setEnabled(true);
				}
				else {
					awayFieldGoal.setEnabled(false);
				}
				if (homePossession.isVisible() && !homeSideOfField && yardLine != 50) {
					homeFieldGoal.setEnabled(true);
				}
				else {
					homeFieldGoal.setEnabled(false);
				}
				
				
				normalOffenseDefensePlay();
			}
		}
	}
	
	// Updates the game clock after a timed play
	private void updateTime() {
		if (quarterNum < 5 || homeTeam instanceof NFLTeam) {
			if (!timeoutUsed) {
				String[] times = timeLeft.split(":");
				int minutes = Integer.parseInt(times[0]);
				minutes--;
				timeLeft = minutes + ":00";
				time.setText(timeLeft);
			}
			else {
				timeoutUsed = false;
			}
		}
	}
	
	// Draws a Java football offense card
	private JavaFootballOffenseCard drawOffenseCard(boolean home) {
		double[] passOff;
		double[] rushOff;
		double[] rushDrawOff;
		if (!home) {
			passOff = awayPassOff;
			rushOff = awayRushOff;
			rushDrawOff = awayRushDrawOff;
		}
		else {
			passOff = homePassOff;
			rushOff = homeRushOff;
			rushDrawOff = homeRushDrawOff;
		}
		double i = Math.random();
		if (i < 0.3) {
			double j = Math.random();
			if (j < passOff[0]) {
				return offenseCards.get("pass10");
			}
			else if (j < passOff[0] + passOff[1]) {
				return offenseCards.get("pass20");
			}
			else if (j < passOff[0] + passOff[1] + passOff[2]) {
				return offenseCards.get("pass30");
			}
			else if (j < passOff[0] + passOff[1] + passOff[2] + passOff[3]) {
				return offenseCards.get("pass40");
			}
			else {
				return offenseCards.get("pass50");
			}
		}
		else if (i < 0.6) {
			double j = Math.random();
			if (j < rushOff[0]) {
				return offenseCards.get("run5");
			}
			else if (j < rushOff[0] + rushOff[1]) {
				return offenseCards.get("run10");
			}
			else if (j < rushOff[0] + rushOff[1] + rushOff[2]) {
				return offenseCards.get("run15");
			}
			else if (j < rushOff[0] + rushOff[1] + rushOff[2] + rushOff[3]) {
				return offenseCards.get("run20");
			}
			else {
				return offenseCards.get("run25");
			}
		}
		else if (i < 0.7) {
			double j = Math.random();
			if (j < rushDrawOff[0]) {
				return offenseCards.get("runDraw5");
			}
			else if (j < rushDrawOff[0] + rushDrawOff[1]) {
				return offenseCards.get("runDraw10");
			}
			else if (j < rushDrawOff[0] + rushDrawOff[1] + rushDrawOff[2]) {
				return offenseCards.get("runDraw15");
			}
			else {
				return offenseCards.get("runDraw20");
			}
		}
		else if (i < 0.8) {
			return offenseCards.get("screenPass");
		}
		else {
			double j = Math.random();
			if (j < passOff[0]) {
				return offenseCards.get("playAction10");
			}
			else if (j < passOff[0] + passOff[1]) {
				return offenseCards.get("playAction20");
			}
			else if (j < passOff[0] + passOff[1] + passOff[2]) {
				return offenseCards.get("playAction30");
			}
			else if (j < passOff[0] + passOff[1] + passOff[2] + passOff[3]) {
				return offenseCards.get("playAction40");
			}
			else {
				return offenseCards.get("playAction50");
			}
		}
	}
	
	// Draws a Java football defense card
	private JavaFootballDefenseCard drawDefenseCard(boolean home) {
		double[] passDef;
		double[] rushDef;
		if (!home) {
			passDef = awayPassDef;
			rushDef = awayRushDef;
		}
		else {
			passDef = homePassDef;
			rushDef = homeRushDef;
		}
		double i = Math.random();
		if (i < 0.54) {
			double j = Math.random();
			if (j < passDef[0]) {
				return defenseCards.get("passDefense");
			}
			else if (j < passDef[0] + passDef[1]) {
				return defenseCards.get("zoneBlitz");
			}
			else {
				return defenseCards.get("interception");
			}
		}
		else if (i < 0.9) {
			double j = Math.random();
			if (j < rushDef[0]) {
				return defenseCards.get("runDefense");
			}
			else if (j < rushDef[0] + rushDef[1]) {
				return defenseCards.get("allOutBlitz");
			}
			else {
				return defenseCards.get("fumble");
			}
		}
		else {
			return defenseCards.get("penaltyFlag");
		}
	}
	
	// Kickoff in the game
	private void kickoff(boolean awayKick, JFrame frame, JPanel panel) {
		
		downs.setText("KICKOFF");
		
		resetYardlineMarkers();
		
		awayKickoff.setEnabled(false);
		awayOnsideKick.setEnabled(false);
		awayPunt.setEnabled(false);
		awayFieldGoal.setEnabled(false);
		awayExtraPoint.setEnabled(false);
		awayTwoPoint.setEnabled(false);
		homeKickoff.setEnabled(false);
		homeOnsideKick.setEnabled(false);
		homePunt.setEnabled(false);
		homeFieldGoal.setEnabled(false);
		homeExtraPoint.setEnabled(false);
		homeTwoPoint.setEnabled(false);
		
		awayPlay1.setIcon(null);
		awayPlay2.setIcon(null);
		awayPlay3.setIcon(null);
		awayPlay4.setIcon(null);
		awayPlay5.setIcon(null);
		homePlay1.setIcon(null);
		homePlay2.setIcon(null);
		homePlay3.setIcon(null);
		homePlay4.setIcon(null);
		homePlay5.setIcon(null);
		awayPlay1.setEnabled(false);
		awayPlay2.setEnabled(false);
		awayPlay3.setEnabled(false);
		awayPlay4.setEnabled(false);
		awayPlay5.setEnabled(false);
		homePlay1.setEnabled(false);
		homePlay2.setEnabled(false);
		homePlay3.setEnabled(false);
		homePlay4.setEnabled(false);
		homePlay5.setEnabled(false);
		awayPlay1.setVisible(false);
		awayPlay2.setVisible(false);
		awayPlay3.setVisible(false);
		awayPlay4.setVisible(false);
		awayPlay5.setVisible(false);
		homePlay1.setVisible(false);
		homePlay2.setVisible(false);
		homePlay3.setVisible(false);
		homePlay4.setVisible(false);
		homePlay5.setVisible(false);
		
		if (kickoffStart) {
			awayTimeout1.setEnabled(true);
			awayTimeout2.setEnabled(true);
			awayTimeout3.setEnabled(true);
			homeTimeout1.setEnabled(true);
			homeTimeout2.setEnabled(true);
			homeTimeout3.setEnabled(true);
		}
		
		if (quarterNum == 3 && timeLeft.equals("15:00")) {
			playByPlayList.remove(playByPlayList.size() - 1);
		}
		
		if (quarterNum >= 5 && timeLeft.equals("10:00")) {
			playByPlayList.remove(playByPlayList.size() - 1);
		}
		
		if (awayKick) {
			awayPossession.setVisible(true);
			homePossession.setVisible(false);
			awayKickoff.setEnabled(true);
			awayOnsideKick.setEnabled(true);
			football.setLocation(field.getWidth() * 35 / 100 - 49, field.getHeight() / 8 - 30);
			homeSideOfField = false;
			yardLine = 35;
			position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
			playByPlayList.add(new JavaFootballPlayInfo(awayTeam, homeTeam, awayScore, homeScore, quarterNum,
					time.getText(), downs.getText(), position.getText()));
			if (!awayHuman) {
				clearAwaySimActionListeners();
				awaySim.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (quarterNum == 4 && awayScore < homeScore) {
							String[] times = timeLeft.split(":");
							int minutes = Integer.parseInt(times[0]);
							if (minutes <= 5) {
								awayOnsideKick.doClick();
							}
							else if (minutes <= 10 && awayScore + 8 < homeScore) {
								awayOnsideKick.doClick();
							}
							else if (minutes <= 15 && awayScore + 16 < homeScore) {
								awayOnsideKick.doClick();
							}
							else {
								awayKickoff.doClick();
							}
						}
						else {
							awayKickoff.doClick();
						}
					}
				});
				awaySim.setVisible(true);
				if (!homeHuman) {
					clearHomeSimActionListeners();
					homeSim.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (quarterNum == 4 && awayScore < homeScore) {
								String[] times = timeLeft.split(":");
								int minutes = Integer.parseInt(times[0]);
								if (minutes <= 5) {
									awayOnsideKick.doClick();
								}
								else if (minutes <= 10 && awayScore + 8 < homeScore) {
									awayOnsideKick.doClick();
								}
								else if (minutes <= 15 && awayScore + 16 < homeScore) {
									awayOnsideKick.doClick();
								}
								else {
									awayKickoff.doClick();
								}
							}
							else {
								awayKickoff.doClick();
							}
						}
					});
					homeSim.setVisible(true);
				}
			}
		}
		else {
			awayPossession.setVisible(false);
			homePossession.setVisible(true);
			homeKickoff.setEnabled(true);
			homeOnsideKick.setEnabled(true);
			football.setLocation(field.getWidth() * 65 / 100 - 49, field.getHeight() / 8 - 30);
			homeSideOfField = true;
			yardLine = 35;
			position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
			playByPlayList.add(new JavaFootballPlayInfo(awayTeam, homeTeam, awayScore, homeScore, quarterNum,
					time.getText(), downs.getText(), position.getText()));
			if (!homeHuman) {
				clearHomeSimActionListeners();
				homeSim.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (quarterNum == 4 && homeScore < awayScore) {
							String[] times = timeLeft.split(":");
							int minutes = Integer.parseInt(times[0]);
							if (minutes <= 5) {
								homeOnsideKick.doClick();
							}
							else if (minutes <= 10 && homeScore + 8 < awayScore) {
								homeOnsideKick.doClick();
							}
							else if (minutes <= 15 && homeScore + 16 < awayScore) {
								homeOnsideKick.doClick();
							}
							else {
								homeKickoff.doClick();
							}
						}
						else {
							homeKickoff.doClick();
						}
					}
				});
				homeSim.setVisible(true);
				if (!awayHuman) {
					clearAwaySimActionListeners();
					awaySim.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (quarterNum == 4 && homeScore < awayScore) {
								String[] times = timeLeft.split(":");
								int minutes = Integer.parseInt(times[0]);
								if (minutes <= 5) {
									homeOnsideKick.doClick();
								}
								else if (minutes <= 10 && homeScore + 8 < awayScore) {
									homeOnsideKick.doClick();
								}
								else if (minutes <= 15 && homeScore + 16 < awayScore) {
									homeOnsideKick.doClick();
								}
								else {
									homeKickoff.doClick();
								}
							}
							else {
								homeKickoff.doClick();
							}
						}
					});
					awaySim.setVisible(true);
				}
			}
		}
		
		if (overtimeNow && !(homeTeam instanceof NFLTeam)) {
			boolean finished = false;
			if (numCollegeOTPoss > 0 && numCollegeOTPoss % 2 == 0) {
				if (awayScore != homeScore) {
					finished = true;
				}
				else {
					quarterNum++;
				}
				awayKick = !awayKick;
			}
			numCollegeOTPoss++;
			if (awayKick) {
				down = 1;
				yardsToGo = 10;
				downs.setText("1st & 10");
				homeSideOfField = false;
				yardLine = 25;
				position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
				awayPossession.setVisible(false);
				homePossession.setVisible(true);
				homeFieldGoal.setEnabled(true);
			}
			else {
				down = 1;
				yardsToGo = 10;
				downs.setText("1st & 10");
				homeSideOfField = true;
				yardLine = 25;
				position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
				awayPossession.setVisible(true);
				homePossession.setVisible(false);
				awayFieldGoal.setEnabled(true);
			}
			quarter.setText("Overtime " + (quarterNum - 4));
			awayKickoff.setEnabled(false);
			awayOnsideKick.setEnabled(false);
			homeKickoff.setEnabled(false);
			homeOnsideKick.setEnabled(false);
			kickoffStart = false;
			updatePositionMarkers();
			normalOffenseDefensePlay();
			
			if (finished) {
				lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
				quarter.setText("FINAL");
				awayTimeout1.setVisible(false);
				awayTimeout2.setVisible(false);
				awayTimeout3.setVisible(false);
				homeTimeout1.setVisible(false);
				homeTimeout2.setVisible(false);
				homeTimeout3.setVisible(false);
				awaySim.setVisible(false);
				homeSim.setVisible(false);
				awayKickoff.setVisible(false);
				awayOnsideKick.setVisible(false);
				awayPunt.setVisible(false);
				awayFieldGoal.setVisible(false);
				awayExtraPoint.setVisible(false);
				awayTwoPoint.setVisible(false);
				homeKickoff.setVisible(false);
				homeOnsideKick.setVisible(false);
				homePunt.setVisible(false);
				homeFieldGoal.setVisible(false);
				homeExtraPoint.setVisible(false);
				homeTwoPoint.setVisible(false);
				awayPlay1.setVisible(false);
				awayPlay2.setVisible(false);
				awayPlay3.setVisible(false);
				awayPlay4.setVisible(false);
				awayPlay5.setVisible(false);
				homePlay1.setVisible(false);
				homePlay2.setVisible(false);
				homePlay3.setVisible(false);
				homePlay4.setVisible(false);
				homePlay5.setVisible(false);
				JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
				returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
				returnToMainMenu.setBackground(Color.BLACK);
				returnToMainMenu.setForeground(Color.WHITE);
				returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
				returnToMainMenu.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mainMenu.mainMenu();
					}
				});
				returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
			}
		}
		else {
			kickoffStart = false;
		}
	}
	
	// Handles the event of a touchdown during the game
	private void touchdown(boolean awayTouchdown, JFrame frame, JPanel panel) {
		downs.setText("PAT");
		
		resetYardlineMarkers();
		
		awayKickoff.setEnabled(false);
		awayOnsideKick.setEnabled(false);
		awayPunt.setEnabled(false);
		awayFieldGoal.setEnabled(false);
		awayExtraPoint.setEnabled(false);
		awayTwoPoint.setEnabled(false);
		homeKickoff.setEnabled(false);
		homeOnsideKick.setEnabled(false);
		homePunt.setEnabled(false);
		homeFieldGoal.setEnabled(false);
		homeExtraPoint.setEnabled(false);
		homeTwoPoint.setEnabled(false);
		
		awayPlay1.setIcon(null);
		awayPlay2.setIcon(null);
		awayPlay3.setIcon(null);
		awayPlay4.setIcon(null);
		awayPlay5.setIcon(null);
		homePlay1.setIcon(null);
		homePlay2.setIcon(null);
		homePlay3.setIcon(null);
		homePlay4.setIcon(null);
		homePlay5.setIcon(null);
		awayPlay1.setEnabled(false);
		awayPlay2.setEnabled(false);
		awayPlay3.setEnabled(false);
		awayPlay4.setEnabled(false);
		awayPlay5.setEnabled(false);
		homePlay1.setEnabled(false);
		homePlay2.setEnabled(false);
		homePlay3.setEnabled(false);
		homePlay4.setEnabled(false);
		homePlay5.setEnabled(false);
		awayPlay1.setVisible(false);
		awayPlay2.setVisible(false);
		awayPlay3.setVisible(false);
		awayPlay4.setVisible(false);
		awayPlay5.setVisible(false);
		homePlay1.setVisible(false);
		homePlay2.setVisible(false);
		homePlay3.setVisible(false);
		homePlay4.setVisible(false);
		homePlay5.setVisible(false);
		
		if (awayTouchdown) {
			awayScore += 6;
			awayPoints.setText("" + awayScore);
			if (!overtimeNow || homeTeam instanceof NFLTeam || quarterNum < 7) {
				awayExtraPoint.setEnabled(true);
			}
			awayTwoPoint.setEnabled(true);
			football.setLocation(field.getWidth() - 98, field.getHeight() / 8 - 30);
			homeSideOfField = true;
			yardLine = 2;
			position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
			playByPlayList.add(new JavaFootballPlayInfo(awayTeam, homeTeam, awayScore, homeScore, quarterNum,
					time.getText(), downs.getText(), position.getText()));
			
			if (overtimeNow && homeTeam instanceof NFLTeam) {
				lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
				quarter.setText("FINAL");
				awayTimeout1.setVisible(false);
				awayTimeout2.setVisible(false);
				awayTimeout3.setVisible(false);
				homeTimeout1.setVisible(false);
				homeTimeout2.setVisible(false);
				homeTimeout3.setVisible(false);
				awaySim.setVisible(false);
				homeSim.setVisible(false);
				awayKickoff.setVisible(false);
				awayOnsideKick.setVisible(false);
				awayPunt.setVisible(false);
				awayFieldGoal.setVisible(false);
				awayExtraPoint.setVisible(false);
				awayTwoPoint.setVisible(false);
				homeKickoff.setVisible(false);
				homeOnsideKick.setVisible(false);
				homePunt.setVisible(false);
				homeFieldGoal.setVisible(false);
				homeExtraPoint.setVisible(false);
				homeTwoPoint.setVisible(false);
				awayPlay1.setVisible(false);
				awayPlay2.setVisible(false);
				awayPlay3.setVisible(false);
				awayPlay4.setVisible(false);
				awayPlay5.setVisible(false);
				homePlay1.setVisible(false);
				homePlay2.setVisible(false);
				homePlay3.setVisible(false);
				homePlay4.setVisible(false);
				homePlay5.setVisible(false);
				JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
				returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
				returnToMainMenu.setBackground(Color.BLACK);
				returnToMainMenu.setForeground(Color.WHITE);
				returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
				returnToMainMenu.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mainMenu.mainMenu();
					}
				});
				returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
			}
			else {
			
				if (!awayHuman) {
					clearAwaySimActionListeners();
					awaySim.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (quarterNum == 3 || quarterNum == 4) {
								if (homeScore > awayScore &&
										((homeScore - awayScore - 2) % 8 == 0 || (homeScore - awayScore - 5) % 8 == 0)) {
									awayTwoPoint.doClick();
								}
								else if (awayScore > homeScore &&
											(awayScore - homeScore == 1 || awayScore - homeScore == 5)) {
									awayTwoPoint.doClick();
								}
								else {
									awayExtraPoint.doClick();
								}
							}
							else {
								if (!overtimeNow || homeTeam instanceof NFLTeam || quarterNum < 7) {
									awayExtraPoint.doClick();
								}
								else {
									awayTwoPoint.doClick();
								}
							}
						}
					});
					awaySim.setVisible(true);
					if (!homeHuman) {
						clearHomeSimActionListeners();
						homeSim.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (quarterNum == 3 || quarterNum == 4) {
									if (homeScore > awayScore &&
											((homeScore - awayScore - 2) % 8 == 0 || (homeScore - awayScore - 5) % 8 == 0)) {
										awayTwoPoint.doClick();
									}
									else if (awayScore > homeScore &&
												(awayScore - homeScore == 1 || awayScore - homeScore == 5)) {
										awayTwoPoint.doClick();
									}
									else {
										awayExtraPoint.doClick();
									}
								}
								else {
									if (!overtimeNow || homeTeam instanceof NFLTeam || quarterNum < 7) {
										awayExtraPoint.doClick();
									}
									else {
										awayTwoPoint.doClick();
									}
								}
							}
						});
						homeSim.setVisible(true);
					}
				}
			}
		}
		else {
			homeScore += 6;
			homePoints.setText("" + homeScore);
			if (!overtimeNow || homeTeam instanceof NFLTeam || quarterNum < 7) {
				homeExtraPoint.setEnabled(true);
			}
			homeTwoPoint.setEnabled(true);
			football.setLocation(0, field.getHeight() / 8 - 30);
			homeSideOfField = false;
			yardLine = 2;
			position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
			playByPlayList.add(new JavaFootballPlayInfo(awayTeam, homeTeam, awayScore, homeScore, quarterNum,
					time.getText(), downs.getText(), position.getText()));
			
			if (overtimeNow && homeTeam instanceof NFLTeam) {
				lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
				quarter.setText("FINAL");
				awayTimeout1.setVisible(false);
				awayTimeout2.setVisible(false);
				awayTimeout3.setVisible(false);
				homeTimeout1.setVisible(false);
				homeTimeout2.setVisible(false);
				homeTimeout3.setVisible(false);
				awaySim.setVisible(false);
				homeSim.setVisible(false);
				awayKickoff.setVisible(false);
				awayOnsideKick.setVisible(false);
				awayPunt.setVisible(false);
				awayFieldGoal.setVisible(false);
				awayExtraPoint.setVisible(false);
				awayTwoPoint.setVisible(false);
				homeKickoff.setVisible(false);
				homeOnsideKick.setVisible(false);
				homePunt.setVisible(false);
				homeFieldGoal.setVisible(false);
				homeExtraPoint.setVisible(false);
				homeTwoPoint.setVisible(false);
				awayPlay1.setVisible(false);
				awayPlay2.setVisible(false);
				awayPlay3.setVisible(false);
				awayPlay4.setVisible(false);
				awayPlay5.setVisible(false);
				homePlay1.setVisible(false);
				homePlay2.setVisible(false);
				homePlay3.setVisible(false);
				homePlay4.setVisible(false);
				homePlay5.setVisible(false);
				JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
				returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
				returnToMainMenu.setBackground(Color.BLACK);
				returnToMainMenu.setForeground(Color.WHITE);
				returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
				returnToMainMenu.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mainMenu.mainMenu();
					}
				});
				returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
			}
			else {
			
				if (!homeHuman) {
					clearHomeSimActionListeners();
					homeSim.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (quarterNum == 3 || quarterNum == 4) {
								if (awayScore > homeScore &&
										((awayScore - homeScore - 2) % 8 == 0 || (awayScore - homeScore - 5) % 8 == 0)) {
									homeTwoPoint.doClick();
								}
								else if (homeScore > awayScore &&
											(homeScore - awayScore == 1 || homeScore - awayScore == 5)) {
									homeTwoPoint.doClick();
								}
								else {
									homeExtraPoint.doClick();
								}
							}
							else {
								if (!overtimeNow || homeTeam instanceof NFLTeam || quarterNum < 7) {
									homeExtraPoint.doClick();
								}
								else {
									homeTwoPoint.doClick();
								}
							}
						}
					});
					homeSim.setVisible(true);
					if (!awayHuman) {
						clearAwaySimActionListeners();
						awaySim.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (quarterNum == 3 || quarterNum == 4) {
									if (awayScore > homeScore &&
											((awayScore - homeScore - 2) % 8 == 0 || (awayScore - homeScore - 5) % 8 == 0)) {
										homeTwoPoint.doClick();
									}
									else if (homeScore > awayScore &&
												(homeScore - awayScore == 1 || homeScore - awayScore == 5)) {
										homeTwoPoint.doClick();
									}
									else {
										homeExtraPoint.doClick();
									}
								}
								else {
									if (!overtimeNow || homeTeam instanceof NFLTeam || quarterNum < 7) {
										homeExtraPoint.doClick();
									}
									else {
										homeTwoPoint.doClick();
									}
								}
							}
						});
						awaySim.setVisible(true);
					}
				}
			}
		}
	}
	
	// Updates the position of the football and blue & yellow lines on the field
	private void updatePositionMarkers() {
		int num;
		if (!homeSideOfField) {
			num = yardLine;
		}
		else {
			num = 100 - yardLine;
		}
		football.setLocation(field.getWidth() * num / 100 - 49, field.getHeight() / 8 - 30);
		
		resetYardlineMarkers();
		if (downs.getText().contains("&")) {
			num /= 5;
			yardMarkers[num].setBackground(Color.BLUE);
			
			if (awayPossession.isVisible()) {
				num += yardsToGo / 5;
				if (num > 20) {
					num = 20;
				}
			}
			else {
				num -= yardsToGo / 5;
				if (num < 0) {
					num = 0;
				}
			}
			yardMarkers[num].setBackground(Color.YELLOW);
		}
	}
	
	// Resets all yardline markers to their default white color
	private void resetYardlineMarkers() {
		for (JButton element : yardMarkers) {
			element.setBackground(Color.WHITE);
		}
	}
	
	// Executes a normal play (offense versus defense)
	private void normalOffenseDefensePlay() {
		if (down == 1) {
			awayPlay1.setEnabled(true);
			awayPlay2.setEnabled(true);
			awayPlay3.setEnabled(true);
			awayPlay4.setEnabled(true);
			awayPlay5.setEnabled(true);
			homePlay1.setEnabled(true);
			homePlay2.setEnabled(true);
			homePlay3.setEnabled(true);
			homePlay4.setEnabled(true);
			homePlay5.setEnabled(true);
		}
		
		if (awayPossession.isVisible()) {
			
			if (down == 1) {
				for (int i = 0; i < 5; i++) {
					if (awayOffenseCards[i] == null) {
						awayOffenseCards[i] = drawOffenseCard(false);
					}
					if (awayHuman) {
						switch (i) {
							case 0:
								awayPlay1.setIcon(new ImageIcon(awayOffenseCards[i].getIcon().getImage()
						        		.getScaledInstance(516, 60, Image.SCALE_DEFAULT)));
								awayPlay1.setVisible(true);
								break;
							case 1:
								awayPlay2.setIcon(new ImageIcon(awayOffenseCards[i].getIcon().getImage()
						        		.getScaledInstance(516, 60, Image.SCALE_DEFAULT)));
								awayPlay2.setVisible(true);
								break;
							case 2:
								awayPlay3.setIcon(new ImageIcon(awayOffenseCards[i].getIcon().getImage()
						        		.getScaledInstance(516, 60, Image.SCALE_DEFAULT)));
								awayPlay3.setVisible(true);
								break;
							case 3:
								awayPlay4.setIcon(new ImageIcon(awayOffenseCards[i].getIcon().getImage()
						        		.getScaledInstance(516, 60, Image.SCALE_DEFAULT)));
								awayPlay4.setVisible(true);
								break;
							case 4:
								awayPlay5.setIcon(new ImageIcon(awayOffenseCards[i].getIcon().getImage()
						        		.getScaledInstance(516, 60, Image.SCALE_DEFAULT)));
								awayPlay5.setVisible(true);
								break;
						}
					}
					else {
						switch (i) {
							case 0:
								awayPlay1.setIcon(null);
								awayPlay1.setVisible(false);
								break;
							case 1:
								awayPlay2.setIcon(null);
								awayPlay2.setVisible(false);
								break;
							case 2:
								awayPlay3.setIcon(null);
								awayPlay3.setVisible(false);
								break;
							case 3:
								awayPlay4.setIcon(null);
								awayPlay4.setVisible(false);
								break;
							case 4:
								awayPlay5.setIcon(null);
								awayPlay5.setVisible(false);
								break;
						}
					}
					if (homeDefenseCards[i] == null) {
						homeDefenseCards[i] = drawDefenseCard(true);
					}
					if (homeHuman && !awayHuman) {
						switch (i) {
							case 0:
								homePlay1.setIcon(new ImageIcon(homeDefenseCards[i].getIcon().getImage()
						        		.getScaledInstance(516, 60, Image.SCALE_DEFAULT)));
								homePlay1.setVisible(true);
								break;
							case 1:
								homePlay2.setIcon(new ImageIcon(homeDefenseCards[i].getIcon().getImage()
						        		.getScaledInstance(516, 60, Image.SCALE_DEFAULT)));
								homePlay2.setVisible(true);
								break;
							case 2:
								homePlay3.setIcon(new ImageIcon(homeDefenseCards[i].getIcon().getImage()
						        		.getScaledInstance(516, 60, Image.SCALE_DEFAULT)));
								homePlay3.setVisible(true);
								break;
							case 3:
								homePlay4.setIcon(new ImageIcon(homeDefenseCards[i].getIcon().getImage()
						        		.getScaledInstance(516, 60, Image.SCALE_DEFAULT)));
								homePlay4.setVisible(true);
								break;
							case 4:
								homePlay5.setIcon(new ImageIcon(homeDefenseCards[i].getIcon().getImage()
						        		.getScaledInstance(516, 60, Image.SCALE_DEFAULT)));
								homePlay5.setVisible(true);
								break;
						}
					}
					else {
						switch (i) {
							case 0:
								homePlay1.setIcon(null);
								homePlay1.setVisible(false);
								break;
							case 1:
								homePlay2.setIcon(null);
								homePlay2.setVisible(false);
								break;
							case 2:
								homePlay3.setIcon(null);
								homePlay3.setVisible(false);
								break;
							case 3:
								homePlay4.setIcon(null);
								homePlay4.setVisible(false);
								break;
							case 4:
								homePlay5.setIcon(null);
								homePlay5.setVisible(false);
								break;
						}
					}
				}
			}
		}
		else {
			
			if (down == 1) {
				for (int i = 0; i < 5; i++) {
					if (homeOffenseCards[i] == null) {
						homeOffenseCards[i] = drawOffenseCard(true);
					}
					if (homeHuman) {
						switch (i) {
							case 0:
								homePlay1.setIcon(new ImageIcon(homeOffenseCards[i].getIcon().getImage()
						        		.getScaledInstance(516, 60, Image.SCALE_DEFAULT)));
								homePlay1.setVisible(true);
								break;
							case 1:
								homePlay2.setIcon(new ImageIcon(homeOffenseCards[i].getIcon().getImage()
						        		.getScaledInstance(516, 60, Image.SCALE_DEFAULT)));
								homePlay2.setVisible(true);
								break;
							case 2:
								homePlay3.setIcon(new ImageIcon(homeOffenseCards[i].getIcon().getImage()
						        		.getScaledInstance(516, 60, Image.SCALE_DEFAULT)));
								homePlay3.setVisible(true);
								break;
							case 3:
								homePlay4.setIcon(new ImageIcon(homeOffenseCards[i].getIcon().getImage()
						        		.getScaledInstance(516, 60, Image.SCALE_DEFAULT)));
								homePlay4.setVisible(true);
								break;
							case 4:
								homePlay5.setIcon(new ImageIcon(homeOffenseCards[i].getIcon().getImage()
						        		.getScaledInstance(516, 60, Image.SCALE_DEFAULT)));
								homePlay5.setVisible(true);
								break;
						}
					}
					else {
						switch (i) {
							case 0:
								homePlay1.setIcon(null);
								homePlay1.setVisible(false);
								break;
							case 1:
								homePlay2.setIcon(null);
								homePlay2.setVisible(false);
								break;
							case 2:
								homePlay3.setIcon(null);
								homePlay3.setVisible(false);
								break;
							case 3:
								homePlay4.setIcon(null);
								homePlay4.setVisible(false);
								break;
							case 4:
								homePlay5.setIcon(null);
								homePlay5.setVisible(false);
								break;
						}
					}
					if (awayDefenseCards[i] == null) {
						awayDefenseCards[i] = drawDefenseCard(false);
					}
					if (awayHuman && !homeHuman) {
						switch (i) {
							case 0:
								awayPlay1.setIcon(new ImageIcon(awayDefenseCards[i].getIcon().getImage()
						        		.getScaledInstance(516, 60, Image.SCALE_DEFAULT)));
								awayPlay1.setVisible(true);
								break;
							case 1:
								awayPlay2.setIcon(new ImageIcon(awayDefenseCards[i].getIcon().getImage()
						        		.getScaledInstance(516, 60, Image.SCALE_DEFAULT)));
								awayPlay2.setVisible(true);
								break;
							case 2:
								awayPlay3.setIcon(new ImageIcon(awayDefenseCards[i].getIcon().getImage()
						        		.getScaledInstance(516, 60, Image.SCALE_DEFAULT)));
								awayPlay3.setVisible(true);
								break;
							case 3:
								awayPlay4.setIcon(new ImageIcon(awayDefenseCards[i].getIcon().getImage()
						        		.getScaledInstance(516, 60, Image.SCALE_DEFAULT)));
								awayPlay4.setVisible(true);
								break;
							case 4:
								awayPlay5.setIcon(new ImageIcon(awayDefenseCards[i].getIcon().getImage()
						        		.getScaledInstance(516, 60, Image.SCALE_DEFAULT)));
								awayPlay5.setVisible(true);
								break;
						}
					}
					else {
						switch (i) {
							case 0:
								awayPlay1.setIcon(null);
								awayPlay1.setVisible(false);
								break;
							case 1:
								awayPlay2.setIcon(null);
								awayPlay2.setVisible(false);
								break;
							case 2:
								awayPlay3.setIcon(null);
								awayPlay3.setVisible(false);
								break;
							case 3:
								awayPlay4.setIcon(null);
								awayPlay4.setVisible(false);
								break;
							case 4:
								awayPlay5.setIcon(null);
								awayPlay5.setVisible(false);
								break;
						}
					}
				}
			}
		}
		
		if (!awayHuman && !homeHuman) {
			simStuff();
		}
		
		if (quarter.getText().equals("FINAL")) {
			awaySim.setVisible(false);
			homeSim.setVisible(false);
			awayPlay1.setVisible(false);
			awayPlay2.setVisible(false);
			awayPlay3.setVisible(false);
			awayPlay4.setVisible(false);
			awayPlay5.setVisible(false);
			homePlay1.setVisible(false);
			homePlay2.setVisible(false);
			homePlay3.setVisible(false);
			homePlay4.setVisible(false);
			homePlay5.setVisible(false);
		}
	}
	
	// Runs a normal offense vs. defense play if the away team has the ball
	private void awayPlay(int[] outcome, JavaFootballOffenseCard off, JavaFootballDefenseCard def, JFrame frame, JPanel panel) {
		if (outcome[1] != 0) {
			timeoutUsed = true;
		}
		if (outcome[2] == 2) {
			awayPossession.setVisible(false);
			homePossession.setVisible(true);
			if (overtimeNow) {
    			overtimeAwayPoss = true;
    			if (!(homeTeam instanceof NFLTeam)) {
    				kickoff(true, frame, panel);
    			}
    		}
			if (off.getName().contains("screenPass")) {
				lastPlay.setText(awayTeam.getTeamAbbr() + " screen pass INTERCEPTED and returned for a TOUCHDOWN by "
						+ homeTeam.getTeamAbbr() + "!");
			}
			else {
				lastPlay.setText(awayTeam.getTeamAbbr() + " run draw FUMBLED and returned for a TOUCHDOWN by "
						+ homeTeam.getTeamAbbr() + "!");
			}
			touchdown(false, frame, panel);
		}
		else if (outcome[2] == 1) {
			awayPossession.setVisible(false);
			homePossession.setVisible(true);
			if (overtimeNow) {
    			overtimeAwayPoss = true;
    			if (!(homeTeam instanceof NFLTeam)) {
    				kickoff(true, frame, panel);
    			}
    		}
			down = 1;
			yardsToGo = 10;
			if (!homeSideOfField && yardLine <= 10) {
				downs.setText("1st & GL");
				if (yardLine == 5) {
					yardsToGo = 5;
				}
			}
			else {
				downs.setText("1st & 10");
			}
			if (off.getName().contains("playAction")) {
				if (def.getName().equals("fumble")) {
					lastPlay.setText(awayTeam.getTeamAbbr() + " play action pass FUMBLED and recovered by "
							+ homeTeam.getTeamAbbr() + "!");
				}
				else {
					lastPlay.setText(awayTeam.getTeamAbbr() + " play action pass INTERCEPTED by "
							+ homeTeam.getTeamAbbr() + "!");
				}
			}
			else if (off.getName().contains("pass")) {
				lastPlay.setText(awayTeam.getTeamAbbr() + " pass INTERCEPTED by "
						+ homeTeam.getTeamAbbr() + "!");
			}
			else {
				lastPlay.setText(awayTeam.getTeamAbbr() + " FUMBLE recovered by "
						+ homeTeam.getTeamAbbr() + "!");
			}
			updatePosition();
			play(frame, panel);
		}
		else if (outcome[1] == 2) {
			down++;
			yardsToGo += 10;
			if (homeSideOfField) {
				yardLine += 10;
				if (yardLine > 50) {
					yardLine = 100 - yardLine;
					homeSideOfField = false;
				}
			}
			else {
				yardLine -= 10;
				homeSideOfField = false;
			}
			if (yardLine <= 0) {
				lastPlay.setText("10 yard PENALTY on " + awayTeam.getTeamAbbr() + ". SAFETY!!");
				if (overtimeNow) {
        			overtimeAwayPoss = true;
        			overtimeHomePoss = true;
        		}
				homeScore += 2;
				homePoints.setText("" + homeScore);
				if (overtimeNow) {
					if (overtimeNow && homeTeam instanceof NFLTeam) {
						lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
						quarter.setText("FINAL");
						awayTimeout1.setVisible(false);
						awayTimeout2.setVisible(false);
						awayTimeout3.setVisible(false);
						homeTimeout1.setVisible(false);
						homeTimeout2.setVisible(false);
						homeTimeout3.setVisible(false);
						awaySim.setVisible(false);
						homeSim.setVisible(false);
						awayKickoff.setVisible(false);
						awayOnsideKick.setVisible(false);
						awayPunt.setVisible(false);
						awayFieldGoal.setVisible(false);
						awayExtraPoint.setVisible(false);
						awayTwoPoint.setVisible(false);
						homeKickoff.setVisible(false);
						homeOnsideKick.setVisible(false);
						homePunt.setVisible(false);
						homeFieldGoal.setVisible(false);
						homeExtraPoint.setVisible(false);
						homeTwoPoint.setVisible(false);
						awayPlay1.setVisible(false);
						awayPlay2.setVisible(false);
						awayPlay3.setVisible(false);
						awayPlay4.setVisible(false);
						awayPlay5.setVisible(false);
						homePlay1.setVisible(false);
						homePlay2.setVisible(false);
						homePlay3.setVisible(false);
						homePlay4.setVisible(false);
						homePlay5.setVisible(false);
						JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
						returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
						returnToMainMenu.setBackground(Color.BLACK);
						returnToMainMenu.setForeground(Color.WHITE);
						returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
						returnToMainMenu.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								mainMenu.mainMenu();
							}
						});
						returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
					}
				}
				else {
					kickoff(true, frame, panel);
				}
			}
			else {
				if (down < 5) {
					if (down == 2) {
						if (downs.getText().contains("GL")) {
							downs.setText("2nd & GL");
						}
						else {
							downs.setText("2nd & " + yardsToGo);
						}
					}
					else if (down == 3) {
						if (downs.getText().contains("GL")) {
							downs.setText("3rd & GL");
						}
						else {
							downs.setText("3rd & " + yardsToGo);
						}
					}
					else {
						if (downs.getText().contains("GL")) {
							downs.setText("4th & GL");
						}
						else {
							downs.setText("4th & " + yardsToGo);
						}
					}
					lastPlay.setText("10 yard PENALTY on " + awayTeam.getTeamAbbr() + " and a LOSS OF DOWN!");
					updatePosition();
					play(frame, panel);
				}
				else {
					awayPossession.setVisible(false);
					homePossession.setVisible(true);
					down = 1;
					yardsToGo = 10;
					if (!homeSideOfField && yardLine <= 10) {
						downs.setText("1st & GL");
						if (yardLine == 5) {
							yardsToGo = 5;
						}
					}
					else {
						downs.setText("1st & 10");
					}
					lastPlay.setText("10 yard PENALTY on " + awayTeam.getTeamAbbr() + " and a LOSS OF DOWN."
							+ "TURNOVER ON DOWNS!");
					if (overtimeNow) {
	        			overtimeAwayPoss = true;
	        			if (!(homeTeam instanceof NFLTeam)) {
	        				kickoff(true, frame, panel);
	        			}
	        		}
					updatePosition();
					play(frame, panel);
				}
			}
		}
		else if (outcome[1] == 1) {
			yardsToGo -= outcome[0];
			if (homeSideOfField) {
				int temp = yardLine;
				yardLine -= outcome[0];
				if (yardLine > 50) {
					yardLine = 100 - yardLine;
					homeSideOfField = false;
				}
				else if (yardLine <= 0) {
					down = 1;
					yardsToGo = 5;
					downs.setText("1st & GL");
					yardLine = 5;
					position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
					int dist = temp - yardLine;
					lastPlay.setText(dist + " yard PENALTY on " + homeTeam.getTeamAbbr() + ". First down!");
				}
			}
			else {
				yardLine += outcome[0];
				if (yardLine > 50) {
					yardLine = 100 - yardLine;
					homeSideOfField = true;
				}
			}
			if (yardLine <= 0) {
				lastPlay.setText(Math.abs(outcome[0]) + " yard PENALTY on " + awayTeam.getTeamAbbr() + ". SAFETY!!");
				if (overtimeNow) {
        			overtimeAwayPoss = true;
        			overtimeHomePoss = true;
        		}
				homeScore += 2;
				homePoints.setText("" + homeScore);
				if (overtimeNow) {
					if (overtimeNow && homeTeam instanceof NFLTeam) {
						lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
						quarter.setText("FINAL");
						awayTimeout1.setVisible(false);
						awayTimeout2.setVisible(false);
						awayTimeout3.setVisible(false);
						homeTimeout1.setVisible(false);
						homeTimeout2.setVisible(false);
						homeTimeout3.setVisible(false);
						awaySim.setVisible(false);
						homeSim.setVisible(false);
						awayKickoff.setVisible(false);
						awayOnsideKick.setVisible(false);
						awayPunt.setVisible(false);
						awayFieldGoal.setVisible(false);
						awayExtraPoint.setVisible(false);
						awayTwoPoint.setVisible(false);
						homeKickoff.setVisible(false);
						homeOnsideKick.setVisible(false);
						homePunt.setVisible(false);
						homeFieldGoal.setVisible(false);
						homeExtraPoint.setVisible(false);
						homeTwoPoint.setVisible(false);
						awayPlay1.setVisible(false);
						awayPlay2.setVisible(false);
						awayPlay3.setVisible(false);
						awayPlay4.setVisible(false);
						awayPlay5.setVisible(false);
						homePlay1.setVisible(false);
						homePlay2.setVisible(false);
						homePlay3.setVisible(false);
						homePlay4.setVisible(false);
						homePlay5.setVisible(false);
						JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
						returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
						returnToMainMenu.setBackground(Color.BLACK);
						returnToMainMenu.setForeground(Color.WHITE);
						returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
						returnToMainMenu.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								mainMenu.mainMenu();
							}
						});
						returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
					}
				}
				else {
					kickoff(true, frame, panel);
				}
			}
			else {
				if (yardsToGo <= 0) {
					down = 1;
					yardsToGo = 10;
					if (homeSideOfField && yardLine <= 10) {
						downs.setText("1st & GL");
						if (yardLine == 5) {
							yardsToGo = 5;
						}
					}
					else {
						downs.setText("1st & 10");
					}
					lastPlay.setText(Math.abs(outcome[0]) + " yard PENALTY on " + homeTeam.getTeamAbbr() + ". First down!");
				}
				else {
					if (downs.getText().contains("GL")) {
						if (down == 1) {
							downs.setText("1st & GL");
						}
						else if (down == 2) {
							downs.setText("2nd & GL");
						}
						else if (down == 3) {
							downs.setText("3rd & GL");
						}
						else {
							downs.setText("4th & GL");
						}
					}
					else {
						if (down == 1) {
							downs.setText("1st & " + yardsToGo);
						}
						else if (down == 2) {
							downs.setText("2nd & " + yardsToGo);
						}
						else if (down == 3) {
							downs.setText("3rd & " + yardsToGo);
						}
						else {
							downs.setText("4th & " + yardsToGo);
						}
					}
					if (outcome[0] > 0) {
						lastPlay.setText(Math.abs(outcome[0]) + " yard PENALTY on " + homeTeam.getTeamAbbr() + ". Replay down.");
					}
					else {
						lastPlay.setText(Math.abs(outcome[0]) + " yard PENALTY on " + awayTeam.getTeamAbbr() + ". Replay down.");
					}
				}
				int i = 0;
				boolean offenseReplenished = false;
				while (!offenseReplenished && i < 5) {
					if (awayOffenseCards[i] == null) {
						awayOffenseCards[i] = drawOffenseCard(false);
						offenseReplenished = true;
					}
					else {
						i++;
					}
				}
				if (offenseReplenished) {
					switch (i) {
						case 0:
							awayPlay1.setEnabled(true);
							break;
						case 1:
							awayPlay2.setEnabled(true);
							break;
						case 2:
							awayPlay3.setEnabled(true);
							break;
						case 3:
							awayPlay4.setEnabled(true);
							break;
						case 4:
							awayPlay5.setEnabled(true);
							break;
					}
				}
				i = 0;
				boolean defenseReplenished = false;
				while (!defenseReplenished && i < 5) {
					if (homeDefenseCards[i] == null) {
						homeDefenseCards[i] = drawDefenseCard(true);
						defenseReplenished = true;
					}
					else {
						i++;
					}
				}
				if (defenseReplenished) {
					switch (i) {
						case 0:
							homePlay1.setEnabled(true);
							break;
						case 1:
							homePlay2.setEnabled(true);
							break;
						case 2:
							homePlay3.setEnabled(true);
							break;
						case 3:
							homePlay4.setEnabled(true);
							break;
						case 4:
							homePlay5.setEnabled(true);
							break;
					}
				}
				updatePosition();
				play(frame, panel);
			}
		}
		else {
			down++;
			yardsToGo -= outcome[0];
			if (off.getName().contains("playAction")) {
				if (outcome[0] > 0) {
					if (homeSideOfField) {
						yardLine -= outcome[0];
						if (yardLine <= 0) {
							int yardsGained = outcome[0] + yardLine;
							lastPlay.setText(yardsGained + " yard play action pass by " + awayTeam.getTeamAbbr() +
									". TOUCHDOWN!!!");
							touchdown(true, frame, panel);
						}
						else {
							if (yardsToGo <= 0) {
								down = 1;
    							yardsToGo = 10;
    							if (homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
								lastPlay.setText(outcome[0] + " yard play action pass by " + awayTeam.getTeamAbbr() +
										". First down!");
								updatePosition();
    							play(frame, panel);
							}
							else if (down > 4) {
								down = 1;
    							yardsToGo = 10;
    							if (!homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(false);
    							homePossession.setVisible(true);
    							lastPlay.setText(outcome[0] + " yard play action pass by " + awayTeam.getTeamAbbr() +
    									". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeAwayPoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(true, frame, panel);
    			        			}
    			        		}
    							updatePosition();
    							play(frame, panel);
							}
							else {
								if (downs.getText().contains("GL")) {
									if (down == 2) {
										downs.setText("2nd & GL");
									}
									else if (down == 3) {
										downs.setText("3rd & GL");
									}
									else {
										downs.setText("4th & GL");
									}
								}
								else {
									if (down == 2) {
										downs.setText("2nd & " + yardsToGo);
									}
									else if (down == 3) {
										downs.setText("3rd & " + yardsToGo);
									}
									else {
										downs.setText("4th & " + yardsToGo);
									}
								}
								lastPlay.setText(outcome[0] + " yard play action pass by " + awayTeam.getTeamAbbr() + ".");
								updatePosition();
								play(frame, panel);
							}
						}
					}
					else {
						yardLine += outcome[0];
						if (yardLine > 100) {
							int yardsGained = outcome[0] + 100 - yardLine;
							lastPlay.setText(yardsGained + " yard play action pass by " + awayTeam.getTeamAbbr() +
									". TOUCHDOWN!!!");
							touchdown(true, frame, panel);
						}
						else {
							if (yardLine > 50) {
    							yardLine = 100 - yardLine;
    							homeSideOfField = true;
							}
							if (yardsToGo <= 0) {
								down = 1;
    							yardsToGo = 10;
    							if (homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
								lastPlay.setText(outcome[0] + " yard play action pass by " + awayTeam.getTeamAbbr() +
										". First down!");
							}
							else if (down > 4) {
								down = 1;
    							yardsToGo = 10;
    							if (!homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(false);
    							homePossession.setVisible(true);
    							lastPlay.setText(outcome[0] + " yard play action pass by " + awayTeam.getTeamAbbr() +
    									". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeAwayPoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(true, frame, panel);
    			        			}
    			        		}
							}
							else {
								if (downs.getText().contains("GL")) {
									if (down == 2) {
										downs.setText("2nd & GL");
									}
									else if (down == 3) {
										downs.setText("3rd & GL");
									}
									else {
										downs.setText("4th & GL");
									}
								}
								else {
									if (down == 2) {
										downs.setText("2nd & " + yardsToGo);
									}
									else if (down == 3) {
										downs.setText("3rd & " + yardsToGo);
									}
									else {
										downs.setText("4th & " + yardsToGo);
									}
								}
								lastPlay.setText(outcome[0] + " yard play action pass by " + awayTeam.getTeamAbbr() + ".");
							}
							updatePosition();
							play(frame, panel);
						}
					}
				}
				else if (outcome[0] == 0) {
					if (down == 2) {
						if (downs.getText().contains("GL")) {
							downs.setText("2nd & GL");
						}
						else {
							downs.setText("2nd & " + yardsToGo);
						}
						lastPlay.setText("No gain on the play action pass by " + awayTeam.getTeamAbbr() + ".");
					}
					else if (down == 3) {
						if (downs.getText().contains("GL")) {
							downs.setText("3rd & GL");
						}
						else {
							downs.setText("3rd & " + yardsToGo);
						}
						lastPlay.setText("No gain on the play action pass by " + awayTeam.getTeamAbbr() + ".");
					}
					else if (down == 4) {
						if (downs.getText().contains("GL")) {
							downs.setText("4th & GL");
						}
						else {
							downs.setText("4th & " + yardsToGo);
						}
						lastPlay.setText("No gain on the play action pass by " + awayTeam.getTeamAbbr() + ".");
					}
					else {
						down = 1;
						yardsToGo = 10;
						if (!homeSideOfField && yardLine <= 10) {
							downs.setText("1st & GL");
							if (yardLine == 5) {
								yardsToGo = 5;
							}
						}
						else {
							downs.setText("1st & 10");
						}
						awayPossession.setVisible(false);
						homePossession.setVisible(true);
						lastPlay.setText("No gain on the play action pass by " + awayTeam.getTeamAbbr()
						+ ". TURNOVER ON DOWNS!");
						if (overtimeNow) {
		        			overtimeAwayPoss = true;
		        			if (!(homeTeam instanceof NFLTeam)) {
		        				kickoff(true, frame, panel);
		        			}
		        		}
					}
					updatePosition();
					play(frame, panel);
				}
				else {
					if (homeSideOfField) {
						yardLine -= outcome[0];
						if (yardLine > 50) {
							yardLine = 100 - yardLine;
							homeSideOfField = false;
						}
						if (down == 2) {
							if (downs.getText().contains("GL")) {
								downs.setText("2nd & GL");
							}
							else {
								downs.setText("2nd & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + homeTeam.getTeamAbbr() + ".");
						}
						else if (down == 3) {
							if (downs.getText().contains("GL")) {
								downs.setText("3rd & GL");
							}
							else {
								downs.setText("3rd & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + homeTeam.getTeamAbbr() + ".");
						}
						else if (down == 4) {
							if (downs.getText().contains("GL")) {
								downs.setText("4th & GL");
							}
							else {
								downs.setText("4th & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + homeTeam.getTeamAbbr() + ".");
						}
						else {
							down = 1;
							yardsToGo = 10;
							if (!homeSideOfField && yardLine <= 10) {
								downs.setText("1st & GL");
								if (yardLine == 5) {
									yardsToGo = 5;
								}
							}
							else {
								downs.setText("1st & 10");
							}
							awayPossession.setVisible(false);
							homePossession.setVisible(true);
							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + homeTeam.getTeamAbbr()
							+ ". TURNOVER ON DOWNS!");
							if (overtimeNow) {
			        			overtimeAwayPoss = true;
			        			if (!(homeTeam instanceof NFLTeam)) {
			        				kickoff(true, frame, panel);
			        			}
			        		}
						}
						updatePosition();
						play(frame, panel);
					}
					else {
						yardLine += outcome[0];
						if (yardLine <= 0) {
        					lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + homeTeam.getTeamAbbr()
        					+ ". SAFETY!!");
        					if (overtimeNow) {
        	        			overtimeAwayPoss = true;
        	        			overtimeHomePoss = true;
        	        		}
        					homeScore += 2;
        					homePoints.setText("" + homeScore);
        					if (overtimeNow) {
        						if (overtimeNow && homeTeam instanceof NFLTeam) {
        							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
        							quarter.setText("FINAL");
        							awayTimeout1.setVisible(false);
        							awayTimeout2.setVisible(false);
        							awayTimeout3.setVisible(false);
        							homeTimeout1.setVisible(false);
        							homeTimeout2.setVisible(false);
        							homeTimeout3.setVisible(false);
        							awaySim.setVisible(false);
        							homeSim.setVisible(false);
        							awayKickoff.setVisible(false);
        							awayOnsideKick.setVisible(false);
        							awayPunt.setVisible(false);
        							awayFieldGoal.setVisible(false);
        							awayExtraPoint.setVisible(false);
        							awayTwoPoint.setVisible(false);
        							homeKickoff.setVisible(false);
        							homeOnsideKick.setVisible(false);
        							homePunt.setVisible(false);
        							homeFieldGoal.setVisible(false);
        							homeExtraPoint.setVisible(false);
        							homeTwoPoint.setVisible(false);
        							awayPlay1.setVisible(false);
        							awayPlay2.setVisible(false);
        							awayPlay3.setVisible(false);
        							awayPlay4.setVisible(false);
        							awayPlay5.setVisible(false);
        							homePlay1.setVisible(false);
        							homePlay2.setVisible(false);
        							homePlay3.setVisible(false);
        							homePlay4.setVisible(false);
        							homePlay5.setVisible(false);
        							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
        							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        							returnToMainMenu.setBackground(Color.BLACK);
        							returnToMainMenu.setForeground(Color.WHITE);
        							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
        							returnToMainMenu.addActionListener(new ActionListener() {
        								public void actionPerformed(ActionEvent e) {
        									mainMenu.mainMenu();
        								}
        							});
        							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
        						}
        					}
        					else {
        						kickoff(true, frame, panel);
        					}
						}
						else {
    						if (down == 2) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("2nd & GL");
    							}
    							else {
    								downs.setText("2nd & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + homeTeam.getTeamAbbr() + ".");
    						}
    						else if (down == 3) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("3rd & GL");
    							}
    							else {
    								downs.setText("3rd & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + homeTeam.getTeamAbbr() + ".");
    						}
    						else if (down == 4) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("4th & GL");
    							}
    							else {
    								downs.setText("4th & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + homeTeam.getTeamAbbr() + ".");
    						}
    						else {
    							down = 1;
    							yardsToGo = 10;
    							if (!homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(false);
    							homePossession.setVisible(true);
    							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + homeTeam.getTeamAbbr()
    							+ ". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeAwayPoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(true, frame, panel);
    			        			}
    			        		}
    						}
    						updatePosition();
    						play(frame, panel);
						}
					}
				}
			}
			else if (off.getName().contains("screenPass")) {
				if (outcome[0] > 0) {
					if (homeSideOfField) {
						yardLine -= outcome[0];
						if (yardLine <= 0) {
							int yardsGained = outcome[0] + yardLine;
							lastPlay.setText(yardsGained + " yard screen pass by " + awayTeam.getTeamAbbr() +
									". TOUCHDOWN!!!");
							touchdown(true, frame, panel);
						}
						else {
							if (yardsToGo <= 0) {
								down = 1;
    							yardsToGo = 10;
    							if (homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
								lastPlay.setText(outcome[0] + " yard screen pass by " + awayTeam.getTeamAbbr() +
										". First down!");
								updatePosition();
    							play(frame, panel);
							}
							else if (down > 4) {
								down = 1;
    							yardsToGo = 10;
    							if (!homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(false);
    							homePossession.setVisible(true);
    							lastPlay.setText(outcome[0] + " yard screen pass by " + awayTeam.getTeamAbbr() +
    									". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeAwayPoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(true, frame, panel);
    			        			}
    			        		}
    							updatePosition();
    							play(frame, panel);
							}
							else {
								if (down == 2) {
									if (downs.getText().contains("GL")) {
										downs.setText("2nd & GL");
									}
									else {
										downs.setText("2nd & " + yardsToGo);
									}
								}
								else if (down == 3) {
									if (downs.getText().contains("GL")) {
										downs.setText("3rd & GL");
									}
									else {
										downs.setText("3rd & " + yardsToGo);
									}
								}
								else {
									if (downs.getText().contains("GL")) {
										downs.setText("4th & GL");
									}
									else {
										downs.setText("4th & " + yardsToGo);
									}
								}
								lastPlay.setText(outcome[0] + " yard screen pass by " + awayTeam.getTeamAbbr() + ".");
								updatePosition();
								play(frame, panel);
							}
						}
					}
					else {
						yardLine += outcome[0];
						if (yardLine > 100) {
							int yardsGained = outcome[0] + 100 - yardLine;
							lastPlay.setText(yardsGained + " yard screen pass by " + awayTeam.getTeamAbbr() +
									". TOUCHDOWN!!!");
							touchdown(true, frame, panel);
						}
						else {
							if (yardLine > 50) {
    							yardLine = 100 - yardLine;
    							homeSideOfField = true;
							}
							if (yardsToGo <= 0) {
								down = 1;
    							yardsToGo = 10;
    							if (homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
								lastPlay.setText(outcome[0] + " yard screen pass by " + awayTeam.getTeamAbbr() +
										". First down!");
							}
							else if (down > 4) {
								down = 1;
    							yardsToGo = 10;
    							if (!homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(false);
    							homePossession.setVisible(true);
    							lastPlay.setText(outcome[0] + " yard screen pass by " + awayTeam.getTeamAbbr() +
    									". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeAwayPoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(true, frame, panel);
    			        			}
    			        		}
							}
							else {
								if (down == 2) {
									if (downs.getText().contains("GL")) {
										downs.setText("2nd & GL");
									}
									else {
										downs.setText("2nd & " + yardsToGo);
									}
									lastPlay.setText("No gain on the screen pass by " + awayTeam.getTeamAbbr() + ".");
								}
								else if (down == 3) {
									if (downs.getText().contains("GL")) {
										downs.setText("3rd & GL");
									}
									else {
										downs.setText("3rd & " + yardsToGo);
									}
									lastPlay.setText("No gain on the screen pass by " + awayTeam.getTeamAbbr() + ".");
								}
								else {
									if (downs.getText().contains("GL")) {
										downs.setText("4th & GL");
									}
									else {
										downs.setText("4th & " + yardsToGo);
									}
									lastPlay.setText("No gain on the screen pass by " + awayTeam.getTeamAbbr() + ".");
								}
								lastPlay.setText(outcome[0] + " yard screen pass by " + awayTeam.getTeamAbbr() + ".");
							}
							updatePosition();
							play(frame, panel);
						}
					}
				}
				else if (outcome[0] == 0) {
					if (down == 2) {
						if (downs.getText().contains("GL")) {
							downs.setText("2nd & GL");
						}
						else {
							downs.setText("2nd & " + yardsToGo);
						}
						lastPlay.setText("No gain on the screen pass by " + awayTeam.getTeamAbbr() + ".");
					}
					else if (down == 3) {
						if (downs.getText().contains("GL")) {
							downs.setText("3rd & GL");
						}
						else {
							downs.setText("3rd & " + yardsToGo);
						}
						lastPlay.setText("No gain on the screen pass by " + awayTeam.getTeamAbbr() + ".");
					}
					else if (down == 4) {
						if (downs.getText().contains("GL")) {
							downs.setText("4th & GL");
						}
						else {
							downs.setText("4th & " + yardsToGo);
						}
						lastPlay.setText("No gain on the screen pass by " + awayTeam.getTeamAbbr() + ".");
					}
					else {
						down = 1;
						yardsToGo = 10;
						if (!homeSideOfField && yardLine <= 10) {
							downs.setText("1st & GL");
							if (yardLine == 5) {
								yardsToGo = 5;
							}
						}
						else {
							downs.setText("1st & 10");
						}
						awayPossession.setVisible(false);
						homePossession.setVisible(true);
						lastPlay.setText("No gain on the screen pass by " + awayTeam.getTeamAbbr()
						+ ". TURNOVER ON DOWNS!");
						if (overtimeNow) {
		        			overtimeAwayPoss = true;
		        			if (!(homeTeam instanceof NFLTeam)) {
		        				kickoff(true, frame, panel);
		        			}
		        		}
					}
					updatePosition();
					play(frame, panel);
				}
				else {
					if (homeSideOfField) {
						yardLine -= outcome[0];
						if (yardLine > 50) {
							yardLine = 100 - yardLine;
							homeSideOfField = false;
						}
						if (down == 2) {
							if (downs.getText().contains("GL")) {
								downs.setText("2nd & GL");
							}
							else {
								downs.setText("2nd & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard LOSS on the screen pass by "
									+ awayTeam.getTeamAbbr() + ".");
						}
						else if (down == 3) {
							if (downs.getText().contains("GL")) {
								downs.setText("3rd & GL");
							}
							else {
								downs.setText("3rd & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard LOSS on the screen pass by "
									+ awayTeam.getTeamAbbr() + ".");
						}
						else if (down == 4) {
							if (downs.getText().contains("GL")) {
								downs.setText("4th & GL");
							}
							else {
								downs.setText("4th & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard LOSS on the screen pass by "
									+ awayTeam.getTeamAbbr() + ".");
						}
						else {
							down = 1;
							yardsToGo = 10;
							if (!homeSideOfField && yardLine <= 10) {
								downs.setText("1st & GL");
								if (yardLine == 5) {
									yardsToGo = 5;
								}
							}
							else {
								downs.setText("1st & 10");
							}
							awayPossession.setVisible(false);
							homePossession.setVisible(true);
							lastPlay.setText(Math.abs(outcome[0]) + " yard LOSS on the screen pass by " + awayTeam.getTeamAbbr()
							+ ". TURNOVER ON DOWNS!");
							if (overtimeNow) {
			        			overtimeAwayPoss = true;
			        			if (!(homeTeam instanceof NFLTeam)) {
			        				kickoff(true, frame, panel);
			        			}
			        		}
						}
						updatePosition();
						play(frame, panel);
					}
					else {
						yardLine += outcome[0];
						if (yardLine <= 0) {
        					lastPlay.setText(Math.abs(outcome[0]) + " yard LOSS on the screen pass by " + awayTeam.getTeamAbbr()
        					+ ". SAFETY!!");
        					if (overtimeNow) {
        	        			overtimeAwayPoss = true;
        	        			overtimeHomePoss = true;
        	        		}
        					homeScore += 2;
        					homePoints.setText("" + homeScore);
        					if (overtimeNow) {
        						if (overtimeNow && homeTeam instanceof NFLTeam) {
        							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
        							quarter.setText("FINAL");
        							awayTimeout1.setVisible(false);
        							awayTimeout2.setVisible(false);
        							awayTimeout3.setVisible(false);
        							homeTimeout1.setVisible(false);
        							homeTimeout2.setVisible(false);
        							homeTimeout3.setVisible(false);
        							awaySim.setVisible(false);
        							homeSim.setVisible(false);
        							awayKickoff.setVisible(false);
        							awayOnsideKick.setVisible(false);
        							awayPunt.setVisible(false);
        							awayFieldGoal.setVisible(false);
        							awayExtraPoint.setVisible(false);
        							awayTwoPoint.setVisible(false);
        							homeKickoff.setVisible(false);
        							homeOnsideKick.setVisible(false);
        							homePunt.setVisible(false);
        							homeFieldGoal.setVisible(false);
        							homeExtraPoint.setVisible(false);
        							homeTwoPoint.setVisible(false);
        							awayPlay1.setVisible(false);
        							awayPlay2.setVisible(false);
        							awayPlay3.setVisible(false);
        							awayPlay4.setVisible(false);
        							awayPlay5.setVisible(false);
        							homePlay1.setVisible(false);
        							homePlay2.setVisible(false);
        							homePlay3.setVisible(false);
        							homePlay4.setVisible(false);
        							homePlay5.setVisible(false);
        							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
        							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        							returnToMainMenu.setBackground(Color.BLACK);
        							returnToMainMenu.setForeground(Color.WHITE);
        							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
        							returnToMainMenu.addActionListener(new ActionListener() {
        								public void actionPerformed(ActionEvent e) {
        									mainMenu.mainMenu();
        								}
        							});
        							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
        						}
        					}
        					else {
        						kickoff(true, frame, panel);
        					}
						}
						else {
    						if (down == 2) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("2nd & GL");
    							}
    							else {
    								downs.setText("2nd & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard LOSS by " + awayTeam.getTeamAbbr() + ".");
    						}
    						else if (down == 3) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("3rd & GL");
    							}
    							else {
    								downs.setText("3rd & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard LOSS by " + awayTeam.getTeamAbbr() + ".");
    						}
    						else if (down == 4) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("4th & GL");
    							}
    							else {
    								downs.setText("4th & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard LOSS by " + awayTeam.getTeamAbbr() + ".");
    						}
    						else {
    							down = 1;
    							yardsToGo = 10;
    							if (!homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(false);
    							homePossession.setVisible(true);
    							lastPlay.setText(Math.abs(outcome[0]) + " yard LOSS by " + awayTeam.getTeamAbbr()
    							+ ". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeAwayPoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(true, frame, panel);
    			        			}
    			        		}
    						}
    						updatePosition();
    						play(frame, panel);
						}
					}
				}
			}
			else if (off.getName().contains("runDraw")) {
				if (outcome[0] > 0) {
					if (homeSideOfField) {
						yardLine -= outcome[0];
						if (yardLine <= 0) {
							int yardsGained = outcome[0] + yardLine;
							lastPlay.setText(yardsGained + " yard run draw by " + awayTeam.getTeamAbbr() +
									". TOUCHDOWN!!!");
							touchdown(true, frame, panel);
						}
						else {
							if (yardsToGo <= 0) {
								down = 1;
    							yardsToGo = 10;
    							if (homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
								lastPlay.setText(outcome[0] + " yard run draw by " + awayTeam.getTeamAbbr() +
										". First down!");
								updatePosition();
    							play(frame, panel);
							}
							else if (down > 4) {
								down = 1;
    							yardsToGo = 10;
    							if (!homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(false);
    							homePossession.setVisible(true);
    							lastPlay.setText(outcome[0] + " yard run draw by " + awayTeam.getTeamAbbr() +
    									". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeAwayPoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(true, frame, panel);
    			        			}
    			        		}
    							updatePosition();
    							play(frame, panel);
							}
							else {
								if (down == 2) {
									if (downs.getText().contains("GL")) {
										downs.setText("2nd & GL");
									}
									else {
										downs.setText("2nd & " + yardsToGo);
									}
								}
								else if (down == 3) {
									if (downs.getText().contains("GL")) {
										downs.setText("3rd & GL");
									}
									else {
										downs.setText("3rd & " + yardsToGo);
									}
								}
								else {
									if (downs.getText().contains("GL")) {
										downs.setText("4th & GL");
									}
									else {
										downs.setText("4th & " + yardsToGo);
									}
								}
								lastPlay.setText(outcome[0] + " yard run draw by " + awayTeam.getTeamAbbr() + ".");
								updatePosition();
								play(frame, panel);
							}
						}
					}
					else {
						yardLine += outcome[0];
						if (yardLine > 100) {
							int yardsGained = outcome[0] + 100 - yardLine;
							lastPlay.setText(yardsGained + " yard run draw by " + awayTeam.getTeamAbbr() +
									". TOUCHDOWN!!!");
							touchdown(true, frame, panel);
						}
						else {
							if (yardLine > 50) {
    							yardLine = 100 - yardLine;
    							homeSideOfField = true;
							}
							if (yardsToGo <= 0) {
								down = 1;
    							yardsToGo = 10;
    							if (homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
								lastPlay.setText(outcome[0] + " yard run draw by " + awayTeam.getTeamAbbr() +
										". First down!");
							}
							else if (down > 4) {
								down = 1;
    							yardsToGo = 10;
    							if (!homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(false);
    							homePossession.setVisible(true);
    							lastPlay.setText(outcome[0] + " yard run draw by " + awayTeam.getTeamAbbr() +
    									". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeAwayPoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(true, frame, panel);
    			        			}
    			        		}
							}
							else {
								if (down == 2) {
									if (downs.getText().contains("GL")) {
										downs.setText("2nd & GL");
									}
									else {
										downs.setText("2nd & " + yardsToGo);
									}
								}
								else if (down == 3) {
									if (downs.getText().contains("GL")) {
										downs.setText("3rd & GL");
									}
									else {
										downs.setText("3rd & " + yardsToGo);
									}
								}
								else {
									if (downs.getText().contains("GL")) {
										downs.setText("4th & GL");
									}
									else {
										downs.setText("4th & " + yardsToGo);
									}
								}
								lastPlay.setText(outcome[0] + " yard run draw by " + awayTeam.getTeamAbbr() + ".");
							}
							updatePosition();
							play(frame, panel);
						}
					}
				}
				else if (outcome[0] == 0) {
					if (down == 2) {
						if (downs.getText().contains("GL")) {
							downs.setText("2nd & GL");
						}
						else {
							downs.setText("2nd & " + yardsToGo);
						}
						lastPlay.setText("No gain on the run draw by " + awayTeam.getTeamAbbr() + ".");
					}
					else if (down == 3) {
						if (downs.getText().contains("GL")) {
							downs.setText("3rd & GL");
						}
						else {
							downs.setText("3rd & " + yardsToGo);
						}
						lastPlay.setText("No gain on the run draw by " + awayTeam.getTeamAbbr() + ".");
					}
					else if (down == 4) {
						if (downs.getText().contains("GL")) {
							downs.setText("4th & GL");
						}
						else {
							downs.setText("4th & " + yardsToGo);
						}
						lastPlay.setText("No gain on the run draw by " + awayTeam.getTeamAbbr() + ".");
					}
					else {
						down = 1;
						yardsToGo = 10;
						if (!homeSideOfField && yardLine <= 10) {
							downs.setText("1st & GL");
							if (yardLine == 5) {
								yardsToGo = 5;
							}
						}
						else {
							downs.setText("1st & 10");
						}
						awayPossession.setVisible(false);
						homePossession.setVisible(true);
						lastPlay.setText("No gain on the run draw by " + awayTeam.getTeamAbbr()
						+ ". TURNOVER ON DOWNS!");
						if (overtimeNow) {
		        			overtimeAwayPoss = true;
		        			if (!(homeTeam instanceof NFLTeam)) {
		        				kickoff(true, frame, panel);
		        			}
		        		}
					}
					updatePosition();
					play(frame, panel);
				}
				else {
					if (homeSideOfField) {
						yardLine -= outcome[0];
						if (yardLine > 50) {
							yardLine = 100 - yardLine;
							homeSideOfField = false;
						}
						if (down == 2) {
							if (downs.getText().contains("GL")) {
								downs.setText("2nd & GL");
							}
							else {
								downs.setText("2nd & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + homeTeam.getTeamAbbr() + ".");
						}
						else if (down == 3) {
							if (downs.getText().contains("GL")) {
								downs.setText("3rd & GL");
							}
							else {
								downs.setText("3rd & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + homeTeam.getTeamAbbr() + ".");
						}
						else if (down == 4) {
							if (downs.getText().contains("GL")) {
								downs.setText("4th & GL");
							}
							else {
								downs.setText("4th & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + homeTeam.getTeamAbbr() + ".");
						}
						else {
							down = 1;
							yardsToGo = 10;
							if (!homeSideOfField && yardLine <= 10) {
								downs.setText("1st & GL");
								if (yardLine == 5) {
									yardsToGo = 5;
								}
							}
							else {
								downs.setText("1st & 10");
							}
							awayPossession.setVisible(false);
							homePossession.setVisible(true);
							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + homeTeam.getTeamAbbr()
							+ ". TURNOVER ON DOWNS!");
							if (overtimeNow) {
			        			overtimeAwayPoss = true;
			        			if (!(homeTeam instanceof NFLTeam)) {
			        				kickoff(true, frame, panel);
			        			}
			        		}
						}
						updatePosition();
						play(frame, panel);
					}
					else {
						yardLine += outcome[0];
						if (yardLine <= 0) {
        					lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + homeTeam.getTeamAbbr()
        					+ ". SAFETY!!");
        					if (overtimeNow) {
        	        			overtimeAwayPoss = true;
        	        			overtimeHomePoss = true;
        	        		}
        					homeScore += 2;
        					homePoints.setText("" + homeScore);
        					if (overtimeNow) {
        						if (overtimeNow && homeTeam instanceof NFLTeam) {
        							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
        							quarter.setText("FINAL");
        							awayTimeout1.setVisible(false);
        							awayTimeout2.setVisible(false);
        							awayTimeout3.setVisible(false);
        							homeTimeout1.setVisible(false);
        							homeTimeout2.setVisible(false);
        							homeTimeout3.setVisible(false);
        							awaySim.setVisible(false);
        							homeSim.setVisible(false);
        							awayKickoff.setVisible(false);
        							awayOnsideKick.setVisible(false);
        							awayPunt.setVisible(false);
        							awayFieldGoal.setVisible(false);
        							awayExtraPoint.setVisible(false);
        							awayTwoPoint.setVisible(false);
        							homeKickoff.setVisible(false);
        							homeOnsideKick.setVisible(false);
        							homePunt.setVisible(false);
        							homeFieldGoal.setVisible(false);
        							homeExtraPoint.setVisible(false);
        							homeTwoPoint.setVisible(false);
        							awayPlay1.setVisible(false);
        							awayPlay2.setVisible(false);
        							awayPlay3.setVisible(false);
        							awayPlay4.setVisible(false);
        							awayPlay5.setVisible(false);
        							homePlay1.setVisible(false);
        							homePlay2.setVisible(false);
        							homePlay3.setVisible(false);
        							homePlay4.setVisible(false);
        							homePlay5.setVisible(false);
        							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
        							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        							returnToMainMenu.setBackground(Color.BLACK);
        							returnToMainMenu.setForeground(Color.WHITE);
        							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
        							returnToMainMenu.addActionListener(new ActionListener() {
        								public void actionPerformed(ActionEvent e) {
        									mainMenu.mainMenu();
        								}
        							});
        							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
        						}
        					}
        					else {
        						kickoff(true, frame, panel);
        					}
						}
						else {
    						if (down == 2) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("2nd & GL");
    							}
    							else {
    								downs.setText("2nd & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + homeTeam.getTeamAbbr() + ".");
    						}
    						else if (down == 3) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("3rd & GL");
    							}
    							else {
    								downs.setText("3rd & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + homeTeam.getTeamAbbr() + ".");
    						}
    						else if (down == 4) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("4th & GL");
    							}
    							else {
    								downs.setText("4th & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + homeTeam.getTeamAbbr() + ".");
    						}
    						else {
    							down = 1;
    							yardsToGo = 10;
    							if (!homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(false);
    							homePossession.setVisible(true);
    							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + homeTeam.getTeamAbbr()
    							+ ". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeAwayPoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(true, frame, panel);
    			        			}
    			        		}
    						}
    						updatePosition();
    						play(frame, panel);
						}
					}
				}
			}
			else if (off.getName().contains("run")) {
				if (outcome[0] > 0) {
					if (homeSideOfField) {
						yardLine -= outcome[0];
						if (yardLine <= 0) {
							int yardsGained = outcome[0] + yardLine;
							lastPlay.setText(yardsGained + " yard run by " + awayTeam.getTeamAbbr() +
									". TOUCHDOWN!!!");
							touchdown(true, frame, panel);
						}
						else {
							if (yardsToGo <= 0) {
								down = 1;
    							yardsToGo = 10;
    							if (homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
								lastPlay.setText(outcome[0] + " yard run by " + awayTeam.getTeamAbbr() +
										". First down!");
								updatePosition();
    							play(frame, panel);
							}
							else if (down > 4) {
								down = 1;
    							yardsToGo = 10;
    							if (!homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(false);
    							homePossession.setVisible(true);
    							lastPlay.setText(outcome[0] + " yard run by " + awayTeam.getTeamAbbr() +
    									". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeAwayPoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(true, frame, panel);
    			        			}
    			        		}
    							updatePosition();
    							play(frame, panel);
							}
							else {
								if (down == 2) {
									if (downs.getText().contains("GL")) {
										downs.setText("2nd & GL");
									}
									else {
										downs.setText("2nd & " + yardsToGo);
									}
								}
								else if (down == 3) {
									if (downs.getText().contains("GL")) {
										downs.setText("3rd & GL");
									}
									else {
										downs.setText("3rd & " + yardsToGo);
									}
								}
								else {
									if (downs.getText().contains("GL")) {
										downs.setText("4th & GL");
									}
									else {
										downs.setText("4th & " + yardsToGo);
									}
								}
								lastPlay.setText(outcome[0] + " yard run by " + awayTeam.getTeamAbbr() + ".");
								updatePosition();
								play(frame, panel);
							}
						}
					}
					else {
						yardLine += outcome[0];
						if (yardLine > 100) {
							int yardsGained = outcome[0] + 100 - yardLine;
							lastPlay.setText(yardsGained + " yard run by " + awayTeam.getTeamAbbr() +
									". TOUCHDOWN!!!");
							touchdown(true, frame, panel);
						}
						else {
							if (yardLine > 50) {
    							yardLine = 100 - yardLine;
    							homeSideOfField = true;
							}
							if (yardsToGo <= 0) {
								down = 1;
    							yardsToGo = 10;
    							if (homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
								lastPlay.setText(outcome[0] + " yard run by " + awayTeam.getTeamAbbr() +
										". First down!");
							}
							else if (down > 4) {
								down = 1;
    							yardsToGo = 10;
    							if (!homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(false);
    							homePossession.setVisible(true);
    							lastPlay.setText(outcome[0] + " yard run by " + awayTeam.getTeamAbbr() +
    									". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeAwayPoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(true, frame, panel);
    			        			}
    			        		}
							}
							else {
								if (down == 2) {
									if (downs.getText().contains("GL")) {
										downs.setText("2nd & GL");
									}
									else {
										downs.setText("2nd & " + yardsToGo);
									}
								}
								else if (down == 3) {
									if (downs.getText().contains("GL")) {
										downs.setText("3rd & GL");
									}
									else {
										downs.setText("3rd & " + yardsToGo);
									}
								}
								else {
									if (downs.getText().contains("GL")) {
										downs.setText("4th & GL");
									}
									else {
										downs.setText("4th & " + yardsToGo);
									}
								}
								lastPlay.setText(outcome[0] + " yard run by " + awayTeam.getTeamAbbr() + ".");
							}
							updatePosition();
							play(frame, panel);
						}
					}
				}
				else if (outcome[0] == 0) {
					if (down == 2) {
						if (downs.getText().contains("GL")) {
							downs.setText("2nd & GL");
						}
						else {
							downs.setText("2nd & " + yardsToGo);
						}
						lastPlay.setText("No gain on the run by " + awayTeam.getTeamAbbr() + ".");
					}
					else if (down == 3) {
						if (downs.getText().contains("GL")) {
							downs.setText("3rd & GL");
						}
						else {
							downs.setText("3rd & " + yardsToGo);
						}
						lastPlay.setText("No gain on the run by " + awayTeam.getTeamAbbr() + ".");
					}
					else if (down == 4) {
						if (downs.getText().contains("GL")) {
							downs.setText("4th & GL");
						}
						else {
							downs.setText("4th & " + yardsToGo);
						}
						lastPlay.setText("No gain on the run by " + awayTeam.getTeamAbbr() + ".");
					}
					else {
						down = 1;
						yardsToGo = 10;
						if (!homeSideOfField && yardLine <= 10) {
							downs.setText("1st & GL");
							if (yardLine == 5) {
								yardsToGo = 5;
							}
						}
						else {
							downs.setText("1st & 10");
						}
						awayPossession.setVisible(false);
						homePossession.setVisible(true);
						lastPlay.setText("No gain on the run by " + awayTeam.getTeamAbbr()
						+ ". TURNOVER ON DOWNS!");
						if (overtimeNow) {
		        			overtimeAwayPoss = true;
		        			if (!(homeTeam instanceof NFLTeam)) {
		        				kickoff(true, frame, panel);
		        			}
		        		}
					}
					updatePosition();
					play(frame, panel);
				}
				else {
					if (homeSideOfField) {
						yardLine -= outcome[0];
						if (yardLine > 50) {
							yardLine = 100 - yardLine;
							homeSideOfField = false;
						}
						if (down == 2) {
							if (downs.getText().contains("GL")) {
								downs.setText("2nd & GL");
							}
							else {
								downs.setText("2nd & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + homeTeam.getTeamAbbr() + ".");
						}
						else if (down == 3) {
							if (downs.getText().contains("GL")) {
								downs.setText("3rd & GL");
							}
							else {
								downs.setText("3rd & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + homeTeam.getTeamAbbr() + ".");
						}
						else if (down == 4) {
							if (downs.getText().contains("GL")) {
								downs.setText("4th & GL");
							}
							else {
								downs.setText("4th & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + homeTeam.getTeamAbbr() + ".");
						}
						else {
							down = 1;
							yardsToGo = 10;
							if (!homeSideOfField && yardLine <= 10) {
								downs.setText("1st & GL");
								if (yardLine == 5) {
									yardsToGo = 5;
								}
							}
							else {
								downs.setText("1st & 10");
							}
							awayPossession.setVisible(false);
							homePossession.setVisible(true);
							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + homeTeam.getTeamAbbr()
							+ ". TURNOVER ON DOWNS!");
							if (overtimeNow) {
			        			overtimeAwayPoss = true;
			        			if (!(homeTeam instanceof NFLTeam)) {
			        				kickoff(true, frame, panel);
			        			}
			        		}
						}
						updatePosition();
						play(frame, panel);
					}
					else {
						yardLine += outcome[0];
						if (yardLine <= 0) {
        					lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + homeTeam.getTeamAbbr()
        					+ ". SAFETY!!");
        					if (overtimeNow) {
        	        			overtimeAwayPoss = true;
        	        			overtimeHomePoss = true;
        	        		}
        					homeScore += 2;
        					homePoints.setText("" + homeScore);
        					if (overtimeNow) {
        						if (overtimeNow && homeTeam instanceof NFLTeam) {
        							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
        							quarter.setText("FINAL");
        							awayTimeout1.setVisible(false);
        							awayTimeout2.setVisible(false);
        							awayTimeout3.setVisible(false);
        							homeTimeout1.setVisible(false);
        							homeTimeout2.setVisible(false);
        							homeTimeout3.setVisible(false);
        							awaySim.setVisible(false);
        							homeSim.setVisible(false);
        							awayKickoff.setVisible(false);
        							awayOnsideKick.setVisible(false);
        							awayPunt.setVisible(false);
        							awayFieldGoal.setVisible(false);
        							awayExtraPoint.setVisible(false);
        							awayTwoPoint.setVisible(false);
        							homeKickoff.setVisible(false);
        							homeOnsideKick.setVisible(false);
        							homePunt.setVisible(false);
        							homeFieldGoal.setVisible(false);
        							homeExtraPoint.setVisible(false);
        							homeTwoPoint.setVisible(false);
        							awayPlay1.setVisible(false);
        							awayPlay2.setVisible(false);
        							awayPlay3.setVisible(false);
        							awayPlay4.setVisible(false);
        							awayPlay5.setVisible(false);
        							homePlay1.setVisible(false);
        							homePlay2.setVisible(false);
        							homePlay3.setVisible(false);
        							homePlay4.setVisible(false);
        							homePlay5.setVisible(false);
        							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
        							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        							returnToMainMenu.setBackground(Color.BLACK);
        							returnToMainMenu.setForeground(Color.WHITE);
        							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
        							returnToMainMenu.addActionListener(new ActionListener() {
        								public void actionPerformed(ActionEvent e) {
        									mainMenu.mainMenu();
        								}
        							});
        							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
        						}
        					}
        					else {
        						kickoff(true, frame, panel);
        					}
						}
						else {
    						if (down == 2) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("2nd & GL");
    							}
    							else {
    								downs.setText("2nd & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + homeTeam.getTeamAbbr() + ".");
    						}
    						else if (down == 3) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("3rd & GL");
    							}
    							else {
    								downs.setText("3rd & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + homeTeam.getTeamAbbr() + ".");
    						}
    						else if (down == 4) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("4th & GL");
    							}
    							else {
    								downs.setText("4th & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + homeTeam.getTeamAbbr() + ".");
    						}
    						else {
    							down = 1;
    							yardsToGo = 10;
    							if (!homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(false);
    							homePossession.setVisible(true);
    							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + homeTeam.getTeamAbbr()
    							+ ". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeAwayPoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(true, frame, panel);
    			        			}
    			        		}
    						}
    						updatePosition();
    						play(frame, panel);
						}
					}
				}
			}
			else if (off.getName().contains("pass")) {
				if (outcome[0] > 0) {
					if (homeSideOfField) {
						yardLine -= outcome[0];
						if (yardLine <= 0) {
							int yardsGained = outcome[0] + yardLine;
							lastPlay.setText(yardsGained + " yard pass by " + awayTeam.getTeamAbbr() +
									". TOUCHDOWN!!!");
							touchdown(true, frame, panel);
						}
						else {
							if (yardsToGo <= 0) {
								down = 1;
    							yardsToGo = 10;
    							if (homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
								lastPlay.setText(outcome[0] + " yard pass by " + awayTeam.getTeamAbbr() +
										". First down!");
								updatePosition();
    							play(frame, panel);
							}
							else if (down > 4) {
								down = 1;
    							yardsToGo = 10;
    							if (!homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(false);
    							homePossession.setVisible(true);
    							lastPlay.setText(outcome[0] + " yard pass by " + awayTeam.getTeamAbbr() +
    									". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeAwayPoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(true, frame, panel);
    			        			}
    			        		}
    							updatePosition();
    							play(frame, panel);
							}
							else {
								if (down == 2) {
									if (downs.getText().contains("GL")) {
										downs.setText("2nd & GL");
									}
									else {
										downs.setText("2nd & " + yardsToGo);
									}
								}
								else if (down == 3) {
									if (downs.getText().contains("GL")) {
										downs.setText("3rd & GL");
									}
									else {
										downs.setText("3rd & " + yardsToGo);
									}
								}
								else {
									if (downs.getText().contains("GL")) {
										downs.setText("4th & GL");
									}
									else {
										downs.setText("4th & " + yardsToGo);
									}
								}
								lastPlay.setText(outcome[0] + " yard pass by " + awayTeam.getTeamAbbr() + ".");
								updatePosition();
								play(frame, panel);
							}
						}
					}
					else {
						yardLine += outcome[0];
						if (yardLine > 100) {
							int yardsGained = outcome[0] + 100 - yardLine;
							lastPlay.setText(yardsGained + " yard pass by " + awayTeam.getTeamAbbr() +
									". TOUCHDOWN!!!");
							touchdown(true, frame, panel);
						}
						else {
							if (yardLine > 50) {
    							yardLine = 100 - yardLine;
    							homeSideOfField = true;
							}
							if (yardsToGo <= 0) {
								down = 1;
    							yardsToGo = 10;
    							if (homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
								lastPlay.setText(outcome[0] + " yard pass by " + awayTeam.getTeamAbbr() +
										". First down!");
							}
							else if (down > 4) {
								down = 1;
    							yardsToGo = 10;
    							if (!homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(false);
    							homePossession.setVisible(true);
    							lastPlay.setText(outcome[0] + " yard pass by " + awayTeam.getTeamAbbr() +
    									". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeAwayPoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(true, frame, panel);
    			        			}
    			        		}
							}
							else {
								if (down == 2) {
									if (downs.getText().contains("GL")) {
										downs.setText("2nd & GL");
									}
									else {
										downs.setText("2nd & " + yardsToGo);
									}
								}
								else if (down == 3) {
									if (downs.getText().contains("GL")) {
										downs.setText("3rd & GL");
									}
									else {
										downs.setText("3rd & " + yardsToGo);
									}
								}
								else {
									if (downs.getText().contains("GL")) {
										downs.setText("4th & GL");
									}
									else {
										downs.setText("4th & " + yardsToGo);
									}
								}
								lastPlay.setText(outcome[0] + " yard pass by " + awayTeam.getTeamAbbr() + ".");
							}
							updatePosition();
							play(frame, panel);
						}
					}
				}
				else if (outcome[0] == 0) {
					if (down == 2) {
						if (downs.getText().contains("GL")) {
							downs.setText("2nd & GL");
						}
						else {
							downs.setText("2nd & " + yardsToGo);
						}
						lastPlay.setText("Incomplete pass by " + awayTeam.getTeamAbbr() + ".");
					}
					else if (down == 3) {
						if (downs.getText().contains("GL")) {
							downs.setText("3rd & GL");
						}
						else {
							downs.setText("3rd & " + yardsToGo);
						}
						lastPlay.setText("Incomplete pass by " + awayTeam.getTeamAbbr() + ".");
					}
					else if (down == 4) {
						if (downs.getText().contains("GL")) {
							downs.setText("4th & GL");
						}
						else {
							downs.setText("4th & " + yardsToGo);
						}
						lastPlay.setText("Incomplete pass by " + awayTeam.getTeamAbbr() + ".");
					}
					else {
						down = 1;
						yardsToGo = 10;
						if (!homeSideOfField && yardLine <= 10) {
							downs.setText("1st & GL");
							if (yardLine == 5) {
								yardsToGo = 5;
							}
						}
						else {
							downs.setText("1st & 10");
						}
						awayPossession.setVisible(false);
						homePossession.setVisible(true);
						lastPlay.setText("Incomplete pass by " + awayTeam.getTeamAbbr()
						+ ". TURNOVER ON DOWNS!");
						if (overtimeNow) {
		        			overtimeAwayPoss = true;
		        			if (!(homeTeam instanceof NFLTeam)) {
		        				kickoff(true, frame, panel);
		        			}
		        		}
					}
					updatePosition();
					play(frame, panel);
				}
				else {
					if (homeSideOfField) {
						yardLine -= outcome[0];
						if (yardLine > 50) {
							yardLine = 100 - yardLine;
							homeSideOfField = false;
						}
						if (down == 2) {
							if (downs.getText().contains("GL")) {
								downs.setText("2nd & GL");
							}
							else {
								downs.setText("2nd & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + homeTeam.getTeamAbbr() + ".");
						}
						else if (down == 3) {
							if (downs.getText().contains("GL")) {
								downs.setText("3rd & GL");
							}
							else {
								downs.setText("3rd & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + homeTeam.getTeamAbbr() + ".");
						}
						else if (down == 4) {
							if (downs.getText().contains("GL")) {
								downs.setText("4th & GL");
							}
							else {
								downs.setText("4th & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + homeTeam.getTeamAbbr() + ".");
						}
						else {
							down = 1;
							yardsToGo = 10;
							if (!homeSideOfField && yardLine <= 10) {
								downs.setText("1st & GL");
								if (yardLine == 5) {
									yardsToGo = 5;
								}
							}
							else {
								downs.setText("1st & 10");
							}
							awayPossession.setVisible(false);
							homePossession.setVisible(true);
							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + homeTeam.getTeamAbbr()
							+ ". TURNOVER ON DOWNS!");
							if (overtimeNow) {
			        			overtimeAwayPoss = true;
			        			if (!(homeTeam instanceof NFLTeam)) {
			        				kickoff(true, frame, panel);
			        			}
			        		}
						}
						updatePosition();
						play(frame, panel);
					}
					else {
						yardLine += outcome[0];
						if (yardLine <= 0) {
        					lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + homeTeam.getTeamAbbr()
        					+ ". SAFETY!!");
        					if (overtimeNow) {
        	        			overtimeAwayPoss = true;
        	        			overtimeHomePoss = true;
        	        		}
        					homeScore += 2;
        					homePoints.setText("" + homeScore);
        					if (overtimeNow) {
        						if (overtimeNow && homeTeam instanceof NFLTeam) {
        							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
        							quarter.setText("FINAL");
        							awayTimeout1.setVisible(false);
        							awayTimeout2.setVisible(false);
        							awayTimeout3.setVisible(false);
        							homeTimeout1.setVisible(false);
        							homeTimeout2.setVisible(false);
        							homeTimeout3.setVisible(false);
        							awaySim.setVisible(false);
        							homeSim.setVisible(false);
        							awayKickoff.setVisible(false);
        							awayOnsideKick.setVisible(false);
        							awayPunt.setVisible(false);
        							awayFieldGoal.setVisible(false);
        							awayExtraPoint.setVisible(false);
        							awayTwoPoint.setVisible(false);
        							homeKickoff.setVisible(false);
        							homeOnsideKick.setVisible(false);
        							homePunt.setVisible(false);
        							homeFieldGoal.setVisible(false);
        							homeExtraPoint.setVisible(false);
        							homeTwoPoint.setVisible(false);
        							awayPlay1.setVisible(false);
        							awayPlay2.setVisible(false);
        							awayPlay3.setVisible(false);
        							awayPlay4.setVisible(false);
        							awayPlay5.setVisible(false);
        							homePlay1.setVisible(false);
        							homePlay2.setVisible(false);
        							homePlay3.setVisible(false);
        							homePlay4.setVisible(false);
        							homePlay5.setVisible(false);
        							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
        							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        							returnToMainMenu.setBackground(Color.BLACK);
        							returnToMainMenu.setForeground(Color.WHITE);
        							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
        							returnToMainMenu.addActionListener(new ActionListener() {
        								public void actionPerformed(ActionEvent e) {
        									mainMenu.mainMenu();
        								}
        							});
        							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
        						}
        					}
        					else {
        						kickoff(true, frame, panel);
        					}
						}
						else {
    						if (down == 2) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("2nd & GL");
    							}
    							else {
    								downs.setText("2nd & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + homeTeam.getTeamAbbr() + ".");
    						}
    						else if (down == 3) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("3rd & GL");
    							}
    							else {
    								downs.setText("3rd & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + homeTeam.getTeamAbbr() + ".");
    						}
    						else if (down == 4) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("4th & GL");
    							}
    							else {
    								downs.setText("4th & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + homeTeam.getTeamAbbr() + ".");
    						}
    						else {
    							down = 1;
    							yardsToGo = 10;
    							if (!homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(false);
    							homePossession.setVisible(true);
    							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + homeTeam.getTeamAbbr()
    							+ ". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeAwayPoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(true, frame, panel);
    			        			}
    			        		}
    						}
    						updatePosition();
    						play(frame, panel);
						}
					}
				}
			}
		}
	}
	
	// Runs a normal offense vs. defense play if the home team has the ball
	private void homePlay(int[] outcome, JavaFootballOffenseCard off, JavaFootballDefenseCard def, JFrame frame, JPanel panel) {
		if (outcome[1] != 0) {
			timeoutUsed = true;
		}
		if (outcome[2] == 2) {
			awayPossession.setVisible(true);
			homePossession.setVisible(false);
			if (overtimeNow) {
    			overtimeHomePoss = true;
    			if (!(homeTeam instanceof NFLTeam)) {
    				kickoff(false, frame, panel);;
    			}
    		}
			if (off.getName().contains("screenPass")) {
				lastPlay.setText(homeTeam.getTeamAbbr() + " screen pass INTERCEPTED and returned for a TOUCHDOWN by "
						+ awayTeam.getTeamAbbr() + "!");
			}
			else {
				lastPlay.setText(homeTeam.getTeamAbbr() + " run draw FUMBLED and returned for a TOUCHDOWN by "
						+ awayTeam.getTeamAbbr() + "!");
			}
			touchdown(true, frame, panel);
		}
		else if (outcome[2] == 1) {
			awayPossession.setVisible(true);
			homePossession.setVisible(false);
			if (overtimeNow) {
    			overtimeHomePoss = true;
    			if (!(homeTeam instanceof NFLTeam)) {
    				kickoff(false, frame, panel);;
    			}
    		}
			down = 1;
			yardsToGo = 10;
			if (homeSideOfField && yardLine <= 10) {
				downs.setText("1st & GL");
				if (yardLine == 5) {
					yardsToGo = 5;
				}
			}
			else {
				downs.setText("1st & 10");
			}
			if (off.getName().contains("playAction")) {
				if (def.getName().equals("fumble")) {
					lastPlay.setText(homeTeam.getTeamAbbr() + " play action pass FUMBLED and recovered by "
							+ awayTeam.getTeamAbbr() + "!");
				}
				else {
					lastPlay.setText(homeTeam.getTeamAbbr() + " play action pass INTERCEPTED by "
							+ awayTeam.getTeamAbbr() + "!");
				}
			}
			else if (off.getName().contains("pass")) {
				lastPlay.setText(homeTeam.getTeamAbbr() + " pass INTERCEPTED by "
						+ awayTeam.getTeamAbbr() + "!");
			}
			else {
				lastPlay.setText(homeTeam.getTeamAbbr() + " FUMBLE recovered by "
						+ awayTeam.getTeamAbbr() + "!");
			}
			updatePosition();
			play(frame, panel);
		}
		else if (outcome[1] == 2) {
			down++;
			yardsToGo += 10;
			if (!homeSideOfField) {
				yardLine += 10;
				if (yardLine > 50) {
					yardLine = 100 - yardLine;
					homeSideOfField = true;
				}
			}
			else {
				yardLine -= 10;
				homeSideOfField = true;
			}
			if (yardLine <= 0) {
				lastPlay.setText("10 yard PENALTY on " + homeTeam.getTeamAbbr() + ". SAFETY!!");
				if (overtimeNow) {
        			overtimeAwayPoss = true;
        			overtimeHomePoss = true;
        		}
				awayScore += 2;
				awayPoints.setText("" + awayScore);
				if (overtimeNow) {
					if (overtimeNow && homeTeam instanceof NFLTeam) {
						lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
						quarter.setText("FINAL");
						awayTimeout1.setVisible(false);
						awayTimeout2.setVisible(false);
						awayTimeout3.setVisible(false);
						homeTimeout1.setVisible(false);
						homeTimeout2.setVisible(false);
						homeTimeout3.setVisible(false);
						awaySim.setVisible(false);
						homeSim.setVisible(false);
						awayKickoff.setVisible(false);
						awayOnsideKick.setVisible(false);
						awayPunt.setVisible(false);
						awayFieldGoal.setVisible(false);
						awayExtraPoint.setVisible(false);
						awayTwoPoint.setVisible(false);
						homeKickoff.setVisible(false);
						homeOnsideKick.setVisible(false);
						homePunt.setVisible(false);
						homeFieldGoal.setVisible(false);
						homeExtraPoint.setVisible(false);
						homeTwoPoint.setVisible(false);
						awayPlay1.setVisible(false);
						awayPlay2.setVisible(false);
						awayPlay3.setVisible(false);
						awayPlay4.setVisible(false);
						awayPlay5.setVisible(false);
						homePlay1.setVisible(false);
						homePlay2.setVisible(false);
						homePlay3.setVisible(false);
						homePlay4.setVisible(false);
						homePlay5.setVisible(false);
						JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
						returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
						returnToMainMenu.setBackground(Color.BLACK);
						returnToMainMenu.setForeground(Color.WHITE);
						returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
						returnToMainMenu.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								mainMenu.mainMenu();
							}
						});
						returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
					}
				}
				else {
					kickoff(false, frame, panel);;
				}
			}
			else {
				if (down < 5) {
					if (down == 2) {
						if (downs.getText().contains("GL")) {
							downs.setText("2nd & GL");
						}
						else {
							downs.setText("2nd & " + yardsToGo);
						}
					}
					else if (down == 3) {
						if (downs.getText().contains("GL")) {
							downs.setText("3rd & GL");
						}
						else {
							downs.setText("3rd & " + yardsToGo);
						}
					}
					else {
						if (downs.getText().contains("GL")) {
							downs.setText("4th & GL");
						}
						else {
							downs.setText("4th & " + yardsToGo);
						}
					}
					lastPlay.setText("10 yard PENALTY on " + homeTeam.getTeamAbbr() + " and a LOSS OF DOWN!");
					updatePosition();
					play(frame, panel);
				}
				else {
					awayPossession.setVisible(true);
					homePossession.setVisible(false);
					down = 1;
					yardsToGo = 10;
					if (homeSideOfField && yardLine <= 10) {
						downs.setText("1st & GL");
						if (yardLine == 5) {
							yardsToGo = 5;
						}
					}
					else {
						downs.setText("1st & 10");
					}
					lastPlay.setText("10 yard PENALTY on " + homeTeam.getTeamAbbr() + " and a LOSS OF DOWN."
							+ "TURNOVER ON DOWNS!");
					if (overtimeNow) {
	        			overtimeHomePoss = true;
	        			if (!(homeTeam instanceof NFLTeam)) {
	        				kickoff(false, frame, panel);;
	        			}
	        		}
					updatePosition();
					play(frame, panel);
				}
			}
		}
		else if (outcome[1] == 1) {
			yardsToGo -= outcome[0];
			if (!homeSideOfField) {
				int temp = yardLine;
				yardLine -= outcome[0];
				if (yardLine > 50) {
					yardLine = 100 - yardLine;
					homeSideOfField = true;
				}
				else if (yardLine <= 0) {
					down = 1;
					yardsToGo = 5;
					downs.setText("1st & GL");
					yardLine = 5;
					position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
					int dist = temp - yardLine;
					lastPlay.setText(dist + " yard PENALTY on " + awayTeam.getTeamAbbr() + ". First down!");
				}
			}
			else {
				yardLine += outcome[0];
				if (yardLine > 50) {
					yardLine = 100 - yardLine;
					homeSideOfField = false;
				}
			}
			if (yardLine <= 0) {
				lastPlay.setText(Math.abs(outcome[0]) + " yard PENALTY on " + homeTeam.getTeamAbbr() + ". SAFETY!!");
				if (overtimeNow) {
        			overtimeAwayPoss = true;
        			overtimeHomePoss = true;
        		}
				awayScore += 2;
				awayPoints.setText("" + awayScore);
				if (overtimeNow) {
					if (overtimeNow && homeTeam instanceof NFLTeam) {
						lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
						quarter.setText("FINAL");
						awayTimeout1.setVisible(false);
						awayTimeout2.setVisible(false);
						awayTimeout3.setVisible(false);
						homeTimeout1.setVisible(false);
						homeTimeout2.setVisible(false);
						homeTimeout3.setVisible(false);
						awaySim.setVisible(false);
						homeSim.setVisible(false);
						awayKickoff.setVisible(false);
						awayOnsideKick.setVisible(false);
						awayPunt.setVisible(false);
						awayFieldGoal.setVisible(false);
						awayExtraPoint.setVisible(false);
						awayTwoPoint.setVisible(false);
						homeKickoff.setVisible(false);
						homeOnsideKick.setVisible(false);
						homePunt.setVisible(false);
						homeFieldGoal.setVisible(false);
						homeExtraPoint.setVisible(false);
						homeTwoPoint.setVisible(false);
						awayPlay1.setVisible(false);
						awayPlay2.setVisible(false);
						awayPlay3.setVisible(false);
						awayPlay4.setVisible(false);
						awayPlay5.setVisible(false);
						homePlay1.setVisible(false);
						homePlay2.setVisible(false);
						homePlay3.setVisible(false);
						homePlay4.setVisible(false);
						homePlay5.setVisible(false);
						JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
						returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
						returnToMainMenu.setBackground(Color.BLACK);
						returnToMainMenu.setForeground(Color.WHITE);
						returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
						returnToMainMenu.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								mainMenu.mainMenu();
							}
						});
						returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
					}
				}
				else {
					kickoff(false, frame, panel);;
				}
			}
			else {
				if (yardsToGo <= 0) {
					down = 1;
					yardsToGo = 10;
					if (!homeSideOfField && yardLine <= 10) {
						downs.setText("1st & GL");
						if (yardLine == 5) {
							yardsToGo = 5;
						}
					}
					else {
						downs.setText("1st & 10");
					}
					lastPlay.setText(Math.abs(outcome[0]) + " yard PENALTY on " + awayTeam.getTeamAbbr() + ". First down!");
				}
				else {
					if (downs.getText().contains("GL")) {
						if (down == 1) {
							downs.setText("1st & GL");
						}
						else if (down == 2) {
							downs.setText("2nd & GL");
						}
						else if (down == 3) {
							downs.setText("3rd & GL");
						}
						else {
							downs.setText("4th & GL");
						}
					}
					else {
						if (down == 1) {
							downs.setText("1st & " + yardsToGo);
						}
						else if (down == 2) {
							downs.setText("2nd & " + yardsToGo);
						}
						else if (down == 3) {
							downs.setText("3rd & " + yardsToGo);
						}
						else {
							downs.setText("4th & " + yardsToGo);
						}
					}
					if (outcome[0] > 0) {
						lastPlay.setText(Math.abs(outcome[0]) + " yard PENALTY on " + awayTeam.getTeamAbbr() + ". Replay down.");
					}
					else {
						lastPlay.setText(Math.abs(outcome[0]) + " yard PENALTY on " + homeTeam.getTeamAbbr() + ". Replay down.");
					}
				}
				int i = 0;
				boolean offenseReplenished = false;
				while (!offenseReplenished && i < 5) {
					if (homeOffenseCards[i] == null) {
						homeOffenseCards[i] = drawOffenseCard(true);
						offenseReplenished = true;
					}
					else {
						i++;
					}
				}
				if (offenseReplenished) {
					switch (i) {
						case 0:
							homePlay1.setEnabled(true);
							break;
						case 1:
							homePlay2.setEnabled(true);
							break;
						case 2:
							homePlay3.setEnabled(true);
							break;
						case 3:
							homePlay4.setEnabled(true);
							break;
						case 4:
							homePlay5.setEnabled(true);
							break;
					}
				}
				i = 0;
				boolean defenseReplenished = false;
				while (!defenseReplenished && i < 5) {
					if (awayDefenseCards[i] == null) {
						awayDefenseCards[i] = drawDefenseCard(false);
						defenseReplenished = true;
					}
					else {
						i++;
					}
				}
				if (defenseReplenished) {
					switch (i) {
						case 0:
							awayPlay1.setEnabled(true);
							break;
						case 1:
							awayPlay2.setEnabled(true);
							break;
						case 2:
							awayPlay3.setEnabled(true);
							break;
						case 3:
							awayPlay4.setEnabled(true);
							break;
						case 4:
							awayPlay5.setEnabled(true);
							break;
					}
				}
				updatePosition();
				play(frame, panel);
			}
		}
		else {
			down++;
			yardsToGo -= outcome[0];
			if (off.getName().contains("playAction")) {
				if (outcome[0] > 0) {
					if (!homeSideOfField) {
						yardLine -= outcome[0];
						if (yardLine <= 0) {
							int yardsGained = outcome[0] + yardLine;
							lastPlay.setText(yardsGained + " yard play action pass by " + homeTeam.getTeamAbbr() +
									". TOUCHDOWN!!!");
							touchdown(false, frame, panel);
						}
						else {
							if (yardsToGo <= 0) {
								down = 1;
    							yardsToGo = 10;
    							if (!homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
								lastPlay.setText(outcome[0] + " yard play action pass by " + homeTeam.getTeamAbbr() +
										". First down!");
								updatePosition();
    							play(frame, panel);
							}
							else if (down > 4) {
								down = 1;
    							yardsToGo = 10;
    							if (homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(true);
    							homePossession.setVisible(false);
    							lastPlay.setText(outcome[0] + " yard play action pass by " + homeTeam.getTeamAbbr() +
    									". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeHomePoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(false, frame, panel);;
    			        			}
    			        		}
    							updatePosition();
    							play(frame, panel);
							}
							else {
								if (downs.getText().contains("GL")) {
									if (down == 2) {
										downs.setText("2nd & GL");
									}
									else if (down == 3) {
										downs.setText("3rd & GL");
									}
									else {
										downs.setText("4th & GL");
									}
								}
								else {
									if (down == 2) {
										downs.setText("2nd & " + yardsToGo);
									}
									else if (down == 3) {
										downs.setText("3rd & " + yardsToGo);
									}
									else {
										downs.setText("4th & " + yardsToGo);
									}
								}
								lastPlay.setText(outcome[0] + " yard play action pass by " + homeTeam.getTeamAbbr() + ".");
								updatePosition();
								play(frame, panel);
							}
						}
					}
					else {
						yardLine += outcome[0];
						if (yardLine > 100) {
							int yardsGained = outcome[0] + 100 - yardLine;
							lastPlay.setText(yardsGained + " yard play action pass by " + homeTeam.getTeamAbbr() +
									". TOUCHDOWN!!!");
							touchdown(false, frame, panel);
						}
						else {
							if (yardLine > 50) {
    							yardLine = 100 - yardLine;
    							homeSideOfField = false;
							}
							if (yardsToGo <= 0) {
								down = 1;
    							yardsToGo = 10;
    							if (!homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
								lastPlay.setText(outcome[0] + " yard play action pass by " + homeTeam.getTeamAbbr() +
										". First down!");
							}
							else if (down > 4) {
								down = 1;
    							yardsToGo = 10;
    							if (homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(true);
    							homePossession.setVisible(false);
    							lastPlay.setText(outcome[0] + " yard play action pass by " + homeTeam.getTeamAbbr() +
    									". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeHomePoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(false, frame, panel);;
    			        			}
    			        		}
							}
							else {
								if (downs.getText().contains("GL")) {
									if (down == 2) {
										downs.setText("2nd & GL");
									}
									else if (down == 3) {
										downs.setText("3rd & GL");
									}
									else {
										downs.setText("4th & GL");
									}
								}
								else {
									if (down == 2) {
										downs.setText("2nd & " + yardsToGo);
									}
									else if (down == 3) {
										downs.setText("3rd & " + yardsToGo);
									}
									else {
										downs.setText("4th & " + yardsToGo);
									}
								}
								lastPlay.setText(outcome[0] + " yard play action pass by " + homeTeam.getTeamAbbr() + ".");
							}
							updatePosition();
							play(frame, panel);
						}
					}
				}
				else if (outcome[0] == 0) {
					if (down == 2) {
						if (downs.getText().contains("GL")) {
							downs.setText("2nd & GL");
						}
						else {
							downs.setText("2nd & " + yardsToGo);
						}
						lastPlay.setText("No gain on the play action pass by " + homeTeam.getTeamAbbr() + ".");
					}
					else if (down == 3) {
						if (downs.getText().contains("GL")) {
							downs.setText("3rd & GL");
						}
						else {
							downs.setText("3rd & " + yardsToGo);
						}
						lastPlay.setText("No gain on the play action pass by " + homeTeam.getTeamAbbr() + ".");
					}
					else if (down == 4) {
						if (downs.getText().contains("GL")) {
							downs.setText("4th & GL");
						}
						else {
							downs.setText("4th & " + yardsToGo);
						}
						lastPlay.setText("No gain on the play action pass by " + homeTeam.getTeamAbbr() + ".");
					}
					else {
						down = 1;
						yardsToGo = 10;
						if (homeSideOfField && yardLine <= 10) {
							downs.setText("1st & GL");
							if (yardLine == 5) {
								yardsToGo = 5;
							}
						}
						else {
							downs.setText("1st & 10");
						}
						awayPossession.setVisible(true);
						homePossession.setVisible(false);
						lastPlay.setText("No gain on the play action pass by " + homeTeam.getTeamAbbr()
						+ ". TURNOVER ON DOWNS!");
						if (overtimeNow) {
		        			overtimeHomePoss = true;
		        			if (!(homeTeam instanceof NFLTeam)) {
		        				kickoff(false, frame, panel);;
		        			}
		        		}
					}
					updatePosition();
					play(frame, panel);
				}
				else {
					if (!homeSideOfField) {
						yardLine -= outcome[0];
						if (yardLine > 50) {
							yardLine = 100 - yardLine;
							homeSideOfField = true;
						}
						if (down == 2) {
							if (downs.getText().contains("GL")) {
								downs.setText("2nd & GL");
							}
							else {
								downs.setText("2nd & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + awayTeam.getTeamAbbr() + ".");
						}
						else if (down == 3) {
							if (downs.getText().contains("GL")) {
								downs.setText("3rd & GL");
							}
							else {
								downs.setText("3rd & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + awayTeam.getTeamAbbr() + ".");
						}
						else if (down == 4) {
							if (downs.getText().contains("GL")) {
								downs.setText("4th & GL");
							}
							else {
								downs.setText("4th & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + awayTeam.getTeamAbbr() + ".");
						}
						else {
							down = 1;
							yardsToGo = 10;
							if (homeSideOfField && yardLine <= 10) {
								downs.setText("1st & GL");
								if (yardLine == 5) {
									yardsToGo = 5;
								}
							}
							else {
								downs.setText("1st & 10");
							}
							awayPossession.setVisible(true);
							homePossession.setVisible(false);
							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + awayTeam.getTeamAbbr()
							+ ". TURNOVER ON DOWNS!");
							if (overtimeNow) {
			        			overtimeHomePoss = true;
			        			if (!(homeTeam instanceof NFLTeam)) {
			        				kickoff(false, frame, panel);;
			        			}
			        		}
						}
						updatePosition();
						play(frame, panel);
					}
					else {
						yardLine += outcome[0];
						if (yardLine <= 0) {
        					lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + awayTeam.getTeamAbbr()
        					+ ". SAFETY!!");
        					if (overtimeNow) {
        	        			overtimeAwayPoss = true;
        	        			overtimeHomePoss = true;
        	        		}
        					awayScore += 2;
        					awayPoints.setText("" + awayScore);
        					if (overtimeNow) {
        						if (overtimeNow && homeTeam instanceof NFLTeam) {
        							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
        							quarter.setText("FINAL");
        							awayTimeout1.setVisible(false);
        							awayTimeout2.setVisible(false);
        							awayTimeout3.setVisible(false);
        							homeTimeout1.setVisible(false);
        							homeTimeout2.setVisible(false);
        							homeTimeout3.setVisible(false);
        							awaySim.setVisible(false);
        							homeSim.setVisible(false);
        							awayKickoff.setVisible(false);
        							awayOnsideKick.setVisible(false);
        							awayPunt.setVisible(false);
        							awayFieldGoal.setVisible(false);
        							awayExtraPoint.setVisible(false);
        							awayTwoPoint.setVisible(false);
        							homeKickoff.setVisible(false);
        							homeOnsideKick.setVisible(false);
        							homePunt.setVisible(false);
        							homeFieldGoal.setVisible(false);
        							homeExtraPoint.setVisible(false);
        							homeTwoPoint.setVisible(false);
        							awayPlay1.setVisible(false);
        							awayPlay2.setVisible(false);
        							awayPlay3.setVisible(false);
        							awayPlay4.setVisible(false);
        							awayPlay5.setVisible(false);
        							homePlay1.setVisible(false);
        							homePlay2.setVisible(false);
        							homePlay3.setVisible(false);
        							homePlay4.setVisible(false);
        							homePlay5.setVisible(false);
        							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
        							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        							returnToMainMenu.setBackground(Color.BLACK);
        							returnToMainMenu.setForeground(Color.WHITE);
        							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
        							returnToMainMenu.addActionListener(new ActionListener() {
        								public void actionPerformed(ActionEvent e) {
        									mainMenu.mainMenu();
        								}
        							});
        							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
        						}
        					}
        					else {
        						kickoff(false, frame, panel);;
        					}
						}
						else {
    						if (down == 2) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("2nd & GL");
    							}
    							else {
    								downs.setText("2nd & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + awayTeam.getTeamAbbr() + ".");
    						}
    						else if (down == 3) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("3rd & GL");
    							}
    							else {
    								downs.setText("3rd & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + awayTeam.getTeamAbbr() + ".");
    						}
    						else if (down == 4) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("4th & GL");
    							}
    							else {
    								downs.setText("4th & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + awayTeam.getTeamAbbr() + ".");
    						}
    						else {
    							down = 1;
    							yardsToGo = 10;
    							if (homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(true);
    							homePossession.setVisible(false);
    							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + awayTeam.getTeamAbbr()
    							+ ". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeHomePoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(false, frame, panel);;
    			        			}
    			        		}
    						}
    						updatePosition();
    						play(frame, panel);
						}
					}
				}
			}
			else if (off.getName().contains("screenPass")) {
				if (outcome[0] > 0) {
					if (!homeSideOfField) {
						yardLine -= outcome[0];
						if (yardLine <= 0) {
							int yardsGained = outcome[0] + yardLine;
							lastPlay.setText(yardsGained + " yard screen pass by " + homeTeam.getTeamAbbr() +
									". TOUCHDOWN!!!");
							touchdown(false, frame, panel);
						}
						else {
							if (yardsToGo <= 0) {
								down = 1;
    							yardsToGo = 10;
    							if (!homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
								lastPlay.setText(outcome[0] + " yard screen pass by " + homeTeam.getTeamAbbr() +
										". First down!");
								updatePosition();
    							play(frame, panel);
							}
							else if (down > 4) {
								down = 1;
    							yardsToGo = 10;
    							if (homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(true);
    							homePossession.setVisible(false);
    							lastPlay.setText(outcome[0] + " yard screen pass by " + homeTeam.getTeamAbbr() +
    									". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeHomePoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(false, frame, panel);;
    			        			}
    			        		}
    							updatePosition();
    							play(frame, panel);
							}
							else {
								if (down == 2) {
									if (downs.getText().contains("GL")) {
										downs.setText("2nd & GL");
									}
									else {
										downs.setText("2nd & " + yardsToGo);
									}
								}
								else if (down == 3) {
									if (downs.getText().contains("GL")) {
										downs.setText("3rd & GL");
									}
									else {
										downs.setText("3rd & " + yardsToGo);
									}
								}
								else {
									if (downs.getText().contains("GL")) {
										downs.setText("4th & GL");
									}
									else {
										downs.setText("4th & " + yardsToGo);
									}
								}
								lastPlay.setText(outcome[0] + " yard screen pass by " + homeTeam.getTeamAbbr() + ".");
								updatePosition();
								play(frame, panel);
							}
						}
					}
					else {
						yardLine += outcome[0];
						if (yardLine > 100) {
							int yardsGained = outcome[0] + 100 - yardLine;
							lastPlay.setText(yardsGained + " yard screen pass by " + homeTeam.getTeamAbbr() +
									". TOUCHDOWN!!!");
							touchdown(false, frame, panel);
						}
						else {
							if (yardLine > 50) {
    							yardLine = 100 - yardLine;
    							homeSideOfField = false;
							}
							if (yardsToGo <= 0) {
								down = 1;
    							yardsToGo = 10;
    							if (!homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
								lastPlay.setText(outcome[0] + " yard screen pass by " + homeTeam.getTeamAbbr() +
										". First down!");
							}
							else if (down > 4) {
								down = 1;
    							yardsToGo = 10;
    							if (homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(true);
    							homePossession.setVisible(false);
    							lastPlay.setText(outcome[0] + " yard screen pass by " + homeTeam.getTeamAbbr() +
    									". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeHomePoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(false, frame, panel);;
    			        			}
    			        		}
							}
							else {
								if (down == 2) {
									if (downs.getText().contains("GL")) {
										downs.setText("2nd & GL");
									}
									else {
										downs.setText("2nd & " + yardsToGo);
									}
									lastPlay.setText("No gain on the screen pass by " + homeTeam.getTeamAbbr() + ".");
								}
								else if (down == 3) {
									if (downs.getText().contains("GL")) {
										downs.setText("3rd & GL");
									}
									else {
										downs.setText("3rd & " + yardsToGo);
									}
									lastPlay.setText("No gain on the screen pass by " + homeTeam.getTeamAbbr() + ".");
								}
								else {
									if (downs.getText().contains("GL")) {
										downs.setText("4th & GL");
									}
									else {
										downs.setText("4th & " + yardsToGo);
									}
									lastPlay.setText("No gain on the screen pass by " + homeTeam.getTeamAbbr() + ".");
								}
								lastPlay.setText(outcome[0] + " yard screen pass by " + homeTeam.getTeamAbbr() + ".");
							}
							updatePosition();
							play(frame, panel);
						}
					}
				}
				else if (outcome[0] == 0) {
					if (down == 2) {
						if (downs.getText().contains("GL")) {
							downs.setText("2nd & GL");
						}
						else {
							downs.setText("2nd & " + yardsToGo);
						}
						lastPlay.setText("No gain on the screen pass by " + homeTeam.getTeamAbbr() + ".");
					}
					else if (down == 3) {
						if (downs.getText().contains("GL")) {
							downs.setText("3rd & GL");
						}
						else {
							downs.setText("3rd & " + yardsToGo);
						}
						lastPlay.setText("No gain on the screen pass by " + homeTeam.getTeamAbbr() + ".");
					}
					else if (down == 4) {
						if (downs.getText().contains("GL")) {
							downs.setText("4th & GL");
						}
						else {
							downs.setText("4th & " + yardsToGo);
						}
						lastPlay.setText("No gain on the screen pass by " + homeTeam.getTeamAbbr() + ".");
					}
					else {
						down = 1;
						yardsToGo = 10;
						if (homeSideOfField && yardLine <= 10) {
							downs.setText("1st & GL");
							if (yardLine == 5) {
								yardsToGo = 5;
							}
						}
						else {
							downs.setText("1st & 10");
						}
						awayPossession.setVisible(true);
						homePossession.setVisible(false);
						lastPlay.setText("No gain on the screen pass by " + homeTeam.getTeamAbbr()
						+ ". TURNOVER ON DOWNS!");
						if (overtimeNow) {
		        			overtimeHomePoss = true;
		        			if (!(homeTeam instanceof NFLTeam)) {
		        				kickoff(false, frame, panel);;
		        			}
		        		}
					}
					updatePosition();
					play(frame, panel);
				}
				else {
					if (!homeSideOfField) {
						yardLine -= outcome[0];
						if (yardLine > 50) {
							yardLine = 100 - yardLine;
							homeSideOfField = true;
						}
						if (down == 2) {
							if (downs.getText().contains("GL")) {
								downs.setText("2nd & GL");
							}
							else {
								downs.setText("2nd & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard LOSS on the screen pass by "
									+ homeTeam.getTeamAbbr() + ".");
						}
						else if (down == 3) {
							if (downs.getText().contains("GL")) {
								downs.setText("3rd & GL");
							}
							else {
								downs.setText("3rd & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard LOSS on the screen pass by "
									+ homeTeam.getTeamAbbr() + ".");
						}
						else if (down == 4) {
							if (downs.getText().contains("GL")) {
								downs.setText("4th & GL");
							}
							else {
								downs.setText("4th & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard LOSS on the screen pass by "
									+ homeTeam.getTeamAbbr() + ".");
						}
						else {
							down = 1;
							yardsToGo = 10;
							if (homeSideOfField && yardLine <= 10) {
								downs.setText("1st & GL");
								if (yardLine == 5) {
									yardsToGo = 5;
								}
							}
							else {
								downs.setText("1st & 10");
							}
							awayPossession.setVisible(true);
							homePossession.setVisible(false);
							lastPlay.setText(Math.abs(outcome[0]) + " yard LOSS on the screen pass by " + homeTeam.getTeamAbbr()
							+ ". TURNOVER ON DOWNS!");
							if (overtimeNow) {
			        			overtimeHomePoss = true;
			        			if (!(homeTeam instanceof NFLTeam)) {
			        				kickoff(false, frame, panel);;
			        			}
			        		}
						}
						updatePosition();
						play(frame, panel);
					}
					else {
						yardLine += outcome[0];
						if (yardLine <= 0) {
        					lastPlay.setText(Math.abs(outcome[0]) + " yard LOSS on the screen pass by " + homeTeam.getTeamAbbr()
        					+ ". SAFETY!!");
        					if (overtimeNow) {
        	        			overtimeAwayPoss = true;
        	        			overtimeHomePoss = true;
        	        		}
        					awayScore += 2;
        					awayPoints.setText("" + awayScore);
        					if (overtimeNow) {
        						if (overtimeNow && homeTeam instanceof NFLTeam) {
        							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
        							quarter.setText("FINAL");
        							awayTimeout1.setVisible(false);
        							awayTimeout2.setVisible(false);
        							awayTimeout3.setVisible(false);
        							homeTimeout1.setVisible(false);
        							homeTimeout2.setVisible(false);
        							homeTimeout3.setVisible(false);
        							awaySim.setVisible(false);
        							homeSim.setVisible(false);
        							awayKickoff.setVisible(false);
        							awayOnsideKick.setVisible(false);
        							awayPunt.setVisible(false);
        							awayFieldGoal.setVisible(false);
        							awayExtraPoint.setVisible(false);
        							awayTwoPoint.setVisible(false);
        							homeKickoff.setVisible(false);
        							homeOnsideKick.setVisible(false);
        							homePunt.setVisible(false);
        							homeFieldGoal.setVisible(false);
        							homeExtraPoint.setVisible(false);
        							homeTwoPoint.setVisible(false);
        							awayPlay1.setVisible(false);
        							awayPlay2.setVisible(false);
        							awayPlay3.setVisible(false);
        							awayPlay4.setVisible(false);
        							awayPlay5.setVisible(false);
        							homePlay1.setVisible(false);
        							homePlay2.setVisible(false);
        							homePlay3.setVisible(false);
        							homePlay4.setVisible(false);
        							homePlay5.setVisible(false);
        							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
        							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        							returnToMainMenu.setBackground(Color.BLACK);
        							returnToMainMenu.setForeground(Color.WHITE);
        							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
        							returnToMainMenu.addActionListener(new ActionListener() {
        								public void actionPerformed(ActionEvent e) {
        									mainMenu.mainMenu();
        								}
        							});
        							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
        						}
        					}
        					else {
        						kickoff(false, frame, panel);;
        					}
						}
						else {
    						if (down == 2) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("2nd & GL");
    							}
    							else {
    								downs.setText("2nd & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard LOSS by " + homeTeam.getTeamAbbr() + ".");
    						}
    						else if (down == 3) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("3rd & GL");
    							}
    							else {
    								downs.setText("3rd & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard LOSS by " + homeTeam.getTeamAbbr() + ".");
    						}
    						else if (down == 4) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("4th & GL");
    							}
    							else {
    								downs.setText("4th & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard LOSS by " + homeTeam.getTeamAbbr() + ".");
    						}
    						else {
    							down = 1;
    							yardsToGo = 10;
    							if (homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(true);
    							homePossession.setVisible(false);
    							lastPlay.setText(Math.abs(outcome[0]) + " yard LOSS by " + homeTeam.getTeamAbbr()
    							+ ". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeHomePoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(false, frame, panel);;
    			        			}
    			        		}
    						}
    						updatePosition();
    						play(frame, panel);
						}
					}
				}
			}
			else if (off.getName().contains("runDraw")) {
				if (outcome[0] > 0) {
					if (!homeSideOfField) {
						yardLine -= outcome[0];
						if (yardLine <= 0) {
							int yardsGained = outcome[0] + yardLine;
							lastPlay.setText(yardsGained + " yard run draw by " + homeTeam.getTeamAbbr() +
									". TOUCHDOWN!!!");
							touchdown(false, frame, panel);
						}
						else {
							if (yardsToGo <= 0) {
								down = 1;
    							yardsToGo = 10;
    							if (!homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
								lastPlay.setText(outcome[0] + " yard run draw by " + homeTeam.getTeamAbbr() +
										". First down!");
								updatePosition();
    							play(frame, panel);
							}
							else if (down > 4) {
								down = 1;
    							yardsToGo = 10;
    							if (homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(true);
    							homePossession.setVisible(false);
    							lastPlay.setText(outcome[0] + " yard run draw by " + homeTeam.getTeamAbbr() +
    									". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeHomePoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(false, frame, panel);;
    			        			}
    			        		}
    							updatePosition();
    							play(frame, panel);
							}
							else {
								if (down == 2) {
									if (downs.getText().contains("GL")) {
										downs.setText("2nd & GL");
									}
									else {
										downs.setText("2nd & " + yardsToGo);
									}
								}
								else if (down == 3) {
									if (downs.getText().contains("GL")) {
										downs.setText("3rd & GL");
									}
									else {
										downs.setText("3rd & " + yardsToGo);
									}
								}
								else {
									if (downs.getText().contains("GL")) {
										downs.setText("4th & GL");
									}
									else {
										downs.setText("4th & " + yardsToGo);
									}
								}
								lastPlay.setText(outcome[0] + " yard run draw by " + homeTeam.getTeamAbbr() + ".");
								updatePosition();
								play(frame, panel);
							}
						}
					}
					else {
						yardLine += outcome[0];
						if (yardLine > 100) {
							int yardsGained = outcome[0] + 100 - yardLine;
							lastPlay.setText(yardsGained + " yard run draw by " + homeTeam.getTeamAbbr() +
									". TOUCHDOWN!!!");
							touchdown(false, frame, panel);
						}
						else {
							if (yardLine > 50) {
    							yardLine = 100 - yardLine;
    							homeSideOfField = false;
							}
							if (yardsToGo <= 0) {
								down = 1;
    							yardsToGo = 10;
    							if (!homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
								lastPlay.setText(outcome[0] + " yard run draw by " + homeTeam.getTeamAbbr() +
										". First down!");
							}
							else if (down > 4) {
								down = 1;
    							yardsToGo = 10;
    							if (homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(true);
    							homePossession.setVisible(false);
    							lastPlay.setText(outcome[0] + " yard run draw by " + homeTeam.getTeamAbbr() +
    									". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeHomePoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(false, frame, panel);;
    			        			}
    			        		}
							}
							else {
								if (down == 2) {
									if (downs.getText().contains("GL")) {
										downs.setText("2nd & GL");
									}
									else {
										downs.setText("2nd & " + yardsToGo);
									}
								}
								else if (down == 3) {
									if (downs.getText().contains("GL")) {
										downs.setText("3rd & GL");
									}
									else {
										downs.setText("3rd & " + yardsToGo);
									}
								}
								else {
									if (downs.getText().contains("GL")) {
										downs.setText("4th & GL");
									}
									else {
										downs.setText("4th & " + yardsToGo);
									}
								}
								lastPlay.setText(outcome[0] + " yard run draw by " + homeTeam.getTeamAbbr() + ".");
							}
							updatePosition();
							play(frame, panel);
						}
					}
				}
				else if (outcome[0] == 0) {
					if (down == 2) {
						if (downs.getText().contains("GL")) {
							downs.setText("2nd & GL");
						}
						else {
							downs.setText("2nd & " + yardsToGo);
						}
						lastPlay.setText("No gain on the run draw by " + homeTeam.getTeamAbbr() + ".");
					}
					else if (down == 3) {
						if (downs.getText().contains("GL")) {
							downs.setText("3rd & GL");
						}
						else {
							downs.setText("3rd & " + yardsToGo);
						}
						lastPlay.setText("No gain on the run draw by " + homeTeam.getTeamAbbr() + ".");
					}
					else if (down == 4) {
						if (downs.getText().contains("GL")) {
							downs.setText("4th & GL");
						}
						else {
							downs.setText("4th & " + yardsToGo);
						}
						lastPlay.setText("No gain on the run draw by " + homeTeam.getTeamAbbr() + ".");
					}
					else {
						down = 1;
						yardsToGo = 10;
						if (homeSideOfField && yardLine <= 10) {
							downs.setText("1st & GL");
							if (yardLine == 5) {
								yardsToGo = 5;
							}
						}
						else {
							downs.setText("1st & 10");
						}
						awayPossession.setVisible(true);
						homePossession.setVisible(false);
						lastPlay.setText("No gain on the run draw by " + homeTeam.getTeamAbbr()
						+ ". TURNOVER ON DOWNS!");
						if (overtimeNow) {
		        			overtimeHomePoss = true;
		        			if (!(homeTeam instanceof NFLTeam)) {
		        				kickoff(false, frame, panel);;
		        			}
		        		}
					}
					updatePosition();
					play(frame, panel);
				}
				else {
					if (!homeSideOfField) {
						yardLine -= outcome[0];
						if (yardLine > 50) {
							yardLine = 100 - yardLine;
							homeSideOfField = true;
						}
						if (down == 2) {
							if (downs.getText().contains("GL")) {
								downs.setText("2nd & GL");
							}
							else {
								downs.setText("2nd & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + awayTeam.getTeamAbbr() + ".");
						}
						else if (down == 3) {
							if (downs.getText().contains("GL")) {
								downs.setText("3rd & GL");
							}
							else {
								downs.setText("3rd & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + awayTeam.getTeamAbbr() + ".");
						}
						else if (down == 4) {
							if (downs.getText().contains("GL")) {
								downs.setText("4th & GL");
							}
							else {
								downs.setText("4th & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + awayTeam.getTeamAbbr() + ".");
						}
						else {
							down = 1;
							yardsToGo = 10;
							if (homeSideOfField && yardLine <= 10) {
								downs.setText("1st & GL");
								if (yardLine == 5) {
									yardsToGo = 5;
								}
							}
							else {
								downs.setText("1st & 10");
							}
							awayPossession.setVisible(true);
							homePossession.setVisible(false);
							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + awayTeam.getTeamAbbr()
							+ ". TURNOVER ON DOWNS!");
							if (overtimeNow) {
			        			overtimeHomePoss = true;
			        			if (!(homeTeam instanceof NFLTeam)) {
			        				kickoff(false, frame, panel);;
			        			}
			        		}
						}
						updatePosition();
						play(frame, panel);
					}
					else {
						yardLine += outcome[0];
						if (yardLine <= 0) {
        					lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + awayTeam.getTeamAbbr()
        					+ ". SAFETY!!");
        					if (overtimeNow) {
        	        			overtimeAwayPoss = true;
        	        			overtimeHomePoss = true;
        	        		}
        					awayScore += 2;
        					awayPoints.setText("" + awayScore);
        					if (overtimeNow) {
        						if (overtimeNow && homeTeam instanceof NFLTeam) {
        							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
        							quarter.setText("FINAL");
        							awayTimeout1.setVisible(false);
        							awayTimeout2.setVisible(false);
        							awayTimeout3.setVisible(false);
        							homeTimeout1.setVisible(false);
        							homeTimeout2.setVisible(false);
        							homeTimeout3.setVisible(false);
        							awaySim.setVisible(false);
        							homeSim.setVisible(false);
        							awayKickoff.setVisible(false);
        							awayOnsideKick.setVisible(false);
        							awayPunt.setVisible(false);
        							awayFieldGoal.setVisible(false);
        							awayExtraPoint.setVisible(false);
        							awayTwoPoint.setVisible(false);
        							homeKickoff.setVisible(false);
        							homeOnsideKick.setVisible(false);
        							homePunt.setVisible(false);
        							homeFieldGoal.setVisible(false);
        							homeExtraPoint.setVisible(false);
        							homeTwoPoint.setVisible(false);
        							awayPlay1.setVisible(false);
        							awayPlay2.setVisible(false);
        							awayPlay3.setVisible(false);
        							awayPlay4.setVisible(false);
        							awayPlay5.setVisible(false);
        							homePlay1.setVisible(false);
        							homePlay2.setVisible(false);
        							homePlay3.setVisible(false);
        							homePlay4.setVisible(false);
        							homePlay5.setVisible(false);
        							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
        							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        							returnToMainMenu.setBackground(Color.BLACK);
        							returnToMainMenu.setForeground(Color.WHITE);
        							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
        							returnToMainMenu.addActionListener(new ActionListener() {
        								public void actionPerformed(ActionEvent e) {
        									mainMenu.mainMenu();
        								}
        							});
        							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
        						}
        					}
        					else {
        						kickoff(false, frame, panel);;
        					}
						}
						else {
    						if (down == 2) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("2nd & GL");
    							}
    							else {
    								downs.setText("2nd & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + awayTeam.getTeamAbbr() + ".");
    						}
    						else if (down == 3) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("3rd & GL");
    							}
    							else {
    								downs.setText("3rd & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + awayTeam.getTeamAbbr() + ".");
    						}
    						else if (down == 4) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("4th & GL");
    							}
    							else {
    								downs.setText("4th & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + awayTeam.getTeamAbbr() + ".");
    						}
    						else {
    							down = 1;
    							yardsToGo = 10;
    							if (homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(true);
    							homePossession.setVisible(false);
    							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + awayTeam.getTeamAbbr()
    							+ ". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeHomePoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(false, frame, panel);;
    			        			}
    			        		}
    						}
    						updatePosition();
    						play(frame, panel);
						}
					}
				}
			}
			else if (off.getName().contains("run")) {
				if (outcome[0] > 0) {
					if (!homeSideOfField) {
						yardLine -= outcome[0];
						if (yardLine <= 0) {
							int yardsGained = outcome[0] + yardLine;
							lastPlay.setText(yardsGained + " yard run by " + homeTeam.getTeamAbbr() +
									". TOUCHDOWN!!!");
							touchdown(false, frame, panel);
						}
						else {
							if (yardsToGo <= 0) {
								down = 1;
    							yardsToGo = 10;
    							if (!homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
								lastPlay.setText(outcome[0] + " yard run by " + homeTeam.getTeamAbbr() +
										". First down!");
								updatePosition();
    							play(frame, panel);
							}
							else if (down > 4) {
								down = 1;
    							yardsToGo = 10;
    							if (homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(true);
    							homePossession.setVisible(false);
    							lastPlay.setText(outcome[0] + " yard run by " + homeTeam.getTeamAbbr() +
    									". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeHomePoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(false, frame, panel);;
    			        			}
    			        		}
    							updatePosition();
    							play(frame, panel);
							}
							else {
								if (down == 2) {
									if (downs.getText().contains("GL")) {
										downs.setText("2nd & GL");
									}
									else {
										downs.setText("2nd & " + yardsToGo);
									}
								}
								else if (down == 3) {
									if (downs.getText().contains("GL")) {
										downs.setText("3rd & GL");
									}
									else {
										downs.setText("3rd & " + yardsToGo);
									}
								}
								else {
									if (downs.getText().contains("GL")) {
										downs.setText("4th & GL");
									}
									else {
										downs.setText("4th & " + yardsToGo);
									}
								}
								lastPlay.setText(outcome[0] + " yard run by " + homeTeam.getTeamAbbr() + ".");
								updatePosition();
								play(frame, panel);
							}
						}
					}
					else {
						yardLine += outcome[0];
						if (yardLine > 100) {
							int yardsGained = outcome[0] + 100 - yardLine;
							lastPlay.setText(yardsGained + " yard run by " + homeTeam.getTeamAbbr() +
									". TOUCHDOWN!!!");
							touchdown(false, frame, panel);
						}
						else {
							if (yardLine > 50) {
    							yardLine = 100 - yardLine;
    							homeSideOfField = false;
							}
							if (yardsToGo <= 0) {
								down = 1;
    							yardsToGo = 10;
    							if (!homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
								lastPlay.setText(outcome[0] + " yard run by " + homeTeam.getTeamAbbr() +
										". First down!");
							}
							else if (down > 4) {
								down = 1;
    							yardsToGo = 10;
    							if (homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(true);
    							homePossession.setVisible(false);
    							lastPlay.setText(outcome[0] + " yard run by " + homeTeam.getTeamAbbr() +
    									". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeHomePoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(false, frame, panel);;
    			        			}
    			        		}
							}
							else {
								if (down == 2) {
									if (downs.getText().contains("GL")) {
										downs.setText("2nd & GL");
									}
									else {
										downs.setText("2nd & " + yardsToGo);
									}
								}
								else if (down == 3) {
									if (downs.getText().contains("GL")) {
										downs.setText("3rd & GL");
									}
									else {
										downs.setText("3rd & " + yardsToGo);
									}
								}
								else {
									if (downs.getText().contains("GL")) {
										downs.setText("4th & GL");
									}
									else {
										downs.setText("4th & " + yardsToGo);
									}
								}
								lastPlay.setText(outcome[0] + " yard run by " + homeTeam.getTeamAbbr() + ".");
							}
							updatePosition();
							play(frame, panel);
						}
					}
				}
				else if (outcome[0] == 0) {
					if (down == 2) {
						if (downs.getText().contains("GL")) {
							downs.setText("2nd & GL");
						}
						else {
							downs.setText("2nd & " + yardsToGo);
						}
						lastPlay.setText("No gain on the run by " + homeTeam.getTeamAbbr() + ".");
					}
					else if (down == 3) {
						if (downs.getText().contains("GL")) {
							downs.setText("3rd & GL");
						}
						else {
							downs.setText("3rd & " + yardsToGo);
						}
						lastPlay.setText("No gain on the run by " + homeTeam.getTeamAbbr() + ".");
					}
					else if (down == 4) {
						if (downs.getText().contains("GL")) {
							downs.setText("4th & GL");
						}
						else {
							downs.setText("4th & " + yardsToGo);
						}
						lastPlay.setText("No gain on the run by " + homeTeam.getTeamAbbr() + ".");
					}
					else {
						down = 1;
						yardsToGo = 10;
						if (homeSideOfField && yardLine <= 10) {
							downs.setText("1st & GL");
							if (yardLine == 5) {
								yardsToGo = 5;
							}
						}
						else {
							downs.setText("1st & 10");
						}
						awayPossession.setVisible(true);
						homePossession.setVisible(false);
						lastPlay.setText("No gain on the run by " + homeTeam.getTeamAbbr()
						+ ". TURNOVER ON DOWNS!");
						if (overtimeNow) {
		        			overtimeHomePoss = true;
		        			if (!(homeTeam instanceof NFLTeam)) {
		        				kickoff(false, frame, panel);;
		        			}
		        		}
					}
					updatePosition();
					play(frame, panel);
				}
				else {
					if (!homeSideOfField) {
						yardLine -= outcome[0];
						if (yardLine > 50) {
							yardLine = 100 - yardLine;
							homeSideOfField = true;
						}
						if (down == 2) {
							if (downs.getText().contains("GL")) {
								downs.setText("2nd & GL");
							}
							else {
								downs.setText("2nd & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + awayTeam.getTeamAbbr() + ".");
						}
						else if (down == 3) {
							if (downs.getText().contains("GL")) {
								downs.setText("3rd & GL");
							}
							else {
								downs.setText("3rd & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + awayTeam.getTeamAbbr() + ".");
						}
						else if (down == 4) {
							if (downs.getText().contains("GL")) {
								downs.setText("4th & GL");
							}
							else {
								downs.setText("4th & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + awayTeam.getTeamAbbr() + ".");
						}
						else {
							down = 1;
							yardsToGo = 10;
							if (homeSideOfField && yardLine <= 10) {
								downs.setText("1st & GL");
								if (yardLine == 5) {
									yardsToGo = 5;
								}
							}
							else {
								downs.setText("1st & 10");
							}
							awayPossession.setVisible(true);
							homePossession.setVisible(false);
							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + awayTeam.getTeamAbbr()
							+ ". TURNOVER ON DOWNS!");
							if (overtimeNow) {
			        			overtimeHomePoss = true;
			        			if (!(homeTeam instanceof NFLTeam)) {
			        				kickoff(false, frame, panel);;
			        			}
			        		}
						}
						updatePosition();
						play(frame, panel);
					}
					else {
						yardLine += outcome[0];
						if (yardLine <= 0) {
        					lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + awayTeam.getTeamAbbr()
        					+ ". SAFETY!!");
        					if (overtimeNow) {
        	        			overtimeAwayPoss = true;
        	        			overtimeHomePoss = true;
        	        		}
        					awayScore += 2;
        					awayPoints.setText("" + awayScore);
        					if (overtimeNow) {
        						if (overtimeNow && homeTeam instanceof NFLTeam) {
        							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
        							quarter.setText("FINAL");
        							awayTimeout1.setVisible(false);
        							awayTimeout2.setVisible(false);
        							awayTimeout3.setVisible(false);
        							homeTimeout1.setVisible(false);
        							homeTimeout2.setVisible(false);
        							homeTimeout3.setVisible(false);
        							awaySim.setVisible(false);
        							homeSim.setVisible(false);
        							awayKickoff.setVisible(false);
        							awayOnsideKick.setVisible(false);
        							awayPunt.setVisible(false);
        							awayFieldGoal.setVisible(false);
        							awayExtraPoint.setVisible(false);
        							awayTwoPoint.setVisible(false);
        							homeKickoff.setVisible(false);
        							homeOnsideKick.setVisible(false);
        							homePunt.setVisible(false);
        							homeFieldGoal.setVisible(false);
        							homeExtraPoint.setVisible(false);
        							homeTwoPoint.setVisible(false);
        							awayPlay1.setVisible(false);
        							awayPlay2.setVisible(false);
        							awayPlay3.setVisible(false);
        							awayPlay4.setVisible(false);
        							awayPlay5.setVisible(false);
        							homePlay1.setVisible(false);
        							homePlay2.setVisible(false);
        							homePlay3.setVisible(false);
        							homePlay4.setVisible(false);
        							homePlay5.setVisible(false);
        							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
        							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        							returnToMainMenu.setBackground(Color.BLACK);
        							returnToMainMenu.setForeground(Color.WHITE);
        							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
        							returnToMainMenu.addActionListener(new ActionListener() {
        								public void actionPerformed(ActionEvent e) {
        									mainMenu.mainMenu();
        								}
        							});
        							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
        						}
        					}
        					else {
        						kickoff(false, frame, panel);;
        					}
						}
						else {
    						if (down == 2) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("2nd & GL");
    							}
    							else {
    								downs.setText("2nd & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + awayTeam.getTeamAbbr() + ".");
    						}
    						else if (down == 3) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("3rd & GL");
    							}
    							else {
    								downs.setText("3rd & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + awayTeam.getTeamAbbr() + ".");
    						}
    						else if (down == 4) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("4th & GL");
    							}
    							else {
    								downs.setText("4th & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + awayTeam.getTeamAbbr() + ".");
    						}
    						else {
    							down = 1;
    							yardsToGo = 10;
    							if (homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(true);
    							homePossession.setVisible(false);
    							lastPlay.setText(Math.abs(outcome[0]) + " yard TACKLE FOR A LOSS by " + awayTeam.getTeamAbbr()
    							+ ". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeHomePoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(false, frame, panel);;
    			        			}
    			        		}
    						}
    						updatePosition();
    						play(frame, panel);
						}
					}
				}
			}
			else if (off.getName().contains("pass")) {
				if (outcome[0] > 0) {
					if (!homeSideOfField) {
						yardLine -= outcome[0];
						if (yardLine <= 0) {
							int yardsGained = outcome[0] + yardLine;
							lastPlay.setText(yardsGained + " yard pass by " + homeTeam.getTeamAbbr() +
									". TOUCHDOWN!!!");
							touchdown(false, frame, panel);
						}
						else {
							if (yardsToGo <= 0) {
								down = 1;
    							yardsToGo = 10;
    							if (!homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
								lastPlay.setText(outcome[0] + " yard pass by " + homeTeam.getTeamAbbr() +
										". First down!");
								updatePosition();
    							play(frame, panel);
							}
							else if (down > 4) {
								down = 1;
    							yardsToGo = 10;
    							if (homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(true);
    							homePossession.setVisible(false);
    							lastPlay.setText(outcome[0] + " yard pass by " + homeTeam.getTeamAbbr() +
    									". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeHomePoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(false, frame, panel);;
    			        			}
    			        		}
    							updatePosition();
    							play(frame, panel);
							}
							else {
								if (down == 2) {
									if (downs.getText().contains("GL")) {
										downs.setText("2nd & GL");
									}
									else {
										downs.setText("2nd & " + yardsToGo);
									}
								}
								else if (down == 3) {
									if (downs.getText().contains("GL")) {
										downs.setText("3rd & GL");
									}
									else {
										downs.setText("3rd & " + yardsToGo);
									}
								}
								else {
									if (downs.getText().contains("GL")) {
										downs.setText("4th & GL");
									}
									else {
										downs.setText("4th & " + yardsToGo);
									}
								}
								lastPlay.setText(outcome[0] + " yard pass by " + homeTeam.getTeamAbbr() + ".");
								updatePosition();
								play(frame, panel);
							}
						}
					}
					else {
						yardLine += outcome[0];
						if (yardLine > 100) {
							int yardsGained = outcome[0] + 100 - yardLine;
							lastPlay.setText(yardsGained + " yard pass by " + homeTeam.getTeamAbbr() +
									". TOUCHDOWN!!!");
							touchdown(false, frame, panel);
						}
						else {
							if (yardLine > 50) {
    							yardLine = 100 - yardLine;
    							homeSideOfField = false;
							}
							if (yardsToGo <= 0) {
								down = 1;
    							yardsToGo = 10;
    							if (!homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
								lastPlay.setText(outcome[0] + " yard pass by " + homeTeam.getTeamAbbr() +
										". First down!");
							}
							else if (down > 4) {
								down = 1;
    							yardsToGo = 10;
    							if (homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(true);
    							homePossession.setVisible(false);
    							lastPlay.setText(outcome[0] + " yard pass by " + homeTeam.getTeamAbbr() +
    									". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeHomePoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(false, frame, panel);;
    			        			}
    			        		}
							}
							else {
								if (down == 2) {
									if (downs.getText().contains("GL")) {
										downs.setText("2nd & GL");
									}
									else {
										downs.setText("2nd & " + yardsToGo);
									}
								}
								else if (down == 3) {
									if (downs.getText().contains("GL")) {
										downs.setText("3rd & GL");
									}
									else {
										downs.setText("3rd & " + yardsToGo);
									}
								}
								else {
									if (downs.getText().contains("GL")) {
										downs.setText("4th & GL");
									}
									else {
										downs.setText("4th & " + yardsToGo);
									}
								}
								lastPlay.setText(outcome[0] + " yard pass by " + homeTeam.getTeamAbbr() + ".");
							}
							updatePosition();
							play(frame, panel);
						}
					}
				}
				else if (outcome[0] == 0) {
					if (down == 2) {
						if (downs.getText().contains("GL")) {
							downs.setText("2nd & GL");
						}
						else {
							downs.setText("2nd & " + yardsToGo);
						}
						lastPlay.setText("Incomplete pass by " + homeTeam.getTeamAbbr() + ".");
					}
					else if (down == 3) {
						if (downs.getText().contains("GL")) {
							downs.setText("3rd & GL");
						}
						else {
							downs.setText("3rd & " + yardsToGo);
						}
						lastPlay.setText("Incomplete pass by " + homeTeam.getTeamAbbr() + ".");
					}
					else if (down == 4) {
						if (downs.getText().contains("GL")) {
							downs.setText("4th & GL");
						}
						else {
							downs.setText("4th & " + yardsToGo);
						}
						lastPlay.setText("Incomplete pass by " + homeTeam.getTeamAbbr() + ".");
					}
					else {
						down = 1;
						yardsToGo = 10;
						if (homeSideOfField && yardLine <= 10) {
							downs.setText("1st & GL");
							if (yardLine == 5) {
								yardsToGo = 5;
							}
						}
						else {
							downs.setText("1st & 10");
						}
						awayPossession.setVisible(true);
						homePossession.setVisible(false);
						lastPlay.setText("Incomplete pass by " + homeTeam.getTeamAbbr()
						+ ". TURNOVER ON DOWNS!");
						if (overtimeNow) {
		        			overtimeHomePoss = true;
		        			if (!(homeTeam instanceof NFLTeam)) {
		        				kickoff(false, frame, panel);;
		        			}
		        		}
					}
					updatePosition();
					play(frame, panel);
				}
				else {
					if (!homeSideOfField) {
						yardLine -= outcome[0];
						if (yardLine > 50) {
							yardLine = 100 - yardLine;
							homeSideOfField = true;
						}
						if (down == 2) {
							if (downs.getText().contains("GL")) {
								downs.setText("2nd & GL");
							}
							else {
								downs.setText("2nd & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + awayTeam.getTeamAbbr() + ".");
						}
						else if (down == 3) {
							if (downs.getText().contains("GL")) {
								downs.setText("3rd & GL");
							}
							else {
								downs.setText("3rd & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + awayTeam.getTeamAbbr() + ".");
						}
						else if (down == 4) {
							if (downs.getText().contains("GL")) {
								downs.setText("4th & GL");
							}
							else {
								downs.setText("4th & " + yardsToGo);
							}
							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + awayTeam.getTeamAbbr() + ".");
						}
						else {
							down = 1;
							yardsToGo = 10;
							if (homeSideOfField && yardLine <= 10) {
								downs.setText("1st & GL");
								if (yardLine == 5) {
									yardsToGo = 5;
								}
							}
							else {
								downs.setText("1st & 10");
							}
							awayPossession.setVisible(true);
							homePossession.setVisible(false);
							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + awayTeam.getTeamAbbr()
							+ ". TURNOVER ON DOWNS!");
							if (overtimeNow) {
			        			overtimeHomePoss = true;
			        			if (!(homeTeam instanceof NFLTeam)) {
			        				kickoff(false, frame, panel);;
			        			}
			        		}
						}
						updatePosition();
						play(frame, panel);
					}
					else {
						yardLine += outcome[0];
						if (yardLine <= 0) {
        					lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + awayTeam.getTeamAbbr()
        					+ ". SAFETY!!");
        					if (overtimeNow) {
        	        			overtimeAwayPoss = true;
        	        			overtimeHomePoss = true;
        	        		}
        					awayScore += 2;
        					awayPoints.setText("" + awayScore);
        					if (overtimeNow) {
        						if (overtimeNow && homeTeam instanceof NFLTeam) {
        							lastPlay.setText(lastPlay.getText() + "\nEND OF OVERTIME PERIOD. GAME OVER!");
        							quarter.setText("FINAL");
        							awayTimeout1.setVisible(false);
        							awayTimeout2.setVisible(false);
        							awayTimeout3.setVisible(false);
        							homeTimeout1.setVisible(false);
        							homeTimeout2.setVisible(false);
        							homeTimeout3.setVisible(false);
        							awaySim.setVisible(false);
        							homeSim.setVisible(false);
        							awayKickoff.setVisible(false);
        							awayOnsideKick.setVisible(false);
        							awayPunt.setVisible(false);
        							awayFieldGoal.setVisible(false);
        							awayExtraPoint.setVisible(false);
        							awayTwoPoint.setVisible(false);
        							homeKickoff.setVisible(false);
        							homeOnsideKick.setVisible(false);
        							homePunt.setVisible(false);
        							homeFieldGoal.setVisible(false);
        							homeExtraPoint.setVisible(false);
        							homeTwoPoint.setVisible(false);
        							awayPlay1.setVisible(false);
        							awayPlay2.setVisible(false);
        							awayPlay3.setVisible(false);
        							awayPlay4.setVisible(false);
        							awayPlay5.setVisible(false);
        							homePlay1.setVisible(false);
        							homePlay2.setVisible(false);
        							homePlay3.setVisible(false);
        							homePlay4.setVisible(false);
        							homePlay5.setVisible(false);
        							JButton returnToMainMenu = new JButton("RETURN TO MAIN MENU");
        							returnToMainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        							returnToMainMenu.setBackground(Color.BLACK);
        							returnToMainMenu.setForeground(Color.WHITE);
        							returnToMainMenu.setBounds(0, 530, frame.getWidth(), 60);
        							returnToMainMenu.addActionListener(new ActionListener() {
        								public void actionPerformed(ActionEvent e) {
        									mainMenu.mainMenu();
        								}
        							});
        							returnToMainMenu.setVisible(true); panel.add(returnToMainMenu); frame.revalidate();
        						}
        					}
        					else {
        						kickoff(false, frame, panel);;
        					}
						}
						else {
    						if (down == 2) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("2nd & GL");
    							}
    							else {
    								downs.setText("2nd & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + awayTeam.getTeamAbbr() + ".");
    						}
    						else if (down == 3) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("3rd & GL");
    							}
    							else {
    								downs.setText("3rd & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + awayTeam.getTeamAbbr() + ".");
    						}
    						else if (down == 4) {
    							if (downs.getText().contains("GL")) {
    								downs.setText("4th & GL");
    							}
    							else {
    								downs.setText("4th & " + yardsToGo);
    							}
    							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + awayTeam.getTeamAbbr() + ".");
    						}
    						else {
    							down = 1;
    							yardsToGo = 10;
    							if (homeSideOfField && yardLine <= 10) {
    								downs.setText("1st & GL");
    								if (yardLine == 5) {
    									yardsToGo = 5;
    								}
    							}
    							else {
    								downs.setText("1st & 10");
    							}
    							awayPossession.setVisible(true);
    							homePossession.setVisible(false);
    							lastPlay.setText(Math.abs(outcome[0]) + " yard SACK by " + awayTeam.getTeamAbbr()
    							+ ". TURNOVER ON DOWNS!");
    							if (overtimeNow) {
    			        			overtimeHomePoss = true;
    			        			if (!(homeTeam instanceof NFLTeam)) {
    			        				kickoff(false, frame, panel);;
    			        			}
    			        		}
    						}
    						updatePosition();
    						play(frame, panel);
						}
					}
				}
			}
		}
	}
	
	// Updates the position text
	private void updatePosition() {
		if (yardLine == 50) {
			position.setText("50");
		}
		else if (homeSideOfField) {
			position.setText(homeTeam.getTeamAbbr() + " " + yardLine);
		}
		else {
			position.setText(awayTeam.getTeamAbbr() + " " + yardLine);
		}
	}
	
	// Clears the away simulator's action listeners
	private void clearAwaySimActionListeners() {
		for (ActionListener al : awaySim.getActionListeners()) {
			awaySim.removeActionListener(al);
		}
	}
	
	// Clears the home simulator's action listeners
	private void clearHomeSimActionListeners() {
		for (ActionListener al : homeSim.getActionListeners()) {
			homeSim.removeActionListener(al);
		}
	}
	
	// Simulates a normal offense vs. defense play
	private void simStuff() {
		clearAwaySimActionListeners();
		clearHomeSimActionListeners();
		awaySim.setVisible(true);
		homeSim.setVisible(true);
		awaySim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] times = timeLeft.split(":");
				int minutes = Integer.parseInt(times[0]);
				boolean played = false;
				String timeoutCalled = null;
				
				if ((quarterNum == 2 || quarterNum > 4) && minutes == 1 && down != 4) {
					if (awayPossession.isVisible()) {
						if (awayTimeout1.isEnabled()) {
							awayTimeout1.doClick();
							int timeoutsUsed = 1;
			        		if (!awayTimeout2.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		if (!awayTimeout3.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		timeoutCalled = "Timeout #" + timeoutsUsed + " " + awayTeam.getTeamAbbr() + ".";
						}
						else if (awayTimeout2.isEnabled()) {
							awayTimeout2.doClick();
							int timeoutsUsed = 1;
							if (!awayTimeout1.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		if (!awayTimeout3.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		timeoutCalled = "Timeout #" + timeoutsUsed + " " + awayTeam.getTeamAbbr() + ".";
						}
						else if (awayTimeout3.isEnabled()) {
							awayTimeout3.doClick();
							int timeoutsUsed = 1;
							if (!awayTimeout1.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		if (!awayTimeout2.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		timeoutCalled = "Timeout #" + timeoutsUsed + " " + awayTeam.getTeamAbbr() + ".";
						}
						else {
							played = true;
							if (awayFieldGoal.isEnabled()) {
								awayFieldGoal.doClick();
							}
							else {
								awaySimPlay();
							}
						}
					}
					else {
						if (homeTimeout1.isEnabled()) {
							homeTimeout1.doClick();
							int timeoutsUsed = 1;
			        		if (!homeTimeout2.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		if (!homeTimeout3.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		timeoutCalled = "Timeout #" + timeoutsUsed + " " + homeTeam.getTeamAbbr() + ".";
						}
						else if (homeTimeout2.isEnabled()) {
							homeTimeout2.doClick();
							int timeoutsUsed = 1;
			        		if (!homeTimeout1.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		if (!homeTimeout3.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		timeoutCalled = "Timeout #" + timeoutsUsed + " " + homeTeam.getTeamAbbr() + ".";
						}
						else if (homeTimeout3.isEnabled()) {
							homeTimeout3.doClick();
							int timeoutsUsed = 1;
			        		if (!homeTimeout1.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		if (!homeTimeout2.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		timeoutCalled = "Timeout #" + timeoutsUsed + " " + homeTeam.getTeamAbbr() + ".";
						}
						else {
							played = true;
							if (homeFieldGoal.isEnabled()) {
								homeFieldGoal.doClick();
							}
							else {
								homeSimPlay();
							}
						}
					}
				}
				
				if (quarterNum == 4 && minutes == 1) {
					if (awayScore < homeScore || (awayScore == homeScore && awayPossession.isVisible())) {
						if (awayTimeout1.isEnabled()) {
							awayTimeout1.doClick();
							int timeoutsUsed = 1;
			        		if (!awayTimeout2.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		if (!awayTimeout3.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		timeoutCalled = "Timeout #" + timeoutsUsed + " " + awayTeam.getTeamAbbr() + ".";
						}
						else if (awayTimeout2.isEnabled()) {
							awayTimeout2.doClick();
							int timeoutsUsed = 1;
			        		if (!awayTimeout1.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		if (!awayTimeout3.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		timeoutCalled = "Timeout #" + timeoutsUsed + " " + awayTeam.getTeamAbbr() + ".";
						}
						else if (awayTimeout3.isEnabled()) {
							awayTimeout3.doClick();
							int timeoutsUsed = 1;
			        		if (!awayTimeout1.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		if (!awayTimeout2.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		timeoutCalled = "Timeout #" + timeoutsUsed + " " + awayTeam.getTeamAbbr() + ".";
						}
						else if (awayPossession.isVisible()) {
							played = true;
							if (awayScore + 3 >= homeScore && awayFieldGoal.isEnabled()) {
								awayFieldGoal.doClick();
							}
							else {
								awaySimPlay();
							}
						}
					}
					else if (homeScore <= awayScore || (homeScore == awayScore && homePossession.isVisible())) {
						if (homeTimeout1.isEnabled()) {
							homeTimeout1.doClick();
							int timeoutsUsed = 1;
			        		if (!homeTimeout2.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		if (!homeTimeout3.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		timeoutCalled = "Timeout #" + timeoutsUsed + " " + homeTeam.getTeamAbbr() + ".";
						}
						else if (homeTimeout2.isEnabled()) {
							homeTimeout2.doClick();
							int timeoutsUsed = 1;
			        		if (!homeTimeout1.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		if (!homeTimeout3.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		timeoutCalled = "Timeout #" + timeoutsUsed + " " + homeTeam.getTeamAbbr() + ".";
						}
						else if (homeTimeout3.isEnabled()) {
							homeTimeout3.doClick();
							int timeoutsUsed = 1;
			        		if (!homeTimeout1.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		if (!homeTimeout2.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		timeoutCalled = "Timeout #" + timeoutsUsed + " " + homeTeam.getTeamAbbr() + ".";
						}
						else if (homePossession.isVisible()) {
							played = true;
							if (homeScore + 3 >= awayScore && homeFieldGoal.isEnabled()) {
								homeFieldGoal.doClick();
							}
							else {
								homeSimPlay();
							}
						}
					}
				}
				
				if (!played) {
					
					if (down == 4) {
						if (awayPossession.isVisible()) {
							if (yardsToGo == 5 &&
									((homeSideOfField && yardLine == 40) || (homeSideOfField && yardLine == 45) || yardLine == 50)) {
								awaySimPlay();
							}
							else if (quarterNum == 4 && minutes <= 5 && awayScore < homeScore) {
								if (awayFieldGoal.isEnabled() && awayScore + 3 >= homeScore) {
									awayFieldGoal.doClick();
								}
								else {
									awaySimPlay();
								}
							}
							else if (quarterNum == 4 && minutes <= 10 && awayScore + 8 < homeScore) {
								if (awayFieldGoal.isEnabled() && awayScore + 11 <= homeScore) {
									awayFieldGoal.doClick();
								}
								else {
									awaySimPlay();
								}
							}
							else if (quarterNum == 4 && minutes <= 15 && awayScore + 16 < homeScore) {
								if (awayFieldGoal.isEnabled() && awayScore + 19 <= homeScore) {
									awayFieldGoal.doClick();
								}
								else {
									awaySimPlay();
								}
							}
							else if (overtimeNow && !(homeTeam instanceof NFLTeam) && numCollegeOTPoss % 2 == 0 && awayScore + 3 < homeScore) {
								awaySimPlay();
							}
							else {
								if (awayFieldGoal.isEnabled()) {
									awayFieldGoal.doClick();
								}
								else {
									if (!overtimeNow || homeTeam instanceof NFLTeam) {
										awayPunt.doClick();
									}
									else {
										awaySimPlay();
									}
								}
							}
						}
						else {
							if (yardsToGo == 5 &&
									((!homeSideOfField && yardLine == 40) || (!homeSideOfField && yardLine == 45) || yardLine == 50)) {
								homeSimPlay();
							}
							else if (quarterNum == 4 && minutes <= 5 && homeScore < awayScore) {
								if (homeFieldGoal.isEnabled() && homeScore + 3 >= awayScore) {
									homeFieldGoal.doClick();
								}
								else {
									homeSimPlay();
								}
							}
							else if (quarterNum == 4 && minutes <= 10 && homeScore + 8 < awayScore) {
								if (homeFieldGoal.isEnabled() && homeScore + 11 <= awayScore) {
									homeFieldGoal.doClick();
								}
								else {
									homeSimPlay();
								}
							}
							else if (quarterNum == 4 && minutes <= 15 && homeScore + 16 < awayScore) {
								if (homeFieldGoal.isEnabled() && homeScore + 19 <= awayScore) {
									homeFieldGoal.doClick();
								}
								else {
									homeSimPlay();
								}
							}
							else if (overtimeNow && !(homeTeam instanceof NFLTeam) && numCollegeOTPoss % 2 == 0 && homeScore + 3 < awayScore) {
								homeSimPlay();
							}
							else {
								if (homeFieldGoal.isEnabled()) {
									homeFieldGoal.doClick();
								}
								else {
									if (!overtimeNow || homeTeam instanceof NFLTeam) {
										homePunt.doClick();
									}
									else {
										homeSimPlay();
									}
								}
							}
						}
					}
					
					else {
						if (awayPossession.isVisible()) {
							awaySimPlay();
						}
						else {
							homeSimPlay();
						}
					}
				}
				if (timeoutCalled != null) {
					lastPlay.setText(timeoutCalled + "\n" + lastPlay.getText());
				}
			}
		});
		homeSim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] times = timeLeft.split(":");
				int minutes = Integer.parseInt(times[0]);
				boolean played = false;
				String timeoutCalled = null;
				
				if ((quarterNum == 2 || quarterNum > 4) && minutes == 1 && down != 4) {
					if (awayPossession.isVisible()) {
						if (awayTimeout1.isEnabled()) {
							awayTimeout1.doClick();
							int timeoutsUsed = 1;
			        		if (!awayTimeout2.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		if (!awayTimeout3.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		timeoutCalled = "Timeout #" + timeoutsUsed + " " + awayTeam.getTeamAbbr() + ".";
						}
						else if (awayTimeout2.isEnabled()) {
							awayTimeout2.doClick();
							int timeoutsUsed = 1;
							if (!awayTimeout1.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		if (!awayTimeout3.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		timeoutCalled = "Timeout #" + timeoutsUsed + " " + awayTeam.getTeamAbbr() + ".";
						}
						else if (awayTimeout3.isEnabled()) {
							awayTimeout3.doClick();
							int timeoutsUsed = 1;
							if (!awayTimeout1.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		if (!awayTimeout2.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		timeoutCalled = "Timeout #" + timeoutsUsed + " " + awayTeam.getTeamAbbr() + ".";
						}
						else {
							played = true;
							if (awayFieldGoal.isEnabled()) {
								awayFieldGoal.doClick();
							}
							else {
								awaySimPlay();
							}
						}
					}
					else {
						if (homeTimeout1.isEnabled()) {
							homeTimeout1.doClick();
							int timeoutsUsed = 1;
			        		if (!homeTimeout2.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		if (!homeTimeout3.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		timeoutCalled = "Timeout #" + timeoutsUsed + " " + homeTeam.getTeamAbbr() + ".";
						}
						else if (homeTimeout2.isEnabled()) {
							homeTimeout2.doClick();
							int timeoutsUsed = 1;
			        		if (!homeTimeout1.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		if (!homeTimeout3.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		timeoutCalled = "Timeout #" + timeoutsUsed + " " + homeTeam.getTeamAbbr() + ".";
						}
						else if (homeTimeout3.isEnabled()) {
							homeTimeout3.doClick();
							int timeoutsUsed = 1;
			        		if (!homeTimeout1.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		if (!homeTimeout2.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		timeoutCalled = "Timeout #" + timeoutsUsed + " " + homeTeam.getTeamAbbr() + ".";
						}
						else {
							played = true;
							if (homeFieldGoal.isEnabled()) {
								homeFieldGoal.doClick();
							}
							else {
								homeSimPlay();
							}
						}
					}
				}
				
				if (quarterNum == 4 && minutes == 1) {
					if (awayScore < homeScore || (awayScore == homeScore && awayPossession.isVisible())) {
						if (awayTimeout1.isEnabled()) {
							awayTimeout1.doClick();
							int timeoutsUsed = 1;
			        		if (!awayTimeout2.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		if (!awayTimeout3.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		timeoutCalled = "Timeout #" + timeoutsUsed + " " + awayTeam.getTeamAbbr() + ".";
						}
						else if (awayTimeout2.isEnabled()) {
							awayTimeout2.doClick();
							int timeoutsUsed = 1;
			        		if (!awayTimeout1.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		if (!awayTimeout3.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		timeoutCalled = "Timeout #" + timeoutsUsed + " " + awayTeam.getTeamAbbr() + ".";
						}
						else if (awayTimeout3.isEnabled()) {
							awayTimeout3.doClick();
							int timeoutsUsed = 1;
			        		if (!awayTimeout1.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		if (!awayTimeout2.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		timeoutCalled = "Timeout #" + timeoutsUsed + " " + awayTeam.getTeamAbbr() + ".";
						}
						else if (awayPossession.isVisible()) {
							played = true;
							if (awayScore + 3 >= homeScore && awayFieldGoal.isEnabled()) {
								awayFieldGoal.doClick();
							}
							else {
								awaySimPlay();
							}
						}
					}
					else if (homeScore <= awayScore || (homeScore == awayScore && homePossession.isVisible())) {
						if (homeTimeout1.isEnabled()) {
							homeTimeout1.doClick();
							int timeoutsUsed = 1;
			        		if (!homeTimeout2.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		if (!homeTimeout3.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		timeoutCalled = "Timeout #" + timeoutsUsed + " " + homeTeam.getTeamAbbr() + ".";
						}
						else if (homeTimeout2.isEnabled()) {
							homeTimeout2.doClick();
							int timeoutsUsed = 1;
			        		if (!homeTimeout1.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		if (!homeTimeout3.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		timeoutCalled = "Timeout #" + timeoutsUsed + " " + homeTeam.getTeamAbbr() + ".";
						}
						else if (homeTimeout3.isEnabled()) {
							homeTimeout3.doClick();
							int timeoutsUsed = 1;
			        		if (!homeTimeout1.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		if (!homeTimeout2.isEnabled()) {
			        			timeoutsUsed++;
			        		}
			        		timeoutCalled = "Timeout #" + timeoutsUsed + " " + homeTeam.getTeamAbbr() + ".";
						}
						else if (homePossession.isVisible()) {
							played = true;
							if (homeScore + 3 >= awayScore && homeFieldGoal.isEnabled()) {
								homeFieldGoal.doClick();
							}
							else {
								homeSimPlay();
							}
						}
					}
				}
				
				if (!played) {
					
					if (down == 4) {
						if (awayPossession.isVisible()) {
							if (yardsToGo == 5 &&
									((homeSideOfField && yardLine == 40) || (homeSideOfField && yardLine == 45) || yardLine == 50)) {
								awaySimPlay();
							}
							else if (quarterNum == 4 && minutes <= 5 && awayScore < homeScore) {
								if (awayFieldGoal.isEnabled() && awayScore + 3 >= homeScore) {
									awayFieldGoal.doClick();
								}
								else {
									awaySimPlay();
								}
							}
							else if (quarterNum == 4 && minutes <= 10 && awayScore + 8 < homeScore) {
								if (awayFieldGoal.isEnabled() && awayScore + 11 <= homeScore) {
									awayFieldGoal.doClick();
								}
								else {
									awaySimPlay();
								}
							}
							else if (quarterNum == 4 && minutes <= 15 && awayScore + 16 < homeScore) {
								if (awayFieldGoal.isEnabled() && awayScore + 19 <= homeScore) {
									awayFieldGoal.doClick();
								}
								else {
									awaySimPlay();
								}
							}
							else if (overtimeNow && !(homeTeam instanceof NFLTeam) && numCollegeOTPoss % 2 == 0 && awayScore + 3 < homeScore) {
								awaySimPlay();
							}
							else {
								if (awayFieldGoal.isEnabled()) {
									awayFieldGoal.doClick();
								}
								else {
									if (!overtimeNow || homeTeam instanceof NFLTeam) {
										awayPunt.doClick();
									}
									else {
										awaySimPlay();
									}
								}
							}
						}
						else {
							if (yardsToGo == 5 &&
									((!homeSideOfField && yardLine == 40) || (!homeSideOfField && yardLine == 45) || yardLine == 50)) {
								homeSimPlay();
							}
							else if (quarterNum == 4 && minutes <= 5 && homeScore < awayScore) {
								if (homeFieldGoal.isEnabled() && homeScore + 3 >= awayScore) {
									homeFieldGoal.doClick();
								}
								else {
									homeSimPlay();
								}
							}
							else if (quarterNum == 4 && minutes <= 10 && homeScore + 8 < awayScore) {
								if (homeFieldGoal.isEnabled() && homeScore + 11 <= awayScore) {
									homeFieldGoal.doClick();
								}
								else {
									homeSimPlay();
								}
							}
							else if (quarterNum == 4 && minutes <= 15 && homeScore + 16 < awayScore) {
								if (homeFieldGoal.isEnabled() && homeScore + 19 <= awayScore) {
									homeFieldGoal.doClick();
								}
								else {
									homeSimPlay();
								}
							}
							else if (overtimeNow && !(homeTeam instanceof NFLTeam) && numCollegeOTPoss % 2 == 0 && homeScore + 3 < awayScore) {
								homeSimPlay();
							}
							else {
								if (homeFieldGoal.isEnabled()) {
									homeFieldGoal.doClick();
								}
								else {
									if (!overtimeNow || homeTeam instanceof NFLTeam) {
										homePunt.doClick();
									}
									else {
										homeSimPlay();
									}
								}
							}
						}
					}
					
					else {
						if (awayPossession.isVisible()) {
							awaySimPlay();
						}
						else {
							homeSimPlay();
						}
					}
				}
				if (timeoutCalled != null) {
					lastPlay.setText(timeoutCalled + "\n" + lastPlay.getText());
				}
			}
		});
	}
	
	// Choose a random away play for the simulator
	private void awaySimPlay() {
		List<Integer> availablePlays = new ArrayList<>();
		if (awayPlay1.isEnabled()) {
			availablePlays.add(0);
		}
		if (awayPlay2.isEnabled()) {
			availablePlays.add(1);
		}
		if (awayPlay3.isEnabled()) {
			availablePlays.add(2);
		}
		if (awayPlay4.isEnabled()) {
			availablePlays.add(3);
		}
		if (awayPlay5.isEnabled()) {
			availablePlays.add(4);
		}
		int randomIndex = (int) (Math.random() * availablePlays.size());
		if (availablePlays.get(randomIndex) == 0) {
			awayPlay1.doClick();
		}
		if (availablePlays.get(randomIndex) == 1) {
			awayPlay2.doClick();
		}
		if (availablePlays.get(randomIndex) == 2) {
			awayPlay3.doClick();
		}
		if (availablePlays.get(randomIndex) == 3) {
			awayPlay4.doClick();
		}
		if (availablePlays.get(randomIndex) == 4) {
			awayPlay5.doClick();
		}
	}
	
	// Choose a random home play for the simulator
	private void homeSimPlay() {
		List<Integer> availablePlays = new ArrayList<>();
		if (homePlay1.isEnabled()) {
			availablePlays.add(0);
		}
		if (homePlay2.isEnabled()) {
			availablePlays.add(1);
		}
		if (homePlay3.isEnabled()) {
			availablePlays.add(2);
		}
		if (homePlay4.isEnabled()) {
			availablePlays.add(3);
		}
		if (homePlay5.isEnabled()) {
			availablePlays.add(4);
		}
		int randomIndex = (int) (Math.random() * availablePlays.size());
		if (availablePlays.get(randomIndex) == 0) {
			homePlay1.doClick();
		}
		if (availablePlays.get(randomIndex) == 1) {
			homePlay2.doClick();
		}
		if (availablePlays.get(randomIndex) == 2) {
			homePlay3.doClick();
		}
		if (availablePlays.get(randomIndex) == 3) {
			homePlay4.doClick();
		}
		if (availablePlays.get(randomIndex) == 4) {
			homePlay5.doClick();
		}
	}
	
	// Update the play probabilities for the away team
	private void updateAwayRanks() {
		double passOff = 16.5 - ((NFLTeam) awayTeam).getPassOffRank();
		double rushOff = 16.5 - ((NFLTeam) awayTeam).getRushOffRank();
		double passDef = 16.5 - ((NFLTeam) awayTeam).getPassDefRank();
		double rushDef = 16.5 - ((NFLTeam) awayTeam).getRushDefRank();
		if (!neutral) {
			passOff -= 4;
			rushOff -= 4;
			passDef -= 4;
			rushDef -= 4;
		}
		passOff /= 100;
		rushOff /= 100;
		passDef /= 100;
		rushDef /= 100;
		
		awayPassOff[0] -= passOff;
		awayPassOff[1] -= passOff;
		awayPassOff[3] += passOff;
		awayPassOff[4] += passOff;
		awayPassOff[0] = (double) Math.round(awayPassOff[0] * 10000d) / 10000d;
		awayPassOff[1] = (double) Math.round(awayPassOff[1] * 10000d) / 10000d;
		awayPassOff[3] = (double) Math.round(awayPassOff[3] * 10000d) / 10000d;
		awayPassOff[4] = (double) Math.round(awayPassOff[4] * 10000d) / 10000d;
		if (awayPassOff[3] <= 0) {
			awayPassOff[0] = .45;
			awayPassOff[3] = 0;
		}
		if (awayPassOff[4] <= 0) {
			awayPassOff[1] = .35;
			awayPassOff[4] = 0;
		}
		
		awayRushOff[0] -= rushOff;
		awayRushOff[1] -= rushOff;
		awayRushOff[3] += rushOff;
		awayRushOff[4] += rushOff;
		awayRushOff[0] = (double) Math.round(awayRushOff[0] * 10000d) / 10000d;
		awayRushOff[1] = (double) Math.round(awayRushOff[1] * 10000d) / 10000d;
		awayRushOff[3] = (double) Math.round(awayRushOff[3] * 10000d) / 10000d;
		awayRushOff[4] = (double) Math.round(awayRushOff[4] * 10000d) / 10000d;
		if (awayRushOff[3] <= 0) {
			awayRushOff[0] = .45;
			awayRushOff[3] = 0;
		}
		if (awayRushOff[4] <= 0) {
			awayRushOff[1] = .35;
			awayRushOff[4] = 0;
		}
		
		awayRushDrawOff[0] -= rushOff;
		awayRushDrawOff[1] -= rushOff;
		awayRushDrawOff[2] += rushOff;
		awayRushDrawOff[3] += rushOff;
		awayRushDrawOff[0] = (double) Math.round(awayRushDrawOff[0] * 10000d) / 10000d;
		awayRushDrawOff[1] = (double) Math.round(awayRushDrawOff[1] * 10000d) / 10000d;
		awayRushDrawOff[2] = (double) Math.round(awayRushDrawOff[2] * 10000d) / 10000d;
		awayRushDrawOff[3] = (double) Math.round(awayRushDrawOff[3] * 10000d) / 10000d;
		if (awayRushDrawOff[2] <= 0) {
			awayRushDrawOff[0] = .55;
			awayRushDrawOff[2] = 0;
		}
		if (awayRushDrawOff[3] <= 0) {
			awayRushDrawOff[1] = .45;
			awayRushDrawOff[3] = 0;
		}
		
		awayPassDef[0] -= passDef;
		awayPassDef[1] += passDef / 2;
		awayPassDef[2] += passDef / 2;
		awayPassDef[0] = (double) Math.round(awayPassDef[0] * 10000d) / 10000d;
		awayPassDef[1] = (double) Math.round(awayPassDef[1] * 10000d) / 10000d;
		awayPassDef[2] = (double) Math.round(awayPassDef[2] * 10000d) / 10000d;
		if (awayPassDef[2] <= .01) {
			awayPassDef[0] = .99 - awayPassDef[1];
			awayPassDef[0] = (double) Math.round(awayPassDef[0] * 10000d) / 10000d;
			awayPassDef[2] = .01;
		}
		
		awayRushDef[0] -= rushDef;
		awayRushDef[1] += rushDef / 2;
		awayRushDef[2] += rushDef / 2;
		awayRushDef[0] = (double) Math.round(awayRushDef[0] * 10000d) / 10000d;
		awayRushDef[1] = (double) Math.round(awayRushDef[1] * 10000d) / 10000d;
		awayRushDef[2] = (double) Math.round(awayRushDef[2] * 10000d) / 10000d;
		if (awayRushDef[2] <= .01) {
			awayRushDef[0] = .99 - awayRushDef[1];
			awayRushDef[0] = (double) Math.round(awayRushDef[0] * 10000d) / 10000d;
			awayRushDef[2] = .01;
		}
	}
	
	// Update the play probabilities for the home team
	private void updateHomeRanks() {
		double passOff = 16.5 - ((NFLTeam) homeTeam).getPassOffRank();
		double rushOff = 16.5 - ((NFLTeam) homeTeam).getRushOffRank();
		double passDef = 16.5 - ((NFLTeam) homeTeam).getPassDefRank();
		double rushDef = 16.5 - ((NFLTeam) homeTeam).getRushDefRank();
		if (!neutral) {
			passOff += 4;
			rushOff += 4;
			passDef += 4;
			rushDef += 4;
		}
		passOff /= 100;
		rushOff /= 100;
		passDef /= 100;
		rushDef /= 100;
		
		homePassOff[0] -= passOff;
		homePassOff[1] -= passOff;
		homePassOff[3] += passOff;
		homePassOff[4] += passOff;
		homePassOff[0] = (double) Math.round(homePassOff[0] * 10000d) / 10000d;
		homePassOff[1] = (double) Math.round(homePassOff[1] * 10000d) / 10000d;
		homePassOff[3] = (double) Math.round(homePassOff[3] * 10000d) / 10000d;
		homePassOff[4] = (double) Math.round(homePassOff[4] * 10000d) / 10000d;
		if (homePassOff[3] <= 0) {
			homePassOff[0] = .45;
			homePassOff[3] = 0;
		}
		if (homePassOff[4] <= 0) {
			homePassOff[1] = .35;
			homePassOff[4] = 0;
		}
		
		homeRushOff[0] -= rushOff;
		homeRushOff[1] -= rushOff;
		homeRushOff[3] += rushOff;
		homeRushOff[4] += rushOff;
		homeRushOff[0] = (double) Math.round(homeRushOff[0] * 10000d) / 10000d;
		homeRushOff[1] = (double) Math.round(homeRushOff[1] * 10000d) / 10000d;
		homeRushOff[3] = (double) Math.round(homeRushOff[3] * 10000d) / 10000d;
		homeRushOff[4] = (double) Math.round(homeRushOff[4] * 10000d) / 10000d;
		if (homeRushOff[3] <= 0) {
			homeRushOff[0] = .45;
			homeRushOff[3] = 0;
		}
		if (homeRushOff[4] <= 0) {
			homeRushOff[1] = .35;
			homeRushOff[4] = 0;
		}
		
		homeRushDrawOff[0] -= rushOff;
		homeRushDrawOff[1] -= rushOff;
		homeRushDrawOff[2] += rushOff;
		homeRushDrawOff[3] += rushOff;
		homeRushDrawOff[0] = (double) Math.round(homeRushDrawOff[0] * 10000d) / 10000d;
		homeRushDrawOff[1] = (double) Math.round(homeRushDrawOff[1] * 10000d) / 10000d;
		homeRushDrawOff[2] = (double) Math.round(homeRushDrawOff[2] * 10000d) / 10000d;
		homeRushDrawOff[3] = (double) Math.round(homeRushDrawOff[3] * 10000d) / 10000d;
		if (homeRushDrawOff[2] <= 0) {
			homeRushDrawOff[0] = .55;
			homeRushDrawOff[2] = 0;
		}
		if (homeRushDrawOff[3] <= 0) {
			homeRushDrawOff[1] = .45;
			homeRushDrawOff[3] = 0;
		}
		
		homePassDef[0] -= passDef;
		homePassDef[1] += passDef / 2;
		homePassDef[2] += passDef / 2;
		homePassDef[0] = (double) Math.round(homePassDef[0] * 10000d) / 10000d;
		homePassDef[1] = (double) Math.round(homePassDef[1] * 10000d) / 10000d;
		homePassDef[2] = (double) Math.round(homePassDef[2] * 10000d) / 10000d;
		if (homePassDef[2] < .01) {
			homePassDef[0] = .99 - homePassDef[1];
			homePassDef[0] = (double) Math.round(homePassDef[0] * 10000d) / 10000d;
			homePassDef[2] = .01;
		}
		
		homeRushDef[0] -= rushDef;
		homeRushDef[1] += rushDef / 2;
		homeRushDef[2] += rushDef / 2;
		homeRushDef[0] = (double) Math.round(homeRushDef[0] * 10000d) / 10000d;
		homeRushDef[1] = (double) Math.round(homeRushDef[1] * 10000d) / 10000d;
		homeRushDef[2] = (double) Math.round(homeRushDef[2] * 10000d) / 10000d;
		if (homeRushDef[2] < .01) {
			homeRushDef[0] = .99 - homeRushDef[1];
			homeRushDef[0] = (double) Math.round(homeRushDef[0] * 10000d) / 10000d;
			homeRushDef[2] = .01;
		}
	}
}
