package grammerSolver;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/*
 * @author Tesfaye
 * @version 3
 */

//package grammar;
public class GrammarSolver {
	private Map<String, ArrayList<String[]>> mA;

	/*
	 * Constructor reads all the rules in the list passed as a parameter to
	 * construct grammarRules. Throws a IllegalArgumentException if the list is
	 * empty.
	 */
	public GrammarSolver(List<String> grammar) {
		mA = new TreeMap<String, ArrayList<String[]>>();
		if (grammar.isEmpty()) {
			throw new IllegalArgumentException();
		}
		// Convert list to string
		// String s = grammar.);
		for (String s : grammar) {
			// System.out.println(s);
			// split and clean
			String[] parts = s.split("::=");
			for (int i = 0; i < parts.length; i++) {
				parts[i] = parts[i].trim();
			}
			String[] parts1 = parts[1].split("[|]");
			ArrayList<String[]> parts2 = new ArrayList<String[]>();
			for (String st : parts1) {
				st = st.trim();
				String[] sr = st.split("\\s+");
				parts2.add(sr);
			}
			mA.put(parts[0], parts2);
		}
		// showMap();
	}
/*
 * In order to show how the map reads from the file
 * 
 */
	private void showMap() {
		for (String key : mA.keySet()) {
			System.out.println(key);
			for (String[] str : mA.get(key)) {
				System.out.println("\t" + Arrays.toString(str));
			}
		}
	}

	/*
	 * returns true if the given symbol string is a rule in the grammar
	 * (non-terminal)
	 */
	public boolean grammarContains(String symbol) {
		return mA.containsKey(symbol);
	}

	/*
	 * returns all the non-terminal symbols in the grammar as a string with
	 * brackets and commas separating each symbol.
	 */
	public String getSymbols() {

		return mA.keySet().toString();
	}
/*
 * return the number of times generated
 */
	public String[] generate(String symbol, int times) {
		String[] result = new String[times];
		for (int i = 0; i < times; i++) {
			result[i] = generate(symbol);
		}
		return result;
	}

	/*
	 * constructs and returns as a string a random phrase from the grammar given
	 * the symbol string passed
	 */
	private String generate(String symbol) {
		if (!grammarContains(symbol)) {

			return symbol;
		}
		ArrayList<String[]> rules = mA.get(symbol);
		int random = (int) (Math.random() * rules.size());
		String answer = "";
		// System.out.println(random);
		for (String s : rules.get(random)) {
			answer += generate(s) + " ";
		}
		return answer;
	}
}
