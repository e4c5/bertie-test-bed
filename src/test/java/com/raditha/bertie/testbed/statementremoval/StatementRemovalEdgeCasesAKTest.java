package com.raditha.bertie.testbed.statementremoval;

import java.io.PrintStream;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.mockito.Mockito;
import org.mockito.Mock;
import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StatementRemovalEdgeCasesAKTest {

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
     * Method under test: StatementRemovalEdgeCases.processCustomerAtEnd()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void processCustomerAtEndTest() {
        StatementRemovalEdgeCases statementRemovalEdgeCases = new StatementRemovalEdgeCases();
        statementRemovalEdgeCases.processCustomerAtEnd();
        assertEquals("User{id='null\', name='Second\', email='second@example.com\', active=true, role='null\', age=0, loginCount=0}", outputStream.toString().trim());
    }

    /**
     * Method under test: StatementRemovalEdgeCases.processCustomerInNestedBlock()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void processCustomerInNestedBlockTest() {
        StatementRemovalEdgeCases statementRemovalEdgeCases = new StatementRemovalEdgeCases();
        boolean condition = false;
        assertDoesNotThrow(() -> statementRemovalEdgeCases.processCustomerInNestedBlock(condition));
    }

    /**
     * Method under test: StatementRemovalEdgeCases.processCustomerInNestedBlock()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void processCustomerInNestedBlockTest_F() {
        StatementRemovalEdgeCases statementRemovalEdgeCases = new StatementRemovalEdgeCases();
        boolean condition = true;
        statementRemovalEdgeCases.processCustomerInNestedBlock(condition);
        assertEquals("User{id='null\', name='NestedCustomer\', email='nested@example.com\', active=true, role='null\', age=0, loginCount=0}", outputStream.toString().trim());
    }

    /**
     * Method under test: StatementRemovalEdgeCases.processUserAtEnd()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void processUserAtEndTest() {
        StatementRemovalEdgeCases statementRemovalEdgeCases = new StatementRemovalEdgeCases();
        statementRemovalEdgeCases.processUserAtEnd();
        assertEquals("User{id='null\', name='First\', email='first@example.com\', active=true, role='null\', age=0, loginCount=0}", outputStream.toString().trim());
    }

    /**
     * Method under test: StatementRemovalEdgeCases.processUserInNestedBlock()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void processUserInNestedBlockTest() {
        StatementRemovalEdgeCases statementRemovalEdgeCases = new StatementRemovalEdgeCases();
        boolean condition = false;
        assertDoesNotThrow(() -> statementRemovalEdgeCases.processUserInNestedBlock(condition));
    }

    /**
     * Method under test: StatementRemovalEdgeCases.processUserInNestedBlock()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void processUserInNestedBlockTest_C() {
        StatementRemovalEdgeCases statementRemovalEdgeCases = new StatementRemovalEdgeCases();
        boolean condition = true;
        statementRemovalEdgeCases.processUserInNestedBlock(condition);
        assertEquals("User{id='null\', name='Nested\', email='nested@example.com\', active=true, role='null\', age=0, loginCount=0}", outputStream.toString().trim());
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
