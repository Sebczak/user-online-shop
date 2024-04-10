package com.company.sevices;

import com.company.Messages;
import com.company.domain.AddCartItemRequest;
import com.company.entities.Product;
import com.company.entities.Cart;
import com.company.entities.CartItem;
import com.company.entities.User;
import com.company.mapper.Mapper;
import com.company.repositories.CartItemRepository;
import com.company.repositories.CartRepository;
import com.company.repositories.ProductRepository;
import com.company.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

    public List<Cart> getAllProductsFromBasket() {
        return cartRepository.findAll();
    }

    public void createBasket(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.USER_NOT_FOUND, userId)));

        Cart cart = user.getProductBucket();
        if (cart == null) {
            cart = new Cart();
            user.setProductBucket(cart);
            cart.setBasketOwner(user);
        }

        userRepository.save(user);
        //productBasketRepository.save(productBasket);
    }

    public void updateProductBasket(Long basketId, @RequestBody Cart cart) {
        Cart basket = cartRepository.findById(basketId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.BUCKET_NOT_FOUND, basketId)));

    }

    public void addProductToBasket(AddCartItemRequest addCartItemRequest) {
        Cart cart = cartRepository.findById(addCartItemRequest.cartId()).orElseThrow(() -> new IllegalStateException("Cart not found"));

        Product product = productRepository.findById(addCartItemRequest.productId()).orElseThrow(() -> new IllegalStateException("Product not found"));

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantityOfProductItemsInBasket(addCartItemRequest.quantity());

        cart.addCartItem(cartItem);

        cartRepository.save(cart);
    }

    public void deleteProductFromBasket(Long basketId, Long productBasketItemId) {
        Cart basket = cartRepository.findById(basketId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.BUCKET_NOT_FOUND, basketId)));

        CartItem product = cartItemRepository.findById(productBasketItemId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.PRODUCT_ID_NOT_FOUND, productBasketItemId)));

        //basket.getProductBasketItem().remove(product);

        cartRepository.save(basket);
    }

    public void deleteBasket(Long basketId) {
        boolean basketExists = cartRepository.existsById(basketId);

        if (basketExists) {
            cartRepository.deleteById(basketId);
        } else {
            throw new IllegalStateException(String.format(Messages.BUCKET_NOT_FOUND, basketId));
        }
    }

    public void reduceQuantityOrProductsWhenProductIsAddedToBasket(Product product, Cart cart) {

    }
}
