package com.raditha.bertie.testbed.food;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Basic execution tests for KitchenOperations.
 * These tests ensure the workflows run without errors.
 */
class KitchenOperationsTest {

    private KitchenOperations operations;

    @BeforeEach
    void setUp() {
        operations = new KitchenOperations();
    }

    @Test
    void testCookPasta_RunsSuccessfully() {
        assertDoesNotThrow(() -> operations.cookPasta("Carbonara", 200));
    }

    @Test
    void testCookSoup_RunsSuccessfully() {
        assertDoesNotThrow(() -> operations.cookSoup("Tomato", 500));
    }

    @Test
    void testGrillSteak_RunsSuccessfully() {
        assertDoesNotThrow(() -> operations.grillSteak("Ribeye", "Medium Rare"));
    }
}
