-- SQL (-- comment, which will not be read)
--Structured Query Language
-- SQL is broken down into 5 sublanguges (also called dialects)
-- DDL - Data Definition Language -- We use this for creating tables
-- * CREATE -- creates a table
-- * ALTER -- edits a table
-- * DROP -- deletes a table from schema/db
-- * TRUNCATE -- erases all the data within the tabl 

-- DDL Commands
-- Column names
-- Data types
-- Constraints

CREATE TABLE IF NOT EXISTS users (
--  col-name , datatype, constraint <comma>
	id SERIAL PRIMARY KEY,-- everytime a new record is entered, SERIAL automatically increases by 1
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL UNIQUE 
);

CREATE TABLE IF NOT EXISTS posts (
	id SERIAL PRIMARY KEY,
	author_id INTEGER REFERENCES users(id) NOT NULL,
	tweet VARCHAR(250) NOT NULL
);

-- ALTER TABLE is used to edit a table
ALTER TABLE users
	ADD COLUMN test INTEGER;

ALTER TABLE users
	ALTER COLUMN test SET DATA TYPE VARCHAR(2);

ALTER TABLE users
	ALTER COLUMN test SET DEFAULT 'hi';


-- TO QUERY ALL OF THE DATA within users table
-- DQL: SELECT
SELECT * FROM users;

-- COMPOSITE KEY = a unique identifier that is a combination
-- of multiple columns
CREATE TABLE IF NOT EXISTS likes (
	user_id INTEGER REFERENCES users(id) NOT NULL,
	post_id INTEGER REFERENCES posts(id) NOT NULL,
	
	PRIMARY KEY(user_id, post_id) 
);

SELECT * FROM users;






