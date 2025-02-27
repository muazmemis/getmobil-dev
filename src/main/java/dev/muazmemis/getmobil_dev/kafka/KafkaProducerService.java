package dev.muazmemis.getmobil_dev.kafka;

import dev.muazmemis.getmobil_dev.controller.AlertManagerRequest;
import dev.muazmemis.getmobil_dev.dto.ProductStockUpdateDto;
import dev.muazmemis.getmobil_dev.entity.Invoice;
import dev.muazmemis.getmobil_dev.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static dev.muazmemis.getmobil_dev.helper.LogHelperComponent.logInfo;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, ProductStockUpdateDto> kafkaTemplateProduct;
    private final KafkaTemplate<String, Order> kafkaTemplateOrder;
    private final KafkaTemplate<String, Invoice> kafkaTemplateInvoice;

    private final KafkaTemplate<String, AlertManagerRequest  > kafkaTemplateTest;
    public void sendMessageAlert(String key, AlertManagerRequest message) {
        String topic = "Alert";
        kafkaTemplateTest.send(topic, key, message);
        logInfo("Message sent to Kafka topic: {}, key: {}, value: {}", topic, key, message);
    }

    public void sendMessageProduct(String key, ProductStockUpdateDto stockUpdate) {
        String topic = "product-topic";
        kafkaTemplateProduct.send(topic, key, stockUpdate);
        logInfo("Message sent to Kafka topic: {}, key: {}, value: {}", topic, key, stockUpdate);
    }

    public void sendMessageOrder(String key, Order order) {
        String topic = "order-topic";
        kafkaTemplateOrder.send("order-topic", key, order);
        logInfo("Message sent to Kafka topic: {}, key: {}, value: {}", topic, key, order);
    }

    public void sendMessageInvoice(String key, Invoice invoice) {
        String topic = "invoice-topic";
        kafkaTemplateInvoice.send(topic, key, invoice);
        logInfo("Message sent to Kafka topic: {}, key: {}, value: {}", topic, key, invoice);
    }

}
