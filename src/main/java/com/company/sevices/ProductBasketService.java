package com.company.sevices;

import com.company.Messages;
import com.company.domain.ProductBasketItemDto;
import com.company.entities.Product;
import com.company.entities.ProductBasket;
import com.company.entities.ProductBasketItem;
import com.company.entities.User;
import com.company.mapper.Mapper;
import com.company.repositories.ProductBasketItemRepository;
import com.company.repositories.ProductBasketRepository;
import com.company.repositories.ProductRepository;
import com.company.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductBasketService {

    private final ProductBasketRepository productBasketRepository;
    private final ProductRepository productRepository;
    private final ProductBasketItemRepository productBasketItemRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;

    public ProductBasketService(ProductBasketRepository productBasketRepository, ProductRepository productRepository, ProductBasketItemRepository productBasketItemRepository, UserRepository userRepository, Mapper mapper) {
        this.productBasketRepository = productBasketRepository;
        this.productRepository = productRepository;
        this.productBasketItemRepository = productBasketItemRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public List<ProductBasket> getAllProductsFromBasket() {
        return productBasketRepository.findAll();
    }

    public void createBasket(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.USER_NOT_FOUND, userId)));

        ProductBasket productBasket = user.getProductBucket();
        if (productBasket == null) {
            productBasket = new ProductBasket();
            user.setProductBucket(productBasket);
            productBasket.setBasketOwner(user);
        }

        userRepository.save(user);
        //productBasketRepository.save(productBasket);
    }

    public void updateProductBasket(Long basketId, @RequestBody ProductBasket productBasket) {
        ProductBasket basket = productBasketRepository.findById(basketId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.BUCKET_NOT_FOUND, basketId)));

    }

    public void addProductToBasket(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.USER_NOT_FOUND, userId)));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.PRODUCT_ID_NOT_FOUND, productId)));

        ProductBasket productBasket = user.getProductBucket();
        if (productBasket == null) {
            productBasket = new ProductBasket();
            user.setProductBucket(productBasket);
            productBasket.setBasketOwner(user);
        }

        ProductBasketItemDto productBasketItemDto = mapper.mapToProductBasketItemDto(product, 5, userId);
        ProductBasketItem productBasketItem = mapper.mapProductBasketItemDtoToProductBasketItem(productBasketItemDto, userId);

        System.out.println(productBasketItemDto);
        System.out.println(productBasketItem);

        user.getProductBucket().getProductBasketItem().add(productBasketItemDto);
//        productBasketRepository.save(productBasket);
        userRepository.save(user);
    }

    public void deleteProductFromBasket(Long basketId, Long productBasketItemId) {
        ProductBasket basket = productBasketRepository.findById(basketId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.BUCKET_NOT_FOUND, basketId)));

        ProductBasketItem product = productBasketItemRepository.findById(productBasketItemId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.PRODUCT_ID_NOT_FOUND, productBasketItemId)));

        //basket.getProductBasketItem().remove(product);

        productBasketRepository.save(basket);
    }

    public void deleteBasket(Long basketId) {
        boolean basketExists = productBasketRepository.existsById(basketId);

        if (basketExists) {
            productBasketRepository.deleteById(basketId);
        } else {
            throw new IllegalStateException(String.format(Messages.BUCKET_NOT_FOUND, basketId));
        }
    }

    public void reduceQuantityOrProductsWhenProductIsAddedToBasket(Product product, ProductBasket productBasket) {

    }
}
