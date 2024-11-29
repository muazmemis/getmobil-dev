package dev.muazmemis.getmobil_dev.dto;

import java.math.BigDecimal;

public record OrderItemResponseDto(
        String productName,
        Integer quantity,
        BigDecimal sellingPrice
) { }
