package com.raditha.bertie.testbed.sideeffects;

import com.raditha.bertie.testbed.model.Database;
import com.raditha.bertie.testbed.model.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Gap 10: No Validation That Extracted Code is Side-Effect Free
 * 
 * These tests will FAIL if Bertie extracts code with side effects incorrectly,
 * causing side effects to execute in wrong order or wrong number of times.
 * 
 * CRITICAL: After refactoring, side effects must:
 * - Execute in the same order
 * - Execute the same number of times
 * - Not be duplicated or missing
 */
class ServiceWithDatabaseAndLoggingTest {

    private Database database;
    private Logger logger;
    private ServiceWithDatabaseAndLogging service;

    @BeforeEach
    void setUp() {
        database = new Database();
        logger = new Logger();
        service = new ServiceWithDatabaseAndLogging(database, logger);
    }

    @Test
    void testDatabaseSaveOrder_preservesSequence() {
        // Process multiple users
        service.processUserWithLogging("Alice");
        service.processAnotherUserWithLogging("Bob");
        service.processThirdUserWithLogging("Charlie");

        // Verify all saves happened
        assertEquals(3, database.getSaveCount(),
                "MUST save exactly 3 users, not more, not less");

        List<String> saved = database.getSavedEntities();
        assertEquals(3, saved.size());

        // Verify order is preserved
        assertTrue(saved.get(0).contains("Alice"), "First save should be Alice");
        assertTrue(saved.get(1).contains("Bob"), "Second save should be Bob");
        assertTrue(saved.get(2).contains("Charlie"), "Third save should be Charlie");

        // CRITICAL: If Bertie extracts and causes side effects to run out of order,
        // the sequence will be wrong!
    }

    @Test
    void testLoggingOrder_preservesSequence() {
        service.processUserWithLogging("TestUser");

        List<String> messages = logger.getMessages();

        // Should have exactly 2 log messages per call
        assertEquals(2, messages.size(),
                "MUST log exactly 2 messages: Starting and Completed");

        // Verify order
        assertTrue(messages.get(0).contains("Starting"),
                "First log should be 'Starting'");
        assertTrue(messages.get(0).contains("TestUser"),
                "First log should contain user name");

        assertTrue(messages.get(1).contains("Completed"),
                "Second log should be 'Completed'");
        assertTrue(messages.get(1).contains("TestUser"),
                "Second log should contain user name");

        // CRITICAL: If extraction changes timing, logs might appear in wrong order!
    }

    @Test
    void testMixedSideEffects_correctTiming() {
        service.processWithMultipleSideEffects("Dave");

        // Verify interleaved side effects maintain correct order
        List<String> logs = logger.getMessages();
        List<String> saves = database.getSavedEntities();

        // Should have 3 log messages
        assertEquals(3, logs.size(), "MUST have exactly 3 log messages");

        // Should have 2 saves (user + audit)
        assertEquals(2, saves.size(), "MUST have exactly 2 saves (user + audit)");

        // Verify order of logs
        assertTrue(logs.get(0).contains("Validating"), "First: Validating log");
        assertTrue(logs.get(1).contains("Saved"), "Second: Saved log");
        assertTrue(logs.get(2).contains("Audit recorded"), "Third: Audit log");

        // Verify order of saves
        assertTrue(saves.get(0).contains("Dave"), "First save: user");
        assertTrue(saves.get(1).contains("processed"), "Second save: audit entry");

        // CRITICAL: If Bertie extracts and side effects get reordered:
        // - Audit might be logged before user is saved
        // - Logs and saves might be out of sync
    }

    @Test
    void testSideEffectsExecuteOnce_notDuplicated() {
        // Clear any previous state
        logger.clear();
        database.clear();

        // Process one user
        service.processUserWithLogging("SingleUser");

        // Verify side effects executed exactly once
        assertEquals(1, database.getSaveCount(),
                "MUST save exactly once, not duplicated!");

        assertEquals(2, logger.getMessageCount(),
                "MUST log exactly twice (start + end), not duplicated!");

        // Process another user
        service.processAnotherUserWithLogging("SecondUser");

        // Now should have 2 saves and 4 logs total
        assertEquals(2, database.getSaveCount(),
                "MUST save exactly twice total");

        assertEquals(4, logger.getMessageCount(),
                "MUST log exactly 4 times total (2 per user)");

        // CRITICAL: If Bertie's extraction causes side effects to duplicate,
        // we'll see more saves or logs than expected!
    }

    @Test
    void testComplexSideEffectsNotDuplicated() {
        logger.clear();
        database.clear();

        // Process two users with complex workflow
        service.processWithMultipleSideEffects("User1");
        service.processAnotherWithMultipleSideEffects("User2");

        // Should have exactly 4 saves (2 users + 2 audit entries)
        assertEquals(4, database.getSaveCount(),
                "MUST have exactly 4 saves: 2 users + 2 audits");

        // Should have exactly 6 logs (3 per user)
        assertEquals(6, logger.getMessageCount(),
                "MUST have exactly 6 logs: 3 per user");

        // Verify no duplication in database
        List<String> saves = database.getSavedEntities();
        assertTrue(saves.get(0).contains("User1"), "First user save");
        assertTrue(saves.get(1).contains("User1 processed"), "First audit");
        assertTrue(saves.get(2).contains("User2"), "Second user save");
        assertTrue(saves.get(3).contains("User2 processed"), "Second audit");

        // CRITICAL: If extraction causes side effects to run multiple times,
        // counts and sequence will be wrong!
    }
}
