package com.gmail.arthurstrokov.printcheck.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Sale sale = (Sale) o;
        return id != null && Objects.equals(id, sale.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
