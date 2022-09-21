package com.example.instaclone.service;

import com.example.instaclone.exception.Status409SubscriptionException;
import com.example.instaclone.exception.entity.Status404UserNotFoundException;
import com.example.instaclone.model.Subscription;

public interface SubscriptionService {
    Subscription subscribeUser(Long followedId) throws Status409SubscriptionException, Status404UserNotFoundException;
    void removeSubscription(Long unfollowedId) throws Status404UserNotFoundException;
}
