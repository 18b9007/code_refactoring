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