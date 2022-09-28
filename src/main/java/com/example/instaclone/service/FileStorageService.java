package com.example.instaclone.service;

import com.example.instaclone.exception.file.Status432InvalidFileException;
import com.example.instaclone.exception.file.Status432InvalidFileNameException;
import com.example.instaclone.exception.file.Status433StorageException;
import com.example.instaclone.model.ImageMetadata;
import com.example.instaclone.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    //    ImageMetadata upload(MultipartFile file, User user);
    ImageMetadata upload(MultipartFile files, User user) throws Status433StorageException, Status432InvalidFileException, Status432InvalidFileNameException;
}
