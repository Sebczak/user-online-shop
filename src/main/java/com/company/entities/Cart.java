package com.company.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product_basket")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "basket_id")
    private Long basketId;
    @OneToMany(
            targetEntity = CartItem.class,
            mappedBy = "cart",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<CartItem> productBasketItem = new ArrayList<>();
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User basketOwner;

    public Cart(Long basketId, List<CartItem> productBasketItem, BigDecimal totalPrice, User basketOwner) {
        this.basketId = basketId;
        this.productBasketItem = productBasketItem;
        this.totalPrice = totalPrice;
        this.basketOwner = basketOwner;
    }

    public Cart(List<CartItem> productBasketItem, User basketOwner) {
        this.productBasketItem = productBasketItem;
        this.basketOwner = basketOwner;
    }

    public Cart() {
    }

    public Long getBasketId() {
        return basketId;
    }

    public void setBasketId(Long basketId) {
        this.basketId = basketId;
    }

    public List<CartItem> getProductBasketItem() {
        return productBasketItem;
    }

    public void setProductBasketItem(List<CartItem> productBasketItem) {
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
        Cart that = (Cart) o;
        return Objects.equals(basketId, that.basketId) && Objects.equals(productBasketItem, that.productBasketItem) && Objects.equals(totalPrice, that.totalPrice) && Objects.equals(basketOwner, that.basketOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketId, productBasketItem, totalPrice, basketOwner);
    }

    public void addCartItem(CartItem cartItem) {
        this.productBasketItem.add(cartItem);
        cartItem.setProductBasket(this);
    }
}
