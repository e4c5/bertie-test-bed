package com.raditha.bertie.testbed.partial;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConstructorDuplicatesTest {

    private static PrintStream originalOut;
    private ByteArrayOutputStream capturedOut;

    @BeforeAll
    static void stashOriginalSystemOut() {
        originalOut = System.out;
    }

    @BeforeEach
    void redirectSystemOut() {
        capturedOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturedOut, true));
    }

    @AfterAll
    static void restoreSystemOut() {
        System.setOut(originalOut);
    }

    private String capturedOutput() {
        System.out.flush();
        return capturedOut.toString();
    }

    @Test
    void defaultConstructorInitializesDefaultStateAndLogs() {
        ConstructorDuplicates subject = new ConstructorDuplicates();

        assertEquals(List.of("default"), subject.items, "Default constructor should seed a single default item");
        assertEquals("default", subject.name);
        assertTrue(capturedOutput().contains("Initialized default constructor"));
    }

    @Test
    void stringConstructorUsesProvidedNameAndSameItemSetup() {
        ConstructorDuplicates subject = new ConstructorDuplicates("customName");

        assertEquals(List.of("default"), subject.items);
        assertEquals("customName", subject.name);
        assertTrue(capturedOutput().contains("Initialized default constructor"));
    }

    @Test
    void capacityConstructorCreatesIndexedItemsAndLogsCapacity() {
        ConstructorDuplicates subject = new ConstructorDuplicates(3);

        assertEquals(List.of("item0", "item1", "item2"), subject.items);
        assertEquals("capacity", subject.name);
        assertTrue(capturedOutput().contains("Created with name: capacity"));
    }

    @Test
    void factorConstructorScalesCapacityAndLogsFactor() {
        ConstructorDuplicates subject = new ConstructorDuplicates(1.5);

        assertEquals(15, subject.items.size());
        List<String> expectedItems = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            expectedItems.add("item" + i);
        }
        assertIterableEquals(expectedItems, subject.items);
        assertEquals("factor", subject.name);
        assertTrue(capturedOutput().contains("Created with name: factor"));
    }
}
