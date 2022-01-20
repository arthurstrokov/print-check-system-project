package com.gmail.arthurstrokov.printcheck;

import com.gmail.arthurstrokov.printcheck.service.CardService;
import com.gmail.arthurstrokov.printcheck.service.InputService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@SpringBootTest
class PrintCheckApplicationTests {
    @Autowired
    private InputService inputService;
    @Autowired
    private CardService cardService;

    @Test
    void contextLoads() {

    }

    @Test
    void test() {
        String inputFromFile = inputService.readFromSomewhere(Path.of("demoTest.txt"));
        assertEquals(inputFromFile, "4-3 4-6 2-1 9-2 8-4 8-5 1-4 2-6 3-12 card-4");
        Assertions.assertNotEquals(inputFromFile, "4-3 4-6 2-1");

        List<String> inputValuesList = new ArrayList<>(Arrays.asList(inputFromFile.split(" ")));
        BigDecimal cardDiscount = cardService.getCardDiscount(inputValuesList);
        System.out.println(cardDiscount);
    }
}