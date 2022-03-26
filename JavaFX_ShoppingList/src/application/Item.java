package application;


 
public class Item extends ShoppingCart implements Comparable <Item>
{
	// Class Variables -- state of the object

	private String name;
	private double price;
	private int    priority;
	private int    quantity;

	// Constructor -- set the state of the object

	public Item( String name, double price, int quantity, int priority )
	{	
		this.name     = name;
		this.price    = price;
		this.quantity = quantity;   
		this.priority = priority;
	}  

	

	public String getName(){
		return this.name;
	}

	public double getPrice(){
		return this.price;
	}

	public int getQuantity(){
		return this.quantity;
	}
	
	public int getPriority(){
		return this.priority;
	}
	
	public double calculateTotalCost()
	{
		return ( this.getPrice() * this.getQuantity() );
	}


	@Override
	public int compareTo( Item item ) 
	{
		return Integer.compare( priority, item.getPriority() );
	}

	@Override
	public String toString(){
		return "Name: " + name + " Price: $" + price + " Quantity: " + quantity + " Priority: " + priority;
	}
	
	// Mutators/Transformers -- setters -- change the state
	
	public void setName(String name){
		this.name = name;
	}	

	public void setPrice(double price, int quantity){
		this.price = price * quantity;
	}

	public void setPriority(int priority){
		this.priority = priority;
	}

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
}