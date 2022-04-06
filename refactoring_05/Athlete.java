import java.lang.reflect.Method;

// Athelete class and medal subclasses

public class Athlete {
	
	private String athleteName = "";
	
	public String getAthleteName() 
    { return athleteName; }

	public void setAthleteName(String athleteName){ this.athleteName = athleteName; }
	
	public static Athlete getInstance()
    { return null; }
}

class GoldWinner extends Athlete{
	private static GoldWinner goldAthlete = null;
	
	private GoldWinner(String athleteName)
    { setAthleteName(athleteName); }
	
	public static GoldWinner getInstance(String athleteName){

		if(goldAthlete == null)
        { goldAthlete = new GoldWinner(athleteName); } 
		
		return goldAthlete;
	}
}

class SilverWinner extends Athlete{
	private static SilverWinner silverAthlete = null;
	
	private SilverWinner(String athleteName)
    { setAthleteName(athleteName); }
	
	public static SilverWinner getInstance(String athleteName){
		
		if(silverAthlete == null)
        { silverAthlete = new SilverWinner(athleteName); } 
		
		return silverAthlete;
	}	
}

class BronzeWinner extends Athlete{
	private static BronzeWinner bronzeAthlete = null;
	
	private BronzeWinner(String athleteName)
    { setAthleteName(athleteName); }
	
	public static BronzeWinner getInstance(String athleteName){
		
		if(bronzeAthlete == null)
        { bronzeAthlete = new BronzeWinner(athleteName); } 
		
		return bronzeAthlete;
	}
}

class MedalFactory{
	
	public Athlete getMedal(String medalType, String athleteName){
		
		try {

			Class[] athleteNameParameter = new Class[]{String.class};

			Method getInstanceMethod =  Class.forName(medalType).getMethod("getInstance", athleteNameParameter);
			
			Object[] params = new Object[]{new String(athleteName)};

			return (Athlete) getInstanceMethod.invoke(null, params);
		}
		
		catch(Exception e){
			throw new IllegalArgumentException("Invalid Medal Type");
		}	
	}	
}

class TestMedalWinner{
	
	public static void main(String[] args){
		
		MedalFactory medalFactory = new MedalFactory();
		
		Athlete goldWinner = medalFactory.getMedal("GoldWinner", "Dave Thomas");
		Athlete silverWinner = medalFactory.getMedal("SilverWinner", "Mac McDonald");
		Athlete bronzeWinner = medalFactory.getMedal("BronzeWinner", "David Edgerton");
		
		// Athlete goldWinnerNew = medalFactory.getMedal("GoldWinner", "Ray Kroc");
		
		System.out.println("GoldWinner: " + goldWinner.getAthleteName());
		System.out.println("SilverWinner: " + silverWinner.getAthleteName());
		System.out.println("BronzeWinner: " + bronzeWinner.getAthleteName());		
	}	
}
