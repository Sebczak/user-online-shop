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
            name = "basket_name",
            nullable = false
    )
    private String basketName;
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
    private int quantityOfProductInBasket;
    @Column(
            name = "total_price"
    )
    private BigDecimal totalPrice;
    private String basketOwner;

    public ProductBasket(Long basketId, String basketName, List<Product> products, int quantityOfProductInBasket, BigDecimal totalPrice, String basketOwner) {
        this.basketId = basketId;
        this.basketName = basketName;
        this.products = products;
        this.quantityOfProductInBasket = quantityOfProductInBasket;
        this.totalPrice = totalPrice;
        this.basketOwner = basketOwner;
    }

    public ProductBasket(String basketName, List<Product> products, String basketOwner) {
        this.basketName = basketName;
        this.products = products;
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

    public String getBasketName() {
        return basketName;
    }

    public void setBasketName(String basketName) {
        this.basketName = basketName;
    }

    public int getQuantityOfProductInBasket() {
        return quantityOfProductInBasket;
    }

    public void setQuantityOfProductInBasket(int quantityOfProductInBasket) {
        this.quantityOfProductInBasket = quantityOfProductInBasket;
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

    public String getBasketOwner() {
        return basketOwner;
    }

    public void setBasketOwner(String basketOwner) {
        this.basketOwner = basketOwner;
    }
}
