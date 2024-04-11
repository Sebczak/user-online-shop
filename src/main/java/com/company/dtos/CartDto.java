package com.company.dtos;

import com.company.entities.CartItem;
import com.company.entities.User;

import java.math.BigDecimal;
import java.util.List;

public record CartDto(List<CartItem> cartItems, BigDecimal totalPrice, User user) {
}
