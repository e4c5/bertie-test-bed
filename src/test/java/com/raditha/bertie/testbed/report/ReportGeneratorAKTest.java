package com.raditha.bertie.testbed.report;

import java.io.PrintStream;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.mockito.Mockito;
import org.mockito.Mock;
import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReportGeneratorAKTest {

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
     * Method under test: ReportGenerator.generateFooter()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void generateFooterTest() {
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.generateFooter();
        assertEquals("3\ndone\nend", outputStream.toString().trim());
    }

    /**
     * Method under test: ReportGenerator.generateHeader()
     * Argument generator : DummyArgumentGenerator
     * Author : Antikythera
     */
    @Test()
    void generateHeaderTest() {
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.generateHeader();
        assertEquals("3\ndone\nend", outputStream.toString().trim());
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
