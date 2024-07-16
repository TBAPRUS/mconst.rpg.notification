package mconst.rpg.notification.services;

import lombok.extern.slf4j.Slf4j;
import mconst.rpg.notification.models.entities.MailEntity;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void sendEmail(MailEntity mailEntity) {
        System.out.println(mailEntity);
        // TODO: email sending
    }
}
