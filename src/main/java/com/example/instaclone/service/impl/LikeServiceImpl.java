package com.example.instaclone.service.impl;

import com.example.instaclone.exception.Status430AlreadyExists;
import com.example.instaclone.exception.entity.Status404LikeNotFoundException;
import com.example.instaclone.exception.entity.Status404PostNotFoundException;
import com.example.instaclone.exception.entity.Status404UserNotFoundException;
import com.example.instaclone.model.Like;
import com.example.instaclone.model.Post;
import com.example.instaclone.model.SponsoredPost;
import com.example.instaclone.model.User;
import com.example.instaclone.model.notification.INotification;
import com.example.instaclone.model.notification.NewLikeINotification;
import com.example.instaclone.model.notification.NotificationType;
import com.example.instaclone.repository.post.LikeRepository;
import com.example.instaclone.repository.post.SponsoredPostRepository;
import com.example.instaclone.service.LikeService;
import com.example.instaclone.service.NotificationService;
import com.example.instaclone.service.PostService;
import com.example.instaclone.service.UserService;
import com.example.instaclone.service.facade.IAuthFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final SponsoredPostRepository sponsoredPostRepository;
    private final PostService postService;
    private final IAuthFacade authFacade;
    private final UserService userService;
    private final NotificationService notificationService;

    @Override
    public Like likePostById(Long postId) throws Status430AlreadyExists, Status404UserNotFoundException, Status404PostNotFoundException {
        Post likedPost = postService.findPostById(postId);
        User user = userService.findByUsername(authFacade.getUsername());

        SponsoredPost sponsoredPost = sponsoredPostRepository.findByPost(likedPost)
                .orElseThrow(() -> new Status430AlreadyExists("Post already exists"));

        if (likeRepository.findByPostAndAuthor(likedPost, user).isEmpty()) {
            likedPost.setLikesQty(likedPost.getLikesQty() + 1);
            log.info("Post {} was liked by {}", likedPost, user);

            Like like = Like.builder()
                    .post(likedPost)
                    .author(user)
                    .build();

            INotification INotification = new NewLikeINotification(likedPost, likedPost.getAuthor());
            if (sponsoredPost.getPost() == likedPost) {
                INotification.addRecipient(sponsoredPost.getSponsor());
            }

            log.info(INotification.sendNotification());
            notificationService.sendNotificationToRecipients(INotification, NotificationType.LIKE);

            return likeRepository.save(like);

        } else throw new Status430AlreadyExists("Like is already exists");
    }

    @Override
    public void unlikePostById(Long postId) throws Status404UserNotFoundException, Status404PostNotFoundException, Status404LikeNotFoundException {
        User user = userService.findByUsername(authFacade.getUsername());
        Post unlikedPost = postService.findPostById(postId);


        Like likeToRemove = likeRepository.findByPostAndAuthor(unlikedPost, user)
                .orElseThrow(() -> new Status404LikeNotFoundException("Like not found"));

        log.info("Post {} was unliked by {}", unlikedPost, user);
        unlikedPost.setLikesQty(unlikedPost.getLikesQty() - 1);
        likeRepository.delete(likeToRemove);

    }

    @Override
    public List<User> findAllLikesByPostId(Long postId) throws Status404PostNotFoundException {
        return postService.findPostById(postId).getLikes().stream()
                .map(Like::getAuthor)
                .collect(Collectors.toList());
    }

}
