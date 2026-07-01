-- ============================================================
-- Test / demo script for Exercise 7 packages
-- Run 00_schema.sql and 02_exercise7_packages.sql first.
-- ============================================================
SET SERVEROUTPUT ON;

-- ----- CustomerManagement -----
EXEC CustomerManagement.AddCustomer(4, 'David Lee', TO_DATE('1988-03-11','YYYY-MM-DD'), 750);
EXEC CustomerManagement.UpdateCustomerDetails(4, p_balance => 900);

DECLARE
    v_balance NUMBER;
BEGIN
    v_balance := CustomerManagement.GetCustomerBalance(4);
    DBMS_OUTPUT.PUT_LINE('Customer 4 balance: ' || v_balance);
END;
/

-- ----- EmployeeManagement -----
EXEC EmployeeManagement.HireEmployee(3, 'Ellen Ray', 'Analyst', 55000, 'Finance');
EXEC EmployeeManagement.UpdateEmployeeDetails(3, p_salary => 58000);

DECLARE
    v_annual NUMBER;
BEGIN
    v_annual := EmployeeManagement.CalculateAnnualSalary(3);
    DBMS_OUTPUT.PUT_LINE('Employee 3 annual salary: ' || v_annual);
END;
/

-- ----- AccountOperations -----
EXEC AccountOperations.OpenAccount(3, 1, 'Savings', 400);

DECLARE
    v_total NUMBER;
BEGIN
    v_total := AccountOperations.GetTotalBalance(1);
    DBMS_OUTPUT.PUT_LINE('Customer 1 total balance across accounts: ' || v_total);
END;
/

EXEC AccountOperations.CloseAccount(3);

DECLARE
    v_total NUMBER;
BEGIN
    v_total := AccountOperations.GetTotalBalance(1);
    DBMS_OUTPUT.PUT_LINE('Customer 1 total balance after closing account 3: ' || v_total);
END;
/
