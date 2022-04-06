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