package com.company.controllers;

import com.company.domain.AddCartItemRequest;
import com.company.entities.Cart;
import com.company.sevices.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product_basket")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<Cart> getProductsFromBasket() {
        return cartService.getAllProductsFromBasket();
    }

    @PostMapping("{userId}")
    public void createBasket(@PathVariable Long userId) {
        cartService.createBasket(userId);
    }

    @PutMapping
    public void addProductToBasket(@RequestBody AddCartItemRequest addCartItemRequest) {
        cartService.addProductToBasket(addCartItemRequest);
    }

    @PutMapping(path = "{basketId}")
    public void updateProductBasket(@PathVariable Long basketId, @RequestParam(required = false) Cart cart) {
        cartService.updateProductBasket(basketId, cart);
    }

    @DeleteMapping(path = "{basketId}/{productId}")
    public void deleteProductFromBasket(@PathVariable Long basketId,@PathVariable Long productId) {
        cartService.deleteProductFromBasket(basketId, productId);
    }

    @DeleteMapping(path = "{basketId}")
    public void deleteBasket(@PathVariable Long basketId) {
        cartService.deleteBasket(basketId);
    }
}
