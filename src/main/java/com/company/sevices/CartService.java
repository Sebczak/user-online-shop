package com.company.sevices;

import com.company.Messages;
import com.company.domain.AddCartItemRequest;
import com.company.domain.RemoveCartItemRequest;
import com.company.entities.Cart;
import com.company.entities.CartItem;
import com.company.entities.Product;
import com.company.entities.User;
import com.company.mapper.Mapper;
import com.company.repositories.CartItemRepository;
import com.company.repositories.CartRepository;
import com.company.repositories.ProductRepository;
import com.company.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, CartItemRepository cartItemRepository, UserRepository userRepository, Mapper mapper) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public List<Cart> getProductsFromCart() {
        return cartRepository.findAll();
    }

    public void createBasket(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.USER_NOT_FOUND, userId)));

        Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart();
            user.setCart(cart);
            cart.setCartOwner(user);
        }

        userRepository.save(user);
    }

    public void updateProductBasket(Long basketId, @RequestBody Cart cart) {
        Cart basket = cartRepository.findById(basketId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.CART_NOT_FOUND, basketId)));

    }

    public void addProductToBasket(AddCartItemRequest addCartItemRequest) {
        Cart cart = cartRepository.findById(addCartItemRequest.cartId()).orElseThrow(() -> new IllegalStateException(String.format(Messages.CART_NOT_FOUND, addCartItemRequest.cartId())));

        Product product = productRepository.findById(addCartItemRequest.productId()).orElseThrow(() -> new IllegalStateException(String.format(Messages.PRODUCT_ID_NOT_FOUND, addCartItemRequest.productId())));

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantityOfCartItemsInCart(addCartItemRequest.quantity());

        cart.addCartItem(cartItem);

        cartRepository.save(cart);
    }

    public void removeCartItemFromCart(RemoveCartItemRequest removeCartItemRequest) {
        Cart cart = cartRepository.findById(removeCartItemRequest.cartId())
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.CART_NOT_FOUND, removeCartItemRequest.cartId())));

        CartItem cartItem = cartItemRepository.findById(removeCartItemRequest.cartItemId())
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.CART_ITEM_ID_NOT_FOUND, removeCartItemRequest.cartItemId())));

        cart.removeCartItem(cartItem);

        cartItemRepository.delete(cartItem);
        cartRepository.save(cart);
    }

    public void deleteCart(Long basketId) {
        boolean basketExists = cartRepository.existsById(basketId);

        if (basketExists) {
            cartRepository.deleteById(basketId);
        } else {
            throw new IllegalStateException(String.format(Messages.CART_NOT_FOUND, basketId));
        }
    }

    private BigDecimal calculateCartTotalPrice(List<CartItem> cartItems) {

        BigDecimal totalPrice = BigDecimal.ZERO;

        for (CartItem cartItem : cartItems) {
            BigDecimal productPrice = cartItem.getProduct().getPrice();
            totalPrice = totalPrice.add(productPrice.multiply(new BigDecimal(cartItem.getQuantityOfCartItemsInCart())));
        }

        return totalPrice;
    }
}
