package com.gmail.arthurstrokov.printcheck.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String productName;
    private BigDecimal productDiscountPercentage;
    @NotNull
    private BigDecimal productPrice;
    @NotNull
    private Integer priceReduction; // Quantity at which a price reduction is possible

    public Product(String productName, BigDecimal productDiscountPercentage, BigDecimal productPrice, Integer priceReduction) {
        this.productName = productName;
        this.productDiscountPercentage = productDiscountPercentage;
        this.productPrice = productPrice;
        this.priceReduction = priceReduction;
    }
}
