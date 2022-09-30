package com.example.instaclone.controller;

import com.example.instaclone.exception.Status434SubscriptionException;
import com.example.instaclone.exception.entity.Status404UserNotFoundException;
import com.example.instaclone.exception.file.Status432InvalidFileException;
import com.example.instaclone.exception.file.Status432InvalidFileNameException;
import com.example.instaclone.exception.file.Status433StorageException;
import com.example.instaclone.model.Post;
import com.example.instaclone.model.Subscription;
import com.example.instaclone.model.User;
import com.example.instaclone.model.notification.Notification;
import com.example.instaclone.service.NotificationService;
import com.example.instaclone.service.PostService;
import com.example.instaclone.service.UserService;
import com.example.instaclone.service.facade.IAuthFacade;
import com.example.instaclone.service.impl.SubscriptionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final IAuthFacade authFacade;
    private final SubscriptionServiceImpl subscriptionServiceImpl;
    private final PostService postService;
    private final NotificationService notificationService;

    @PutMapping("/update/avatar")
    public ResponseEntity<User> updateAvatar(@RequestParam("image") MultipartFile file)
            throws Status432InvalidFileException, Status433StorageException, Status432InvalidFileNameException,
            Status404UserNotFoundException {
        userService.updatePhoto(file);
        return ResponseEntity.ok(userService.findByUsername(authFacade.getUsername()));
    }

    @PostMapping("/{userId}/subscribe")
    public ResponseEntity<Subscription> subscribeUser(@PathVariable Long userId)
            throws Status434SubscriptionException, Status404UserNotFoundException {
        return ResponseEntity.ok(subscriptionServiceImpl.subscribeUser(userId));

    }

    @PostMapping("/{userId}/unsubscribe")
    public ResponseEntity<User> unsubscribeUser(@PathVariable Long userId)
            throws Status404UserNotFoundException {
        subscriptionServiceImpl.removeSubscription(userId);
        return ResponseEntity.ok(userService.findById(userId));
    }

    @GetMapping("{userId}/posts")
    public ResponseEntity<List<Post>> getAllPostsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(postService.findAllPostsByUserId(userId));
    }

    @GetMapping("{userId}/subscriptions")
    public ResponseEntity<List<User>> getAllSubscriptionsByLoggedInUser(@PathVariable Long userId) {
        return ResponseEntity.ok(subscriptionServiceImpl.getAllSubscriptionsByUserId(userId));
    }

    @GetMapping("{userId}/subscribers")
    public ResponseEntity<List<User>> getAllSubscribersByCurrentUser(@PathVariable Long userId) {
        return ResponseEntity.ok(subscriptionServiceImpl.getAllSubscribersByUserId(userId));
    }

    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> getAllNotificationsByCurrentUser()
            throws Status404UserNotFoundException {
        return ResponseEntity.ok(notificationService.findAllNotificationsByCurrentUser());
    }

}
