package com.company.dtos;

import com.company.entities.Cart;

import java.time.LocalDate;

public record UserDto(Long userId, String firstName, String lastName, String email, LocalDate dayOfBirth, Cart cartDto) {
}
