package com.raditha.bertie.testbed.wrongreturnvalue;

import java.io.PrintStream;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import com.raditha.bertie.testbed.model.User;
import org.mockito.Mockito;
import org.mockito.Mock;
import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReturnValueEdgeCasesAKTest {

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
     * Method under test: ReturnValueEdgeCases.buildCustomer()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void buildCustomerTest() {
        ReturnValueEdgeCases returnValueEdgeCases = new ReturnValueEdgeCases();
        User resp = returnValueEdgeCases.buildCustomer();
        assertNotNull(resp);
        assertEquals("BuilderCustomer", resp.getName());
        assertEquals("builder@example.com", resp.getEmail());
        assertEquals(0, resp.getAge());
        assertEquals(0, resp.getLoginCount());
    }

    /**
     * Method under test: ReturnValueEdgeCases.buildUser()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void buildUserTest() {
        ReturnValueEdgeCases returnValueEdgeCases = new ReturnValueEdgeCases();
        User resp = returnValueEdgeCases.buildUser();
        assertNotNull(resp);
        assertEquals("Builder", resp.getName());
        assertEquals("builder@example.com", resp.getEmail());
        assertEquals(0, resp.getAge());
        assertEquals(0, resp.getLoginCount());
    }

    /**
     * Method under test: ReturnValueEdgeCases.createCustomerWithFieldVsLocal()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void createCustomerWithFieldVsLocalTest() {
        ReturnValueEdgeCases returnValueEdgeCases = new ReturnValueEdgeCases();
        User resp = returnValueEdgeCases.createCustomerWithFieldVsLocal();
        assertNotNull(resp);
        assertEquals("local@example.com", resp.getEmail());
        assertEquals(0, resp.getAge());
        assertEquals(0, resp.getLoginCount());
    }

    /**
     * Method under test: ReturnValueEdgeCases.createCustomerWithMultipleCandidates()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void createCustomerWithMultipleCandidatesTest() {
        ReturnValueEdgeCases returnValueEdgeCases = new ReturnValueEdgeCases();
        User resp = returnValueEdgeCases.createCustomerWithMultipleCandidates();
        assertNotNull(resp);
        assertEquals("customer2@example.com", resp.getEmail());
        assertEquals(0, resp.getAge());
        assertEquals(0, resp.getLoginCount());
    }

    /**
     * Method under test: ReturnValueEdgeCases.createUnusedCustomer()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void createUnusedCustomerTest() {
        ReturnValueEdgeCases returnValueEdgeCases = new ReturnValueEdgeCases();
        User resp = returnValueEdgeCases.createUnusedCustomer();
        assertNotNull(resp);
        assertEquals("unused@example.com", resp.getEmail());
        assertEquals(0, resp.getAge());
        assertEquals(0, resp.getLoginCount());
    }

    /**
     * Method under test: ReturnValueEdgeCases.createUnusedUser()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void createUnusedUserTest() {
        ReturnValueEdgeCases returnValueEdgeCases = new ReturnValueEdgeCases();
        User resp = returnValueEdgeCases.createUnusedUser();
        assertNotNull(resp);
        assertEquals("unused@example.com", resp.getEmail());
        assertEquals(0, resp.getAge());
        assertEquals(0, resp.getLoginCount());
    }

    /**
     * Method under test: ReturnValueEdgeCases.createUserWithFieldVsLocal()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void createUserWithFieldVsLocalTest() {
        ReturnValueEdgeCases returnValueEdgeCases = new ReturnValueEdgeCases();
        User resp = returnValueEdgeCases.createUserWithFieldVsLocal();
        assertNotNull(resp);
        assertEquals("local@example.com", resp.getEmail());
        assertEquals(0, resp.getAge());
        assertEquals(0, resp.getLoginCount());
    }

    /**
     * Method under test: ReturnValueEdgeCases.createUserWithMultipleCandidates()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void createUserWithMultipleCandidatesTest() {
        ReturnValueEdgeCases returnValueEdgeCases = new ReturnValueEdgeCases();
        User resp = returnValueEdgeCases.createUserWithMultipleCandidates();
        assertNotNull(resp);
        assertEquals("second@example.com", resp.getEmail());
        assertEquals(0, resp.getAge());
        assertEquals(0, resp.getLoginCount());
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
