package com.example.instaclone.controller;

import com.example.instaclone.exception.entity.Status404CommentNotFoundException;
import com.example.instaclone.exception.entity.Status404LikeNotFoundException;
import com.example.instaclone.exception.entity.Status404PostNotFoundException;
import com.example.instaclone.exception.entity.Status404UserNotFoundException;
import com.example.instaclone.exception.file.Status432InvalidFileException;
import com.example.instaclone.exception.file.Status432InvalidFileNameException;
import com.example.instaclone.exception.file.Status433StorageException;
import com.example.instaclone.model.Comment;
import com.example.instaclone.model.Post;
import com.example.instaclone.model.SponsoredPost;
import com.example.instaclone.model.User;
import com.example.instaclone.service.CommentService;
import com.example.instaclone.service.LikeService;
import com.example.instaclone.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final LikeService likeService;

    @PutMapping("/create")
    public ResponseEntity<?> createPost(@RequestParam("image[]") MultipartFile[] files,
                                             @RequestParam("caption") @Nullable String caption,
                                             @RequestParam("sponsorId") @Nullable Long sponsorId) throws Status432InvalidFileException, Status433StorageException, Status432InvalidFileNameException, Status404UserNotFoundException {
        if (sponsorId != null) {
            SponsoredPost sponsoredPost = postService.createPost(files, caption, sponsorId);
            return ResponseEntity.ok(sponsoredPost);
        }
        Post post = postService.createPost(files, caption);
        return ResponseEntity.ok(post);
    }

    @GetMapping("{postId}/comments")
    public ResponseEntity<Set<Comment>> getCommentsByPostId (@PathVariable Long postId) throws Status404CommentNotFoundException, Status404PostNotFoundException {
        return ResponseEntity.ok(commentService.findAllCommentsByPostId(postId));
    }
    @GetMapping("/{postId}/likes")
    public ResponseEntity<List<User>> getUserLikesByPostId(@PathVariable Long postId) throws Status404LikeNotFoundException, Status404PostNotFoundException {
        return ResponseEntity.ok(likeService.findAllLikesByPostId(postId));

    }


}
