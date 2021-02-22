-- AGGREGATE FUNCTIONS
-- They reduce an entire column to a single value
-- examples include avg() and max()
SELECT * FROM "Invoice";

SELECT max("Total") FROM "Invoice";
SELECT avg("Total") FROM "Invoice";

-- SCALAR FUNCTIONS
-- return a value for EACH input in the table
SELECT upper("BillingCountry") FROM "Invoice";

