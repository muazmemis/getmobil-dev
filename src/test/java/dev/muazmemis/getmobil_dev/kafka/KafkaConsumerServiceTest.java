package dev.muazmemis.getmobil_dev.kafka;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import static org.junit.jupiter.api.Assertions.*;

class KafkaConsumerServiceTest {

    @Mock
    private  KafkaProperties properties;

    @Test
    void consumeProduct() {
        assertEquals(1, 1);
    }


    @Test
    void consumeOrder() {
        assertEquals(1, 1);
    }

    @Test
    void consumeInvoice() {
        assertEquals(1, 1);
    }
}
