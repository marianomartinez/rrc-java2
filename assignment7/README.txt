For the SQL part, there are 2 separate SQL scripts:
- CreateTables.sql creates the database and the tables
- InsertInitialData.sql inserts initial data into all 4 tables (*). This script can also be replaced by running the methods in the App.Main() code

(*) For the Transaction table initial data, the transaction IDs were replaced with consecutive numbers, as equal primary key IDs would not work 


For the Java part, there are 4 classes (1 per table), containing the methods required plus additional utility methods.
There is an additional App.java with the Main method, so all the tables' methods can be called from one Main().

All 4 classes have insert, update and delete methods. However, for the account update example I went a different route. Instead of using the "UPDATE field FROM table WHERE id=?;", I used the ResultSet updateInt method.

Also, I added an extra method that manages a transfer between 2 accounts, updating both balances, and adding the transaction record.

Eclipse shows 17 warnings in App.java, but all of them refer to the local variables that are unused because the calls to methods are commented.

