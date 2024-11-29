package dev.muazmemis.getmobil_dev.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record InvoiceResponseDto(
        Long id,
        Long orderId,
        List<OrderItemResponseDto> orderItems,
        BigDecimal grandTotal,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
        LocalDateTime date
) {
}
