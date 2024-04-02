package com.company.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_bucket")
public class ProductBasket {

    @Id
    @SequenceGenerator(
            name = "product_bucket_sequence",
            sequenceName = "product_bucket_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_bucket_sequence"
    )
    @Column(name = "bucket_id")
    private Long bucketId;

    @Column(
            name = "bucket_name",
            nullable = false
    )
    private String bucketName;
    @Column(
            name = "product",
            nullable = false
    )
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "join_product_bucket",
            joinColumns = {@JoinColumn(name = "bucket_id", referencedColumnName = "bucket_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "product_id")}
    )
    private List<Product> products = new ArrayList<>();
    @Column(
            name = "quantity",
            nullable = false
    )
    private int quantity;
    @Column(
            name = "total_price",
            nullable = false
    )
    private BigDecimal totalPrice;

    public ProductBasket(Long bucketId, String bucketName, List<Product> products, int quantity, BigDecimal totalPrice) {
        this.bucketId = bucketId;
        this.bucketName = bucketName;
        this.products = products;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public ProductBasket(String bucketName, List<Product> products, int quantity, BigDecimal totalPrice) {
        this.bucketName = bucketName;
        this.products = products;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public ProductBasket() {
    }

    public Long getBucketId() {
        return bucketId;
    }

    public void setBucketId(Long bucketId) {
        this.bucketId = bucketId;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
