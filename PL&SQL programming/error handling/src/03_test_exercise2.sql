
SET SERVEROUTPUT ON;

EXEC SafeTransferFunds(2, 1, 200);

EXEC SafeTransferFunds(1, 2, 5000);

EXEC SafeTransferFunds(1, 99, 100);

EXEC UpdateSalary(2, 10);

EXEC UpdateSalary(999, 10);

EXEC AddNewCustomer(3, 'Carol White', TO_DATE('1992-01-10','YYYY-MM-DD'), 500);

EXEC AddNewCustomer(1, 'Duplicate John', TO_DATE('1985-05-15','YYYY-MM-DD'), 0);

SELECT LogID, ProcName, ErrorMsg FROM ErrorLog ORDER BY LogID;
