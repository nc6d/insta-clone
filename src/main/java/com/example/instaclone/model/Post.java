package com.example.instaclone.model;

import com.example.instaclone.model.picture.PostPicture;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    User author;

//    String imageUrl;

    String caption;

    Integer likesQty;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "post")
    Set<Comment> comments;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "post")
    Set<Like> likes;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "post")
    List<PostPicture> images;

    LocalDateTime publicationDate;

    @PrePersist
    public void publicationDate() {
        this.publicationDate = LocalDateTime.now();
        this.likesQty = 0;
    }
}
