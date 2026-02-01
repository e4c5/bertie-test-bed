package com.raditha.bertie.testbed.report;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ReportTest {

    @Test
    void testReportA() {
        ReportA report = new ReportA();
        report.generate();
        assertThat(report).isNotNull();
    }

    @Test
    void testReportB() {
        ReportB report = new ReportB();
        report.generate();
        assertThat(report).isNotNull();
    }
}
