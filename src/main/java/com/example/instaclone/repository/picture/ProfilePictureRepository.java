package com.example.instaclone.repository.picture;

import com.example.instaclone.model.picture.ProfilePicture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilePictureRepository extends CrudRepository<ProfilePicture, Long> {
}
