package com.gmail.arthurstrokov.printcheck;

import com.gmail.arthurstrokov.printcheck.model.Card;
import com.gmail.arthurstrokov.printcheck.model.Product;
import com.gmail.arthurstrokov.printcheck.model.Sale;
import com.gmail.arthurstrokov.printcheck.repository.CardRepository;
import com.gmail.arthurstrokov.printcheck.repository.ProductRepository;
import com.gmail.arthurstrokov.printcheck.repository.SaleRepository;
import com.gmail.arthurstrokov.printcheck.service.InputService;
import com.gmail.arthurstrokov.printcheck.util.GetCardDiscount;
import com.gmail.arthurstrokov.printcheck.util.PrintCheck;
import com.gmail.arthurstrokov.printcheck.util.SumCalculation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootApplication
public class PrintCheckApplication {

    private static final Logger log = LoggerFactory.getLogger(PrintCheckApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PrintCheckApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx,
                                               ProductRepository productRepository,
                                               CardRepository cardRepository,
                                               SaleRepository sellRepository) {
        return args -> {
            boolean success = (new File("check.txt")).delete();
            log.info(String.valueOf(success));
            // Create some Card/Product objects, add them in DB
            Random rn = new Random();
            for (long i = 0; i < 5; i++) {
                cardRepository.save(new Card(
                        rn.nextInt(5) + 1));
            }
            for (long i = 0; i < 10; i++) {
                productRepository.save(new Product(
                        "Product" + i,
                        BigDecimal.valueOf(rn.nextInt(9) + 2),
                        BigDecimal.valueOf(Math.random()).setScale(2, RoundingMode.DOWN)));
            }
            // Take values from somewhere
            String input = InputService.readFrom(Path.of("demo.txt"));
            // Add values to list
            List<String> checkIn = new ArrayList<>(Arrays.asList(input.split(" ")));
            int sizeCheckIn = checkIn.size();
            // If card exists, get discount
            Integer cardDiscount = GetCardDiscount.getCardDiscount(checkIn, cardRepository);
            if (cardDiscount > 0) {
                sizeCheckIn = sizeCheckIn - 1;
            }

            // Count products price sum
            PrintCheck.printHeader();
            List<Sale> saleList = SumCalculation.sumCalculation(checkIn, sizeCheckIn, productRepository, cardDiscount, sellRepository);


            BigDecimal cost = BigDecimal.ZERO;
            for (Sale sale : saleList
            ) {
                PrintCheck.printCheckConsole(sale);
                cost = cost.add(sale.getProduct().getProductPrice().multiply(BigDecimal.valueOf(sale.getProductSalesAmount())));
            }
            BigDecimal percent = BigDecimal.ZERO;
            if (cardDiscount > 0) {
                percent = cost.multiply(BigDecimal.valueOf(cardDiscount)).divide(BigDecimal.valueOf(100), RoundingMode.DOWN);
            }
            BigDecimal total = cost.subtract(percent);
            PrintCheck.printTotalConsole(cardDiscount, cost, percent, total);
        };
    }
}
