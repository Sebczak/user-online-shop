package com.company.sevices;

import com.company.Messages;
import com.company.entities.Product;
import com.company.entities.ProductBucket;
import com.company.repositories.ProductBucketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductBucketService {

    private final ProductBucketRepository productBucketRepository;

    public ProductBucketService(ProductBucketRepository productBucketRepository) {
        this.productBucketRepository = productBucketRepository;
    }

    public List<ProductBucket> getAllProductsFromBucket() {
        return productBucketRepository.findAll();
    }

    public void createBucket(ProductBucket productBucket) {
        productBucketRepository.save(productBucket);
    }

    public void updateProductBucket(Long bucketId, ProductBucket productBucket) {
        ProductBucket bucket = productBucketRepository.findById(bucketId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.BUCKET_NOT_FOUND, bucketId)));

        bucket.setBucketName(productBucket.getBucketName());
        bucket.setProducts(productBucket.getProducts());
        bucket.setQuantity(productBucket.getQuantity());
    }

    public void deleteProductFromBucket(Long bucketId, Product product) {
        boolean bucketExists = productBucketRepository.existsById(bucketId);
        ProductBucket bucket = productBucketRepository.findById(bucketId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.BUCKET_NOT_FOUND, bucketId)));

        if (bucketExists) {
            bucket.getProducts().remove(product);
        } else {
            throw new IllegalStateException(String.format(Messages.BUCKET_NOT_FOUND, bucketId));
        }
    }

    public void deleteBucket(Long bucketId) {
        boolean bucketExists = productBucketRepository.existsById(bucketId);

        if (bucketExists) {
            productBucketRepository.deleteById(bucketId);
        } else {
            throw new IllegalStateException(String.format(Messages.BUCKET_NOT_FOUND, bucketId));
        }
    }
}
