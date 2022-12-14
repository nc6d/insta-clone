package com.example.instaclone.service.impl;

import com.example.instaclone.exception.entity.Status404UserNotFoundException;
import com.example.instaclone.model.User;
import com.example.instaclone.model.notification.INotification;
import com.example.instaclone.model.notification.Notification;
import com.example.instaclone.model.notification.NotificationType;
import com.example.instaclone.repository.NotificationRepository;
import com.example.instaclone.service.NotificationService;
import com.example.instaclone.service.UserService;
import com.example.instaclone.service.facade.IAuthFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final IAuthFacade authFacade;
    private final UserService userService;

    @Override
    public void sendNotificationToRecipients(INotification INotification, NotificationType notificationType)
            throws Status404UserNotFoundException {
        User currentUser = userService.findByUsername(authFacade.getUsername());
        INotification.getAllRecipients().forEach(
                user -> notificationRepository.save(Notification.builder()
                        .sender(currentUser)
                        .receiver(user)
                        .notificationType(notificationType)
                        .build()));
        INotification.clearRecipients();
    }

    @Override
    public List<Notification> findAllNotificationsByCurrentUser()
            throws Status404UserNotFoundException {
        return notificationRepository
                .findAllByReceiver(userService.findByUsername(authFacade.getUsername()))
                .orElseThrow(() -> new Status404UserNotFoundException("User not found"))
                .stream()
                .sorted(Comparator
                        .comparing(Notification::getTimestamp)
                        .reversed())
                .toList();
    }

}
