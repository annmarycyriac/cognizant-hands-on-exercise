package com.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Exercise 1: Mocking and Stubbing.
 *
 * Demonstrates creating a mock of ExternalApi, stubbing its getData() method
 * to return a predefined value, and verifying that MyService returns that
 * stubbed value.
 */
public class MyServiceTest {

    @Test
    public void testExternalApi() {
        // 1. Create a mock object for the external API.
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // 2. Stub the method to return a predefined value.
        when(mockApi.getData()).thenReturn("Mock Data");

        // 3. Write a test case that uses the mock object.
        MyService service = new MyService(mockApi);
        String result = service.fetchData();

        assertEquals("Mock Data", result);
    }
}
