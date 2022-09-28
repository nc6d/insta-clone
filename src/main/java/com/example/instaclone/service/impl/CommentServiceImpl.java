package com.example.instaclone.service.impl;

import com.example.instaclone.exception.Status430AlreadyExists;
import com.example.instaclone.exception.entity.Status404CommentNotFoundException;
import com.example.instaclone.exception.entity.Status404PostNotFoundException;
import com.example.instaclone.exception.entity.Status404UserNotFoundException;
import com.example.instaclone.model.Comment;
import com.example.instaclone.model.Post;
import com.example.instaclone.model.SponsoredPost;
import com.example.instaclone.model.User;
import com.example.instaclone.model.notification.INotification;
import com.example.instaclone.model.notification.NewCommentINotification;
import com.example.instaclone.model.notification.NotificationType;
import com.example.instaclone.repository.post.CommentRepository;
import com.example.instaclone.repository.post.SponsoredPostRepository;
import com.example.instaclone.service.CommentService;
import com.example.instaclone.service.NotificationService;
import com.example.instaclone.service.PostService;
import com.example.instaclone.service.UserService;
import com.example.instaclone.service.facade.IAuthFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentServiceImpl implements CommentService {

    private final IAuthFacade authFacade;
    private final CommentRepository commentRepository;
    private final SponsoredPostRepository sponsoredPostRepository;
    private final PostService postService;
    private final UserService userService;
    private final NotificationService notificationService;

    @Override
    public Comment createComment(Long postId, String text) throws Status430AlreadyExists, Status404UserNotFoundException, Status404PostNotFoundException {

        User user = userService.findByUsername(authFacade.getUsername());
        Post post = postService.findPostById(postId);

        SponsoredPost sponsoredPost = sponsoredPostRepository.findByPost(post)
                .orElseThrow(() -> new Status430AlreadyExists("Post already exists"));

        log.info("Creating comment by user {}", user.getUsername());

        Comment comment = Comment.builder()
                .content(text)
                .post(post)
                .author(user)
                .build();

        INotification INotification = new NewCommentINotification(post, post.getAuthor());
        if (sponsoredPost.getPost() == post) {
            INotification.addRecipient(sponsoredPost.getSponsor());
        }
        log.info(INotification.sendNotification());

        notificationService.sendNotificationToRecipients(INotification, NotificationType.COMMENT);

        return commentRepository.save(comment);
    }

    @Override
    public Comment findCommentById(Long id) throws Status404CommentNotFoundException {
        return commentRepository.findById(id).orElseThrow(() ->
                new Status404CommentNotFoundException("Comment with" + id + "not found"));
    }

    @Override
    public Set<Comment> findAllCommentsByPostId(Long id) throws Status404PostNotFoundException {
        return postService.findPostById(id).getComments();
    }

}
