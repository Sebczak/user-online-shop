package com.company.product;

import com.company.entities.Product;
import com.company.sevices.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTestSuite {

    @Mock
    private ProductService productService = new ProductService();


}
