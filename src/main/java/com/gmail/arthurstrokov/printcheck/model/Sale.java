package com.gmail.arthurstrokov.printcheck.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Product product;
    private BigDecimal productSalesPrice;
    private Long productSalesAmount;

    public Sale() {
    }

    public Sale(Product product, BigDecimal productSalesPrice, Long productSalesAmount) {
        this.product = product;
        this.productSalesPrice = productSalesPrice;
        this.productSalesAmount = productSalesAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getProductSalesPrice() {
        return productSalesPrice;
    }

    public void setProductSalesPrice(BigDecimal productSalesPrice) {
        this.productSalesPrice = productSalesPrice;
    }

    public Long getProductSalesAmount() {
        return productSalesAmount;
    }

    public void setProductSalesAmount(Long productSalesAmount) {
        this.productSalesAmount = productSalesAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return Objects.equals(id, sale.id) && Objects.equals(product, sale.product) && Objects.equals(productSalesPrice, sale.productSalesPrice) && Objects.equals(productSalesAmount, sale.productSalesAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, productSalesPrice, productSalesAmount);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", product=" + product +
                ", productSalesPrice=" + productSalesPrice +
                ", productSalesAmount=" + productSalesAmount +
                '}';
    }
}
