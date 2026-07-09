package com.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ArgumentMatchingTest {

    @Test
    public void testArgumentMatching() {
        // 1. Create a mock object.
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

       
        when(mockApi.getDataById(eq("123"))).thenReturn("Data for 123");

        MyService service = new MyService(mockApi);

        // 2. Call the method with specific arguments.
        String result = service.fetchDataById("123");

        assertEquals("Data for 123", result);

        // 3. Use argument matchers to verify the interaction.
        verify(mockApi).getDataById(eq("123"));
        verify(mockApi).getDataById(anyString());
    }
}
