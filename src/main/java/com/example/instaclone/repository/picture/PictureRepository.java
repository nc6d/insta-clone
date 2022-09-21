package com.example.instaclone.repository.picture;

import com.example.instaclone.model.picture.Picture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PictureRepository extends CrudRepository<Picture, Long> {
}
