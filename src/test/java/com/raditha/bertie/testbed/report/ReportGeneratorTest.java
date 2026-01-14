package com.raditha.bertie.testbed.report;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReportGeneratorTest {

    @Test
    void testGenerateHeader() {
        ReportGenerator generator = new ReportGenerator();
        assertDoesNotThrow(() -> generator.generateHeader());
    }

    @Test
    void testGenerateFooter() {
        ReportGenerator generator = new ReportGenerator();
        assertDoesNotThrow(() -> generator.generateFooter());
    }
}

