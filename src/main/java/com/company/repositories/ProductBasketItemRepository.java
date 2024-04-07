package com.company.repositories;

import com.company.entities.ProductBasketItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductBasketItemRepository extends JpaRepository<ProductBasketItem, Long> {
}
