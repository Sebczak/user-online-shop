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

    public Cart getCartItem() {
        return cart;
    }

    public void setCartItem(Cart cart) {
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
        return "ProductBasketItem{" +
                "productBasketItemId=" + cartItemId +
                ", product=" + product +
                ", quantityOfProductItemsInBasket=" + quantityOfCartItemsInCart +
                ", productBasket=" + cart +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem that = (CartItem) o;
        return quantityOfCartItemsInCart == that.quantityOfCartItemsInCart && Objects.equals(cartItemId, that.cartItemId) && Objects.equals(product, that.product) && Objects.equals(cart, that.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartItemId, product, quantityOfCartItemsInCart, cart);
    }
}
