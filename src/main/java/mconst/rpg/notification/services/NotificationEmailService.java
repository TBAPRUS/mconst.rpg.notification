package mconst.rpg.notification.services;

import mconst.rpg.notification.models.dtos.CreatedOrderEvent;
import mconst.rpg.notification.models.entities.MailEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificationEmailService implements NotificationService {
    private UserService userService;
    private EmailService emailService;

    public NotificationEmailService(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    public void notify(CreatedOrderEvent createdOrderEvent) {
        var user = userService.getUserById(createdOrderEvent.getUserId());
        String message = "Заказ из " + createdOrderEvent.getCount() + " товаров на сумму " + createdOrderEvent.getCost() + " копеек заказан";
        var email = new MailEntity(
            user.getEmail(), message
        );
        emailService.sendEmail(email);
    }
}
