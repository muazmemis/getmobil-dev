package dev.muazmemis.getmobil_dev.kafka;

import dev.muazmemis.getmobil_dev.entity.Invoice;
import dev.muazmemis.getmobil_dev.entity.Order;
import dev.muazmemis.getmobil_dev.service.InvoiceService;
import dev.muazmemis.getmobil_dev.service.OrderService;
import dev.muazmemis.getmobil_dev.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static dev.muazmemis.getmobil_dev.helper.LogHelperComponent.logInfo;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final String PRODUCT_TOPIC = "product-topic";
    private final String PRODUCT_GROUP_ID = "product-group";

    private final String ORDER_TOPIC = "order-topic";
    private final String ORDER_GROUP_ID = "order-group";

    private final String INVOICE_TOPIC = "invoice-topic";
    private final String INVOICE_GROUP_ID = "invoice-group";

    private final ProductService productService;
    private final OrderService orderService;
    private final InvoiceService invoiceService;

    @KafkaListener(topics = PRODUCT_TOPIC, groupId = PRODUCT_GROUP_ID)
    public void consumeProduct(Long id, Integer stock) {
        productService.updateStock(id, stock);
    }

    @KafkaListener(topics = ORDER_TOPIC, groupId = ORDER_GROUP_ID)
    public void consumeOrder(Order order) {
        logInfo("Order consumed: {}", order);
        orderService.save(order);
    }

    @KafkaListener(topics = INVOICE_TOPIC, groupId = INVOICE_GROUP_ID)
    public Invoice consumeInvoice(Invoice invoice) {
        return invoiceService.save(invoice);
    }

}
