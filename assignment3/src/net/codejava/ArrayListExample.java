package net.codejava;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 */
public class ArrayListExample {

	/**
	 * @param args (not used)
	 */
	public static void main(String[] args) {
		
		/**
		 * Initializes an arrayList for languages
		 */
		ArrayList<String> languageList = new ArrayList<String>();
		
		/**
		 * Adds languages to the list
		 */
		languageList.add("English");
		languageList.add("French");
		languageList.add("Italian");
		languageList.add("Arabic");
		
		/**
		 * Iterates through the list and prints to console
		 */
		Iterator<String> iterateLanguages = languageList.iterator();
		
		System.out.println("Unsorted list: ");
		while (iterateLanguages.hasNext()) {
			System.out.println(iterateLanguages.next());
		}
		
		/**
		 * Sorts the list alphabetically
		 */
		languageList.sort(null);
		
		/**
		 * Iterates through the list and prints to console
		 */
		iterateLanguages = languageList.iterator();

		System.out.println();
		System.out.println("Sorted list: ");
		while (iterateLanguages.hasNext()) {
			System.out.println(iterateLanguages.next());
		}

	}
		
}