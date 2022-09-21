package com.example.instaclone.service.impl;

import com.example.instaclone.exception.Status409UserAlreadyRegistered;
import com.example.instaclone.exception.entity.Status404UserNotFoundException;
import com.example.instaclone.exception.file.Status422StorageException;
import com.example.instaclone.model.ImageMetadata;
import com.example.instaclone.model.User;
import com.example.instaclone.model.picture.ProfilePicture;
import com.example.instaclone.repository.user.UserRepository;
import com.example.instaclone.service.UserService;
import com.example.instaclone.service.facade.IAuthFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final IAuthFacade authFacade;
    private final ImageStorageService imageStorageService;

    @Override
    public User register(User user) throws Status409UserAlreadyRegistered {
        userRepository.findByUsername(user.getUsername())
                .ifPresent(finalUser -> {
                    throw new Status409UserAlreadyRegistered("Username " + user.getUsername() + " is already exists. Try another one!");
                });
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return saveUser(user);
    }

    @Override
    public User findById(Long id) throws Status404UserNotFoundException {
        return userRepository.findById(id).orElseThrow(
                () -> new Status404UserNotFoundException("No user with ID " + id));
    }

    @Override
    public User findByUsername(String username) throws Status404UserNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new Status404UserNotFoundException("No user with username " + username));
    }

    @Override
    public User updateInfo(User user) {
        return userRepository.findByUsername(user.getUsername())
                .map(finalUser -> {
                    log.info("User {} updated his info", finalUser.getUsername());

                    return saveUser(User.builder()
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .username(user.getUsername())
                            .build());

                }).orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User %s not exists", user.getUsername())));

    }


    @Override
    public void updatePhoto(MultipartFile file)
            throws Status404UserNotFoundException, Status422StorageException {
        User user = findByUsername(authFacade.getUsername());
        ImageMetadata image = imageStorageService.upload(file, user);
        userRepository.findByUsername(user.getUsername())
                .map(finalUser -> {
                    ProfilePicture profilePicture = new ProfilePicture(image.getUri(), user);
                    finalUser.addAvatar(profilePicture);
                    imageStorageService.saveProfilePicture(profilePicture);

                    log.info("User {} updated his profile photo", finalUser.getUsername());

                    return saveUser(finalUser);

                }).orElseThrow(() -> new Status404UserNotFoundException(
                        String.format("User %s not exists", user.getUsername())));


    }

    private User saveUser(User user) {
        return userRepository.save(user);
    }

}
