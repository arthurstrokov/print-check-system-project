package com.gmail.arthurstrokov.printcheck.repository;

import com.gmail.arthurstrokov.printcheck.model.Sale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Arthur Strokov
 * @version 1.0
 */
@Repository
public interface SaleRepository extends CrudRepository<Sale, Long> {

}
