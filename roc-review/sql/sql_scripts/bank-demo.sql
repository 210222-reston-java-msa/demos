CREATE TABLE roles (
	id SERIAL PRIMARY KEY,
	role_name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE users (
	id INTEGER PRIMARY KEY,
	username VARCHAR(50) UNIQUE NOT NULL,
	pass VARCHAR(50) NOT NULL,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	email VARCHAR(50),
	role_id INTEGER NOT NULL REFERENCES roles (id) -- this is one to many
	-- one to many 
	-- one to one
	-- many to many (cardinality which describes the numerical rltns between tables
);

-- create a Account_Status table -- similar to roles table (static info)
CREATE TABLE account_status (
	id SERIAL PRIMARY KEY,
	status VARCHAR(50) UNIQUE NOT NULL
);

-- create a Account_Type table
CREATE TABLE account_type (
	id SERIAL PRIMARY KEY,
	acc_type VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE accounts (
	id SERIAL PRIMARY KEY,
	balance NUMERIC(20, 2) DEFAULT 0 NOT NULL,
	status_id INTEGER NOT NULL REFERENCES account_status(id),
	type_id INTEGER NOT NULL REFERENCES account_type(id)
);

-- Many-to-many relationship are best definied by a JOINS table
-- in the user_accounts table, we will feature all of the accounts that may
-- belong to different users:
CREATE TABLE user_accounts (
	
	user_id INTEGER NOT NULL,
	account_id INTEGER NOT NULL,
	
	CONSTRAINT user_accounts_pk PRIMARY KEY (user_id, account_id),
	
	CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES users(id),
	CONSTRAINT account_id_fk FOREIGN KEY (account_id) REFERENCES accounts(id)
);














