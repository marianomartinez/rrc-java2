package net.codejava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;

/**
 * Class that provides interaction with the table on the database
 */
public class AccountType {

	/**
	 * Properties for access to the database
	 */
	private static final String USER_NAME = "root";
    private static final String PASS_WORD = "root";
    private static final String CONNECTION_STR = "jdbc:mysql://localhost/assignment7db";
    
    /**
     * Updates a row of the AccountType table
     * @param row Object[] int, String
     * row[0] contains the account type ID (acctTypeId) to identify the row.
     * row[1] contains the new account description (accountTypeDesc) to update.
     */
    public static void updateAccountType(Object[] row) {
    	String sql = "UPDATE accountType SET accountTypeDesc = ? WHERE acctTypeId = ?";
    	
    	try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
    			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
    	{
    		
    		// Account Description
    		stmt.setString(1, (String) row[1]);
    		
    		// Account ID
    		stmt.setInt(2, (int) row[0]);
    		
    		int result = stmt.executeUpdate();
        	
        	// Update successful if result==1
            if (result == 1) {
            	System.out.println("Account type updated");            	
            } else {
            	System.out.println("Account type not updated");
            }
    		
    	} catch (SQLException e) {
            System.err.println(e);
    	}
    	
    }
    
    /**
     * Deletes a row of the AccountType table
     * @param identifier int
     */
    public static void deleteAccountType(int identifier) {
		String sql = "DELETE FROM accountType WHERE acctTypeId = ?";

        try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
        {
        	
        	// Account ID
        	stmt.setInt(1, identifier);
        	
        	int result = stmt.executeUpdate();
        	
        	// Insert successful if result==1
            if (result == 1) {
            	System.out.println("Account type deleted");            	
            } else {
            	System.out.println("Account type not deleted");
            }
        	
        } catch (SQLException e) {
                System.err.println(e);
        }
	}

    
    /**
     * Inserts a row into the AccountType table
     * @param rows Object[] int, String
     * row[0] contains the account type ID (acctTypeId)
     * row[1] contains the account description (accountDesc)
     */
    public static void insertAccountType(Object[][] rows) {
		
		String sql = "INSERT INTO accountType VALUES (?,?)";

        try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
        {
            for (Object[] row : rows) {
            	
            	// Account Type ID
            	stmt.setInt(1, (int) row[0]);
            	
            	// Account Description
            	stmt.setString(2, (String) row[1]);

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
                    System.out.println("All account types were inserted");
                } else {
                    System.out.println("Not all account types were inserted");
                }
                
        } catch (SQLException e) {
                System.err.println(e);
        }
	}

}
