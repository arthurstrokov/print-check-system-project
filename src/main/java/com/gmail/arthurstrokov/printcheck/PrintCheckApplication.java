package com.gmail.arthurstrokov.printcheck;

import com.gmail.arthurstrokov.printcheck.model.Card;
import com.gmail.arthurstrokov.printcheck.model.Product;
import com.gmail.arthurstrokov.printcheck.repository.CardRepository;
import com.gmail.arthurstrokov.printcheck.repository.ProductRepository;
import com.gmail.arthurstrokov.printcheck.util.PrintCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class PrintCheckApplication {

    private static final Logger log = LoggerFactory.getLogger(PrintCheckApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PrintCheckApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx,
                                               ProductRepository productRepository,
                                               CardRepository cardRepository) {
        return args -> {
            // Create some Card/Product objects, add them in DB
            Random rn = new Random();
            for (long i = 0; i < 5; i++) {
                cardRepository.save(new Card(
                        rn.nextInt(10) + 1));
            }
            for (long i = 0; i < 10; i++) {
                productRepository.save(new Product(
                        "Product" + i,
                        rn.nextInt(10) + 1,
                        BigDecimal.valueOf(Math.random()).setScale(2, RoundingMode.DOWN)));
            }
            // Take values from somewhere
            String input = "4-3 2-1 9-2 8-4 8-5 1-4 card-3";
            // Add values to list
            List<String> checkIn = new ArrayList<>(Arrays.asList(input.split(" ")));
            int sizeCheckIn = checkIn.size();
            // If card exists, get discount
            int cardDiscount = 0;
            Pattern pattern = Pattern.compile("card*");
            for (String card : checkIn
            ) {
                Matcher matcher = pattern.matcher(card);
                boolean matchFound = matcher.find();
                if (matchFound) {
                    sizeCheckIn = checkIn.size() - 1;
                    String presentedCard = checkIn.get(checkIn.size() - 1);
                    String[] part = presentedCard.split("-");
                    String cardId = part[1];
                    Card availableCard = cardRepository.findById(Long.parseLong(cardId));
                    cardDiscount = availableCard.getDiscount();
                }
            }

            System.out.println("cty: name:    price: finalPrice: total: ");
            // Count products price sum
            BigDecimal cost = BigDecimal.ZERO;
            BigDecimal total = BigDecimal.ZERO;

            for (int i = 0; i < sizeCheckIn; i++) {
                String productInCheck = checkIn.get(i);
                String[] parts = productInCheck.split("-");
                String productId = parts[0];
                Product product = productRepository.findById(Integer.parseInt(productId));

                BigDecimal productPrice = product.getPrice();
                int productDiscount = product.getDiscount();
                int productAmount = Integer.parseInt((parts[1]));

                if (productAmount >= 5) {
                    BigDecimal productPriceDiscount = productPrice
                            .multiply(BigDecimal.valueOf(productDiscount))
                            .divide(BigDecimal.valueOf(100), RoundingMode.DOWN);
                    productPrice = productPrice.subtract(productPriceDiscount);
                }

                BigDecimal finalPrice = productPrice;
                cost = cost.add(productPrice.multiply(BigDecimal.valueOf(productAmount)));

                PrintCheck.printCheckConsole(productAmount, product, finalPrice);
            }

            BigDecimal percent = BigDecimal.ZERO;
            if (cardDiscount > 0) {
                percent = cost.multiply(BigDecimal.valueOf(cardDiscount)).divide(BigDecimal.valueOf(100), RoundingMode.DOWN);
            }
            total = cost.subtract(percent);

            PrintCheck.printTotalConsole(cardDiscount, cost, percent, total);
        };
    }
}
