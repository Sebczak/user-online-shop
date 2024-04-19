package com.company.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long cartItemId;

    @OneToOne
    private Product product;

    private int quantityOfCartItemsInCart;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public CartItem(Long cartItemId, Product product, int quantityOfCartItemsInCart, Cart cart) {
        this.cartItemId = cartItemId;
        this.product = product;
        this.quantityOfCartItemsInCart = quantityOfCartItemsInCart;
        this.cart = cart;
    }

    public CartItem(Product product, int quantityOfCartItemsInCart, Cart cart) {
        this.product = product;
        this.quantityOfCartItemsInCart = quantityOfCartItemsInCart;
        this.cart = cart;
    }

    public CartItem() {
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long productBasketItemId) {
        this.cartItemId = productBasketItemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getQuantityOfCartItemsInCart() {
        return quantityOfCartItemsInCart;
    }

    public void setQuantityOfCartItemsInCart(int quantityOfProductItemsInBasket) {
        this.quantityOfCartItemsInCart = quantityOfProductItemsInBasket;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "quantityOfCartItemsInCart=" + quantityOfCartItemsInCart +
                ", product=" + product +
                ", cartItemId=" + cartItemId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return quantityOfCartItemsInCart == cartItem.quantityOfCartItemsInCart && Objects.equals(cartItemId, cartItem.cartItemId) && Objects.equals(product, cartItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartItemId, product, quantityOfCartItemsInCart);
    }
}
