package com.example.instaclone.repository.post;

import com.example.instaclone.model.Post;
import com.example.instaclone.model.SponsoredPost;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SponsoredPostRepository extends CrudRepository<SponsoredPost, Long> {
    Optional<SponsoredPost> findByPost(Post post);
}
