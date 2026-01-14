package com.raditha.bertie.testbed.returnvalue;

import com.raditha.bertie.testbed.model.User;
import com.raditha.bertie.testbed.repository.Repository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReturnValueServicesTest {

    private Repository<User> repository;
    private User mockUser;

    @BeforeEach
    void setUp() {
        @SuppressWarnings("unchecked")
        Repository<User> repo = mock(Repository.class);
        repository = repo;
        mockUser = mock(User.class);
        when(repository.findById(anyString())).thenReturn(mockUser);
    }

    @Test
    void testServiceWithNoReturnProcessUser1() {
        ServiceWithNoReturn service = new ServiceWithNoReturn(repository);
        assertDoesNotThrow(() -> service.processUser1("123"));
    }

    @Test
    void testServiceWithNoReturnProcessUser2() {
        ServiceWithNoReturn service = new ServiceWithNoReturn(repository);
        assertDoesNotThrow(() -> service.processUser2("456"));
    }

    @Test
    void testServiceWithNoReturnProcessUser3() {
        ServiceWithNoReturn service = new ServiceWithNoReturn(repository);
        assertDoesNotThrow(() -> service.processUser3("789"));
    }

    @Test
    void testServiceWithSimpleReturnGetUserName1() {
        when(mockUser.getName()).thenReturn("John Doe");
        ServiceWithSimpleReturn service = new ServiceWithSimpleReturn(repository);
        String name = service.getUserName1("123");
        assertEquals("John Doe", name);
    }

    @Test
    void testServiceWithSimpleReturnGetUserEmail2() {
        when(mockUser.getEmail()).thenReturn("john@example.com");
        ServiceWithSimpleReturn service = new ServiceWithSimpleReturn(repository);
        String email = service.getUserEmail2("456");
        assertEquals("john@example.com", email);
    }

    @Test
    void testServiceWithSimpleReturnIsUserActive3() {
        when(mockUser.isActive()).thenReturn(true);
        ServiceWithSimpleReturn service = new ServiceWithSimpleReturn(repository);
        boolean active = service.isUserActive3("789");
        assertTrue(active);
    }

    @Test
    void testServiceWithCollectionReturnsProcessNames1() {
        ServiceWithCollectionReturns service = new ServiceWithCollectionReturns();
        List<String> names = Arrays.asList("Alice", "Bob", null, "", "Charlie");
        int count = service.processNames1(names);
        assertEquals(3, count);
    }

    @Test
    void testServiceWithCollectionReturnsGetFirst2() {
        ServiceWithCollectionReturns service = new ServiceWithCollectionReturns();
        List<String> items = Arrays.asList(null, "", "First", "Second");
        String first = service.getFirst2(items);
        assertEquals("First", first);
    }

    @Test
    void testServiceWithCollectionReturnsIsEmpty3() {
        ServiceWithCollectionReturns service = new ServiceWithCollectionReturns();
        List<String> values = Arrays.asList("Value1", "Value2");
        boolean isEmpty = service.isEmpty3(values);
        assertFalse(isEmpty);
    }

    @Test
    void testServiceWithPrimitiveReturnsCalculateTotal1() {
        ServiceWithPrimitiveReturns service = new ServiceWithPrimitiveReturns();
        int total = service.calculateTotal1(100, 50);
        assertEquals(165, total); // (100 + 50) + (150 * 10 / 100) = 150 + 15 = 165
    }

    @Test
    void testServiceWithPrimitiveReturnsCalculateTotal2() {
        ServiceWithPrimitiveReturns service = new ServiceWithPrimitiveReturns();
        int total = service.calculateTotal2(200, 100);
        assertEquals(330, total); // (200 + 100) + (300 * 10 / 100) = 300 + 30 = 330
    }

    @Test
    void testServiceWithPrimitiveReturnsIsValid1() {
        ServiceWithPrimitiveReturns service = new ServiceWithPrimitiveReturns();
        assertTrue(service.isValid1("Valid"));
    }

    @Test
    void testServiceWithPrimitiveReturnsIsValid2() {
        ServiceWithPrimitiveReturns service = new ServiceWithPrimitiveReturns();
        assertTrue(service.isValid2("AnotherValid"));
    }
}

