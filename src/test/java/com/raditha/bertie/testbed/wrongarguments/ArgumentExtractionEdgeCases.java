package com.raditha.bertie.testbed.wrongarguments;

import com.raditha.bertie.testbed.model.User;

/**
 * Edge case scenarios for argument extraction testing.
 * Tests complex parameter extraction scenarios not covered by basic tests.
 */
public class ArgumentExtractionEdgeCases {

    private User user = new User();
    private User customer = new User();

    /**
     * Edge Case 1: Multiple parameters of the same type.
     * Challenge: Need to extract correct value for each parameter.
     */
    public void processUserNames() {
        String firstName = "John";
        String lastName = "Doe";
        user.setName(firstName + " " + lastName);
        user.setEmail("john@example.com");
    }

    public void processCustomerNames() {
        String firstName = "Jane";
        String lastName = "Smith";
        customer.setName(firstName + " " + lastName);
        customer.setEmail("jane@example.com");
    }

    /**
     * Edge Case 2: Nested expression parameters.
     * Challenge: Extract method call results, not just literals.
     */
    public void updateUserWithGetter() {
        String email = getEmailFromConfig();
        user.setEmail(email);
        user.setName("Config User");
    }

    public void updateCustomerWithGetter() {
        String email = getEmailFromDatabase();
        customer.setEmail(email);
        customer.setName("DB Customer");
    }

    /**
     * Edge Case 3: Null parameter values.
     * Challenge: Handle null literals correctly.
     */
    public void processOptionalUser() {
        String middleName = null;
        if (middleName != null) {
            user.setName(middleName);
        }
        user.setEmail("optional@example.com");
    }

    public void processOptionalCustomer() {
        String middleName = null;
        if (middleName != null) {
            customer.setName(middleName);
        }
        customer.setEmail("optional@example.com");
    }

    /**
     * Edge Case 4: Array parameters.
     * Challenge: Extract array initialization expressions.
     */
    public void addUserRoles() {
        String[] roles = { "admin", "user" };
        user.setName("Admin User");
        user.setEmail(roles[0] + "@example.com");
    }

    public void addCustomerRoles() {
        String[] roles = { "customer", "premium" };
        customer.setName("Premium Customer");
        customer.setEmail(roles[0] + "@example.com");
    }

    /**
     * Edge Case 5: Generic type parameters.
     * Challenge: Handle parameterized types like List<String>.
     */
    public void addUserTags() {
        java.util.List<String> tags = java.util.Arrays.asList("important", "verified");
        user.setName("Tagged User");
        user.setEmail(tags.get(0) + "@example.com");
    }

    public void addCustomerTags() {
        java.util.List<String> tags = java.util.Arrays.asList("premium", "vip");
        customer.setName("VIP Customer");
        customer.setEmail(tags.get(0) + "@example.com");
    }

    // Helper methods
    private String getEmailFromConfig() {
        return "config@example.com";
    }

    private String getEmailFromDatabase() {
        return "db@example.com";
    }
}
