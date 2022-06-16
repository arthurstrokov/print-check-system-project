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

/**
 * @author Arthur Strokov
 * @version 1.0
 */
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

    public Product(String productName, BigDecimal productDiscountPercentage, BigDecimal productPrice, int priceReduction) {
        this.productName = productName;
        this.productDiscountPercentage = productDiscountPercentage;
        this.productPrice = productPrice;
        this.priceReduction = priceReduction;
    }

    private Product(Builder builder) {
        setId(builder.id);
        setProductName(builder.productName);
        setProductDiscountPercentage(builder.productDiscountPercentage);
        setProductPrice(builder.productPrice);
        setPriceReduction(builder.priceReduction);
    }

    public static Builder newBuilder() {
        return new Builder();
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

    /**
     * {@code Product} builder static inner class.
     */
    public static final class Builder {
        private Long id;
        private @NotNull String productName;
        private BigDecimal productDiscountPercentage;
        private @NotNull BigDecimal productPrice;
        private @NotNull Integer priceReduction;

        private Builder() {
        }

        /**
         * Sets the {@code id} and returns a reference to this Builder enabling method chaining.
         *
         * @param val the {@code id} to set
         * @return a reference to this Builder
         */
        public Builder id(long val) {
            id = val;
            return this;
        }

        /**
         * Sets the {@code productName} and returns a reference to this Builder enabling method chaining.
         *
         * @param val the {@code productName} to set
         * @return a reference to this Builder
         */
        public Builder productName(@NotNull String val) {
            productName = val;
            return this;
        }

        /**
         * Sets the {@code productDiscountPercentage} and returns a reference to this Builder enabling method chaining.
         *
         * @param val the {@code productDiscountPercentage} to set
         * @return a reference to this Builder
         */
        public Builder productDiscountPercentage(BigDecimal val) {
            productDiscountPercentage = val;
            return this;
        }

        /**
         * Sets the {@code productPrice} and returns a reference to this Builder enabling method chaining.
         *
         * @param val the {@code productPrice} to set
         * @return a reference to this Builder
         */
        public Builder productPrice(@NotNull BigDecimal val) {
            productPrice = val;
            return this;
        }

        /**
         * Sets the {@code priceReduction} and returns a reference to this Builder enabling method chaining.
         *
         * @param val the {@code priceReduction} to set
         * @return a reference to this Builder
         */
        public Builder priceReduction(@NotNull int val) {
            priceReduction = val;
            return this;
        }

        /**
         * Returns a {@code Product} built from the parameters previously set.
         *
         * @return a {@code Product} built with parameters of this {@code Product.Builder}
         */
        public Product build() {
            return new Product(this);
        }
    }
}
