package com.company.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "product_basket_item")
public class ProductBasketItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_basket_item_id")
    private Long productBasketItemId;

    @OneToOne
    private Product product;

    private int quantityOfProductItemsInBasket;
    @ManyToOne
    @JoinColumn(name = "basket_id")
    private ProductBasket productBasket;

    public ProductBasketItem(Long productBasketItemId, Product product, int quantityOfProductItemsInBasket,  ProductBasket productBasket) {
        this.productBasketItemId = productBasketItemId;
        this.product = product;
        this.quantityOfProductItemsInBasket = quantityOfProductItemsInBasket;
        this.productBasket = productBasket;
    }

    public ProductBasketItem(Product product, int quantityOfProductItemsInBasket, ProductBasket productBasket) {
        this.product = product;
        this.quantityOfProductItemsInBasket = quantityOfProductItemsInBasket;
        this.productBasket = productBasket;
    }

    public ProductBasketItem() {
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

    public ProductBasket getProductBasket() {
        return productBasket;
    }

    public void setProductBasket(ProductBasket productBasket) {
        this.productBasket = productBasket;
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
                ", productBasket=" + productBasket +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductBasketItem that = (ProductBasketItem) o;
        return quantityOfProductItemsInBasket == that.quantityOfProductItemsInBasket && Objects.equals(productBasketItemId, that.productBasketItemId) && Objects.equals(product, that.product) && Objects.equals(productBasket, that.productBasket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productBasketItemId, product, quantityOfProductItemsInBasket, productBasket);
    }
}
