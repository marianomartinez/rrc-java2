/**
 * 
 */
package assignment5;

/**
 * An account class, with 3 constants (an account number and 2 error codes), and 2 methods for adding or deleting a specified amount.
 */
public class AccountExample {
	
	final int ACCOUNT_NO = 777;
	final int ADD_ACCT_ERR = -10;
	final int DEL_ACCT_ERR = -20;
	
	private final String ERROR_MSG = "Account Exception thrown, not the account number that was expected.";

	
	/**
	 * addAmount method verifies that the account number received matches the account number in the constant. 
	 * Throws AccountException if it does not match 
	 * @param acctNo
	 * @param amt
	 * @return
	 * @throws AccountException
	 */
	public int addAmount(int acctNo, int amt) throws AccountException {
		
		if (acctNo != ACCOUNT_NO) {
			throw new AccountException(ADD_ACCT_ERR, ERROR_MSG);
		}
		
		return amt;
		
	}

	/**
	 * deleteAmount method verifies that the account number received matches the account number in the constant. 
	 * Throws AccountException if it does not match 
	 * @param acctNo
	 * @param amt
	 * @return
	 * @throws AccountException
	 */
	public int deleteAmount(int acctNo, int amt) throws AccountException {
		
		if (acctNo != ACCOUNT_NO) {
			throw new AccountException(DEL_ACCT_ERR, ERROR_MSG);
		}
		
		return amt;
		
	}
	
}
