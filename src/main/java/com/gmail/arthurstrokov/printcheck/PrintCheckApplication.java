package com.gmail.arthurstrokov.printcheck;

import com.gmail.arthurstrokov.printcheck.model.Card;
import com.gmail.arthurstrokov.printcheck.model.Product;
import com.gmail.arthurstrokov.printcheck.repository.CardRepository;
import com.gmail.arthurstrokov.printcheck.repository.ProductRepository;
import com.gmail.arthurstrokov.printcheck.service.CheckService;
import com.gmail.arthurstrokov.printcheck.service.JavaMailSenderService;
import com.gmail.arthurstrokov.printcheck.service.PrintService;
import com.gmail.arthurstrokov.printcheck.util.HelpfulUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Arthur Strokov
 * @version 1.0
 */
@SpringBootApplication
@RequiredArgsConstructor
public class PrintCheckApplication {
    private final CardRepository cardRepository;
    private final ProductRepository productRepository;
    private final CheckService checkService;
    private final PrintService printService;
    private final JavaMailSenderService javaMailSenderService;

    public static void main(String[] args) {
        SpringApplication.run(PrintCheckApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            String fileName = "check.txt";
            Files.deleteIfExists(Path.of(fileName));
            // Create some Card/Product objects, add them in DB
            List<Card> cardList = HelpfulUtils.randomCards();
            List<Product> productList = HelpfulUtils.randomProducts();
            // Save objects in DB
            cardRepository.saveAll(cardList);
            productRepository.saveAll(productList);
            // Subscribe service for JsonFile event type
            checkService.getEventManager().subscribe("JsonFile", javaMailSenderService);
            // Print check
            checkService.createCheckFromJsonFilePurchaseData("inputValues.json");
            // Print links
            printService.printLinks();
        };
    }
}
