package com.gmail.arthurstrokov.printcheck.repository;

import com.gmail.arthurstrokov.printcheck.model.Sale;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Arthur Strokov
 * @version 1.0
 */
@Repository
public interface SaleMDBRepository extends MongoRepository<Sale, Long> {
}
