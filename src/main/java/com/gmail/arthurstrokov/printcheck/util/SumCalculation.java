package com.gmail.arthurstrokov.printcheck.util;

import com.gmail.arthurstrokov.printcheck.model.Product;
import com.gmail.arthurstrokov.printcheck.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class SumCalculation {
    public static void sumCalculation(List<String> checkInValues, Integer sizeCheckIn, ProductRepository productRepository, Integer cardDiscount) throws IOException {
        BigDecimal cost = BigDecimal.ZERO;
        BigDecimal percent = BigDecimal.ZERO;

        boolean success = (new File("check.txt")).delete();

        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter("check.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert out != null;
        out.write("cty: name:    price: finalPrice: total: \n");
        out.close();

        for (int i = 0; i < sizeCheckIn; i++) {
            String productInCheck = checkInValues.get(i);
            String[] parts = productInCheck.split("-");
            String productId = parts[0];
            int productAmount = Integer.parseInt((parts[1]));

            Product product = productRepository.findById(Integer.parseInt(productId));
            int productDiscount = product.getDiscount();
            BigDecimal productPrice = product.getPrice();

            if (productAmount >= 5) {
                BigDecimal productPriceDiscount = productPrice
                        .multiply(BigDecimal.valueOf(productDiscount))
                        .divide(BigDecimal.valueOf(100), RoundingMode.DOWN);
                productPrice = productPrice.subtract(productPriceDiscount);
            }

            BigDecimal finalPrice = productPrice;
            cost = cost.add(productPrice.multiply(BigDecimal.valueOf(productAmount)));

            PrintCheck.printCheckConsole(productAmount, product, finalPrice);

            try {
                PrintCheck.printCheckFile("check.txt", productAmount, product, finalPrice);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (cardDiscount > 0) {
            percent = cost.multiply(BigDecimal.valueOf(cardDiscount)).divide(BigDecimal.valueOf(100), RoundingMode.DOWN);
        }
        BigDecimal total = cost.subtract(percent);

        PrintCheck.printTotalConsole(cardDiscount, cost, percent, total);

        try {
            PrintCheck.printTotalFile("check.txt", cardDiscount, cost, percent, total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
