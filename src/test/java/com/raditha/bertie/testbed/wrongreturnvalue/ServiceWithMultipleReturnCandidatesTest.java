package com.raditha.bertie.testbed.wrongreturnvalue;

import com.raditha.bertie.testbed.model.User;
import com.raditha.bertie.testbed.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests for Wrong Return Value Bug
 * 
 * These tests will FAIL if Bertie returns the wrong variable (tempUser instead of finalUser).
 * 
 * CRITICAL: After refactoring, must return the variable that is ACTUALLY used later!
 * Tests verify that the correct variable's data appears in the results.
 */
class ServiceWithMultipleReturnCandidatesTest {
    
    private Repository<User> repository;
    private ServiceWithMultipleReturnCandidates service;
    
    @SuppressWarnings("unchecked")
    @BeforeEach
    void setUp() {
        repository = mock(Repository.class);
        service = new ServiceWithMultipleReturnCandidates(repository);
    }
    
    @Test
    void testProcessUserAndReturnCorrectOne_returnsFinalUserNotTemp() {
        User finalUser = new User();
        finalUser.setId("123");
        finalUser.setName("John");
        finalUser.setEmail("john@example.com");
        
        when(repository.findById("123")).thenReturn(finalUser);
        
        String result = service.processUserAndReturnCorrectOne("123");
        
        // Must return finalUser's name, not tempUser's name!
        assertEquals("John is now active", result, 
            "MUST return finalUser ('John'), not tempUser ('Temporary')");
        assertFalse(result.contains("Temporary"), 
            "MUST NOT contain 'Temporary' - that's the temp user!");
    }
    
    @Test
    void testProcessAnotherUserAndReturnCorrectOne_returnsFinalUserNotTemp() {
        User finalUser = new User();
        finalUser.setId("456");
        finalUser.setName("Jane");
        finalUser.setEmail("jane@example.com");
        
        when(repository.findById("456")).thenReturn(finalUser);
        
        String result = service.processAnotherUserAndReturnCorrectOne("456");
        
        // Must return finalUser's email, not tempUser's!
        assertEquals("jane@example.com was updated", result,
            "MUST return finalUser's email, not tempUser's");
        assertFalse(result.contains("Temporary"),
            "MUST NOT contain 'Temporary'");
    }
    
    @Test
    void testProcessThirdUserAndReturnCorrectOne_returnsFinalUserNotTemp() {
        User finalUser = new User();
        finalUser.setId("789");
        finalUser.setName("Bob");
        
        when(repository.findById("789")).thenReturn(finalUser);
        
        String result = service.processThirdUserAndReturnCorrectOne("789");
        
        // Must return finalUser's ID, not "temp"!
        assertEquals("User 789 processed", result,
            "MUST return finalUser's ID (789), not tempUser's ID (temp)");
        assertFalse(result.contains("temp"),
            "MUST NOT contain 'temp' - that's the tempUser's ID!");
    }
    
    @Test
    void testProcessAndDontReturn1_completesSuccessfully() {
        User user = new User();
        user.setId("100");
        
        when(repository.findById("100")).thenReturn(user);
        
        // Should complete without error
        assertDoesNotThrow(() -> service.processAndDontReturn1("100"));
        
        // Should have saved the user
        verify(repository).findById("100");
    }
    
    @Test
    void testProcessAndDontReturn2_completesSuccessfully() {
        User user = new User();
        user.setId("200");
        
        when(repository.findById("200")).thenReturn(user);
        
        // Should complete without error
        assertDoesNotThrow(() -> service.processAndDontReturn2("200"));
        
        // Should have saved the user
        verify(repository).findById("200");
    }
    
    @Test
    void testAllMethodsReturnDifferentResults() {
        // Ensure each method uses ITS OWN finalUser, not a shared one
        User user1 = new User();
        user1.setId("1");
        user1.setName("User1");
        user1.setEmail("user1@test.com");
        
        User user2 = new User();
        user2.setId("2");
        user2.setName("User2");
        user2.setEmail("user2@test.com");
        
        User user3 = new User();
        user3.setId("3");
        user3.setName("User3");
        
        when(repository.findById("1")).thenReturn(user1);
        when(repository.findById("2")).thenReturn(user2);
        when(repository.findById("3")).thenReturn(user3);
        
        String result1 = service.processUserAndReturnCorrectOne("1");
        String result2 = service.processAnotherUserAndReturnCorrectOne("2");
        String result3 = service.processThirdUserAndReturnCorrectOne("3");
        
        // All results must be different
        assertNotEquals(result1, result2);
        assertNotEquals(result2, result3);
        assertNotEquals(result1, result3);
        
        // Each must use its own user's data
        assertTrue(result1.contains("User1"));
        assertTrue(result2.contains("user2@test.com"));
        assertTrue(result3.contains("3"));
    }
}
