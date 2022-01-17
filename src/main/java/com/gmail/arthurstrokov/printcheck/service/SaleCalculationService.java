package com.gmail.arthurstrokov.printcheck.service;

import com.gmail.arthurstrokov.printcheck.model.Product;
import com.gmail.arthurstrokov.printcheck.model.Sale;
import com.gmail.arthurstrokov.printcheck.repository.ProductRepository;
import com.gmail.arthurstrokov.printcheck.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleCalculationService {
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final SaleRepository saleRepository;
    @Autowired
    private final PrintCheckService printCheckService;

    public SaleCalculationService(ProductRepository productRepository, SaleRepository saleRepository, PrintCheckService printCheckService) {
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
        this.printCheckService = printCheckService;
    }

    public List<Sale> saleOperation(List<String> inputValuesList, Integer sizeValuesList) {
        List<Sale> saleList = new ArrayList<>();

        for (int i = 0; i < sizeValuesList; i++) {
            String productInCheck = inputValuesList.get(i);
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

            Sale sale = new Sale();
            sale.setProduct(product);
            sale.setProductSalesPrice(productSalesPrice);
            sale.setProductSalesAmount(productSalesAmount);
            saleList.add(sale);
            saleRepository.save(sale);
        }
        return saleList;
    }

    public void totalCalculation(List<Sale> saleList, BigDecimal cardDiscount) {
        printCheckService.printHeader();
        BigDecimal cost = BigDecimal.ZERO;
        for (Sale sale : saleList
        ) {
            printCheckService.printCheck(sale);
            cost = cost.add(sale.getProduct().getProductPrice().multiply(BigDecimal.valueOf(sale.getProductSalesAmount())));
        }
        BigDecimal percent = BigDecimal.ZERO;
        if (cardDiscount.compareTo(BigDecimal.ZERO) > 0) {
            percent = cost.multiply(cardDiscount).divide(BigDecimal.valueOf(100), 2, RoundingMode.DOWN);
        }
        BigDecimal total = cost.subtract(percent);

        printCheckService.printTotal(cardDiscount, cost, percent, total);
    }
}
