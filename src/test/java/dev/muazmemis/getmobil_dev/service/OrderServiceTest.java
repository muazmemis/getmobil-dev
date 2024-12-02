package dev.muazmemis.getmobil_dev.service;

import dev.muazmemis.getmobil_dev.dto.OrderResponseDto;
import dev.muazmemis.getmobil_dev.entity.Order;
import dev.muazmemis.getmobil_dev.enums.OrderStatus;
import dev.muazmemis.getmobil_dev.exception.ItemNotFoundException;
import dev.muazmemis.getmobil_dev.kafka.KafkaProducerService;
import dev.muazmemis.getmobil_dev.mapper.OrderMapper;
import dev.muazmemis.getmobil_dev.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private Order order;

    @Mock
    private OrderResponseDto orderResponseDto;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private KafkaProducerService kafkaProducerService;

    @InjectMocks
    private OrderService orderService;

    // TODO: Test will be implemented
//    @Test
//    void shouldSave() {
//        when(order.getId()).thenReturn(1L);
//        when(orderRepository.save(any())).thenReturn(order);
//        doNothing().when(kafkaProducerService).sendMessageInvoice(anyString(), any(Invoice.class));
//
//        assertEquals(1L, orderService.save(order).getId());
//        verify(kafkaProducerService).sendMessageInvoice(eq("invoice"), any(Invoice.class));
//    }

//    @Test
//    void saveWithKafka() {
//        assertEquals(1, 1);
//    }

    @Test
    void shouldFindAll() {
        when(orderRepository.findAll()).thenReturn(List.of());
        when(orderMapper.map(anyList())).thenReturn(List.of());

        assertEquals(0, orderService.findAll().size());
    }

    @Test
    void shouldFindAllWhenReturnsOrders() {
        when(orderRepository.findAll()).thenReturn(List.of(order));
        when(orderMapper.map(anyList())).thenReturn(List.of(orderResponseDto));

        assertEquals(1, orderService.findAll().size());
    }

    @Test
    void shouldFindAllWhenOrderListIsNull() {
        when(orderMapper.map(anyList())).thenReturn(null);

        assertNull(orderService.findAll());
    }

    @Test
    void shouldFindById() {
        when(orderResponseDto.status()).thenReturn(OrderStatus.COMPLETED);
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));
        when(orderMapper.map(order)).thenReturn(orderResponseDto);


        OrderResponseDto result = orderService.findById(1L);
        assertEquals(OrderStatus.COMPLETED, result.status());
    }

    @Test
    void shouldNotFindByIdWhenIdDoesNotExist() {
        when(orderRepository.findById(anyLong())).thenThrow(ItemNotFoundException.class);
        assertThrows(ItemNotFoundException.class, () -> orderService.findById(-1L));
    }

}
