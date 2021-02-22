-- INNER JOIN
-- Joins all the rows from both tables as long
-- as a certain condition is met.  INNER JOIN is the default join

SELECT * FROM "Artist";
SELECT * FROM "Album";

-- A VIEW is a virtual table based on the result set of a SQL statement
CREATE VIEW art AS
SELECT al."Title", ar."Name" FROM "Album" al
	INNER JOIN "Artist" ar ON al."ArtistId" = ar."ArtistId";
	
-- We can query a VIEW just like any other table 
SELECT * FROM art WHERE "Name" = 'AC/DC';

-- LEFT JOIN/ LEFT OUTER JOIN 
-- The LEFT JOIN keyword returns all of the  rows of the table on the LEFT
-- side of the join and only the matching rows for the table
-- on the right side of the join.

SELECT * FROM "Track";
SELECT * FROM "InvoiceLine";
-- Our goal in joining these 2 tables is to display all track records
-- with matching information from the InvoiceLine table 

SELECT t."Name", t."Composer", il."InvoiceLineId" 
FROM "Track" t 
LEFT JOIN "InvoiceLine" il 
ON t."TrackId" = il."TrackId";

-- The RIGHT JOIN is similar to LEFT JOIN
-- This join returns all of the rows of the table on the right side of
-- the join and only matching rows for the table on the LEFT side of the join

SELECT t."Name", t."Composer", il."InvoiceLineId" 
	FROM "Track" t 
	RIGHT JOIN "InvoiceLine" il 
	ON t."TrackId" = il."TrackId";

SELECT * FROM "InvoiceLine" ;
-- The FULL OUTER JOIN keyword creates a result by 
-- combining the result of BOTH a LEFT & RIGHT JOIN.
SELECT * FROM "Invoice" i2 
	FULL OUTER JOIN "InvoiceLine" il 
	ON i2."InvoiceId" = il."InvoiceId" 
	LIMIT 5; -- This will return only 10 records

-- JOINS combine data into new COLUMNS
-- UNIONS combine data into new ROWS

-- UNION is used to combine the results 
-- of two or more SELECT statements. 
-- However it will eliminate duplicate rows from its resultset. 
-- In case of union, number of columns and datatype must be same 
-- in both the tables, on which UNION operation is being applied.
SELECT "InvoiceId", "Total" FROM "Invoice"
UNION
SELECT "InvoiceId", "UnitPrice" FROM "InvoiceLine";