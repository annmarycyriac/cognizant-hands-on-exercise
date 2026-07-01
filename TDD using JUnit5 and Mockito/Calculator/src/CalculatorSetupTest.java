package com.cognizant.junitbasics;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Exercise 1: Setting Up JUnit
 * ------------------------------------------------------------------
 * This test class exists purely to confirm that the JUnit dependency
 * has been added correctly to the project (via Maven's pom.xml, see
 * pom.xml in this folder) and that a test class can be created,
 * compiled, and executed successfully.
 *
 * Maven dependency used (as specified in the exercise):
 *   <dependency>
 *       <groupId>junit</groupId>
 *       <artifactId>junit</artifactId>
 *       <version>4.13.2</version>
 *       <scope>test</scope>
 *   </dependency>
 */
public class CalculatorSetupTest {

    @Test
    public void testJUnitIsWorking() {
        Calculator calculator = new Calculator();
        int result = calculator.add(2, 3);
        assertEquals(5, result);
    }
}
