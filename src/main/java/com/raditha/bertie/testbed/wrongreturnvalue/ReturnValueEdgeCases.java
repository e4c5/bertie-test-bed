package com.raditha.bertie.testbed.wrongreturnvalue;

import com.raditha.bertie.testbed.model.User;

/**
 * Edge case scenarios for return value detection.
 * Tests complex return value scenarios not covered by basic tests.
 */
public class ReturnValueEdgeCases {

    /**
     * Edge Case 1: Multiple candidates of same type.
     * Challenge: Choose the correct one (prefer the modified variable).
     */
    public User createUserWithMultipleCandidates() {
        User user1 = new User("First");
        User user2 = new User("Second");
        user2.setEmail("second@example.com");
        user2.setActive(true);
        return user2; // Should return user2, not user1
    }

    public User createCustomerWithMultipleCandidates() {
        User customer1 = new User("Customer1");
        User customer2 = new User("Customer2");
        customer2.setEmail("customer2@example.com");
        customer2.setActive(true);
        return customer2;
    }

    /**
     * Edge Case 2: No live variable (defined but not used after).
     * Challenge: Recognize when there's no valid return variable.
     */
    public User createUnusedUser() {
        User user = new User("Unused");
        user.setEmail("unused@example.com");
        // user is not used after this point
        return user;
    }

    public User createUnusedCustomer() {
        User customer = new User("UnusedCustomer");
        customer.setEmail("unused@example.com");
        return customer;
    }

    /**
     * Edge Case 3: Field vs local variable.
     * Challenge: Prefer local variable over instance field.
     */
    private User globalUser;

    public User createUserWithFieldVsLocal() {
        User localUser = new User("Local");
        localUser.setEmail("local@example.com");
        globalUser = new User("Global");
        return localUser; // Should return local, not field
    }

    public User createCustomerWithFieldVsLocal() {
        User localCustomer = new User("LocalCustomer");
        localCustomer.setEmail("local@example.com");
        globalUser = new User("GlobalCustomer");
        return localCustomer;
    }

    /**
     * Edge Case 4: Builder pattern (chain of calls).
     * Challenge: Track variable through method chains.
     */
    public User buildUser() {
        User user = new User();
        user.setName("Builder");
        user.setEmail("builder@example.com");
        user.setActive(true);
        return user;
    }

    public User buildCustomer() {
        User customer = new User();
        customer.setName("BuilderCustomer");
        customer.setEmail("builder@example.com");
        customer.setActive(true);
        return customer;
    }
}
