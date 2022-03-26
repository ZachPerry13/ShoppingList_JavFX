package application;

import java.util.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ShoppingCartFileUtilities {
	public static void read(ShoppingCart shoppingCart, String filename) {
		// Local Variables

		Scanner sc = null;

		String record = null;
		String fields[] = null;

		Item item = null;

		String name = null;
		double price = 0.0;
		int priority = 0;
		int quantity = 0;

		try {
			sc = new Scanner(new File(filename));

			while (sc.hasNextLine()) {
				// Read in line of text file

				record = sc.nextLine().trim();

				System.out.println(record);

				if (record.length() > 0) {
					// Parse line to extract item information

					fields = record.split(",");

					name = fields[0].trim();
					price = Double.parseDouble(fields[1].trim());
					quantity = Integer.parseInt(fields[2].trim());
					priority = Integer.parseInt(fields[3].trim());

					// Instantiate item

					item = new Item(name, price, quantity, priority);

					// Add item to shopping cart

					try {
						shoppingCart.addItem(item);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}

			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Filename: " + filename + " does not exist!");
		}
	}

	public void write(ShoppingCart shoppingCart, String filename) {
		// Local Variables
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));

		PrintWriter writer = null;

		try {
			writer = new PrintWriter(filename);

			writer.write("Last time shopping: " + dtf.format(now) + "\n");
			writer.write("Items purchased: \n");
			writer.write(shoppingCart.formatedShoppingCart());
			writer.close();

		} catch (Exception e) {
			System.out.println("Exception error writing shopping to filename: " + filename + "!");
		}
	}
}