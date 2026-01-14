package com.raditha.bertie.testbed.partial;

import java.util.logging.Logger;

public class MixedResponsibilityService {
    // Dummy classes to support the test code
    static class User {
        void setName(String s) {}
        void setEmail(String s) {}
        void setActive(boolean b) {}
        void setRole(String s) {}
        void save() {}
    }
    static class Validator { void check() {} }
    static class Cache { void invalidate() {} }

    void createUser() {
        User user = new User();
        user.setName("Test");
        user.setEmail("test@example.com");
        user.setActive(true);
        user.setRole("admin");
        user.save();
    }

    void processRequest() {
        Logger logger = Logger.getLogger("Test");
        Validator validator = new Validator();
        Cache cache = new Cache();

        // Setup phase
        logger.info("Starting");
        validator.check();

        // DUPLICATE CODE
        User user = new User();
        user.setName("Test");
        user.setEmail("test@example.com");
        user.setActive(true);
        user.setRole("admin");
        user.save();

        // Cleanup phase
        logger.info("Done");
        cache.invalidate();
    }
}
