/**
 * 
 */
package assignment5;

/**
 * A demo/test class try/catch for exception handling
 */
public class AccountExampleTest {

	/**
	 * @param args (not used)
	 */
	public static void main(String[] args) {

		/**
		 * Initializes an instance of AccountExample
		 */
		AccountExample myAccount = new AccountExample();
				
		/**
		 * Calls the addAmount method of the account object, throws an exception if it fails.
		 */
		try {
			myAccount.addAmount(666, 100);			
		} 
		catch (AccountException e) {
			System.out.println("addAmount threw an Account Exception: " + e.toString());
		}
		
		/**
		 * Calls the deleteAmount method of the account object, throws an exception if it fails.
		 */
		try {
			myAccount.deleteAmount(666, 200);			
		} 
		catch (AccountException e) {
			System.out.println("deleteAmount threw an Account Exception: " + e.toString());
		}
		
	}

}
