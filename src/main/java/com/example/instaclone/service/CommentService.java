package com.example.instaclone.service;

import com.example.instaclone.exception.Status403AlreadyExists;
import com.example.instaclone.exception.entity.Status404CommentNotFoundException;
import com.example.instaclone.exception.entity.Status404PostNotFoundException;
import com.example.instaclone.exception.entity.Status404UserNotFoundException;
import com.example.instaclone.model.Comment;

import java.util.Set;

public interface CommentService {

    Comment createComment(Long postId, String text) throws Status403AlreadyExists, Status404UserNotFoundException, Status404PostNotFoundException;

    Comment findCommentById(Long id) throws Status404CommentNotFoundException;

    Set<Comment> findAllCommentsByPostId(Long id) throws Status404CommentNotFoundException, Status404PostNotFoundException;
}
