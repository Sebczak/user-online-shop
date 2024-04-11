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
    private List<CartItem> cartItems = new ArrayList<>();
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User cartOwner;

    public Cart(Long basketId, List<CartItem> cartItems, BigDecimal totalPrice, User cartOwner) {
        this.basketId = basketId;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
        this.cartOwner = cartOwner;
    }

    public Cart(List<CartItem> cartItems, User cartOwner) {
        this.cartItems = cartItems;
        this.cartOwner = cartOwner;
    }

    public Cart(User cartOwner) {
        this.cartOwner = cartOwner;
    }

    public Cart() {
    }

    public Long getBasketId() {
        return basketId;
    }

    public void setBasketId(Long basketId) {
        this.basketId = basketId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getCartOwner() {
        return cartOwner;
    }

    public void setCartOwner(User cartOwner) {
        this.cartOwner = cartOwner;
    }

    @Override
    public String toString() {
        return "ProductBasket{" +
                "basketId=" + basketId +
                ", productBasketItem=" + cartItems +
                ", totalPrice=" + totalPrice +
                ", basketOwner=" + cartOwner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart that = (Cart) o;
        return Objects.equals(basketId, that.basketId) && Objects.equals(cartItems, that.cartItems) && Objects.equals(totalPrice, that.totalPrice) && Objects.equals(cartOwner, that.cartOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketId, cartItems, totalPrice, cartOwner);
    }

    public void addCartItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
        cartItem.setProductBasket(this);
    }
}
