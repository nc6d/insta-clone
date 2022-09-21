package com.example.instaclone.repository.post;

import com.example.instaclone.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

//    List<Post> findAllPosts();

    List<Post> findAllByAuthorId(Long id);


}

