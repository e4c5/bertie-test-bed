package com.raditha.bertie.testbed.testisolation;

import com.raditha.bertie.testbed.model.User;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Edge case scenarios for test isolation (mutability).
 * Tests complex mutability scenarios.
 */
public class TestIsolationEdgeCases {

    /**
     * Edge Case 1: Generic mutable containers.
     * Challenge: List/Map containing domain objects are mutable.
     */
    private List<User> users;
    private Map<String, User> userMap;

    public void initializeUserList() {
        users = new ArrayList<>();
        users.add(new User("Test User"));
    }

    public void initializeUserMap() {
        userMap = new HashMap<>();
        userMap.put("key", new User("Map User"));
    }

    /**
     * Edge Case 2: Nested domain objects.
     * Challenge: Complex object graphs with nested mutability.
     */
    private static class Order {
        private List<LineItem> items = new ArrayList<>();

        public void addItem(LineItem item) {
            items.add(item);
        }
    }

    private static class LineItem {
        private String product;
        private int quantity;
    }

    private Order order;

    public void initializeOrder() {
        order = new Order();
        order.addItem(new LineItem());
    }

    /**
     * Edge Case 3: Builder pattern objects.
     * Challenge: Are builder classes mutable?
     */
    private static class UserBuilder {
        private String name;
        private String email;

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(name, email);
        }
    }

    private UserBuilder builder;

    public void initializeBuilder() {
        builder = new UserBuilder();
        builder.setName("Builder User");
    }
}
