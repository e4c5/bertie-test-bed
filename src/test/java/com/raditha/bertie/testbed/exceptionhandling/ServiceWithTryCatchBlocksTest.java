package com.raditha.bertie.testbed.exceptionhandling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Gap 4: Exception Handling Propagation is Incomplete
 * 
 * These tests will FAIL if Bertie incorrectly modifies exception declarations
 * after extracting methods.
 * 
 * CRITICAL: After refactoring, exception behavior must remain unchanged!
 * - Methods with try-catch should NOT gain "throws" clauses
 * - Methods that throw should keep their "throws" clauses
 * - Methods without exceptions should remain without "throws"
 */
class ServiceWithTryCatchBlocksTest {

    private ServiceWithTryCatchBlocks service;

    @BeforeEach
    void setUp() {
        service = new ServiceWithTryCatchBlocks();
    }

    @Test
    void testHandledExceptions_noThrowsClauseNeeded() {
        // These methods handle exceptions internally
        // They should NOT throw exceptions after refactoring!

        String result1 = service.processWithHandledExceptions("TEST_DATA");
        assertEquals("Processed: test-data", result1);

        String result2 = service.processMoreWithHandledExceptions("MORE_DATA");
        assertEquals("Processed: more-data", result2);

        // Test error case - should be handled, not thrown
        String errorResult = service.processWithHandledExceptions("  ");
        assertTrue(errorResult.startsWith("Error:"),
                "Empty data should be handled, not thrown");

        // CRITICAL: This test proves the method does NOT throw exceptions
        // If Bertie adds "throws IOException", this test would fail compilation
    }

    @Test
    void testUncheckedExceptions_throwsClauseRequired() throws IOException {
        // These methods throw checked exceptions
        // They MUST keep "throws IOException" after refactoring!

        boolean result1 = service.validateWithUncheckedExceptions("valid123");
        assertTrue(result1);

        boolean result2 = service.validateMoreWithUncheckedExceptions("test456");
        assertTrue(result2);

        // Test that exceptions are actually thrown
        assertThrows(IOException.class,
                () -> service.validateWithUncheckedExceptions("ab"),
                "Should throw IOException for data too short");

        assertThrows(IOException.class,
                () -> service.validateMoreWithUncheckedExceptions("invalid"),
                "Should throw IOException for invalid data");

        // CRITICAL: If Bertie removes "throws IOException", this test fails compilation
    }

    @Test
    void testNoExceptions_noThrowsClause() {
        // These methods have no exception handling
        // They should NOT have "throws" after refactoring!

        int[] numbers1 = { 1, 2, 3, 4, 5 };
        int result1 = service.calculateSum(numbers1);
        assertEquals(30, result1, "Sum of 1+2+3+4+5 = 15, doubled = 30");

        int[] numbers2 = { 2, 4, 6 };
        int result2 = service.calculateProduct(numbers2);
        assertEquals(240, result2, "Sum of 2+4+6 = 12, doubled = 24, * 10 = 240");

        // Test with negative numbers (filtered out)
        int[] mixed = { -1, 5, -3, 10 };
        int result3 = service.calculateSum(mixed);
        assertEquals(30, result3, "Only positive numbers: 5+10 = 15, doubled = 30");

        // CRITICAL: No "throws" clause should be added
        // This is pure computation with no I/O
    }

    @Test
    void testExceptionBehaviorPreserved() {
        // Integration test: verify all exception behaviors work correctly

        // 1. Handled exceptions return error messages
        String handled = service.processWithHandledExceptions("");
        assertTrue(handled.contains("Error"), "Handled exceptions return error messages");

        // 2. Unhandled exceptions are thrown
        assertThrows(IOException.class,
                () -> service.validateWithUncheckedExceptions("x"),
                "Unhandled exceptions are thrown");

        // 3. No exceptions case works normally
        int computed = service.calculateSum(new int[] { 10 });
        assertEquals(20, computed, "No exception code works normally");

        // CRITICAL: After refactoring, all three patterns must still work!
    }

    @Test
    void testCalculateWithTryCatch_normalCase() {
        // Test the normal execution path (try block)
        // The method has duplicate code in both try and catch blocks

        int[] numbers = { 1, 2, 3, 4, 5 };
        double result = service.calculateWithTryCatch(numbers);
        // Sum of 1+2+3+4+5 = 15, doubled = 30, * 10 = 300
        assertEquals(300.0, result, "Normal case: sum 15, doubled 30, * 10 = 300");

        // Test with only positive numbers
        int[] positiveOnly = { 10, 20, 30 };
        result = service.calculateWithTryCatch(positiveOnly);
        // Sum of 10+20+30 = 60, doubled = 120, * 10 = 1200
        assertEquals(1200.0, result, "Positive numbers: sum 60, doubled 120, * 10 = 1200");

        // Test with mixed positive and negative numbers
        int[] mixed = { -5, 3, -2, 7, 0, 4 };
        result = service.calculateWithTryCatch(mixed);
        // Only positive: 3+7+4 = 14, doubled = 28, * 10 = 280
        assertEquals(280.0, result, "Mixed numbers: only positive counted");

        // Test with all negative numbers (sum = 0)
        int[] allNegative = { -1, -2, -3 };
        result = service.calculateWithTryCatch(allNegative);
        // Sum = 0, doubled = 0, * 10 = 0
        assertEquals(0.0, result, "All negative: sum 0, doubled 0, * 10 = 0");

        // Test with empty array
        int[] empty = {};
        result = service.calculateWithTryCatch(empty);
        assertEquals(0.0, result, "Empty array: sum 0, doubled 0, * 10 = 0");

        // CRITICAL: After refactoring the duplicate code in try/catch blocks,
        // this method should still work correctly and NOT throw any exceptions
    }
}
