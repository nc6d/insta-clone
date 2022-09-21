package com.example.instaclone.service;

import com.example.instaclone.exception.file.Status412InvalidFileException;
import com.example.instaclone.exception.file.Status412InvalidFileNameException;
import com.example.instaclone.exception.file.Status422StorageException;
import com.example.instaclone.model.ImageMetadata;
import com.example.instaclone.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    //    ImageMetadata upload(MultipartFile file, User user);
    ImageMetadata upload(MultipartFile files, User user) throws Status422StorageException, Status412InvalidFileException, Status412InvalidFileNameException;
}
