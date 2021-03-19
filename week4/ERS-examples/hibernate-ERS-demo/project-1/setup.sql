DROP TABLE IF EXISTS users cascade;
DROP TABLE IF EXISTS userrole cascade;
DROP TABLE IF EXISTS reimbursement cascade;
DROP TABLE IF EXISTS reimbstatus cascade;
DROP TABLE IF EXISTS reimbtype cascade;

DROP TABLE hibernate_sequences;

SELECT * FROM users;
SELECT * FROM userrole;
SELECT * FROM reimbursement ORDER BY reimbid;
SELECT * FROM reimbstatus;
SELECT * FROM reimbtype;


UPDATE users SET role_roleid = 1 WHERE userid = 4;
UPDATE users SET role_roleid = 1 WHERE userid = 2;

SELECT * FROM reimbursement WHERE reimbid=3;

SELECT * FROM reimbursement WHERE reimbstatusid=2;

SELECT * FROM reimbursement WHERE reimbstatusid=1;

UPDATE reimbursement SET reimbstatusid=3 WHERE reimbid =4;

DROP TABLE IF EXISTS student cascade;


COMMIT;