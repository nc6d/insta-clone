package com.example.instaclone.model.notification;

import com.example.instaclone.model.User;

import java.util.HashSet;
import java.util.Set;

public interface Notification {
    Set<User> recipients = new HashSet<>();
    String sendNotification();
    default void addRecipient(User user){
        recipients.add(user);
    }
    default void deleteRecipient(User user){
        recipients.remove(user);
    }
    default void clearRecipients(){
        recipients.clear();
    }
    default Set<User> getAllRecipients(){
        return recipients;
    }

}
