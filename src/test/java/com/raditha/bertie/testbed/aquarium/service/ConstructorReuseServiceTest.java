package com.raditha.bertie.testbed.aquarium.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ConstructorReuseServiceTest {

    @Test
    void testNoArgConstructor() {
        ConstructorReuseService service = new ConstructorReuseService();
        assertThat(service).isNotNull();
    }

    @Test
    void testStringConstructor() {
        ConstructorReuseService service = new ConstructorReuseService("test-name");
        assertThat(service).isNotNull();
    }
}
