package com.raditha.bertie.testbed.filter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShapeVariationsTest {

    @Test
    void testCalculateIntegers() {
        ShapeVariations shapeVariations = new ShapeVariations();
        assertDoesNotThrow(() -> shapeVariations.calculateIntegers());
    }

    @Test
    void testCalculateMoreIntegers() {
        ShapeVariations shapeVariations = new ShapeVariations();
        assertDoesNotThrow(() -> shapeVariations.calculateMoreIntegers());
    }

    @Test
    void testProcessStrings() {
        ShapeVariations shapeVariations = new ShapeVariations();
        assertDoesNotThrow(() -> shapeVariations.processStrings());
    }
}

