package com.gmail.arthurstrokov.printcheck.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    Card card;

    @BeforeEach
    void setUp() {
        card = new Card();
    }

    @Test
    void getId() {
        assertNull(card.getId());
    }

    @Test
    void setId() {
        assertNull(card.getId());
        card.setId(1L);
        assertEquals(1, card.getId());
        assertNotEquals(2, card.getId());
    }

    @Test
    void getDiscount() {
        assertNull(card.getDiscount());
        card = new Card(BigDecimal.ZERO);
        assertNotNull(card.getDiscount());
    }

    @Test
    void setDiscount() {
        card.setDiscount(BigDecimal.TEN);
        assertEquals(BigDecimal.valueOf(10), card.getDiscount());
    }
}
