package com.company.sevices;

import com.company.Messages;
import com.company.entities.Product;
import com.company.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(Long productId) {
        return productRepository.findById(productId);
    }

    public void addProductToDb(Product product) {
        Optional<Product> isProductInDb = productRepository.findProductByProductName(product.getProductName());

        if (isProductInDb.isPresent()) {
            throw new IllegalStateException(String.format(Messages.PRODUCT_ALREADY_ADDED, product.getProductName()));
        }

        productRepository.save(product);
    }

    public void deleteProductFromDb(Long productId) {
        boolean isProductInDb = productRepository.existsById(productId);

        if (isProductInDb) {
            productRepository.deleteById(productId);
        } else {
            throw new IllegalStateException(String.format(Messages.PRODUCT_ID_NOT_FOUND, productId));
        }
    }

    @Transactional
    public void updateProductName(Long productId, String productName) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.PRODUCT_ID_NOT_FOUND, productId)));

        if (!productName.isEmpty() && !productName.equals(product.getProductName())) {
            product.setProductName(productName);
        }
    }

    @Transactional
    public void updateProductDescription(Long productId, String productDescription) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.PRODUCT_ID_NOT_FOUND, productId)));
        if (productDescription != null && !productDescription.isEmpty() && !productDescription.equals(product.getProductDescription())) {
            product.setProductDescription(productDescription);
        }
    }

    @Transactional
    public void updateProductPrice(Long productId, Double price) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.PRODUCT_ID_NOT_FOUND, productId)));
        if (price != null && !price.isNaN() && !price.equals(product.getPrice())) {
            product.setPrice(price);
        }
    }
}
