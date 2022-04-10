package com.gmail.arthurstrokov.printcheck.controllers;

import com.gmail.arthurstrokov.printcheck.aspects.InspectingController;
import com.gmail.arthurstrokov.printcheck.exceptions.ProductNotFoundException;
import com.gmail.arthurstrokov.printcheck.model.Product;
import com.gmail.arthurstrokov.printcheck.repository.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Product rest controller
 *
 * @author Arthur Strokov
 */
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository repository;

    /**
     * @return List of products
     */
    @Operation(summary = "Get all products", description = "Return list of products")
    @InspectingController
    @GetMapping("/products")
    List<Product> getAllProducts() {
        return repository.findAll();
    }

    /**
     * @param id product id
     * @return Product found by id
     */
    @Operation(summary = "Get product", description = "Get product by it's id")
    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
