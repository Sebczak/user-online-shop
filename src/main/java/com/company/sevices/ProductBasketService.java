package com.company.sevices;

import com.company.Messages;
import com.company.entities.Product;
import com.company.entities.ProductBasket;
import com.company.repositories.ProductBasketRepository;
import org.springframework.stereotype.Service;

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

    public void updateProductBasket(Long bucketId, ProductBasket productBasket) {
        ProductBasket bucket = productBasketRepository.findById(bucketId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.BUCKET_NOT_FOUND, bucketId)));

        bucket.setBucketName(productBasket.getBucketName());
        bucket.setProducts(productBasket.getProducts());
        bucket.setQuantity(productBasket.getQuantity());
    }

    public void deleteProductFromBasket(Long bucketId, Product product) {
        boolean bucketExists = productBasketRepository.existsById(bucketId);
        ProductBasket bucket = productBasketRepository.findById(bucketId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.BUCKET_NOT_FOUND, bucketId)));

        if (bucketExists) {
            bucket.getProducts().remove(product);
        } else {
            throw new IllegalStateException(String.format(Messages.BUCKET_NOT_FOUND, bucketId));
        }
    }

    public void deleteBasket(Long bucketId) {
        boolean bucketExists = productBasketRepository.existsById(bucketId);

        if (bucketExists) {
            productBasketRepository.deleteById(bucketId);
        } else {
            throw new IllegalStateException(String.format(Messages.BUCKET_NOT_FOUND, bucketId));
        }
    }
}
