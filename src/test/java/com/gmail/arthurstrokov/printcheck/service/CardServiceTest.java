package com.gmail.arthurstrokov.printcheck.service;

import com.gmail.arthurstrokov.printcheck.model.Card;
import com.gmail.arthurstrokov.printcheck.repository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class CardServiceTest {

    InputService inputService = Mockito.mock(InputService.class);

    CardService cardService = Mockito.mock(CardService.class);

    CardRepository cardRepository = Mockito.mock(CardRepository.class);

    Card card;

    @BeforeEach
    void setUp() {
        card = new Card(BigDecimal.valueOf(10));
        card.setId(4L);
        when(inputService.readFromSomewhere(any(Path.class))).thenReturn("4-3 4-6 2-1 9-2 8-4 8-5 1-4 2-6 3-12 card-4");
        when(cardService.getCardDiscount(anyList())).thenReturn(BigDecimal.valueOf(10));
        when(cardRepository.findById(anyLong())).thenReturn(card);
    }

    @Test
    void getCardDiscount() {
        String input = inputService.readFromSomewhere(Path.of("test.txt"));
        List<String> inputValuesList = new ArrayList<>(Arrays.asList(input.split(" ")));
        BigDecimal cardDiscount = cardService.getCardDiscount(inputValuesList);
        assertNotNull(cardDiscount);
        assertEquals(BigDecimal.valueOf(10), cardDiscount);
        assertNotEquals(BigDecimal.valueOf(0), cardDiscount);
        assertEquals(cardDiscount, cardRepository.findById(4).getDiscount());
        assertDoesNotThrow(() -> cardRepository.findById(4));
    }
}
