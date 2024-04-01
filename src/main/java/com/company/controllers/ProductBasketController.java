package com.company.controllers;

import com.company.entities.Product;
import com.company.entities.ProductBucket;
import com.company.sevices.ProductBucketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product_bucket")
public class ProductBucketController {

    private final ProductBucketService productBucketService;

    public ProductBucketController(ProductBucketService productBucketService) {
        this.productBucketService = productBucketService;
    }

    @GetMapping
    public List<ProductBucket> getProductsFromBucket() {
        return productBucketService.getAllProductsFromBucket();
    }

    @PostMapping
    public void createBucket(@RequestBody ProductBucket productBucket) {
        productBucketService.createBucket(productBucket);
    }

    @PutMapping(path = "{bucketId}")
    public void updateProductBucket(@PathVariable Long bucketId, @RequestParam(required = false) ProductBucket productBucket) {
        productBucketService.updateProductBucket(bucketId, productBucket);
    }

    @DeleteMapping(path = "{bucketId}/{product}")
    public void deleteProductFromBucket(@PathVariable Long bucketId, @RequestParam(required = false) Product product) {
        productBucketService.deleteProductFromBucket(bucketId, product);
    }

    @DeleteMapping(path = "{bucketId}")
    public void deleteBucket(@PathVariable Long bucketId) {
        productBucketService.deleteBucket(bucketId);
    }
}
