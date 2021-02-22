CREATE SCHEMA project0;
-- CONSTRAINTS
-- What is a constraint?
-- A rule which restricts the breadth of valid data which can be inserted into a column
-- NOT NULL -- values in this column cna't be NULL
-- UNIQUE --Data in this coilumn must be different from all other records (unique)
-- CHECK -- Allows us to provide an expression which the row must pass (for example no values > 100)
-- PRIMARY KEY -- must be unique, and not-null (implicitly so) 
-- FOREIGN KEY -- must reference a primary key or a unique column in another table OR the same table

-- REFERENTIAL INTEGRITY
-- maintains logical relationships between tables to avoid corruption of data.
-- composed of a combination of PRIMARY KEY and FOREGIN KEY
-- doesn't allow the data structure to add any record to a table with a foreign key that doens't
-- correspond to a primary key.


-- Only use
CREATE TABLE project0.employee (
	emp_id SERIAL PRIMARY KEY,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	email VARCHAR (100),
	salary NUMERIC (50, 2) DEFAULT 0, 
	-- NUMERIC datatype for decimals
	supervisor INTEGER
	-- this supervisor column points to the id of the employee that is the supervisor of this employee
	-- that's a foreign key
);

-- By ALTERing the table, I can add constraints manually
-- we are declaring a constraint which is a foreign key
ALTER TABLE project0.employee
	ADD CONSTRAINT supervisor_employee_fk
	FOREIGN KEY (supervisor) REFERENCES project0.employee (emp_id);


CREATE TABLE project0.phonenumber (

	phone_id SERIAL PRIMARY KEY,
	emp_id INTEGER NOT NULL,
	home VARCHAR(20) UNIQUE,
	work_num VARCHAR(20) UNIQUE,
	mobile_num VARCHAR(20) UNIQUE
);

ALTER TABLE project0.phonenumber
	ADD CONSTRAINT phonenumber_employee_fk
	FOREIGN KEY (emp_id) REFERENCES project0.employee (emp_id);

INSERT INTO project0.employee (first_name, last_name, email, salary) 
	VALUES ('Sue', 'Queen', 'sue@mail.com', 10000),
			('Larry', 'Smith', 'ls@mail.com', 5000000000);
		
SELECT * FROM project0.employee;
	
	
UPDATE project0.employee SET supervisor = 2 WHERE emp_id = 1;


INSERT INTO project0.employee (first_name, last_name, email, salary, supervisor) 
	VALUES ('Joe', 'Schmoe', 'js@mail.com', 10000, 2);

DELETE FROM project0.employee WHERE emp_id = 1;

-- ALIAS: Is a way to return a query in a more readable fashion by giving the result a title
SELECT first_name AS "Name" FROM project0.employee;

-- you can concatenate the results with || 
SELECT first_name || ' ' || last_name AS "Full Name" FROM project0.employee;

INSERT INTO project0.employee (first_name, last_name, email, salary) 
	VALUES ('Eli', 'McCormick', 'eli@mail.com', 900000),
			('Jake', 'Gavrila', 'jake@mail.com', 700000),
			('Hayley', 'Fields', 'hf@mail.com', 6000000);

-- ORDER BY clause sorts our queries.  It allows us to declare a column to sort by
-- you can choose ascending or descending
SELECT * FROM project0.employee ORDER BY salary DESC;
SELECT * FROM project0.employee ORDER BY first_name ASC;
SELECT * FROM  project0.employee ORDER BY emp_id ASC;

SELECT * FROM project0.phonenumber;

-- Larry's phonenumbers are as follows:
-- home 999 999 9999
-- work 888 888 8888
-- mobile 777 777 7777


-- Hayley
-- home 111 111 1111
-- work 222 222 2222
--- mobile 333 333 3333 


INSERT INTO project0.phonenumber (emp_id, home, work_num, mobile_num)
	VALUES (2, '999 999 9999','888 888 8888', '777 777 7777');

-- This is hayley's info because emp_6 is the FOREIGN KEY that points to the PPRIMARY KEY of 6 in the empyee table
INSERT INTO project0.phonenumber (emp_id, home, work_num, mobile_num)
	VALUES (6, '111 111 1111','222 222 2222', '333 333 3333');





	
