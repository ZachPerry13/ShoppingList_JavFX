package application;

import java.util.*;

public class ShoppingCart {
	// Class Constants

	private final double DEFAULT_BUDGET = 0.0;
	private final double DEFAULT_TOTAL_COST = 0.0;

	// Class Variables -- state of the object

	private ArrayList<Item> shoppingCart;

	private double budget;
	private double totalCost;

	// Constructor -- set the state of the object

	public ShoppingCart() {
		this.shoppingCart = new ArrayList<Item>();

		this.budget = DEFAULT_BUDGET;

		this.totalCost = DEFAULT_TOTAL_COST;
	}

	public ShoppingCart(double budget) {
		this.shoppingCart = new ArrayList<Item>();

		this.budget = budget;

		this.totalCost = DEFAULT_TOTAL_COST;
	}

	// Accessors - getters - return the state

	public double getBudget() {
		return this.budget;
	}

	public double getTotalCost() {
		return this.totalCost;
	}

	public int getNumberItems() {
		return shoppingCart.size();
	}

	public String formatedShoppingCart() {
		// Local Variables

		StringBuffer sb = new StringBuffer();

		String line = null;

		// Iterate thru list to format every item in the list

		for (Item item : shoppingCart) {
			// Format the state of the object

			line = String.format("%10s, %10.2f %2d, %2d%n", item.getName(), item.getPrice(), item.getQuantity(),
					item.getPriority());

			sb.append(line);
		}

		return sb.toString();
	}

	public String createFormattedShoppingCartListGUI() {
		// Local Variables
		double newbudget = budget;

		StringBuffer sb = new StringBuffer();

		// Add formatted header

		sb.append(displayFormattedHeader());
		sb.append("\n");

		// Iterate thru list to format every item in the list

		for (Item item : shoppingCart) {
			sb.append(displayFormattedItem(item));
			newbudget -= (item.getPrice()*item.getQuantity());
		}
		

		// Now add total cost of items in shopping cart

		sb.append(displayFormattedTotalCost());
		sb.append("Money Left: $" + newbudget);
		return sb.toString();
	}

	// Mutators/Transformers -- setters -- change the state

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public void addItem(Item newItem) throws Exception {
		// Local Variables

		double costItem = 0.0;

		// Calculate cost of this item

		costItem = newItem.calculateTotalCost();

		if (budget - costItem > 0) {
			shoppingCart.add(newItem);

			totalCost += costItem;
		}

		else
			throw new Exception("Purchase of Item: " + newItem.getName() + " Price: " + newItem.getPrice()
					+ " will make total cost exceed the budget: $" + budget + "!");

		// Sort by priority

		Collections.sort(shoppingCart);
	}

	public void removeItem(Item removeItem) {
		// Local Variables

		Item listItem = null;
		boolean found = false;

		// Iterate thru list to format every item in the list

		for (int i = 0; i < shoppingCart.size() && !found; i++) {
			listItem = shoppingCart.get(i);

			if (listItem.equals(removeItem)) {
				shoppingCart.remove(i);
				found = true;

				totalCost -= removeItem.calculateTotalCost();
			}
		}

		// Sort by priority

		Collections.sort(shoppingCart);
	}

	public void clear() {
		shoppingCart.clear();

		totalCost = 0.0;
	}

	// Class helper methods

	public String displayFormattedHeader() {
		// Local Variables

		String header = null;

		// Format the state of the object

		header = String.format("%-20s %13s    %8s    %8s%n", "Name", "Price", "Quantity", "Priority");

		return header;
	}

	private String displayFormattedItem(Item item) {
		// Local Variables

		String line = null;

		// Format the state of the object

		line = String.format("%-20s %13.2f    %8d    %8d%n", item.getName(), item.getPrice(), item.getQuantity(),
				item.getPriority());

		// Return formatted line

		return line;
	}

	private String displayFormattedTotalCost() {
		// Local Variables

		String formatStr = null;

		// Format total cost line

		formatStr = String.format("%nTotal cost of Cart: $%10.2f%n", this.totalCost);

		return formatStr;
	}
}
