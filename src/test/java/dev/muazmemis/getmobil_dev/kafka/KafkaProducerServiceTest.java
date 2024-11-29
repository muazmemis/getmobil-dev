package dev.muazmemis.getmobil_dev.kafka;

import dev.muazmemis.getmobil_dev.dto.ProductStockUpdateDto;
import dev.muazmemis.getmobil_dev.entity.Invoice;
import dev.muazmemis.getmobil_dev.entity.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KafkaProducerServiceTest {

    @Mock
    private KafkaTemplate<String, ProductStockUpdateDto> kafkaTemplateProduct;

    @Mock
    private KafkaTemplate<String, Order> kafkaTemplateOrder;

    @Mock
    private KafkaTemplate<String, Invoice> kafkaTemplateInvoice;

    @InjectMocks
    private KafkaProducerService kafkaService;

//    @Test
//    void shouldSendMessageProductToKafka() {
//        String key = "product-key";
//        ProductStockUpdateDto stockUpdate = new ProductStockUpdateDto(1L, 100);
//
//        kafkaService.sendMessageProduct(key, stockUpdate);
//
//        verify(kafkaTemplateProduct).send("product-topic", key, stockUpdate);
//    }
//
//    @Test
//    void shouldSendMessageOrderToKafka() {
//        String key = "order-key";
//        Order order = mock(Order.class);
//        CompletableFuture<SendResult<String, Order>> future = mock(CompletableFuture.class);
//
//        when(kafkaTemplateOrder.send("order-topic", key, order)).thenReturn(future);
//
//        kafkaService.sendMessageOrder(key, order);
//
//        verify(kafkaTemplateOrder).send("order-topic", key, order);
//    }
//
//    @Test
//    void shouldSendMessageInvoiceToKafka() {
//        String key = "invoice-key";
//        Invoice invoice = mock(Invoice.class);
//        CompletableFuture<SendResult<String, Invoice>> future = mock(CompletableFuture.class);
//        when(kafkaTemplateInvoice.send("invoice-topic", key, invoice)).thenReturn(future);
//
//        kafkaService.sendMessageInvoice(key, invoice);
//
//        verify(kafkaTemplateInvoice).send("invoice-topic", key, invoice);
//    }

}

