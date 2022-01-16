package com.gmail.arthurstrokov.printcheck.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Sell {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long amount;
    @ManyToOne
    private Product product;

    public Sell() {
    }

    public Sell(Long amount, Product product) {
        this.amount = amount;
        this.product = product;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sell sell = (Sell) o;
        return Objects.equals(id, sell.id) && Objects.equals(amount, sell.amount) && Objects.equals(product, sell.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, product);
    }

    @Override
    public String toString() {
        return "Sell{" +
                "id=" + id +
                ", amount=" + amount +
                ", product=" + product +
                '}';
    }
}
