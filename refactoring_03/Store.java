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