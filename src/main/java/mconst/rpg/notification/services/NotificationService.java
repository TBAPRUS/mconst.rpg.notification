package mconst.rpg.notification.services;

import mconst.rpg.notification.models.dtos.CreatedOrderEvent;

public interface NotificationService {
    void notify(CreatedOrderEvent createdOrderEvent);
}
