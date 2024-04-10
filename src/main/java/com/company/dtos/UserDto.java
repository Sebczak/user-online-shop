package com.company.dtos;

import java.time.LocalDate;

public record UserDto(Long userId, String firstName, String lastName, String email, LocalDate dayOfBirth) {
}
