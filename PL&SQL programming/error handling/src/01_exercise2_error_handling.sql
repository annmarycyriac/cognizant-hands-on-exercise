-- ============================================================
-- Exercise 2: Error Handling
-- ============================================================

CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_from_account IN Accounts.AccountID%TYPE,
    p_to_account   IN Accounts.AccountID%TYPE,
    p_amount       IN NUMBER
) IS
    v_from_balance   Accounts.Balance%TYPE;
    e_insufficient_funds EXCEPTION;
    e_invalid_account    EXCEPTION;
BEGIN
   
    BEGIN
        SELECT Balance INTO v_from_balance
        FROM Accounts
        WHERE AccountID = p_from_account
        FOR UPDATE;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE e_invalid_account;
    END;

  
    DECLARE
        v_dummy Accounts.AccountID%TYPE;
    BEGIN
        SELECT AccountID INTO v_dummy
        FROM Accounts
        WHERE AccountID = p_to_account;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE e_invalid_account;
    END;

    IF v_from_balance < p_amount THEN
        RAISE e_insufficient_funds;
    END IF;

    UPDATE Accounts
       SET Balance = Balance - p_amount, LastModified = SYSDATE
     WHERE AccountID = p_from_account;

    UPDATE Accounts
       SET Balance = Balance + p_amount, LastModified = SYSDATE
     WHERE AccountID = p_to_account;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transfer of ' || p_amount || ' from account ' ||
                          p_from_account || ' to account ' || p_to_account ||
                          ' completed successfully.');

EXCEPTION
    WHEN e_insufficient_funds THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcName, ErrorMsg)
        VALUES ('SafeTransferFunds',
                'Insufficient funds in account ' || p_from_account ||
                ' for transfer of ' || p_amount);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('ERROR: Insufficient funds in account ' || p_from_account || '.');

    WHEN e_invalid_account THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcName, ErrorMsg)
        VALUES ('SafeTransferFunds',
                'Invalid account(s): ' || p_from_account || ' -> ' || p_to_account);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('ERROR: One or both accounts do not exist.');

    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcName, ErrorMsg)
        VALUES ('SafeTransferFunds', SQLERRM);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('ERROR: Unexpected error during transfer - ' || SQLERRM);
END SafeTransferFunds;
/



CREATE OR REPLACE PROCEDURE UpdateSalary (
    p_employee_id IN Employees.EmployeeID%TYPE,
    p_percentage  IN NUMBER
) IS
    v_current_salary Employees.Salary%TYPE;
BEGIN
    SELECT Salary INTO v_current_salary
    FROM Employees
    WHERE EmployeeID = p_employee_id;

    UPDATE Employees
       SET Salary = Salary + (Salary * p_percentage / 100)
     WHERE EmployeeID = p_employee_id;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Salary for employee ' || p_employee_id ||
                          ' increased by ' || p_percentage || '%. New salary: ' ||
                          (v_current_salary + (v_current_salary * p_percentage / 100)));

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        INSERT INTO ErrorLog (ProcName, ErrorMsg)
        VALUES ('UpdateSalary', 'Employee ID ' || p_employee_id || ' does not exist.');
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('ERROR: Employee ID ' || p_employee_id || ' not found.');

    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcName, ErrorMsg)
        VALUES ('UpdateSalary', SQLERRM);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('ERROR: Unexpected error updating salary - ' || SQLERRM);
END UpdateSalary;
/



CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_customer_id IN Customers.CustomerID%TYPE,
    p_name        IN Customers.Name%TYPE,
    p_dob         IN Customers.DOB%TYPE,
    p_balance     IN Customers.Balance%TYPE
) IS
BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE);

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Customer ' || p_name || ' (ID: ' || p_customer_id ||
                          ') added successfully.');

EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcName, ErrorMsg)
        VALUES ('AddNewCustomer',
                'Customer ID ' || p_customer_id || ' already exists. Insertion prevented.');
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('ERROR: Customer ID ' || p_customer_id || ' already exists.');

    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcName, ErrorMsg)
        VALUES ('AddNewCustomer', SQLERRM);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('ERROR: Unexpected error adding customer - ' || SQLERRM);
END AddNewCustomer;
/
