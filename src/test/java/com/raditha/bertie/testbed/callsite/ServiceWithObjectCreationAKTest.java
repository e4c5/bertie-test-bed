package com.raditha.bertie.testbed.callsite;

import java.io.PrintStream;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceWithObjectCreationAKTest {

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
     * Method under test: TaxDetails.getId()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void getIdTest() {
        ServiceWithObjectCreation.TaxDetails taxDetails = new ServiceWithObjectCreation.TaxDetails();
        int resp = taxDetails.getId();
        assertEquals(0, resp);
    }

    /**
     * Method under test: TaxDetails.getIncomeTypeIds()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void getIncomeTypeIdsTest() {
        ServiceWithObjectCreation.TaxDetails taxDetails = new ServiceWithObjectCreation.TaxDetails();
        Set<Integer> resp = taxDetails.getIncomeTypeIds();
        assertNull(resp);
    }

    /**
     * Method under test: ServiceWithObjectCreation.processIncome1()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void processIncome1Test() {
        ServiceWithObjectCreation serviceWithObjectCreation = new ServiceWithObjectCreation();
        serviceWithObjectCreation.processIncome1();
        assertEquals("Processing: TaxDetails{ids=[1, 3]}", outputStream.toString().trim());
    }

    /**
     * Method under test: ServiceWithObjectCreation.processIncome3()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void processIncome3Test() {
        ServiceWithObjectCreation serviceWithObjectCreation = new ServiceWithObjectCreation();
        serviceWithObjectCreation.processIncome3();
        assertEquals("2", outputStream.toString().trim());
    }

    /**
     * Author : Antikythera
     */
    @AfterEach()
    void tearDown() {
        System.setOut(originalOut);
        outputStream.reset();
    }

    /**
     * Method under test: TaxDetails.toString()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void toStringTest() {
        ServiceWithObjectCreation.TaxDetails taxDetails = new ServiceWithObjectCreation.TaxDetails();
        String resp = taxDetails.toString();
        assertEquals("TaxDetails{ids=null}", resp);
    }

    @Test
    void testProcessIncome2() {
        ServiceWithObjectCreation service = new ServiceWithObjectCreation();
        // Should not throw exception
        assertDoesNotThrow(() -> service.processIncome2());
    }
}
