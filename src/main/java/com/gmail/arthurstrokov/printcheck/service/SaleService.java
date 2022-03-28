package com.gmail.arthurstrokov.printcheck.service;

import com.gmail.arthurstrokov.printcheck.model.Product;
import com.gmail.arthurstrokov.printcheck.model.Sale;
import com.gmail.arthurstrokov.printcheck.repository.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SaleService {
    private static final Logger log = LoggerFactory.getLogger(SaleService.class);
    @Autowired
    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<Sale> sale(Map<Product, Integer> products) {
        List<Sale> saleList = new ArrayList<>();
        for (Map.Entry<Product, Integer> product : products.entrySet()) {
            try {
                Sale sale = saleProduct(product.getKey(), product.getValue());
                saleList.add(sale);
            } catch (NullPointerException e) {
                log.error("{} {}", e.getMessage(), "...Check input values; in 'sale' method; seems like you don't have product with it's id");
            }
        }
        return saleList;
    }

    public Sale saleProduct(Product product, Integer productSalesAmount) {
        Sale sale = new Sale();
        BigDecimal productSalesTotalPrice = BigDecimal.ZERO;

        BigDecimal productDiscountPercentage = product.getProductDiscountPercentage();
        BigDecimal productPrice = product.getProductPrice();
        BigDecimal productPriceDiscount = productPrice.multiply(productDiscountPercentage).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_DOWN);

        BigDecimal productSalesPrice = product.getPriceReduction() == 0 ? productPrice : productPrice.subtract(productPriceDiscount);

        productSalesTotalPrice = productSalesTotalPrice.add(product.getProductPrice()).multiply(BigDecimal.valueOf(productSalesAmount));

        sale.setProduct(product);
        sale.setProductSalesPrice(productSalesPrice);
        sale.setProductSalesAmount(productSalesAmount);
        sale.setProductSalesTotalPrice(productSalesTotalPrice);
        saleRepository.save(sale);
        return sale;
    }
}
