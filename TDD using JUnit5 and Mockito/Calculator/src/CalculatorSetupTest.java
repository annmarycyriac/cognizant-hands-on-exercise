package com.cognizant.junitbasics;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CalculatorSetupTest {

    @Test
    public void testJUnitIsWorking() {
        Calculator calculator = new Calculator();
        int result = calculator.add(2, 3);
        assertEquals(5, result);
    }
}
