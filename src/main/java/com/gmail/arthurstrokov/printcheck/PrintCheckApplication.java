package com.gmail.arthurstrokov.printcheck;

import com.gmail.arthurstrokov.printcheck.model.Card;
import com.gmail.arthurstrokov.printcheck.model.Product;
import com.gmail.arthurstrokov.printcheck.model.Sale;
import com.gmail.arthurstrokov.printcheck.repository.CardRepository;
import com.gmail.arthurstrokov.printcheck.repository.ProductRepository;
import com.gmail.arthurstrokov.printcheck.service.*;
import com.gmail.arthurstrokov.printcheck.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class PrintCheckApplication {
    private static final Logger log = LoggerFactory.getLogger(PrintCheckApplication.class);
    @Autowired
    private final Util util;
    @Autowired
    private final InputService inputService;
    @Autowired
    private final CardService cardService;
    @Autowired
    private final SaleService saleCalculationService;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final PrintService printService;
    @Autowired
    private final CardRepository cardRepository;
    @Autowired
    private final ProductRepository productRepository;

    public PrintCheckApplication(Util util,
                                 InputService inputService,
                                 CardService cardService,
                                 SaleService saleCalculationService,
                                 ProductService productService,
                                 PrintService printService, CardRepository cardRepository, ProductRepository productRepository) {
        this.util = util;
        this.inputService = inputService;
        this.cardService = cardService;
        this.saleCalculationService = saleCalculationService;
        this.productService = productService;
        this.printService = printService;
        this.cardRepository = cardRepository;
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PrintCheckApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            String fileName = "check.txt";
            boolean success = Files.deleteIfExists(Path.of(fileName));
            log.info("File deleted: " + success);

            // Create some Card/Product objects, add them in H2 DB
            List<Card> cardList = util.randomCards();
            List<Product> productList = util.randomProducts();
            cardRepository.saveAll(cardList);
            productRepository.saveAll(productList);

            // Take values from somewhere
            String input = inputService.readFromSomewhere(Path.of("demo.txt"));
            // Add values to list
            List<String> inputValuesList = new ArrayList<>(Arrays.asList(input.split(" ")));
            // If card exists, get discount
            BigDecimal cardDiscount = cardService.getCardDiscount(inputValuesList);
            int sizeValuesList = cardDiscount.compareTo(BigDecimal.ZERO) > 0 ? inputValuesList.size() - 1 : inputValuesList.size();
            // Get products from order
            Map<Product, Integer> products = productService.getProducts(inputValuesList, sizeValuesList);
            // Count products price sum
            List<Sale> saleList = saleCalculationService.sale(products);
            // Print result
            printService.totalCalculation(saleList, cardDiscount);
        };
    }
}
