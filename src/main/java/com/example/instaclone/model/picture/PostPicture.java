package com.example.instaclone.model.picture;

import com.example.instaclone.model.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "post_picture")
public class PostPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Post post;

    private String uri;

    public PostPicture(String uri, Post post) {
        this.post = post;
        this.uri = uri;
    }
}
