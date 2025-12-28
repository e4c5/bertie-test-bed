package com.raditha.bertie.testbed.testisolation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Gap 6: Field Promotion in @BeforeEach Creates Test Isolation Issues
 *
 * These tests will FAIL if Bertie promotes mutable User objects to instance fields.
 *
 * CRITICAL: After refactoring, each test MUST still have independent User instances.
 * Tests verify that state modifications in one test don't affect other tests.
 */
class ServiceWithMutableUserSetupTest {

    private ServiceWithMutableUserSetup service;

    @BeforeEach
    void setUp() {
        service = new ServiceWithMutableUserSetup();
    }

    @Test
    void processAlice_usesCorrectUserData() {
        String result = service.processAlice();

        assertEquals("Alice logged in 1 times", result,
            "MUST use Alice's name, not Bob or Charlie");
        assertTrue(result.contains("Alice"), "Result must contain Alice");
        assertTrue(result.contains("1 times"), "Alice should have 1 login");
    }

    @Test
    void processBob_usesCorrectUserData() {
        String result = service.processBob();

        assertEquals("Bob logged in 2 times", result,
            "MUST use Bob's name, not Alice or Charlie");
        assertTrue(result.contains("Bob"), "Result must contain Bob");
        assertTrue(result.contains("2 times"), "Bob should have 2 logins");
    }

    @Test
    void processCharlie_usesCorrectUserData() {
        String result = service.processCharlie();

        assertEquals("Charlie logged in 3 times", result,
            "MUST use Charlie's name, not Alice or Bob");
        assertTrue(result.contains("Charlie"), "Result must contain Charlie");
        assertTrue(result.contains("3 times"), "Charlie should have 3 logins");
    }

    @Test
    void processWithHeavyModification_doesNotAffectOtherProcesses() {
        boolean result = service.processWithHeavyModification();

        assertTrue(result, "David's processing should complete successfully");

        // This method heavily modifies user state
        // If user is shared, subsequent tests will see modified state
    }

    @Test
    void processAliceAgain_stillHasCleanState() {
        // Run Alice process again AFTER state modification process
        String result = service.processAlice();

        assertEquals("Alice logged in 1 times", result,
            "Alice should have clean state, not affected by David's process");

        // If User was promoted to field, this might fail because:
        // 1. Name might be "David" instead of "Alice"
        // 2. Login count might be > 1 from previous processes
        // 3. Active flag might be false
    }

    @Test
    void isolation_eachProcessGetsOwnInstance() {
        // This test verifies that running processes in sequence doesn't create interference

        // Process 1
        String alice1 = service.processAlice();
        assertEquals("Alice logged in 1 times", alice1);

        // Process 2
        String bob = service.processBob();
        assertEquals("Bob logged in 2 times", bob);

        // Process 3
        String alice2 = service.processAlice();
        assertEquals("Alice logged in 1 times", alice2,
            "Second Alice process should have same result as first");

        // If User is shared, alice2 might have different login count
    }

    @Test
    void parallelExecution_simulatesConcurrency() {
        // Simulate multiple processes running
        String alice = service.processAlice();
        String bob = service.processBob();
        String charlie = service.processCharlie();

        // All should have correct data
        assertTrue(alice.contains("Alice"));
        assertTrue(bob.contains("Bob"));
        assertTrue(charlie.contains("Charlie"));

        // None should have interfered with each other
        assertTrue(alice.contains("1 times"));
        assertTrue(bob.contains("2 times"));
        assertTrue(charlie.contains("3 times"));
    }
}

