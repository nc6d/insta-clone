package com.example.instaclone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    String caption;

    Integer likesQty;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    @ToString.Exclude
    Set<Comment> comments;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    @ToString.Exclude
    Set<Like> likes;

    @Transient
    List<String> imageUris;

    LocalDateTime publicationDate;

    @PrePersist
    public void publicationDate() {
        this.publicationDate = LocalDateTime.now();
        this.likesQty = 0;
    }
}
