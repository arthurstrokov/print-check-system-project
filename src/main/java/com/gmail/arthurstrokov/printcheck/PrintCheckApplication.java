package com.gmail.arthurstrokov.printcheck;

import com.gmail.arthurstrokov.printcheck.model.Card;
import com.gmail.arthurstrokov.printcheck.model.Product;
import com.gmail.arthurstrokov.printcheck.model.Sale;
import com.gmail.arthurstrokov.printcheck.repository.CardRepository;
import com.gmail.arthurstrokov.printcheck.repository.ProductRepository;
import com.gmail.arthurstrokov.printcheck.service.*;
import com.gmail.arthurstrokov.printcheck.util.HelpfulUtils;
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

/**
 * @author Arthur Strokov
 * @version 1.0
 */
@SpringBootApplication
public class PrintCheckApplication {

    private final InputService inputService;
    private final CardService cardService;
    private final SaleService saleCalculationService;
    private final ProductService productService;
    private final PrintService printService;
    private final CardRepository cardRepository;
    private final ProductRepository productRepository;

    public PrintCheckApplication(InputService inputService,
                                 CardService cardService,
                                 SaleService saleCalculationService,
                                 ProductService productService,
                                 PrintService printService,
                                 CardRepository cardRepository,
                                 ProductRepository productRepository) {
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
            Files.deleteIfExists(Path.of(fileName));

            // Create some Card/Product objects, add them in H2 DB
            List<Card> cardList = HelpfulUtils.randomCards();
            List<Product> productList = HelpfulUtils.randomProducts();
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
            // Print result both in console and file
            printService.totalCalculation(saleList, cardDiscount);
        };
    }
}
