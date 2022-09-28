package com.example.instaclone.service;

import com.example.instaclone.exception.entity.Status404UserNotFoundException;
import com.example.instaclone.model.notification.INotification;
import com.example.instaclone.model.notification.Notification;
import com.example.instaclone.model.notification.NotificationType;

import java.util.List;

public interface NotificationService {
    void sendNotificationToRecipients(INotification INotification, NotificationType notificationType) throws Status404UserNotFoundException;

    List<Notification> findAllNotificationsByCurrentUser() throws Status404UserNotFoundException;
}
