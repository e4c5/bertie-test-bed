package com.raditha.bertie.testbed.simple;

import com.raditha.bertie.testbed.model.User;
import com.raditha.bertie.testbed.repository.Repository;
import com.raditha.bertie.testbed.model.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SimpleClassesTest {

    @Test
    void testCalculatorAddAndDouble1() {
        Calculator calculator = new Calculator();
        int result = calculator.addAndDouble1(5, 3);
        assertEquals(16, result); // (5 + 3) * 2 = 16
    }

    @Test
    void testCalculatorAddAndDouble2() {
        Calculator calculator = new Calculator();
        int result = calculator.addAndDouble2(10, 20);
        assertEquals(60, result); // (10 + 20) * 2 = 60
    }

    @Test
    void testAdditionMethod1() {
        Addition addition = new Addition();
        assertDoesNotThrow(() -> addition.method1());
    }

    @Test
    void testAdditionMethod2() {
        Addition addition = new Addition();
        assertDoesNotThrow(() -> addition.method2());
    }

    @Test
    void testPrintingMethod1() {
        Printing printing = new Printing();
        assertDoesNotThrow(() -> printing.method1());
    }

    @Test
    void testPrintingMethod2() {
        Printing printing = new Printing();
        assertDoesNotThrow(() -> printing.method2());
    }

    @Test
    void testServiceProcessUser1() {
        @SuppressWarnings("unchecked")
        Repository<User> repository = mock(Repository.class);
        Logger logger = mock(Logger.class);
        User user = mock(User.class);

        when(repository.findById(anyString())).thenReturn(user);

        Service service = new Service(repository, logger);
        assertDoesNotThrow(() -> service.processUser1());
    }

    @Test
    void testServiceProcessUser2() {
        @SuppressWarnings("unchecked")
        Repository<User> repository = mock(Repository.class);
        Logger logger = mock(Logger.class);
        User user = mock(User.class);

        when(repository.findById(anyString())).thenReturn(user);

        Service service = new Service(repository, logger);
        assertDoesNotThrow(() -> service.processUser2());
    }

    @Test
    void testDistinctOperationsMethod1() {
        DistinctOperations ops = new DistinctOperations();
        assertDoesNotThrow(() -> ops.method1());
    }

    @Test
    void testDistinctOperationsMethod2() {
        DistinctOperations ops = new DistinctOperations();
        assertDoesNotThrow(() -> ops.method2());
    }
}

