package com.gmail.arthurstrokov.printcheck.util;

import com.gmail.arthurstrokov.printcheck.model.Card;
import com.gmail.arthurstrokov.printcheck.model.Product;
import com.gmail.arthurstrokov.printcheck.repository.CardRepository;
import com.gmail.arthurstrokov.printcheck.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@Service
public class Util {
    @Autowired
    private final CardRepository cardRepository;
    @Autowired
    private final ProductRepository productRepository;

    public Util(CardRepository cardRepository, ProductRepository productRepository) {
        this.cardRepository = cardRepository;
        this.productRepository = productRepository;
    }

    public void util() {
        Random rn = new Random();
        for (long i = 0; i < 5; i++) {
            cardRepository.save(new Card(
                    BigDecimal.valueOf(rn.nextInt(5) + 1)));
        }
        for (long i = 0; i < 10; i++) {
            productRepository.save(new Product(
                    "Product " + i,
                    BigDecimal.valueOf(rn.nextInt(9) + 2),
                    BigDecimal.valueOf(Math.random()).setScale(2, RoundingMode.HALF_DOWN),
                    rn.nextInt(10) + 1));
        }
    }
}
