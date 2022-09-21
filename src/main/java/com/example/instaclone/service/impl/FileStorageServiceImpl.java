package com.example.instaclone.service.impl;

import com.example.instaclone.exception.file.Status412InvalidFileException;
import com.example.instaclone.exception.file.Status412InvalidFileNameException;
import com.example.instaclone.exception.file.Status422StorageException;
import com.example.instaclone.model.ImageMetadata;
import com.example.instaclone.model.User;
import com.example.instaclone.service.FileStorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDirectory;

    @Value("${file.path.prefix}")
    private String filePathPrefix;

    private final Environment environment;

    @Autowired
    public FileStorageServiceImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public ImageMetadata upload(MultipartFile file, User user) throws Status422StorageException {

            String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

            try {
                if (file.isEmpty()) {
                    throw new Status412InvalidFileException("Failed to store empty file - " + filename);
                }
                if (filename.contains("..")) {
                    throw new Status412InvalidFileNameException("Invalid filename - " + filename);
                }

                String extension = FilenameUtils.getExtension(filename);
                String newFilename = UUID.randomUUID() + "." + extension;

                try (InputStream inputStream = file.getInputStream()) {
                    Path userDir = Paths.get(uploadDirectory + "/" + user.getId());

                    if (Files.notExists(userDir))
                        Files.createDirectory(userDir);

                    Files.copy(inputStream, userDir.resolve(newFilename), StandardCopyOption.REPLACE_EXISTING);
                }

                String port = environment.getProperty("local.server.port");
                String hostName = "localhost";
//            String hostName = InetAddress.getLocalHost().getHostName();
                String fileUrl = String.format("https://%s:%s%s/%s/%s", hostName, port, filePathPrefix, user.getId(), newFilename);

                return new ImageMetadata(newFilename, fileUrl, Objects.requireNonNull(file.getContentType()));

            } catch (IOException | Status412InvalidFileException e) {
                throw new Status422StorageException("Failed to store file " + filename, e);
            } catch (Status412InvalidFileNameException e) {
                throw new RuntimeException(e);
            }


             }
}
