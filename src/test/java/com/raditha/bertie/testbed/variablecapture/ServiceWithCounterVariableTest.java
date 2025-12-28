package com.raditha.bertie.testbed.variablecapture;

import com.raditha.bertie.testbed.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Variable Capture Bug
 * 
 * These tests will FAIL if Bertie extracts code that modifies outer variables.
 * Tests verify that counters increment correctly and lists accumulate values.
 * 
 * CRITICAL: After refactoring (if Bertie allows it), counter/list must still be modified!
 * PREFERRED: Bertie should BLOCK extraction with validation error.
 */
class ServiceWithCounterVariableTest {
    
    private ServiceWithCounterVariable service;
    
    @BeforeEach
    void setUp() {
        service = new ServiceWithCounterVariable();
    }
    
    @Test
    void testProcessItemsAndCount_countsActiveUsers() {
        List<User> users = createUsers(5, 3); // 5 users, 3 active
        
        int count = service.processItemsAndCount(users);
        
        assertEquals(3, count, "Must count exactly 3 active users");
    }
    
    @Test
    void testProcessMoreItemsAndCount_countsActiveUsers() {
        List<User> users = createUsers(10, 7); // 10 users, 7 active
        
        int count = service.processMoreItemsAndCount(users);
        
        assertEquals(7, count, "Must count exactly 7 active users");
    }
    
    @Test
    void testProcessThirdSetAndCount_countsActiveUsers() {
        List<User> users = createUsers(8, 8); // 8 users, all active
        
        int count = service.processThirdSetAndCount(users);
        
        assertEquals(8, count, "Must count all 8 active users");
    }
    
    @Test
    void testProcessItemsAndCount_returnsZeroWhenNoActiveUsers() {
        List<User> users = createUsers(5, 0); // 5 users, 0 active
        
        int count = service.processItemsAndCount(users);
        
        assertEquals(0, count, "Must return 0 when no users are active");
    }
    
    @Test
    void testCollectActiveUserNames_collectsCorrectNames() {
        List<User> users = new ArrayList<>();
        users.add(createUser("John", true));
        users.add(createUser("Jane", false));  // Inactive
        users.add(createUser("Bob", true));
        users.add(createUser("Alice", true));
        
        List<String> names = service.collectActiveUserNames(users);
        
        assertEquals(3, names.size(), "Must collect exactly 3 active users");
        assertTrue(names.contains("John"));
        assertTrue(names.contains("Bob"));
        assertTrue(names.contains("Alice"));
        assertFalse(names.contains("Jane"), "Must NOT include inactive user");
    }
    
    @Test
    void testCollectAnotherSet_collectsCorrectNames() {
        List<User> users = new ArrayList<>();
        users.add(createUser("Charlie", true));
        users.add(createUser("David", true));
        users.add(createUser("Eve", false));  // Inactive
        
        List<String> names = service.collectAnotherSet(users);
        
        assertEquals(2, names.size(), "Must collect exactly 2 active users");
        assertTrue(names.contains("Charlie"));
        assertTrue(names.contains("David"));
        assertFalse(names.contains("Eve"), "Must NOT include inactive user");
    }
    
    @Test
    void testAllMethodsCountIndependently() {
        // Each method must maintain its OWN counter
        List<User> users1 = createUsers(3, 2);
        List<User> users2 = createUsers(5, 4);
        List<User> users3 = createUsers(2, 1);
        
        int count1 = service.processItemsAndCount(users1);
        int count2 = service.processMoreItemsAndCount(users2);
        int count3 = service.processThirdSetAndCount(users3);
        
        // Each must return its own count, not shared
        assertEquals(2, count1, "First method must count 2");
        assertEquals(4, count2, "Second method must count 4");
        assertEquals(1, count3, "Third method must count 1");
        
        // Calling again should reset counter
        int count1Again = service.processItemsAndCount(users1);
        assertEquals(2, count1Again, "Counter must reset on new call");
    }
    
    // === Helper Methods ===
    
    private List<User> createUsers(int total, int activeCount) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            User user = new User();
            user.setId("user" + i);
            user.setName("User" + i);
            user.setActive(i < activeCount);
            users.add(user);
        }
        return users;
    }
    
    private User createUser(String name, boolean active) {
        User user = new User();
        user.setName(name);
        user.setActive(active);
        return user;
    }
}
