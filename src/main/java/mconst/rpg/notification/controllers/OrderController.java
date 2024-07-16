package mconst.rpg.notification.controllers;

import mconst.rpg.notification.models.dtos.CreatedOrderEvent;
import mconst.rpg.notification.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private NotificationService notificationService;

    @Autowired
    KafkaTemplate<String, CreatedOrderEvent> kafkaTemplate;

    public OrderController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "orders.created", groupId = "Orders")
    public void listenCreatedOrder(CreatedOrderEvent createdOrderEvent) {
        notificationService.notify(createdOrderEvent);
    }

    @GetMapping("/test")
    public void test() {
        kafkaTemplate.send("orders.created", new CreatedOrderEvent(1L, 1L, 1L));
    }
}
