package dev.muazmemis.getmobil_dev.mapper;

import dev.muazmemis.getmobil_dev.dto.OrderRequestDto;
import dev.muazmemis.getmobil_dev.dto.OrderResponseDto;
import dev.muazmemis.getmobil_dev.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;

    public Order map(OrderRequestDto orderDto) {
        return Order.builder()
                .items(orderItemMapper.map(orderDto.orderItemDtos()))
                .build();
    }

    public OrderResponseDto map(Order order) {
        return new OrderResponseDto(orderItemMapper.mapToResponse(order.getItems()),
                order.getDate(),
                order.getStatus(),
                order.getTotalAmount());
    }

    public List<OrderResponseDto> map(List<Order> orders) {
        return orders.stream().map(this::map).toList();
    }

}
