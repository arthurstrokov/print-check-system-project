package com.gmail.arthurstrokov.printcheck.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

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

    public Product() {
    }

    public Product(String productName, BigDecimal productDiscountPercentage, BigDecimal productPrice, Integer priceReduction) {
        this.productName = productName;
        this.productDiscountPercentage = productDiscountPercentage;
        this.productPrice = productPrice;
        this.priceReduction = priceReduction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductDiscountPercentage() {
        return productDiscountPercentage;
    }

    public void setProductDiscountPercentage(BigDecimal productDiscountPercentage) {
        this.productDiscountPercentage = productDiscountPercentage;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getPriceReduction() {
        return priceReduction;
    }

    public void setPriceReduction(Integer priceReduction) {
        this.priceReduction = priceReduction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(productName, product.productName) && Objects.equals(productDiscountPercentage, product.productDiscountPercentage) && Objects.equals(productPrice, product.productPrice) && Objects.equals(priceReduction, product.priceReduction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, productDiscountPercentage, productPrice, priceReduction);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productDiscountPercentage=" + productDiscountPercentage +
                ", productPrice=" + productPrice +
                ", priceReduction=" + priceReduction +
                '}';
    }
}
