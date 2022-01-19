package com.gmail.arthurstrokov.printcheck.service;

import com.gmail.arthurstrokov.printcheck.model.Product;
import com.gmail.arthurstrokov.printcheck.model.Sale;
import com.gmail.arthurstrokov.printcheck.repository.ProductRepository;
import com.gmail.arthurstrokov.printcheck.repository.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleCalculationService {
    private static final Logger log = LoggerFactory.getLogger(SaleCalculationService.class);

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

            Product product = new Product();
            long productSalesAmount = 0;
            try {
                String productId = parts[0];
                productSalesAmount = Integer.parseInt((parts[1]));
                product = productRepository.findById(Integer.parseInt(productId));
            } catch (Exception e) {
                log.info(e.getMessage() + "...Check input values");
            }
            try {
                Sale sale = saleProduct(product, productSalesAmount);
                saleList.add(sale);
            } catch (NullPointerException e) {
                log.info(e.getMessage() + "...Check input values");
            }
        }
        return saleList;
    }

    public Sale saleProduct(Product product, Long productSalesAmount) {
        Sale sale = new Sale();
        BigDecimal productSalesTotalPrice = BigDecimal.ZERO;

        BigDecimal productDiscountPercentage = product.getProductDiscountPercentage();
        BigDecimal productPrice = product.getProductPrice();
        BigDecimal productPriceDiscount = productPrice.multiply(productDiscountPercentage).divide(BigDecimal.valueOf(100), 2, RoundingMode.DOWN);

        BigDecimal productSalesPrice = product.getPriceReduction() == 0 ? productPrice : productPrice.subtract(productPriceDiscount);

        productSalesTotalPrice = productSalesTotalPrice.add(product.getProductPrice()).multiply(BigDecimal.valueOf(productSalesAmount));

        sale.setProduct(product);
        sale.setProductSalesPrice(productSalesPrice);
        sale.setProductSalesAmount(productSalesAmount);
        sale.setProductSalesTotalPrice(productSalesTotalPrice);
        saleRepository.save(sale);
        return sale;
    }

    public void totalCalculation(List<Sale> saleList, BigDecimal cardDiscount) {
        printCheckService.printHeader();
        BigDecimal cost = BigDecimal.ZERO;
        for (Sale sale : saleList
        ) {
            printCheckService.printCheck(sale);
            cost = cost.add(sale.getProductSalesTotalPrice());
        }
        BigDecimal percent = BigDecimal.ZERO;
        if (cardDiscount.compareTo(BigDecimal.ZERO) > 0) {
            percent = cost.multiply(cardDiscount).divide(BigDecimal.valueOf(100), 2, RoundingMode.DOWN);
        }
        BigDecimal total = cost.subtract(percent);

        printCheckService.printTotal(cardDiscount, cost, percent, total);
    }
}
