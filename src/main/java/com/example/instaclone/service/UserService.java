package com.example.instaclone.service;

import com.example.instaclone.exception.Status409UserAlreadyRegistered;
import com.example.instaclone.exception.entity.Status404UserNotFoundException;
import com.example.instaclone.exception.file.Status412InvalidFileException;
import com.example.instaclone.exception.file.Status412InvalidFileNameException;
import com.example.instaclone.exception.file.Status422StorageException;
import com.example.instaclone.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    User register(User user) throws Status409UserAlreadyRegistered;

    User findById(Long id) throws Status404UserNotFoundException;

    User findByUsername(String username) throws Status404UserNotFoundException;

    User updateInfo(User user);

    void updatePhoto(MultipartFile file) throws Status412InvalidFileException, Status422StorageException, Status412InvalidFileNameException, Status404UserNotFoundException;


//    User updatePhoto(MultipartFile[] files);
}
