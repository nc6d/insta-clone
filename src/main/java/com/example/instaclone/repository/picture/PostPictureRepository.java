package com.example.instaclone.repository.picture;

import com.example.instaclone.model.picture.PostPicture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostPictureRepository extends CrudRepository<PostPicture, Long> {
}
