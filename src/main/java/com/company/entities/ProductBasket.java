package com.company.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_basket")
public class ProductBasket {

    @Id
    @SequenceGenerator(
            name = "product_basket_sequence",
            sequenceName = "product_basket_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_basket_sequence"
    )
    @Column(name = "basket_id")
    private Long basketId;
    @Column(
            name = "product",
            nullable = false
    )
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "join_product_basket",
            joinColumns = {@JoinColumn(name = "basket_id", referencedColumnName = "basket_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "product_id")}
    )
    private List<Product> products = new ArrayList<>();
    @Column(
            name = "quantity"
    )
    @OneToMany(
            targetEntity = ProductBasketItem.class,
            mappedBy = "productBasket",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<ProductBasketItem> productBasketItem = new ArrayList<>();
    @Column(
            name = "total_price"
    )
    private BigDecimal totalPrice;
    @OneToOne
    private User basketOwner;

    public ProductBasket(Long basketId, List<Product> products, List<ProductBasketItem> productBasketItem, BigDecimal totalPrice, User basketOwner) {
        this.basketId = basketId;
        this.products = products;
        this.productBasketItem = productBasketItem;
        this.totalPrice = totalPrice;
        this.basketOwner = basketOwner;
    }

    public ProductBasket(List<Product> products, List<ProductBasketItem> productBasketItem, User basketOwner) {
        this.products = products;
        this.productBasketItem = productBasketItem;
        this.basketOwner = basketOwner;
    }

    public ProductBasket() {
    }

    public Long getBasketId() {
        return basketId;
    }

    public void setBasketId(Long basketId) {
        this.basketId = basketId;
    }

    public List<ProductBasketItem> getQuantityOfProductInBasket() {
        return productBasketItem;
    }

    public void setQuantityOfProductInBasket(List<ProductBasketItem> productBasketItem) {
        this.productBasketItem = productBasketItem;
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

    public User getBasketOwner() {
        return basketOwner;
    }

    public void setBasketOwner(User basketOwner) {
        this.basketOwner = basketOwner;
    }
}
