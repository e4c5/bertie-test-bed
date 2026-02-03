package com.raditha.bertie.testbed.variablecapture;

import java.io.PrintStream;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.mockito.Mockito;
import org.mockito.Mock;
import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VariableCaptureEdgeCasesAKTest {

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
     * Method under test: VariableCaptureEdgeCases.modifyArrayElement2()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void modifyArrayElement2Test() {
        VariableCaptureEdgeCases variableCaptureEdgeCases = new VariableCaptureEdgeCases();
        variableCaptureEdgeCases.modifyArrayElement2();
        assertEquals("50", outputStream.toString().trim());
    }

    /**
     * Method under test: VariableCaptureEdgeCases.modifyArrayElement()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void modifyArrayElementTest() {
        VariableCaptureEdgeCases variableCaptureEdgeCases = new VariableCaptureEdgeCases();
        variableCaptureEdgeCases.modifyArrayElement();
        assertEquals("10", outputStream.toString().trim());
    }

    /**
     * Method under test: VariableCaptureEdgeCases.modifyObjectField2()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void modifyObjectField2Test() {
        VariableCaptureEdgeCases variableCaptureEdgeCases = new VariableCaptureEdgeCases();
        variableCaptureEdgeCases.modifyObjectField2();
        assertEquals("changed", outputStream.toString().trim());
    }

    /**
     * Method under test: VariableCaptureEdgeCases.modifyObjectField()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void modifyObjectFieldTest() {
        VariableCaptureEdgeCases variableCaptureEdgeCases = new VariableCaptureEdgeCases();
        variableCaptureEdgeCases.modifyObjectField();
        assertEquals("modified", outputStream.toString().trim());
    }

    /**
     * Method under test: VariableCaptureEdgeCases.modifyParameter2()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void modifyParameter2Test() {
        VariableCaptureEdgeCases variableCaptureEdgeCases = new VariableCaptureEdgeCases();
        int amount = 0;
        variableCaptureEdgeCases.modifyParameter2(amount);
        assertEquals("Modified: 0", outputStream.toString().trim());
    }

    /**
     * Method under test: VariableCaptureEdgeCases.modifyParameter()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void modifyParameterTest() {
        VariableCaptureEdgeCases variableCaptureEdgeCases = new VariableCaptureEdgeCases();
        int value = 0;
        variableCaptureEdgeCases.modifyParameter(value);
        assertEquals("Modified: 1", outputStream.toString().trim());
    }

    /**
     * Method under test: VariableCaptureEdgeCases.processNestedScopes2()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void processNestedScopes2Test() {
        VariableCaptureEdgeCases variableCaptureEdgeCases = new VariableCaptureEdgeCases();
        variableCaptureEdgeCases.processNestedScopes2();
        assertEquals("0\n1\n3\n6\n10\n15\n21\n28\n36\n45\nFinal: 45", outputStream.toString().trim());
    }

    /**
     * Method under test: VariableCaptureEdgeCases.processNestedScopes()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void processNestedScopesTest() {
        VariableCaptureEdgeCases variableCaptureEdgeCases = new VariableCaptureEdgeCases();
        variableCaptureEdgeCases.processNestedScopes();
        assertEquals("1\n2\n3\n4\n5\nFinal: 5", outputStream.toString().trim());
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
