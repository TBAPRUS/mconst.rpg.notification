package mconst.rpg.notification.controllers;

import lombok.extern.slf4j.Slf4j;
import mconst.rpg.notification.models.dtos.CreatedOrderEvent;
import mconst.rpg.notification.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController {
    private NotificationService notificationService;

    @Autowired
    KafkaTemplate<String, CreatedOrderEvent> kafkaTemplate;

    public OrderController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "orders.created", groupId = "Orders", containerFactory = "kafkaListenerContainerFactory")
    public void listenCreatedOrder(CreatedOrderEvent createdOrderEvent) {
        try {
            notificationService.notify(createdOrderEvent);
        } catch (Exception exception) {
            log.info("Got exception");
            log.error(exception.toString());
        }
    }

    @GetMapping("/test")
    public String test() {
        kafkaTemplate.send("orders.created", new CreatedOrderEvent("d6e53247-b2ff-42fe-9225-e9886d6519b3", 1L, 1L));
        return "kafka";
    }
}
