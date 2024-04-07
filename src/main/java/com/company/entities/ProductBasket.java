package com.company.entities;

import com.company.domain.ProductBasketItemDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product_basket")
public class ProductBasket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "basket_id")
    private Long basketId;
    @Column(name = "products")
    @OneToMany(
            targetEntity = ProductBasketItem.class,
            mappedBy = "productBasket",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<ProductBasketItemDto> productBasketItem = new ArrayList<>();
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User basketOwner;

    public ProductBasket(Long basketId, List<ProductBasketItemDto> productBasketItem, BigDecimal totalPrice, User basketOwner) {
        this.basketId = basketId;
        this.productBasketItem = productBasketItem;
        this.totalPrice = totalPrice;
        this.basketOwner = basketOwner;
    }

    public ProductBasket(List<ProductBasketItemDto> productBasketItem, User basketOwner) {
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

    public List<ProductBasketItemDto> getProductBasketItem() {
        return productBasketItem;
    }

    public void setProductBasketItem(List<ProductBasketItemDto> productBasketItem) {
        this.productBasketItem = productBasketItem;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getBasketOwner() {
        return basketOwner;
    }

    public void setBasketOwner(User basketOwner) {
        this.basketOwner = basketOwner;
    }

    @Override
    public String toString() {
        return "ProductBasket{" +
                "basketId=" + basketId +
                ", productBasketItem=" + productBasketItem +
                ", totalPrice=" + totalPrice +
                ", basketOwner=" + basketOwner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductBasket that = (ProductBasket) o;
        return Objects.equals(basketId, that.basketId) && Objects.equals(productBasketItem, that.productBasketItem) && Objects.equals(totalPrice, that.totalPrice) && Objects.equals(basketOwner, that.basketOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketId, productBasketItem, totalPrice, basketOwner);
    }
}
