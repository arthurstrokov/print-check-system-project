package com.gmail.arthurstrokov.printcheck.service;

import com.gmail.arthurstrokov.printcheck.model.Sale;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Class that present service for printing check in file
 *
 * @author Arthur Strokov
 */
@Service
public class PrintService {
    /**
     * Save data to file
     *
     * @param string with data that saves in file
     */
    private void printToFile(String string) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("check.txt", true))) {
            out.write(string);
            out.append('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print check header
     */
    private void printHeader() {
        String string = String.format("%s %8s %15s %10s %10s", "qty", "name", "price", "fPrice", "total");
        System.out.println(string);
        printToFile(string);
    }

    /**
     * Print each product
     *
     * @param sale sale
     */
    private void printCheck(Sale sale) {
        String string = String.format("%s %15s %10s %10s %10s",
                sale.getProductSalesAmount(),
                sale.getProduct().getProductName(),
                sale.getProduct().getProductPrice(),
                sale.getProductSalesPrice(),
                sale.getProductSalesTotalPrice());
        System.out.println(string);
        printToFile(string);
    }

    /**
     * Print total
     *
     * @param cardDiscount card discount
     * @param cost         cost
     * @param percent      percent
     * @param total        total
     */
    private void printTotal(BigDecimal cardDiscount, BigDecimal cost, BigDecimal percent, BigDecimal total) {
        String string = String.join("\n",
                "--------------------------------------------------",
                "Card discount:                               " + cardDiscount,
                "         Cost:                               " + cost,
                "        %                                    " + percent,
                "        Total:                               " + total);
        System.out.println(string);
        printToFile(string);
    }

    public void totalCalculation(List<Sale> saleList, BigDecimal cardDiscount) {
        printHeader();
        BigDecimal cost = BigDecimal.ZERO;
        for (Sale sale : saleList
        ) {
            printCheck(sale);
            cost = cost.add(sale.getProductSalesTotalPrice());
        }
        BigDecimal percent = cardDiscount.compareTo(BigDecimal.ZERO) > 0 ? cost.multiply(cardDiscount).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_DOWN) : BigDecimal.ZERO;
        BigDecimal total = cost.subtract(percent);
        printTotal(cardDiscount, cost, percent, total);
    }
}
