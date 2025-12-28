package com.raditha.bertie.testbed.statementremoval;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Gap 9: Duplicate Removal Logic Doesn't Account for Statement Position Changes
 *
 * These tests will FAIL if Bertie always removes from index 0 instead of using startOffset.
 *
 * CRITICAL: After refactoring, NON-DUPLICATE code must be preserved!
 * Tests verify that only the actual duplicate statements are removed.
 */
class ServiceWithDuplicatesAtDifferentPositionsTest {

    private ServiceWithDuplicatesAtDifferentPositions service;

    @BeforeEach
    void setUp() {
        service = new ServiceWithDuplicatesAtDifferentPositions();
    }

    // ===== Tests for duplicates at START =====

    @Test
    void processDuplicateAtStart_preservesNonDuplicateCode() {
        String result = service.processDuplicateAtStart();

        assertEquals("Start User", result);
        // If Bertie removes from wrong position, this might fail
    }

    @Test
    void processAnotherDuplicateAtStart_preservesNonDuplicateCode() {
        String result = service.processAnotherDuplicateAtStart();

        assertEquals("Start User 2", result);
    }

    // ===== Tests for duplicates in MIDDLE =====

    @Test
    void processDuplicateInMiddle_preservesCodeBeforeAndAfter() {
        String result = service.processDuplicateInMiddle();

        assertEquals("Middle:Middle User", result);

        // CRITICAL: This will FAIL if Bertie removes from index 0!
        // If it removes from index 0, it will delete:
        //   String prefix = "Middle:";  ← WRONG!
        // Instead of deleting:
        //   User user = new User(); ... (the actual duplicate)

        assertTrue(result.startsWith("Middle:"),
            "MUST preserve 'String prefix' statement - it's NOT duplicate!");
    }

    @Test
    void processAnotherDuplicateInMiddle_preservesCodeBeforeAndAfter() {
        String result = service.processAnotherDuplicateInMiddle();

        assertEquals("Middle2:Middle User 2", result);

        assertTrue(result.startsWith("Middle2:"),
            "MUST preserve prefix statement!");
    }

    // ===== Tests for duplicates at END =====

    @Test
    void processDuplicateAtEnd_preservesCodeBefore() {
        String result = service.processDuplicateAtEnd();

        assertEquals("Processing...: End User", result);

        // CRITICAL: This will FAIL if Bertie removes from index 0!
        // If it removes from index 0, it will delete:
        //   String message = "Processing...";  ← WRONG!
        // Instead of deleting the duplicate at the end

        assertTrue(result.startsWith("Processing..."),
            "MUST preserve message and counter statements - they're NOT duplicate!");
    }

    @Test
    void processAnotherDuplicateAtEnd_preservesCodeBefore() {
        String result = service.processAnotherDuplicateAtEnd();

        assertEquals("Processing again...: End User 2", result);

        assertTrue(result.startsWith("Processing again..."),
            "MUST preserve message statement!");
    }

    // ===== Integration tests =====

    @Test
    void allMethods_nonDuplicateCodePreserved() {
        // Run all methods and verify each produces correct result

        String start = service.processDuplicateAtStart();
        assertEquals("Start User", start,
            "Start method must preserve email assignment");

        String middle = service.processDuplicateInMiddle();
        assertEquals("Middle:Middle User", middle,
            "Middle method must preserve prefix AND suffix code");

        String end = service.processDuplicateAtEnd();
        assertEquals("Processing...: End User", end,
            "End method must preserve message and counter initialization");
    }

    @Test
    void positionMatters() {
        // This test explicitly verifies that position detection is working

        String middle = service.processDuplicateInMiddle();

        // If position detection fails, the result will be wrong
        // because the wrong statements were removed
        assertNotEquals(":Middle User", middle,
            "Should NOT have removed the prefix - it's at a different position!");
        assertNotEquals("MiddleMiddle User", middle,
            "Should NOT have removed the colon separator!");
    }
}

