package com.company.controllers;

import com.company.domain.AddCartItemRequest;
import com.company.domain.RemoveCartItemRequest;
import com.company.dtos.CartDto;
import com.company.entities.Cart;
import com.company.mapper.Mapper;
import com.company.sevices.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/cart")
public class CartController {

    private final CartService cartService;
    private final Mapper mapper;

    public CartController(CartService cartService, Mapper mapper) {
        this.cartService = cartService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<CartDto> getProductsFromCart() {
        List<Cart> carts = cartService.getProductsFromCart();
        return mapper.toCartDtos(carts);
    }

    @PostMapping("{userId}")
    public void createCart(@PathVariable Long userId) {
        cartService.createBasket(userId);
    }

    @PutMapping
    public void addProductToCart(@RequestBody AddCartItemRequest addCartItemRequest) {
        cartService.addProductToBasket(addCartItemRequest);
    }

    @PutMapping(path = "/remove")
    public void deleteProductFromCart(@RequestBody RemoveCartItemRequest removeCartItemRequest) {
        cartService.removeCartItemFromCart(removeCartItemRequest);
    }

    @DeleteMapping(path = "{cartId}")
    public void deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
    }
}
