- Transactions and their properties:
-- The essential point of a transaction is that it bundles multople steps into a single
-- "all-or-nothing" operation.

CREATE TABLE accounts (
	c_name VARCHAR(50),
	balance NUMERIC(50, 2)
);

INSERT INTO accounts VALUES ('Alice', 100);
INSERT INTO accounts VALUES ('Bob', 300);

UPDATE accounts SET balance = balance - 50 
	WHERE c_name = 'Alice';

UPDATE accounts SET balance = balance + 50
	WHERE c_name = 'Bob';

INSERT INTO accounts VALUES ('Wally', 0);

-- TCL Transaction control language
BEGIN;
UPDATE accounts SET balance = balance -50
	WHERE c_name = 'Alice';
SAVEPOINT my_savepoint;
UPDATE accounts SET balance = balance +50
	WHERE c_name = 'Bob';
-- oops didn't mean to send it to Bob...
ROLLBACK TO my_savepoint;
UPDATE accounts SET balance = balance + 50
	WHERE c_name = 'Wally';
COMMIT;

SELECT * FROM accounts;

-- Transactions are a set of tasks to be performed against the DB, grouped within a single unit
-- Transactions always have to do with CRUD operations 

-----------------------------------------------------------------------------------
-- A.C.I.D Properties
-- A proper transaction must have these properties

-- A = Atomicity = it either happens or it doesn't. It's successfull, or not
-- C = Consistancy = Referential Integrity is maintained and constraints are changed properly upon successful transaction 
-- I = Isolation = Transactions occur independently of eachother
-- D = Durability = Ensures that the result of a committed transaction persists in the case of system failure.

--------------------------------------------------------------------------------------
-- Transaction Problems - sometimes issues occur with transactions interfering with eachother
-- and getting ROLLED BACK due to the concurrent nature by which we use them.

-- Dirty Read: A transaction reads data from another transaction that hasn't been commited
-- Non-Repeatable Read: 1 transaction reads the same data TWICE while another transaction 
-- updates the data in between 1st and 2nd
-- Phantom Read: transaction runs a query twice and gets different data each time. (i.e returns a different # of rows.

---------------------------------------
-- Transaction Isolation Levels: The higher the isolation level, the more careful the system is about avoiding conflicts.
