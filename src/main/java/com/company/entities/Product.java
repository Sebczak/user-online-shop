package com.company.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private long id;
    private String productName;
    private String productDescription;
    private double price;

    public Product(long id, String productName, String productDescription, double price) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
    }

    public Product(String productName, String productDescription, double price) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
    }

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Double.compare(price, product.price) == 0 && Objects.equals(productName, product.productName) && Objects.equals(productDescription, product.productDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, productDescription, price);
    }
}
