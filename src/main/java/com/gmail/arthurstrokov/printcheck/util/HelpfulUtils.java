package com.gmail.arthurstrokov.printcheck.util;

import com.gmail.arthurstrokov.printcheck.model.Card;
import com.gmail.arthurstrokov.printcheck.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Create some random data class
 *
 * @author Arthur Strokov
 * @version 1.0
 */
@Service
public class HelpfulUtils {

    private static Random random;

    private HelpfulUtils() {
    }

    /**
     * Create random cards
     *
     * @return cards
     */
    public static List<Card> randomCards() {
        List<Card> cards = new ArrayList<>();
        Card card;
        random = new Random();
        for (long i = 0; i < 5; i++) {
            card = Card.newBuilder()
                    .discount(BigDecimal.valueOf(random.nextInt(5) + 1L))
                    .build();
            cards.add(card);
        }
        return cards;
    }

    /**
     * Create random products
     *
     * @return products
     */
    public static List<Product> randomProducts() {
        List<Product> products = new ArrayList<>();
        Product product;
        random = new Random();
        for (long i = 0; i < 10; i++) {
            product = Product.newBuilder()
                    .productName("Product " + i)
                    .productDiscountPercentage(BigDecimal.valueOf(random.nextInt(9) + 2L))
                    .productPrice(BigDecimal.valueOf(Math.random()).setScale(2, RoundingMode.HALF_DOWN))
                    .priceReduction(random.nextInt(10) + 1)
                    .build();
            products.add(product);
        }
        return products;
    }
}
