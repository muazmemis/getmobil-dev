package dev.muazmemis.getmobil_dev.controller;

import dev.muazmemis.getmobil_dev.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RequestMapping("/alerts")
@RestController
@RequiredArgsConstructor
public class AlertController {

    private final KafkaProducerService kafkaProducerService;

    @PostMapping
    public void sendAlert(@RequestBody AlertManagerRequest message)  {
        message.setExternalURL(message.getExternalURL());
        message.setHostName(getHostname());
        kafkaProducerService.sendMessageAlert("firing", message);
    }

    private String getHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "unknown-host";
        }
    }

}
