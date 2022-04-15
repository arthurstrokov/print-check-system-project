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
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private BigDecimal discount;

    public Card(BigDecimal discount) {
        this.discount = discount;
    }

    private Card(Builder builder) {
        setId(builder.id);
        setDiscount(builder.discount);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Card card = (Card) o;
        return id != null && Objects.equals(id, card.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    /**
     * {@code Card} builder static inner class.
     */
    public static final class Builder {
        private Long id;
        private @NotNull BigDecimal discount;

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
         * Sets the {@code discount} and returns a reference to this Builder enabling method chaining.
         *
         * @param val the {@code discount} to set
         * @return a reference to this Builder
         */
        public Builder discount(@NotNull BigDecimal val) {
            discount = val;
            return this;
        }

        /**
         * Returns a {@code Card} built from the parameters previously set.
         *
         * @return a {@code Card} built with parameters of this {@code Card.Builder}
         */
        public Card build() {
            return new Card(this);
        }
    }
}
