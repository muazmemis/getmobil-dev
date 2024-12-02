package dev.muazmemis.getmobil_dev.service;

import dev.muazmemis.getmobil_dev.dto.OrderRequestDto;
import dev.muazmemis.getmobil_dev.dto.OrderResponseDto;
import dev.muazmemis.getmobil_dev.entity.Product;
import dev.muazmemis.getmobil_dev.entity.Invoice;
import dev.muazmemis.getmobil_dev.entity.Order;
import dev.muazmemis.getmobil_dev.entity.OrderItem;
import dev.muazmemis.getmobil_dev.enums.OrderStatus;
import dev.muazmemis.getmobil_dev.exception.InsufficientStockException;
import dev.muazmemis.getmobil_dev.exception.ItemNotFoundException;
import dev.muazmemis.getmobil_dev.kafka.KafkaProducerService;
import dev.muazmemis.getmobil_dev.mapper.OrderMapper;
import dev.muazmemis.getmobil_dev.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.math.BigDecimal;
import java.util.List;

import static dev.muazmemis.getmobil_dev.helper.LogHelperComponent.logInfo;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final KafkaProducerService kafkaProducerService;
    private final OrderMapper orderMapper;

    @Transactional
    public void save(Order order) {
        Order savedOrder = orderRepository.save(order);
        logInfo("Order saved. Order id: {}", savedOrder.getId());

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                kafkaProducerService.sendMessageInvoice("invoice", Invoice.builder()
                        .order(savedOrder).build());
            }
        });
    }

    @Transactional(rollbackOn = InsufficientStockException.class)
    public void processSaveOrder(OrderRequestDto orderRequestDto) {
        Order order = orderMapper.map(orderRequestDto);

        order.getItems().forEach(item -> {
            item.setProduct(productService.findById(item.getProduct().getId()));
            Integer newStock = reduceStock(item.getProduct(), item.getQuantity());
            productService.updateStock(item.getProduct().getId(), newStock);
            item.getProduct().setStock(newStock);
        });

        order.setTotalAmount(calculateTotalAmount(order.getItems()));
        order.setStatus(OrderStatus.COMPLETED);
        kafkaProducerService.sendMessageOrder("order", order);
    }

    public List<OrderResponseDto> findAll() {
        List<Order> orders = orderRepository.findAll();
        logInfo("All orders fetched. Order count: {}", orders.size());

        return orderMapper.map(orders);
    }

    public OrderResponseDto findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
        logInfo("Order fetched. Order id: {}", id);

        return orderMapper.map(order);
    }

    private Integer reduceStock(Product product, Integer quantity) {
        if (product.getStock() == null || product.getStock() < quantity)
            throw new InsufficientStockException(product.getName());

        return product.getStock() - quantity;
    }

    private BigDecimal calculateTotalAmount(List<OrderItem> items) {
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItem item : items)
            totalAmount = totalAmount.add(item.getProduct().getDiscountedPrice()
                    .multiply(BigDecimal.valueOf(item.getQuantity())));

        return totalAmount;
    }

}
