package com.gmail.arthurstrokov.printcheck.util;

import com.gmail.arthurstrokov.printcheck.model.Product;

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
}
