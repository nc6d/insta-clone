package com.example.instaclone.service;

import com.example.instaclone.exception.entity.Status404UserNotFoundException;
import com.example.instaclone.model.notification.Notification;
import com.example.instaclone.model.notification.NotificationEntity;
import com.example.instaclone.model.notification.NotificationType;

import java.util.List;

public interface NotificationService {
    void sendNotificationToRecipients(Notification notification, NotificationType notificationType) throws Status404UserNotFoundException;

    List<NotificationEntity> findAllNotificationsByCurrentUser() throws Status404UserNotFoundException;
}
