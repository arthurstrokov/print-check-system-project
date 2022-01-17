package com.gmail.arthurstrokov.printcheck.service;

import com.gmail.arthurstrokov.printcheck.model.Sale;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

@Service
public class PrintCheckService {

    private void print(String string) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("check.txt", true))) {
            out.write(string);
            out.append('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void printHeader() {
        String string = "quantity:   name:   price:  finalPrice: total: ";
        System.out.println(string);
        print(string);
    }

    void printCheck(Sale sale) {
        String string = String.join("\n",
                sale.getProductSalesAmount() + "        " +
                        sale.getProduct().getProductName() + "      " +
                        sale.getProduct().getProductPrice() + "     " +
                        sale.getProductSalesPrice() + "     " +
                        sale.getProductSalesTotalPrice());
        System.out.println(string);
        print(string);
    }

    void printTotal(BigDecimal cardDiscount, BigDecimal cost, BigDecimal percent, BigDecimal total) {
        String string = String.join("\n",
                "-------------------------------------",
                "Card discount:                   " + cardDiscount,
                "        Cost                     " + cost,
                "        %                        " + percent,
                "        Total:                   " + total);
        System.out.println(string);
        print(string);
    }
}
