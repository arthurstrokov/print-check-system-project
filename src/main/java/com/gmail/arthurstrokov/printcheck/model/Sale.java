package com.gmail.arthurstrokov.printcheck.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Arthur Strokov
 * @version 1.0
 */
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

    private Sale(Builder builder) {
        setId(builder.id);
        setProduct(builder.product);
        setProductSalesPrice(builder.productSalesPrice);
        setProductSalesTotalPrice(builder.productSalesTotalPrice);
        setProductSalesAmount(builder.productSalesAmount);
    }

    public static Builder newBuilder() {
        return new Builder();
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

    /**
     * {@code Sale} builder static inner class.
     */
    public static final class Builder {
        private Long id;
        private Product product;
        private BigDecimal productSalesPrice;
        private BigDecimal productSalesTotalPrice;
        private Integer productSalesAmount;

        private Builder() {
        }

        /**
         * Sets the {@code id} and returns a reference to this Builder enabling method chaining.
         *
         * @param val the {@code id} to set
         * @return a reference to this Builder
         */
        public Builder id(Long val) {
            id = val;
            return this;
        }

        /**
         * Sets the {@code product} and returns a reference to this Builder enabling method chaining.
         *
         * @param val the {@code product} to set
         * @return a reference to this Builder
         */
        public Builder product(Product val) {
            product = val;
            return this;
        }

        /**
         * Sets the {@code productSalesPrice} and returns a reference to this Builder enabling method chaining.
         *
         * @param val the {@code productSalesPrice} to set
         * @return a reference to this Builder
         */
        public Builder productSalesPrice(BigDecimal val) {
            productSalesPrice = val;
            return this;
        }

        /**
         * Sets the {@code productSalesTotalPrice} and returns a reference to this Builder enabling method chaining.
         *
         * @param val the {@code productSalesTotalPrice} to set
         * @return a reference to this Builder
         */
        public Builder productSalesTotalPrice(BigDecimal val) {
            productSalesTotalPrice = val;
            return this;
        }

        /**
         * Sets the {@code productSalesAmount} and returns a reference to this Builder enabling method chaining.
         *
         * @param val the {@code productSalesAmount} to set
         * @return a reference to this Builder
         */
        public Builder productSalesAmount(Integer val) {
            productSalesAmount = val;
            return this;
        }

        /**
         * Returns a {@code Sale} built from the parameters previously set.
         *
         * @return a {@code Sale} built with parameters of this {@code Sale.Builder}
         */
        public Sale build() {
            return new Sale(this);
        }
    }
}
