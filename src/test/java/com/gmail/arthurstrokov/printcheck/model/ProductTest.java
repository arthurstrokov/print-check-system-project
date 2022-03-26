package com.gmail.arthurstrokov.printcheck.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    Product product;

    @BeforeEach
    void setUp() {
        product = new Product("Product", BigDecimal.ZERO, BigDecimal.ONE, 0);
    }

    @Test
    void getId() {
        assertNull(product.getId());
        product = new Product();
        assertNull(product.getId());
    }

    @Test
    void getProductName() {
        assertNotNull(product.getProductName());
        assertEquals("Product", product.getProductName());
    }

    @Test
    void getProductDiscountPercentage() {
        assertNotNull(product.getProductDiscountPercentage());
        assertEquals(BigDecimal.ZERO, product.getProductDiscountPercentage());
    }

    @Test
    void getProductPrice() {
        assertNotNull(product.getProductPrice());
        assertEquals(BigDecimal.ONE, product.getProductPrice());
    }

    @Test
    void getPriceReduction() {
        assertNotNull(product.getPriceReduction());
        assertEquals(0, product.getPriceReduction());
    }

    @Test
    void setId() {
        product.setId(1L);
        assertEquals(1, product.getId());
    }

    @Test
    void setProductName() {
        product.setProductName("Nestle");
        assertEquals("Nestle", product.getProductName());
    }

    @Test
    void setProductDiscountPercentage() {
        product.setProductDiscountPercentage(BigDecimal.valueOf(42));
        assertEquals(BigDecimal.valueOf(42), product.getProductDiscountPercentage());
    }

    @Test
    void setProductPrice() {
        product.setProductPrice(BigDecimal.valueOf(0.21));
        assertEquals(BigDecimal.valueOf(0.21), product.getProductPrice());
    }

    @Test
    void setPriceReduction() {
        assertEquals(0, product.getPriceReduction());
        product.setPriceReduction(7);
        assertEquals(7, product.getPriceReduction());
    }
}
