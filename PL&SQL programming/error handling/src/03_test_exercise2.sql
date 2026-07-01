-- ============================================================
-- Test / demo script for Exercise 2 procedures
-- Run 00_schema.sql and 01_exercise2_error_handling.sql first.
-- ============================================================
SET SERVEROUTPUT ON;

-- 1) Successful transfer: 200 from account 2 -> account 1
EXEC SafeTransferFunds(2, 1, 200);

-- 2) Insufficient funds: account 1 only has 1000+200=1200, try to move 5000
EXEC SafeTransferFunds(1, 2, 5000);

-- 3) Invalid account (account 99 does not exist)
EXEC SafeTransferFunds(1, 99, 100);

-- 4) Successful salary update: employee 2, +10%
EXEC UpdateSalary(2, 10);

-- 5) Salary update for non-existent employee
EXEC UpdateSalary(999, 10);

-- 6) Successfully add a brand-new customer
EXEC AddNewCustomer(3, 'Carol White', TO_DATE('1992-01-10','YYYY-MM-DD'), 500);

-- 7) Attempt to add a customer with a duplicate ID (1 already exists)
EXEC AddNewCustomer(1, 'Duplicate John', TO_DATE('1985-05-15','YYYY-MM-DD'), 0);

-- Inspect the error log that was populated along the way
SELECT LogID, ProcName, ErrorMsg FROM ErrorLog ORDER BY LogID;
