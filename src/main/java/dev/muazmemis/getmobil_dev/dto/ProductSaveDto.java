package dev.muazmemis.getmobil_dev.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductSaveDto(
        @NotNull(message = "Name cannot be null")
        @NotBlank(message = "Name cannot be blank")
        @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
        String name,

        @NotNull(message = "Price cannot be null")
        @DecimalMin(value = "50.0", message = "Price must be at least 50")
        BigDecimal price,

        @DecimalMin(value = "0.0", inclusive = true, message = "Tax rate must be at least 0")
        @DecimalMax(value = "100.0", inclusive = true, message = "Tax rate must be at most 100")
        BigDecimal taxRate,

        @DecimalMin(value = "0.0", inclusive = true, message = "Discount rate must be at least 0")
        @DecimalMax(value = "100.0", inclusive = true, message = "Discount rate must be at most 100")
        BigDecimal discountRate,

        @NotNull(message = "Stock cannot be null")
        @Min(value = 1, message = "Stock must be at least 1")
        Integer stock
) { }
