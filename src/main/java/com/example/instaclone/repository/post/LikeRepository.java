package com.example.instaclone.repository.post;

import com.example.instaclone.model.Like;
import com.example.instaclone.model.Post;
import com.example.instaclone.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends CrudRepository<Like, Long> {
    Optional<Like> findByPostAndAuthor(Post post, User author);
}
