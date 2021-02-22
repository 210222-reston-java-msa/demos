-- DML
-- Data Manipuation Language
-- DDL is about describing tables, DML is about the data WITHIN the tables

-- DML commands: 
-- INSERT
-- UPDATE
-- DELETE 

-- insertion anatomy:
-- INSERT INTO table_name (col_name, col_name) 
	-- VALUES (col_value, col_value)

-- you can always specifiy the schema you're working with like this:
INSERT INTO public.users (first_name, last_name, email)
	VALUES ('Harry', 'Potter', 'hp@hogwarts.com');

-- inserting multiple values
INSERT INTO public.users (first_name, last_name, email)
	VALUES
		 ('Hermione', 'Granger', 'hg@hogwarts.com'),
		  ('Severus', 'Snape', 'ss@hogwarts.com'),
		  ('Ron', 'Weasley', 'rw@hotmail.com');

SELECT * FROM users;

-- to update table data we use the UPDATE STATEMENT
-- DANGEROUS STATEMENT!!! NO WHERE CLAUSE
UPDATE users SET first_name = 'billy bob';

UPDATE users 
	SET first_name = 'Joe',
		last_name = 'Schmoe' 
	WHERE id > 5;

-- WHERE clause specifies WHERE we want to commit a change OR query data
-- specifies a conditional expression for filtering data
UPDATE users SET first_name = 'billy bob' WHERE id = 8;

SELECT * FROM users WHERE id = 5;

-- CASCADE sets any dependent data to NULL in the child table 
TRUNCATE users CASCADE;

-- Basic exercise:
-- PROVIDE DATA for just the posts table
-- Hermione posts 2 times, Severus snape posts once, 

-- Advanced exercise:
-- PROVIDE DATA for the likes table 

SELECT * FROM posts;
