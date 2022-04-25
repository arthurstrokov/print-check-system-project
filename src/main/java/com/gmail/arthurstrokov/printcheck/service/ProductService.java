package com.gmail.arthurstrokov.printcheck.service;

import com.gmail.arthurstrokov.printcheck.model.Product;
import com.gmail.arthurstrokov.printcheck.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that allowed get products
 *
 * @author Arthur Strokov
 * @version 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * @param inputValuesList List with products
     * @param sizeValuesList  List size
     * @return counted products
     */
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
                log.error("Check input values; in 'getProducts' method");
                log.error(e.getMessage());
            }
        }
        return products;
    }
}
