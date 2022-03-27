package com.gmail.arthurstrokov.printcheck;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.assertj.core.api.Fail.fail;

@SpringBootTest
class PrintCheckApplicationTests {

    @Test
    void contextLoads() {
        Stream<Object> should_not_be_called = Stream.of().map(entry -> fail("should not be called"));
//        fail("Not yet implemented");
    }
}
