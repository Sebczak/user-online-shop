package com.company.domain;

import java.math.BigDecimal;

public record ProductDto(Long productId, String productName, BigDecimal price, Integer productQuantity) {
}
