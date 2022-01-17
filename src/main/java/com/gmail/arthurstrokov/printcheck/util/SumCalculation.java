package com.gmail.arthurstrokov.printcheck.util;

import com.gmail.arthurstrokov.printcheck.model.Product;
import com.gmail.arthurstrokov.printcheck.model.Sale;
import com.gmail.arthurstrokov.printcheck.repository.ProductRepository;
import com.gmail.arthurstrokov.printcheck.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class SumCalculation {

    public static List<Sale> sumCalculation(List<String> checkInValues, Integer sizeCheckIn, ProductRepository productRepository, Integer cardDiscount, SaleRepository saleRepository) {
        List<Sale> saleList = new ArrayList<>();
//        BigDecimal cost = BigDecimal.ZERO;
//        BigDecimal percent = BigDecimal.ZERO;

        for (int i = 0; i < sizeCheckIn; i++) {
            String productInCheck = checkInValues.get(i);
            String[] parts = productInCheck.split("-");
            String productId = parts[0];
            long productSalesAmount = Integer.parseInt((parts[1]));

            Product product = productRepository.findById(Integer.parseInt(productId));
            BigDecimal productDiscountPercentage = product.getProductDiscountPercentage();
            BigDecimal productPrice = product.getProductPrice();
            BigDecimal productPriceDiscount = productPrice.multiply(productDiscountPercentage).divide(BigDecimal.valueOf(100), 2, RoundingMode.DOWN);

            BigDecimal productSalesPrice;
            if (productSalesAmount > 4) {
                productSalesPrice = productPrice.subtract(productPriceDiscount);
            } else {
                productSalesPrice = productPrice;
            }

//            cost = cost.add(productPrice.multiply(BigDecimal.valueOf(productSalesAmount)));

            Sale sale = new Sale();
            sale.setProduct(product);
            sale.setProductSalesPrice(productSalesPrice);
            sale.setProductSalesAmount(productSalesAmount);
            saleList.add(sale);
            saleRepository.save(sale);

//            PrintCheck.printCheckFile("check.txt", product, finalPrice, productAmount);

        }

//        if (cardDiscount > 0) {
//            percent = cost.multiply(BigDecimal.valueOf(cardDiscount)).divide(BigDecimal.valueOf(100), RoundingMode.DOWN);
//        }
//        BigDecimal total = cost.subtract(percent);

//        PrintCheck.printTotalConsole(cardDiscount, cost, percent, total);

//        PrintCheck.printTotalFile("check.txt", cardDiscount, cost, percent, total);
        return saleList;
    }
}
