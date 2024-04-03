package com.company.controllers;

import com.company.entities.Product;
import com.company.entities.ProductBasket;
import com.company.sevices.ProductBasketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product_basket")
public class ProductBasketController {

    private final ProductBasketService productBasketService;

    public ProductBasketController(ProductBasketService productBasketService) {
        this.productBasketService = productBasketService;
    }

    @GetMapping
    public List<ProductBasket> getProductsFromBasket() {
        return productBasketService.getAllProductsFromBasket();
    }

    @PostMapping
    public void createBasket(@RequestBody ProductBasket productBasket) {
        productBasketService.createBasket(productBasket);
    }

    @PostMapping(path = "{basketId}/{productId}")
    public void addProductToBasket(@PathVariable Long basketId,@PathVariable Long productId) {
        productBasketService.addProductToBasket(basketId, productId);
    }

    @PutMapping(path = "{basketId}")
    public void updateProductBasket(@PathVariable Long basketId, @RequestParam(required = false) ProductBasket productBasket) {
        productBasketService.updateProductBasket(basketId, productBasket);
    }

    @DeleteMapping(path = "{basketId}/{productId}")
    public void deleteProductFromBasket(@PathVariable Long basketId,@PathVariable Long productId) {
        productBasketService.deleteProductFromBasket(basketId, productId);
    }

    @DeleteMapping(path = "{basketId}")
    public void deleteBasket(@PathVariable Long basketId) {
        productBasketService.deleteBasket(basketId);
    }
}
