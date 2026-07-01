package com.cognizant.junitbasics;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Exercise 3: Assertions in JUnit
 * ------------------------------------------------------------------
 * Demonstrates the core JUnit assertion methods used to validate
 * test results: assertEquals, assertTrue, assertFalse, assertNull,
 * and assertNotNull.
 */
public class AssertionsTest {

    @Test
    public void testAssertions() {
        // Assert equals
        assertEquals(5, 2 + 3);

        // Assert true
        assertTrue(5 > 3);

        // Assert false
        assertFalse(5 < 3);

        // Assert null
        assertNull(null);

        // Assert not null
        assertNotNull(new Object());
    }

    @Test
    public void testMoreAssertions() {
        // assertEquals with a delta, for floating point comparisons
        assertEquals(0.3, 0.1 + 0.2, 0.0001);

        // assertSame checks reference equality
        String s1 = "hello";
        String s2 = s1;
        assertSame(s1, s2);

        // assertNotSame checks reference inequality
        String s3 = new String("hello");
        assertNotSame(s1, s3);

        // assertArrayEquals checks array contents
        int[] expected = {1, 2, 3};
        int[] actual = {1, 2, 3};
        assertArrayEquals(expected, actual);
    }
}
