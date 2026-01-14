package com.raditha.bertie.testbed.partial;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MixedResponsibilityServiceTest {

    @Test
    void testCreateUser() {
        MixedResponsibilityService service = new MixedResponsibilityService();
        assertDoesNotThrow(() -> service.createUser());
    }

    @Test
    void testProcessRequest() {
        MixedResponsibilityService service = new MixedResponsibilityService();
        assertDoesNotThrow(() -> service.processRequest());
    }
}

