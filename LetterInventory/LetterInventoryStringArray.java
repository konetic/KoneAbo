package LetterInventory;

/* LetterInventory - used to store the count of letters of the alphabet for a given input,
 *keep track of an inventory of letters of the alphabet size will change when user adds letters
 *of the alphabet into the inventory, combined or find the difference with other LetterInventory 
 *The constructor for the class takes a String and computes how many of each letter are in the String. 
 *
 * @author Tesfaye
 * @version 3
 */

public class LetterInventoryStringArray {

	private static final String[] alphabet = { "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z" };
	private int[] counters = new int[26];

	/*
	 * Constructs an inventory (a count) of the alphabetic letters in the given
	 * string, ignoring the case of letters and ignoring any non-alphabetic
	 * characters. letters are all in lower cases
	 * 
	 * @param data pass data to the method letterInventory
	 */
	public LetterInventoryStringArray(String data) {
		data = data.toLowerCase();
		for (int i = 0; i < data.length(); i++) {
			char let = data.charAt(i);
			if (let >= 'a' && let <= 'z') {
				int index = let - 'a';
				counters[index]++;
			}
		}
	}

	/*
	 * Returns the sum of all of the counts in this inventory.
	 */
	public int size() {
		int sum = 0;
		for (int i = 0; i < counters.length; i++) {
			sum += counters[i];
		}
		return sum;
	}

	/*
	 * Returns true if there is no letter in this inventory(all counts are 0) otherwise false
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/*
	 * 
	 * Returns a String representation of the inventory with the letters all in
	 * lower case and in sorted order and surrounded by square brackets.
	 * 
	 * @return strings surrounded by square brackets
	 */
	public String toString() {
		String s = "[";
		for (int i = 0; i < counters.length; i++) {
			for (int x = 0; x < counters[i]; x++) {
				s += (char) ('a' + i);
			}
		}
		return s + "]";
	}

	/*
	 * take in a LetterInventory object, return a LetterInventory object which
	 * is the sum of other two LetterInventory objects or in other words adds
	 * the letters in the given inventory to those in this one
	 * @param other - the inventory to add
	 * @return temp the summation of two inventories
	 */
	public LetterInventoryStringArray add(LetterInventoryStringArray other) {
		LetterInventoryStringArray temp = new LetterInventoryStringArray("");
		for (int i = 0; i < counters.length; i++)
			temp.counters[i] = this.counters[i] + other.counters[i];
		return temp;
	}

	/*
	 * Constructs and returns a new LetterInventory object that represents the
	 * result of subtracting the other inventory from this inventory (i.e.,
	 * subtracting the counts in the other inventory from this object�s counts)
	 * 
	 * @param other - the inventory to subtract
	 * 
	 * @return temp the subtraction of two inventories
	 */
	public LetterInventoryStringArray subtract(LetterInventoryStringArray other) {
		LetterInventoryStringArray temp = new LetterInventoryStringArray("");
		for (int i = 0; i < counters.length; i++) {
			temp.counters[i] = this.counters[i] - other.counters[i];
			if (temp.counters[i] < 0) {
				return null;
			}
		}
		return temp;
	}

	/*
	 * Sets the count for the given letter to the given value, change the number
	 * of count for a single aplphabet will throw an IllegalArgumentException
	 * when the letter is a non-alphabetic letter or the value is negative
	 * 
	 * @param letter, value take in a character and an integer
	 */
	public void set(char letter, int value) {
		if (!Character.isLetter(letter) || value < 0) {
			throw new IllegalArgumentException();
		}
		int index;
		if (letter >= 'a' && letter <= 'z') {
			index = letter - 'a';

		} else {
			index = letter - 'A';
		}
		counters[index] = value;
	}

	/*
	 * Returns a count of how many of this letter are in the inventory take in a
	 * character, return the count of given character in the inventory, will
	 * throw an IllegalArgumentException when the letter is a nonalphabetic
	 * letter
	 * 
	 * @param letter: pass letter to the method get
	 * 
	 * @return counters: returns a count of how many of this letter are in the
	 * inventory
	 */
	public int get(char letter) {
		if (!Character.isLetter(letter))
			throw new IllegalArgumentException("letter: " + letter);
		return counters[Character.toLowerCase(letter) - 'a'];
	}
}
