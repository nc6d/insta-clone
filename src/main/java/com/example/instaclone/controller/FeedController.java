package com.example.instaclone.controller;

import com.example.instaclone.exception.Status403AlreadyExists;
import com.example.instaclone.exception.entity.Status404LikeNotFoundException;
import com.example.instaclone.exception.entity.Status404PostNotFoundException;
import com.example.instaclone.exception.entity.Status404UserNotFoundException;
import com.example.instaclone.model.Comment;
import com.example.instaclone.model.Like;
import com.example.instaclone.model.Post;
import com.example.instaclone.service.CommentService;
import com.example.instaclone.service.LikeService;
import com.example.instaclone.service.impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/feed")
public class FeedController {

    private final CommentService commentService;
    private final LikeService likeService;
    private final PostServiceImpl postServiceImpl;

    @GetMapping("/")
    public ResponseEntity<List<Post>> getAllPostsSortedByDateBySubscriptions() throws Status404UserNotFoundException {
        return ResponseEntity.ok(postServiceImpl.getAllPostsBySubscriptions());
    }


    @PutMapping("/comment-post/{postId}")
    public ResponseEntity<Comment> createComment(@PathVariable Long postId, @RequestParam String text)
            throws Status403AlreadyExists, Status404UserNotFoundException, Status404PostNotFoundException {

        Comment comment = commentService.createComment(postId, text);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PutMapping("/like-post/{postId}")
    public ResponseEntity<Like> likePost(@PathVariable Long postId)
            throws Status404LikeNotFoundException, Status403AlreadyExists, Status404UserNotFoundException, Status404PostNotFoundException {

        return ResponseEntity.ok(likeService.likePostById(postId));
    }

    @PutMapping("/unlike-post/{postId}")
    public void unlikePost(@PathVariable Long postId) throws Status404LikeNotFoundException, Status404UserNotFoundException, Status404PostNotFoundException {
        likeService.unlikePostById(postId);
    }
}
