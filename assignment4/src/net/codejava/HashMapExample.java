/**
 * 
 */
package net.codejava;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that:
 * Initializes an instance of a HashMap
 * Adds key:value pairs of country code and country
 * Prints the contents of the list
 * Removes an entry
 * Adds an entry
 * Prints the contents of the list
 */
public class HashMapExample {

	/**
	 * Main method to run the app
	 * @param args (not used)
	 */
	public static void main(String[] args) {

		/**
		 * Initializes an instance of a HashMap
		 */
		HashMap<String, String> countryMap = new HashMap<String, String>();
		
		/**
		 * Adds key:value pairs of country code and country
		 */
		countryMap.put("US", "United States");
		countryMap.put("CAN", "Canada");
		countryMap.put("UK", "United Kingdom");
		countryMap.put("FRC", "France");
		countryMap.put("IT", "Italy");
		
		/**
		 * Prints the contents of the list
		 */
		printCountries(countryMap);
		
		/**
		 * Removes an entry
		 */
		countryMap.remove("FRC");
		
		/**
		 * Adds an entry
		 */
		countryMap.put("SP", "Spain");
		
		/**
		 * Prints the contents of the list
		 */
	    System.out.println();
		printCountries(countryMap);
		
	}

	/**
	 * Method that prints the country code and country name of the key:value pairs in the countryMap hashMap
	 * @param countryMap
	 */
	private static void printCountries(HashMap<String, String> countryMap) {

		System.out.println("Contents of countryMap: ");

		/**
		 * Iterates through the entries and prints the key:value pairs
		 */
		for (Map.Entry<String, String> entry : countryMap.entrySet()) {
            System.out.print(entry.getKey() + " > " + entry.getValue());
            System.out.println();
        
		}
	}

}
