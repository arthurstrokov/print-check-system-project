package com.gmail.arthurstrokov.printcheck.util;

import com.gmail.arthurstrokov.printcheck.model.Product;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class PrintCheck {
    public static void printCheckConsole(Integer productAmount, Product product, BigDecimal finalPrice) {
        System.out.println(
                productAmount + "  " +
                        product.getName() + "   " +
                        product.getPrice() + "   " +
                        finalPrice + "        " +
                        finalPrice.multiply(BigDecimal.valueOf(productAmount))
        );
    }

    public static void printTotalConsole(Integer cardDiscount, BigDecimal cost, BigDecimal percent, BigDecimal total) {
        System.out.println("-------------------------------------");
        System.out.println("Card discount:                   " + cardDiscount + " %");
        System.out.println("        Cost                     " + cost);
        System.out.println("        %                        " + percent);
        System.out.println("        Total:                   " + total);
    }

    public static void printCheckFile(String fileName, Integer productAmount, Product product, BigDecimal finalPrice) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        String content = String.join("\n",
                productAmount + "  " +
                        product.getName() + "   " +
                        product.getPrice() + "   " +
                        finalPrice + "        " +
                        finalPrice.multiply(BigDecimal.valueOf(productAmount))
        );
        writer.write(content);
        writer.append('\n');
        writer.close();

    }

    public static void printTotalFile(String fileName, Integer cardDiscount, BigDecimal cost, BigDecimal percent, BigDecimal total) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        String content = String.join("\n",
                "-------------------------------------",
                "Card discount:                   " + cardDiscount,
                "        Cost                     " + cost,
                "        %                        " + percent,
                "        Total:                   " + total
        );
        writer.write(content);
        writer.close();
    }
}
