package com.gmail.arthurstrokov.printcheck.service;

import com.gmail.arthurstrokov.printcheck.model.PurchaseData;
import com.gmail.arthurstrokov.printcheck.model.Product;
import com.gmail.arthurstrokov.printcheck.model.Sale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Class that executes the receipt printing logic
 *
 * @author Arthur Strokov
 */
@Service
@RequiredArgsConstructor
public class PrintCheckService {
    private final InputService inputService;
    private final CardService cardService;
    private final SaleService saleCalculationService;
    private final ProductService productService;
    private final PrintService printService;

    /**
     * @param fileName name file with store values
     */
    public void printCheckFromTxtFile(String fileName) {
        // Take values from file
        String input = inputService.readFromSomewhere(Path.of(fileName));
        // Add values to list
        List<String> inputValuesList = new ArrayList<>(Arrays.asList(input.split(" ")));
        // If card exists, get discount
        BigDecimal cardDiscount = cardService.getCardDiscount(inputValuesList);
        // List size
        int sizeValuesList = cardDiscount.compareTo(BigDecimal.ZERO) > 0 ? inputValuesList.size() - 1 : inputValuesList.size();
        // Get products from order
        Map<Product, Integer> products = productService.getProducts(inputValuesList, sizeValuesList);
        // Count products price sum
        List<Sale> saleList = saleCalculationService.sale(products);
        // Print result both in console and file
        printService.totalCalculation(saleList, cardDiscount);
    }

    /**
     * @param fileName name file with store values
     */
    public void printCheckFromJsonFile(String fileName) {
        // Take values from Json file
        PurchaseData purchaseData = inputService.readFromJson(Path.of(fileName));
        // Add values to list
        List<String> inputValuesList = new ArrayList<>(purchaseData.getProducts());
        // If card exists, get discount
        BigDecimal cardDiscount = cardService.getCardDiscount(purchaseData.getCard());
        // List size
        int sizeValuesList = inputValuesList.size();
        // Get products from order
        Map<Product, Integer> products = productService.getProducts(inputValuesList, sizeValuesList);
        // Count products price sum
        List<Sale> saleList = saleCalculationService.sale(products);
        // Print result both in console and file
        printService.totalCalculation(saleList, cardDiscount);
    }
}
