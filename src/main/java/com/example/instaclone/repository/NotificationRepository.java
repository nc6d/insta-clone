package com.example.instaclone.repository;

import com.example.instaclone.model.User;
import com.example.instaclone.model.notification.Notification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
    Optional<List<Notification>> findAllByReceiver(User user);
}
