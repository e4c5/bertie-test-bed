package com.raditha.bertie.testbed.food;

import java.io.PrintStream;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.mockito.Mockito;
import org.mockito.Mock;
import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KitchenOperationsAKTest {

    private PrintStream originalOut;

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    /**
     * Author : Antikythera
     */
    @BeforeEach()
    void setUp() {
        MockitoAnnotations.openMocks(this);
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * Method under test: KitchenOperations.cookPasta()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void cookPastaTest() {
        KitchenOperations kitchenOperations = new KitchenOperations();
        String sauce = "Antikythera";
        int grams = 0;
        kitchenOperations.cookPasta(sauce, grams);
        assertEquals("LOG: Activating kitchen station...\nInventory check for: Ingredients\nBoiling 0g of pasta for Antikythera\nCooking for 10 minutes...\nLOG: Preparation completed in 2ms\nSanitizing kitchen station...\nUpdating service log status: SUCCESS", outputStream.toString().trim());
    }

    /**
     * Method under test: KitchenOperations.cookSoup()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void cookSoupTest() {
        KitchenOperations kitchenOperations = new KitchenOperations();
        String base = "Antikythera";
        int ml = 0;
        kitchenOperations.cookSoup(base, ml);
        assertEquals("LOG: Activating kitchen station...\nInventory check for: Ingredients\nSimmering 0ml of soup with Antikythera base\nCooking for 20 minutes...\nLOG: Preparation completed in 2ms\nSanitizing kitchen station...\nUpdating service log status: SUCCESS", outputStream.toString().trim());
    }

    /**
     * Method under test: KitchenOperations.grillSteak()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void grillSteakTest() {
        KitchenOperations kitchenOperations = new KitchenOperations();
        String cut = "Antikythera";
        String doneness = "Antikythera";
        kitchenOperations.grillSteak(cut, doneness);
        assertEquals("LOG: Activating kitchen station...\nInventory check for: Ingredients\nGrilling Antikythera steak to Antikythera\nCooking for 15 minutes...\nLOG: Preparation completed in 4ms\nSanitizing kitchen station...\nUpdating service log status: SUCCESS", outputStream.toString().trim());
    }

    /**
     * Author : Antikythera
     */
    @AfterEach()
    void tearDown() {
        System.setOut(originalOut);
        outputStream.reset();
    }
}
