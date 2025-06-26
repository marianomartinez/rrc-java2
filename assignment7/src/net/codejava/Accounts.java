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
public class Accounts {

	/**
	 * Properties for access to the database
	 */
	private static final String CONNECTION_STR = "jdbc:mysql://localhost/assignment7db";
	private static final String USER_NAME = "root";
    private static final String PASS_WORD = "root";
    
    /**
     * Gets the balance of an account
     * @param identifier int
     * @return balance int
     */
    public static int getBalance(int identifier) {
    	
    	int balance = 0;
    	
    	String sql = "SELECT balance FROM accounts WHERE accountId = ?";
    	
    	try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
    			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
    	{
    		stmt.setInt(1, identifier);
    		
    		ResultSet rs = stmt.executeQuery();
    		
    		if (rs.next()) {
    			
    			balance = rs.getInt("balance");
    			
    		}
    		
    	} catch (SQLException e) {
            System.err.println(e);
    	}
    	
    	return balance;

    }
    
    /**
     * Deletes a row of the Accounts table
     * @param identifier int
     */
    public static void deleteAccount(int identifier) {
		String sql = "DELETE FROM accounts WHERE accountId = ?";

        try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
        {
        	
        	// Account ID
        	stmt.setInt(1, identifier);
        	
        	int result = stmt.executeUpdate();
        	
        	// Delete successful if result==1
            if (result == 1) {
            	System.out.println("Account deleted");            	
            } else {
            	System.out.println("No account deleted");
            }
        	
        } catch (SQLException e) {
                System.err.println(e);
        }
	}

    /**
     * Inserts rows into the Accounts table
     * @param rows int[][]
     * rows[0] contains the account ID (accountId)
     * rows[1] contains the account type ID (acctTypeId)
     * rows[2] contains the balance (balance)
     */
    public static void insertAccount(int[][] rows) {
		
    	/* Gets a list valid account types */
    	ArrayList<Integer> validAccountTypes = getValidAccountTypes();
    	
    	/* Validates the account types provided for insertion */
    	boolean accountTypesAreValid = validateAccountType(rows, validAccountTypes);
    	
    	if (accountTypesAreValid) {
    		
    		String sql = "INSERT INTO accounts VALUES (?,?,?)";
    		
    		try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
    				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
    		{
    			for (int[] row : rows) {
    				
    				// Account ID
    				stmt.setInt(1, row[0]);
    				
    				// Account Type ID
    				stmt.setInt(2, row[1]);
    				
    				// Balance
    				stmt.setInt(3, row[2]);
    				
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
    				System.out.println("All accounts were inserted");
    			} else {
    				System.out.println("Not all accounts were inserted");
    			}
    			
    		} catch (SQLException e) {
    			System.err.println(e);
    		}
    		
    	} else {
    		System.out.println("One or more accounts to add have an invalid account type");
    	}
	}

    /**
     * Validates if the account type is in the list of account types
     * @param rows int[][]
     * rows[0] contains the account ID (accountId)
     * rows[1] contains the account type ID (acctTypeId)
     * rows[2] contains the balance (balance)
     * @param validAccountTypes ArrayList<Integer>
     * @return accountTypesEnteredAreValid boolean
     */
	private static boolean validateAccountType(int[][] rows, ArrayList<Integer> validAccountTypes) {
		boolean accountTypesEnteredAreValid = false;
    	
    	/* validate account types */
    	for (int[] row : rows) {
    		
    		/* Check if the account type entered is in the accountType table */
    		if (validAccountTypes.contains(row[1]) == true) {
    			accountTypesEnteredAreValid = true;
    		} else {
    			accountTypesEnteredAreValid = false;
    		}
    		
    	}
    	
		return accountTypesEnteredAreValid;
	}


	/**
	 * Gets a list of account types
	 * @return validAccountTypes ArrayList<Integer>
	 */
	private static ArrayList<Integer> getValidAccountTypes() {
		
		ArrayList<Integer> validAccountTypes = new ArrayList<>();

		String sql = "SELECT acctTypeid FROM accountType";
		
    	try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
    			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
    	{
    		
    		ResultSet rs = stmt.executeQuery();
    		
    		while (rs.next()) {
    			
    			validAccountTypes.add(rs.getInt("AcctTypeId"));
    			
    		}
    		
    	} catch (SQLException e) {
            System.err.println(e);
    	}
		
		return validAccountTypes;
		
	}
    
	/**
	 * Modifies the balance of an account, either adding or subtracting
	 * @param dataToAlterBalance Object[] int, int, String
	 * accountNumber int
	 * amount int
	 * operand String
	 */
    public static void alterBalance(Object[] dataToAlterBalance) {
    	
    	int accountNumber = (int) dataToAlterBalance[0];
    	int amount = (int) dataToAlterBalance[1];
    	String operand = (String) dataToAlterBalance[2];
    	
    	String sql = "SELECT * FROM accounts WHERE accountId = ?";
    	
    	try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
    	         PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE))
    	{
    		
    		// Account ID
    		stmt.setInt(1, accountNumber);
    		
    		ResultSet rs = stmt.executeQuery();
    		
    		if (rs.next()) {
    			
    			int oldBalance = rs.getInt("balance");
    			int newBalance = oldBalance;
    			
    			/* Operand + increases the balance, operand - decreases the balance */
    			if (operand == "+") {
    				newBalance = oldBalance + amount;    				
    			} else if (operand == "-" ) {
    				newBalance = oldBalance - amount;
    			} else {
    				/* If the operand is neither + nor - it prints the error */
    				System.out.println("Alter Balance operation could not be done. Wrong operand.");
    				return;
    			}
    			
    			/* Updates the value on the table through the updateInt method of ResultSet */
    			rs.updateInt("balance", newBalance);
    			rs.updateRow();
    				
    			if (operand == "+") {
    				System.out.println("Deposit successful. New balance: " + newBalance);
    			} else if (operand == "-") {
    				System.out.println("Withdraw successful. New balance: " + newBalance);
    			}
    			
            } else {
                System.out.println("Account not found.");
    		}
    		
    	} catch (SQLException e) {
            System.err.println(e);
    	}
    	
    }
    
    /**
     * Gets a list of the existing account IDs
     * @return accountIds ArrayList<Integer>
     */
    public static ArrayList<Integer> getAccountIds() {
		
    	ArrayList<Integer> accountIds = new ArrayList<>();

		String sql = "SELECT accountId FROM accounts";
		
    	try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
    			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
    	{
    		
    		ResultSet rs = stmt.executeQuery();
    		
    		while (rs.next()) {
    			
    			accountIds.add(rs.getInt("AccountId"));
    			
    		}
    		
    	} catch (SQLException e) {
            System.err.println(e);
    	}
    	
    	return accountIds;
    	
    }

}
