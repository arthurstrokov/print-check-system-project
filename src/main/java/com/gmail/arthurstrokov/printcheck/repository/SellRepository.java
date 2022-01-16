package com.gmail.arthurstrokov.printcheck.repository;

import com.gmail.arthurstrokov.printcheck.model.Sell;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SellRepository extends CrudRepository<Sell, Long> {

    List<Sell> findAllByProductId(Long productId);
}
