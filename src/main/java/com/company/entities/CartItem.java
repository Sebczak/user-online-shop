package com.company.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "product_basket_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_basket_item_id")
    private Long productBasketItemId;

    @OneToOne
    private Product product;

    private int quantityOfProductItemsInBasket;
    @ManyToOne
    @JoinColumn(name = "basket_id")
    private Cart cart;

    public CartItem(Long productBasketItemId, Product product, int quantityOfProductItemsInBasket, Cart cart) {
        this.productBasketItemId = productBasketItemId;
        this.product = product;
        this.quantityOfProductItemsInBasket = quantityOfProductItemsInBasket;
        this.cart = cart;
    }

    public CartItem(Product product, int quantityOfProductItemsInBasket, Cart cart) {
        this.product = product;
        this.quantityOfProductItemsInBasket = quantityOfProductItemsInBasket;
        this.cart = cart;
    }

    public CartItem() {
    }

    public Long getProductBasketItemId() {
        return productBasketItemId;
    }

    public void setProductBasketItemId(Long productBasketItemId) {
        this.productBasketItemId = productBasketItemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getProductBasket() {
        return cart;
    }

    public void setProductBasket(Cart cart) {
        this.cart = cart;
    }

    public int getQuantityOfProductItemsInBasket() {
        return quantityOfProductItemsInBasket;
    }

    public void setQuantityOfProductItemsInBasket(int quantityOfProductItemsInBasket) {
        this.quantityOfProductItemsInBasket = quantityOfProductItemsInBasket;
    }

    @Override
    public String toString() {
        return "ProductBasketItem{" +
                "productBasketItemId=" + productBasketItemId +
                ", product=" + product +
                ", quantityOfProductItemsInBasket=" + quantityOfProductItemsInBasket +
                ", productBasket=" + cart +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem that = (CartItem) o;
        return quantityOfProductItemsInBasket == that.quantityOfProductItemsInBasket && Objects.equals(productBasketItemId, that.productBasketItemId) && Objects.equals(product, that.product) && Objects.equals(cart, that.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productBasketItemId, product, quantityOfProductItemsInBasket, cart);
    }
}
