package com.raditha.bertie.testbed.filter;

import java.io.PrintStream;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.mockito.Mockito;
import org.mockito.Mock;
import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShapeVariationsAKTest {

    private PrintStream originalOut;

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    /**
     * Author : Antikythera
     */
    @BeforeEach()
    void setUp() {
        MockitoAnnotations.openMocks(this);
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * Method under test: ShapeVariations.calculateIntegers()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void calculateIntegersTest() {
        ShapeVariations shapeVariations = new ShapeVariations();
        shapeVariations.calculateIntegers();
        assertEquals("15", outputStream.toString().trim());
    }

    /**
     * Method under test: ShapeVariations.calculateMoreIntegers()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void calculateMoreIntegersTest() {
        ShapeVariations shapeVariations = new ShapeVariations();
        shapeVariations.calculateMoreIntegers();
        assertEquals("15", outputStream.toString().trim());
    }

    /**
     * Method under test: ShapeVariations.processStrings()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void processStringsTest() {
        ShapeVariations shapeVariations = new ShapeVariations();
        shapeVariations.processStrings();
        assertEquals("helloworldtestexampledone", outputStream.toString().trim());
    }

    /**
     * Author : Antikythera
     */
    @AfterEach()
    void tearDown() {
        System.setOut(originalOut);
        outputStream.reset();
    }
}
