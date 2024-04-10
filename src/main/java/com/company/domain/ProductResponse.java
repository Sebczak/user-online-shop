package com.company.domain;

import java.math.BigDecimal;

public record ProductResponse(Long productId, String productName, BigDecimal price, Integer productQuantity) {
}
