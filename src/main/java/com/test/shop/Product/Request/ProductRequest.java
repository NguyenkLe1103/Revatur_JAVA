package com.test.shop.Product.Request;

import java.math.BigDecimal;
import jakarta.validation.constraints.*;


public record ProductRequest(
        @NotBlank String name,
        String description,
        @NotNull @DecimalMin(value = "0.0", inclusive = true) BigDecimal price,
        @NotNull Integer categoryId
) {}