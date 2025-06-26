package net.codejava;

/**
 * The App class contains the Main method, to run the different methods from the other classes, to interact with the different tables of the database.
 */
public class app {
	
	/**
	 * 
	 * @param args (not used)
	 */

	public static void main(String[] args) {
		
		
		/* Working with AccountType table */
		
		/* Insert initial data into AccountType */
    	Object[][] accountTypeInitialData = {
    			{10, "Single"},
    			{20, "Joint"},
    			{30, "Minor"}
    	};
    	// AccountType.insertAccountType(accountTypeInitialData);
    	
    	/* Update an Account Type description */
    	Object[] accountTypeToUpdate = {20, "Conjunta"};
    	// AccountType.updateAccountType(accountTypeToUpdate);
    	
    	/* Delete an Account Type */
    	int accountTypeToDelete = 30;
    	// AccountType.deleteAccountType(accountTypeToDelete);

    	
    	
    	/* Working with Accounts table */
    	
    	/* Insert initial data into Accounts */
    	int[][] accountsInitialData = {
    			{100, 10, 3000},
    			{200, 20, 1000},
    			{300, 30, 5000},
    			{400, 10, 3000}
    	};
    	// Accounts.insertAccount(accountsInitialData);
    	
    	/* Try to insert an invalid AccountType Account */
    	int[][] accountToAddInvalidType = {
    			{500, 50, 2999}
    	};
    	// Accounts.insertAccount(accountToAddInvalidType);
    	
    	/* Modify the balance of an Account (use "+" or "-" to test either deposit or withdraw) */
    	Object[] dataToAlterBalance = {200, 5000, "+"};
    	// Accounts.alterBalance(dataToAlterBalance);

		/* Delete an Account */
    	int accountToDelete = 100;
    	// Accounts.deleteAccount(accountToDelete);
    	
    	
    	
    	/* Working with TransactionType table */
    	
    	/* Insert initial data into TransactionType */
    	String[][] transactionTypeInitialData = {
    			{"A", "Authorization"},
    			{"C", "Credit"},
    			{"P", "Payment"}
    	}; 
        // TransactionType.insertTransactionType(transactionTypeInitialData);
    	
    	/* Update TransactionType description */
    	String[] transactionTypeToUpdate = {"A", "Autorizacion"};
    	// TransactionType.updateTransactionType(transactionTypeToUpdate);
    	
    	/* Delete a TransactionType */
    	String transactionTypeToDelete = "P";
    	// TransactionType.deleteTransactionType(transactionTypeToDelete);
    	
    	

    	/* Working with Transaction table */
    	
    	/* Insert initial data into Transaction table */
		Object[][] transactionInitialData = {
    			{1, "A", 100, 200, 100},
    			{2, "C", 300, 200, 1000},
    			{3, "A", 400, 200, 100}
    	};
    	// Transaction.insertTransaction(transactionInitialData);
		
		/* Test insert into Transaction with invalid field. */
		/* 
		 * Change value "A" to "B".
		 * Change value 400 to 700.
		 * Change value 200 to 700.
		 *  */
		Object[][] oneTransactionInitialData = {
    			{4, "A", 400, 200, 100}
    	};
		// Transaction.insertTransaction(oneTransactionInitialData);
		
    	

		/* Update the TransactionType of a Transaction */
		Object[] transactionDataToUpdate = {1, "P"};
		// Transaction.updateTransaction(transactionDataToUpdate);
		
		/* Delete a Transaction */
    	int transactionToDelete = 4;
    	// Transaction.deleteTransaction(transactionToDelete);
    	
    	
    	
    	/* Transfer between accounts */
    	int originAccountNumber = 100;
    	int destinationAccountNumber = 200;
    	int amountToTransfer = 123;
    	// transferBetweenAccounts(originAccountNumber, destinationAccountNumber, amountToTransfer);
    	
	}

	/**
	 * transferBetweenAccounts is an extra method, added to perform a transfer between two existing accounts.
	 * It modifies the balance of the origin and destination accounts, and records the operation on the Transaction table. 
	 * @param originAccountNumber
	 * @param destinationAccountNumber
	 * @param amountToTransfer
	 */
	public static void transferBetweenAccounts(int originAccountNumber, int destinationAccountNumber, int amountToTransfer) {
    	
		/**
		 * Gets the current balance of the origin account
		 */
		int originAccountBalance = Accounts.getBalance(originAccountNumber);
    	
		/**
		 * Generates objects to pass to the different methods
		 */
    	Object[] withdrawData = {originAccountNumber, amountToTransfer, "-"};
    	Object[] depositData = {destinationAccountNumber, amountToTransfer, "+"};
    	Object[][] transactionData = {
    			{Transaction.getLastTransactionId() + 1, "P", originAccountNumber, destinationAccountNumber, amountToTransfer}
    	};		
    	
    	/**
    	 * If the origin account has enough balance for the operation, it runs the 3 table-modification methods
    	 */
    	if (originAccountBalance >= amountToTransfer) {
    		Accounts.alterBalance(withdrawData);
    		Accounts.alterBalance(depositData);
    		Transaction.insertTransaction(transactionData);
    	} else {
    		System.out.println("Balance of origin account is not enough to make the transfer");
    	}
	}

}
