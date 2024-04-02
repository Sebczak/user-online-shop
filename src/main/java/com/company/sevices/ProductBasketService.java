package com.company.sevices;

import com.company.Messages;
import com.company.entities.Product;
import com.company.entities.ProductBasket;
import com.company.repositories.ProductBasketRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductBasketService {

    private final ProductBasketRepository productBasketRepository;

    public ProductBasketService(ProductBasketRepository productBasketRepository) {
        this.productBasketRepository = productBasketRepository;
    }

    public List<ProductBasket> getAllProductsFromBasket() {
        return productBasketRepository.findAll();
    }

    public void createBasket(ProductBasket productBasket) {
        productBasketRepository.save(productBasket);
    }

    public void updateProductBasket(Long basketId, ProductBasket productBasket) {
        ProductBasket basket = productBasketRepository.findById(basketId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.BUCKET_NOT_FOUND, basketId)));

        basket.setBasketName(productBasket.getBasketName());
        basket.setProducts(productBasket.getProducts());
        basket.setQuantityOfProductInBasket(productBasket.getQuantityOfProductInBasket());
    }

    public void addProductToBasket(Long basketId, Product product) {
        ProductBasket basket = productBasketRepository.findById(basketId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.BUCKET_NOT_FOUND, basketId)));

        basket.getProducts().add(product);

        productBasketRepository.save(basket);
    }

    public void deleteProductFromBasket(Long basketId, Product product) {
        boolean basketExists = productBasketRepository.existsById(basketId);
        ProductBasket basket = productBasketRepository.findById(basketId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.BUCKET_NOT_FOUND, basketId)));

        if (basketExists) {
            basket.getProducts().remove(product);
        } else {
            throw new IllegalStateException(String.format(Messages.BUCKET_NOT_FOUND, basketId));
        }
    }

    public void deleteBasket(Long basketId) {
        boolean basketExists = productBasketRepository.existsById(basketId);

        if (basketExists) {
            productBasketRepository.deleteById(basketId);
        } else {
            throw new IllegalStateException(String.format(Messages.BUCKET_NOT_FOUND, basketId));
        }
    }
}
