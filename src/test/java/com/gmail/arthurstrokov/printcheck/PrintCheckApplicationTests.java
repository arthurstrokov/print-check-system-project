package com.gmail.arthurstrokov.printcheck;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PrintCheckApplicationTests {

    @Autowired
    PrintCheckApplication printCheckApplication;

    @Test
    void contextLoads() {
        assertThat(printCheckApplication).isNotNull();

        assertTrue(true); // serves no real purpose

        Stream<Object> should_not_be_called = Stream.of().map(entry -> fail("should not be called"));
//        fail("Not yet implemented");
    }
}
