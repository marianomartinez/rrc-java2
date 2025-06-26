/* Data insertion */

INSERT INTO `TransactionType` VALUES
	('A', 'Authorization'),
    ('C', 'Credit'), 
    ('P', 'Payment')
;

INSERT INTO `AccountType` VALUES
	(10, 'Single'),
    (20, 'Joint'), 
    (30, 'Minor')
;

INSERT INTO `Accounts` VALUES
	(100, 10, 3000),
    (200, 20, 1000),
    (300, 30, 5000),
    (400, 10, 3000)
;

INSERT INTO `Transaction` VALUES
	(1, 'A' , 100, 200, 100),
    (2, 'C' , 300, 200, 1000),
    (3, 'A' , 400, 200, 100)
;