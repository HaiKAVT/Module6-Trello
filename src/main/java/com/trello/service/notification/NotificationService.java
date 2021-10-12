package com.trello.service.notification;

import com.trello.model.Notification;
import com.trello.service.GeneralService;

public interface NotificationService extends GeneralService<Notification> {

    Iterable<Notification> findByUserId(Long userId);

    Iterable<Notification> saveAll(Iterable<Notification> notifications);

    void allRead(Long userId);
}
