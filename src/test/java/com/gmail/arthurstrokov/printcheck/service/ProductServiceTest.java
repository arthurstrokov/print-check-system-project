package com.gmail.arthurstrokov.printcheck.service;

import com.gmail.arthurstrokov.printcheck.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    ProductService productService = Mockito.mock(ProductService.class);

    Map<Product, Integer> productIntegerMap = new HashMap<>();

    @BeforeEach
    void setUp() {
        when(productService.getProducts(anyList(), anyInt())).thenReturn(productIntegerMap);
    }

    @Test
    void getProducts() {
        List<String> list = new ArrayList<>();
        assertEquals(productIntegerMap, productService.getProducts(list, 0));
    }
}
