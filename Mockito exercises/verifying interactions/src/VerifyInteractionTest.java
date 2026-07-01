package com.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

/**
 * Exercise 2: Verifying Interactions.
 *
 * Demonstrates verifying that a mock's method was actually called,
 * rather than just checking a return value.
 */
public class VerifyInteractionTest {

    @Test
    public void testVerifyInteraction() {
        // 1. Create a mock object.
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        // 2. Call the method with specific arguments (no-arg here).
        service.fetchData();

        // 3. Verify the interaction: getData() was called exactly once.
        verify(mockApi).getData();
    }
}
