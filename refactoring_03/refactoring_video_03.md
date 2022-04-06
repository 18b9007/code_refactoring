# Video 3: ...

Given two classes, Product.java and given a Store.java that executes with the help of Product
<br>Product.java initial version

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

Store.java initial version
<br>This class Simply shows the different Total cost, cost per product and savings per product based on the number of Pizzas.

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

The whole if else block in getTotalCost is a bad code. What we can do with it
remove the chunk
Use Explaining Variables - used whenever we have complicated expressions, make them make more sense by SAVING the conditionals into a referrable variables. Store.java will run and still give same results

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

Now for Store.java, the getCostofProducts print statements are pretty long and complicated with all the getters
Can also use Explaining variables here, for complicated calculation. It still runs normally after change

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

Bonus code snippets: Bad practice of assigning multiple values to a temp variable. In the long term people will not understand what it is used for. Solution is to change with descriptive variable names. Example

```Java
double temp = totalCost / numberOfProducts; // temp = Individual product Cost	
temp = temp + shipping; // Individual Product Cost + Shipping	
temp = temp - discount; // (Individual Product Cost + Shipping) - Discount
```
Solution

```Java
double indivProductCost = totalCost / numberOfProducts; /
prodCostAndShipping = indivProductCost + shipping; 
discountedProductCost = prodCostAndShipping  - discount; 
```

This bad practice also appliues to parameters that are passed to variables. They should not be used again and again as such. Should be changed with descriptive names that make things make sense forever

```Java
public double getTotPrice(double quantity, double price, double shippingCost, double discount) {
		price = price + shippingCost;
		price = price * quantity;
		return price - discount;
	}
```