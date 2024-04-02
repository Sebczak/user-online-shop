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
    private ProductService productService = mock(ProductService.class);

    @Test
    void testGetAllProductsWhenThereIsOneProduct() {
        //Given
        when(productService.getProducts()).thenReturn(
                List.of(
                        new Product(1L, "Bike", "Red Bike", 150.99, 1)
                )
        );
        //When
        List<Product> products = productService.getProducts();

        //Then
        assertEquals(1, products.size());
        verify(productService, times(1)).getProducts();
    }

    @Test
    void testGetAllProductsWhenThereIsMoreProducts() {
        //Given
        when(productService.getProducts()).thenReturn(
                List.of(
                        new Product(1L, "Bike", "Red Bike", 100.99, 1),
                        new Product(2L, "Car", "Blue car", 15000.00, 1),
                        new Product(3L, "Motorcycle", "Green motorcycle", 999.99, 1),
                        new Product(4L, "Scooter", "White scooter", 350.00, 1),
                        new Product(5L, "Plane", "Long plane", 150000.99, 1)
                )
        );

        //When
        List<Product> products = productService.getProducts();

        //Then
        assertEquals(5, products.size());
        verify(productService, times(1)).getProducts();
    }

    @Test
    void testGetProductsWhenThereIsNoProducts() {
        //Given

        //When
        List<Product> products = productService.getProducts();

        //Then
        assertTrue(products.isEmpty());
    }

    @Test
    void testGetOneProductUsingId() {
        //Given
        when(productService.getProduct(anyLong())).thenReturn(
                Optional.of(new Product(1L, "Bike", "Red Bike", 150.99, 1))

        );

        //When
        Optional<Product> product = productService.getProduct(1L);

        //Then
        assertTrue(product.isPresent());
        verify(productService, times(1)).getProduct(anyLong());
    }

    @Test
    void testGetOneProductUsingIdFromMultipleProducts() {
        //Given
        when(productService.getProduct(anyLong())).thenAnswer(invocation -> {
                    Long id = invocation.getArgument(0);
                    return switch (id.intValue()) {
                        case 1 -> Optional.of(new Product(1L, "Bike", "Red Bike", 100.99, 1));
                        case 2 -> Optional.of(new Product(2L, "Car", "Blue car", 15000.00, 1));
                        case 3 -> Optional.of(new Product(3L, "Motorcycle", "Green motorcycle", 999.99, 1));
                        case 4 -> Optional.of(new Product(4L, "Scooter", "White scooter", 350.00, 1));
                        case 5 -> Optional.of(new Product(5L, "Plane", "Long plane", 150000.99, 1));
                        default -> Optional.empty();
                    };
                }
        );

        //When
        Optional<Product> product1 = productService.getProduct(1L);

        //Then
        assertTrue(product1.isPresent());
        assertEquals("Bike", product1.get().getProductName());
    }

    @Test
    void testGetProductWhenThereIsNoProducts() {
        //Given

        //When
        Optional<Product> product = productService.getProduct(1L);

        //Then
        assertTrue(product.isEmpty());
    }

    @Test
    void testAddProductWhenListIsEmpty() {
        //Given

        //When
        productService.addProductToDb(new Product(1L, "Car", "Brown Car", 10.1, 1));
        List<Product> products = productService.getProducts();
        System.out.println(products);

        //Then
        //assertEquals(1, products.size());
    }
}
