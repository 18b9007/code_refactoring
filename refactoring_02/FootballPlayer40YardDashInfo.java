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