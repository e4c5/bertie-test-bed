package com.raditha.bertie.testbed.testisolation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Gap 6: Immutable Objects (SAFE to promote)
 *
 * These tests should PASS even after Bertie promotes immutable values to fields.
 * Demonstrates the difference between safe (immutable) and unsafe (mutable) promotion.
 */
class ServiceWithImmutableConfigurationTest {

    private ServiceWithImmutableConfiguration service;

    @BeforeEach
    void setUp() {
        service = new ServiceWithImmutableConfiguration();
    }

    @Test
    void processApiCall_usesCorrectValues() {
        String result = service.processApiCall();

        assertEquals("API: https://api.example.com/v1, Timeout: 30, SSL: true", result);
        assertTrue(result.contains("https://api.example.com"));
    }

    @Test
    void calculateTimeout_usesCorrectValues() {
        int result = service.calculateTimeout();

        assertEquals(90, result, "Timeout * retryCount should be 90");
    }

    @Test
    void validateSecuritySettings_usesCorrectValues() {
        boolean result = service.validateSecuritySettings();

        assertTrue(result, "SSL should be enabled and URL should be HTTPS with Bearer token");
    }

    @Test
    void multipleCallsProduceSameResult() {
        // Immutables should always give same results
        String result1 = service.processApiCall();
        String result2 = service.processApiCall();

        assertEquals(result1, result2, "Immutable values should produce identical results");
    }
}

