-- ============================================================
-- Exercise 7: Packages
-- ============================================================

CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddCustomer (
        p_customer_id IN Customers.CustomerID%TYPE,
        p_name        IN Customers.Name%TYPE,
        p_dob         IN Customers.DOB%TYPE,
        p_balance     IN Customers.Balance%TYPE
    );

    PROCEDURE UpdateCustomerDetails (
        p_customer_id IN Customers.CustomerID%TYPE,
        p_name        IN Customers.Name%TYPE DEFAULT NULL,
        p_balance     IN Customers.Balance%TYPE DEFAULT NULL
    );

    FUNCTION GetCustomerBalance (
        p_customer_id IN Customers.CustomerID%TYPE
    ) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

    PROCEDURE AddCustomer (
        p_customer_id IN Customers.CustomerID%TYPE,
        p_name        IN Customers.Name%TYPE,
        p_dob         IN Customers.DOB%TYPE,
        p_balance     IN Customers.Balance%TYPE
    ) IS
    BEGIN
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
        VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('CustomerManagement.AddCustomer: Added ' || p_name ||
                              ' (ID ' || p_customer_id || ').');
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('CustomerManagement.AddCustomer: Customer ID ' ||
                                  p_customer_id || ' already exists.');
    END AddCustomer;

    PROCEDURE UpdateCustomerDetails (
        p_customer_id IN Customers.CustomerID%TYPE,
        p_name        IN Customers.Name%TYPE DEFAULT NULL,
        p_balance     IN Customers.Balance%TYPE DEFAULT NULL
    ) IS
    BEGIN
        UPDATE Customers
           SET Name         = NVL(p_name, Name),
               Balance      = NVL(p_balance, Balance),
               LastModified = SYSDATE
         WHERE CustomerID = p_customer_id;

        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('CustomerManagement.UpdateCustomerDetails: Customer ID ' ||
                                  p_customer_id || ' not found.');
        ELSE
            COMMIT;
            DBMS_OUTPUT.PUT_LINE('CustomerManagement.UpdateCustomerDetails: Customer ' ||
                                  p_customer_id || ' updated.');
        END IF;
    END UpdateCustomerDetails;

    FUNCTION GetCustomerBalance (
        p_customer_id IN Customers.CustomerID%TYPE
    ) RETURN NUMBER IS
        v_balance Customers.Balance%TYPE;
    BEGIN
        SELECT Balance INTO v_balance
        FROM Customers
        WHERE CustomerID = p_customer_id;
        RETURN v_balance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL;
    END GetCustomerBalance;

END CustomerManagement;
/



CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee (
        p_employee_id IN Employees.EmployeeID%TYPE,
        p_name        IN Employees.Name%TYPE,
        p_position    IN Employees.Position%TYPE,
        p_salary      IN Employees.Salary%TYPE,
        p_department  IN Employees.Department%TYPE
    );

    PROCEDURE UpdateEmployeeDetails (
        p_employee_id IN Employees.EmployeeID%TYPE,
        p_position    IN Employees.Position%TYPE DEFAULT NULL,
        p_salary      IN Employees.Salary%TYPE DEFAULT NULL,
        p_department  IN Employees.Department%TYPE DEFAULT NULL
    );

    FUNCTION CalculateAnnualSalary (
        p_employee_id IN Employees.EmployeeID%TYPE
    ) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

    PROCEDURE HireEmployee (
        p_employee_id IN Employees.EmployeeID%TYPE,
        p_name        IN Employees.Name%TYPE,
        p_position    IN Employees.Position%TYPE,
        p_salary      IN Employees.Salary%TYPE,
        p_department  IN Employees.Department%TYPE
    ) IS
    BEGIN
        INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
        VALUES (p_employee_id, p_name, p_position, p_salary, p_department, SYSDATE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('EmployeeManagement.HireEmployee: Hired ' || p_name ||
                              ' as ' || p_position || ' (ID ' || p_employee_id || ').');
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('EmployeeManagement.HireEmployee: Employee ID ' ||
                                  p_employee_id || ' already exists.');
    END HireEmployee;

    PROCEDURE UpdateEmployeeDetails (
        p_employee_id IN Employees.EmployeeID%TYPE,
        p_position    IN Employees.Position%TYPE DEFAULT NULL,
        p_salary      IN Employees.Salary%TYPE DEFAULT NULL,
        p_department  IN Employees.Department%TYPE DEFAULT NULL
    ) IS
    BEGIN
        UPDATE Employees
           SET Position   = NVL(p_position, Position),
               Salary     = NVL(p_salary, Salary),
               Department = NVL(p_department, Department)
         WHERE EmployeeID = p_employee_id;

        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('EmployeeManagement.UpdateEmployeeDetails: Employee ID ' ||
                                  p_employee_id || ' not found.');
        ELSE
            COMMIT;
            DBMS_OUTPUT.PUT_LINE('EmployeeManagement.UpdateEmployeeDetails: Employee ' ||
                                  p_employee_id || ' updated.');
        END IF;
    END UpdateEmployeeDetails;

    FUNCTION CalculateAnnualSalary (
        p_employee_id IN Employees.EmployeeID%TYPE
    ) RETURN NUMBER IS
        v_monthly Employees.Salary%TYPE;
    BEGIN
        SELECT Salary INTO v_monthly
        FROM Employees
        WHERE EmployeeID = p_employee_id;
        RETURN v_monthly * 12;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL;
    END CalculateAnnualSalary;

END EmployeeManagement;
/



CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount (
        p_account_id   IN Accounts.AccountID%TYPE,
        p_customer_id  IN Accounts.CustomerID%TYPE,
        p_account_type IN Accounts.AccountType%TYPE,
        p_balance      IN Accounts.Balance%TYPE
    );

    PROCEDURE CloseAccount (
        p_account_id IN Accounts.AccountID%TYPE
    );

    FUNCTION GetTotalBalance (
        p_customer_id IN Customers.CustomerID%TYPE
    ) RETURN NUMBER;
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS

    PROCEDURE OpenAccount (
        p_account_id   IN Accounts.AccountID%TYPE,
        p_customer_id  IN Accounts.CustomerID%TYPE,
        p_account_type IN Accounts.AccountType%TYPE,
        p_balance      IN Accounts.Balance%TYPE
    ) IS
        v_dummy Customers.CustomerID%TYPE;
    BEGIN
        SELECT CustomerID INTO v_dummy FROM Customers WHERE CustomerID = p_customer_id;

        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
        VALUES (p_account_id, p_customer_id, p_account_type, p_balance, SYSDATE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('AccountOperations.OpenAccount: Opened ' || p_account_type ||
                              ' account ' || p_account_id || ' for customer ' || p_customer_id || '.');
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('AccountOperations.OpenAccount: Customer ' ||
                                  p_customer_id || ' does not exist.');
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('AccountOperations.OpenAccount: Account ID ' ||
                                  p_account_id || ' already exists.');
    END OpenAccount;

    PROCEDURE CloseAccount (
        p_account_id IN Accounts.AccountID%TYPE
    ) IS
    BEGIN
        DELETE FROM Accounts WHERE AccountID = p_account_id;
        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('AccountOperations.CloseAccount: Account ID ' ||
                                  p_account_id || ' not found.');
        ELSE
            COMMIT;
            DBMS_OUTPUT.PUT_LINE('AccountOperations.CloseAccount: Account ' ||
                                  p_account_id || ' closed.');
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('AccountOperations.CloseAccount: Could not close account ' ||
                                  p_account_id || ' - ' || SQLERRM);
    END CloseAccount;

    FUNCTION GetTotalBalance (
        p_customer_id IN Customers.CustomerID%TYPE
    ) RETURN NUMBER IS
        v_total NUMBER := 0;
    BEGIN
        SELECT NVL(SUM(Balance), 0) INTO v_total
        FROM Accounts
        WHERE CustomerID = p_customer_id;
        RETURN v_total;
    END GetTotalBalance;

END AccountOperations;
/
