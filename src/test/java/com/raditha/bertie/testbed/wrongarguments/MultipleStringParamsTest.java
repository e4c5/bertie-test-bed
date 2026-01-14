package com.raditha.bertie.testbed.wrongarguments;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MultipleStringParamsTest {

    @Test
    void testProcessAlice() {
        MultipleStringParams service = new MultipleStringParams();
        assertDoesNotThrow(() -> service.processAlice());
    }

    @Test
    void testProcessBob() {
        MultipleStringParams service = new MultipleStringParams();
        assertDoesNotThrow(() -> service.processBob());
    }
}

