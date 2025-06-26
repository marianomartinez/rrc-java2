package assignment5;

/**
 * A custom exception class, for throwing an exception when there is something wrong with the account number.
 */
public class AccountException extends Exception {

	private static final long serialVersionUID = 1L;
	
	int errorCode;
	String message;
	
	/**
	 * Constructor that takes an errorCode and a message as parameters.
	 * @param errorCode
	 * @param message
	 */
	public AccountException(int errorCode, String message) {
		
		super();
		
		this.errorCode = errorCode;
		this.message = message;
		
	}
	
	/**
	 * @Override
	 * Overridden toString method 
	 */
	public String toString() {
		
		return "Error Code: " + errorCode + ", Message: " + message;
		
	};
	
	
}
