package com.gmail.arthurstrokov.printcheck;

import com.gmail.arthurstrokov.printcheck.model.Sale;
import com.gmail.arthurstrokov.printcheck.service.CardDiscountService;
import com.gmail.arthurstrokov.printcheck.service.InputService;
import com.gmail.arthurstrokov.printcheck.service.SaleCalculationService;
import com.gmail.arthurstrokov.printcheck.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class PrintCheckApplication {
    private static final Logger log = LoggerFactory.getLogger(PrintCheckApplication.class);
    @Autowired
    private final Util util;
    @Autowired
    private final InputService inputService;
    @Autowired
    private final CardDiscountService cardDiscountService;
    @Autowired
    private final SaleCalculationService saleCalculationService;

    public PrintCheckApplication(Util util, InputService inputService,
                                 CardDiscountService cardDiscountService,
                                 SaleCalculationService saleCalculationService) {
        this.util = util;
        this.inputService = inputService;
        this.cardDiscountService = cardDiscountService;
        this.saleCalculationService = saleCalculationService;
    }

    public static void main(String[] args) {
        SpringApplication.run(PrintCheckApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            String fileName = "check.txt";
            boolean success = (new File(fileName)).delete();
            log.info("File deleted: " + success);
            // Create some Card/Product objects, add them in DB
            util.util();
            // Take values from somewhere
            String input = inputService.readFromFile(Path.of("demo.txt"));
            // Add values to list
            List<String> inputValuesList = new ArrayList<>(Arrays.asList(input.split(" ")));
            int sizeValuesList = inputValuesList.size();
            // If card exists, get discount
            BigDecimal cardDiscount = cardDiscountService.getCardDiscount(inputValuesList);
            if (cardDiscount.compareTo(BigDecimal.ZERO) > 0) {
                sizeValuesList = sizeValuesList - 1;
            }
            // Count products price sum
            List<Sale> saleList = saleCalculationService.saleOperation(inputValuesList, sizeValuesList);
            saleCalculationService.totalCalculation(saleList, cardDiscount);
        };
    }
}
