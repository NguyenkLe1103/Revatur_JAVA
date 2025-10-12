package com.test.shop.Product.Request;

import jakarta.validation.constraints.*;



public record CategoryRequest(
        @NotBlank String name,
        String description
) {}