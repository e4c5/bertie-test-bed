package com.raditha.bertie.testbed.partial;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConstructorDuplicatesTest {

    @Test
    void testDefaultConstructor() {
        assertDoesNotThrow(() -> new ConstructorDuplicates());
    }

    @Test
    void testNameConstructor() {
        assertDoesNotThrow(() -> new ConstructorDuplicates("test"));
    }

    @Test
    void testCapacityConstructor() {
        assertDoesNotThrow(() -> new ConstructorDuplicates(10));
    }

    @Test
    void testFactorConstructor() {
        assertDoesNotThrow(() -> new ConstructorDuplicates(1.5));
    }
}
