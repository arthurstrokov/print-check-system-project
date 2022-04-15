package com.gmail.arthurstrokov.printcheck.service;

import com.gmail.arthurstrokov.printcheck.model.Product;
import com.gmail.arthurstrokov.printcheck.model.Sale;
import com.gmail.arthurstrokov.printcheck.repository.SaleMDBRepository;
import com.gmail.arthurstrokov.printcheck.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class that present sale calculation
 *
 * @author Arthur Strokov
 */
@Service
@RequiredArgsConstructor
public class SaleService {
    private static final Logger log = LoggerFactory.getLogger(SaleService.class);

    private final SaleRepository saleRepository;
    private final SaleMDBRepository saleMDBRepository;

    /**
     * @param products counted products
     * @return sale list for next calculation and printing in check
     * @see #saleProduct(Product, Integer)
     */
    public List<Sale> sale(Map<Product, Integer> products) {
        List<Sale> saleList = new ArrayList<>();
        for (Map.Entry<Product, Integer> product : products.entrySet()) {
            try {
                Sale sale = saleProduct(product.getKey(), product.getValue());
                saleList.add(sale);
            } catch (NullPointerException e) {
                log.error("Check input values in 'sale' method. Seems like you don't have product with it's id");
                log.error(e.getMessage());
            }
        }
        return saleList;
    }

    /**
     * Save each sale in database
     * and return for the next calculation
     *
     * @param product            product
     * @param productSalesAmount product count
     * @return sale
     * @see #sale(Map)
     */
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
        saleMDBRepository.save(sale);
        return sale;
    }
}
