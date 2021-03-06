# Video 5: Factory Method to replace constructors
Customer2.java and Athlete.java demosntrates the use of factory methods to replace constructors.
## Customer2.java
```Java
public abstract class Customer2 {
	
	private String custRating;
	static final int PREMIER = 2;
	static final int VALUED = 1;
	static final int DEADBEAT = 0;
	
	public String getCustRating(){ return custRating; }
	
	public void setCustRating(String custRating) { this.custRating = custRating; }

    public static void main(String[] args){
		
		CustomerFactory customerFactory  = new CustomerFactory();
		Customer2 goodCustomer = customerFactory.getCustomer("Premier");
		System.out.println("Customer Rating: " + goodCustomer.getCustRating());
    }
}

class Premier extends Customer2{
	Premier(){ setCustRating("Premier Customer"); }	
}

class Valued extends Customer2{
	Valued(){ setCustRating("Valued Customer"); }	
}

class Deadbeat extends Customer2{
	Deadbeat(){ setCustRating("Deadbeat Customer"); }	
}

class CustomerFactory{
	
	public Customer2 getCustomer(String custName){
		try {
			return (Customer2) Class.forName(custName).getDeclaredConstructor().newInstance();
		}
		
		catch(Exception e){
			throw new IllegalArgumentException("Invalid Customer Type");
		}
	}
}
```
## Athlete.java
Athelete.java was created under the below conditions:
<br>- Athlete class, Medal subclasses (Gold, Silver, Bronze) and Winner subclass
<br>- Once an Athelete receives a medal, no other Athletes can be assigned to that medal subclass
<br>- No conditional statements are to be used (e.g. switches)
<br><br>Athlete.java:
```Java
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
		
		Athlete goldWinnerNew = medalFactory.getMedal("GoldWinner", "Ray Kroc");
		
		System.out.println("GoldWinner: " + goldWinner.getAthleteName());
		System.out.println("SilverWinner: " + silverWinner.getAthleteName());
		System.out.println("BronzeWinner: " + bronzeWinner.getAthleteName());		
	}	
}
```
