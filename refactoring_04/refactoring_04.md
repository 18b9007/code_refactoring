# Video 4: Extracting Methods, Fields, Classes - Converting primitive/simple types into objects

Given Customer.java, the Customer class is initially rather lengthy and has many responsibilities.
<br>Some of the primitives in the class can be extracted and grouped together to form their own class and object. In this case:
<br>- street, city, state and postalCode can be extracted and grouped to make an Address object,
<br>- birthDay can be extracted to form a Birthday object.
<br>Doing this lessens the responsibility of one class and lessens the overall use of accessor methods (mainly used in the print statements) which can be hard to read.
<br><br>Customer.java before extraction:

```Java
public class Customer {
	private String firstName = "";
	private String lastName = "";
	private String street = "";
	private String city = "";
	private String state ="";
	private int postalCode = 0;
	private String birthDay = "";
	
	public String getFirstName() 
	{ return firstName; }
	public void setFirstName(String firstName) 
	{ this.firstName = firstName; }

	public String getLastName() 
	{ return lastName; }
	public void setLastName(String lastName) 
	{ this.lastName = lastName; }	

	public String getStreet() 
	{ return street; }
	public void setStreet(String street)
	{ this.street = street; }

	public String getCity() 
	{ return city; }
	public void setCity(String city)
	{ this.city = city; }

	public String getState() 
	{ return state; }
	public void setState(String state)
	{ this.state = state; }

	public int getPostalCode() 
	{ return postalCode; }
	public void setPostalCode(int postalCode)
	{ this.postalCode = postalCode; }

	public String getbirthDay() 
	{ return birthDay; }
	public void setBirthday(String birthDay)
	{ this.birthDay = birthDay; }

	public Customer(String firstName, String lastName, String street,
    String city, String state, int postalCode, String birthday) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.birthDay = birthday;
	}
}
```

Customer.java after extraction + print statements:

```Java
public class Customer {
	private String firstName = "";
	private String lastName = "";

    private Address address = null;
    private Birthday birthDay = null;
	
	public String getFirstName() 
	{ return firstName; }
	public void setFirstName(String firstName) 
	{ this.firstName = firstName; }

	public String getLastName() 
	{ return lastName; }
	public void setLastName(String lastName) 
	{ this.lastName = lastName; }	

	public Customer(String firstName, String lastName, Address address, Birthday birthDay) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.birthDay = birthDay;
	}

    public static void main(String[] args){
        
        Address sallySmithAddress = new Address("123 Main St", "Perry", "Iowa", 50220);
        Birthday sallySmithBirthday = new Birthday(12,21,1974);
        Customer sallySmith = new Customer("Sally", "Smith", sallySmithAddress, sallySmithBirthday);
        System.out.println("Customer Name: " + sallySmith.getFirstName() + " " + sallySmith.getLastName());
        System.out.println("Customer Address: " + sallySmith.address);
        System.out.println("Customer Birthday: " + sallySmith.birthDay);
    }
}

class Address{
	private String street = "";
	private String city = "";
	private String state ="";
	private int postalCode = 0;
	
	public String getStreet() 
	{ return street; }
	public void setStreet(String street)
	{ this.street = street; }

	public String getCity() 
	{ return city; }
	public void setCity(String city)
	{ this.city = city; }

	public String getState() 
	{ return state; }
	public void setState(String state)
	{ this.state = state; }

	public int getPostalCode() 
	{ return postalCode; }
	public void setPostalCode(int postalCode)
	{ this.postalCode = postalCode; }
	
	public Address(String street, String city, String state, int postalCode) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
	}
	
	public String toString(){
		return getStreet() + " " + getCity() + " " + getState() + " " + getPostalCode();
	}
}

class Birthday{

    private int day;
    private int month;
    private int year;
        
    public int getDay() 
    { return day; }
    public void setDay(int day) 
    { this.day = day; }

    public int getMonth() 
    { return month; }
    public void setMonth(int month) 
    { this.month = month;}

    public int getYear() 
    { return year; }
    public void setYear(int year)
    { this.year = year; }
    
    public Birthday(int day, int month, int year) {
        super();
        this.day = day;
        this.month = month;
        this.year = year;
    }
    
    public String getBirthDate(){
        return getDay() + " / " + getMonth() + " / " + getYear();
    }
    
    public String toString(){
        return getDay() + " / " + getMonth() + " / " + getYear();
    }
}
```
