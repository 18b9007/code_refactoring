# Video 3: Complex Conditioning Statements and other expressions
Given the class Product.java that is required to run Store.java, which simply returns the total cost, cost per product and savings per product based on the number of Pizzas.
## Product.java
The initial Product class, the bad smell lies in the conditional statement in getTotalCost(). The expression is complicated and can be made more understandable by saving the conditionals into referrable variables i.e. Explaining Variables.
<br>Product.java with bad smell:
```Java
public class Product {

	private String name = "";
	private double price = 0.0;
	private double shippingCost = 0.0;
	private int quantity = 0;
	
	public String getName(){ return name; }
	public double getPrice(){ return price; }
	public double getShippingCost(){ return shippingCost; }
	public int getQuantity(){ return quantity; }
	
	Product(String name, double price, double shippingCost, int quantity){
		
		this.name = name;
		this.price = price;
		this.shippingCost = shippingCost;
		this.quantity = quantity;
	}
	
	public double getTotalCost(){
		
		double quantityDiscount = 0.0;
		
		if((quantity > 50) || ((quantity * price) > 500)) {
			quantityDiscount = .10;
			
		} else if((quantity > 25) || ((quantity * price) > 100)) {
			quantityDiscount = .07;
			
		} else if((quantity >= 10) || ((quantity * price) > 50)) {	
			quantityDiscount = .05;
		}
		
		double discount = ((quantity - 1) * quantityDiscount) * price;
		
		return (quantity * price) + (quantity * shippingCost) - discount;
	}
}
```

Product.java after applying Explaining Variables:


```Java
public class Product {

	private String name = "";
	private double price = 0.0;
	private double shippingCost = 0.0;
	private int quantity = 0;
	
	public String getName(){ return name; }
	public double getPrice(){ return price; }
	public double getShippingCost(){ return shippingCost; }
	public int getQuantity(){ return quantity; }
	
	Product(String name, double price, double shippingCost, int quantity){
		
		this.name = name;
		this.price = price;
		this.shippingCost = shippingCost;
		this.quantity = quantity;
	}
	
	public double getTotalCost(){
		
		double quantityDiscount = 0.0;
		
		final boolean over50Products = (quantity > 50) || ((quantity * price) > 500);
        final boolean over25Products = (quantity > 25) || ((quantity * price) > 100);
        final boolean over10Products = (quantity >= 10) || ((quantity * price) > 50);
        
        if(over50Products) 
        { quantityDiscount = .10; } 
        
        else if(over25Products) 
        { quantityDiscount = .07; } 
        
        else if(over10Products) 
        { quantityDiscount = .05; }
		
		double discount = ((quantity - 1) * quantityDiscount) * price;
		
		return (quantity * price) + (quantity * shippingCost) - discount;
	}
}
```
## Store.java
In the initial Store.java, the print statements in getCostOfProducts() is rather complicated and lengthy with multiple getters being used. Instead, they can be replaced with Explaining Variables that have more descriptive names than using getters. This again makes the code more easily made sense of.
<br>Store.java with bad smell:

```Java
import java.util.ArrayList;

public class Store {
	
	public ArrayList<Product> theProducts = new ArrayList<Product>();
	
	public void addAProduct(Product newProduct){
		theProducts.add(newProduct);
	}
	
	public void getCostOfProducts(){
		
		for(Product product : theProducts){
			
			System.out.println("Total cost for " + product.getQuantity() + " " + product.getName() + "s is $" + product.getTotalCost());
			
			System.out.println("Cost per product " + product.getTotalCost() / product.getQuantity());
			
			System.out.println("Savings per product " + ((product.getPrice() + product.getShippingCost()) - (product.getTotalCost() / product.getQuantity())) + "\n");
		}
	}
	
	public static void main(String[] args){
		
		Store cornerStore = new Store();
		
		cornerStore.addAProduct(new Product("Pizza", 10.00, 1.00, 52));
		
		cornerStore.addAProduct(new Product("Pizza", 10.00, 1.00, 26));
		
		cornerStore.addAProduct(new Product("Pizza", 10.00, 1.00, 10));
		
		cornerStore.getCostOfProducts();
	}
}
```
Store.java after applying Explaining variables:

```Java
import java.util.ArrayList;

public class Store {
	
	public ArrayList<Product> theProducts = new ArrayList<Product>();
	
	public void addAProduct(Product newProduct){
		theProducts.add(newProduct);
	}
	
	public void getCostOfProducts(){
		
		for(Product product : theProducts){
			
			final int numOfProducts = product.getQuantity();  
			final String prodName = product.getName();
			final double cost = product.getTotalCost();
			
			final double costWithDiscount = product.getTotalCost() / product.getQuantity();
			final double costWithoutDiscount = product.getPrice() + product.getShippingCost();
			
			System.out.println("Total cost for " + numOfProducts + " " + prodName + "s is $" + cost);
			
			System.out.println("Cost per product " + costWithDiscount);
			
			System.out.println("Savings per product " + (costWithoutDiscount - costWithDiscount));
			
			System.out.println();
		}
	}
	
	public static void main(String[] args){
		
		Store cornerStore = new Store();
		
		cornerStore.addAProduct(new Product("Pizza", 10.00, 1.00, 52));
		
		cornerStore.addAProduct(new Product("Pizza", 10.00, 1.00, 26));
		
		cornerStore.addAProduct(new Product("Pizza", 10.00, 1.00, 10));
		
		cornerStore.getCostOfProducts();
	}
}
```
## More examples of bad practices: Assigning multiple values to a temp or parameter
By assigning multiple values to the same temp variable, readers of the code will not be able to easily understand what it is used for at first glance, in the long term. The solution hereis also to change them with descriptive variable names.
<br>Example of bad practice:

```Java
double temp = totalCost / numberOfProducts; // temp = Individual product Cost	
temp = temp + shipping; // Individual Product Cost + Shipping	
temp = temp - discount; // (Individual Product Cost + Shipping) - Discount
```
<br>After applying descriptive variable/explaining variable:

```Java
double indivProductCost = totalCost / numberOfProducts; 
prodCostAndShipping = indivProductCost + shipping; 
discountedProductCost = prodCostAndShipping  - discount; 
```

The above practice also applies to parameters that are passed to variables. These parameters should Not be assigned multiple values as shown below, and should be replaced with explaining variables:

```Java
public double getTotPrice(double quantity, double price, double shippingCost, double discount) {
		price = price + shippingCost;
		price = price * quantity;
		return price - discount;
	}
```
