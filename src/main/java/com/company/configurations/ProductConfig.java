package com.company.configurations;

import com.company.entities.Product;
import com.company.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
                    150.99
            );

            Product car = new Product(
                    2L,
                    "Car",
                    "Blue car",
                    15000.00
            );
            repository.saveAll(List.of(bike, car));
        };
    }

}
