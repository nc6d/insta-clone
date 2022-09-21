package com.example.instaclone.service.impl;

import com.example.instaclone.exception.file.Status422StorageException;
import com.example.instaclone.model.ImageMetadata;
import com.example.instaclone.model.User;
import com.example.instaclone.model.picture.PostPicture;
import com.example.instaclone.model.picture.ProfilePicture;
import com.example.instaclone.repository.picture.PostPictureRepository;
import com.example.instaclone.repository.picture.ProfilePictureRepository;
import com.example.instaclone.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ImageStorageService implements FileStorageService {

    private final FileStorageServiceImpl fileStorageServiceImpl;
    private final ProfilePictureRepository profilePictureRepository;
    private final PostPictureRepository postPictureRepository;

    @Override
    public ImageMetadata upload(MultipartFile file, User user) throws Status422StorageException {

        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        log.info("storing photo {}", filename);

        return fileStorageServiceImpl.upload(file, user);

    }

    public void saveProfilePicture(ProfilePicture profilePicture){
        profilePictureRepository.save(profilePicture);
    }

    public void savePostPicture(PostPicture postPicture){
        postPictureRepository.save(postPicture);
    }



}
