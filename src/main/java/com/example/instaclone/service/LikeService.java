package com.example.instaclone.service;

import com.example.instaclone.exception.Status430AlreadyExists;
import com.example.instaclone.exception.entity.Status404LikeNotFoundException;
import com.example.instaclone.exception.entity.Status404PostNotFoundException;
import com.example.instaclone.exception.entity.Status404UserNotFoundException;
import com.example.instaclone.model.Like;
import com.example.instaclone.model.User;

import java.util.List;

public interface LikeService {

    Like likePostById(Long postId) throws Status430AlreadyExists, Status404LikeNotFoundException, Status404UserNotFoundException, Status404PostNotFoundException;
    void unlikePostById(Long postId) throws Status404UserNotFoundException, Status404PostNotFoundException, Status404LikeNotFoundException;

    List<User> findAllLikesByPostId(Long postId) throws Status404LikeNotFoundException, Status404PostNotFoundException;

}
