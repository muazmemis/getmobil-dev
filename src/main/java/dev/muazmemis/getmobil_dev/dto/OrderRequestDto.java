package dev.muazmemis.getmobil_dev.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequestDto(@NotNull @NotEmpty List<OrderItemDto> orderItemDtos) { }
