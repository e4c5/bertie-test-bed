package com.raditha.bertie.testbed.callsite;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServiceWithObjectCreationTest {

    @Test
    void testProcessIncome1() {
        ServiceWithObjectCreation service = new ServiceWithObjectCreation();
        // Should not throw exception
        assertDoesNotThrow(() -> service.processIncome1());
    }

    @Test
    void testProcessIncome2() {
        ServiceWithObjectCreation service = new ServiceWithObjectCreation();
        // Should not throw exception
        assertDoesNotThrow(() -> service.processIncome2());
    }

    @Test
    void testProcessIncome3() {
        ServiceWithObjectCreation service = new ServiceWithObjectCreation();
        // Should not throw exception
        assertDoesNotThrow(() -> service.processIncome3());
    }
}

