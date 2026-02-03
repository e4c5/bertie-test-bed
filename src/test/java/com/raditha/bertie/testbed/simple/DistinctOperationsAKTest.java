package com.raditha.bertie.testbed.simple;

import java.io.PrintStream;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.mockito.Mockito;
import org.mockito.Mock;
import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DistinctOperationsAKTest {

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
     * Method under test: DistinctOperations.method1()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void method1Test() {
        DistinctOperations distinctOperations = new DistinctOperations();
        distinctOperations.method1();
        assertEquals("15", outputStream.toString().trim());
    }

    /**
     * Method under test: DistinctOperations.method2()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void method2Test() {
        DistinctOperations distinctOperations = new DistinctOperations();
        distinctOperations.method2();
        assertEquals("helloworld", outputStream.toString().trim());
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
