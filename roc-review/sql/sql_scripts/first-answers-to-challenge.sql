-- 2.1 Answer
SELECT * FROM chinook."Employee";

-- 2.1 Last name is king
SELECT * FROM "Employee" WHERE "LastName" = 'King';

-- 2.1
-- SELECT all records from the Employee table where first name is Andrew and REPORTSTO is NULL
SELECT * FROM "Employee" WHERE "FirstName" = 'Andrew' AND "ReportsTo" IS NULL;

-- 2.2
SELECT * FROM "Album" 
	ORDER BY "Title" DESC;
	
--2.2
SELECT "FirstName", "City" FROM "Customer" 
	ORDER BY "City" ASC;
	
-- 2.5
SELECT * FROM "Invoice" 
	WHERE "BillingAddress" LIKE 'T%';




