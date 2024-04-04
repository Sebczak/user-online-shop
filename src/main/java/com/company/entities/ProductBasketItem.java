package com.company.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "product_basket_item")
public class ProductBasketItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_basket_item_id")
    private Long productBasketItemId;

    @ManyToOne
    @JoinColumn(name = "basket_id")
    private ProductBasket productBasket;
    private int quantityOfProductItemsInBasket;

    public ProductBasketItem(Long productBasketItemId, ProductBasket productBasket, int quantityOfProductItemsInBasket) {
        this.productBasketItemId = productBasketItemId;
        this.productBasket = productBasket;
        this.quantityOfProductItemsInBasket = quantityOfProductItemsInBasket;
    }

    public ProductBasketItem(ProductBasket productBasket, int quantityOfProductItemsInBasket) {
        this.productBasket = productBasket;
        this.quantityOfProductItemsInBasket = quantityOfProductItemsInBasket;
    }

    public ProductBasketItem() {
    }

    public Long getProductBasketItemId() {
        return productBasketItemId;
    }

    public void setProductBasketItemId(Long productBasketItemId) {
        this.productBasketItemId = productBasketItemId;
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
}
