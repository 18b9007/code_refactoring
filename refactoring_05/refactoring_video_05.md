# Video 5: ...
Create Athelete class with Gold, Silver, Brinze subclasses for winners
Once an Athelete receives  medal, no other Athletes can be assigned to that medal subclass
No conditional statements allowed
Replace constructors with Factory

Given initial Customer2.java class:

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
		Customer2 goodCustomer = customerFactory.getCustomer(PREMIER);
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
	
	public Customer2 getCustomer(int custType){
		
		switch (custType)
        {
            case 2:
            return new Premier();
            case 1:
			return new Valued();
            case 0:
            return new Deadbeat();
            default:
			throw new IllegalArgumentException("Invalid Customer Type");
		}
	}
}
```

Given by the challenge that no conditionals should be used, but switches were used. So solution to that below version:

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

Make Athlete.java based on the challenge given:

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

// Silver and Bronze subclasses is the exact same as Gold
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

// Next is medal factory to spit out the right subclass based on the subclass asked for
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