package com.gmail.arthurstrokov.printcheck.service;

import com.gmail.arthurstrokov.printcheck.model.Product;
import com.gmail.arthurstrokov.printcheck.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Map<Product, Integer> getProducts(List<String> inputValuesList, Integer sizeValuesList) {
        Map<Product, Integer> products = new HashMap<>();
        for (int i = 0; i < sizeValuesList; i++) {
            String productInOrder = inputValuesList.get(i);
            String[] parts = productInOrder.split("-");
            try {
                String productId = parts[0];
                Product product = productRepository.findById(Integer.parseInt(productId));
                int productSalesAmount = Integer.parseInt((parts[1]));
                if (products.containsKey(product)) {
                    int oldValueProductSalesAmount = products.get(product);
                    products.put(product, oldValueProductSalesAmount + productSalesAmount);
                } else {
                    products.put(product, productSalesAmount);
                }
            } catch (Exception e) {
                log.info("{} {}", e.getMessage(), "...Check input values; in 'getProducts' method");
            }
        }
        return products;
    }
}
