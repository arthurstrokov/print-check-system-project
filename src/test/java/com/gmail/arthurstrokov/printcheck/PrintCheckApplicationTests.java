package com.gmail.arthurstrokov.printcheck;

import com.gmail.arthurstrokov.printcheck.service.CardService;
import com.gmail.arthurstrokov.printcheck.service.InputService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PrintCheckApplicationTests {
    @Autowired
    private InputService inputService;
    @Autowired
    private CardService cardService;

    @Test
    void contextLoads() {
        assertTrue(true);
    }
}
