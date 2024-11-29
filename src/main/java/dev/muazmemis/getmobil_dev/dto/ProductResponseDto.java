package dev.muazmemis.getmobil_dev.dto;

import java.math.BigDecimal;

public record ProductResponseDto(String name,
                                 BigDecimal sellingPrice,
                                 Integer stock
) {
}
