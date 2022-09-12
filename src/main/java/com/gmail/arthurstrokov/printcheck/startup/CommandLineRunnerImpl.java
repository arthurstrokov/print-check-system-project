package com.gmail.arthurstrokov.printcheck.startup;

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
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Arthur Strokov
 * @email arthurstrokov@gmail.com
 * @created 12.09.2022
 */
@RequiredArgsConstructor
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CardRepository cardRepository;
    private final ProductRepository productRepository;
    private final CheckService checkService;
    private final PrintService printService;
    private final JavaMailSenderService javaMailSenderService;

    @Override
    public void run(String... args) throws Exception {
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
    }
}
