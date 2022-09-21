package com.example.instaclone.service;

import com.example.instaclone.exception.entity.Status404PostNotFoundException;
import com.example.instaclone.exception.entity.Status404UserNotFoundException;
import com.example.instaclone.exception.file.Status412InvalidFileException;
import com.example.instaclone.exception.file.Status412InvalidFileNameException;
import com.example.instaclone.exception.file.Status422StorageException;
import com.example.instaclone.model.Post;
import com.example.instaclone.model.SponsoredPost;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {

//    List<Post> findAllPosts();
//    Post createPost(MultipartFile file, String caption);
//    SponsoredPost createPost(MultipartFile file, String caption, Long sponsorId);

    Post createPost(MultipartFile[] files, String caption) throws Status412InvalidFileException, Status422StorageException, Status412InvalidFileNameException, Status404UserNotFoundException;

    SponsoredPost createPost(MultipartFile[] files, String caption, Long sponsorId) throws Status412InvalidFileException, Status422StorageException, Status412InvalidFileNameException, Status404UserNotFoundException;

    Post findPostById(Long id) throws Status404PostNotFoundException;
    List<Post> findAllPostsByUserId(Long id);

//    List<Post> findRecommendationsById(Long id);
}
