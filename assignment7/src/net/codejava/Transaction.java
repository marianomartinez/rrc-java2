package net.codejava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class that provides interaction with the table on the database
 */
public class Transaction {

	/**
	 * Properties for access to the database
	 */
	private static final String CONNECTION_STR = "jdbc:mysql://localhost/assignment7db";
	private static final String USER_NAME = "root";
    private static final String PASS_WORD = "root";
    
    /**
     * Gets the last transaction ID, to generate a new transaction ID
     * @return lastTransactionId int
     */
    public static int getLastTransactionId() {
    	
    	int lastTransactionId = 0;
    	
    	String sql = "SELECT MAX(transId) AS transId FROM transaction";
    	
    	try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
    			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
    	{
    		
    		ResultSet rs = stmt.executeQuery();
    		
    		
    		if (rs.next()) {
    			
    			try {
    					lastTransactionId = rs.getInt("transId");
    				}
    				catch (NumberFormatException e) {
    					lastTransactionId = 0;
    				}

    			return lastTransactionId;
    			
    		}
    		
    	} catch (SQLException e) {
            System.err.println(e);
    	}

    	return lastTransactionId;

    }
    
    /**
     * Deletes a row of the Transaction table
     * @param identifier int
     */
    public static void deleteTransaction(int identifier) {
		String sql = "DELETE FROM transaction WHERE transId = ?";

        try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
        {
        	
        	// Transaction ID
        	stmt.setInt(1, identifier);
        	
        	int result = stmt.executeUpdate();
        	
        	// Delete successful if result==1
            if (result == 1) {
            	System.out.println("Transaction deleted");            	
            } else {
            	System.out.println("No transaction deleted");
            }
        	
        } catch (SQLException e) {
                System.err.println(e);
        }
	}
    
    /**
     * Inserts rows into the Transaction table
     * @param rows Object[][] int, String, int, int, int
     * transactionId int
     * transactionTypeId String
     * acctIdFrom int
     * acctIdTo int
     * amount int
     */
    public static void insertTransaction(Object[][] rows) {
    	
    	/* Gets a list of existing transaction types */
    	ArrayList<String> validTransactionTypes = TransactionType.getValidTransactionTypes();
		
    	/* Gets a list of existing accounts */
    	ArrayList<Integer> existingAccounts = Accounts.getAccountIds();
    	    	
		String sql = "INSERT INTO transaction VALUES (?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
        {
            for (Object[] row : rows) {
            	
            	// Transaction ID
            	stmt.setInt(1, (int) row[0]);
            	
            	// Transaction Type ID, checking validation before addition to SQL script
            	if (validateTransactionType((String) row[1], validTransactionTypes)) {
            		stmt.setString(2, (String) row[1]);            		
            	} else {
            		System.out.println("Invalid transaction type. Transaction not inserted");
            	}
            	
            	// From account ID, checking validation before addition to SQL script
            	if (validateAccountExists((int) row[2], existingAccounts)) {
            		stmt.setInt(3, (int) row[2]);            		
            	} else {
            		System.out.println("Invalid origin account. Transaction not inserted");
            	}

            	// To account ID, checking validation before addition to SQL script
            	if (validateAccountExists((int) row[3], existingAccounts)) {
                	stmt.setInt(4, (int) row[3]);
            	} else {
            		System.out.println("Invalid destination account. Transaction not inserted");
            	}
            	
            	// Amount
            	stmt.setInt(5, (int) row[4]);

            	// Add the current statement to the batch
                stmt.addBatch();
                
            }
                
                int[] results = stmt.executeBatch();

                boolean allInserted = true;
                
                // Checks if any execution failed
                for (int result : results) {
                    if (result == Statement.EXECUTE_FAILED) {
                        allInserted = false;
                        break;
                    }
                }

                // Prints feedback related to the execution results
                if (allInserted) {
                    System.out.println("All transactions were inserted");
                } else {
                    System.out.println("Not all transactions were inserted");
                }
                
        } catch (SQLException e) {
                System.err.println(e);
        }
	}

    /**
     * Validates if the transaction type entered exists in the transactionType table
     * @param transactionTypeEntered String
     * @param validTransactionTypes ArrayList<String>
     * @return transactionTypeIsValid boolean
     */
    public static boolean validateTransactionType(String transactionTypeEntered, ArrayList<String> validTransactionTypes) {
		
    	boolean transactionTypeIsValid = false;

    	/* validate transaction type */

		/* Check if the transaction type entered is in the transactionType table */
    	if (validTransactionTypes.contains(transactionTypeEntered) == true) {
    		transactionTypeIsValid = true;
		} else {
			transactionTypeIsValid = false;
		}

		return transactionTypeIsValid;
	}
    
    /**
     * Validates if the account entered exists in the accounts table
     * @param accountId int
     * @param existingAccounts ArrayList<Integer>
     * @return accountExists boolean
     */
    public static boolean validateAccountExists(int accountId, ArrayList<Integer> existingAccounts) {
    	boolean accountExists = false;
    	
		/* Check if the account entered exists in the accounts table */
    	if (existingAccounts.contains(accountId) == true) {
    		accountExists = true;
    	} else {
    		accountExists = false;
    	}
    	
    	return accountExists;
    }

    /**
     * Updates a row of the Accounts table
     * @param data Object[] int, String
     * data[0] contains the transaction ID (transId) to identify the row.
     * data[1] contains the transaction type ID (tranTypeId) to update.
     */
    public static void updateTransaction(Object[] data) {
    	String sql = "UPDATE transaction SET tranTypeId = ? WHERE transId = ?";
    	
    	try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
    			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
    	{
    		
    		// Transaction Type ID
    		stmt.setString(1, (String) data[1]);
    		
    		// Transaction ID
    		stmt.setInt(2, (int) data[0]);
    		
    		int result = stmt.executeUpdate();
        	
        	// Update successful if result==1
            if (result == 1) {
            	System.out.println("Transaction updated");            	
            } else {
            	System.out.println("Transaction not updated");
            }
    		
    	} catch (SQLException e) {
            System.err.println(e);
    	}
    }

}
