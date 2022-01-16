package com.gmail.arthurstrokov.printcheck.util;

import com.gmail.arthurstrokov.printcheck.model.Product;
import com.gmail.arthurstrokov.printcheck.model.Sell;
import com.gmail.arthurstrokov.printcheck.repository.ProductRepository;
import com.gmail.arthurstrokov.printcheck.repository.SellRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class SumCalculation {

    public static void sumCalculation(List<String> checkInValues, Integer sizeCheckIn, ProductRepository productRepository, Integer cardDiscount, SellRepository sellRepository) {
        BigDecimal cost = BigDecimal.ZERO;
        BigDecimal percent = BigDecimal.ZERO;

        for (int i = 0; i < sizeCheckIn; i++) {
            String productInCheck = checkInValues.get(i);
            String[] parts = productInCheck.split("-");
            String productId = parts[0];
            long productAmount = Integer.parseInt((parts[1]));

            Product product = productRepository.findById(Integer.parseInt(productId));
            int productDiscount = product.getDiscount();
            BigDecimal productPrice = product.getPrice();
            String productName = product.getName();

            if (productAmount >= 5) {
                BigDecimal productPriceDiscount = productPrice
                        .multiply(BigDecimal.valueOf(productDiscount))
                        .divide(BigDecimal.valueOf(100), RoundingMode.DOWN);
                productPrice = productPrice.subtract(productPriceDiscount);
            }

            BigDecimal finalPrice = productPrice;
            cost = cost.add(productPrice.multiply(BigDecimal.valueOf(productAmount)));

            Sell sell = new Sell();
            sell.setProduct(product);
            sell.setAmount(productAmount);
            sellRepository.save(sell);

            Iterable<Sell> sells = sellRepository.findAllByProductId(Long.valueOf(productId));
            System.out.println(sells);

            PrintCheck.printCheckConsole(product, finalPrice, productAmount);

            try {
                PrintCheck.printCheckFile("check.txt", product, finalPrice, productAmount);
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
