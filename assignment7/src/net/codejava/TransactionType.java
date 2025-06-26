package net.codejava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Class that provides interaction with the table on the database
 */
public class TransactionType {
	
	/**
	 * Properties for access to the database
	 */
	private static final String USER_NAME = "root";
    private static final String PASS_WORD = "root";
    private static final String CONNECTION_STR = "jdbc:mysql://localhost/assignment7db";
    
    /**
     * Updates a row of the TransactionType table
     * @param row String[]
     * row[0] contains the transaction type ID (tranTypeId) to identify the row.
     * row[1] contains the new transaction description (transactionDesc) to update.
     */
    public static void updateTransactionType(String[] row) {
    	String sql = "UPDATE transactionType SET transactionDesc = ? WHERE tranTypeId = ?";
    	
    	try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
    			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
    	{
    		
    		// Transaction Description
    		stmt.setString(1, row[1]);
    		
    		// Transaction ID
    		stmt.setString(2, row[0]);
    		
    		int result = stmt.executeUpdate();
        	
        	// Update successful if result==1
            if (result == 1) {
            	System.out.println("Transaction type updated");            	
            } else {
            	System.out.println("Transaction type not updated");
            }
    		
    	} catch (SQLException e) {
            System.err.println(e);
    	}
    	
    }

    /**
     * Deletes a row of the TransactionType table
     * @param identifier String
     */
    public static void deleteTransactionType(String identifier) {
		String sql = "DELETE FROM transactionType WHERE tranTypeId = ?";

        try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
        {
        	
        	// Transaction Type ID
        	stmt.setString(1, identifier);
        	
        	int result = stmt.executeUpdate();
        	
        	// Insert successful if result==1
            if (result == 1) {
            	System.out.println("Transaction type deleted");            	
            } else {
            	System.out.println("No transaction type deleted");
            }
        	
        } catch (SQLException e) {
                System.err.println(e);
        }
	}


    /**
     * Inserts rows into the TransactionType table
     * @param rows String[]
     * row[0] contains the transaction type ID (tranTypeId)
     * row[1] contains the transaction description (tranDesc)
     */
    public static void insertTransactionType(String[][] rows) {
		
		String sql = "INSERT INTO transactionType VALUES (?,?)";

        try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
        {
            for (String[] row : rows) {
            	
            	// Transaction Type ID
            	stmt.setString(1, row[0]);
            	
            	// Transaction Description
            	stmt.setString(2, row[1]);

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
                System.out.println("All transaction types were inserted");
            } else {
                System.out.println("Not all transaction types were inserted");
            }
                
        } catch (SQLException e) {
                System.err.println(e);
        }
	}
    
    /**
     * Gets a list of currently valid transaction types
     * @return ArrayList<String>
     */
    public static ArrayList<String> getValidTransactionTypes() {
		
		ArrayList<String> validTransactionTypes = new ArrayList<>();

		String sql = "SELECT tranTypeid FROM transactionType";
		
    	try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
    			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
    	{
    		
    		ResultSet rs = stmt.executeQuery();
    		
    		while (rs.next()) {
    			
    			validTransactionTypes.add(rs.getString("TranTypeId"));
    			
    		}
    		
    	} catch (SQLException e) {
            System.err.println(e);
    	}

		return validTransactionTypes;
		
	}
    
	
    
}
