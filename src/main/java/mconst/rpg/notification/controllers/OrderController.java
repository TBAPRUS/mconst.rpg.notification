package mconst.rpg.notification.controllers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private KafkaTemplate<String, String> kafkaTemplate;

    public OrderController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "createdOrders")
    public void listenCreatedOrders(String message) {
        System.out.println("Received message: " + message);
    }

    @GetMapping("/test")
    public String test() {
        kafkaTemplate.send("createdOrders", "hi");
        return "good";
    }
}
