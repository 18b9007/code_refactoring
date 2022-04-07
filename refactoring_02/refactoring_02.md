# Video 2: Extract Methods, Working with Local Variables and Temps

## Extracting a method
Given a FootballPlayer class:
```Java
public class FootballPlayer{

    private String name = "";
    private double[] fortyYardDashTimes = null;
    public String getName(){return name;}
    public double[] get40YardDashTimes(){return fortyYardDashTimes;}

    FootballPlayer(String name, double[] fortyYardDashTimes){
        this.name = name;
        this.fortyYardDashTimes = fortyYardDashTimes;
    }    
}
```

And given the below class that runs with the help of above class. Produces football player name and their average 40 yard dash time
```Java
import java.util.ArrayList;

public class FootballPlayer40YardDashInfo {
	
	ArrayList<FootballPlayer> players = new ArrayList<FootballPlayer>();
	
	public void addFootballPlayer(FootballPlayer player){
		
		players.add(player);
		
	}

	public void printPlayerInfo(){
		
		double avg40YdTime = 0.0;
		System.out.printf("%-15s %15s", "Name", "Avg 40 Time\n");
		
		// Print dashes under titles
		for(int i = 0; i < 30; i++){ System.out.print("_"); }
		System.out.println();
		
	
		for(FootballPlayer player : players){
		
			System.out.printf("%-19s", player.getName());
			
			double total40YdDashTimes = 0.0;
			double[] fortyYardDashTimes = player.get40YardDashTimes();
			
			for(int i = 0; i < player.get40YardDashTimes().length; i++){
				
				total40YdDashTimes += fortyYardDashTimes[i];
				
			}

			avg40YdTime = total40YdDashTimes / player.get40YardDashTimes().length;
			System.out.printf("%1$.2f", avg40YdTime);
			System.out.println();
		}
	} 
	
	public static void main(String[] args){
		
	FootballPlayer40YardDashInfo fb40Dash = new FootballPlayer40YardDashInfo();
		
	// Add Clark Kent for example
	double cKent40YdDashTimes[] = {4.36, 4.39, 4.41};
	FootballPlayer clarkKent = new FootballPlayer("Clark Kent", cKent40YdDashTimes);
	fb40Dash.addFootballPlayer(clarkKent);
		
	// Add Bruce Wayne for example
	double bWayne40YdDashTimes[] = {4.42, 4.43, 4.49};
	FootballPlayer bruceWayne = new FootballPlayer("Bruce Wayne", bWayne40YdDashTimes);
	fb40Dash.addFootballPlayer(bruceWayne);
		
	fb40Dash.printPlayerInfo();
	}
}
```

The long printPlayerInfo method can extracted into separate smaller methods (printTitles, printCharMultiTimes and printPlayersWith40Avg), resulting in a cleaner and more manageable printPlayerInfo.
```Java
import java.util.ArrayList;

public class FootballPlayer40YardDashInfo {
	
	ArrayList<FootballPlayer> players = new ArrayList<FootballPlayer>();
	
	public void addFootballPlayer(FootballPlayer player){
		players.add(player);
	}

	public void printPlayerInfo(){
		printTitles();
		printPlayersWith40Avg();
	} 

	public void printTitles(){  // extracted into function
		System.out.printf("%-15s %15s", "Name", "Avg 40 Time\n");
		printCharMultiTimes('_', 30);
	}

	public void printCharMultiTimes(char charToPrint, int howManyTimes){
		// Print dashes under titles
		for(int i = 0; i < howManyTimes; i++){ System.out.print(charToPrint); }
		System.out.println();
	}

	public void printPlayersWith40Avg(){
		for(FootballPlayer player : players){
			System.out.printf("%-19s", player.getName());
			double total40YdDashTimes = 0.0;
			double[] fortyYardDashTimes = player.get40YardDashTimes();
			
			for(int i = 0; i < player.get40YardDashTimes().length; i++)
			{ total40YdDashTimes += fortyYardDashTimes[i]; }

			double avg40YdTime = total40YdDashTimes / player.get40YardDashTimes().length;
			System.out.printf("%1$.2f", avg40YdTime);
			System.out.println();
		}
	}
	
	public static void main(String[] args){
		FootballPlayer40YardDashInfo fb40Dash = new FootballPlayer40YardDashInfo();
		
		// Add Clark Kent for example
		double cKent40YdDashTimes[] = {4.36, 4.39, 4.41};
		FootballPlayer clarkKent = new FootballPlayer("Clark Kent", cKent40YdDashTimes);
		fb40Dash.addFootballPlayer(clarkKent);
		
		// Add Bruce Wayne for example
		double bWayne40YdDashTimes[] = {4.42, 4.43, 4.49};
		FootballPlayer bruceWayne = new FootballPlayer("Bruce Wayne", bWayne40YdDashTimes);
		fb40Dash.addFootballPlayer(bruceWayne);
		
		fb40Dash.printPlayerInfo();
	}
}
```

If the code is already as clear as a method, then there is no need to extract it.

## Working with local variables
Given a 7-line code which uses local variable:
```Java
double average 0.0;
double[] dashTimes = {4.36, 4.39, 4.41};
double totalDashTimes = 0.0;

for(int i = 0; i < dashTimes.length; i++){
	totalDashTimes += dashTimes[i];
}
average = totalDashTimes / numOfTimes.length;
```

By extracting into a separate function, it shortens the 7-liner to just 2 lines, and a new separate function that is easily understandable:

```Java
double[] dashTimes = {4.36, 4.39, 4.41};
double average = getAvgDashTime(dashTimes);

public static double getAvgDashTime(double[] dashTimes){
	double totalDashTimes = 0.0;
	for(int i = 0; i < dashTimes.length; i++)
	{ totalDashTimes += dashTimes[i]; }
	return totalDashTimes / dashTimes.length;
	}
```

## Working with Temps
A temp should be gotten rid of if it's only used once and doesn't add to understanding of the code, and if it's just there to hold the value to an expression.
<br>Solution: replace Temp with a Query. Given below code and the temp is avgDashTime:
```Java
double avgDashTime = totalDashTimes / totalDashes;

if(avgDashTime > 4.41) {
	System.out.println("Average Time");
}
```
<br>The better approach is as such:

```Java
// Better Solution
if(avgDashTime() > 4.41) {
	System.out.println("Average Time");
}

double avgDashTime(){
	return totalDashTimes / totalDashes;
}
```
