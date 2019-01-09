// import statements
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * The driver class for the JavaFootball program
 * @author Zachary Desai (github.com/ZacharyDesai)
 */
public class JavaFootball {
	
	// instance variables
	private JFrame frame;
	private Map<FootballType, List<FootballTeam>> teams;
	private Map<FootballType, FootballTeam[]> teamsArr;
	private FootballTeam selectedAwayTeam;
	private FootballTeam selectedHomeTeam;
	
	// Initiates and opens a new game
	private JavaFootball() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setTitle("JavaFootball");
		frame.setVisible(true);
		
		teams = new HashMap<>();
		teamsArr = new HashMap<>();
		selectedAwayTeam = null;
		selectedHomeTeam = null;
	}

	// Creates the main game frame
	public static void main(String[] args) throws FileNotFoundException {
		JavaFootball game = new JavaFootball();
		for (FootballType type : EnumSet.allOf(FootballType.class)) {
			game.loadTeams(type);
		}
		game.loadRanks();
		game.mainMenu();
	}
	
	// Loads all the teams into the game
	private void loadTeams(FootballType type) throws FileNotFoundException {
		List<FootballTeam> list = new ArrayList<>();
		File file = null;
		switch (type) {
			case NFL:
				file = new File("NFLteams.txt");
				break;
			case FBS:
				file = new File("FBSteams.txt");
				break;
			case FCS:
				file = new File("FCSteams.txt");
				break;
		}
		Scanner scan = new Scanner(file);
		
		FootballConference conference = null;
		FootballDivision division = null;
		while (scan.hasNextLine()) {
			String str = scan.nextLine();
			if (str.indexOf(' ') != -1) {
				String[] strArr = str.split(" ");
				conference = getConference(type, strArr[0]);
				if (conference.hasDivisions()) {
					division = getDivision(conference, strArr[1]);
				}
			}
			else {
				String teamAbbr = str;
				String locationName = scan.nextLine();
				String mascotName = scan.nextLine();
				Color primaryColor = new Color(scan.nextInt(), scan.nextInt(), scan.nextInt());
				Color secondaryColor = new Color(scan.nextInt(), scan.nextInt(), scan.nextInt());
				String logoFilePath = null;
				if (scan.hasNextLine()) {
					scan.nextLine();
				}
				
				switch (type) {
					case NFL:
						logoFilePath = "NFLlogos/" + teamAbbr + ".png";
						list.add(new NFLTeam(teamAbbr, locationName, mascotName, primaryColor, secondaryColor,
								logoFilePath, (NFLConference) conference, (NFLDivision) division));
						break;
					case FBS:
						logoFilePath = "FBSlogos/" + conference.toString() + "/" + teamAbbr + ".png";
						list.add(new FBSTeam(teamAbbr, locationName, mascotName, primaryColor, secondaryColor,
								logoFilePath, (FBSConference) conference, (FBSDivision) division));
						break;
					case FCS:
						logoFilePath = "FCSlogos/" + conference.toString() + "/" + teamAbbr + ".png";
						list.add(new FCSTeam(teamAbbr, locationName, mascotName, primaryColor, secondaryColor,
								logoFilePath, (FCSConference) conference, (FCSDivision) division));
						break;
				}
			}
			if (scan.hasNextLine()) {
				scan.nextLine();
			}
		}
		
		scan.close();
		teams.put(type, list);
		
		FootballTeam[] teamArr = list.toArray(new FootballTeam[0]);
		Arrays.sort(teamArr);
		teamsArr.put(type, teamArr);
	}
	
	// Loads all the team ranks into the game
	private void loadRanks() throws FileNotFoundException {
		File file = new File("NFLranks.txt");
		Scanner scan = new Scanner(file);
		List<FootballTeam> teamList = teams.get(FootballType.NFL);
		Map<String, NFLTeam> teamMap = new HashMap<>();
		for (FootballTeam team : teamList) {
			teamMap.put(team.getTeamAbbr(), (NFLTeam) team);
		}
		scan.nextLine();
		for (int i = 1; i <= teamList.size(); i++) {
			NFLTeam team = teamMap.get(scan.next());
			team.setPassOffRank(i);
		}
		scan.nextLine();
		scan.nextLine();
		scan.nextLine();
		for (int i = 1; i <= teamList.size(); i++) {
			NFLTeam team = teamMap.get(scan.next());
			team.setRushOffRank(i);
		}
		scan.nextLine();
		scan.nextLine();
		scan.nextLine();
		for (int i = 1; i <= teamList.size(); i++) {
			NFLTeam team = teamMap.get(scan.next());
			team.setPassDefRank(i);
		}
		scan.nextLine();
		scan.nextLine();
		scan.nextLine();
		for (int i = 1; i <= teamList.size(); i++) {
			NFLTeam team = teamMap.get(scan.next());
			team.setRushDefRank(i);
		}
		scan.close();
	}
	
	// Gets the conference from its team/league type the given String
	private FootballConference getConference(FootballType type, String str) {
		switch (type) {
			case NFL:
				for (FootballConference conf : EnumSet.allOf(NFLConference.class)) {
					if (conf.toString().equals(str)) {
						return conf;
					}
				}
			case FBS:
				for (FootballConference conf : EnumSet.allOf(FBSConference.class)) {
					if (conf.toString().equals(str)) {
						return conf;
					}
				}
			case FCS:
				for (FootballConference conf : EnumSet.allOf(FCSConference.class)) {
					if (conf.toString().equals(str)) {
						return conf;
					}
				}
		}
		return null;
	}
	
	// Gets the division from its conference and the given String
	private FootballDivision getDivision(FootballConference conf, String str) {
		for (FootballDivision div : conf.getDivisions()) {
			if (div.toString().equals(str)) {
				return div;
			}
		}
		return null;
	}
	
	// Creates the main menu screen
	public void mainMenu() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 100, 0));
		
        JLabel title = new JLabel("JavaFootball", JLabel.CENTER);
        title.setFont(new Font(Font.MONOSPACED, Font.BOLD, 200));
        title.setForeground(Color.WHITE);
        title.setBounds(frame.getWidth() / 10, 0, frame.getWidth() * 4 / 5, frame.getHeight() / 5);
        panel.add(title);
        
        JLabel credits = new JLabel("This project was entirely created by", JLabel.CENTER);
        credits.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        credits.setForeground(Color.WHITE);
        credits.setBounds(0, frame.getHeight() - 200, frame.getWidth(), 75);
        panel.add(credits);
        
        JLabel author = new JLabel("Zachary Desai (github.com/ZacharyDesai).", JLabel.CENTER);
        author.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        author.setForeground(Color.WHITE);
        author.setBounds(0, frame.getHeight() - 125, frame.getWidth(), 75);
        panel.add(author);
        
        JButton instructions = new JButton("How to Play");
        instructions.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 100));
        instructions.setForeground(Color.WHITE);
        instructions.setBackground(Color.BLACK);
        instructions.setBounds(frame.getWidth() / 2 - 375, frame.getHeight() / 4, 750, 125);
        instructions.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		instructions();
            }
        });
        panel.add(instructions);
        
        JButton gameMode = new JButton("Game Mode");
        gameMode.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 100));
        gameMode.setForeground(Color.WHITE);
        gameMode.setBackground(Color.RED);
        gameMode.setBounds(100, frame.getHeight() / 2, 750, 125);
        gameMode.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		gameMode();
            }
        });
        panel.add(gameMode);
        
        JButton seasonMode = new JButton("Season Mode");
        seasonMode.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 100));
        seasonMode.setForeground(Color.WHITE);
        seasonMode.setBackground(Color.BLUE);
        seasonMode.setBounds(frame.getWidth() - 850, frame.getHeight() / 2, 750, 125);
        seasonMode.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		seasonMode();
            }
        });
        panel.add(seasonMode);
        
        frame.setContentPane(panel);
        frame.revalidate();
	}
	
	// Creates the instructions screen
	private void instructions() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 100, 0));
		
        JLabel title = new JLabel("JavaFootball Instructions", JLabel.CENTER);
        title.setFont(new Font(Font.MONOSPACED, Font.BOLD, 75));
        title.setForeground(Color.WHITE);
        title.setBounds(0, 0, frame.getWidth() * 3 / 5, 125);
        panel.add(title);
        
        JButton mainMenu = new JButton("Return to Main Menu");
        mainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        mainMenu.setForeground(Color.WHITE);
        mainMenu.setBackground(Color.BLACK);
        mainMenu.setBounds(frame.getWidth() * 5 / 8, 25, frame.getWidth() * 5 / 16, 75);
        mainMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mainMenu();
            }
        });
        panel.add(mainMenu);
		
		JTextArea text = new JTextArea();
		text.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
		text.setForeground(Color.BLACK);
		text.setBackground(Color.WHITE);
		text.setBounds(0, 125, frame.getWidth(), frame.getHeight() - 125);
		StringBuilder textStr = new StringBuilder();
		textStr.append("     To play or simulate a single game, select \"Game Mode\".\n");
		textStr.append("     To play or simulate a season or mulitple seasons, select \"Season Mode\".\n\n");
		textStr.append("     Gameplay is a model of professional or collegiate American football games, depending on your team/season selection.\n");
		textStr.append("     The game is 60 MINUTES long, divided into two 30-minute halves and four 15-minute quarters.\n\n");
		textStr.append("     Every play takes 1 MINUTE, EXCEPT KICKOFFS, PAT ATTEMPTS, plays PROCEDED by a PENALTY,\n");
		textStr.append("          and plays PRECEDED by a TIMEOUT or a TWO MINUTE WARNING, which each take NO TIME (free play).\n");
		textStr.append("     Each team has 3 timeouts per half, which can be called before any play, offense or defense.\n");
		textStr.append("     A timeout CANNOT be called with 0 MINUTES remaining. It must be called with some time left on the clock.\n");
		textStr.append("     A two minute warning (PROFESSIONAL GAMES ONLY) has the same effect as a timeout and happens with 2 minutes left in the 2nd and 4th quarters.\n\n");
		textStr.append("     To start the game, a COIN TOSS will be executed with the away team calling the toss.\n");
		textStr.append("     Whoever wins the coin toss will choose whether to kickoff to start the 1st or 2nd half, and the other team will kickoff to start the other half.\n\n");
		textStr.append("     For each play, the players on offense and defense each play a GAME CARD.\n");
		textStr.append("     Cards have different effects based on the card played by the other player. All possible effects are listed on each card.\n");
		textStr.append("     Both the offense and defense will always have the same amount of cards and both play one card per turn.\n");
		textStr.append("     Every FIRST DOWN, both sides will always start with 5 CARDS.\n");
		textStr.append("     If a penalty results in a replay of down, both sides will receive an additional card.\n");
		textStr.append("     Unplayed cards are kept by the player throughout the entire half.\n");
		textStr.append("     A player's hand of cards is only reset at HALFTIME or before OVERTIME periods\n\n");
		textStr.append("     The offense, however, also has the option to PUNT or KICK A FIELD GOAL (WHEN WITHIN RANGE),\n");
		textStr.append("          in which case NO OFFENSIVE OR DEFENSIVE CARDS ARE PLAYED.\n");
		textStr.append("     A PUNT, a common option on 4th down (the last down), is useful for field position by giving the ball to your opponent far on their side of the field.\n");
		textStr.append("     A FIELD GOAL can only be attempted on the OPPONENT'S SIDE of the 50 yard line (NOT including the 50), and shorter field goals are more likely to be successful.\n\n");
		textStr.append("     Punts and kickoffs usually result in the receiving team regaining possession,\n");
		textStr.append("     BUT have a slight chance of resulting in a FUMBLE that allows the kicking team to maintain possession.\n");
		textStr.append("     Missed field goals usually just result in a change of possession, but have a chance of being BLOCKED and RETURNED for more yards, or even a touchdown.\n");
		textStr.append("     Punts might also be BLOCKED and RETURNED as well.\n");
		textStr.append("     Punts and kickoffs have a slight chance of either team scoring a touchdown on the return.\n\n");
		textStr.append("     After the offense scores a touchdown, they can either kick an extra point (high chance), or attempt a two-point conversion (half the chance).\n");
		textStr.append("     After any score, the offense will have to kickoff. They can, however, attempt an ONSIDE KICK,\n");
		textStr.append("          which gives them a low chance of maintaining possession at the risk of field position.\n");
		textStr.append("     For simplicity, onside kicks cannot be returned for touchdowns. The ball will remain around midfield, regardless of which team recovers it.\n\n");
		textStr.append("     If the score is tied at the end of the game, OVERTIME takes place.\n\n");
		textStr.append("     For PROFESSIONAL REGULAR SEASON games, a 10-minute overtime period will be played.\n");
		textStr.append("     The overtime period will include a two-minute warning, and both teams will have 3 timeouts.\n");
		textStr.append("     A coin toss will be executed, and the winner will have the option to receive the ball first (common).\n");
		textStr.append("     Overtime ends when one team scores a touchdown OR leads the game after both teams have possessed the ball at least once.\n");
		textStr.append("     If the overtime period ends and neither team is leading, then the game becomes a TIE.\n\n");
		textStr.append("     For PROFESSIONAL PLAYOFF games, the same rules apply, except that the game cannot end in a tie.\n");
		textStr.append("     Instead, a NEW OVERTIME PERIOD will be played with gameplay being reset (like halftime).\n");
		textStr.append("     The team who did not receive the ball in the first overtime will receive it in the second half.\n");
		textStr.append("     Overtime periods will continue to be played until a team wins,\n");
		textStr.append("          and opening possession will continue to alternate between the two teams as so in the first two overtime periods.\n\n");
		textStr.append("     For collegiate overtime games, overtime periods are untimed and do NOT result in ties.\n");
		textStr.append("     A coin toss will be executed, and the winner can elect whether to have possession first or second (commonly second).\n");
		textStr.append("     In an overtime period, BOTH teams will have possession starting at the 25 yardline on their OPPONENT'S SIDE of the field.\n");
		textStr.append("     Whichever team leads at the end of the overtime period wins the game.\n");
		textStr.append("     If the game is still tied, then a NEW OVERTIME PERIOD is played with the team who did not possess the ball first having opening possession.\n");
		textStr.append("     Overtime periods will continue to be played until a team wins,\n");
		textStr.append("          and opening possession will continue to alternate between the two teams as so in the first two overtime periods.\n");
		textStr.append("     However, starting with the THIRD OVERTIME PERIOD, a TWO-POINT CONVERSION must be attempted after each touchdown.\n\n");
		text.setText(textStr.toString());
		text.setCaretPosition(0);
		JScrollPane scrollPane = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 125, frame.getWidth() - 25, frame.getHeight() - 125);
		panel.add(scrollPane);
		
		frame.setContentPane(panel);
		frame.revalidate();
	}

	// Creates the game mode screen
	private void gameMode() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 100, 0));
		
        JLabel title = new JLabel("JavaFootball Game Mode", JLabel.CENTER);
        title.setFont(new Font(Font.MONOSPACED, Font.BOLD, 75));
        title.setForeground(Color.WHITE);
        title.setBounds(0, 0, frame.getWidth() * 3 / 5, 125);
        panel.add(title);
        
        JButton mainMenu = new JButton("Return to Main Menu");
        mainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        mainMenu.setForeground(Color.WHITE);
        mainMenu.setBackground(Color.BLACK);
        mainMenu.setBounds(frame.getWidth() * 5 / 8, 25, frame.getWidth() * 5 / 16, 75);
        mainMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mainMenu();
        		selectedAwayTeam = null;
        		selectedHomeTeam = null;
            }
        });
        panel.add(mainMenu);
        
        JPanel awayPanel = new JPanel();
        awayPanel.setLayout(null);
        awayPanel.setBackground(Color.RED);
        awayPanel.setBounds(0, 125, frame.getWidth() * 3 / 8, frame.getHeight() - 125);
        panel.add(awayPanel);
        
        JPanel homePanel = new JPanel();
        homePanel.setLayout(null);
        homePanel.setBackground(Color.BLUE);
        homePanel.setBounds(frame.getWidth() * 5 / 8, 125, frame.getWidth() * 3 / 8, frame.getHeight() - 125);
        panel.add(homePanel);
        
        JLabel gameOptions = new JLabel("Game Options", JLabel.CENTER);
        gameOptions.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
        gameOptions.setForeground(Color.WHITE);
        gameOptions.setBounds(frame.getWidth() * 3 / 8, 150, frame.getWidth() / 4, 75);
        panel.add(gameOptions);
        
        JButton playerVsComputer = new JButton("Player vs. CPU");
        playerVsComputer.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 35));
        playerVsComputer.setForeground(Color.BLACK);
        playerVsComputer.setBackground(Color.WHITE);
        playerVsComputer.setBounds(frame.getWidth() * 3 / 8 + 25, 225, frame.getWidth() / 4 - 50, 75);
        panel.add(playerVsComputer);
        
        JButton computerVsPlayer = new JButton("CPU vs. Player");
        computerVsPlayer.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 35));
        computerVsPlayer.setForeground(Color.BLACK);
        computerVsPlayer.setBackground(Color.WHITE);
        computerVsPlayer.setBounds(frame.getWidth() * 3 / 8 + 25, 300, frame.getWidth() / 4 - 50, 75);
        panel.add(computerVsPlayer);
        
        JButton playerVsPlayer = new JButton("Player vs. Player");
        playerVsPlayer.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 35));
        playerVsPlayer.setForeground(Color.BLACK);
        playerVsPlayer.setBackground(Color.WHITE);
        playerVsPlayer.setBounds(frame.getWidth() * 3 / 8 + 25, 375, frame.getWidth() / 4 - 50, 75);
        panel.add(playerVsPlayer);
        
        JButton computerVsComputer = new JButton("CPU vs. CPU");
        computerVsComputer.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 35));
        computerVsComputer.setForeground(Color.BLACK);
        computerVsComputer.setBackground(Color.WHITE);
        computerVsComputer.setBounds(frame.getWidth() * 3 / 8 + 25, 450, frame.getWidth() / 4 - 50, 75);
        panel.add(computerVsComputer);
        
        JButton neutralField = new JButton("Neutral Field");
        neutralField.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 35));
        neutralField.setForeground(Color.BLACK);
        neutralField.setBackground(Color.WHITE);
        neutralField.setBounds(frame.getWidth() * 3 / 8 + 25, 550, frame.getWidth() / 4 - 50, 75);
        neutralField.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (neutralField.isSelected()) {
        			neutralField.setSelected(false);
        			neutralField.setBackground(Color.WHITE);
        		}
        		else {
        			neutralField.setSelected(true);
        			neutralField.setBackground(Color.ORANGE);
        		}
            }
        });
        panel.add(neutralField);
        
        JButton playoffOvertimeNFL = new JButton("Playoff Overtime (NFL)");
        playoffOvertimeNFL.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 25));
        playoffOvertimeNFL.setForeground(Color.BLACK);
        playoffOvertimeNFL.setBackground(Color.WHITE);
        playoffOvertimeNFL.setBounds(frame.getWidth() * 3 / 8 + 25, 650, frame.getWidth() / 4 - 50, 50);
        playoffOvertimeNFL.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (playoffOvertimeNFL.isSelected()) {
        			playoffOvertimeNFL.setSelected(false);
        			playoffOvertimeNFL.setBackground(Color.WHITE);
        		}
        		else {
        			playoffOvertimeNFL.setSelected(true);
        			playoffOvertimeNFL.setBackground(Color.ORANGE);
        		}
            }
        });
        panel.add(playoffOvertimeNFL);
        
        JButton startGame = new JButton("Start Game");
        startGame.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
        startGame.setForeground(Color.BLACK);
        startGame.setBackground(Color.WHITE);
        startGame.setBounds(frame.getWidth() * 3 / 8 + 25, frame.getHeight() - 275, frame.getWidth() / 4 - 50, frame.getHeight() - 850);
        startGame.setEnabled(false);
        startGame.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			gameModeGame(playerVsComputer.isSelected(), computerVsPlayer.isSelected(), playerVsPlayer.isSelected(),
            				computerVsComputer.isSelected(), neutralField.isSelected(), playoffOvertimeNFL.isSelected());
        		}
        		catch (FileNotFoundException exception) {
        			
        		}
            }
        });
        panel.add(startGame);
        
        playerVsComputer.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (playerVsComputer.isSelected()) {
        			playerVsComputer.setSelected(false);
        			playerVsComputer.setBackground(Color.WHITE);
        			startGame.setEnabled(false);
        		}
        		else {
        			playerVsComputer.setSelected(true);
        			playerVsComputer.setBackground(Color.ORANGE);
        			computerVsPlayer.setSelected(false);
        			computerVsPlayer.setBackground(Color.WHITE);
        			playerVsPlayer.setSelected(false);
        			playerVsPlayer.setBackground(Color.WHITE);
        			computerVsComputer.setSelected(false);
        			computerVsComputer.setBackground(Color.WHITE);
        			if (selectedAwayTeam != null && selectedHomeTeam != null) {
        				startGame.setEnabled(true);
        			}
        		}
            }
        });
        computerVsPlayer.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (computerVsPlayer.isSelected()) {
        			computerVsPlayer.setSelected(false);
        			computerVsPlayer.setBackground(Color.WHITE);
        			startGame.setEnabled(false);
        		}
        		else {
        			playerVsComputer.setSelected(false);
        			playerVsComputer.setBackground(Color.WHITE);
        			computerVsPlayer.setSelected(true);
        			computerVsPlayer.setBackground(Color.ORANGE);
        			playerVsPlayer.setSelected(false);
        			playerVsPlayer.setBackground(Color.WHITE);
        			computerVsComputer.setSelected(false);
        			computerVsComputer.setBackground(Color.WHITE);
        			if (selectedAwayTeam != null && selectedHomeTeam != null) {
        				startGame.setEnabled(true);
        			}
        		}
            }
        });
        playerVsPlayer.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (playerVsPlayer.isSelected()) {
        			playerVsPlayer.setSelected(false);
        			playerVsPlayer.setBackground(Color.WHITE);
        			startGame.setEnabled(false);
        		}
        		else {
        			playerVsComputer.setSelected(false);
        			playerVsComputer.setBackground(Color.WHITE);
        			computerVsPlayer.setSelected(false);
        			computerVsPlayer.setBackground(Color.WHITE);
        			playerVsPlayer.setSelected(true);
        			playerVsPlayer.setBackground(Color.ORANGE);
        			computerVsComputer.setSelected(false);
        			computerVsComputer.setBackground(Color.WHITE);
        			if (selectedAwayTeam != null && selectedHomeTeam != null) {
        				startGame.setEnabled(true);
        			}
        		}
            }
        });
        computerVsComputer.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (computerVsComputer.isSelected()) {
        			computerVsComputer.setSelected(false);
        			computerVsComputer.setBackground(Color.WHITE);
        			startGame.setEnabled(false);
        		}
        		else {
        			playerVsComputer.setSelected(false);
        			playerVsComputer.setBackground(Color.WHITE);
        			computerVsPlayer.setSelected(false);
        			computerVsPlayer.setBackground(Color.WHITE);
        			playerVsPlayer.setSelected(false);
        			playerVsPlayer.setBackground(Color.WHITE);
        			computerVsComputer.setSelected(true);
        			computerVsComputer.setBackground(Color.ORANGE);
        			if (selectedAwayTeam != null && selectedHomeTeam != null) {
        				startGame.setEnabled(true);
        			}
        		}
            }
        });
        
        JLabel awayTeam = new JLabel("Away Team", JLabel.CENTER);
        awayTeam.setFont(new Font(Font.MONOSPACED, Font.BOLD, 75));
        awayTeam.setForeground(Color.WHITE);
        awayTeam.setBounds(0, 0, awayPanel.getWidth(), 100);
        awayPanel.add(awayTeam);
        
        JLabel homeTeam = new JLabel("Home Team", JLabel.CENTER);
        homeTeam.setFont(new Font(Font.MONOSPACED, Font.BOLD, 75));
        homeTeam.setForeground(Color.WHITE);
        homeTeam.setBounds(0, 0, homePanel.getWidth(), 100);
        homePanel.add(homeTeam);
        
        JButton awayNFL = new JButton("NFL");
        awayNFL.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        awayNFL.setForeground(Color.BLACK);
        awayNFL.setBackground(Color.ORANGE);
        awayNFL.setBounds(awayPanel.getWidth() / 13, 125, awayPanel.getWidth() / 4, 75);
        awayNFL.setSelected(true);
        awayPanel.add(awayNFL);
        
        JButton awayFBS = new JButton("FBS");
        awayFBS.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        awayFBS.setForeground(Color.BLACK);
        awayFBS.setBackground(Color.WHITE);
        awayFBS.setBounds(awayPanel.getWidth() * 5 / 13, 125, awayPanel.getWidth() / 4, 75);
        awayPanel.add(awayFBS);
        
        JButton awayFCS = new JButton("FCS");
        awayFCS.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        awayFCS.setForeground(Color.BLACK);
        awayFCS.setBackground(Color.WHITE);
        awayFCS.setBounds(awayPanel.getWidth() * 9 / 13, 125, awayPanel.getWidth() / 4, 75);
        awayPanel.add(awayFCS);
        
        JButton homeNFL = new JButton("NFL");
        homeNFL.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        homeNFL.setForeground(Color.BLACK);
        homeNFL.setBackground(Color.ORANGE);
        homeNFL.setBounds(homePanel.getWidth() / 13, 125, homePanel.getWidth() / 4, 75);
        awayNFL.setSelected(true);
        homePanel.add(homeNFL);
        
        JButton homeFBS = new JButton("FBS");
        homeFBS.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        homeFBS.setForeground(Color.BLACK);
        homeFBS.setBackground(Color.WHITE);
        homeFBS.setBounds(homePanel.getWidth() * 5 / 13, 125, homePanel.getWidth() / 4, 75);
        homePanel.add(homeFBS);
        
        JButton homeFCS = new JButton("FCS");
        homeFCS.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        homeFCS.setForeground(Color.BLACK);
        homeFCS.setBackground(Color.WHITE);
        homeFCS.setBounds(homePanel.getWidth() * 9 / 13, 125, homePanel.getWidth() / 4, 75);
        homePanel.add(homeFCS);
        
        JList<FootballTeam> awayTeams = new JList<>(teamsArr.get(FootballType.NFL));
        awayTeams.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        awayTeams.setLayoutOrientation(JList.VERTICAL);
        awayTeams.setVisibleRowCount(-1);
        awayTeams.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 29));
        JScrollPane awayScrollPane = new JScrollPane(awayTeams);
        awayScrollPane.setBounds(25, 225, homePanel.getWidth() - 50, homePanel.getHeight() - 500);
        awayPanel.add(awayScrollPane);
        
        JList<FootballTeam> homeTeams = new JList<>(teamsArr.get(FootballType.NFL));
        homeTeams.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        homeTeams.setLayoutOrientation(JList.VERTICAL);
        homeTeams.setVisibleRowCount(-1);
        homeTeams.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 29));
        JScrollPane homeScrollPane = new JScrollPane(homeTeams);
        homeScrollPane.setBounds(25, 225, homePanel.getWidth() - 50, homePanel.getHeight() - 500);
        homePanel.add(homeScrollPane);
        
        JButton awayPrimaryColor = new JButton();
        awayPrimaryColor.setBounds(50, awayPanel.getHeight() - 250, awayPanel.getWidth() - 370, 90);
        awayPrimaryColor.setEnabled(false);
        awayPrimaryColor.setVisible(false);
        awayPanel.add(awayPrimaryColor);
        
        JButton awaySecondaryColor = new JButton();
        awaySecondaryColor.setBounds(50, awayPanel.getHeight() - 160, awayPanel.getWidth() - 370, 90);
        awaySecondaryColor.setEnabled(false);
        awaySecondaryColor.setVisible(false);
        awayPanel.add(awaySecondaryColor);
        
        JLabel awayTeamLogo = new JLabel();
        awayTeamLogo.setBounds(awayPanel.getWidth() - 320, awayPanel.getHeight() - 250, 270, 180);
        awayTeamLogo.setVisible(false);
        awayPanel.add(awayTeamLogo);
        
        JButton homePrimaryColor = new JButton();
        homePrimaryColor.setBounds(320, homePanel.getHeight() - 250, homePanel.getWidth() - 370, 90);
        homePrimaryColor.setEnabled(false);
        homePrimaryColor.setVisible(false);
        homePanel.add(homePrimaryColor);
        
        JButton homeSecondaryColor = new JButton();
        homeSecondaryColor.setBounds(320, homePanel.getHeight() - 160, homePanel.getWidth() - 370, 90);
        homeSecondaryColor.setEnabled(false);
        homeSecondaryColor.setVisible(false);
        homePanel.add(homeSecondaryColor);
        
        JLabel homeTeamLogo = new JLabel();
        homeTeamLogo.setBounds(50, homePanel.getHeight() - 250, 270, 180);
        homeTeamLogo.setVisible(false);
        homePanel.add(homeTeamLogo);
        
        awayNFL.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!awayNFL.isSelected()) {
        			awayNFL.setSelected(true);
        			awayNFL.setBackground(Color.ORANGE);
        			awayFBS.setSelected(false);
        			awayFBS.setBackground(Color.WHITE);
        			awayFCS.setSelected(false);
        			awayFCS.setBackground(Color.WHITE);
        			awayTeams.setListData(teamsArr.get(FootballType.NFL));
        			awayTeams.ensureIndexIsVisible(0);
        			selectedAwayTeam = null;
        			awayTeamLogo.setVisible(false);
        			awayPrimaryColor.setVisible(false);
        			awaySecondaryColor.setVisible(false);
        		}
            }
        });
        awayFBS.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!awayFBS.isSelected()) {
        			awayNFL.setSelected(false);
        			awayNFL.setBackground(Color.WHITE);
        			awayFBS.setSelected(true);
        			awayFBS.setBackground(Color.ORANGE);
        			awayFCS.setSelected(false);
        			awayFCS.setBackground(Color.WHITE);
        			awayTeams.setListData(teamsArr.get(FootballType.FBS));
        			awayTeams.ensureIndexIsVisible(0);
        			selectedAwayTeam = null;
        			awayTeamLogo.setVisible(false);
        			awayPrimaryColor.setVisible(false);
        			awaySecondaryColor.setVisible(false);
        		}
            }
        });
        awayFCS.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!awayFCS.isSelected()) {
        			awayNFL.setSelected(false);
        			awayNFL.setBackground(Color.WHITE);
        			awayFBS.setSelected(false);
        			awayFBS.setBackground(Color.WHITE);
        			awayFCS.setSelected(true);
        			awayFCS.setBackground(Color.ORANGE);
        			awayTeams.setListData(teamsArr.get(FootballType.FCS));
        			awayTeams.ensureIndexIsVisible(0);
        			selectedAwayTeam = null;
        			awayTeamLogo.setVisible(false);
        			awayPrimaryColor.setVisible(false);
        			awaySecondaryColor.setVisible(false);
        		}
            }
        });
        homeNFL.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!homeNFL.isSelected()) {
        			homeNFL.setSelected(true);
        			homeNFL.setBackground(Color.ORANGE);
        			homeFBS.setSelected(false);
        			homeFBS.setBackground(Color.WHITE);
        			homeFCS.setSelected(false);
        			homeFCS.setBackground(Color.WHITE);
        			homeTeams.setListData(teamsArr.get(FootballType.NFL));
        			homeTeams.ensureIndexIsVisible(0);
        			selectedHomeTeam = null;
        			homeTeamLogo.setVisible(false);
        			homePrimaryColor.setVisible(false);
        			homeSecondaryColor.setVisible(false);
        		}
            }
        });
        homeFBS.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!homeFBS.isSelected()) {
        			homeNFL.setSelected(false);
        			homeNFL.setBackground(Color.WHITE);
        			homeFBS.setSelected(true);
        			homeFBS.setBackground(Color.ORANGE);
        			homeFCS.setSelected(false);
        			homeFCS.setBackground(Color.WHITE);
        			homeTeams.setListData(teamsArr.get(FootballType.FBS));
        			homeTeams.ensureIndexIsVisible(0);
        			selectedHomeTeam = null;
        			homeTeamLogo.setVisible(false);
        			homePrimaryColor.setVisible(false);
        			homeSecondaryColor.setVisible(false);
        		}
            }
        });
        homeFCS.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!homeFCS.isSelected()) {
        			homeNFL.setSelected(false);
        			homeNFL.setBackground(Color.WHITE);
        			homeFBS.setSelected(false);
        			homeFBS.setBackground(Color.WHITE);
        			homeFCS.setSelected(true);
        			homeFCS.setBackground(Color.ORANGE);
        			homeTeams.setListData(teamsArr.get(FootballType.FCS));
        			homeTeams.ensureIndexIsVisible(0);
        			selectedHomeTeam = null;
        			homeTeamLogo.setVisible(false);
        			homePrimaryColor.setVisible(false);
        			homeSecondaryColor.setVisible(false);
        		}
            }
        });
        
        awayTeams.addListSelectionListener(new ListSelectionListener() {
        	public void valueChanged(ListSelectionEvent e) {
        		selectedAwayTeam = awayTeams.getSelectedValue();
        		if (selectedAwayTeam != null) {
            		awayTeamLogo.setIcon(new ImageIcon(selectedAwayTeam.getLogo().getImage().getScaledInstance(270, 180, Image.SCALE_DEFAULT)));
            		awayTeamLogo.setVisible(true);
            		awayPrimaryColor.setBackground(selectedAwayTeam.getPrimaryColor());
            		awayPrimaryColor.setVisible(true);
            		awaySecondaryColor.setBackground(selectedAwayTeam.getSecondaryColor());
            		awaySecondaryColor.setVisible(true);
            		if (selectedHomeTeam != null && (playerVsComputer.isSelected() || computerVsPlayer.isSelected() ||
            				playerVsPlayer.isSelected() || computerVsComputer.isSelected())) {
            			startGame.setEnabled(true);
            		}
        		}
        		else {
        			startGame.setEnabled(false);
        		}
        	}
        });
        homeTeams.addListSelectionListener(new ListSelectionListener() {
        	public void valueChanged(ListSelectionEvent e) {
        		selectedHomeTeam = homeTeams.getSelectedValue();
        		if (selectedHomeTeam != null) {
            		homeTeamLogo.setIcon(new ImageIcon(selectedHomeTeam.getLogo().getImage().getScaledInstance(270, 180, Image.SCALE_DEFAULT)));
        			homeTeamLogo.setVisible(true);
            		homePrimaryColor.setBackground(selectedHomeTeam.getPrimaryColor());
            		homePrimaryColor.setVisible(true);
            		homeSecondaryColor.setBackground(selectedHomeTeam.getSecondaryColor());
            		homeSecondaryColor.setVisible(true);
            		if (selectedAwayTeam != null && (playerVsComputer.isSelected() || computerVsPlayer.isSelected() ||
            				playerVsPlayer.isSelected() || computerVsComputer.isSelected())) {
            			startGame.setEnabled(true);
            		}
        		}
        		else {
        			startGame.setEnabled(false);
        		}
        	}
        });
		
		frame.setContentPane(panel);
		frame.revalidate();
	}
	
	// Creates the season mode screen
	private void seasonMode() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 100, 0));
		
        JLabel title = new JLabel("JavaFootball Season Mode", JLabel.CENTER);
        title.setFont(new Font(Font.MONOSPACED, Font.BOLD, 75));
        title.setForeground(Color.WHITE);
        title.setBounds(0, 0, frame.getWidth() * 3 / 5, 125);
        panel.add(title);
        
        JButton mainMenu = new JButton("Return to Main Menu");
        mainMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        mainMenu.setForeground(Color.WHITE);
        mainMenu.setBackground(Color.BLACK);
        mainMenu.setBounds(frame.getWidth() * 5 / 8, 25, frame.getWidth() * 5 / 16, 75);
        mainMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mainMenu();
            }
        });
        panel.add(mainMenu);
		
		frame.setContentPane(panel);
		frame.revalidate();
	}
	
	// Plays or simulates a game
	private void gameModeGame(boolean playerVsComputer, boolean computerVsPlayer, boolean playerVsPlayer,
			boolean computerVsComputer, boolean neutralField, boolean playoffOvertimeNFL) throws FileNotFoundException {
		JavaFootballGame game = new JavaFootballGame(selectedAwayTeam, selectedHomeTeam,
				playerVsComputer, computerVsPlayer, playerVsPlayer, computerVsComputer, neutralField, this, !playoffOvertimeNFL);
		game.runGame(frame);
	}
}
