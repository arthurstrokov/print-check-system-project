package com.gmail.arthurstrokov.printcheck.util;

import com.gmail.arthurstrokov.printcheck.model.Product;
import com.gmail.arthurstrokov.printcheck.model.Sale;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class PrintCheck {

    public static void printHeader() {
        System.out.println("cty: name:    price: finalPrice: total: ");
        try (BufferedWriter out = new BufferedWriter(new FileWriter("check.txt"))) {
            out.write("cty: name:    price: finalPrice: total: \n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printCheckConsole(Sale sale) {
        System.out.println(
                sale.getProductSalesAmount() + "    " +
                        sale.getProduct().getProductName() + "  " +
                        sale.getProduct().getProductPrice() + "   " +
                        sale.getProductSalesPrice() + " " +
                        sale.getProductSalesPrice().multiply(BigDecimal.valueOf(sale.getProductSalesAmount()))
        );
    }

    public static void printTotalConsole(Integer cardDiscount, BigDecimal cost, BigDecimal percent, BigDecimal total) {
        System.out.println("-------------------------------------");
        System.out.println("Card discount:                   " + cardDiscount + " %");
        System.out.println("        Cost                     " + cost);
        System.out.println("        %                        " + percent);
        System.out.println("        Total:                   " + total);
    }

    public static void printCheckFile(String fileName, Product product, BigDecimal finalPrice, Long productAmount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String content = String.join("\n",
                    productAmount + "  " +
                            product.getProductName() + "   " +
                            product.getProductPrice() + "   " +
                            finalPrice + "        " +
                            finalPrice.multiply(BigDecimal.valueOf(productAmount))
            );
            writer.write(content);
            writer.append('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void printTotalFile(String fileName, Integer cardDiscount, BigDecimal cost, BigDecimal percent, BigDecimal total) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String content = String.join("\n",
                    "-------------------------------------",
                    "Card discount:                   " + cardDiscount,
                    "        Cost                     " + cost,
                    "        %                        " + percent,
                    "        Total:                   " + total
            );
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
