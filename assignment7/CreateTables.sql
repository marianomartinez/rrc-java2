/* Notes about this section:
on the insert into transaction table part, I changed the transaction ids to be different, not always 1.
*/


/* Database creation */
DROP DATABASE IF EXISTS assignment7db;
CREATE DATABASE IF NOT EXISTS assignment7db;
USE assignment7db;

/* Tables removal */
DROP TABLE IF EXISTS TransactionType,
					 AccountType, 
                     Accounts, 
                     Transaction;
         

/* Tables creation */

CREATE TABLE TransactionType (
	TranTypeId      VARCHAR(10) NOT NULL,
    TransactionDesc VARCHAR(30) NOT NULL,
    PRIMARY KEY (TranTypeId)
);

CREATE TABLE AccountType (
	AcctTypeId      INT NOT NULL,
    AccountTypeDesc VARCHAR(30) NOT NULL,
    PRIMARY KEY (AcctTypeId)
);

CREATE TABLE Accounts (
	AccountId      INT NOT NULL,
    AcctTypeId     INT NOT NULL,
    Balance        INT NOT NULL,
    PRIMARY KEY (AccountId),
    FOREIGN KEY (AcctTypeId)  REFERENCES AccountType (AcctTypeId)    ON DELETE CASCADE
);

CREATE TABLE Transaction (
	TransId      INT NOT NULL,
	TranTypeId   VARCHAR(10) NOT NULL,
    AcctIdFrom   INT NOT NULL,
    AcctIdTo     INT NOT NULL,
    Amount       INT NOT NULL,
    PRIMARY KEY (TransId),
    FOREIGN KEY (TranTypeId) REFERENCES TransactionType (TranTypeId) ON DELETE CASCADE,
    FOREIGN KEY (AcctIdFrom) REFERENCES Accounts (AccountId) ON DELETE CASCADE,
    FOREIGN KEY (AcctIdTo) REFERENCES Accounts (AccountId) ON DELETE CASCADE 
);

