package com.raditha.bertie.testbed.statementremoval;

import com.raditha.bertie.testbed.model.User;

/**
 * Edge case scenarios for statement removal.
 * Tests complex removal scenarios.
 */
public class StatementRemovalEdgeCases {

    /**
     * Edge Case 1: Remove last statement in method.
     * Challenge: Sequence is at end of method body.
     */
    public void processUserAtEnd() {
        User user = new User("First");
        user.setEmail("first@example.com");
        // Last statements in method:
        user.setActive(true);
        user.save();
    }

    public void processCustomerAtEnd() {
        User customer = new User("Second");
        customer.setEmail("second@example.com");
        // Last statements:
        customer.setActive(true);
        customer.save();
    }

    /**
     * Edge Case 2: Remove from nested block.
     * Challenge: Duplicate sequence inside if/for/while.
     */
    public void processUserInNestedBlock(boolean condition) {
        if (condition) {
            User user = new User("Nested");
            user.setEmail("nested@example.com");
            user.setActive(true);
            user.save();
        }
    }

    public void processCustomerInNestedBlock(boolean condition) {
        if (condition) {
            User customer = new User("NestedCustomer");
            customer.setEmail("nested@example.com");
            customer.setActive(true);
            customer.save();
        }
    }
}
