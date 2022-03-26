package com.gmail.arthurstrokov.printcheck.util;

import com.gmail.arthurstrokov.printcheck.model.Card;
import com.gmail.arthurstrokov.printcheck.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class HelpfulUtils {

    private static Random random;

    private HelpfulUtils() {
    }

    public static List<Card> randomCards() {
        List<Card> cards = new ArrayList<>();
        Card card;
        random = new Random();
        for (long i = 0; i < 5; i++) {
            card = new Card(BigDecimal.valueOf(random.nextInt(5) + 1L));
            cards.add(card);
        }
        return cards;
    }

    public static List<Product> randomProducts() {
        List<Product> products = new ArrayList<>();
        Product product;
        random = new Random();
        for (long i = 0; i < 10; i++) {
            product = new Product(
                    "Product " + i,
                    BigDecimal.valueOf(random.nextInt(9) + 2L),
                    BigDecimal.valueOf(Math.random()).setScale(2, RoundingMode.HALF_DOWN),
                    random.nextInt(10) + 1);
            products.add(product);
        }
        return products;
    }
}
