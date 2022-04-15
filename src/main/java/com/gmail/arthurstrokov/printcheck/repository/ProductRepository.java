package com.gmail.arthurstrokov.printcheck.repository;

import com.gmail.arthurstrokov.printcheck.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Arthur Strokov
 * @version 1.0
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAll();

    Product findById(long id);
}