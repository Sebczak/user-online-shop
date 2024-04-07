package com.company.domain;

import com.company.entities.Product;

public record ProductBasketItemDto (Product product, Integer quantity, Long productBasketId) {
}
