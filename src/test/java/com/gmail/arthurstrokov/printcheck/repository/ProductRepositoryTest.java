package com.gmail.arthurstrokov.printcheck.repository;

import com.gmail.arthurstrokov.printcheck.model.Product;
import com.gmail.arthurstrokov.printcheck.util.InitialUtilsData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductRepositoryTest {

    ProductRepository productRepository = mock(ProductRepository.class);

    List<Product> products = InitialUtilsData.randomProducts();

    Product product = new Product();

    @BeforeEach
    public void init() {
        when(productRepository.findAll()).thenReturn(products);
        when(productRepository.findById(anyLong())).thenReturn(product);
    }

    @Test
    void findAll() {
        assertNotNull(productRepository.findAll());
        assertEquals(products, productRepository.findAll());
    }

    @Test
    void findById() {
        assertEquals(product, productRepository.findById(0));
    }
}