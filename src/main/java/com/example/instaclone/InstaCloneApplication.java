package com.example.instaclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.instaclone.repository")
@EntityScan("com.example.instaclone.model")
public class InstaCloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstaCloneApplication.class, args);
    }

}


