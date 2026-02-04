package com.raditha.bertie.testbed.aquarium.service;

import java.io.PrintStream;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.mockito.Mockito;
import org.mockito.Mock;
import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConstructorReuseServiceAKTest {

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
     * Method under test: ConstructorReuseService.ConstructorReuseService()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void ConstructorTest() {
        String name = "Antikythera";
        new ConstructorReuseService(name);
        assertTrue( outputStream.toString().contains("Initialized ConstructorReuseService with defaults"));
    }

    /**
     * Method under test: ConstructorReuseService.ConstructorReuseService()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void DefaultConstructorTest() {
        new ConstructorReuseService();
        assertTrue(outputStream.toString().trim().contains("Initialized ConstructorReuseService with defaults"));
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
