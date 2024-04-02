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

    @PutMapping(path = "{basketId}")
    public void updateProductBasket(@PathVariable Long basketId, @RequestParam(required = false) ProductBasket productBasket) {
        productBasketService.updateProductBasket(basketId, productBasket);
    }

    @DeleteMapping(path = "{basketId}/{product}")
    public void deleteProductFromBasket(@PathVariable Long basketId, @RequestParam(required = false) Product product) {
        productBasketService.deleteProductFromBasket(basketId, product);
    }

    @DeleteMapping(path = "{basketId}")
    public void deleteBasket(@PathVariable Long basketId) {
        productBasketService.deleteBasket(basketId);
    }
}
