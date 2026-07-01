package com.cognizant.junitbasics;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Exercise 4: Arrange-Act-Assert (AAA) Pattern, Test Fixtures,
 * Setup and Teardown Methods in JUnit
 * ------------------------------------------------------------------
 * Demonstrates:
 *   - The AAA (Arrange-Act-Assert) pattern for structuring each test
 *   - @Before: runs before EVERY test method (setup / fixture creation)
 *   - @After:  runs after EVERY test method (teardown / cleanup)
 *
 * Console print statements are included in setUp()/tearDown() and each
 * test purely so the execution order is visible in the output; this is
 * for demonstration only and would normally be omitted or replaced with
 * logging in real test suites.
 */
public class BankAccountTest {

    private BankAccount account;

    @Before
    public void setUp() {
        // Runs before each test method - creates a fresh fixture
        account = new BankAccount(100.0);
        System.out.println("[@Before] setUp() - fresh BankAccount created with balance 100.0");
    }

    @After
    public void tearDown() {
        // Runs after each test method - cleans up the fixture
        account = null;
        System.out.println("[@After] tearDown() - account reference cleared");
    }

    @Test
    public void testDeposit() {
        System.out.println("[Test] testDeposit()");

        // Arrange
        double depositAmount = 50.0;

        // Act
        account.deposit(depositAmount);

        // Assert
        assertEquals(150.0, account.getBalance(), 0.001);
    }

    @Test
    public void testWithdraw() {
        System.out.println("[Test] testWithdraw()");

        // Arrange
        double withdrawAmount = 30.0;

        // Act
        account.withdraw(withdrawAmount);

        // Assert
        assertEquals(70.0, account.getBalance(), 0.001);
    }

    @Test
    public void testWithdrawInsufficientFundsThrows() {
        System.out.println("[Test] testWithdrawInsufficientFundsThrows()");

        // Arrange
        double withdrawAmount = 1000.0; // more than the 100.0 balance

        // Act & Assert
        try {
            account.withdraw(withdrawAmount);
            org.junit.Assert.fail("Expected IllegalStateException was not thrown");
        } catch (IllegalStateException e) {
            assertEquals("Insufficient funds", e.getMessage());
        }
    }

    @Test
    public void testDepositThenWithdraw() {
        System.out.println("[Test] testDepositThenWithdraw()");

        // Arrange
        double depositAmount = 25.0;
        double withdrawAmount = 40.0;

        // Act
        account.deposit(depositAmount);   // balance: 125.0
        account.withdraw(withdrawAmount); // balance: 85.0

        // Assert
        assertEquals(85.0, account.getBalance(), 0.001);
    }
}
