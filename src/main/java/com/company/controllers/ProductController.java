package com.company.controllers;

import com.company.Messages;
import com.company.dtos.ProductDto;
import com.company.entities.Product;
import com.company.mapper.Mapper;
import com.company.sevices.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductService productService;
    private final Mapper mapper;

    @Autowired
    public ProductController(ProductService productService, Mapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        List<Product> products = productService.getProducts();
        return mapper.mapToListOfProducts(products);
    }

    @GetMapping(path = "{productId}")
    public Optional<ProductDto> getProduct(@PathVariable("productId") Long productId) {
        Product product = productService.getProduct(productId).orElseThrow(() -> new IllegalStateException(String.format(Messages.PRODUCT_ID_NOT_FOUND, productId)));
        return Optional.ofNullable(mapper.toProductDto(product));
    }

    @PostMapping
    public Long addProductToDb(@RequestBody Product product) {
        productService.addProductToDb(product);
        return product.getProductId();
    }

    @DeleteMapping(path = "{productId}")
    public Long deleteProductFromDb(@PathVariable("productId") Long productId) {
        productService.deleteProductFromDb(productId);
        return productId;
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
    public void updateProductPrice(@PathVariable("productId") Long productId,@RequestParam(required = false) BigDecimal price) {
        productService.updateProductPrice(productId, price);
    }
}
