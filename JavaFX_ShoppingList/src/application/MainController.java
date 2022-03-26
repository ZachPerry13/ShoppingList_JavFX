package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class MainController {

	@FXML 
	TextField quanityFieldfxml = null;
	@FXML
	TextField priorityFieldfxml = null;
	@FXML
	TextField intialBudgetfxml = null;
	@FXML
	TextField priceFieldfxml = null;
	@FXML
	TextField itemNamefxml = null;
	@FXML
	TextArea cartOutput = new TextArea();

	ShoppingCart cart = new ShoppingCart();
	
	// Local Variables
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
	
		
	public void setBudget() {

		double budget = 0.0;


		try

		{
			budget = Double.parseDouble( intialBudgetfxml.getText() );
			cart.setBudget(budget);


			if ( budget > 0 )
			{

				intialBudgetfxml.setText( "" + budget );
				intialBudgetfxml.setEditable(false);
				cartOutput.setText("Budget for this trip set to: $" + budget);
				

			}

		}
		catch ( NumberFormatException e )
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alert!");
			alert.setHeaderText("Please enter a valid budget");
			alert.showAndWait();
		}
	}


	public void checkOut() {
		
		
		cartOutput.setText("Checking out...\nCurrent date and "
				+ "time: " + dtf.format(now) + "\n\n");
		
		cartOutput.appendText("Items being written to file:\n");
		
		cartOutput.appendText(cart.createFormattedShoppingCartListGUI());
		
		//Write the current shopping list to the file
		ShoppingCartFileUtilities printer = new ShoppingCartFileUtilities();
		printer.write(cart, "ShoppingList.txt");
		
		
		

	}
	
	public void exit() {
		System.exit(0);
		
	}
	
	public void removeItem(Item item) {
		cart.removeItem(item);
		
	}


	public void addQuant() {
		int quantity = 0;

		// Get quantity from text field

		quantity = Integer.parseInt( quanityFieldfxml.getText() );

		// Check to see if allowed to add one

		if ( quantity < 10 )
		{
			// Add one

			quantity++;
		}

		// Update GUI

		quanityFieldfxml.setText( "" + quantity );
		System.out.println("add quant clicked");

	}
	public void subQuant() {
		int quantity = 0;

		// Get quantity from text field

		quantity = Integer.parseInt( quanityFieldfxml.getText() );

		// Check to see if allowed to add one

		if ( quantity > 0 )
		{
			// Add one

			quantity--;
		}

		// Update GUI

		quanityFieldfxml.setText( "" + quantity );
		System.out.println("sub quant clicked");

	}

	public void addPrior() {
		int priority = 0;

		// Get quantity from text field

		priority = Integer.parseInt( priorityFieldfxml.getText() );

		// Check to see if allowed to add one

		if ( priority < 10 )
		{
			// Add one

			priority++;
		}

		// Update GUI

		priorityFieldfxml.setText( "" + priority );
		System.out.println("add prior clicked");

	}

	public void subPrior() {
		int priority = 0;

		// Get quantity from text field

		priority = Integer.parseInt( priorityFieldfxml.getText() );

		// Check to see if allowed to add one

		if ( priority > 0 )
		{
			// Add one

			priority--;
		}

		// Update GUI

		priorityFieldfxml.setText( "" + priority );
		System.out.println("sub prior clicked");

	}


	public  void addToCart( ) {
		
	
		Item newItem = null;

		String name  = " ";
		double price = 0.0;
		int quantity = 0;
		int priority = 0;
		double budget = 0;
				
		budget = Double.parseDouble(intialBudgetfxml.getText());


		// Get item info from GUI

		name     = itemNamefxml.getText();
		price    = Double.parseDouble( priceFieldfxml.getText());
		quantity = Integer.parseInt( quanityFieldfxml.getText() );
		priority = Integer.parseInt( priorityFieldfxml.getText() );
		


		// Instantiate an item

		newItem = new Item( name, price, quantity, priority );	
		
		
		if(price < budget && name != "") {
		try 
		{
			cart.addItem(newItem);
			cartOutput.setText( "" );
			cartOutput.appendText( cart.createFormattedShoppingCartListGUI() );
			budget = budget -(price*quantity);
			
			//Set all field back to normal
			itemNamefxml.setText("");
			priceFieldfxml.setText("0");
			quanityFieldfxml.setText("0");
			priorityFieldfxml.setText("0");
			
			

		} 
		catch ( Exception e )
		{

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alert!");
			alert.setHeaderText("ERROR");
			alert.showAndWait();
		}
		}else {
		if(name == "") {
			cartOutput.setText("No item entered");}
		if(price > budget){
			
		cartOutput.setText("You can not afford that item or you havent set a budget");
	}

		
	}
	}
	

	public void clearCart() {
		cart.clear();
		cartOutput.setText("Cart Cleared!\n\n");
		cartOutput.appendText(cart.createFormattedShoppingCartListGUI());
	}
	

	
}






