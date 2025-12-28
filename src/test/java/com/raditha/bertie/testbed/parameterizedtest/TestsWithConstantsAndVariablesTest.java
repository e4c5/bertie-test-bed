package com.raditha.bertie.testbed.parameterizedtest;

import com.raditha.bertie.testbed.model.User;
import com.raditha.bertie.testbed.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Tests for Gap 7: Parameterized Test Extraction Uses Wrong Literal Values
 * 
 * These tests will FAIL if Bertie parameterizes constant values that shouldn't
 * vary.
 * 
 * CRITICAL: After refactoring to @ParameterizedTest:
 * - Names and ages should be parameterized (they vary)
 * - Status codes (200) should remain CONSTANT (they don't vary)
 * - Timeouts (5000) should remain CONSTANT (they don't vary)
 * 
 * If Bertie over-parameterizes, tests become less readable and may fail.
 */
class TestsWithConstantsAndVariablesTest {

    private Repository<User> repository;
    private TestsWithConstantsAndVariables service;

    @SuppressWarnings("unchecked")
    @BeforeEach
    void setUp() {
        repository = mock(Repository.class);
        service = new TestsWithConstantsAndVariables(repository);
    }

    @Test
    void testUserJohn_uses200Status() {
        // Mock the repository to return saved user
        when(repository.save(any(User.class))).thenAnswer(inv -> {
            User u = inv.getArgument(0);
            u.setId("user1");
            return u;
        });

        int status = service.testUserJohn();

        assertEquals(200, status, "MUST return HTTP 200 status - this is a CONSTANT!");

        // Verify the user was saved correctly
        verify(repository).save(argThat(u -> "John".equals(u.getName()) && u.getAge() == 25));

        // CRITICAL: Status code 200 should NOT become a parameter
        // It's the same across all tests - it's a constant!
    }

    @Test
    void testUserJane_uses200Status() {
        when(repository.save(any(User.class))).thenAnswer(inv -> {
            User u = inv.getArgument(0);
            u.setId("user2");
            return u;
        });

        int status = service.testUserJane();

        assertEquals(200, status, "MUST return HTTP 200 status - this is a CONSTANT!");

        verify(repository).save(argThat(u -> "Jane".equals(u.getName()) && u.getAge() == 30));

        // Same constant value as testUserJohn!
    }

    @Test
    void testUserBob_uses200Status() {
        when(repository.save(any(User.class))).thenAnswer(inv -> {
            User u = inv.getArgument(0);
            u.setId("user3");
            return u;
        });

        int status = service.testUserBob();

        assertEquals(200, status, "MUST return HTTP 200 status - this is a CONSTANT!");

        verify(repository).save(argThat(u -> "Bob".equals(u.getName()) && u.getAge() == 35));

        // Same constant value as other tests!
    }

    @Test
    void testAllUsersGet200Status() {
        // Integration test: verify that the constant value is truly constant

        when(repository.save(any(User.class))).thenAnswer(inv -> {
            User u = inv.getArgument(0);
            u.setId("id-" + u.getName());
            return u;
        });

        int status1 = service.testUserJohn();
        int status2 = service.testUserJane();
        int status3 = service.testUserBob();

        // All should return the SAME constant
        assertEquals(200, status1);
        assertEquals(200, status2);
        assertEquals(200, status3);

        assertEquals(status1, status2, "Status codes should be CONSTANT - not parameterized!");
        assertEquals(status2, status3, "Status codes should be CONSTANT - not parameterized!");

        // CRITICAL: If Bertie parameterizes status code, it makes tests overly generic
        // The status code is ALWAYS 200 - it's a magic constant that should stay!
    }

    @Test
    void testTimeoutConstant_notParameterized() {
        when(repository.save(any(User.class))).thenAnswer(inv -> {
            User u = inv.getArgument(0);
            u.setId("id-" + u.getName());
            return u;
        });

        long timeout1 = service.testUserAliceWithTimeout();
        long timeout2 = service.testUserCharlieWithTimeout();

        // Both should return the SAME timeout constant
        assertEquals(5000, timeout1, "Timeout should be 5000ms constant");
        assertEquals(5000, timeout2, "Timeout should be 5000ms constant");
        assertEquals(timeout1, timeout2, "Timeouts should be CONSTANT across tests");

        // CRITICAL: Timeout value (5000) is a constant that should NOT be parameterized
        // Only the user names and ages vary - the timeout is always the same!
    }
}
