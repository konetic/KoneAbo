package LetterInventory;

/*
 * @author Tesfaye
 * @version 3
 */

public class Letter {

	public static void main(String[] args) {

		/*
		 * Constructs and returns a new Letter Inventory object that represents
		 * the sum of this letter inventory and the other given LetterInventory.
		 */
		LetterInventoryStringArray inventoryOne = new LetterInventoryStringArray("George W. Bush");
		System.out.println(inventoryOne);

		/*
		 * Constructs and returns a new LetterInventory object that represents
		 * the result of subtracting the other inventory from this inventory
		 * (i.e., subtracting the counts in the other inventory from this
		 * object�s counts)
		 */
		LetterInventoryStringArray inventoryTwo = new LetterInventoryStringArray("Hillary Clinton");
		System.out.println(inventoryTwo);
		System.out.println(inventoryOne.size());
		System.out.println(inventoryTwo.size());

		// adding inventory 1 and inventory 2
		LetterInventoryStringArray add = inventoryOne.add(inventoryTwo);
		System.out.println(add);

		// Sets the count for the given letter to the given value
		inventoryOne.set('z', 10);
		System.out.println(inventoryOne);
		inventoryOne.set('z', 0);
		System.out.println(inventoryOne);

		// subtracting inventory 1 and inventory 2
		LetterInventoryStringArray subtract = inventoryOne.subtract(inventoryTwo);
		System.out.println(subtract);

		// test cases
		LetterInventoryStringArray three = new LetterInventoryStringArray("abcabcabc");
		LetterInventoryStringArray two = new LetterInventoryStringArray("bcacab");
		LetterInventoryStringArray subtract2 = three.subtract(two);
		System.out.println(subtract2);

		// Returns a count of how many of this letter are in the inventory
		System.out.println(inventoryOne.get('g'));

	}

}
