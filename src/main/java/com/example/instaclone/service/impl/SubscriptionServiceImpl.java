package com.example.instaclone.service.impl;

import com.example.instaclone.exception.Status409SubscriptionException;
import com.example.instaclone.exception.entity.Status404UserNotFoundException;
import com.example.instaclone.model.Subscription;
import com.example.instaclone.model.User;
import com.example.instaclone.model.notification.NewSubscriptionNotification;
import com.example.instaclone.model.notification.Notification;
import com.example.instaclone.model.notification.NotificationType;
import com.example.instaclone.repository.user.SubscriptionRepository;
import com.example.instaclone.service.NotificationService;
import com.example.instaclone.service.SubscriptionService;
import com.example.instaclone.service.UserService;
import com.example.instaclone.service.facade.IAuthFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;
    private final IAuthFacade authFacade;
    private final NotificationService notificationService;

    @Override
    public Subscription subscribeUser(Long followedId) throws Status409SubscriptionException, Status404UserNotFoundException {

        User follower = userService.findByUsername(authFacade.getUsername());
        User followed = userService.findById(followedId);

        if (follower != followed && !subscriptionRepository.existsByFollowerAndFollowed(follower, followed)) {
            Subscription subscription = Subscription.builder()
                    .follower(follower)
                    .followed(followed)
                    .build();

            Notification notification = new NewSubscriptionNotification(followed);
            log.info(notification.sendNotification());
            notificationService.sendNotificationToRecipients(notification, NotificationType.SUBSCRIPTION);

            return subscriptionRepository.save(subscription);
        } else {
            throw new Status409SubscriptionException("Subscription is impossible");
        }
    }

    @Override
    public void removeSubscription(Long unfollowedId) throws Status404UserNotFoundException {

        User follower = userService.findByUsername(authFacade.getUsername());
        User unfollowed = userService.findById(unfollowedId);
        subscriptionRepository.deleteByFollowerAndFollowed(follower, unfollowed);


    }

    public List<User> getAllSubscriptionsByUserId(Long userId) {
        return subscriptionRepository.findAllFollowersByFollowedId(userId)
                .stream()
                .map(Subscription::getFollower)
                .collect(Collectors.toList());
    }

    public List<User> getAllSubscribersByUserId(Long userId) {
        return subscriptionRepository.findAllFollowedByFollowerId(userId)
                .stream()
                .map(Subscription::getFollowed)
                .collect(Collectors.toList());
    }
}
