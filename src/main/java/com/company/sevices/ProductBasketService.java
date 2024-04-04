package com.company.sevices;

import com.company.Messages;
import com.company.entities.Product;
import com.company.entities.ProductBasket;
import com.company.repositories.ProductBasketRepository;
import com.company.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductBasketService {

    private final ProductBasketRepository productBasketRepository;
    private final ProductRepository productRepository;

    public ProductBasketService(ProductBasketRepository productBasketRepository, ProductRepository productRepository) {
        this.productBasketRepository = productBasketRepository;
        this.productRepository = productRepository;
    }

    public List<ProductBasket> getAllProductsFromBasket() {
        return productBasketRepository.findAll();
    }

    public void createBasket(ProductBasket productBasket) {
        productBasketRepository.save(productBasket);
    }

    public void updateProductBasket(Long basketId, @RequestBody ProductBasket productBasket) {
        ProductBasket basket = productBasketRepository.findById(basketId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.BUCKET_NOT_FOUND, basketId)));

    }

    public void addProductToBasket(Long basketId, Long productId) {
        ProductBasket basket = productBasketRepository.findById(basketId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.BUCKET_NOT_FOUND, basketId)));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.PRODUCT_ID_NOT_FOUND, productId)));

        basket.getProducts().add(product);

        productBasketRepository.save(basket);
    }

    public void deleteProductFromBasket(Long basketId, Long productId) {
        ProductBasket basket = productBasketRepository.findById(basketId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.BUCKET_NOT_FOUND, basketId)));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.PRODUCT_ID_NOT_FOUND, productId)));

        basket.getProducts().remove(product);

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
