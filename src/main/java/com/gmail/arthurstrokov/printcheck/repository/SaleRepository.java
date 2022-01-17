package com.gmail.arthurstrokov.printcheck.repository;

import com.gmail.arthurstrokov.printcheck.model.Sale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends CrudRepository<Sale, Long> {

}
