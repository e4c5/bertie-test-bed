package com.raditha.bertie.testbed.wrongarguments;

import com.raditha.bertie.testbed.model.Customer;
import com.raditha.bertie.testbed.model.User;
import com.raditha.bertie.testbed.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests for Wrong Arguments Bug
 * 
 * These tests will FAIL if Bertie refactors incorrectly and uses
 * the wrong values for each method call.
 * 
 * CRITICAL: After refactoring, each method must still use its own values!
 * Each test verifies that the specific values for that method are preserved.
 */
class UserServiceWithDifferentValuesTest {
    
    private Repository<User> userRepository;
    private Repository<Customer> customerRepository;
    private UserServiceWithDifferentValues service;
    
    @SuppressWarnings("unchecked")
    @BeforeEach
    void setUp() {
        userRepository = mock(Repository.class);
        customerRepository = mock(Repository.class);
        service = new UserServiceWithDifferentValues(userRepository, customerRepository);
    }
    
    @Test
    void testProcessJohn_usesCorrectValues() {
        when(userRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        
        String result = service.processJohn();
        
        assertEquals("John", result);
        
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        
        User savedUser = captor.getValue();
        assertEquals("John", savedUser.getName(), "MUST use 'John', not 'Jane' or any other value");
        assertEquals("john@example.com", savedUser.getEmail(), "MUST use john's email");
        assertTrue(savedUser.isActive());
        assertEquals("user", savedUser.getRole());
    }
    
    @Test
    void testProcessJane_usesCorrectValues() {
        when(userRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        
        String result = service.processJane();
        
        assertEquals("Jane", result);
        
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        
        User savedUser = captor.getValue();
        assertEquals("Jane", savedUser.getName(), "MUST use 'Jane', not 'John' or any other value");
        assertEquals("jane@example.com", savedUser.getEmail(), "MUST use jane's email");
        assertTrue(savedUser.isActive());
        assertEquals("user", savedUser.getRole());
    }
    
    @Test
    void testProcessAdmin_usesCorrectValues() {
        when(userRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        
        String result = service.processAdmin();
        
        assertEquals("Admin", result);
        
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        
        User savedUser = captor.getValue();
        assertEquals("Admin", savedUser.getName(), "MUST use 'Admin'");
        assertEquals("admin@example.com", savedUser.getEmail(), "MUST use admin's email");
        assertTrue(savedUser.isActive());
        assertEquals("admin", savedUser.getRole(), "MUST use 'admin' role, not 'user'");
    }
    
    @Test
    void testProcessCustomerBob_usesCorrectValues() {
        when(customerRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        
        String result = service.processCustomerBob();
        
        assertEquals("Bob", result);
        
        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(captor.capture());
        
        Customer savedCustomer = captor.getValue();
        assertEquals("Bob", savedCustomer.getName(), "MUST use 'Bob', not 'Alice'");
        assertEquals("bob@customer.com", savedCustomer.getEmail());
        assertTrue(savedCustomer.isActive());
        assertEquals("customer", savedCustomer.getRole());
    }
    
    @Test
    void testProcessCustomerAlice_usesCorrectValues() {
        when(customerRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        
        String result = service.processCustomerAlice();
        
        assertEquals("Alice", result);
        
        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(captor.capture());
        
        Customer savedCustomer = captor.getValue();
        assertEquals("Alice", savedCustomer.getName(), "MUST use 'Alice', not 'Bob'");
        assertEquals("alice@customer.com", savedCustomer.getEmail());
        assertFalse(savedCustomer.isActive(), "Alice should be inactive");
        assertEquals("premium", savedCustomer.getRole(), "MUST use 'premium' role, not 'customer'");
    }
    
    @Test
    void testAllMethodsUseDistinctValues() {
        // This test ensures that if refactored, each method still produces different results
        when(userRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(customerRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        
        String john = service.processJohn();
        String jane = service.processJane();
        String admin = service.processAdmin();
        String bob = service.processCustomerBob();
        String alice = service.processCustomerAlice();
        
        // All must be different!
        assertNotEquals(john, jane);
        assertNotEquals(john, admin);
        assertNotEquals(jane, admin);
        assertNotEquals(bob, alice);
    }
}
