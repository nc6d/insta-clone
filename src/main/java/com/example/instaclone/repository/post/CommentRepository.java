package com.example.instaclone.repository.post;

import com.example.instaclone.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    Optional<List<Comment>> getAllByPostId(Long postId);
}
