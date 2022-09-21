package com.example.instaclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class ImageMetadata {

    private String filename;

    private String uri;

    private String fileType;


}
