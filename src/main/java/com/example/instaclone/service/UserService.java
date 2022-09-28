package com.example.instaclone.service;

import com.example.instaclone.exception.Status431UserAlreadyRegistered;
import com.example.instaclone.exception.entity.Status404UserNotFoundException;
import com.example.instaclone.exception.file.Status432InvalidFileException;
import com.example.instaclone.exception.file.Status432InvalidFileNameException;
import com.example.instaclone.exception.file.Status433StorageException;
import com.example.instaclone.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    User register(User user) throws Status431UserAlreadyRegistered;

    User findById(Long id) throws Status404UserNotFoundException;

    User findByUsername(String username) throws Status404UserNotFoundException;

    User updateInfo(User user);

    void updatePhoto(MultipartFile file) throws Status432InvalidFileException, Status433StorageException, Status432InvalidFileNameException, Status404UserNotFoundException;


//    User updatePhoto(MultipartFile[] files);
}
