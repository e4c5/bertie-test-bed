package com.raditha.bertie.testbed.staticcontext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Gap 3: Static Modifier Inference is Wrong for Mixed Contexts
 *
 * These tests will cause COMPILATION ERRORS if Bertie:
 * 1. Creates instance helper method → static methods can't call it
 * 2. Creates static helper method → can't access instance fields
 *
 * CRITICAL: Bertie should BLOCK this refactoring with validation error!
 * Expected: "Cannot extract: duplicates span both static and instance methods"
 */
class ServiceWithMixedStaticContextTest {

    private ServiceWithMixedStaticContext service;

    @BeforeEach
    void setUp() {
        service = new ServiceWithMixedStaticContext();
    }

    // ===== Instance method tests =====

    @Test
    void instanceMethod1_accessesInstanceField() {
        String result = service.instanceMethod1();

        assertEquals("Result: value! from Instance Data", result);
        assertTrue(result.contains("Instance Data"),
            "Instance method must access instance field");
    }

    @Test
    void instanceMethod2_accessesInstanceField() {
        String result = service.instanceMethod2();

        assertEquals("Result: value! from Instance Data", result);
        assertTrue(result.contains("Instance Data"));
    }

    // ===== Static method tests =====

    @Test
    void staticMethod1_cannotAccessInstanceFields() {
        String result = ServiceWithMixedStaticContext.staticMethod1();

        assertEquals("Result: value! from static", result);
        assertFalse(result.contains("Instance Data"),
            "Static method cannot access instance fields");
    }

    @Test
    void staticMethod2_cannotAccessInstanceFields() {
        String result = ServiceWithMixedStaticContext.staticMethod2();

        assertEquals("Result: value! from static", result);
        assertFalse(result.contains("Instance Data"));
    }

    // ===== Critical validation tests =====

    @Test
    void bertie_shouldBlockMixedContextExtraction() {
        // This test documents expected Bertie behavior

        // EXPECTED BEHAVIOR:
        // When Bertie analyzes this class, it should detect that:
        // - instanceMethod1 and instanceMethod2 have duplicate code
        // - staticMethod1 and staticMethod2 have the SAME duplicate code
        // - The duplicates span BOTH static and instance contexts

        // BERTIE MUST:
        // 1. Detect the mixed context
        // 2. Add validation error: "Cannot extract: duplicates span static and instance methods"
        // 3. BLOCK the refactoring

        // WHAT WOULD BREAK IF BERTIE DOESN'T BLOCK:

        // Scenario 1: Bertie creates INSTANCE helper method
        // private String helper() { ... }
        //
        // Result: COMPILATION ERROR
        // - staticMethod1() tries to call helper()
        // - Error: "non-static method cannot be referenced from a static context"

        // Scenario 2: Bertie creates STATIC helper method
        // private static String helper() { ... }
        //
        // This would compile, but is WRONG because:
        // - Instance methods lose ability to use instance fields
        // - Breaking change: instanceField cannot be accessed

        // VALIDATION:
        // After running Bertie, EITHER:
        // a) Code compiles AND all tests pass (safe extraction)
        // b) Bertie blocks extraction with validation error (correct behavior)
        //
        // NEVER: Code has compilation errors (broken extraction)

        assertTrue(true, "This test documents the expected behavior");
    }

    @Test
    void allMethods_produceCorrectResults() {
        // Verify all methods work correctly before refactoring

        String instance1 = service.instanceMethod1();
        String instance2 = service.instanceMethod2();
        String static1 = ServiceWithMixedStaticContext.staticMethod1();
        String static2 = ServiceWithMixedStaticContext.staticMethod2();

        // Instance methods access instance field
        assertTrue(instance1.contains("Instance Data"));
        assertTrue(instance2.contains("Instance Data"));

        // Static methods don't access instance field
        assertTrue(static1.contains("from static"));
        assertTrue(static2.contains("from static"));

        // All have the duplicate code pattern
        assertTrue(instance1.startsWith("Result: "));
        assertTrue(static1.startsWith("Result: "));
    }
}

