package dev.muazmemis.getmobil_dev.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.muazmemis.getmobil_dev.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDto(
        List<OrderItemResponseDto> orderItems,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
        LocalDateTime date,
        OrderStatus status,
        BigDecimal totalAmount
) { }
