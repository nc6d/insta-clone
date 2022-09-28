package com.example.instaclone.model.notification;

import com.example.instaclone.model.User;
import lombok.Data;


@Data
public class NewSubscriptionINotification implements INotification {

    private User userWasSubscribed;
    private NotificationType notificationType;

    public NewSubscriptionINotification(User userWasSubscribed) {
        this.userWasSubscribed = userWasSubscribed;
        this.notificationType = NotificationType.SUBSCRIPTION;
        recipients.add(userWasSubscribed);
    }

    @Override
    public String sendNotification() {
        return String.format("Now you're following %s", userWasSubscribed);
    }
}

