package com.raditha.bertie.testbed.wrongarguments;

import java.io.PrintStream;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.mockito.Mockito;
import org.mockito.Mock;
import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MultipleStringParamsAKTest {

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
     * Method under test: MultipleStringParams.processAlice()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void processAliceTest() {
        MultipleStringParams multipleStringParams = new MultipleStringParams();
        multipleStringParams.processAlice();
        assertEquals("Processing Alice\nSending email to alice@example.com", outputStream.toString().trim());
    }

    /**
     * Method under test: MultipleStringParams.processBob()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void processBobTest() {
        MultipleStringParams multipleStringParams = new MultipleStringParams();
        multipleStringParams.processBob();
        assertEquals("Processing Bob\nSending email to bob@example.com", outputStream.toString().trim());
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
