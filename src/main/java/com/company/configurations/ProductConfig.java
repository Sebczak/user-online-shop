package com.company.configurations;

import com.company.entities.Product;
import com.company.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner productRunner(ProductRepository repository) {
        return args -> {
            Product bike = new Product(
                    1L,
                    "Bike",
                    "Red Bike",
                    new BigDecimal("1.50"),
                    5
            );

            Product car = new Product(
                    2L,
                    "Car",
                    "Blue car",
                    new BigDecimal("2.50"),
                    10
            );
            repository.saveAll(List.of(bike, car));
        };
    }
}
