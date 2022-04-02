# Creation Method to replace using multiple Constructors
<br>Using numerous constructors can cause problems. In the below example, the last 2 constructors cause a problem where they both have the same attribute signatures such (both are receiving an int).

```Java
public class FootballPlayer {
	
	private double passerRating; // Specific to QBs
	private int rushingYards; // Specific to RBs & QBs
	private int receivingYards; // Specific to RBs & WRs 
	private int totalTackles; // Specific to DEF
	private int interceptions; // Specific to DEF
	private int fieldGoals; // Specific to Kickers
	private double avgPunt; // Specific to Punters
	private double avgKickoffReturn; // Specific to Special Teams
	private double avgPuntReturn; // Specific to Special Teams

	
	Old way of creating numerous constructors
	for the specific roles - there will be problems

	FootballPlayer(double passerRating, int rushingYards){
		
		this.passerRating = passerRating;
		this.rushingYards = rushingYards;	
	}

	FootballPlayer(int rushingYards){
		this.rushingYards = rushingYards;
	}

	FootballPlayer(int receivingYards){
		this.receivingYards = receivingYards;
	}
}
```
Solution: Creation Method
<br>Solution: Instead of creating multiple constructors for different types of players, a single catch all constructor is created with all the player properties.
<br>Then when creating a specific type of plater (e.g. quarterback QB), only values for the relevant arguments are filled with the rest being 0.

```Java
public class FootballPlayer {
	
	private double passerRating; // Specific to QBs
	private int rushingYards; // Specific to RBs & QBs
	private int receivingYards; // Specific to RBs & WRs 
	private int totalTackles; // Specific to DEF
	private int interceptions; // Specific to DEF
	private int fieldGoals; // Specific to Kickers
	private double avgPunt; // Specific to Punters
	private double avgKickoffReturn; // Specific to Special Teams
	private double avgPuntReturn; // Specific to Special Teams


	private FootballPlayer(double passerRating, int rushingYards,
			int receivingYards, int totalTackles, int interceptions,
			int fieldGoals, double avgPunt, double avgKickoffReturn,
			double avgPuntReturn){
		
		this.passerRating = passerRating;
		this.rushingYards = rushingYards;
		this.receivingYards = receivingYards;
		this.totalTackles = totalTackles;
		this.interceptions = interceptions;
		this.fieldGoals = fieldGoals;
		this.avgPunt = avgPunt;
		this.avgKickoffReturn = avgKickoffReturn;
		this.avgPuntReturn = avgPuntReturn;
		
	}

	public double getPasserRating() { return passerRating; }

	// Creation Method
	public static FootballPlayer createQB(double passerRating, int rushingYards){
		return new FootballPlayer(passerRating, rushingYards, 0, 0, 0, 0, 0.0, 0.0, 0.0); 
	}

	public static void main(String[] args){
		FootballPlayer aaronRodgers = FootballPlayer.createQB(108.0, 259);
		System.out.println("Aaron Rodgers Passer Rating: " + aaronRodgers.getPasserRating());

		// Output:   Aaron Rodgers Passer Rating: 108.0	
	}
}
```

# Chain Constructors
Note that the more constructors we have over time, the more likely it is to cause problem or inconvenience. E.g. Someone may update one constructor and forget to update another.
<br>Below is an example of a problematic approach (Mutliple constructors created with just slight differences between then)

```Java
public class FootballPlayer2 {
	
	private String playerName ="";
	private String college = "";
	private double fortyYardDash = 0.0;
	private int repsBenchPress = 0;
	private double sixtyYardDash = 0.0;
	
	public String getPlayerName() { return playerName; }
	public String getCollege() { return college; }
	public double get40YdDash() { return fortyYardDash; }
	public int getRepsBenchPress() { return repsBenchPress; }
	public double get60YdDash() { return sixtyYardDash; }


    public FootballPlayer2(String playerName, String college, double fortyYardDash, double sixtyYardDash){
        this.playerName = playerName;
        this.college = college;
        this.fortyYardDash = fortyYardDash;
        this.sixtyYardDash = sixtyYardDash;
    }

 
    public FootballPlayer2(String playerName, String college, 
			double fortyYardDash, int repsBenchPress){
		
		this.playerName = playerName;
		this.college = college;
		this.fortyYardDash = fortyYardDash;
		this.repsBenchPress = repsBenchPress;
		
	}

    public FootballPlayer2(String playerName, String college, 
			double fortyYardDash, int repsBenchPress, double sixtyYardDash){
		
		this.playerName = playerName;
		this.college = college;
		this.fortyYardDash = fortyYardDash;
		this.repsBenchPress = repsBenchPress;
		this.sixtyYardDash = sixtyYardDash;
		
	}
}
```

The solution to eliminate these repetitive constructors is to Chain them. Creating a general purpose instructor for all the general/recurring football player properties. This results in less constructors overall to work with.

```Java
public class FootballPlayer2 {
	
	private String playerName ="";
	private String college = "";
	private double fortyYardDash = 0.0;
	private int repsBenchPress = 0;
	private double sixtyYardDash = 0.0;
	
	public String getPlayerName() { return playerName; }
	public String getCollege() { return college; }
	public double get40YdDash() { return fortyYardDash; }
	public int getRepsBenchPress() { return repsBenchPress; }
	public double get60YdDash() { return sixtyYardDash; }


    public FootballPlayer2(String playerName, String college, 
			double fortyYardDash, int repsBenchPress, double sixtyYardDash){
		
		this.playerName = playerName;
		this.college = college;
		this.fortyYardDash = fortyYardDash;
		this.repsBenchPress = repsBenchPress;
		this.sixtyYardDash = sixtyYardDash;
		
	}

    public FootballPlayer2(String playerName, String college, 
			double fortyYardDash, int repsBenchPress){
		this(playerName, college, fortyYardDash, repsBenchPress, 0.0);
	}
	
	public FootballPlayer2(String playerName, String college, 
			double fortyYardDash, double sixtyYardDash){
		this(playerName, college, fortyYardDash, 0, sixtyYardDash);
		
	}
	
	public static void main(String[] args){
		
		FootballPlayer2 jamellFleming = new FootballPlayer2("Jamell Fleming", "Oklahoma", 4.53, 10.75);
		
		System.out.println(jamellFleming.getPlayerName());
		System.out.println(jamellFleming.getCollege());
		System.out.println(jamellFleming.get40YdDash());
		System.out.println(jamellFleming.getRepsBenchPress());
		System.out.println(jamellFleming.get60YdDash());
		
	}
}
```