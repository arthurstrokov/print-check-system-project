package com.gmail.arthurstrokov.printcheck.repository;

import com.gmail.arthurstrokov.printcheck.model.Card;
import com.gmail.arthurstrokov.printcheck.util.RandomData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CardRepositoryTest {

    CardRepository cardRepository = mock(CardRepository.class);

    List<Card> cards = RandomData.randomCards();

    Card card = new Card();

    @BeforeEach
    public void init() {
        when(cardRepository.findAll()).thenReturn(cards);
        when(cardRepository.findById(anyLong())).thenReturn(card);
    }

    @Test
    void findAll() {
        assertNotNull(cardRepository.findAll());
        assertEquals(cards, cardRepository.findAll());
    }

    @Test
    void findById() {
        assertEquals(card, cardRepository.findById(4));
    }
}