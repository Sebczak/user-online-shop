package com.company.controllers;

import com.company.domain.AddCartItemRequest;
import com.company.dtos.CartDto;
import com.company.entities.Cart;
import com.company.mapper.Mapper;
import com.company.sevices.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product_basket")
public class CartController {

    private final CartService cartService;
    private final Mapper mapper;

    public CartController(CartService cartService, Mapper mapper) {
        this.cartService = cartService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<CartDto> getProductsFromBasket() {
        List<Cart> carts = cartService.getAllProductsFromBasket();
        return mapper.toCartDtos(carts);
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
