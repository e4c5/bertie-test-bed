package com.raditha.bertie.testbed.food;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for RestaurantOrderProcessor to verify financial calculations
 * and order processing flows.
 */
class RestaurantOrderProcessorTest {

    private RestaurantOrderProcessor processor;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        processor = new RestaurantOrderProcessor();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testProcessDineInOrder_CalculatesCorrectTotal() {
        // subtotal 100 + 10 service charge = 110. + 5% tax = 115.5
        processor.processDineInOrder(100.0, "T1", false);
        assertTrue(outputStreamCaptor.toString().contains("Final amount calculated: $115.5"));
    }

    @Test
    void testProcessDineInOrder_VIP_AppliesDiscount() {
        // subtotal 100 + 10 service charge = 110. VIP 10% discount = 99. + 5% tax =
        // 103.95
        processor.processDineInOrder(100.0, "T1", true);
        assertTrue(outputStreamCaptor.toString().contains("Final amount calculated: $103.95"));
    }

    @Test
    void testProcessTakeoutOrder_AppliesFees() {
        // subtotal 50 + 0.50 bag fee = 50.5. + 5% tax = 53.025
        processor.processTakeoutOrder(50.0, true, false);
        assertTrue(outputStreamCaptor.toString().contains("Final amount calculated: $53.025"));
    }

    @Test
    void testProcessDeliveryOrder_CalculatesDistanceFee() {
        // subtotal 40 + (10km * 1.5) = 55. + 5% tax = 57.75
        processor.processDeliveryOrder(40.0, 10.0, false);
        assertTrue(outputStreamCaptor.toString().contains("Final amount calculated: $57.75"));
    }

    @Test
    void testProcessOrder_ThrowsExceptionForInvalidSubtotal() {
        assertThrows(IllegalArgumentException.class, () -> processor.processDineInOrder(-10.0, "T5", false));
    }
}
