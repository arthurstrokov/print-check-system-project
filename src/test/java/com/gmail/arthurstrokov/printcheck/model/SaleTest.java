package com.gmail.arthurstrokov.printcheck.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {

    Sale sale;

    Product product;

    @BeforeEach
    void setUp() {
        sale = new Sale(product, BigDecimal.valueOf(2), BigDecimal.valueOf(4), 2);
    }

    @Test
    void getId() {
        assertNull(sale.getId());
        sale = new Sale();
        assertNull(sale.getId());
    }

    @Test
    void getProduct() {
        assertEquals(product, sale.getProduct());
    }

    @Test
    void getProductSalesPrice() {
        assertEquals(BigDecimal.valueOf(2), sale.getProductSalesPrice());
    }

    @Test
    void getProductSalesTotalPrice() {
        assertEquals(BigDecimal.valueOf(4), sale.getProductSalesTotalPrice());
    }

    @Test
    void getProductSalesAmount() {
        assertEquals(2, sale.getProductSalesAmount());
    }

    @Test
    void setId() {
        sale.setId(1L);
        assertEquals(1, sale.getId());
    }

    @Test
    void setProduct() {
        sale.setProduct(product);
        assertEquals(product, sale.getProduct());
    }

    @Test
    void setProductSalesPrice() {
        assertEquals(BigDecimal.valueOf(2), sale.getProductSalesPrice());
        sale.setProductSalesPrice(BigDecimal.valueOf(10));
        assertEquals(BigDecimal.valueOf(10), sale.getProductSalesPrice());
    }

    @Test
    void setProductSalesTotalPrice() {
        assertEquals(BigDecimal.valueOf(4), sale.getProductSalesTotalPrice());
        sale.setProductSalesTotalPrice(BigDecimal.valueOf(5));
        assertEquals(BigDecimal.valueOf(5), sale.getProductSalesTotalPrice());
    }

    @Test
    void setProductSalesAmount() {
        assertEquals(2, sale.getProductSalesAmount());
        assertNotEquals(4, sale.getProductSalesAmount());
        sale.setProductSalesAmount(5);
        assertEquals(5, sale.getProductSalesAmount());
    }
}
