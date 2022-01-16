package com.gmail.arthurstrokov.printcheck.repository;

import com.gmail.arthurstrokov.printcheck.model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

    List<Card> findAll();

    Card findById(long id);
}
