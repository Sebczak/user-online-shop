package com.company.dtos;

import java.math.BigDecimal;

public record ProductDto (String productName, String productDescription, BigDecimal productPrice, int productQuantity) {
}
