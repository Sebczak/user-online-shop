package com.company.repositories;

import com.company.entities.Product;
import com.company.entities.ProductBasket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface ProductBasketRepository extends JpaRepository<ProductBasket, Long> {
}
