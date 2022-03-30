package com.gmail.arthurstrokov.printcheck.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
