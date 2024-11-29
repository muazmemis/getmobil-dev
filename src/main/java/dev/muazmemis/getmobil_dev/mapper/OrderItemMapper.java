package dev.muazmemis.getmobil_dev.mapper;

import dev.muazmemis.getmobil_dev.dto.OrderItemDto;
import dev.muazmemis.getmobil_dev.dto.OrderItemResponseDto;
import dev.muazmemis.getmobil_dev.entity.OrderItem;
import dev.muazmemis.getmobil_dev.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderItemMapper {

//    public static final OrderItemMapper INSTANCE = new OrderItemMapper(); // TODO:

    public OrderItem map(OrderItemDto orderItemDto) {
        return OrderItem.builder()
                .product(Product.builder().id(orderItemDto.productId()).build())
                .quantity(orderItemDto.quantity())
                .build();
    }

    public List<OrderItem> map(List<OrderItemDto> orderDtos) {
        if (orderDtos == null) return null;
        return orderDtos.stream().map(this::map).toList();
    }

    public OrderItemResponseDto mapToResponse(OrderItem orderItem) {
        return new OrderItemResponseDto(
                orderItem.getProduct().getName(),
                orderItem.getQuantity(),
                orderItem.getProduct().getDiscountedPrice()
        );
    }

    public List<OrderItemResponseDto> mapToResponse(List<OrderItem> orderItems) {
        return orderItems.stream().map(this::mapToResponse).toList();
    }

}
