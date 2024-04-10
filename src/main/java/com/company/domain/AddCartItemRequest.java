package com.company.domain;

public record AddCartItemRequest(Integer quantity, Long productId, Long cartId) {
}
