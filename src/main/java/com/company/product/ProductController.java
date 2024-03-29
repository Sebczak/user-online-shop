package com.company.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    @GetMapping(path = "{productId}")
    public Optional<Product> getProduct(@PathVariable("productId") Long productId) {
        return productService.getProduct(productId);
    }

    @PostMapping
    public void addProductToDb(@RequestBody Product product) {
        productService.addProductToDb(product);
    }

    @DeleteMapping(path = "{productId}")
    public void deleteProductFromDb(@PathVariable("productId") Long productId) {
        productService.deleteProductFromDb(productId);
    }

    @PutMapping(path = "{productId}/productName")
    public void updateProductName(@PathVariable("productId") Long productId,@RequestParam(required = false) String productName) {
        productService.updateProductName(productId, productName);
    }

    @PutMapping(path = "{productId}/productDescription")
    public void updateProductDescription(@PathVariable("productId") Long productId,@RequestParam(required = false) String productDescription) {
        productService.updateProductDescription(productId, productDescription);
    }

    @PutMapping(path = "{productId}/productPrice")
    public void updateProductPrice(@PathVariable("productId") Long productId,@RequestParam(required = false) Double price) {
        productService.updateProductPrice(productId, price);
    }
}
