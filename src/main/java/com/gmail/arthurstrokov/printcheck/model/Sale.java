package com.gmail.arthurstrokov.printcheck.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Product product;
    private BigDecimal productSalesPrice;
    private BigDecimal productSalesTotalPrice;
    private Integer productSalesAmount;

    public Sale(Product product, BigDecimal productSalesPrice, BigDecimal productSalesTotalPrice, Integer productSalesAmount) {
        this.product = product;
        this.productSalesPrice = productSalesPrice;
        this.productSalesTotalPrice = productSalesTotalPrice;
        this.productSalesAmount = productSalesAmount;
    }
}
